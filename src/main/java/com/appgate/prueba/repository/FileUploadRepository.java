package com.appgate.prueba.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.appgate.prueba.model.File;

@Repository
public interface FileUploadRepository extends JpaRepository<File, Long> {

	@Query(value = "SELECT * FROM PRUEBA p WHERE p.IP_from <=?1 and p.IP_to >= ?1", nativeQuery = true)
	List<File> findByIPFromAndIPTo(String result);

}
