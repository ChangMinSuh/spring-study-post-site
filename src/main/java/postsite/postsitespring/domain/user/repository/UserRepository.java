package postsite.postsitespring.domain.user.repository;

import postsite.postsitespring.domain.user.domain.User;
import postsite.postsitespring.domain.user.dto.CreateMemberBodyDto;
import postsite.postsitespring.domain.user.dto.UpdateMemberBodyDto;

import java.util.List;

public interface UserRepository {

    User save(CreateMemberBodyDto body);
    User findById(Long id);
    List<User> findAll();
    void update(Long id, UpdateMemberBodyDto body);
    void delete(Long id);
}
