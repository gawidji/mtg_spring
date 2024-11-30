package com.example.demo.repositories;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entities.Color;
import com.example.demo.enums.EnumColor;


public interface ColorRepository extends JpaRepository<Color, Long> {
	
	List<Color> findByName (EnumColor name);

}
