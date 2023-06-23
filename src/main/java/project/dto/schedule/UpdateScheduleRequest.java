package project.dto.schedule;

import lombok.Getter;
import lombok.NoArgsConstructor;
import java.util.List;

@Getter
@NoArgsConstructor
public class UpdateScheduleRequest{
    private String name;
    private List<String> users;
}