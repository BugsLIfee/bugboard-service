package com.erbf.bugsLife.bugBoard.repository;

import com.erbf.bugsLife.bugBoard.domain.BugBoardQuestionTag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.List;

public interface BugBoardQuestionTagRepository extends JpaRepository <BugBoardQuestionTag, Long> {
    List<BugBoardQuestionTag> findByQuestionId(Long id);

    @Transactional
    @Modifying
    @Query(value = "delete from bug_board_question_tag where bug_board_question_tag.question_id = :id", nativeQuery = true)
    void deleteAllByQuestionId(@Param("id") Long id);
}
