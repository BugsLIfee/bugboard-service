package com.erbf.bugsLife.bugBoard.application.web;

import com.erbf.bugsLife.bugBoard.domain.BugBoardComment;
import lombok.*;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class BugBoardCommentDto {

    private Long id;
    private Long answerId;
    private Long questionId;
    private String content;
    private String writerName;
    private Long writerId;
    private int reportCnt;
    private boolean blind;
    private int writerLevel;
    private String registDate;
    private String updateDate;
}
