package com.seventhnode.youtubecrawler.service;



import java.util.List;

import com.seventhnode.youtubecrawler.entity.YoutubeVideoStatistics;

public interface YoutubeVideoStatService {
    void save(YoutubeVideoStatistics videoStatistics);
    void update(YoutubeVideoStatistics videoInfo);
    YoutubeVideoStatistics get(long id);
    List<YoutubeVideoStatistics> getAll();
}
