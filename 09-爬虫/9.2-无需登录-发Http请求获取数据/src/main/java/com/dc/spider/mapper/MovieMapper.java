package com.dc.spider.mapper;

import com.dc.spider.Entity.Movie;

import java.util.List;

public interface MovieMapper {

    void insert(Movie movie);

    List<Movie> findAll();
}
