package com.example.myboard.repository;

import com.example.myboard.model.DeleteStatus;
import com.example.myboard.model.entity.Board;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BoardRepository extends JpaRepository<Board, Long> {
	// 전체 게시글 조회
	Page<Board> findAllByDeleteStatus(DeleteStatus deleteStatus, Pageable pageable);

	// 게시글 단건 조회 (게시글 + 댓글)
	@Query("SELECT b FROM Board b LEFT JOIN FETCH b.comments c WHERE b.boardNo = :boardNo")
	Optional<Board> findBoardWithCommentsByBoardNo(@Param("boardNo") Long boardNo);
}
