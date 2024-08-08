package io.app.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.*;

@Entity
@Table(name = "video")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Video {
	@Id
	private String id;
	private String title;
	private String desccription;
	private String contentType;
	private String filePath;
//	@ManyToOne
//	private Course course;
}
















