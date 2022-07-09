package postsite.postsitespring.domain.post;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import postsite.postsitespring.domain.post.domain.Post;
import postsite.postsitespring.domain.post.dto.ArticleDoUpdateDto;
import postsite.postsitespring.domain.post.dto.ArticleDoWriteDto;

import java.util.List;
import java.util.Objects;


@RestController
@RequestMapping("/usr/article")
public class PostController {

    private final PostService postService;

    @Autowired
    public PostController(PostService postService) {
        this.postService = postService;
    }

    @PostMapping()
    public Post articleDoWrite(
            @RequestBody ArticleDoWriteDto body
            ) {
        return postService.save(body);
    }


    @GetMapping("")
    public List<Post> articleList(
            @RequestParam() Long boardId,
            @RequestParam(defaultValue = "0") Long page,
            @RequestParam(required = false) String searchKeyword
    ) {
        return Objects.isNull(searchKeyword) ?
                postService.findAll(boardId, page) :
                postService.findAll(boardId, page, searchKeyword);
    }

    @GetMapping("/{articleId}")
    public Post articleDetail(
            @PathVariable() Long articleId
    ) {
        return postService.findById(articleId);
    }

    // put이 더 좋음.
    @PatchMapping("/{atricleId}")
    public String articleModify(
            @PathVariable Long atricleId,
            @RequestBody ArticleDoUpdateDto body
            ) {
        postService.update(atricleId, body);
        return "success";
    }

    @DeleteMapping("/{atricleId}")
    public String deleteArticle(
            @PathVariable Long atricleId
    ) {
        //postService
        postService.delete(atricleId);
        return "success";
    }

}
