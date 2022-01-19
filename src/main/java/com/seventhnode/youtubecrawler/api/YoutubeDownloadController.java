package com.seventhnode.youtubecrawler.api;
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
}
