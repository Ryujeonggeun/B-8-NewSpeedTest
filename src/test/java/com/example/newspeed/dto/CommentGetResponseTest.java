package com.example.newspeed.dto;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
class CommentGetResponseTest {

    @Test
    @DisplayName("CommentGetResponse 테스트")
    public void testCommentGetResponseTest() {

        // Given
        long id = 20L;
        long userId = 3;
        String comment = "testCommentGetResponseTest 테스트";

        // When
        CommentGetResponse response = new CommentGetResponse(id,userId,comment);

        // Then
        assertEquals(id,response.getId());
        assertEquals(userId,response.getUserId());
        assertEquals(comment,response.getComment());

    }

}