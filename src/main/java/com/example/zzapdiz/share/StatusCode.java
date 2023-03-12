package com.example.zzapdiz.share;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
@NoArgsConstructor
public enum StatusCode {

    // 정상 응답
    OK(200, "정상 처리"),


    // 잘못된 요청으로 인한 응답 (클라이언트 입장)
    NOT_MATCH_PASSWORD(452, "비밀번호와 재확인 비밀번호가 일치하지 않습니다."),
    ALREADY_EXIST_MEMBER(453, "이미 존재하는 이메일 계정입니다."),
    EXIST_INCORRECTABLE_DATA(454, "회원가입 정보가 옳바르지 않습니다."),
    NOT_FOUND_MATCHING_EMAIL(455, "입력한 이메일에 맞는 계정 정보가 존재하지 않습니다."),
    DUPLICATED_ACCOUNT(456, "이미 회원가입된 이메일 계정입니다."),
    NOT_FOUND_MATCHING_PASSWORD(457, "비밀번호가 일치하지 않습니다."),
    UNAUTHORIZED_TOKEN(458, "유효한 토큰이 아닙니다.");



    // 잘못된 서버 응답 (서버 입장)

    private int statusCode;
    private String statusMessage;

    public int getStatusCode(){
        return statusCode;
    }

    public String getStatusMessage(){
        return statusMessage;
    }
}
