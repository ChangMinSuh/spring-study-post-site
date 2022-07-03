package postsite.postsitespring.repository;

import postsite.postsitespring.domain.Post;
import postsite.postsitespring.dto.ArticleDoUpdateDto;
import postsite.postsitespring.dto.ArticleDoWriteDto;

import java.util.List;
import java.util.Optional;

public interface PostRepository {
    Post save(ArticleDoWriteDto body);
    Post findById(Long id);
    List<Post> findAll(Long boardId,Long page);
    List<Post> findAll(Long boardId,Long page, String searchKeyword);
    void update(ArticleDoUpdateDto body);
    void delete(Long id);
}

