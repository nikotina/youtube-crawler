package com.seventhnode.youtubecrawler.api;

import com.seventhnode.youtubecrawler.entity.YoutubeDLRequest;
import com.seventhnode.youtubecrawler.entity.YoutubeDLResponse;
import com.seventhnode.youtubecrawler.exception.YoutubeDLException;
import com.seventhnode.youtubecrawler.util.DownloadProgressCallback;
import javassist.bytecode.stackmap.TypeData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.io.IOException;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.logging.Logger;

/**
 * RestController for YouTube Downloads
 */
@RestController
public class YoutubeDownloadController {
    private static final Logger LOGGER = Logger.getLogger(TypeData.ClassName.class.getName());
    @Autowired
    private Environment env;

    public static final Map<String, SseEmitter> sseEmitters = new ConcurrentHashMap<>();

    /**
     * Download YouTube Video
     *
     * @param videoId
     * @return ResponseBodyEmitter
     */
    @CrossOrigin
    @RequestMapping(path = "youtube/youtubedownload/{videoId}", method = RequestMethod.GET)
    public void download(@PathVariable String videoId) throws IOException {
        String videoUrl = "http://www.youtube.com/watch?v=" + videoId;


        String execDirectory = env.getProperty("youtube.executable");
        String downloadDirectory = env.getProperty("youtube.downloadDirectory");
        SseEmitter sseEmitter = new SseEmitter();
        sseEmitters.put(videoId, sseEmitter);
        sseEmitter.send(SseEmitter.event().name("VideoID").data(videoId));
        sseEmitter.onCompletion(() -> sseEmitters.remove(videoId));
        sseEmitter.onTimeout(() -> sseEmitters.remove(videoId));

        YoutubeDLRequest request = new YoutubeDLRequest(videoUrl, execDirectory);
        request.setOption("ignore-errors");        // --ignore-errors
        //request.setOption("output", "%(title)s.%(ext)s");    // --output "%(id)s"
        request.setOption("retries", 10);        // --retries 10
        request.setOption("output", downloadDirectory + "%(title)s.%(ext)s");
        try {
            YoutubeDLResponse response = YoutubeDL.execute(request, new DownloadProgressCallback() {
                @Override
                public void onProgressUpdate(float progress, long etaInSeconds) {
                    try {
                        //Logger.log(Level.FINE, progress + "%");
                        sseEmitter.send(progress, MediaType.APPLICATION_JSON);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            });

            String stdOut = response.getOut(); // Executable output
            System.out.println(stdOut);
        } catch (YoutubeDLException dlException) {
            System.out.println(dlException.getMessage());
            sseEmitter.completeWithError(dlException);
        }
        sseEmitter.complete();
    }

    @GetMapping(value = "progress/{videoId}")
    public SseEmitter progress(PathVariable videoId) {
        SseEmitter emitter = sseEmitters.get(videoId);
        return emitter;
    }

    @GetMapping(value = "emitters")
    public Set emitters() {
        Set<String> videoIdList = sseEmitters.keySet();
        return videoIdList;
    }
}
