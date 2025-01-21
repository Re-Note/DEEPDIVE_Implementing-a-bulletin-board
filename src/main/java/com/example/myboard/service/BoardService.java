package com.example.myboard.service;

import com.example.myboard.model.DeleteStatus;
import com.example.myboard.model.entity.Board;
import com.example.myboard.model.request.BoardDeleteRequest;
import com.example.myboard.model.request.BoardPostRequest;
import com.example.myboard.model.response.BoardListResponse;
import com.example.myboard.model.response.BoardResponse;
import com.example.myboard.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BoardService {
	private final BoardRepository boardRepository;

	// 게시글 등록
	@Transactional
	public BoardResponse writeBoard(BoardPostRequest request) {
		Board board = new Board();
		board.setTitle(request.getTitle());
		board.setBody(request.getBody());
		board.setDeleteStatus(DeleteStatus.ACTIVE);

		return BoardResponse.from(boardRepository.save(board));
	}

	// 게시글 목록 조회
	// public List<BoardListResponse> searchBoardList(int page, int pageSize) {
	// 	return boardRepository.findAllByDeleteStatus(
	// 			DeleteStatus.ACTIVE,
	// 			PageRequest.of(page, pageSize, Sort.by("boardNo").descending())
	// 		).map(BoardListResponse::from)
	// 		.toList();
	// }

	// 게시글 목록 조회 (N+1 문제 해결)
	public List<BoardListResponse> searchBoardList(int page, int pageSize) {
		return boardRepository.findAllByDeleteStatusWithComments(
				DeleteStatus.ACTIVE,
				PageRequest.of(page, pageSize, Sort.by("boardNo").descending())
			).stream()
			.map(BoardListResponse::from)
			.toList();
	}

	// 게시글 단건 조회(게시글 + 댓글)
	public BoardResponse searchBoard(Long boardNo) {
		return boardRepository.findBoardWithCommentsByBoardNo(boardNo)
			.map(BoardResponse::from)
			.orElseThrow(() -> new RuntimeException("존재하지 않는 게시글입니다!"));
	}

	// 게시글 삭제
	@Transactional
	public String deleteBoard(BoardDeleteRequest request) {
		// 예외 처리
		Optional<Board> boardOptional = boardRepository.findById(request.getBoardNo());
		Board board = boardOptional.orElseThrow(() -> new RuntimeException("존재하지 않는 게시글입니다"));
		boardRepository.delete(board);

		return "OK";
	}

}
