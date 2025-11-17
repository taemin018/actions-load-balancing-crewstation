package com.example.crewstation.repository.ai;

import com.example.crewstation.mapper.ai.KeywordMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class KeywordDAO {

    private final KeywordMapper keywordMapper;

    public void saveKeyword(Long memberId, String searchWord) {
        keywordMapper.insertKeyword(memberId, searchWord);
    }

    public void saveLog(Long memberId, Long postId) {
        keywordMapper.insertLog(memberId, postId);
    }

}
