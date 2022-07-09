package postsite.postsitespring;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import postsite.postsitespring.domain.post.PostService;
import postsite.postsitespring.domain.post.repository.JdbcTemplatePostRepository;
import postsite.postsitespring.domain.post.repository.PostRepository;
import postsite.postsitespring.domain.user.UserService;
import postsite.postsitespring.domain.user.repository.JdbcTemplateUserRepository;
import postsite.postsitespring.domain.user.repository.UserRepository;

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

    @Bean
    public UserService userService(){
        return new UserService((userRepository()));
    }

    public UserRepository userRepository(){
        return new JdbcTemplateUserRepository(dataSource);
    }
}
