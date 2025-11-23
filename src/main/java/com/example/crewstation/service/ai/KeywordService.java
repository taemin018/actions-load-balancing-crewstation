package com.example.crewstation.service.ai;

import com.example.crewstation.auth.CustomUserDetails;
import com.example.crewstation.dto.ai.KeywordDTO;
import com.example.crewstation.dto.diary.DiaryDetailDTO;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public interface KeywordService {

    public List<KeywordDTO> recommendSelect(Long memberId);

    public List<DiaryDetailDTO> recommendDiary(ArrayList<Long> diaryIds, CustomUserDetails customUserDetails);

    public List<KeywordDTO> getAllDiaries();
}
