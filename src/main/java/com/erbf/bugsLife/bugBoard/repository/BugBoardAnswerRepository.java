package com.erbf.bugsLife.bugBoard.repository;

import com.erbf.bugsLife.bugBoard.domain.BugBoardAnswer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BugBoardAnswerRepository extends JpaRepository<BugBoardAnswer, Long> {

    List<BugBoardAnswer> findByQuestionId(Long questionId);
    void deleteByQuestionId(Long questionId);
}
