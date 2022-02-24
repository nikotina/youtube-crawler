package com.seventhnode.youtubecrawler.repository;


import com.seventhnode.youtubecrawler.entity.CrawlingInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SearchInfoRepository extends JpaRepository<CrawlingInfo, Long> {
    CrawlingInfo findBySearchKey(String searchKey);

    void deleteBySearchKey(String keyword);

    void deleteById(long id);
}
