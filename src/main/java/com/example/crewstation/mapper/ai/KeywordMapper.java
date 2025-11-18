package com.example.crewstation.mapper.ai;


import com.example.crewstation.dto.ai.KeywordDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface KeywordMapper {

    public void insertKeyword(@Param("memberId") Long memberId,
                              @Param("searchWord") String searchWord);

    public void insertLog(@Param("memberId") Long memberId,
                   @Param("postId") Long postId);

    public List<KeywordDTO> selectKeyword(Long memberId);

}
