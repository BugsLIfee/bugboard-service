package com.erbf.bugsLife.bugBoard.application.web;

import com.erbf.bugsLife.bugBoard.domain.BugBoardAnswer;
import com.erbf.bugsLife.bugBoard.domain.BugBoardAnswerLike;
import lombok.*;

import java.util.List;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class BugBoardAnswerDto {

    private Long id;
    private Long questionId;
    private String writerName;
    private Long writerId;
    private String content;
    private boolean selected;
    private boolean blind;
    private int reportCnt;
    private int writerLevel;
    private String registDate;
    private String updateDate;
    private int likes;
    private List<BugBoardCommentDto> comments;
    private List<BugBoardAnswerLikeDto> likeList;

}
