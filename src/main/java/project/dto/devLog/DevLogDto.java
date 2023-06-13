package project.dto.devLog;

import lombok.Getter;
import lombok.AllArgsConstructor;
import project.domain.DevLog;

@Getter
@AllArgsConstructor
public class DevLogDto{
    private Long devLog_id;
    private Long scheduleId;
    private String userName;
    private String content;
    
    public DevLogDto(DevLog devLog){
        this.devLog_id = devLog.getId();
        this.scheduleId = devLog.getSchedule().getId();
        this.userName = devLog.getUser().getName();
        this.content = devLog.getContent();
    }
    
    public static DevLogDto toDto(DevLog devLog){
        return new DevLogDto(
            devLog.getId(),
            devLog.getSchedule().getId(),
            devLog.getUser().getName(),
            devLog.getContent()
        );
    }
}

