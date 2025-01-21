package com.example.myboard.model.response;

import com.example.myboard.model.DeleteStatus;
import com.example.myboard.model.entity.Board;
import com.example.myboard.model.entity.Comment;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
public class BoardResponse {
	private Long boardNo;
	private String title;
	private String body;
	private DeleteStatus deleteStatus;
	private List<CommentResponse> comments = new ArrayList<>();

	// 정적 팩토리 메서드
	public static BoardResponse from(Board board) {
		return new BoardResponse(
			board.getBoardNo(),
			board.getTitle(),
			board.getBody(),
			board.getDeleteStatus(),
			board.getComments().stream()
				.map(CommentResponse::from).collect(Collectors.toList())
		);
	}
}
