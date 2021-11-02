package com.seventhnode.youtubecrawler.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.seventhnode.youtubecrawler.entity.CrawlingInfo;

@Repository
public interface CrawlingInfoRepository extends JpaRepository<CrawlingInfo,Long> {
    CrawlingInfo findBySearchKey(String searchKey);
    void deleteBySearchKey(String keyword);
    void deleteById(long id);
}
