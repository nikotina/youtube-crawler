package com.seventhnode.youtubecrawler.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.seventhnode.youtubecrawler.entity.YoutubeChannelInfo;

@Repository
public interface YoutubeChannelRepository extends JpaRepository<YoutubeChannelInfo,Long> {
    public YoutubeChannelInfo findByChannelId(String channelId);
}
