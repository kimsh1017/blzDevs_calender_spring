package project.dto.schedule;

import lombok.Getter;
import lombok.AllArgsConstructor;
import java.util.List;

@Getter
@AllArgsConstructor
public class FindAllScheduleResponse{
    private int size;
    private List<ScheduleDto> data;
}