package com.example.zzapdiz.fundingproject;

import com.example.zzapdiz.exception.member.MemberException;
import com.example.zzapdiz.fundingproject.request.FundingProjectCreatePhase1RequestDto;
import com.example.zzapdiz.fundingproject.request.FundingProjectCreatePhase2RequestDto;
import com.example.zzapdiz.fundingproject.request.FundingProjectCreatePhase4RequestDto;
import com.example.zzapdiz.fundingproject.response.FundingProjectCreatePhase2ResponseDto;
import com.example.zzapdiz.fundingproject.response.FundingProjectCreatePhase4ResponseDto;
import com.example.zzapdiz.fundingproject.service.FundingProjectService;
import com.example.zzapdiz.jwt.JwtTokenProvider;
import com.example.zzapdiz.member.request.MemberLoginRequestDto;
import com.example.zzapdiz.member.service.MemberService;
import com.example.zzapdiz.reward.request.RewardCreateRequestDto;
import com.example.zzapdiz.reward.response.RewardCreateResponseDto;
import com.google.gson.Gson;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.web.JsonPath;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.mock.web.MockMultipartFile;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class FundingProjectServiceTest {

    @InjectMocks
    private FundingProjectService fundingProjectService;

    @Mock
    private MemberException memberException;

    @DisplayName("[FundingProjectService] 펀딩 프로젝트 생성 1단계 서비스")
    @Test
    void createFundingPhase1() {
        // given
        MockHttpServletRequest mockHttpServletRequest = new MockHttpServletRequest();

        // when
        int statusCode = fundingProjectService.fundingCreatePhase1(
                mockHttpServletRequest,
                phase1RequestDto()).getBody().getStatusCode();

        String phase1Info = new Gson().toJson(mockHttpServletRequest.getAttribute("phase1Info"));
        FundingProjectCreatePhase1RequestDto fundingProjectCreatePhase1RequestDto =
                new Gson().fromJson(phase1Info, FundingProjectCreatePhase1RequestDto.class);

        // then
        assertThat(statusCode).isEqualTo(200);
        assertThat(fundingProjectCreatePhase1RequestDto.getProjectType()).isEqualTo(phase1RequestDto().getProjectType());
    }

    @DisplayName("[FundingProjectService] 펀딩 프로젝트 생성 2단계 서비스")
    @Test
    void createFundingPhase2() {
        // given
        MockHttpServletRequest mockHttpServletRequest = new MockHttpServletRequest();

        // when
        int statusCode = fundingProjectService.fundingCreatePhase2(
                mockHttpServletRequest,
                phase2RequestDto(),
                any(MockMultipartFile.class)).getBody().getStatusCode();

        // then
        assertThat(statusCode).isEqualTo(200);
    }

    @DisplayName("[FundingProjectService] 펀딩 프로젝트 생성 4단계 서비스")
    @Test
    void createFundingPhase4() {
        // given
        MockHttpServletRequest mockHttpServletRequest = new MockHttpServletRequest();

        // when
        int statusCode = fundingProjectService.fundingCreatePhase4(
                mockHttpServletRequest,
                phase4RequestDto(),
                rewardCreateRequestDtos()).getBody().getStatusCode();

        // then
        assertThat(statusCode).isEqualTo(200);
    }

    private FundingProjectCreatePhase1RequestDto phase1RequestDto(){
        return FundingProjectCreatePhase1RequestDto.builder()
                .projectCategory("TECH")
                .projectType("FIRST_OPEN")
                .makerType("PERSONAL!")
                .rewardType("가구/인테리어")
                .rewardMakeType("SELF_MADE")
                .achievedAmount(8000000)
                .build();
    }

    private FundingProjectCreatePhase2RequestDto phase2RequestDto(){
        return FundingProjectCreatePhase2RequestDto.builder()
                .projectTitle("생성 2단계 프로젝트 타이틀")
                .endDate("20230314")
                .adultCheck("O")
                .searchTag("#검색태그1-#검색태그2")
                .build();
    }

    private FundingProjectCreatePhase2ResponseDto phase2ResponseDto(){
        return FundingProjectCreatePhase2ResponseDto.builder()
                .projectTitle("생성 2단계 프로젝트 타이틀")
                .endDate("20230314")
                .adultCheck("O")
                .searchTag("#검색태그1-#검색태그2")
                .build();
    }

    private FundingProjectCreatePhase4RequestDto phase4RequestDto() {
        return FundingProjectCreatePhase4RequestDto.builder()
                .deliveryCheck("O")
                .deliveryStartDate("20230316")
                .build();
    }

    private FundingProjectCreatePhase4ResponseDto phase4ResponseDto(){
        return FundingProjectCreatePhase4ResponseDto.builder()
                .memberId(1L)
                .deliveryCheck("O")
                .deliveryPrice(3000)
                .deliveryStartDate("20230316")
                .build();
    }

    private RewardCreateRequestDto rewardCreateRequestDto() {
        return RewardCreateRequestDto.builder()
                .rewardTitle("리워드")
                .rewardContent("초회 한정 리워드")
                .rewardQuantity(67000)
                .rewardAmount(60)
                .rewardOptionOnOff("O")
                .optionContent("색상은 빨강")
                .build();
    }

    private RewardCreateResponseDto rewardCreateResponseDto(){
        return RewardCreateResponseDto.builder()
                .memberId(1L)
                .rewardTitle("리워드")
                .rewardContent("초회 한정 리워드")
                .rewardQuantity(67000)
                .rewardAmount(60)
                .rewardOptionOnOff("O")
                .optionContent("색상은 빨강")
                .build();
    }


    private List<RewardCreateRequestDto> rewardCreateRequestDtos(){
        List<RewardCreateRequestDto> rewardRequests= new ArrayList<>();

        rewardRequests.add(
                RewardCreateRequestDto.builder()
                        .rewardTitle("리워드1")
                        .rewardContent("초회 한정 리워드")
                        .rewardQuantity(67000)
                        .rewardAmount(60)
                        .rewardOptionOnOff("O")
                        .optionContent("색상은 빨강")
                        .build());

        rewardRequests.add(
                RewardCreateRequestDto.builder()
                        .rewardTitle("리워드2")
                        .rewardContent("초회 한정 리워드")
                        .rewardQuantity(77000)
                        .rewardAmount(60)
                        .rewardOptionOnOff("O")
                        .optionContent("색상은 파랑")
                        .build());

        rewardRequests.add(
                RewardCreateRequestDto.builder()
                        .rewardTitle("리워드3")
                        .rewardContent("초회 한정 리워드")
                        .rewardQuantity(87000)
                        .rewardAmount(60)
                        .rewardOptionOnOff("O")
                        .optionContent("색상은 하양")
                        .build());

        return rewardRequests;
    }

    private MemberLoginRequestDto memberLoginRequestDto(){
        return MemberLoginRequestDto.builder()
                .email("wlstpgns51@naver.com")
                .password("wls124578!")
                .build();
    }
}
