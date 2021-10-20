package com.lti.pg.g8.onlineexambackend.controller;


import com.lti.pg.g8.onlineexambackend.model.Exam;
import com.lti.pg.g8.onlineexambackend.model.User;
import com.lti.pg.g8.onlineexambackend.service.ExamService;
import com.lti.pg.g8.onlineexambackend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lti.pg.g8.onlineexambackend.model.Admin;
import com.lti.pg.g8.onlineexambackend.model.Exam;
import com.lti.pg.g8.onlineexambackend.service.AdminLoginService;
import com.lti.pg.g8.onlineexambackend.service.ExamService;

@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    ExamService examService;

    
    @Autowired
    AdminLoginService adminLoginService;
    
    @PostMapping("/login")
    public ResponseEntity<Boolean> validateAdmin(@RequestBody Admin admin){
    	return new ResponseEntity<>(this.adminLoginService.validateAdmin(admin),HttpStatus.OK);
    }
    


    @Autowired
    UserService userService;


    @GetMapping("/exams")
    public ResponseEntity<List<Exam>> getAllExams(){
        return new ResponseEntity<>(this.examService.getAllExams(), HttpStatus.OK);
    }
    
    @GetMapping("/exams/{examId}")
    public ResponseEntity<Exam> getExamById(@PathVariable Long examId){
        return new ResponseEntity<>(this.examService.getExamById(examId), HttpStatus.OK);
    }

    @PostMapping("/exams")
    public ResponseEntity<Exam> addNewExam(@RequestBody Exam exam){
        return new ResponseEntity<>(this.examService.addOrUpdate(exam), HttpStatus.CREATED);
    }

    @DeleteMapping("/exams/{examId}")
    public ResponseEntity<Boolean> deleteExam(@PathVariable Long examId){
        return new ResponseEntity<>(this.examService.deleteExamById(examId), HttpStatus.OK);
    }


    @GetMapping("/users")
    public ResponseEntity<List<User>> getAllUsers(){
        return new ResponseEntity<>(this.userService.getAllUsers(), HttpStatus.OK);
    }

}

//{
//    "userName" : "Jaddu",
//    "userEmail" : "jaddu8@gmail.com",
//    "userMobile" : 8578325345,
//    "dateOfBirth" : "1986-05-19",
//    "qualification" : "BE",
//    "yearOfGraduation" : "2008",
//    "password" : "jaddu@123",
//    "address" : {
//        "city" : "trichy",
//        "state" : "tamilnadu"
//    }


