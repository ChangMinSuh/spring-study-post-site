package postsite.postsitespring.domain.user;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import postsite.postsitespring.domain.user.domain.User;
import postsite.postsitespring.domain.user.dto.CreateMemberBodyDto;
import postsite.postsitespring.domain.user.dto.UpdateMemberBodyDto;

import java.util.List;

@RestController
@RequestMapping("/usr/member")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService){ this.userService = userService; }

    // Create
    @PostMapping()
    public User createMember(@RequestBody CreateMemberBodyDto body){
        return this.userService.createMember(body);
    }

    // Read
    @GetMapping()
    public List<User> allMembers(){
        return this.userService.allMembers();
    }

    @GetMapping({"/{memberId}"})
    public User oneMember(
            @PathVariable Long memberId
    ){
        return this.userService.oneMember(memberId);
    }

    // Update
    @PatchMapping({"/{memberId}"})
    public String updateMember(
            @PathVariable Long memberId,
            @RequestBody UpdateMemberBodyDto body
    ){
        this.userService.updateMember(memberId, body);
        return "success";
    }

    // Delete
    @DeleteMapping({"/{memberId}"})
    public String deleteMember(
            @PathVariable Long memberId
    ){
        this.userService.deleteMember(memberId);
        return "success";
    }


}
