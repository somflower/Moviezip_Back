package com.example.moviezip.service;

import com.example.moviezip.domain.Movie;
import com.example.moviezip.domain.User;

import java.util.List;

public interface UserService {
    public User findByUserId(String userId); // 로그인
    public void registerUser(String userId, String password); // 회원가입

    public void updatePassword(Long id, String newPassword);
    public void updateNickname(Long id, String newNickname);
    public void deleteUser(Long id);

    public List<Movie> searchMoviesByTitle(String keyword);

}
