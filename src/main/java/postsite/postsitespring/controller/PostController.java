package postsite.postsitespring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import postsite.postsitespring.domain.Post;
import postsite.postsitespring.dto.ArticleDoUpdateDto;
import postsite.postsitespring.dto.ArticleDoWriteDto;
import postsite.postsitespring.service.PostService;

import java.util.List;
import java.util.Objects;
import java.util.Optional;


@RestController
@RequestMapping("/usr/article")
public class PostController {

    private final PostService postService;

    @Autowired
    public PostController(PostService postService) {
        this.postService = postService;
    }

    @PostMapping("/doWrite")
    public Post articleDoWrite(
            @RequestBody ArticleDoWriteDto body
            ) {
        return postService.save(body);
    }


    @GetMapping("/list")
    public List<Post> articleList(
            @RequestParam() Long boardId,
            @RequestParam(defaultValue = "0") Long page,
            @RequestParam(required = false) String searchKeyword
    ) {
        return Objects.isNull(searchKeyword) ?
                postService.findAll(boardId, page) :
                postService.findAll(boardId, page, searchKeyword);
    }

    @GetMapping("/detail")
    public Optional<Post> articleDetail(
            @RequestParam() Long id
    ) {
        return postService.findById(id);
    }

    // put이 더 좋음.
    @PostMapping("/doModify")
    public String articleModify(
            @RequestBody ArticleDoUpdateDto body
            ) {
        postService.update(body);
        return "success";
    }

    @DeleteMapping("/{postId}")
    public String deleteArticle(
            @PathVariable Long postId
    ) {
        //postService
        postService.delete(postId);
        return "success";
    }

}
