package com.erbf.bugsLife.bugBoard.repository;

import com.erbf.bugsLife.bugBoard.domain.BugBoardAnswerLike;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.List;

public interface BugBoardAnswerLikeRepository extends JpaRepository<BugBoardAnswerLike, Long> {

    List<BugBoardAnswerLike> findByUserId(Long id);

    @Transactional
    @Modifying
    @Query(value = "delete from bug_board_answer_like " +
            "where bug_board_answer_like.answer_id = :answerId " +
            "   and bug_board_answer_like.user_id = :userId", nativeQuery = true)
    void deleteByAnswerIdAndUserId(Long answerId, Long userId);
}
