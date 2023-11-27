package com.example.mycrud.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.mycrud.service.PlaylistService;
import com.example.mycrud.entity.Playlist;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/playlists")
public class PlaylistController {

    @Autowired
    private PlaylistService playlistService;

    // POST: Create a new Playlist
    @PostMapping
    public ResponseEntity<Playlist> createPlaylist(@RequestBody Playlist playlist) {
        return new ResponseEntity<>(playlistService.savePlaylist(playlist), HttpStatus.CREATED);
    }

    // GET: Retrieve a Playlist by id
    @GetMapping("/{id}")
    public ResponseEntity<Playlist> playlist(@PathVariable Long id) {
        Optional<Playlist> playlist = playlistService.findPlaylistById(id);
        return ResponseEntity.status(200).body(playlist.get());
    }

    // GET: Retrieve all Playlists
    @GetMapping
    public ResponseEntity<List<Playlist>> getAllPlaylists() {
        return new ResponseEntity<>(playlistService.findAllPlaylists(), HttpStatus.OK);
    }

    // PUT: Update a Playlist by id
    @PutMapping("/{id}")
    public ResponseEntity<Playlist> updatePlaylist(@PathVariable Long id, @RequestBody Playlist updatedPlaylist) {
        return new ResponseEntity<>(playlistService.updatePlaylist(id, updatedPlaylist), HttpStatus.OK);
    }

    // PATCH: Partially update a Playlist by id
    @PatchMapping("/{id}")
    public ResponseEntity<Playlist> partialUpdatePlaylist(@PathVariable Long id, @RequestBody Playlist updatedFields) {
        return new ResponseEntity<>(playlistService.partialUpdatePlaylist(id, updatedFields), HttpStatus.OK);
    }

    // DELETE: Delete a Playlist by id
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePlaylist(@PathVariable Long id) {
        playlistService.deletePlaylistById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
