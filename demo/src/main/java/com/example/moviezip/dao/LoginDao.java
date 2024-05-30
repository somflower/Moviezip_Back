package com.example.moviezip.dao;

import com.example.moviezip.domain.Account;
import org.apache.ibatis.annotations.Param;

public interface LoginDao {
    Account userLogin(@Param("userId") String userId, @Param("password") String password);
}
