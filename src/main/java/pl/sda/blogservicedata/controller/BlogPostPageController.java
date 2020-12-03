package pl.sda.blogservicedata.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class BlogPostPageController {

    @GetMapping("/blogPosts")
    public String blogPostsPage() {
        return "blogPosts";
    }
}
