package com.erbf.bugsLife.bugBoard.application.web;

import com.erbf.bugsLife.bugBoard.service.BugBoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/bugboard/")
public class BugBoardController {

    @Autowired
    BugBoardService bugBoardService;

    @GetMapping("/")
    public List<BugBoardQuestionDto> getBugBoardList() {
        List<BugBoardQuestionDto> bugBoardList = bugBoardService.bugBoardList();
        return bugBoardList;
    }

    @GetMapping("list/{uid}")
    public List<BugBoardQuestionDto> getBugBoradListByUid(@PathVariable Long uid){
        List<BugBoardQuestionDto> bugBoardList = bugBoardService.bugBoardListByUid(uid);
        return bugBoardList;
    }

    @PostMapping("/posting/")
    public ResponseEntity<?> questionCreate(@RequestBody BugBoardQuestionDto bugBoardDto) {
        bugBoardService.questionCreate(bugBoardDto);
        return new ResponseEntity<>("success", HttpStatus.OK);
    }

    @PostMapping("/detail/add-answer")
    public ResponseEntity<?> answerCreate(@RequestBody BugBoardAnswerDto dto) {
        bugBoardService.answerCreate(dto);
        return new ResponseEntity<>("success", HttpStatus.OK);
    }

    @PostMapping("/detail/add-comment")
    public ResponseEntity<?>  commentCreate(@RequestBody BugBoardCommentDto dto) {
        bugBoardService.commentCreate(dto);
        return new ResponseEntity<>("success", HttpStatus.OK);
    }

    @DeleteMapping("/question/delete/{id}")
    public ResponseEntity<?> questionDelete(@PathVariable Long id) {
        bugBoardService.deleteQuestion(id);
        return new ResponseEntity<>("success", HttpStatus.OK);
    }

    @DeleteMapping("/answer/delete/{id}")
    public ResponseEntity<?> answerDelete(@PathVariable Long id) {
        bugBoardService.deleteAnswer(id);
        return new ResponseEntity<>("success", HttpStatus.OK);
    }

    @DeleteMapping("/comment/delete/{id}")
    public ResponseEntity<?> commentDelete(@PathVariable Long id) {
        bugBoardService.deleteComment(id);
        return new ResponseEntity<>("success", HttpStatus.OK);
    }

    @GetMapping("/detail/{id}")
    public BugBoardDetailDto postDetail(@PathVariable Long id)
    {
        return bugBoardService.postDetail(id);
    }

    @GetMapping("/user-comment/{id}")
    public List<BugBoardCommentDto> getBugBoardCommentByWriterId(@PathVariable Long id) {
        return bugBoardService.getCommentByWriterId(id);
    }

    @PutMapping("/question/")
    public ResponseEntity<?> modifyQuestion(@RequestBody BugBoardQuestionDto questionDto) {
        bugBoardService.updateQuestion(questionDto);
        return new ResponseEntity<>("success", HttpStatus.OK);
    }

    @PutMapping("/answer/")
    public ResponseEntity<?> modifyAnswer(@RequestBody BugBoardAnswerDto answerDto) {
        bugBoardService.updateAnswer(answerDto);
        return new ResponseEntity<>("success", HttpStatus.OK);
    }

    @PutMapping("/comment/")
    public ResponseEntity<?> modifyComment(@RequestBody BugBoardCommentDto commentDto) {
        bugBoardService.updateComment(commentDto);
        return new ResponseEntity<>("success", HttpStatus.OK);
    }

    @GetMapping("/detail/{questionId}/add-question-like/{userId}")
    public ResponseEntity<?> addQuestionLike(@PathVariable Long questionId, @PathVariable Long userId) {
        bugBoardService.addQuestionLike(questionId, userId);
        return new ResponseEntity<>("success", HttpStatus.OK);
    }

    @GetMapping("/detail/{questionId}/delete-question-like/{userId}")
    public ResponseEntity<?> deleteQuestionLike(@PathVariable Long questionId, @PathVariable Long userId) {
        bugBoardService.deleteQuestionLike(questionId, userId);
        return new ResponseEntity<>("success", HttpStatus.OK);
    }

    @GetMapping("/detail/{answerId}/delete-answer-like/{userId}")
    public ResponseEntity<?> deleteAnswerLike(@PathVariable Long answerId, @PathVariable Long userId) {
        bugBoardService.deleteAnswerLike(answerId, userId);
        return new ResponseEntity<>("success", HttpStatus.OK);
    }

    @GetMapping("/detail/{answerId}/add-answer-like/{userId}")
    public ResponseEntity<?> addAnswerLike(@PathVariable Long answerId, @PathVariable Long userId) {
        bugBoardService.addAnswerLike(answerId, userId);
        return new ResponseEntity<>("success", HttpStatus.OK);
    }

    @GetMapping("/setBlind/{postType}/{id}")
    public ResponseEntity<?> setBlind(@PathVariable String postType, @PathVariable Long id) {
            bugBoardService.setBlind(postType, id);
            return new ResponseEntity<>("success", HttpStatus.OK);
        }

    @GetMapping("/select-answer/{id}")
    public ResponseEntity<?> answerSelect(@PathVariable Long id) {
        bugBoardService.selectAnswer(id);
        return new ResponseEntity<>("success", HttpStatus.OK);
    }

    @GetMapping("/user-like/{userId}")
    public int userLike(@PathVariable Long userId) {
        return bugBoardService.getUserLike(userId);
}

    @GetMapping("/tags/")
    public List<BugBoardQuestionTagDto> tagList() {
        return bugBoardService.tagList();
    }
}
