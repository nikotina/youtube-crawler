package com.seventhnode.youtubecrawler.service;



import java.util.List;

import com.seventhnode.youtubecrawler.entity.YouTubeVideoInfo;

public interface YoutubeVideoInfoService {
    void save(YouTubeVideoInfo videoInfo);
    void update(YouTubeVideoInfo videoInfo);
    YouTubeVideoInfo getByVideoId(String videoId);
    YouTubeVideoInfo get(long id);
    List<YouTubeVideoInfo> getAll();
    List<YouTubeVideoInfo> getByKeyword(String keyword);
    void deleteById(long id);
}
