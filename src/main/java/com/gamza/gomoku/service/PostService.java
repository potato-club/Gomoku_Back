package com.gamza.gomoku.service;

import com.gamza.gomoku.dto.post.PostListResponseDto;
import com.gamza.gomoku.dto.post.PostRequestDto;
import com.gamza.gomoku.dto.post.PostResponseDto;
import com.gamza.gomoku.entity.PostEntity;
import com.gamza.gomoku.error.ErrorCode;
import com.gamza.gomoku.error.execption.NotFoundException;
import com.gamza.gomoku.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PostService {
    private final PostRepository postRepository;

    public ResponseEntity<String> createPost(PostRequestDto postRequestDto, HttpServletRequest request) {
        //체크
        PostEntity postEntity = new PostEntity().builder()
                .title(postRequestDto.getTitle())
                .writer("ㅇㅇ")
                .text(postRequestDto.getText())
                .userEntity()
                .commentEntityList(new ArrayList<>())
                .build();
        postRepository.save(postEntity);
        return ResponseEntity.ok("200ok");
    }
    public PostResponseDto getOnePostWithId(long id) {
        PostEntity postEntity = postRepository.findById(id).orElseThrow(()->{throw new NotFoundException(ErrorCode.NOT_FOUND_EXCEPTION.getMessage(), ErrorCode.NOT_FOUND_EXCEPTION);});
        return new PostResponseDto(postEntity);
    }
    public Page<PostListResponseDto> getPostList(int page) {
        PageRequest pageRequest = PageRequest.of(page-1,20);
        Page<PostEntity> postEntityPage = postRepository.findByAllOrderByIdDesc(pageRequest);
        return new PageImpl<>(postEntityPage.getContent().stream().map(PostListResponseDto::new).collect(Collectors.toList()), pageRequest, postEntityPage.getTotalElements());
    }
}
