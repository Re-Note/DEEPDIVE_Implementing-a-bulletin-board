package com.example.myboard.model.request;

import lombok.Data;

@Data
public class CommentPostRequest {
	private Long boardNo;
	private String commentBody;
}
