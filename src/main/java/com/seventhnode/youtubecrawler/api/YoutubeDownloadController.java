package com.seventhnode.youtubecrawler.api;
import com.seventhnode.youtubecrawler.entity.YoutubeDLRequest;
import com.seventhnode.youtubecrawler.entity.YoutubeDLResponse;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyEmitter;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
@RestController
public class YoutubeDownloadController {

    @RequestMapping("youtubedownload")
    public ResponseBodyEmitter handleRequest(String videoId) {
        String videoUrl = "http://www.youtube.com/watch?v=" + videoId;

// Destination directory
        String directory = System.getProperty("user.home");

// Build request
        YoutubeDLRequest request = new YoutubeDLRequest(videoUrl, directory);
        request.setOption("ignore-errors");		// --ignore-errors
        request.setOption("output", "%(id)s");	// --output "%(id)s"
        request.setOption("retries", 10);		// --retries 10

// Make request and return response
        YoutubeDLResponse response = YoutubeDL.execute(request, new DownloadProgressCallback() {
            @Override
            public void onProgressUpdate(float progress, long etaInSeconds) {
                System.out.println(String.valueOf(progress) + "%");
            }
        });
// Response
        String stdOut = response.getOut(); // Executable output

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
}
