package com.practiceMS.movie_streaming_service.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.InputStreamSource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.logging.Level;
import java.util.logging.Logger;

@RestController
public class MovieStreamingController {

    @Autowired
    private MovieCatalogService movieCatalogService;

    public static final Logger log=Logger.getLogger(MovieStreamingController.class.getName());

    public static final String VIDEO_DIRECTOR="E:\\stream\\";

    @GetMapping("/stream/{videoPath}")
    public ResponseEntity<InputStreamSource>streamVideo(@PathVariable String videoPath) throws FileNotFoundException {
        File file=new File(VIDEO_DIRECTOR+videoPath);
        if(file.exists())
        {
            InputStreamSource inputStreamSource=new InputStreamResource(new FileInputStream(file));
            return ResponseEntity.ok()
                    .contentType(MediaType.parseMediaType("video/mp4"))
                    .body(inputStreamSource);
        }
        else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/stream/with-Id/{videoInfoId}")
    public ResponseEntity<InputStreamSource>streamVideoById(@PathVariable Long videoInfoId) throws FileNotFoundException {
      String moviePath= movieCatalogService.getMoviePath(videoInfoId);
      log.log(Level.INFO,"Resolved Movie Path = {0}",moviePath);
      return streamVideo(moviePath);
    }


}
