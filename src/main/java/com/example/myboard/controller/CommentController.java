package com.example.myboard.controller;

import com.example.myboard.model.request.BoardDeleteRequest;
import com.example.myboard.model.request.CommentDeleteRequest;
import com.example.myboard.model.request.CommentPostRequest;
import com.example.myboard.model.response.BoardResponse;
import com.example.myboard.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class CommentController {
	private final CommentService commentService;

	// 댓글 등록
	@PostMapping("comment")
	public BoardResponse writeComment(
		@RequestBody CommentPostRequest commentPostRequest
	) {
		return commentService.writeComment(commentPostRequest);
	}

	// 댓글 삭제
	@DeleteMapping("comment")
	public String deleteComment(
		@RequestBody CommentDeleteRequest commentDeleteRequest
	) {
		return commentService.deleteComment(commentDeleteRequest);
	}
}
