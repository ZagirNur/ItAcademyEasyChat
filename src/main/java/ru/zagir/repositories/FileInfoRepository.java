package ru.zagir.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.zagir.models.FileInfo;

public interface FileInfoRepository extends JpaRepository<FileInfo, Long> {
  FileInfo findOneByStorageFileName(String fileName);
}
