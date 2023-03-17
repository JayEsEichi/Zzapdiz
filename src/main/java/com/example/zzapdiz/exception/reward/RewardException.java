package com.example.zzapdiz.exception.reward;

import com.example.zzapdiz.reward.request.RewardCreateRequestDto;
import com.example.zzapdiz.share.ResponseBody;
import com.example.zzapdiz.share.StatusCode;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class RewardException implements RewardExceptionInterface {

    // 리워드 생성 시 수량 체크
    public ResponseEntity<ResponseBody> rewardAmountCheck(List<RewardCreateRequestDto> rewardCreateRequestDtos){
        for(RewardCreateRequestDto rewardCreateRequestDto : rewardCreateRequestDtos){
            if(rewardCreateRequestDto.getRewardAmount() > 0 && rewardCreateRequestDto.getRewardAmount() < 50){
                return new ResponseEntity<>(new ResponseBody(StatusCode.NEED_AMOUNT_CHECK, null), HttpStatus.BAD_REQUEST);
            }
        }

        return null;
    }


}
