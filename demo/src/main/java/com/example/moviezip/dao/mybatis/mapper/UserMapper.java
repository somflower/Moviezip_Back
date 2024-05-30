package com.example.moviezip.dao.mybatis.mapper;

import com.example.moviezip.domain.Account;
import com.example.moviezip.domain.Interest;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface UserMapper {
    Account getUserById(Long id);

    void updateUserPassword(@Param("id") Long id, @Param("newPassword") String newPassword);

    //힌트 통해 사용자 찾기
    Account findUser(@Param("hint") String hint, @Param("nickname") String nickname);

    //사용자 닉네임 수정
    void updateUserNickname(@Param("id") Long id, @Param("newNickname") String newPassword);

    //사용자 중복 닉네임 체크
    Account existingUserNickname(@Param("nickname") String nickname);

    //사용자 삭제
    void deleteUser(@Param("id") Long id);

    //사용자 전체 리스트 출력
    List<Account> findAllUser();

    void addInterest(Interest interest);

    Interest findInterest(@Param("id") Long id);

    void updateInterest(@Param("id")Long id, @Param("genre")String genre);

    void addUser(Account account);

    Account findAllUserInterest(@Param("id") Long id);


}
