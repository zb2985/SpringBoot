package kopo.jjh.prj.domain.repository;

import kopo.jjh.prj.domain.entity.Board;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardRepository extends JpaRepository<Board, Long> {

}