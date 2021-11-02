package com.seventhnode.youtubecrawler.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.seventhnode.youtubecrawler.entity.YouTubeVideoInfo;
import com.seventhnode.youtubecrawler.repository.YoutubeVideoInfoRepository;

import java.util.List;

@Service
public class YoutubeVideoInfoServiceImpl implements YoutubeVideoInfoService {

    @Autowired
    private YoutubeVideoInfoRepository youtubeVideoInfoRepository;

    @Override
    public void save(YouTubeVideoInfo videoInfo) {
        youtubeVideoInfoRepository.save(videoInfo);
    }

    @Override
    public void update(YouTubeVideoInfo videoInfo) {
        youtubeVideoInfoRepository.save(videoInfo);
    }

    @Override
    public YouTubeVideoInfo getByVideoId(String videoId) {
        return youtubeVideoInfoRepository.findByVideoId(videoId);
    }

    @Override
    public YouTubeVideoInfo get(long id) {
        return youtubeVideoInfoRepository.findById(id).get();
    }

    @Override
    public List<YouTubeVideoInfo> getAll() {
        return youtubeVideoInfoRepository.findAll();
    }
    @Override
    public List<YouTubeVideoInfo> getByKeyword(String keyword) {
        return youtubeVideoInfoRepository.findByKeyword(keyword);
    }
    @Override
    public void deleteById(long id) {
    	youtubeVideoInfoRepository.deleteById(id);
    }
}
