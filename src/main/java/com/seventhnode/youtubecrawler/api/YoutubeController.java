package com.seventhnode.youtubecrawler.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.seventhnode.youtubecrawler.entity.CrawlingInfo;
import com.seventhnode.youtubecrawler.entity.YouTubeVideoInfo;
import com.seventhnode.youtubecrawler.entity.YoutubeChannelInfo;
import com.seventhnode.youtubecrawler.entity.YoutubeVideoStatistics;
import com.seventhnode.youtubecrawler.service.CrawlingInfoService;
import com.seventhnode.youtubecrawler.service.YoutubeApiService;
import com.seventhnode.youtubecrawler.service.YoutubeChannelService;
import com.seventhnode.youtubecrawler.service.YoutubeVideoInfoService;
import com.seventhnode.youtubecrawler.service.YoutubeVideoStatService;

import java.util.List;

@RestController
@RequestMapping("youtube")
public class YoutubeController {

    @Autowired
    YoutubeApiService youtubeApiService;

    @Autowired
    YoutubeVideoInfoService youtubeVideoInfoService;

    @Autowired
    YoutubeChannelService youtubeChannelService;

    @Autowired
    YoutubeVideoStatService youtubeVideoStatService;
    
    @Autowired
    CrawlingInfoService crawlingInfoService;

    @GetMapping(value = "crawl/{keyword}/{pageToCrawl}")
    public void crawlVideo(@PathVariable String keyword, @PathVariable long pageToCrawl) {
         youtubeApiService.crawlYoutubeVideoInfo(keyword,pageToCrawl);
    }
    
    @GetMapping(value = "crawl")
    public List<CrawlingInfo> getAllCrawlingInfo() {
        return crawlingInfoService.getAll();
    }

    @GetMapping(value = "info")
    public List<YouTubeVideoInfo> getAll(){
        return youtubeVideoInfoService.getAll();
    }

    @GetMapping(value = "info/{id}")
    public YouTubeVideoInfo getOne(@PathVariable long id){
        return youtubeVideoInfoService.get(id);
    }
    
    @GetMapping(value = "info/searchkey/{keyword}")
    public List<YouTubeVideoInfo> getOne(@PathVariable String keyword){
        return youtubeVideoInfoService.getByKeyword(keyword);
    }
    
    @DeleteMapping(value = "info/delete/{id}")
    public ResponseEntity<Long> deleteVideoInfo(@PathVariable long id) {
    	System.out.println("in delete");
    	crawlingInfoService.deleteById(id);
    	return new ResponseEntity<>(id, HttpStatus.OK);
    }

    @GetMapping(value = "channel")
    public List<YoutubeChannelInfo> getAllChannel(){
        return youtubeChannelService.getAll();
    }

    @GetMapping(value = "channel/{id}")
    public YoutubeChannelInfo getChannel(@PathVariable long id){
        return youtubeChannelService.get(id);
    }

    @GetMapping(value = "stat")
    public List<YoutubeVideoStatistics> getAllstat(){
        return youtubeVideoStatService.getAll();
    }

    @GetMapping(value = "stat/{id}")
    public YoutubeVideoStatistics getStats(@PathVariable long id){
        return youtubeVideoStatService.get(id);
    }

}
