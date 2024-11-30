package com.example.demo.repositories;



import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entities.Format;
import com.example.demo.enums.EnumFormat;




public interface FormatRepository extends JpaRepository<Format, Long> {
	
	Format findByName (EnumFormat name);

}
