package com.seventhnode.youtubecrawler.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.seventhnode.youtubecrawler.entity.YoutubeChannelInfo;
import com.seventhnode.youtubecrawler.repository.YoutubeChannelRepository;

import java.util.List;

@Service
public class YoutubeChannelService {

    @Autowired
    YoutubeChannelRepository youtubeChannelRepository;


    public void save(YoutubeChannelInfo channelInfo) {
        youtubeChannelRepository.save(channelInfo);
    }


    public void update(YoutubeChannelInfo channelInfo) {
        youtubeChannelRepository.save(channelInfo);
    }


    public YoutubeChannelInfo get(long id) {
        return youtubeChannelRepository.findById(id).get();
    }


    public YoutubeChannelInfo getByChannelId(String channelId) {
        return youtubeChannelRepository.findByChannelId(channelId);
    }


    public List<YoutubeChannelInfo> getAll() {
        return youtubeChannelRepository.findAll();
    }
}
