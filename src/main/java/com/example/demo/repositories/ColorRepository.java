package com.example.demo.repositories;



import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entities.Color;
import com.example.demo.enums.EnumColor;


public interface ColorRepository extends JpaRepository<Color, Long> {
	
	Color findByName (EnumColor name);

}
