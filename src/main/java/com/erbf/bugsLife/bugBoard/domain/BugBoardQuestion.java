package com.erbf.bugsLife.bugBoard.domain;

import com.erbf.bugsLife.bugBoard.application.web.BugBoardQuestionDto;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
@Builder
@AllArgsConstructor
@SequenceGenerator(
        name = "BUGBOARD_QUESTION_SEQ_GEN",
        sequenceName = "bugboard_question_seq",
        initialValue = 1,
        allocationSize = 2
    )
@Entity
@Setter
public class BugBoardQuestion {

    @Id
    @GeneratedValue(
        strategy = GenerationType.SEQUENCE,
        generator = "BUGBOARD_QUESTION_SEQ_GEN"
    )
    private Long id;

    private Long writerId;
    private String title;
    private String content;
    private int point;
    private String dueDate;
    private boolean publicPost;

    @Column(columnDefinition = "boolean default false")
    private boolean blind;

    @Column(columnDefinition = "integer default 0")
    private int view;
    private String registDate;
    private String updateDate;
    @Column(columnDefinition = "integer default 0")
    private int reportCnt;

    @Column(columnDefinition = "integer default 0")
    private int likes;
    private boolean premium;

    @OneToMany(mappedBy = "question", cascade = CascadeType.ALL)
    private List<BugBoardQuestionTag> questionTags = new ArrayList<BugBoardQuestionTag>();

    public void addQuestionTag(List<BugBoardQuestionTag> bugBoardQuestionTags) {
        questionTags = bugBoardQuestionTags;

        questionTags.stream().forEach(bugBoardQuestionTag -> {
            bugBoardQuestionTag.setQuestion(this);
        });
    }

    public BugBoardQuestionDto toDto() {

        List<String> tags = new ArrayList<>();

        questionTags.stream().forEach(tag -> {
            tags.add(tag.getTagName());
        });

        BugBoardQuestionDto bugBoardCommentDto = BugBoardQuestionDto.builder()
                .id(this.id)
                .writerId(this.writerId)
                .title(this.title)
                .content(this.content)
                .dueDate(this.dueDate)
                .point(this.point)
                .publicPost(this.publicPost)
                .blind(this.blind)
                .view(this.view)
                .registDate(this.registDate)
                .updateDate(this.updateDate)
                .reportCnt(this.reportCnt)
                .likes(this.likes)
                .tags(tags)
                .premium(this.premium)
                .build();

        return bugBoardCommentDto;
    }
}
