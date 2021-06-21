package kopo.jjh.prj.service;

import kopo.jjh.prj.domain.entity.Board;
import kopo.jjh.prj.domain.entity.Comments;
import kopo.jjh.prj.domain.repository.BoardRepository;
import kopo.jjh.prj.domain.repository.CommentsRepository;
import kopo.jjh.prj.mapper.UserService;
import kopo.jjh.prj.security.domain.Account;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class CommentsService {

    @NonNull
    private BoardRepository postrepository;
    @NonNull
    private CommentsRepository CMR;
    @NonNull
    private UserService userService;

    // 댓글 등록
    public Comments createComment(Comments comment, Account user, long id) {
        Optional<Board> post = this.postrepository.findById(id);
        post.ifPresent(re->{
            comment.changePost(re);
        });
        comment.changeAuthor(user);
        return this.CMR.save(comment);
    }

    //댓글 리스트
    @Transactional(readOnly = true)
    public List<Comments> Listcomments(long postNo) {
        return this.CMR.getCommentsOfPost(postNo);
    }
    // 댓글 삭제
    @Transactional
    public List<Comments> Deletecomments(long commentsNo,long postNo) {
        this.CMR.deleteById(commentsNo);
        return this.CMR.getCommentsOfPost(postNo);
    }

    //댓글 수정
    @Transactional
    public List<Comments> Modifycomments(Comments comment,long commentNo,long postNo) {
        Optional<Comments> modifycomment=this.CMR.findById(commentNo);
        modifycomment.ifPresent(origin->{
            origin.setContent(comment.getContent());
            this.CMR.save(origin);
        });
        return this.CMR.getCommentsOfPost(postNo);
    }


}