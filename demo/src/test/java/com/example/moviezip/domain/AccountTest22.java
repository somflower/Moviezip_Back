package com.example.moviezip.domain;

import com.example.moviezip.dao.UserDao;
import com.example.moviezip.dao.mybatis.MybatisUserDao;
import junit.framework.TestCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
@ExtendWith(SpringExtension.class) // JUnit 5에서 Spring 테스트와의 통합을 위해 사용
public class AccountTest22 {

    @Autowired
    UserDao userDao;
    @Autowired
    private MybatisUserDao mybatisUserDao;

    @Test
    public void testUser() throws Exception{
        System.out.println("테스트");

        Account account = userDao.getUserById(3L);

        TestCase.assertNotNull(account);
        System.out.println("유저 현재 비번:"+ account.getId()+ account.getPassword());

        mybatisUserDao.updatePassword(account.getId(),"newPass");

        Account account1 = userDao.getUserById(3L);

        System.out.println("변경된 유저비번:"+ account1.getPassword());

        //비번 확인
        String check = "newPass";
        if (check.equals(account1.getPassword()))
            System.out.println("맞았다!");
        else
            System.out.println("틀렸다!");

        //사용자 찾기
        Account account2 =  mybatisUserDao.findUser("kim", "mimi");
        if (account2 != null) {
            System.out.println("찾은 유저 아이디:" + account2.getUser_id());
        } else {
            System.out.println("사용자를 찾을 수 없습니다.");
        }

        //사용자 닉네임 수정
        Account account3 = userDao.getUserById(3L);
        mybatisUserDao.updateNickname(account.getId(), "dydy");
        Account account4 = userDao.getUserById(3L);
        System.out.println("변경된 유저닉네임:"+ account4.getNickname());

        //중복 닉네임 체크
        Account account5 = mybatisUserDao.existingNickname("dydy");
        if(account5 != null) {
            System.out.println("사용 중인 닉네임입니다");
        }else {
            System.out.println("사용 가능한 닉네임입니다");
        }

        //사용자 삭제
//        User user6 = userDao.getUserById(61L);
//        mybatisUserDao.deleteUser(user6.getId());

        //사용자 전체 출력
        List<Account> allAccount = mybatisUserDao.findAllUser();
        for (Account account7 : allAccount) {
            System.out.println("ID: " + account7.getId());
            System.out.println("USERID: " + account7.getUser_id());
            System.out.println("NICKNAME: " + account7.getNickname());
            System.out.println("PASSWORD: " + account7.getPassword());
            System.out.println("HINT: " + account7.getHint());

            System.out.println("--------------------------");
        }

        //사용자의 관심사 찾기
        Interest interests = userDao.findInterest(3L);
        System.out.println("관심사:"+ interests.getGenre());

        //관심사 수정
//        List<String> genre = new ArrayList<>(); // 예시 관심사
//        genre.add("1");
//        genre.add("2");
//        System.out.println(genre);
//        userDao.updateInterest(3L, genre.toString());

        //사용자 추가
//        User user9 = new User();
//        user9.setUser_id("0511");
//        user9.setPassword("hello");
//        user9.setHint("하이");
//        user9.setNickname("hi");
//        userDao.addUser(user9);
//
//        List<String> genre = new ArrayList<>(); // 예시 관심사
//        genre.add("1");
//        genre.add("3");
//        System.out.println(genre);
//
//        Interest interest = new Interest();
//        interest.setId(user9.getId());
//        interest.setGenre(genre.toString());
//        userDao.addInterest(interest); //관심사도 추가

        //사용자 취향까지 출력
        Account account10 = mybatisUserDao.findAllUserInterest(3L);
        System.out.println("--------------------------");
        System.out.println("ID: " + account10.getId());
        System.out.println("USERID: " + account10.getUser_id());
        System.out.println("NICKNAME: " + account10.getNickname());
        System.out.println("PASSWORD: " + account10.getPassword());
        System.out.println("HINT: " + account10.getHint());
        System.out.println("INTEREST: " + account10.getInterest().getGenre());
        System.out.println("--------------------------");

        ///ㅁㅇㄹㅁㄴㅇㄹ맨ㄹ야ㅐㅁ
    }
}
