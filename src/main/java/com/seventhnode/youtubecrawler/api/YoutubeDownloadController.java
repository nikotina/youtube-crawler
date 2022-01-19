package com.seventhnode.youtubecrawler.api;
import com.seventhnode.youtubecrawler.entity.YoutubeDLRequest;
import com.seventhnode.youtubecrawler.entity.YoutubeDLResponse;
import com.seventhnode.youtubecrawler.exception.YoutubeDLException;
import com.seventhnode.youtubecrawler.util.DownloadProgressCallback;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyEmitter;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class YoutubeDownloadController {

    @RequestMapping("youtubedownload")
    public ResponseBodyEmitter handleRequest() {

        final ResponseBodyEmitter emitter = new ResponseBodyEmitter();
        ExecutorService service = Executors.newSingleThreadExecutor();
        service.execute(() -> {
            for (int i = 0; i < 1000; i++) {
                try {
                    emitter.send(i + " - ", MediaType.TEXT_PLAIN);

                    Thread.sleep(10);
                } catch (Exception e) {
                    e.printStackTrace();
                    emitter.completeWithError(e);
                    return;
                }
            }
            emitter.complete();
        });
        return emitter;
    }
    public void DownloadYoutubeFile(String videoURL) {
        // Destination directory
        String directory = System.getProperty("user.home");

// Build request
        YoutubeDLRequest request = new YoutubeDLRequest(videoURL, directory);
        request.setOption("ignore-errors");		// --ignore-errors
        request.setOption("output", "%(id)s");	// --output "%(id)s"
        request.setOption("retries", 10);		// --retries 10

// Make request and return response
        YoutubeDLResponse response;
        try {
            response = YoutubeDL.execute(request, new DownloadProgressCallback() {
                @Override
                public void onProgressUpdate(float progress, long etaInSeconds) {
                    System.out.println(String.valueOf(progress) + "%");
                }
            });
            // Response
            String stdOut = response.getOut(); // Executable output
            System.out.println(stdOut);
        } catch (YoutubeDLException dlException) {
            System.out.println(dlException.getMessage());
        }


    }
}
