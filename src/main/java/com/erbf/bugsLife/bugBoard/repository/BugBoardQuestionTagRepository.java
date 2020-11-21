package com.erbf.bugsLife.bugBoard.repository;

import com.erbf.bugsLife.bugBoard.domain.BugBoardQuestionTag;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BugBoardQuestionTagRepository extends JpaRepository <BugBoardQuestionTag, Long> {
    void deleteByQuestionId(Long id);
}
