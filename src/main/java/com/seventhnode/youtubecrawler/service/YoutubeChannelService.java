package com.seventhnode.youtubecrawler.service;


import java.util.List;

import com.seventhnode.youtubecrawler.entity.YoutubeChannelInfo;

public interface YoutubeChannelService {

    void save(YoutubeChannelInfo channelInfo);
    void update(YoutubeChannelInfo channelInfo);
    YoutubeChannelInfo get(long id);
    YoutubeChannelInfo getByChannelId(String channelId);
    List<YoutubeChannelInfo> getAll();

}
