package com.erbf.bugsLife.bugBoard.domain;

import com.erbf.bugsLife.bugBoard.application.web.BugBoardCommentDto;
import lombok.*;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Builder
@Setter
@AllArgsConstructor
@SequenceGenerator(
        name = "BUGBOARD_COMMENT_SEQ_GEN",
        sequenceName = "bugboard_comment_seq",
        initialValue = 1,
        allocationSize = 2
)
@Entity
public class BugBoardComment {

    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "BUGBOARD_COMMENT_SEQ_GEN"
    )
    private Long id;

    private Long writerId;
    @Column(columnDefinition = "integer default 0")
    private Long answerId;
    @Column(columnDefinition = "integer default 0")
    private Long questionId;
    private String content;
    private int reportCnt;
    private String registDate;
    private String updateDate;
    private boolean blind;

    public BugBoardCommentDto toDto() {
        BugBoardCommentDto bugBoardCommentDto = BugBoardCommentDto.builder()
                .id(this.id)
                .writerId(this.writerId)
                .answerId(this.answerId)
                .questionId(this.questionId)
                .content(this.content)
                .reportCnt(this.reportCnt)
                .updateDate(this.updateDate)
                .blind(this.blind)
                .registDate(this.registDate)
                .build();

        return bugBoardCommentDto;
    }
}
