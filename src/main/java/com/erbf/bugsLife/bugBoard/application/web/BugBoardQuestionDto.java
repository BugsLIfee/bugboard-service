package com.erbf.bugsLife.bugBoard.application.web;

import com.erbf.bugsLife.bugBoard.domain.BugBoardQuestion;
import lombok.*;

import java.util.List;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class BugBoardQuestionDto {

    private Long id;
    private String dueDate;
    private String writerName;
    private Long writerId;
    private String title;
    private String content;
    private int point;
    private boolean publicPost;
    private boolean premium;
    private int writerLevel;
    private boolean blind;
    private int view;
    private String registDate;
    private String updateDate;
    private int reportCnt;
    private int likes;
    private int numOfAnswers;
    private List<String> tags;
    private List<BugBoardCommentDto> comments;

    public BugBoardQuestion toEntity() {
        return BugBoardQuestion.builder()
                .writerId(this.writerId)
                .title(this.title)
                .content(this.content)
                .dueDate(this.dueDate)
                .publicPost(this.publicPost)
                .point(this.point)
                .premium(this.premium)
                .build();
    }
}
