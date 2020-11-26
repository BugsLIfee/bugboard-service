package com.erbf.bugsLife.bugBoard.domain;

import com.erbf.bugsLife.bugBoard.application.web.BugBoardQuestionLikeDto;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Builder
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BugBoardQuestionLike {

    @Id
    @GeneratedValue
    private Long id;
    private Long userId;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "question_id")
    private BugBoardQuestion question;

    public void addQuestion(BugBoardQuestion question) {
        this.question = question;
        question.getLikeList().add(this);
    }

    public BugBoardQuestionLikeDto toDto() {
        return BugBoardQuestionLikeDto.builder()
                .id(this.id)
                .userId(this.userId)
                .build();
    }
}
