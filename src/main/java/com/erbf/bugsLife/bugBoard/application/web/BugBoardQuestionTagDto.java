package com.erbf.bugsLife.bugBoard.application.web;

import lombok.*;

@Getter
@Builder
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BugBoardQuestionTagDto {
    private Long id;
    private BugBoardQuestionDto question;
    private String tagName;
}
