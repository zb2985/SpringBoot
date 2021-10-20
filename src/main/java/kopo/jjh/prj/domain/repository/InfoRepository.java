package kopo.jjh.prj.domain.repository;


import kopo.jjh.prj.domain.entity.BoardInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface InfoRepository extends JpaRepository<BoardInfo, Long> {
List<BoardInfo> findByTitleContaining(String keyword);
}
