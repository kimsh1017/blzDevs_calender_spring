package project.dto.user;

import lombok.Getter;
import lombok.NoArgsConstructor;
import java.util.List;
import project.domain.User;

@Getter
@NoArgsConstructor
public class CreateUserRequest{
    private String accountId;
    private String password;
    private String name;
    
    public User toUser(){
        return new User(accountId,password,name);
    }
}