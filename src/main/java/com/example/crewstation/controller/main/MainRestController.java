package com.example.crewstation.controller.main;

import com.example.crewstation.auth.CustomUserDetails;
import com.example.crewstation.dto.ai.KeywordDTO;
import com.example.crewstation.service.ai.KeywordService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api")
public class MainRestController {

    private final KeywordService keywordService;

    @GetMapping("/userdata")
    public ResponseEntity<List<KeywordDTO>> getUserKeywordData(@AuthenticationPrincipal CustomUserDetails user) {
        List<KeywordDTO> keywordDTO = keywordService.recommendSelect(user.getId());
        return ResponseEntity.ok(keywordDTO);
    }


}
