package com.erbf.bugsLife.bugBoard.repository;

import com.erbf.bugsLife.bugBoard.domain.BugBoardAnswerLike;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BugBoardAnswerLikeRepository extends JpaRepository<BugBoardAnswerLike, Long> {

    List<BugBoardAnswerLike> findByUserId(Long id);
}
