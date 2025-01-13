package com.temp.up_v3.service;

import com.temp.up_v3.domain.Contest;
import com.temp.up_v3.domain.ContestImageDetails;
import com.temp.up_v3.dto.contest.ContestRequestDto;
import com.temp.up_v3.repository.ImageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;

@Service
@Transactional(rollbackFor = Exception.class)
@RequiredArgsConstructor
public class ImageService {

    private final ImageRepository imageRepository;

    public void uploadImage(Contest contest, MultipartFile image) {
        try {
            String uploadsDir = "src/main/resources/static/uploads/";

            String dbFilePath = saveImage(image, uploadsDir);

            ContestImageDetails imageDetails = new ContestImageDetails(contest, dbFilePath);
            imageDetails.setContest(contest);  // setContest 먼저 호출
            imageRepository.save(imageDetails); // save 후

        } catch (IOException e) {
            // 파일 저장 중 오류가 발생한 경우 처리
            e.printStackTrace();
        }
    }

    // 이미지 파일을 저장하는 메서드
    private String saveImage(MultipartFile image, String uploadsDir) throws IOException {
        // 파일 이름 생성
        String fileName = UUID.randomUUID().toString().replace("-", "") + "_" + image.getOriginalFilename();
        // 실제 파일이 저장될 경로
        String filePath = uploadsDir + fileName;
        // DB에 저장할 경로 문자열
        String dbFilePath = "/uploads/" + fileName;

        Path path = Paths.get(filePath); // Path 객체 생성
        Files.createDirectories(path.getParent()); // 디렉토리 생성
        Files.write(path, image.getBytes()); // 디렉토리에 파일 저장

        return dbFilePath;
    }
}