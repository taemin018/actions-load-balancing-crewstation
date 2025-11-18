package com.example.crewstation.dto.ai;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class KeywordDTO {
    private Long memberId;
    private String searchWord;
    private String createdDatetime;
    private String updatedDatetime;
    private String postContent;
    private String postTitle;

}
