package project.dto.user;

import lombok.Getter;
import lombok.NoArgsConstructor;
import java.util.List;
import project.domain.User;

@Getter
@NoArgsConstructor
public class UpdateUserRequest{
    private String password;
    private String name;
}