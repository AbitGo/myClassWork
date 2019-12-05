package com.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MovieService {
    @Autowired
    MovieMapper movieMapper;
    public int addMoive(Movie movie){
        return movieMapper.addMoive(movie);
    }
}
