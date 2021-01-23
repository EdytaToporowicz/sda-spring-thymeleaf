package pl.sda.blogservicedata.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import pl.sda.blogservicedata.model.BlogPost;
import pl.sda.blogservicedata.model.Topic;
import pl.sda.blogservicedata.service.BlogPostService;
import pl.sda.blogservicedata.service.TopicService;

import java.util.ArrayList;
import java.util.List;

@Controller
public class BlogPostPageController {

    private final BlogPostService blogPostService;
    private final TopicService topicService;

    public BlogPostPageController(BlogPostService blogPostService, TopicService topicService) {
        this.blogPostService = blogPostService;
        this.topicService = topicService;
    }

    @GetMapping("/blogPosts")
    public String blogPostsPage(Model model, @RequestParam(value = "topic", required = false) String topic) {
        List<BlogPost> blogPostList = new ArrayList<>();
        if (topic != null) {
            blogPostList.addAll(blogPostService.findByFilter(topic, null, null, null));
        } else {
            blogPostList.addAll(blogPostService.findAll());
        }
        List<Topic> topics = topicService.findAll();
        model.addAttribute("blogPosts", blogPostList);
        model.addAttribute("topics", topics);
        return "blogPosts";
    }

    @GetMapping("/blogPosts/{blogPostId}")
    public String blogPostsDetails(Model model, @PathVariable Long blogPostId) {
        final BlogPost blogPost = blogPostService.findById(blogPostId);
        model.addAttribute("blogPost", blogPost);
        return "blogPostDetails";
    }
}
