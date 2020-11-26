package com.erbf.bugsLife.bugBoard.service;

import com.erbf.bugsLife.bugBoard.application.web.*;

import java.util.List;

public interface BugBoardService {
    public abstract void questionCreate(BugBoardQuestionDto bugBoardPostingDto);
    public abstract void answerCreate(BugBoardAnswerDto bugBoardAnswerDto);
    public abstract void commentCreate(BugBoardCommentDto bugBoardCommentDto);
    public abstract List<BugBoardQuestionDto> bugBoardList();
    public abstract List<BugBoardCommentDto> getCommentByWriterId(Long writerId);
    public abstract List<BugBoardQuestionDto> bugBoardListByUid(Long uid);
    public abstract BugBoardDetailDto postDetail(Long id);
    public abstract void deleteQuestion(Long id);
    public abstract void deleteAnswer(Long id);
    public abstract void deleteComment(Long id);
    public abstract void updateAnswer(BugBoardAnswerDto answerDto);
    public abstract void updateQuestion(BugBoardQuestionDto questionDto);
    public abstract void updateComment(BugBoardCommentDto commentDto);
    public abstract void addQuestionLike(Long questionId, Long userId);
    public abstract void addAnswerLike(Long answerId, Long userId);
    public abstract void setBlind(String postType, Long id);
    public abstract void selectAnswer(Long id);
    public abstract List<BugBoardQuestionTagDto> tagList();
    public abstract int getUserLike(Long id);
}
