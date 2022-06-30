package com.survivingcodingbootcamp.blog.controller;

import com.survivingcodingbootcamp.blog.model.Hashtag;
import com.survivingcodingbootcamp.blog.model.Post;
import com.survivingcodingbootcamp.blog.repository.HashtagRepository;
import com.survivingcodingbootcamp.blog.repository.PostRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping("/posts")
public class PostController {
    private PostRepository postRepo;
    private HashtagRepository hashtagRepo;

    public PostController(PostRepository postRepo, HashtagRepository hashtagRepo) {
        this.postRepo = postRepo;
        this.hashtagRepo = hashtagRepo;
    }

    @GetMapping("/{id}")
    public String displaySinglePost(@PathVariable long id, Model model) {
        model.addAttribute("post", postRepo.findById(id).get());
        return "single-post-template";
    }

    @PostMapping("/{id}/addHashtag")
    public String addHashtagToPost(@PathVariable Long id, @RequestParam String hashtag){
        Post post = postRepo.findById(id).get();
        Optional<Hashtag> hashtagOptional = hashtagRepo.findByNameIgnoreCase(hashtag);
        if(hashtagOptional.isPresent()){
            if(!post.getHashtags().contains(hashtagOptional.get())){
                post.addHashtag(hashtagOptional.get());
            }
        }
        else {
            Hashtag hashtag1 = new Hashtag(hashtag);
            hashtagRepo.save(hashtag1);
            post.addHashtag(hashtag1);
        }
        postRepo.save(post);

        return "redirect:/posts/"+id;
    }

}
