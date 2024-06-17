package com.example.newspeed.entity;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class LikeTest {
    @Test
    public void testLike() {
        // Given
        Long id = 1L;
        User user = new User();
        Content content = new Content();
        Comment comment = new Comment();
        LocalDateTime createdAt = LocalDateTime.now();

        // When
        Like testLike = new Like();
        testLike.setId(id);
        testLike.setUser(user);
        testLike.setComment(comment);
        testLike.setContent(content);
        testLike.setCreatedAt(createdAt);

        // Then
        assertEquals(id,testLike.getId());
        assertEquals(user,testLike.getUser());
        assertEquals(comment, testLike.getComment());
        assertEquals(content,testLike.getContent());
        assertEquals(createdAt,testLike.getCreatedAt());
    }

}