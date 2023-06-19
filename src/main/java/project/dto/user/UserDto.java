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
    
    public static UserDto toDto(User user){
        return new UserDto(
            user.getId(),
            user.getAccountId(),
            user.getName()
        );
    }
}