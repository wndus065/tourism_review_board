package com.example.demo.map.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.map.entity.MapEntity;

public interface MapRepository extends JpaRepository<MapEntity, String> {



	

}
