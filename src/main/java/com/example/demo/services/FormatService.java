package com.example.demo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entities.Format;
import com.example.demo.repositories.FormatRepository;

@Service
public class FormatService {
	
	@Autowired
	private FormatRepository formatsRepository;
	
	public Format addFormat(Format format) {
		return formatsRepository.save(format);
	}
	
	public String deleteFormat(Long formatId) {
		formatsRepository.deleteById(formatId);
		return " format supprimé";
	}

}
