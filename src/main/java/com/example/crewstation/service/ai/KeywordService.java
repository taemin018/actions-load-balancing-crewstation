package com.example.crewstation.service.ai;

import com.example.crewstation.dto.ai.KeywordDTO;

import java.util.List;

public interface KeywordService {

    public List<KeywordDTO> recommendSelect(Long memberId);

}
