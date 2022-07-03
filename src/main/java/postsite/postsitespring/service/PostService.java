package postsite.postsitespring.service;

import postsite.postsitespring.domain.Post;
import postsite.postsitespring.dto.ArticleDoUpdateDto;
import postsite.postsitespring.dto.ArticleDoWriteDto;
import postsite.postsitespring.repository.PostRepository;

import java.util.List;
import java.util.Optional;

public class PostService {
    private final PostRepository postRepository;

    public PostService(PostRepository postRepository){
        this.postRepository = postRepository;
    }

    // post 생성
    public Post save(ArticleDoWriteDto body){
        return postRepository.save(body);
    }
    // 전체 post 조회
    public List<Post> findAll(Long boardId, Long page){
        return postRepository.findAll(boardId, page);
    }
    public List<Post> findAll(Long boardId, Long page, String searchKeyword){
        return postRepository.findAll(boardId, page, searchKeyword);
    }

    // 해당 post 조회
    public Post findById(Long id){
        return postRepository.findById(id);
    }

    // post update
    public void update(ArticleDoUpdateDto body){
        postRepository.update(body);
    }

    //post delete
    public void delete(Long postId){
        postRepository.delete(postId);
    }
}
