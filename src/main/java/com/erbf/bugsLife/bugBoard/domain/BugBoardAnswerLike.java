package com.erbf.bugsLife.bugBoard.domain;

import com.erbf.bugsLife.bugBoard.application.web.BugBoardAnswerLikeDto;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Builder
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BugBoardAnswerLike {

    @Id
    @GeneratedValue
    private Long id;
    private Long userId;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "answer_id")
    private BugBoardAnswer answer;

    public void addAnswer(BugBoardAnswer answer) {
        this.answer = answer;
        answer.getLikeList().add(this);
    }

    public BugBoardAnswerLikeDto toDto() {
        return BugBoardAnswerLikeDto.builder()
                .id(this.id)
                .userId(this.userId)
                .build();
    }
}
