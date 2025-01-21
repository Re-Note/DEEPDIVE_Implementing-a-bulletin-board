package com.example.myboard.repository;

import com.example.myboard.model.DeleteStatus;
import com.example.myboard.model.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CommentRepository extends JpaRepository<Comment, Long> {
	// 특정 게시글의 댓글 조회
	// 연관 엔티티 포함하여 탐색 _
	Optional<Comment> findByCommentNoAndBoard_BoardNoAndDeleteStatus(Long commentId, Long boardId, DeleteStatus deleteStatus);
}
