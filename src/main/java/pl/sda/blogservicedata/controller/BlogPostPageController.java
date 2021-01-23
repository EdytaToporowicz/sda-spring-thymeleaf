package pl.sda.blogservicedata.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import pl.sda.blogservicedata.model.BlogPost;
import pl.sda.blogservicedata.model.Topic;
import pl.sda.blogservicedata.model.request.BlogPostForm;
import pl.sda.blogservicedata.service.BlogPostService;
import pl.sda.blogservicedata.service.TopicService;

import javax.validation.Valid;
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

    @GetMapping("/blogPostForm")
    public String blogPostForm(Model model) {
        model.addAttribute("blogPostForm", new BlogPostForm());
        List<Topic> topics = topicService.findAll();
        model.addAttribute("topics", topics);
        return "blogPostForm";
    }

    @PostMapping("/blogPostForm")
    public String createBlogPost(@ModelAttribute("blogPostForm") @Valid BlogPostForm blogPostForm, Errors errors,
                                 Model model) {
        if (errors.hasErrors()) {
            List<Topic> topics = topicService.findAll();
            model.addAttribute("topics", topics);
            return "blogPostForm";
        }
        blogPostService.saveForm(blogPostForm);
        return "redirect:blogPosts";
    }
}
