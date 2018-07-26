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
    int update(@Param("id") Long eventid, @Param("name") String name);	
	
	
	@Query(value = "select first_name, last_name, e.location from 	 users_events ue, users u , events e where ue.user_id=u.id and ue.event_id = :id and e.id = ue.event_id", nativeQuery = true)
	public List<Object[]> findAllUsersForListing(@Param("id") Long eventid);

}
