package com.demo;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MovieMapper {
    int addMoive(Movie movie);
}
