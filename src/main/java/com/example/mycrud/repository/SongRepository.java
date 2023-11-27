package com.example.mycrud.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.mycrud.entity.Song;

@Repository
public interface SongRepository extends JpaRepository <Song, Long> {


}
