package com.erbf.bugsLife.bugBoard.repository;

import com.erbf.bugsLife.bugBoard.domain.BugBoardQuestionLike;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.List;

public interface BugBoardQuestionLikeRepository extends JpaRepository<BugBoardQuestionLike, Long> {

    List<BugBoardQuestionLike> findByUserId(Long id);

    @Transactional
    @Modifying
    @Query(value = "delete from bug_board_question_like " +
            "where bug_board_question_like.question_id = :questionId " +
            "   and bug_board_question_like.user_id = :userId", nativeQuery = true)
    void deleteByQuestionIdAndUserId(Long questionId, Long userId);
}
