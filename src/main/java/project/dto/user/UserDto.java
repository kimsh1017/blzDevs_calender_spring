package project.dto.user;

import lombok.Getter;
import lombok.AllArgsConstructor;
import project.domain.User;

@Getter
@AllArgsConstructor
public class UserDto {
    private Long id;
    private String accountId;
    private String name;
    
    public UserDto(User user){
        this.id = user.getId();
        this.accountId = user.getAccountId();
        this.name = user.getName();
    }
}