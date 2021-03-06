package com.erbf.bugsLife.bugBoard.domain;

import com.erbf.bugsLife.bugBoard.application.web.BugBoardQuestionDto;
import com.erbf.bugsLife.bugBoard.application.web.BugBoardQuestionTagDto;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Builder
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BugBoardQuestionTag {

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "question_id")
    private BugBoardQuestion question;
    private String tagName;

    public BugBoardQuestionTagDto toDto() {
        return BugBoardQuestionTagDto.builder()
                .id(this.id)
                .question(this.question.toDto())
                .tagName(this.tagName)
                .build();
    }
}
