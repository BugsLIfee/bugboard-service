package com.erbf.bugsLife.bugBoard.repository;

import com.erbf.bugsLife.bugBoard.domain.BugBoardQuestion;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BugBoardQuestionRepository extends JpaRepository<BugBoardQuestion, Long> {

    public List<BugBoardQuestion> findByWriterId(Long writerId);
}
