package com.example.newspeed.entity;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class ContentTest {

    @Test
    public void testContent() {
        // Given
        Long id = 1L;
        User user = new User();
        String content = "게시글 테스트 입니다";
        LocalDateTime createdDate = LocalDateTime.now();
        LocalDateTime updateDate = LocalDateTime.now();
        Integer likes = 15;

        // When
        Content testContent = new Content();
        testContent.setId(id);
        testContent.setUser(user);
        testContent.setContent(content);
        testContent.setCreatedDate(createdDate);
        testContent.setUpdatedDate(updateDate);
        testContent.setLikes(likes);

        // Then
        assertEquals(id, testContent.getId());
        assertEquals(user, testContent.getUser());
        assertEquals(content, testContent.getContent());
        assertEquals(createdDate, testContent.getCreatedDate());
        assertEquals(updateDate,testContent.getUpdatedDate());
        assertEquals(likes,testContent.getLikes());
    }
}