package com.example.moviezip.service;

import com.example.moviezip.dao.mybatis.MybatisUserDao;
import com.example.moviezip.dao.mybatis.mapper.UserMapper;
import com.example.moviezip.domain.User;
import com.example.moviezip.domain.Movie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    private final MybatisUserDao mybatisUserDao;

    // 로그인 ------------------------------------------------
    @Autowired
    private UserMapper userMapper;

    @Autowired
    private PasswordEncoder passwordEncoder;

    // 로그인
    @Override
    public User findByUserId(String userId){
        return userMapper.findByUserId(userId);
    }

    // 회원가입
    @Override
    public void registerUser(String userId, String password) {
        User user = new User();
        user.setUserId(userId);
        user.setPassword(passwordEncoder.encode(password));
        userMapper.addUser(user);
    }
    //--------------------------------------------------------

    public UserServiceImpl(MybatisUserDao mybatisUserDao) {
    this.mybatisUserDao = mybatisUserDao;
}

    public User getUserById(Long id) {
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
