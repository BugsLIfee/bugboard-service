package com.erbf.bugsLife.bugBoard.service.logic;

import com.erbf.bugsLife.bugBoard.application.web.BugBoardAnswerDto;
import com.erbf.bugsLife.bugBoard.application.web.BugBoardCommentDto;
import com.erbf.bugsLife.bugBoard.application.web.BugBoardDetailDto;
import com.erbf.bugsLife.bugBoard.application.web.BugBoardQuestionDto;
import com.erbf.bugsLife.bugBoard.domain.BugBoardAnswer;
import com.erbf.bugsLife.bugBoard.domain.BugBoardComment;
import com.erbf.bugsLife.bugBoard.domain.BugBoardQuestion;
import com.erbf.bugsLife.bugBoard.domain.BugBoardQuestionTag;
import com.erbf.bugsLife.bugBoard.repository.BugBoardAnswerRepository;
import com.erbf.bugsLife.bugBoard.repository.BugBoardCommentRepository;
import com.erbf.bugsLife.bugBoard.repository.BugBoardQuestionRepository;
import com.erbf.bugsLife.bugBoard.repository.BugBoardQuestionTagRepository;
import com.erbf.bugsLife.bugBoard.service.BugBoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
public class BugBoardServiceImpl implements BugBoardService {

    @Autowired
    BugBoardQuestionRepository questionRepo;

    @Autowired
    BugBoardAnswerRepository answerRepo;

    @Autowired
    BugBoardCommentRepository commentRepo;

//    @Autowired
//    UserRepository userRepo;

    @Autowired
    BugBoardQuestionTagRepository tagRepo;

    @Transactional
    @Override
    public void questionCreate(BugBoardQuestionDto bugBoardPostingDto) {

        List<BugBoardQuestionTag> bugBoardQuestionTags = new ArrayList<>();
        bugBoardPostingDto.getTags().stream().forEach(tag -> {
            bugBoardQuestionTags.add(
                    BugBoardQuestionTag.builder()
                            .tagName(tag)
                            .build());
        });

        BugBoardQuestion bugBoardQuestion = BugBoardQuestion.builder()
                .writerId(bugBoardPostingDto.getWriterId())
                .title(bugBoardPostingDto.getTitle())
                .content(bugBoardPostingDto.getContent())
                .dueDate(bugBoardPostingDto.getDueDate())
                .point(bugBoardPostingDto.getPoint())
                .registDate(BugBoardServiceImpl.getDate())
                .updateDate(BugBoardServiceImpl.getDate())
                .premium(bugBoardPostingDto.isPremium())
                .build();

        bugBoardQuestion.addQuestionTag(bugBoardQuestionTags);

        questionRepo.save(bugBoardQuestion);
    }

    @Override
    public void answerCreate(BugBoardAnswerDto bugBoardAnswerDto) {
        answerRepo.save(BugBoardAnswer.builder()
                .questionId(bugBoardAnswerDto.getQuestionId())
                .writerId(bugBoardAnswerDto.getWriterId())
                .content(bugBoardAnswerDto.getContent())
                .updateDate(getDate())
                .registDate(getDate())
                .build()
        );
    }

    @Override
    public void commentCreate(BugBoardCommentDto bugBoardCommentDto) {
        commentRepo.save(BugBoardComment.builder()
                .answerId(bugBoardCommentDto.getAnswerId())
                .questionId(bugBoardCommentDto.getQuestionId())
                .writerId(bugBoardCommentDto.getWriterId())
                .content(bugBoardCommentDto.getContent())
                .updateDate(getDate())
                .registDate(getDate())
                .build()
        );
    }

    @Override
    @Transactional
    public void deleteQuestion(Long id) {
        List<BugBoardAnswer> answers = answerRepo.findByQuestionId(id);
        answers.stream().forEach(answer -> {
            System.out.println();
            commentRepo.deleteByAnswerId(answer.getId());
        });
        questionRepo.deleteById(id);
        answerRepo.deleteByQuestionId(id);
        commentRepo.deleteByQuestionId(id);
    }

    @Override
    @Transactional
    public void deleteAnswer(Long id) {
        answerRepo.deleteById(id);
        commentRepo.deleteByAnswerId(id);
    }

    @Override
    @Transactional
    public void deleteComment(Long id) {
        commentRepo.deleteById(id);
    }

    @Override
    public BugBoardDetailDto postDetail(Long id) throws NoSuchElementException {
        BugBoardQuestion question = questionRepo.findById(id).get();
        question.setView(question.getView()+1);
        questionRepo.save(question);
        BugBoardQuestionDto questionDto = question.toDto();

//        User writer = userRepo.findById(questionDto.getWriterId()).get();
//        String writerName = writer.getName();
//        int writerLevel = writer.getLevel();
//        questionDto.setWriterName(writerName);
//        questionDto.setWriterLevel(writerLevel);

        List<BugBoardComment> questionComments = commentRepo.findByQuestionId(id);
        questionDto.setComments(questionComments.stream().map(BugBoardComment::toDto)
                .collect(Collectors.toList()));

//        questionDto.getComments().stream().forEach(commentDto -> {
//            User commentWriter = userRepo.findById(commentDto.getWriterId()).get();
//            commentDto.setWriterName(commentWriter.getName());
//            commentDto.setWriterLevel(commentWriter.getLevel());
//        });


        List<BugBoardAnswer> answers = answerRepo.findByQuestionId(id);
        List<BugBoardAnswerDto> answerDtos = answers.stream().map(BugBoardAnswer::toDto).collect(Collectors.toList());
//        answerDtos.stream().forEach(answerDto -> {
//            User answerWriter = userRepo.findById(answerDto.getWriterId()).get();
//            answerDto.setWriterName(answerWriter.getName());
//            answerDto.setWriterLevel(answerWriter.getLevel());
//        });


        answerDtos.stream().forEach(
                answerDto -> {
                    answerDto.setComments(commentRepo.findByAnswerId(answerDto.getId()).stream()
                            .map(BugBoardComment::toDto).collect(Collectors.toList()));

//                    answerDto.getComments().stream().forEach(commentDto -> {
//                        User answerCommentWriter = userRepo.findById(commentDto.getWriterId()).get();
//                        commentDto.setWriterName(answerCommentWriter.getName());
//                        commentDto.setWriterLevel(answerCommentWriter.getLevel());
//                    });
                });

        BugBoardDetailDto detailDto = BugBoardDetailDto.builder()
                .question(questionDto)
                .answers(answerDtos)
                .build();

        return detailDto;
    }

