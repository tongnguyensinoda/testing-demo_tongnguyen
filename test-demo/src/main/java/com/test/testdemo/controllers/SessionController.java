package com.test.testdemo.controllers;

import com.test.testdemo.models.Session;
import com.test.testdemo.repositories.SessionRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/sessions")
public class SessionController {
    @Autowired
    private SessionRepository sessionRepository;

    @GetMapping
    @RequestMapping("/")
    public List<Session> GetAllSession(){
        return sessionRepository.findAll();
    }

    @GetMapping
    @RequestMapping("/{id}")
    public Optional<Session> getSessionById(@PathVariable Long id){
        return sessionRepository.findById(id);
    }

    @PostMapping
    @RequestMapping("/create")
    public Session createNewSession(@RequestBody final Session session){
        return sessionRepository.saveAndFlush(session);
    }

    @DeleteMapping
    @RequestMapping(value = "/remove/{id}")
    public void deleteSession(@PathVariable Long id){
        sessionRepository.deleteById(id);
    }

    @PutMapping
    @RequestMapping(value = "/update/{id}")
    public Session updateById(@PathVariable Long id, @RequestBody Session session){
        Session existingSession = sessionRepository.getById(id);
        BeanUtils.copyProperties(session, existingSession, "s_id");
        return sessionRepository.saveAndFlush(existingSession);

    }




//    @PostMapping
//    @RequestMapping("/create")
//    public
}
