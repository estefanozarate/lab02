package com.example.mycrud.controller;

import com.example.mycrud.entity.Song;
import com.example.mycrud.service.SongService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/songs")
public class SongController {

    @Autowired
    private SongService songService;

    // POST: Create a new Song
    @PostMapping
    public ResponseEntity<Song> createSong(@RequestBody Song song) {
        return new ResponseEntity<>(songService.saveSong(song), HttpStatus.CREATED);
    }

    // GET: Retrieve a Song by id
    @GetMapping("/{id}")
    public ResponseEntity<Song> song(@PathVariable Long id) {
        Optional<Song> song = songService.findSongById(id);
        return ResponseEntity.status(200).body(song.get());
    }

    // GET: Retrieve all Songs
    @GetMapping
    public ResponseEntity<List<Song>> getAllSongs() {
        List<Song> songs = songService.findAllSongs();
        return new ResponseEntity<>(songs, HttpStatus.OK);
    }

    // PUT: Update a Song by id
    @PutMapping("/{id}")
    public ResponseEntity<Song> updateSong(@PathVariable Long id, @RequestBody Song updatedSong) {
        return new ResponseEntity<>(songService.updateSong(id, updatedSong), HttpStatus.OK);
    }

    // PATCH: Partially update a Song by id
    @PatchMapping("/{id}")
    public ResponseEntity<Song> partialUpdateSong(@PathVariable Long id, @RequestBody Song updatedFields) {
        return new ResponseEntity<>(songService.partialUpdateSong(id, updatedFields), HttpStatus.OK);
    }

    // DELETE: Delete a Song by id
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSong(@PathVariable Long id) {
        songService.deleteStudentById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
