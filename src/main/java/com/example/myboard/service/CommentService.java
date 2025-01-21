package com.example.myboard.service;

import com.example.myboard.model.DeleteStatus;
import com.example.myboard.model.entity.Board;
import com.example.myboard.model.entity.Comment;
import com.example.myboard.model.request.CommentDeleteRequest;
import com.example.myboard.model.request.CommentPostRequest;
import com.example.myboard.model.response.BoardResponse;
import com.example.myboard.repository.BoardRepository;
import com.example.myboard.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CommentService {
	private final BoardRepository boardRepository;
	private final CommentRepository commentRepository;

	// 댓글 추가
	@Transactional
	public BoardResponse writeComment(CommentPostRequest request) {
		// 예외처리 - 존재하지 않는 게시글인 경우
		Optional<Board> boardOptional = boardRepository.findBoardWithCommentsByBoardNo(request.getBoardNo());
		Board board = boardOptional.orElseThrow(() -> new RuntimeException("존재하지 않는 게시글입니다."));

		// Comment에 등록해도 되지만, 이미 받아온 board를 활용하여 연관관계 헬퍼 메서드 사용
		board.addComment(request.getCommentBody());
		boardRepository.save(board);

		return BoardResponse.from(board);
	}

	// 댓글 삭제
	@Transactional
	public String deleteComment(CommentDeleteRequest request) {
		Optional<Comment> commentOptional = commentRepository.findByCommentNoAndBoard_BoardNoAndDeleteStatus(
			request.getCommentNo(), request.getBoardNo(), DeleteStatus.ACTIVE
		);
		Comment comment =commentOptional.orElseThrow(() -> new RuntimeException("존재하지 않는 댓글입니다"));
		commentRepository.delete(comment);
		return "OK";
	}
}
