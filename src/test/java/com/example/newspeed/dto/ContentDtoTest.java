package com.example.newspeed.dto;

import com.example.newspeed.entity.Content;
import com.example.newspeed.entity.User;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class ContentDtoTest {

    @Test
    @DisplayName("ContentDto 테스트")
    public void testContentDto() {

        // Given
        Long id = 20L;
        User user = new User("test","test1234","test","test@gmail.com","테스트입니다." );
        String content = "테스트 입니다";
        LocalDateTime createdDate = LocalDateTime.now();
        LocalDateTime updatedDate = LocalDateTime.now();
        Integer likes = 20;

        Content testContent = new Content();
        testContent.setId(id);
        testContent.setContent(content);
        testContent.setUser(user);
        testContent.setCreatedDate(createdDate);
        testContent.setUpdatedDate(updatedDate);
        testContent.setLikes(likes);

        // When
        ContentDto contentDto = new ContentDto(testContent);

        //Then
        assertEquals(id, contentDto.getId());
        assertEquals(user, contentDto.getUser());
        assertEquals(content, contentDto.getContent());
        assertEquals(createdDate, contentDto.getCreatedDate());
        assertEquals(updatedDate, contentDto.getUpdatedDate());
        assertEquals(likes, contentDto.getLikes());
    }

}