package io.app.service.impl;


import ch.qos.logback.core.util.StringUtil;
import io.app.models.Video;
import io.app.repository.VideoRepository;
import io.app.service.VideoService;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class VideoServiceImpl implements VideoService{
	private final VideoRepository repository;

	@Value("${files.video}")
	String dir;

	@PostConstruct
	public void inti(){
		File file=new File(dir);
		if (!file.exists()){
			file.mkdir();
			log.info("Parent Dir has created: {}",file.getAbsolutePath());
		}else{
			log.info("Parent folder already created: {}",file.getAbsolutePath());
		}
	}

	@Override
	public Video save(Video video, MultipartFile file) {
		try{
			String fileName=file.getOriginalFilename();
			String contentType=file.getContentType();
			InputStream inputStream=file.getInputStream();


			//folder path
			String cleanFilePath=StringUtils.cleanPath(fileName);
			String cleanFolderPath=StringUtils.cleanPath(this.dir);

			//File path to save
			Path path=Paths.get(cleanFolderPath,cleanFilePath);
			Files.copy(inputStream,path, StandardCopyOption.REPLACE_EXISTING);

			video.setContentType(contentType);
			video.setFilePath(path.toString());
			Video savedVideo=repository.save(video);

			return savedVideo;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public Video getVideo(String videoId) {
		return null;
	}

	@Override
	public Video getVideoByTitle(String title) {
		return null;
	}

	@Override
	public List<Video> getAllVideo() {
		return List.of();
	}
}
