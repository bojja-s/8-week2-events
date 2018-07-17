package com.hcl.bootcamp.fs.springboot.app.jpa;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.hcl.bootcamp.fs.springboot.app.model.EventVO;

public interface EventsRepository extends JpaRepository<EventVO, Long> {
	List<EventVO> findByName(String name);
	
	@Transactional
    @Modifying
    @Query("UPDATE events e SET e.name = :name WHERE e.id = :id")
    int update(@Param("id") Long companyId, @Param("name") String name);	
}
