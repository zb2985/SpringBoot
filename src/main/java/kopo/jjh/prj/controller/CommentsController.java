package kopo.jjh.prj.controller;

import kopo.jjh.prj.domain.entity.Comments;
import kopo.jjh.prj.domain.repository.AccountRepository;
import kopo.jjh.prj.dto.CommentsRequest;
import kopo.jjh.prj.security.domain.Account;
import kopo.jjh.prj.service.CommentsService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/comments")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class CommentsController {

    @NonNull
    AccountRepository UR;
    @NonNull
    CommentsService commentService;



    // CREATE
    @PostMapping("/add/{postNo}")
    public ResponseEntity<List<Comments>> addComment(@PathVariable Long postNo, @RequestBody CommentsRequest commentsrequest, Principal principal){
        commentsrequest.setPostNo(postNo);
        commentsrequest.setCreated_by(principal.getName());
        Optional<Account> findNo=UR.findByName(principal.getName());
        findNo.ifPresent(finduserNO->{
            commentsrequest.setUserNo(finduserNO.getUsername_no());
            Comments cm= this.commentService.createComment(MapperUtil.convert(commentsrequest, Comments.class),finduserNO,commentsrequest.getPostNo());
        });

        return new ResponseEntity<>(this.commentService.Listcomments(postNo), HttpStatus.CREATED);

    }

    // READ
    @GetMapping("/list/{postNo}")
    public ResponseEntity<List<Comments>> addComment(@PathVariable Long postNo){
        return new ResponseEntity<>(this.commentService.Listcomments(postNo),HttpStatus.CREATED);
    }

    //DELETE
    @DeleteMapping("/delete/{postNo}/{commentsNo}")
    public ResponseEntity<List<Comments>> addComment(@PathVariable Long postNo,@PathVariable Long commentsNo) {
        return new ResponseEntity<>(this.commentService.Deletecomments(commentsNo,postNo),HttpStatus.CREATED);
    }

    //UPDATE
    @PutMapping("/modify/{postNo}/{commentsNo}")
    public ResponseEntity<List<Comments>> modifyComment(@PathVariable Long postNo, @PathVariable Long commentsNo, @RequestBody CommentsRequest commentsrequest, Principal principal) {
        commentsrequest.setCreated_by(principal.getName());
        return new ResponseEntity<>(this.commentService.Modifycomments(MapperUtil.convert(commentsrequest, Comments.class),commentsNo,postNo),HttpStatus.CREATED);
    }

}