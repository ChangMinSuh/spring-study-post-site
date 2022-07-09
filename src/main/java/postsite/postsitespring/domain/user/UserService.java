package postsite.postsitespring.domain.user;

import postsite.postsitespring.domain.user.domain.User;
import postsite.postsitespring.domain.user.dto.CreateMemberBodyDto;
import postsite.postsitespring.domain.user.dto.UpdateMemberBodyDto;
import postsite.postsitespring.domain.user.repository.UserRepository;

import java.util.List;

public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) { this.userRepository = userRepository; }

    public User createMember(CreateMemberBodyDto body){
        return userRepository.save(body);
    }

    public List<User> allMembers(){
        return userRepository.findAll();
    }

    public User oneMember(Long id){
        return userRepository.findById(id);
    }

    public void updateMember(Long id, UpdateMemberBodyDto body){
        userRepository.update(id, body);
    }

    public void deleteMember(Long id){
        userRepository.delete(id);
    }
}
