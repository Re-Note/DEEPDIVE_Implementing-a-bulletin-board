package com.example.myboard.repository;

import com.example.myboard.model.DeleteStatus;
import com.example.myboard.model.entity.Board;
import com.example.myboard.model.entity.Comment;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface CommentRepository extends JpaRepository<Comment, Long> {
	// (N+1 문제 해결)
	@Query("SELECT b FROM Board b LEFT JOIN FETCH b.comments WHERE b.deleteStatus = :deleteStatus")
	List<Board> findAllByDeleteStatusWithComments(@Param("deleteStatus") DeleteStatus deleteStatus, Pageable pageable);

	// 특정 게시글의 댓글 조회
	// 연관 엔티티 포함하여 탐색 _
	Optional<Comment> findByCommentNoAndBoard_BoardNoAndDeleteStatus(Long commentId, Long boardId, DeleteStatus deleteStatus);
}
