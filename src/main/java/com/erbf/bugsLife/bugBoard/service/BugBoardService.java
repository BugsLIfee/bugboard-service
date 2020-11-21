package com.erbf.bugsLife.bugBoard.service;

import com.erbf.bugsLife.bugBoard.application.web.BugBoardAnswerDto;
import com.erbf.bugsLife.bugBoard.application.web.BugBoardCommentDto;
import com.erbf.bugsLife.bugBoard.application.web.BugBoardDetailDto;
import com.erbf.bugsLife.bugBoard.application.web.BugBoardQuestionDto;

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
    public abstract void addQuestionLike(Long id);
    public abstract void addAnswerLike(Long id);
    public abstract void setBlind(String postType, Long id);
}
