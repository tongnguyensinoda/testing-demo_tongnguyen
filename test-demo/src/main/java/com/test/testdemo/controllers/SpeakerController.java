package com.test.testdemo.controllers;


import com.fasterxml.jackson.databind.util.BeanUtil;
import com.test.testdemo.models.Speaker;
import com.test.testdemo.repositories.SpeakerRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/speakers")
public class SpeakerController {
    @Autowired
    private SpeakerRepository speakerRepository;

    @GetMapping
    @RequestMapping("/")
    public List<Speaker> GetAllSpeaker(){
        return speakerRepository.findAll();
    }

    @GetMapping
    @RequestMapping("/{id}")
    public Optional<Speaker> getSpeakerById(@PathVariable Long id){
        return speakerRepository.findById(id);
    }

    @PostMapping
    @RequestMapping("/create")
    public Speaker createSpeaker(@RequestBody final Speaker speaker){
        return speakerRepository.saveAndFlush(speaker);

    }

    @DeleteMapping
    @RequestMapping(value = "/remove/{id}")
    public void deleteSpeaker(@PathVariable Long id){
        speakerRepository.deleteById(id);
    }

    @PutMapping
    @RequestMapping(value = "/update/{id}")
    public Speaker updateSpeaker(@PathVariable Long id, @RequestBody Speaker speaker){
        Speaker existingSpeaker = speakerRepository.getById(id);
        BeanUtils.copyProperties(speaker, existingSpeaker, "speaker_id");
        return speakerRepository.saveAndFlush(existingSpeaker);
    }
}