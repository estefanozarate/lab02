package com.example.mycrud.service;

import com.example.mycrud.entity.*;
import com.example.mycrud.repository.SongRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SongService {

    @Autowired
    private SongRepository songRepository;

    public Song saveSong(Song obj) {
        return songRepository.save(obj);
    }

    public Optional<Song> findSongById(Long id) {
        return songRepository.findById(id);
    }

    public List<Song> findAllSongs() {
        return songRepository.findAll();
    }

    public Song updateSong(Long id, Song student) {
        Optional<Song> existingStudent = songRepository.findById(id);
        if (existingStudent.isPresent()) {
            student.setId(id);
            return songRepository.save(student);
        }
        // Handle student not found, possibly throw an exception
        return null;
    }

    public Song partialUpdateSong(Long id, Song student) {
        Optional<Song> existingStudent = songRepository.findById(id);
        if (existingStudent.isPresent()) {
            BeanUtils.copyProperties(student, existingStudent.get(), "id");
            return songRepository.save(existingStudent.get());
        }
        // Handle student not found, possibly throw an exception
        return null;
    }

    public boolean isEnrolledInAnyCourse(Long id) {
        Optional<Song> studentOptional = songRepository.findById(id);
        if (studentOptional.isPresent()) {
        	Song student = studentOptional.get();
            return !student.getPlaylists().isEmpty(); // true if the student is enrolled in any courses
        }
        return false;
    }

    public void deleteStudentById(Long id) {
    	songRepository.deleteById(id);
    }
}
