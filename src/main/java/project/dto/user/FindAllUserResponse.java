package project.dto.user;

import lombok.Getter;
import lombok.AllArgsConstructor;
import java.util.List;

@Getter
@AllArgsConstructor
public class FindAllUserResponse{
    private int count;
    private List<UserDto> data;
}