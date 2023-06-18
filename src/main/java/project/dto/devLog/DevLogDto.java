package project.dto.devLog;

import lombok.Getter;
import lombok.AllArgsConstructor;
import project.domain.DevLog;

@Getter
@AllArgsConstructor
public class DevLogDto{
    private Long devLog_id;
    private Long scheduleId;
    private String userAccountId;
    private String content;
    
    // 로직이 겹치서 제거
    // public DevLogDto(DevLog devLog){
    //     this.devLog_id = devLog.getId();
    //     this.scheduleId = devLog.getSchedule().getId();
    //     this.userAccountId = devLog.getUser().getAccountId();
    //     this.content = devLog.getContent();
    // }
    
    public static DevLogDto toDto(DevLog devLog){
        return new DevLogDto(
            devLog.getId(),
            devLog.getSchedule().getId(),
            devLog.getUser().getAccountId(),
            devLog.getContent()
        );
    }
}

