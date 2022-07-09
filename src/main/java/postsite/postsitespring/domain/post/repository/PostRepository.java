package postsite.postsitespring.domain.post.repository;

import postsite.postsitespring.domain.post.dto.ArticleDoUpdateDto;
import postsite.postsitespring.domain.post.domain.Post;
import postsite.postsitespring.domain.post.dto.ArticleDoWriteDto;

import java.util.List;

public interface PostRepository {
    Post save(ArticleDoWriteDto body);
    Post findById(Long id);
    List<Post> findAll(Long boardId,Long page);
    List<Post> findAll(Long boardId,Long page, String searchKeyword);
    void update(Long id, ArticleDoUpdateDto body);
    void delete(Long id);
}

