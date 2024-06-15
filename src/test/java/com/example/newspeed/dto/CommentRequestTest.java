package com.example.newspeed.dto;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


class CommentRequestTest {


    @Test
    @DisplayName("CommentRequest 테스트")
    public  void testCommentRequestTest() {
        // Given
        String comment = "CommentRequest 테스트";

        // When
        CommentRequest request = new CommentRequest(comment);

        // Then
        assertEquals(comment,request.getComment());
    }
}