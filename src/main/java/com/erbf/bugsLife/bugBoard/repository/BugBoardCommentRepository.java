package com.erbf.bugsLife.bugBoard.repository;

import com.erbf.bugsLife.bugBoard.domain.BugBoardComment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BugBoardCommentRepository extends JpaRepository <BugBoardComment, Long> {

    List<BugBoardComment> findByQuestionId(Long questionId);
    List<BugBoardComment> findByAnswerId(Long answerId);
    List<BugBoardComment> findByWriterId(Long writerId);
    void deleteByQuestionId(Long questionId);
    void deleteByAnswerId(long answerId);
}
