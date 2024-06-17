package com.example.newspeed.entity;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CommentTest {

    @Test
    public void testComment(){
        // Given
        Long userId = 1L;
        Content content = new Content();
        User user = new User();
        String comment = "댓글 테스트";

        // When
        Comment testComment = new Comment();
        testComment.setComment(comment);
        testComment.setId(userId);
        testComment.setUser(user);
        testComment.setNews(content);

        // Then
        assertEquals(userId,testComment.getId());
        assertEquals(content,testComment.getNews());
        assertEquals(user,testComment.getUser());
        assertEquals(comment,testComment.getComment());
    }

}