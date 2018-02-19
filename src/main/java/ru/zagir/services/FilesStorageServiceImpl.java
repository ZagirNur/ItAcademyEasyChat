package ru.zagir.services;

import lombok.SneakyThrows;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.zagir.models.FileInfo;
import ru.zagir.models.User;
import ru.zagir.repositories.FileInfoRepository;
import ru.zagir.repositories.UserRepository;
import ru.zagir.utils.FilesStorageUtil;
import ru.zagir.utils.ImagesFilesValidator;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

@Service
public class FilesStorageServiceImpl implements FilesStorageService {

  @Autowired
  private FilesStorageUtil filesStorageUtil;

  @Autowired
  private FileInfoRepository fileInfoRepository;

  @Autowired
  private ImagesFilesValidator imagesFilesValidator;

  @Autowired
  private UserRepository userRepository;

  @Autowired
  private ChatService chatService;

  @Override
  public String saveFile(MultipartFile file) {
    FileInfo fileInfo = filesStorageUtil.convertFromMultipart(file);
    fileInfoRepository.save(fileInfo);
    filesStorageUtil.copyToStorage(file, fileInfo.getStorageFileName());
    return fileInfo.getStorageFileName();
  }

  @Override
  @SneakyThrows
  public void writeFileToResponse(String fileName, HttpServletResponse response) {
    FileInfo fileInfo = fileInfoRepository.findOneByStorageFileName(fileName);
    response.setContentType(fileInfo.getType());
    InputStream inputStream = new FileInputStream(new File(fileInfo.getUrl()));
    IOUtils.copy(inputStream, response.getOutputStream());
    response.flushBuffer();
  }

  @Override
  public String saveImage(Authentication authentication, MultipartFile file) {
    // проверяем файл на валидность - картинка или нет
    imagesFilesValidator.validate(file);
    // создаем модель на основе мультипарта
    FileInfo fileInfo = filesStorageUtil.convertFromMultipart(file);
    // сохраняем в бд информацию о файле
    fileInfoRepository.save(fileInfo);
    // копируем файл на диск
    filesStorageUtil.copyToStorage(file, fileInfo.getStorageFileName());
    // находим текущего пользователя по авторизации
    User user = chatService.getUser(authentication);
    if (user.getImage() != null) {
      user.getImage().setUser(null);
      fileInfoRepository.save(user.getImage());
    }
    // кладем эту картинку пользователю
    fileInfo.setUser(user);
    fileInfoRepository.save(fileInfo);
    // возвращаем имя файла
    return fileInfo.getStorageFileName();
  }
}
