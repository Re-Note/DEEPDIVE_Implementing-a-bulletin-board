package com.example.myboard.model.entity;

import com.example.myboard.model.DeleteStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLRestriction;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@SQLDelete(sql = "UPDATE board SET DELETE_STATUS = 'DELETE' WHERE board_no = ?")
@SQLRestriction("DELETE_STATUS = 'ACTIVE'")
@Table(indexes = {
	@Index(name = "idx_board_delete_status", columnList = "deleteStatus"),
	@Index(name = "idx_board_no", columnList = "boardNo")
})
public class Board {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long boardNo;

	private String title;

	@Column(length = 1000)
	private String body;

	@Enumerated(EnumType.STRING)
	private DeleteStatus deleteStatus;

	// Comment와 연관 관계
	@OneToMany(mappedBy = "board", cascade = CascadeType.ALL)
	@SQLRestriction("DELETE_STATUS = 'ACTIVE'")
	private List<Comment> comments = new ArrayList<>();

	// 연관관계 헬퍼 메서드 추가
	public Board addComment(String commentBody) {
		Comment comment = new Comment();
		comment.setBody(commentBody);
		comment.setBoard(this);
		comment.setDeleteStatus(DeleteStatus.ACTIVE);

		this.getComments().add(comment);
		return this;
	}
}
