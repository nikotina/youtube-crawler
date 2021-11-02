package com.seventhnode.youtubecrawler.service;

public interface YoutubeApiService {
    void crawlYoutubeVideoInfo(String keyword, long pageToCrawl);
}
