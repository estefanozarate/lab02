package com.example.mycrud.service;

import com.example.mycrud.entity.Playlist;
import com.example.mycrud.entity.Song;
import com.example.mycrud.repository.PlaylistRepository;
import com.example.mycrud.repository.SongRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PlaylistSongRelationshipService {

	@Autowired
	private PlaylistRepository playlistRepository;

	@Autowired
	private SongRepository songRepository;

	public void addSongToPlaylist(Long songId, Long playlistId) {
		Song song = songRepository.findById(songId).orElseThrow(() -> new RuntimeException("Song not found"));
		Playlist playlist = playlistRepository.findById(playlistId)
				.orElseThrow(() -> new RuntimeException("Playlist not found"));

		song.addToPlaylist(playlist);

		songRepository.save(song);
	}
}
