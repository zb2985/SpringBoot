package kopo.jjh.prj.domain.repository;

import kopo.jjh.prj.domain.entity.File;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FileRepository extends JpaRepository<File, Long> {
}