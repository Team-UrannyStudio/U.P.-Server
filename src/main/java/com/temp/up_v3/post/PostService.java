package com.temp.up_v3.post;

import com.temp.up_v3.image.ImageDetails;
import com.temp.up_v3.image.ImageInfo;
import com.temp.up_v3.image.ImageRepository;
import com.temp.up_v3.image.ImageService;
import com.temp.up_v3.jwt.Member;
import com.temp.up_v3.jwt.MemberRepository;
import com.temp.up_v3.post.dto.PostListResponseDto;
import com.temp.up_v3.post.dto.PostRequestDto;
import com.temp.up_v3.post.dto.PostResponseDto;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
@AllArgsConstructor
public class PostService {

    private final PostRepository postRepository;
    private final MemberRepository memberRepository;
    private final ImageService imageService;
    private final ImageRepository imageRepository;

    //글 생성
    public void createContent(PostRequestDto requestDto, String content_type, MultipartFile image) {

        Post post = new Post(requestDto);

        if (post.getContact() == null) {
            post.setContact(memberRepository.findByUid(SecurityContextHolder.getContext().getAuthentication().getName()).get().getEmail());
        }

        post.setMember(memberRepository.findMemberByUid(SecurityContextHolder.getContext().getAuthentication().getName()));
        post.setContent_type(content_type);
        postRepository.save(post);

        ImageInfo imageInfo = imageService.uploadImage(image);

        ImageDetails imageDetails = new ImageDetails(imageInfo.getImageUrl(), imageInfo.getImageName());

        imageDetails.setContentId("content_" + post.getId());
        imageRepository.save(imageDetails);
    }

    //전부 찾기
    public List<PostListResponseDto> findAllContents(String content_type) {

        List<Post> postList = postRepository.findAll();
        List<PostListResponseDto> responseDtoList = new ArrayList<>();

        Collections.reverse(postList);

        int count1 = 0;
        int count2 = 1;

        for (Post post : postList) {
            System.out.println(post.getContent_type());
            if (post.getContent_type().equals(content_type)) {

                PostListResponseDto responseDto = new PostListResponseDto(post);
                responseDto.setPage(count2);
                responseDto.setImage_path(imageRepository.findByContentId("content_" + post.getId()).getImagePath());

                responseDtoList.add(responseDto);

                count1 ++;

                if (content_type.equals("community")) {
                    if (count1 % 30 == 0) {
                        count2 ++;
                    }
                } else {
                    if (count1 % 15 == 0) {
                        count2 ++;
                    }
                }
            }
        }

        return responseDtoList;
    }

    //한 개 찾기
    public PostResponseDto findOneContent(Long id) {

        Post post = postRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("조회 실패")
        );

        PostResponseDto responseDto = new PostResponseDto(post);
        responseDto.setImage_path(imageRepository.findByContentId("content_" + id).getImagePath());

        return responseDto;
    }

    //글 수정
    @Transactional
    public void updateContent(Long id, PostRequestDto requestDto, MultipartFile image) {

        ImageDetails imageDetails = imageRepository.findByContentId("content_" + id);

        imageService.deleteImage(imageDetails.getImageName());

        ImageInfo imageInfo = imageService.uploadImage(image);

        imageDetails.update(imageInfo.getImageUrl(), imageInfo.getImageName());

        Post post = postRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("해당 아이디가 존재하지 않습니다.")
        );

        post.update(requestDto);
    }

    //글 삭제
    public void deleteContent(Long id) {

        postRepository.deleteById(id);

        imageService.deleteImage(imageRepository.findByContentId("content_" + id).getImageName());

        imageRepository.delete(imageRepository.findByContentId("content_" + id));
    }

    @Transactional
    public void likeContent(Long id) {

        String memberId = SecurityContextHolder.getContext().getAuthentication().getName();

        Member member = memberRepository.findByUid(memberId)
                .orElseThrow(() -> new IllegalArgumentException("해당 멤버가 존재하지 않습니다."));

        Post post = postRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 콘테스트가 존재하지 않습니다."));

        if (member.getLiked().contains("content_" + id)) {

            member.getLiked().remove("content_" + id);
            post.setLike_num(post.getLike_num() - 1);

        } else {

            member.getLiked().add("content_" + id);
            post.setLike_num(post.getLike_num() + 1);

        }
    }
}
