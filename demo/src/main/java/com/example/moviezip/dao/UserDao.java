package com.example.moviezip.dao;

import com.example.moviezip.domain.Account;
import com.example.moviezip.domain.Interest;
import org.springframework.dao.DataAccessException;

import java.util.List;

public interface UserDao {
    public Account getUserById(Long id) throws DataAccessException;

    //비번 변경
    public void updatePassword(Long id, String newPassword) throws DataAccessException;

    public Account findUser(String hint, String nickname) throws DataAccessException;

    public void updateNickname(Long id, String newNickname) throws DataAccessException;

    public Account existingNickname(String nickname) throws DataAccessException;

    public void deleteUser(Long id) throws DataAccessException;

    public List<Account> findAllUser() throws DataAccessException;

    public void addInterest(Interest interest) throws DataAccessException;

    public Interest findInterest(Long id) throws DataAccessException;

    public void updateInterest(Long id, String genre) throws DataAccessException;

    public void addUser(Account account) throws DataAccessException;

    public Account findAllUserInterest(Long id) throws DataAccessException;


}
