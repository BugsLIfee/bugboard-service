package com.erbf.bugsLife.bugBoard.application.web;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor 
@Builder
public class BugBoardDetailDto {

    private BugBoardQuestionDto question;
    private List<BugBoardAnswerDto> answers;

}
