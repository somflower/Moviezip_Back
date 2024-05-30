package com.example.moviezip.dao.mybatis.mapper;

import com.example.moviezip.domain.Account;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface LoginMapper {
    Account userLogin(@Param("userId") String userId, @Param("password") String password);
}

