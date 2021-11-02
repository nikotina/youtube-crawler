package com.seventhnode.youtubecrawler.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.seventhnode.youtubecrawler.entity.YoutubeVideoStatistics;
import com.seventhnode.youtubecrawler.repository.YoutubeVideoStatisticsRepository;

import java.util.List;

@Service
public class YoutubeVideoStatServiceImpl implements YoutubeVideoStatService {

    @Autowired
    private YoutubeVideoStatisticsRepository youtubeVideoStatisticsRepository;

    @Override
    public void save(YoutubeVideoStatistics videoStatistics) {
        youtubeVideoStatisticsRepository.save(videoStatistics);
    }

    @Override
    public void update(YoutubeVideoStatistics videoStatistics) {
        youtubeVideoStatisticsRepository.save(videoStatistics);
    }

    @Override
    public YoutubeVideoStatistics get(long id) {
        return youtubeVideoStatisticsRepository.findById(id).get();
    }

    @Override
    public List<YoutubeVideoStatistics> getAll() {
        return youtubeVideoStatisticsRepository.findAll();
    }
}
