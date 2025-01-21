package com.example.myboard.model.entity;

import com.example.myboard.model.DeleteStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLRestriction;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@SQLDelete(sql = "UPDATE comment SET DELETE_STATUS = 'DELETE' WHERE comment_no = ?")
@SQLRestriction("DELETE_STATUS = 'ACTIVE'")
@Table(indexes = {
	@Index(name = "idx_comment_board_no", columnList = "board_no"),
	@Index(name = "idx_comment_delete_status", columnList = "deleteStatus")
})
public class Comment {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long commentNo;

	@Column(length = 1000)
	private String body;

	@Enumerated(EnumType.STRING)
	private DeleteStatus deleteStatus;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="BOARD_NO")
	private Board board;
}
