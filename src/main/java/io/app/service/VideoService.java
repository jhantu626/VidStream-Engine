package io.app.service;

import io.app.models.Video;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface VideoService {
	public Video save(Video video, MultipartFile file) throws IOException;
	public Video getVideo(String videoId);
	public Video getVideoByTitle(String title);
	public List<Video> getAllVideo();
}
