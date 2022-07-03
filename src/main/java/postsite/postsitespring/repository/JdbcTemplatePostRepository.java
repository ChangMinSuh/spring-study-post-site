package postsite.postsitespring.repository;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import postsite.postsitespring.domain.Post;
import postsite.postsitespring.dto.ArticleDoUpdateDto;
import postsite.postsitespring.dto.ArticleDoWriteDto;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class JdbcTemplatePostRepository implements PostRepository{

    private final JdbcTemplate jdbcTemplate;

    public JdbcTemplatePostRepository(DataSource dataSource){
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public Post save(ArticleDoWriteDto body) {
        ObjectMapper objectMapper = new ObjectMapper();
        SimpleJdbcInsert jdbcInsert = new SimpleJdbcInsert(jdbcTemplate);
        jdbcInsert.withTableName("post").usingGeneratedKeyColumns("id");
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("title", body.getTitle());
        parameters.put("content", body.getBody());

        Number key = jdbcInsert.executeAndReturnKey(new MapSqlParameterSource(parameters));
        // map => Post
        Post post = objectMapper.convertValue(parameters,Post.class);
        post.setId(key.longValue());
        return post;
    }

    @Override
    public Optional<Post> findById(Long id) {
        final String sql = "select * from post where id = ?";
        List<Post> result = jdbcTemplate.query(sql, postRowMapper(), id);
        return result.stream().findAny();
    }


    @Override
    public List<Post> findAll(Long boardId, Long page) {
        final String sql = "SELECT * FROM post ORDER BY id DESC LIMIT 10 OFFSET " + page ;
        return jdbcTemplate.query(sql, postRowMapper());
    }
    public List<Post> findAll(Long boardId, Long page, String searchKeyword) {
        final String sql = "SELECT * FROM post WHERE title LIKE \'%" + searchKeyword + "%\' ORDER BY id DESC LIMIT 10 OFFSET " + page  ;
        return jdbcTemplate.query(sql, postRowMapper());
    }

    @Override
    public void update(ArticleDoUpdateDto body) {
        final String sql = "UPDATE post SET title=\'" + body.getTitle() + "\', content=\'" + body.getBody() + "\' WHERE id=" + body.getId();
        jdbcTemplate.update(sql);
    }

    @Override
    public void delete(Long id) {
        final String sql = "DELETE FROM post WHERE id =" + id;
        jdbcTemplate.update(sql);
    }

    private RowMapper<Post> postRowMapper(){
        return (rs, rowNum) -> {
            Post post = new Post();
            post.setId(rs.getLong("id"));
            post.setTitle(rs.getString("title"));
            post.setContent(rs.getString("content"));
            return post;
        };
    }
}
