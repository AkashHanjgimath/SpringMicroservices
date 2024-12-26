package com.practiceMS.movie_streaming_service.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class MovieCatalogService {

    public static final String CATLOG_SERVICE="http://movie-catalog-service";

    @Autowired
    private RestTemplate restTemplate;


    public String getMoviePath(Long movieInfoId)
    {
        var respone=restTemplate.getForEntity(CATLOG_SERVICE+"/movie-info/findPathById/{movieInfoId}",String.class,movieInfoId);
        return respone.getBody();
    }
}
