package com.example.myboard.model.response;

import com.example.myboard.model.entity.Board;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class BoardListResponse {
	private Long boardNo;
	private String title;

	// 정적 팩토리 메서드
	public static BoardListResponse from(Board board) {
		return new BoardListResponse(board.getBoardNo(), board.getTitle());
	}
}
