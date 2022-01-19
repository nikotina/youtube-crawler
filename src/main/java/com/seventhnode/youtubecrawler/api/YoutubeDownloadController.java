package com.seventhnode.youtubecrawler.api;

import com.seventhnode.youtubecrawler.entity.YoutubeDLRequest;
import com.seventhnode.youtubecrawler.entity.YoutubeDLResponse;
import com.seventhnode.youtubecrawler.exception.YoutubeDLException;
import com.seventhnode.youtubecrawler.util.DownloadProgressCallback;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyEmitter;

import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@RestController
public class YoutubeDownloadController {

    @RequestMapping("youtubedownload/{videoId}")
    public ResponseBodyEmitter handleRequest(@PathVariable String videoId) {
        String videoUrl = "http://www.youtube.com/watch?v=" + videoId;

// Destination directory
        String directory = System.getProperty("user.home");
        final ResponseBodyEmitter emitter = new ResponseBodyEmitter();

// Build request
        YoutubeDLRequest request = new YoutubeDLRequest(videoUrl, directory);
        request.setOption("ignore-errors");        // --ignore-errors
        request.setOption("output", "%(id)s");    // --output "%(id)s"
        request.setOption("retries", 10);        // --retries 10

// Make request and return response
        try {
            YoutubeDLResponse response = YoutubeDL.execute(request, new DownloadProgressCallback() {
                @Override
                public void onProgressUpdate(float progress, long etaInSeconds) {
                    try {
                        System.out.println(String.valueOf(progress) + "%");
                        emitter.send(progress, MediaType.TEXT_PLAIN);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            });
// Response
            String stdOut = response.getOut(); // Executable output
        } catch (YoutubeDLException dlException) {
            System.out.println(dlException.getMessage());
            emitter.completeWithError(dlException);
        }
        emitter.complete();
        return emitter;
    }

}
