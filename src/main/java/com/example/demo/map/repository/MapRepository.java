package com.example.demo.map.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.map.entity.Map;

public interface MapRepository extends JpaRepository<Map, String> {

}
