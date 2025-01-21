package com.example.myboard.model.response;

import com.example.myboard.model.entity.Comment;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CommentResponse {
	private Long commentNo;
	private String body;

	// 정적 팩토리 메서드
	public static CommentResponse from(Comment comment) {
		return new CommentResponse(comment.getCommentNo(), comment.getBody());
	}
}
