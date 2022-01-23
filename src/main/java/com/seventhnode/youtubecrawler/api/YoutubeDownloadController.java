package com.seventhnode.youtubecrawler.api;

import com.seventhnode.youtubecrawler.entity.YoutubeDLRequest;
import com.seventhnode.youtubecrawler.entity.YoutubeDLResponse;
import com.seventhnode.youtubecrawler.exception.YoutubeDLException;
import com.seventhnode.youtubecrawler.util.DownloadProgressCallback;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyEmitter;

import java.io.IOException;

/**
 * RestController for YouTube Downloads
 */
@RestController
public class YoutubeDownloadController {

    ResponseBodyEmitter emitter;

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


        String directory = System.getProperty("user.home");
        emitter = new ResponseBodyEmitter();


        YoutubeDLRequest request = new YoutubeDLRequest(videoUrl, directory);
        request.setOption("ignore-errors");        // --ignore-errors
        request.setOption("output", "%(id)s");    // --output "%(id)s"
        request.setOption("retries", 10);        // --retries 10

        try {
            YoutubeDLResponse response = YoutubeDL.execute(request, new DownloadProgressCallback() {
                @Override
                public void onProgressUpdate(float progress, long etaInSeconds) {
                    try {
                        System.out.println(String.valueOf(progress) + "%");
                        emitter.send(String.valueOf(progress) + "%", MediaType.APPLICATION_JSON);
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

    @GetMapping(value = "progress")
    public ResponseBodyEmitter progress() {
        return emitter;
    }

}
