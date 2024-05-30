package com.example.moviezip.service;

import com.example.moviezip.dao.mybatis.MybatisUserDao;
import com.example.moviezip.domain.Account;
import com.example.moviezip.domain.Movie;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountImpl implements AccountService{
    private final MybatisUserDao mybatisUserDao;

    public AccountImpl(MybatisUserDao mybatisUserDao) {
    this.mybatisUserDao = mybatisUserDao;
}
    public Account getUserById(Long id) {
        return mybatisUserDao.getUserById(id);
    }

    public void updatePassword(Long id, String newPassword) {
        mybatisUserDao.updatePassword(id, newPassword);
    }

    public void updateNickname(Long id, String newNickname) { mybatisUserDao.updateNickname(id, newNickname); }

    public void deleteUser(Long id) { mybatisUserDao.deleteUser(id); }

    @Override
    public List<Movie> searchMoviesByTitle(String keyword) {
        return null;
    }

}
