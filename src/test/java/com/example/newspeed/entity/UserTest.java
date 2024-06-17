package com.example.newspeed.entity;

import com.example.newspeed.status.UserStatus;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserTest {

    @Test
    public void testUser() {

        // Given
        Long id = 1L;
        String userId = "test";
        String password = "1234";
        String userName = "testName";
        String email = "test@email.com";
        String intro = "유저 테스트입니다.";
        UserStatus status = UserStatus.ACTIVE;
        String refreshToken = "refreshToken";

        // When
        User testUser = new User(userId,password,userName,email,intro);
        testUser.setId(id);
        testUser.setStatus(status);
        testUser.setRefreshToken(refreshToken);

        // Then
        assertEquals(id,testUser.getId());
        assertEquals(userId,testUser.getUserId());
        assertEquals(password,testUser.getPassword());
        assertEquals(userName,testUser.getUserName());
        assertEquals(email,testUser.getEmail());
        assertEquals(intro,testUser.getIntro());
        assertEquals(status, testUser.getStatus());
        assertEquals(refreshToken,testUser.getRefreshToken());
    }

}