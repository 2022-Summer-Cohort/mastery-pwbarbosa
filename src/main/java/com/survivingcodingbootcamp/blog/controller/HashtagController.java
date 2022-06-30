package com.survivingcodingbootcamp.blog.controller;

import com.survivingcodingbootcamp.blog.model.Hashtag;
import com.survivingcodingbootcamp.blog.repository.HashtagRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

@Controller
@RequestMapping("/hashtags")
public class HashtagController {
    private HashtagRepository hashtagRepo;

    public HashtagController(HashtagRepository hashtagRepo) {
        this.hashtagRepo = hashtagRepo;
    }

    @RequestMapping("/")
    public String displayAllHashtags(Model model) {
        model.addAttribute("hashtags", hashtagRepo.findAll());
        return "all-hashtags-template";
    }

    @RequestMapping("/{id}")
    public String displayHashtagById(Model model, @PathVariable long id) {
        model.addAttribute("hashtag", hashtagRepo.findById(id).get());
        return "single-hashtag-template";
    }

    @RequestMapping("/name/{name}")
    public String displayHashtagByNameIgnoreCase(Model model, @PathVariable String name) {
        model.addAttribute("hashtag", hashtagRepo.findByNameIgnoreCase(name).get());
        return "single-hashtag-template";
    }
}
