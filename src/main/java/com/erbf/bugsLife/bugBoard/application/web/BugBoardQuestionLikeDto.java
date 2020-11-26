package com.erbf.bugsLife.bugBoard.application.web;

import com.erbf.bugsLife.bugBoard.domain.BugBoardQuestion;
import lombok.*;

@Getter
@Builder
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BugBoardQuestionLikeDto {
    private Long id;
    private BugBoardQuestion questionId;
    private Long userId;
}
