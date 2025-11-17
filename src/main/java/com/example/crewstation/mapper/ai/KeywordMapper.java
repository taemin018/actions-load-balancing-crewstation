package com.example.crewstation.mapper.ai;


import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface KeywordMapper {

    public void insertKeyword(@Param("memberId") Long memberId,
                              @Param("searchWord") String searchWord);

    public void insertLog(@Param("memberId") Long memberId,
                   @Param("postId") Long postId);
}
