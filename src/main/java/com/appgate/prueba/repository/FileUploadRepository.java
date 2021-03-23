package com.appgate.prueba.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.appgate.prueba.model.File;

@Repository
public interface FileUploadRepository extends JpaRepository<File, Long>{

	
	
	
}
