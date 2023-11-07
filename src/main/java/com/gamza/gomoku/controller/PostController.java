package com.gamza.gomoku.controller;

import com.gamza.gomoku.dto.post.PostListResponseDto;
import com.gamza.gomoku.dto.post.PostRequestDto;
import com.gamza.gomoku.dto.post.PostResponseDto;
import com.gamza.gomoku.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/post")
@RequiredArgsConstructor
public class PostController {
    private final PostService postService;
    @PostMapping()
    public ResponseEntity<String> createPost(@RequestBody PostRequestDto postRequestDto, HttpServletRequest request) {
        return postService.createPost(postRequestDto,request);
    }
    @GetMapping("/{id}")
    public PostResponseDto getOnePostWithId(@PathVariable long id) {
        return postService.getOnePostWithId(id);
    }
    @GetMapping("/list")
    public Page<PostListResponseDto> getPostList(@RequestParam("page")int page) {
        return postService.getPostList(page);
    }
}