    @Override
    public List<BugBoardQuestionDto> bugBoardList() {
        List<BugBoardQuestion> questions = questionRepo.findAll();
        List<BugBoardQuestionDto> questionDtos = questions.stream().map(BugBoardQuestion::toDto).collect(Collectors.toList());
//        questionDtos.stream().forEach(
//                questionDto -> {
//                    User wrtier = userRepo.findById(questionDto.getWriterId()).get();
//                    questionDto.setNumOfAnswers(answerRepo.findByQuestionId(questionDto.getId()).size());
//                    questionDto.setWriterName(wrtier.getName());
//                    questionDto.setWriterLevel(wrtier.getLevel());
//                }
//        );


        return questionDtos;
    }

    @Override
    public List<BugBoardCommentDto> getCommentByWriterId(Long writerId) {
        List<BugBoardComment> comments = commentRepo.findByWriterId(writerId);
        List<BugBoardCommentDto> commentDtos = comments.stream().map(BugBoardComment::toDto).collect(Collectors.toList());
        return commentDtos;
    }

    @Override
    public List<BugBoardQuestionDto> bugBoardListByUid(Long uid) {
        List<BugBoardQuestion> questions = questionRepo.findByWriterId(uid);
        List<BugBoardQuestionDto> questionDtos = questions.stream().map(BugBoardQuestion::toDto).collect(Collectors.toList());

//        questionDtos.stream().forEach(
//                questionDto ->  {
//                    User writer = userRepo.findById(questionDto.getWriterId()).get();
//                    questionDto.setNumOfAnswers(answerRepo.findByQuestionId(questionDto.getId()).size());
//                    questionDto.setWriterName(writer.getName());
//                    questionDto.setWriterLevel(writer.getLevel());
//                }
//        );

        return questionDtos;
    }

    @Override
    public void updateQuestion(BugBoardQuestionDto questionDto) {

        tagRepo.deleteByQuestionId(questionDto.getId());
        List<BugBoardQuestionTag> bugBoardQuestionTags = new ArrayList<>();
        questionDto.getTags().stream().forEach(tag -> {
            bugBoardQuestionTags.add(
                    BugBoardQuestionTag.builder()
                            .tagName(tag)
                            .build());
        });

        BugBoardQuestion bugBoardQuestion = BugBoardQuestion.builder()
                .title(questionDto.getTitle())
                .content(questionDto.getContent())
                .dueDate(questionDto.getDueDate())
                .point(questionDto.getPoint())
                .updateDate(BugBoardServiceImpl.getDate())
                .premium(questionDto.isPremium())
                .build();

        bugBoardQuestion.addQuestionTag(bugBoardQuestionTags);

        questionRepo.save(bugBoardQuestion);
    }

    @Override
    public void updateAnswer(BugBoardAnswerDto answerDto) {
        answerRepo.save(BugBoardAnswer.builder()
                .questionId(answerDto.getQuestionId())
                .content(answerDto.getContent())
                .updateDate(getDate())
                .build()
        );
    }

    @Override
    public void updateComment(BugBoardCommentDto commentDto) {
        commentRepo.save(BugBoardComment.builder()
                .answerId(commentDto.getAnswerId())
                .questionId(commentDto.getQuestionId())
                .content(commentDto.getContent())
                .updateDate(getDate())
                .build()
        );
    }

    @Override
    public void addQuestionLike(Long id) {
        BugBoardQuestion bugBoardQuestion = questionRepo.findById(id).get();
        bugBoardQuestion.setLikes(bugBoardQuestion.getLikes()+1);
        questionRepo.save(bugBoardQuestion);
    }

    @Override
    public void addAnswerLike(Long id) {
        BugBoardAnswer answer = answerRepo.findById(id).get();
        answer.setLikes(answer.getLikes()+1);
        answerRepo.save(answer);
    }

    @Override
    public void setBlind(String postType, Long id) {
        if(postType == "question") {
            BugBoardQuestion question = questionRepo.findById(id).get();
            question.setBlind(true);
            questionRepo.save(question);
        } else if( postType == "answer") {
            BugBoardAnswer answer = answerRepo.findById(id).get();
            answer.setBlind(true);
            answerRepo.save(answer);
        } else if ( postType == "comment") {
            BugBoardComment comment = commentRepo.findById(id).get();
            comment.setBlind(true);
            commentRepo.save(comment);
        }
    }


    private static String getDate() {
        Date date = new Date();
        SimpleDateFormat transFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        return transFormat.format(date);
    }
}





