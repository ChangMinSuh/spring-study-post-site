package postsite.postsitespring.domain.post.dto;

public class ArticleDoUpdateDto {
    private String id;
    private String title;
    private String body;

    public String getId(){
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getBody() {
        return body;
    }
}
