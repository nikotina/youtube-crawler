package com.seventhnode.youtubecrawler.service;

import java.util.List;

import com.seventhnode.youtubecrawler.entity.CrawlingInfo;

public interface CrawlingInfoService {
    void save(CrawlingInfo crawlingInfo);
    void update(CrawlingInfo crawlingInfo);
    CrawlingInfo get(long id);
    CrawlingInfo getBySearchKey(String searchKey);
    List<CrawlingInfo> getAll();
    void deleteById(long id);
}
