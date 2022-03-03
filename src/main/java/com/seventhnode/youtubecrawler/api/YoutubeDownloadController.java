package com.seventhnode.youtubecrawler.api;

import com.seventhnode.youtubecrawler.entity.YoutubeDLRequest;
import com.seventhnode.youtubecrawler.entity.YoutubeDLResponse;
import com.seventhnode.youtubecrawler.exception.YoutubeDLException;
import com.seventhnode.youtubecrawler.util.DownloadProgressCallback;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * RestController for YouTube Downloads
 */
@RestController
public class YoutubeDownloadController {

    @Autowired
    private Environment env;

    public static final Map<String, SseEmitter> emitters = Collections.synchronizedMap(new HashMap<String, SseEmitter>());

    /**
     * Download YouTube Video
     *
     * @param videoId
     * @return ResponseBodyEmitter
     */
    @CrossOrigin
    @RequestMapping(path = "youtube/youtubedownload/{videoId}", method = RequestMethod.GET)
    public void download(@PathVariable String videoId) {
        String videoUrl = "http://www.youtube.com/watch?v=" + videoId;


        String execDirectory = ""; //env.getProperty("youtube.executable");
        String downloadDirectory = env.getProperty("youtube.downloadDirectory");
        SseEmitter emitter = new SseEmitter();
        emitters.put(videoId, emitter);

        YoutubeDLRequest request = new YoutubeDLRequest(videoUrl, execDirectory);
        request.setOption("ignore-errors");        // --ignore-errors
        request.setOption("output", "%(id)s");    // --output "%(id)s"
        request.setOption("retries", 10);        // --retries 10
        request.setOption("output", downloadDirectory + videoId + ".mp4");
        try {
            YoutubeDLResponse response = YoutubeDL.execute(request, new DownloadProgressCallback() {
                @Override
                public void onProgressUpdate(float progress, long etaInSeconds) {
                    try {
                        System.out.println(progress + "%");
                        emitter.send(progress + "%", MediaType.APPLICATION_JSON);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            });

            String stdOut = response.getOut(); // Executable output
            System.out.println(stdOut);
        } catch (YoutubeDLException dlException) {
            System.out.println(dlException.getMessage());
            emitter.completeWithError(dlException);
        }
        emitter.complete();
    }

    @GetMapping(value = "progress/{videoId}")
    public SseEmitter progress(PathVariable videoId) {
        SseEmitter emitter = emitters.get(videoId);
        return emitter;
    }
    @GetMapping(value = "emitters")
    public Set emitters(PathVariable videoId) {
        Set<String> videoIdList =  emitters.keySet();
        return videoIdList;
    }

}
