package com.example.crewstation.repository.ai;

import com.example.crewstation.dto.ai.KeywordDTO;
import com.example.crewstation.dto.member.MemberDTO;
import com.example.crewstation.mapper.ai.KeywordMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

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

    public List<KeywordDTO> selectRecommend(Long memberId) {
        return keywordMapper.selectKeyword(memberId);
    }

    public List<KeywordDTO> selectAllDiaries() {
        return keywordMapper.selectAllDiaries();
    }


}
