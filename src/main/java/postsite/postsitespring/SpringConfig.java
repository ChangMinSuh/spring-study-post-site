package postsite.postsitespring;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import postsite.postsitespring.repository.JdbcTemplatePostRepository;
import postsite.postsitespring.repository.PostRepository;
import postsite.postsitespring.service.PostService;

import javax.sql.DataSource;

@Configuration
public class SpringConfig {
    private final DataSource dataSource;

    public SpringConfig(DataSource dataSource){
        this.dataSource = dataSource;
    }

    @Bean
    public PostService postService() {
        return new PostService(postRepository());
    }
    @Bean
    public PostRepository postRepository() {
        return new JdbcTemplatePostRepository(dataSource);
    }
}
