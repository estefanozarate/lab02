package com.example.mycrud.service;

import com.example.mycrud.entity.Playlist;
import com.example.mycrud.repository.PlaylistRepository;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PlaylistService {

	@Autowired
	private PlaylistRepository playListRepository;


	public Playlist savePlaylist(Playlist course) {
		return playListRepository.save(course);
	}

	public Optional<Playlist> findPlaylistById(Long id) {
		return playListRepository.findById(id);
	}

	public List<Playlist> findAllPlaylists() {
		return playListRepository.findAll();
	}

	public Playlist updatePlaylist(Long id, Playlist course) {
		Optional<Playlist> existingCourse = playListRepository.findById(id);
		if (existingCourse.isPresent()) {
			course.setId(id);
			return playListRepository.save(course);
		}
		return null;
	}

	public Playlist partialUpdatePlaylist(Long id, Playlist course) {
		Optional<Playlist> existingCourse = playListRepository.findById(id);
		if (existingCourse.isPresent()) {
			BeanUtils.copyProperties(course, existingCourse.get(), "id");
			return playListRepository.save(existingCourse.get());
		}
		// Handle course not found, possibly throw an exception
		return null;
	}
	
    public void deletePlaylistById(Long id) {
    	playListRepository.deleteById(id);
    }

	public boolean hasStudents(Long id) {
		Optional<Playlist> courseOptional = playListRepository.findById(id);
		if (courseOptional.isPresent()) {
			Playlist course = courseOptional.get();
			return !course.getSongs().isEmpty(); // true if the course has any students enrolled
		}
		return false;
	}

}
