package com.seventhnode.youtubecrawler.service;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class DownloadService {

        @Async
        public void doWork(Runnable runnable) {
            System.out.println("Got runnable " + runnable);
            runnable.run();
        }

}
