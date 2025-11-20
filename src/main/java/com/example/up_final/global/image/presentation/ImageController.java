package com.example.up_final.global.image.presentation;

import com.example.up_final.global.common.response.Response;
import com.example.up_final.global.image.presentation.dto.res.ImageInfo;
import com.example.up_final.global.image.service.ImageService;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@AllArgsConstructor
@RequestMapping("/image")
public class ImageController {

    private final ImageService imageService;

    @PostMapping(value = "/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ImageInfo uploadImage(@RequestParam MultipartFile file) {
        return imageService.uploadImage(file);
    }
}
