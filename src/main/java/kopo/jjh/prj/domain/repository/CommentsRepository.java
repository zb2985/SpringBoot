package kopo.jjh.prj.domain.repository;

import io.lettuce.core.dynamic.annotation.Param;
import kopo.jjh.prj.domain.entity.Comments;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CommentsRepository extends CrudRepository<Comments,Long> {


    @Query("SELECT c from Comments c where c.post.id=:postNo and c.id>0 order by c.id ASC ")
    public List<Comments> getCommentsOfPost(@Param("postNo") Long postNo);


}