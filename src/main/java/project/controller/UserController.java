package project.controller;

import project.domain.*;
import project.user.UserService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class UserController{
    
    private final UserService userService;
    
    @GetMapping("/users")
    public Response findAllUserName(){
        List<User> findUsers = userService.findAll();
        
        List<UserDto> collect = findUsers.stream()
            .map(u -> new UserDto(u.getAccountId(), u.getName()))
            .collect(Collectors.toList());
        
        return new Response(0,"",collect);
    }
    
    @PostMapping("/users")
    public Response registerUser(@RequestBody User user){
        Long userId = userService.register(user);
        return new Response(0,"",userId);
    }
    
    @GetMapping("/users/{accountId}")
    public Response registerUser(@PathVariable("accountId") String accountId){
        
        User user = userService.findByAccountId(accountId);
        return new Response(0,"",new UserDetailDto(user));
    }
    
    
    @Getter
    @AllArgsConstructor
    static class UserDto {
        private String accountId;
        private String name;
    }
    
    @Getter
    @AllArgsConstructor
    static class UserDetailDto {
        private String accountId;
        private String name;
        private List<String> workspaces;
        // private List<Long> schedules;
        // private List<Long> devLogs;
        
        public UserDetailDto(User user){
            accountId = user.getAccountId();
            name = user.getName();
            
            workspaces = user.getUserWorkspaces().stream()
                .map(userWorkspace -> userWorkspace.getWorkspace().getName())
                .collect(Collectors.toList());
        }
    }
    
}