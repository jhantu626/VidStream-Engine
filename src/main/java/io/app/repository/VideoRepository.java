package io.app.repository;

import io.app.models.Video;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface VideoRepository extends JpaRepository<Video,String> {
	Optional<Video> findByTitle(String title);
}
