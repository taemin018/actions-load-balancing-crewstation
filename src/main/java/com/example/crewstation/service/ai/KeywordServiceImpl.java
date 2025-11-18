package com.example.crewstation.service.ai;

import com.example.crewstation.dto.ai.KeywordDTO;
import com.example.crewstation.repository.ai.KeywordDAO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class KeywordServiceImpl implements KeywordService {
    private final KeywordDAO keywordDAO;

    @Override
    public List<KeywordDTO> recommendSelect(Long memberId) {
        return keywordDAO.selectRecommend(memberId);

    }
}
