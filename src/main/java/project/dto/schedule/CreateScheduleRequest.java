package project.dto.schedule;

import lombok.Getter;
import lombok.NoArgsConstructor;
import java.util.List;

@Getter
@NoArgsConstructor
public class CreateScheduleRequest{
    private String workspace;
    private String name;
    private List<String> users;
}