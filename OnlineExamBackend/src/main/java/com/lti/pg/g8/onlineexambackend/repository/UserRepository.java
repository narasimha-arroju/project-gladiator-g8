package com.lti.pg.g8.onlineexambackend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.lti.pg.g8.onlineexambackend.dto.SearchStudentsDto;
import com.lti.pg.g8.onlineexambackend.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
	
	@Query("Select u from User u where u.userEmail = ?1")
	User findByUserEmail(String name);
	
	@Query("Select u from User u join u.address a where a.addressId = ?1")
	List<User> findAllUsersByAddressId(Long addressId);
	
	@Query("Select s.userId from Submission s where s.examId = ?1")
	List<Long> findAllUsersByExamId(Long examId);
	
	@Query("select u.userName,u.userEmail,u.userMobile,a.city,a.state,e.examName,s.percentages from User u,Exam e, Submission s,Address a where e.examId=s.examId and u.userId=s.userId and s.addressId=a.addressId")
	List<SearchStudentsDto> searchStudents();
	
	
}



