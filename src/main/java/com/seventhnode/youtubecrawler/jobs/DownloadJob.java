package com.seventhnode.youtubecrawler.jobs;

import com.seventhnode.youtubecrawler.api.YoutubeDL;
import com.seventhnode.youtubecrawler.entity.YoutubeDLRequest;
import com.seventhnode.youtubecrawler.entity.YoutubeDLResponse;
import com.seventhnode.youtubecrawler.exception.YoutubeDLException;
import com.seventhnode.youtubecrawler.jobs.Message.DownloadProgressMessage;
import com.seventhnode.youtubecrawler.util.DownloadProgressCallback;
import org.springframework.messaging.simp.SimpMessagingTemplate;

import java.util.concurrent.atomic.AtomicInteger;

public class DownloadJob implements Runnable {


    private final SimpMessagingTemplate template;


    private final AtomicInteger progressInt = new AtomicInteger();
    private String state = "NEW";
    private final String videoUrl;
    private final String directory;


    public DownloadJob(String videoUrl, String directory, SimpMessagingTemplate template) {
        this.videoUrl = videoUrl;
        this.directory = directory;
        this.template = template;
    }


    public void run() {
        state = "RUNNING";

        executeDownload(this.videoUrl, this.directory);
        state = "DONE";
        template.convertAndSend("/youtube/status", state);
    }

    /**
     * Download File
     * @param videoId
     * @param directory
     */
    public void executeDownload(String videoId, String directory) {

        String videoUrl = "http://www.youtube.com/watch?v=" + videoId;
        YoutubeDLRequest request = new YoutubeDLRequest(videoUrl, directory);
        request.setOption("ignore-errors");        // --ignore-errors
        request.setOption("output", "%(id)s");    // --output "%(id)s"
        request.setOption("retries", 10);        // --retries 10

        try {
            YoutubeDLResponse response = YoutubeDL.execute(request, new DownloadProgressCallback() {
                @Override
                public void onProgressUpdate(float progress, long etaInSeconds) {
                    DownloadProgressMessage downloadProgressMessage = new DownloadProgressMessage(getVideoId());
                    downloadProgressMessage.setProgress(progress);
                    downloadProgressMessage.setState("RUNNING");
                    template.convertAndSend("/status/" + getVideoId(), downloadProgressMessage);
                    System.out.println(progress + "%");
                }
            });

            String stdOut = response.getOut(); // Executable output
            System.out.println(stdOut);

        } catch (YoutubeDLException dlException) {
            System.out.println(dlException.getMessage());
        }
    }

    /**
     * Get Video Id
     * @return
     */
    public String getVideoId() {
        return videoUrl;
    }

}
