package com.seventhnode.youtubecrawler.api;

import com.seventhnode.youtubecrawler.jobs.DownloadJob;
import com.seventhnode.youtubecrawler.service.DownloadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.simp.annotation.SubscribeMapping;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.ConcurrentHashMap;

/**
 * RestController for YouTube Downloads
 */
@RestController
public class YoutubeDownloadController {
    @Qualifier("taskExecutor")
    @Autowired
    private ThreadPoolTaskExecutor myExecutor;
    @Autowired
    private SimpMessagingTemplate template;
    @Autowired
    private DownloadService downloadService;
    ConcurrentHashMap<String, DownloadJob> downloadJobConcurrentHashMap = new ConcurrentHashMap<>();


    /**
     * Download YouTube Video
     *
     * @param videoId
     */
    @CrossOrigin
    @RequestMapping(path = "youtube/youtubedownload/{videoId}", method = RequestMethod.GET)
    public void download(@PathVariable String videoId) {

        String directory = System.getProperty("user.home");

        DownloadJob downloadJob = new DownloadJob(videoId, directory, template);
        downloadJobConcurrentHashMap.put(videoId, downloadJob);
        downloadService.doWork((Runnable) downloadJob);
    }

    /**
     * Get Job List
     */
    @CrossOrigin
    @RequestMapping(path = "youtube/joblist", method = RequestMethod.GET)
    public ConcurrentHashMap<String, DownloadJob> getJobList() {
        return downloadJobConcurrentHashMap;
    }

    /**
     * Remove Job from Job list
     * @param videoId
     */
    @CrossOrigin
    @RequestMapping(path = "youtube/jobRemove/{videoId}", method = RequestMethod.GET)
    public void removeJob(@PathVariable String videoId) {
        downloadJobConcurrentHashMap.remove(videoId);
    }

    @RequestMapping(value = "/status")
    @ResponseBody
    @SubscribeMapping("initial")
    public String getStatusByVideoId(String videoId){
        return "";
    }


}
