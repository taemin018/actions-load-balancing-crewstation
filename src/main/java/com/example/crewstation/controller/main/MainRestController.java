package com.example.crewstation.controller.main;

import com.example.crewstation.auth.CustomUserDetails;
import com.example.crewstation.dto.ai.KeywordDTO;
import com.example.crewstation.dto.diary.DiaryDetailDTO;
import com.example.crewstation.dto.member.MemberDTO;
import com.example.crewstation.service.ai.KeywordService;
import com.example.crewstation.service.member.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api")
public class MainRestController {

    private final KeywordService keywordService;

    @GetMapping("/userdata")
    public ResponseEntity<List<KeywordDTO>> getUserKeywordData(@AuthenticationPrincipal CustomUserDetails user) {
        log.info("getUserKeywordData");
        List<KeywordDTO> keywordDTO = keywordService.recommendSelect(user.getId());

        return ResponseEntity.ok(keywordDTO);
    }

    @PostMapping("/recommendDiary")
    public ResponseEntity<List<DiaryDetailDTO>> getRecommendDiary(@RequestBody ArrayList<Long> diaryIds, @AuthenticationPrincipal CustomUserDetails customUserDetails) {

        log.info("recommend diary: {}", diaryIds.get(0));
        List<DiaryDetailDTO> diaryDetailDTOS = keywordService.recommendDiary(diaryIds ,customUserDetails);
        return ResponseEntity.ok(diaryDetailDTOS);

    }


}
