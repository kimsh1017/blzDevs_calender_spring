package project.dto.devLog;

import lombok.Getter;
import lombok.AllArgsConstructor;

@Getter
@AllArgsConstructor
public class CreateDevLogRequest{
    private Long scheduleId;
    private String userAccountId;
    private String content;
}