package com.seventhnode.youtubecrawler.repository;

import com.seventhnode.youtubecrawler.entity.YoutubeChannelInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface YoutubeChannelRepository extends JpaRepository<YoutubeChannelInfo,Long> {
    YoutubeChannelInfo findByChannelId(String channelId);
}
