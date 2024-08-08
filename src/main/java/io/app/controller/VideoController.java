package io.app.controller;

import io.app.models.Video;
import io.app.payload.CustomMsg;
import io.app.service.impl.VideoServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

@Slf4j
@RestController
@RequestMapping("/api/v1/videos")
@RequiredArgsConstructor
public class VideoController {
	private final VideoServiceImpl service;

	@PostMapping
	public ResponseEntity<?> create(
			@RequestParam("file") MultipartFile file,
			@RequestParam("title") String title,
			@RequestParam("desc") String desc){
		Video video=Video.builder()
				.id(UUID.randomUUID().toString())
				.title(title)
				.desccription(desc)
				.build();
		Video savedVideo=service.save(video,file);

		if (savedVideo!=null){
			log.info("VideoSaved: {}",savedVideo.getFilePath());
			return ResponseEntity
					.ok(video);
		}

		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
				.body(
					CustomMsg.builder().msg("INTERNAL_SERVER_ERROR").build()
				);
	}

}
