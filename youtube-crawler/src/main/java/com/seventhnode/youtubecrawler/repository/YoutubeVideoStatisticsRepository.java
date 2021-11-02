package com.seventhnode.youtubecrawler.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.seventhnode.youtubecrawler.entity.YoutubeVideoStatistics;

@Repository
public interface YoutubeVideoStatisticsRepository extends JpaRepository<YoutubeVideoStatistics,Long> {
}
