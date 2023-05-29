package project.controller;

import project.domain.*;
import project.devLog.DevLogService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class DevLogController {
    
    private final DevLogService devLogService;
    
    @GetMapping("/devLogs")
    public Response findAllDevlogs(){
        List<DevLog> findDevLogs = devLogService.findAll();
        
        List<DevLogDto> collect = findDevLogs.stream()
            .map(devLog -> new DevLogDto(devLog.getId(), devLog.getSchedule().getId(), devLog.getUser().getName(),devLog.getContent()))
            .collect(Collectors.toList());
        
        return new Response(0,"",collect);
    }
    
    @PostMapping("/devLogs")
    public Response createDevLog(@RequestBody CreateDevLogRequest request){
        Long devLogId = devLogService.createDevLog(request.getScheduleId(), request.getUserAccountId(), request.getContent());
        return new Response(0,"",devLogId);
    }
    
    @Getter
    @AllArgsConstructor
    static class CreateDevLogRequest{
        Long scheduleId;
        String userAccountId;
        String content;
    }
    
    @Getter
    @AllArgsConstructor
    static class DevLogDto{
        Long devLog_id;
        Long scheduleId;
        String userName;
        String content;
    }
}