package com.example.moviezip;

import com.example.moviezip.dao.mybatis.MybatisLoginDao;
import com.example.moviezip.domain.Account;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static junit.framework.TestCase.assertNotNull;

@SpringBootTest
@ExtendWith(SpringExtension.class)
public class LoginTest {
    @Autowired
    private MybatisLoginDao mybatisLoginDao;

    @Test
    public void testLogin() throws Exception{
        // 사용자 조회
        Account account1 = mybatisLoginDao.userLogin("dayun", "newPass");
        //User user2 = mybatisLoginDao.userLogin("dayun", "newPasss");
        // 사용자가 null인지 확인
        //assertNotNull(user1);
        //assertNotNull(user2);


        System.out.println("userId: " + account1.getUser_id() + " nickName: " + account1.getNickname());
        //System.out.println("userId: " + user2.getUser_id() + " nickName: " + user2.getNickname());
    }

}
