package project.controller;

import project.domain.*;
import project.dto.user.*;
import project.service.UserService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import org.springframework.http.ResponseEntity;
import static java.util.stream.Collectors.toList;

@RestController
@RequiredArgsConstructor
public class UserController{
    
    private final UserService userService;
    
    //근데 accountId로 검색이 굳이 필요할까..?
    @GetMapping("/users")
    public ResponseEntity<FindAllUserResponse> findAllUserName(
                                @RequestParam(value = "offset", defaultValue = "0") int offset,
                                 @RequestParam(value = "limit", defaultValue = "100") int limit,
                                @RequestParam(value="accountId", required = false) String accountId,
                                @RequestParam(value="name", required = false) String name){
        
        List<UserDto> responseData = userService.findAllBySearch(offset, limit, accountId, name)
            .stream()
            .map(UserDto::toDto)
            .collect(toList());
        
        FindAllUserResponse response = new FindAllUserResponse(responseData.size(), responseData);
        
        return ResponseEntity.ok(response);
    }
    
    @PostMapping("/users")
    public ResponseEntity<Long> registerUser(@RequestBody CreateUserRequest request){
        
        User user = request.toUser();
        
        Long userId = userService.register(user);
        
        return ResponseEntity.ok(userId);
    }
    
    @GetMapping("/users/{userId}")
    public ResponseEntity<FindSingleUserResponse> findUserDetail(@PathVariable("userId") Long userId){
        
        User findUser = userService.findOne(userId);
        
        FindSingleUserResponse response = new FindSingleUserResponse(findUser);
        
        return ResponseEntity.ok(response);
    }
    
    @PutMapping("/users/{userId}")
    public ResponseEntity<FindSingleUserResponse> updateUser(@PathVariable("userId") Long userId,
                                                            @RequestBody UpdateUserRequest request){
        
        User updateUser = userService.updateUser(userId, request.getPassword(), request.getName());
        
        FindSingleUserResponse response = new FindSingleUserResponse(updateUser);
        
        return ResponseEntity.ok(response);
    }
    
    @DeleteMapping("/users/{userId}")
    public ResponseEntity<DeleteUserResponse> deleteUser(@PathVariable("userId") Long userId){
        userService.deleteUser(userId);
        
        return ResponseEntity.ok(new DeleteUserResponse());
    }
}