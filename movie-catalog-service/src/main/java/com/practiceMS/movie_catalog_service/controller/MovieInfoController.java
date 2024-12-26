package com.practiceMS.movie_catalog_service.controller;

import com.practiceMS.movie_catalog_service.model.MovieInfo;
import com.practiceMS.movie_catalog_service.repository.MovieInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class MovieInfoController {

    @Autowired
    private MovieInfoRepository movieInfoRepository;

    @PostMapping("/movie-info/save")
    public List<MovieInfo> saveAll(@RequestBody List<MovieInfo> movieInfos) {
        return movieInfoRepository.saveAll(movieInfos);

    }

    @GetMapping("/movie-info/list")
    public List<MovieInfo> getAll() {
        return movieInfoRepository.findAll();
    }

    @GetMapping("/movie-info/findPathById/{movieInfoId}")
    public String findPathById(@PathVariable Long movieInfoId) {
        var videoInfoOptional = movieInfoRepository.findById(movieInfoId);
        return videoInfoOptional.map(MovieInfo::getPath).orElse(null);

    }
}
