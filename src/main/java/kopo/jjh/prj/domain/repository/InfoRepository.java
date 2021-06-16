package kopo.jjh.prj.domain.repository;


import kopo.jjh.prj.domain.entity.BoardInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InfoRepository extends JpaRepository<BoardInfo, Long> {

}