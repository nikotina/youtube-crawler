package com.seventhnode.youtubecrawler.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.seventhnode.youtubecrawler.entity.YouTubeVideoInfo;
import com.seventhnode.youtubecrawler.repository.YoutubeVideoInfoRepository;

import java.util.List;

@Service
public class YoutubeVideoInfoService {

    @Autowired
    private YoutubeVideoInfoRepository youtubeVideoInfoRepository;

    public void save(YouTubeVideoInfo videoInfo) {
        youtubeVideoInfoRepository.save(videoInfo);
    }

    public void update(YouTubeVideoInfo videoInfo) {
        youtubeVideoInfoRepository.save(videoInfo);
    }

    public YouTubeVideoInfo getByVideoId(String videoId) {
        return youtubeVideoInfoRepository.findByVideoId(videoId);
    }

    public YouTubeVideoInfo get(long id) {
        return youtubeVideoInfoRepository.findById(id).get();
    }

    public List<YouTubeVideoInfo> getAll() {
        return youtubeVideoInfoRepository.findAll();
    }

    public List<YouTubeVideoInfo> getByKeyword(String keyword) {
        return youtubeVideoInfoRepository.findByKeyword(keyword);
    }

    public void deleteById(long id) {
    	youtubeVideoInfoRepository.deleteById(id);
    }
}
