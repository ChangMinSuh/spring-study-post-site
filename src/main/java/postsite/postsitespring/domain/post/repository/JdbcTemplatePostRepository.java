package postsite.postsitespring.domain.post.repository;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import postsite.postsitespring.domain.post.dto.ArticleDoUpdateDto;
import postsite.postsitespring.domain.post.domain.Post;
import postsite.postsitespring.domain.post.dto.ArticleDoWriteDto;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JdbcTemplatePostRepository implements PostRepository{

    private final JdbcTemplate jdbcTemplate;

    public JdbcTemplatePostRepository(DataSource dataSource){
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public Post save(ArticleDoWriteDto body) {
        SimpleJdbcInsert jdbcInsert = new SimpleJdbcInsert(jdbcTemplate);
        jdbcInsert.withTableName("post").usingGeneratedKeyColumns("id");
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("title", body.getTitle());
        parameters.put("content", body.getContent());

        Long id = jdbcInsert.executeAndReturnKey(new MapSqlParameterSource(parameters)).longValue();

        return new Post(id, body.getTitle(),body.getContent());
    }

    @Override
    public Post findById(Long id) {
        final String sql = "select * from post where id = ?";
        Post result = jdbcTemplate.queryForObject(sql, postRowMapper(), id);
        return result;
    }


    @Override
    public List<Post> findAll(Long boardId, Long page) {
        final String sql = "SELECT * FROM post ORDER BY id DESC LIMIT ? OFFSET ?" ;
        return jdbcTemplate.query(sql, postRowMapper(),10,page);
    }

    @Override
    public List<Post> findAll(Long boardId, Long page, String searchKeyword) {
        final String sql = "SELECT * FROM post WHERE title LIKE ? ORDER BY id DESC LIMIT ? OFFSET ?";
        return jdbcTemplate.query(sql, postRowMapper(),'%' + searchKeyword + '%', 10, page);
    }

    @Override
    public void update(Long id, ArticleDoUpdateDto body) {
        final String sql = "UPDATE post SET title=?, content=? WHERE id=?";
        jdbcTemplate.update(sql, body.getTitle(), body.getContent(), id);
    }

    @Override
    public void delete(Long id) {
        final String sql = "DELETE FROM post WHERE id =?";
        jdbcTemplate.update(sql, id);
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
