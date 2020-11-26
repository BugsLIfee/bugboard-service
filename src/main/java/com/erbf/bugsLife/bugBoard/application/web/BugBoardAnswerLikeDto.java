package com.erbf.bugsLife.bugBoard.application.web;

import com.erbf.bugsLife.bugBoard.domain.BugBoardAnswer;
import lombok.*;

@Getter
@Builder
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BugBoardAnswerLikeDto {

    private Long id;
    private BugBoardAnswer answer;
    private Long userId;

}
