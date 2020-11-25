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

    @GetMapping("/detail/{id}/add-question-like/")
    public ResponseEntity<?> addQuestionLike(@PathVariable Long id) {
        bugBoardService.addQuestionLike(id);
        return new ResponseEntity<>("success", HttpStatus.OK);
    }

    @GetMapping("/detail/{id}/add-answer-like/")
    public ResponseEntity<?> addAnswerLike(@PathVariable Long id) {
        bugBoardService.addAnswerLike(id);
        return new ResponseEntity<>("success", HttpStatus.OK);
    }

    @GetMapping("/setBlind/{postType}/{id}")
    public ResponseEntity<?> setBlind(@PathVariable String postType, @PathVariable Long id) {
        bugBoardService.setBlind(postType, id);
        return new ResponseEntity<>("success", HttpStatus.OK);
    }
}
