package com.example.crewstation.service.ai;

import com.example.crewstation.auth.CustomUserDetails;
import com.example.crewstation.dto.ai.KeywordDTO;
import com.example.crewstation.dto.diary.DiaryDetailDTO;
import com.example.crewstation.mapper.ai.KeywordMapper;
import com.example.crewstation.repository.ai.KeywordDAO;
import com.example.crewstation.repository.diary.DiaryDAO;
import com.example.crewstation.service.diary.DiaryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class KeywordServiceImpl implements KeywordService {
    private final KeywordDAO keywordDAO;
    private final KeywordMapper keywordMapper;
    private final DiaryService diaryService;

    @Override
    public List<KeywordDTO> recommendSelect(Long memberId) {
        return keywordDAO.selectRecommend(memberId);

    }

    @Override
    public List<DiaryDetailDTO> recommendDiary(ArrayList<Long> diaryIds, CustomUserDetails customUserDetails) {
        log.info("recommend diary");
        ArrayList<DiaryDetailDTO> diaryDetailDTOs = new ArrayList<>();
        diaryIds.forEach(diaryId -> {
            diaryDetailDTOs.add(diaryService.getDiary(diaryId, customUserDetails));
        });
        return diaryDetailDTOs;
    }
}
