package com.example.zzapdiz.dofundproject.controller;

import com.example.zzapdiz.dofundproject.request.DoFundPhase1RequestDto;
import com.example.zzapdiz.dofundproject.service.DoFundService;
import com.example.zzapdiz.share.ResponseBody;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Slf4j
@RequestMapping("/zzapdiz")
@RequiredArgsConstructor
@RestController
public class DoFundController {

    private final DoFundService doFundService;

    // 펀딩하기 1단계
    @PostMapping("/dofund/phase1")
    public ResponseEntity<ResponseBody> doFundPhase1(
            HttpServletRequest request,
            @RequestPart(value = "doFundPhase1RequestDto") List<DoFundPhase1RequestDto> doFundPhase1RequestDtos) {
        log.info("펀딩하기 1단계 : 펀딩하는 유저 - {}, 펀딩하는 프로젝트 리워드 개수 - {}", request, doFundPhase1RequestDtos.size());

        return doFundService.doFundPhase1(request, doFundPhase1RequestDtos);
    }

}