package com.seventhnode.youtubecrawler.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.seventhnode.youtubecrawler.entity.CrawlingInfo;
import com.seventhnode.youtubecrawler.repository.CrawlingInfoRepository;
import com.seventhnode.youtubecrawler.repository.YoutubeVideoInfoRepository;

import java.util.List;

@Service
@Transactional
public class CrawlingInfoServiceImpl implements  CrawlingInfoService {

    @Autowired
    private CrawlingInfoRepository crawlingInfoRepository;
    
    @Autowired
    private YoutubeVideoInfoRepository youtubeVideoInfoRepository;

    @Override
    public void save(CrawlingInfo crawlingInfo) {
        crawlingInfoRepository.save(crawlingInfo);
    }

    @Override
    public void update(CrawlingInfo crawlingInfo) {
        crawlingInfoRepository.save(crawlingInfo);
    }

    @Override
    public CrawlingInfo get(long id) {
        return crawlingInfoRepository.getOne(id);
    }

    @Override
    public CrawlingInfo getBySearchKey(String searchKey) {
        return crawlingInfoRepository.findBySearchKey(searchKey);
    }

    @Override
    public List<CrawlingInfo> getAll() {
        return crawlingInfoRepository.findAll();
    }
    @Override
    public void deleteById(long id) {
    	CrawlingInfo cI = crawlingInfoRepository.getById(id);
    	String searchKey = cI.getSearchKey();
    	youtubeVideoInfoRepository.deleteByKeyword(searchKey);
    	crawlingInfoRepository.deleteById(id);
    	
    }
}
