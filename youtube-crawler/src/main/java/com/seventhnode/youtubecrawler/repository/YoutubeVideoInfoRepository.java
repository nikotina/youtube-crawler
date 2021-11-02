package com.seventhnode.youtubecrawler.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.seventhnode.youtubecrawler.entity.YouTubeVideoInfo;

@Repository
public interface YoutubeVideoInfoRepository extends JpaRepository<YouTubeVideoInfo,Long> {
    YouTubeVideoInfo findByVideoId(String videoId);
    List<YouTubeVideoInfo> findByKeyword(String keyword);
    void deleteById(long id);
    void deleteByKeyword(String searchkey);
}
