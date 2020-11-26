package com.erbf.bugsLife.bugBoard.repository;

import com.erbf.bugsLife.bugBoard.domain.BugBoardQuestionLike;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BugBoardQuestionLikeRepository extends JpaRepository<BugBoardQuestionLike, Long> {

    List<BugBoardQuestionLike> findByUserId(Long id);
}
