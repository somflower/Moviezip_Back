package com.example.moviezip.dao.mybatis.mapper;

import com.example.moviezip.domain.Movie;
import com.example.moviezip.domain.Review;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface WishMapper {
     List<Movie> getWishMovie(int id);
     List<Review> getWishReview(int id);
     List<Review> getMyReview(String userId);
}
