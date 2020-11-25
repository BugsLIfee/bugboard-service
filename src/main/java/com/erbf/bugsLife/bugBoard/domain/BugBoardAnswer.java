package com.erbf.bugsLife.bugBoard.domain;

import com.erbf.bugsLife.bugBoard.application.web.BugBoardAnswerDto;
import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@Builder
@AllArgsConstructor
@SequenceGenerator(
        name = "BUGBOARD_ANSWER_SEQ_GEN",
        sequenceName = "bugboard_answer_seq",
        initialValue = 1,
        allocationSize = 2
)
@Entity
public class BugBoardAnswer {

    @Id
    @GeneratedValue(
    strategy = GenerationType.SEQUENCE,
    generator = "BUGBOARD_ANSWER_SEQ_GEN"
            )
    private Long id;

    private Long questionId;
    private Long writerId;
    private String content;

    @Column(columnDefinition = "boolean default false")
    private boolean selected;
    @Column(columnDefinition = "boolean default false")
    private boolean blind;

    @Column(columnDefinition = "integer default 0")
    private int reportCnt;

    private String registDate;
    private String updateDate;

    @Column(columnDefinition = "integer default 0")
    private int likes;

    public BugBoardAnswerDto toDto() {
        BugBoardAnswerDto bugBoardAnswerDto = BugBoardAnswerDto.builder()
                .id(this.id)
                .writerId(this.writerId)
                .content(this.content)
                .questionId(this.questionId)
                .selected(this.selected)
                .blind(this.blind)
                .reportCnt(this.reportCnt)
                .registDate(this.registDate)
                .updateDate(this.updateDate)
                .likes(this.likes)
                .build();

        return bugBoardAnswerDto;
    }
}
