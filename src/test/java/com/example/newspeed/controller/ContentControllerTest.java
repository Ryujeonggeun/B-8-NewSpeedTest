package com.example.newspeed.controller;

import com.example.newspeed.config.WebSecurityConfig;
import com.example.newspeed.dto.ContentDto;
import com.example.newspeed.entity.Content;
import com.example.newspeed.entity.User;
import com.example.newspeed.security.UserDetailsImpl;
import com.example.newspeed.service.ContentService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.security.Principal;
import java.time.LocalDateTime;
import java.util.Arrays;

import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(
        value = ContentController.class,
        excludeFilters = {
                @ComponentScan.Filter(
                        type = FilterType.ASSIGNABLE_TYPE,
                        classes = WebSecurityConfig.class
                )
        }
)
class ContentControllerTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private WebApplicationContext context;

    @Autowired
    private ObjectMapper mapper;

    @MockBean
    private ContentService contentService;

    @MockBean
    private UserDetailsImpl userDetails;

    private Content content;
    private Principal principal;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        content = new Content();
        content.setId(20L);
        content.setUser(new User("testUser0", "112233", "testName", "tset@naver.com", "테스트입니다"));
        content.setLikes(10);
        content.setContent("테스트 컨텐츠");
        content.setUpdatedDate(LocalDateTime.now());

        mvc = MockMvcBuilders.webAppContextSetup(context)
                .apply(springSecurity())
                .build();

        mockUserSetUp();
    }

    private void mockUserSetUp() {
        String username = "testUser";
        String userId = "test";
        String password = "test1234";
        String email = "test@spring.com";
        User testUser = new User(userId, password, username, email, "테스트입니다");
        UserDetailsImpl testUserDetails = new UserDetailsImpl(testUser);
        principal = new UsernamePasswordAuthenticationToken(testUserDetails, "", testUserDetails.getAuthorities());
    }

    @Test
    @WithMockUser(username = "testUser")
    @DisplayName("전체 게시물 조회 테스트")
    void getAllNewsFeeds() throws Exception {
        // Given
        Content content1 = new Content();
        content1.setId(21L);
        content1.setUser(new User("testUser2", "112233", "testName2", "tset2@naver.com", "테스트입니다"));
        content1.setLikes(15);
        content1.setContent("테스트 컨텐츠2");
        content1.setUpdatedDate(LocalDateTime.now());

        ContentDto contentDto1 = new ContentDto(content);
        ContentDto contentDto2 = new ContentDto(content1);

        when(contentService.getAllContents()).thenReturn(Arrays.asList(contentDto1, contentDto2));

        // When - Then
        mvc.perform(get("/api/content")
                        .contentType(MediaType.APPLICATION_JSON)
                        .principal(principal))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(contentDto1.getId()))
//                .andExpect(jsonPath("$[0].userId").value(contentDto1.getUser().getUserId()))
                .andExpect(jsonPath("$[0].content").value(contentDto1.getContent()))
                .andExpect(jsonPath("$[0].likes").value(contentDto1.getLikes()))
                .andExpect(jsonPath("$[1].id").value(contentDto2.getId()))
//                .andExpect(jsonPath("$[1].userId").value(contentDto2.getUser().getUserId()))
                .andExpect(jsonPath("$[1].content").value(contentDto2.getContent()))
                .andExpect(jsonPath("$[1].likes").value(contentDto2.getLikes()));
    }

    @Test
    @WithMockUser(username = "testUser")
    @DisplayName("특정 유저 게시글 조회 테스트")
    void getNewsFeedById() throws Exception {
        // Given
        long id = 20L;
        ContentDto contentDto = new ContentDto(content);
        when(contentService.getContentById(id)).thenReturn(contentDto);

        // When - Then
        mvc.perform(get("/api/content/{id}", id)
                        .contentType(MediaType.APPLICATION_JSON)
                        .principal(principal)) // principal을 직접 전달
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(contentDto.getId()))
//                .andExpect(jsonPath("$.user.username").value(contentDto.getUser().getUserName()))
                .andExpect(jsonPath("$.content").value(contentDto.getContent()))
                .andExpect(jsonPath("$.likes").value(contentDto.getLikes()));
        }
    @Test
    @DisplayName("게시글 등록 테스트")
    void createNewsFeed() {

        // Given

        // When

        // Then
    }

    @Test
    @DisplayName("게시글 수정 테스트")
    void updateNewsFeed() {

        // Given

        // When

        // Then
    }

    @Test
    @DisplayName("게시글 삭제 테스트")
    void deleteNewsFeed() {

        // Given

        // When

        // Then
    }

}