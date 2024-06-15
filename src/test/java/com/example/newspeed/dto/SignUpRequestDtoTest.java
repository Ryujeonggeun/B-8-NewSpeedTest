package com.example.newspeed.dto;


import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import jakarta.validation.ConstraintViolation;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;


class SignUpRequestDtoTest {

    SignUpRequestDto requestDto;

    @Mock
    private Validator validator;

    @BeforeEach
    public void  setUp() {
        // requestDto 생성 및 초기화
        requestDto = new SignUpRequestDto();
        requestDto.setUserId("UserIdTest");
        requestDto.setPassword("Test1234!@");
        requestDto.setEmail("test@naver.com");
        requestDto.setIntro("테스트입니다.");
        requestDto.setUsername("테스트객체");
        // Mockito 초기화
        MockitoAnnotations.openMocks(this);

        // Validator 초기화
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = (Validator) factory.getValidator();
    }


    @Test
    @DisplayName("userId 유효성 10자 미만 테스트")
    void test1() {

        // Given
        requestDto.setUserId("test");

        // When
        Set<ConstraintViolation<SignUpRequestDto>> violations = validator.validate(requestDto);

        // Then
        assertEquals(1, violations.size());
        assertEquals("10에서20자 이내로 만 가능합니다.", violations.iterator().next().getMessage());
    }

    @Test
    @DisplayName("userId 유효성 20자 초과 테스트")
    void test2() {

        // Given
        requestDto.setUserId("test12345678901234567898");

        // When
        Set<ConstraintViolation<SignUpRequestDto>> violations = validator.validate(requestDto);

        // Then
        assertEquals(1, violations.size());
        assertEquals("10에서20자 이내로 만 가능합니다.", violations.iterator().next().getMessage());
    }

    @Test
    @DisplayName("userId 유효성 Null 테스트")
    void test3() {

        // Given
        requestDto.setUserId(null);

        // When
        Set<ConstraintViolation<SignUpRequestDto>> violations = validator.validate(requestDto);

        // Then
        assertEquals(1, violations.size());
        assertEquals("널이어서는 안됩니다", violations.iterator().next().getMessage());
    }


    @Test
    @DisplayName("UserPassword 유효성 테스트")
    void test4() {

        // Given
        requestDto.setPassword("test1234");

        //When
        Set<ConstraintViolation<SignUpRequestDto>> violations = validator.validate(requestDto);

        //Then
        assertEquals(1, violations.size());
        assertEquals("대소문자 포함 영문, 숫자, 특수문자를 포함하여 10자 이상 입력해주세요.",violations.iterator().next().getMessage());
    }

    @Test
    @DisplayName("UserPassword 유효성 Null 테스트")
    void test5() {

        // Given
        requestDto.setPassword(null);

        // When
        Set<ConstraintViolation<SignUpRequestDto>> violations = validator.validate(requestDto);

        // Then
        assertEquals(1, violations.size());
        assertEquals("널이어서는 안됩니다", violations.iterator().next().getMessage());
    }

    @Test
    @DisplayName("Email 유효성 Null 테스트")
    void test6() {

        // Given
        requestDto.setEmail(null);

        // When
        Set<ConstraintViolation<SignUpRequestDto>> violations = validator.validate(requestDto);

        // Then
        assertEquals(1, violations.size());
        assertEquals("널이어서는 안됩니다", violations.iterator().next().getMessage());
    }

    @Test
    @DisplayName("Email 유효성 이메일형식 테스트")
    void test7() {

        // Given
        requestDto.setEmail("test");

        // When
        Set<ConstraintViolation<SignUpRequestDto>> violations = validator.validate(requestDto);

        // Then
        assertEquals(1, violations.size());
        assertEquals("이메일 형식이 아닙니다.", violations.iterator().next().getMessage());
    }

    @Test
    @DisplayName("Intro 유효성 길이 테스트")
    void test8() {

        // Given
        requestDto.setIntro("testtesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttestte" +
                "testtesttesttesttesttesttestteststtesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttes" +
                "testtesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttesttestttest");

        // When
        Set<ConstraintViolation<SignUpRequestDto>> violations = validator.validate(requestDto);

        // Then
        assertEquals(1, violations.size());
        assertEquals("255자 이내로만 작성 가능합니다.", violations.iterator().next().getMessage());
    }




}
