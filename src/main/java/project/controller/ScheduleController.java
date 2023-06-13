package project.controller;

import project.domain.*;
import project.service.ScheduleService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.ArrayList;
import java.util.stream.Collectors;
import java.time.LocalDateTime;

@RestController
@RequiredArgsConstructor
public class ScheduleController {
    
    private final ScheduleService scheduleService;
    
    @GetMapping("/schedules")
    public Response findAllSchedule(@RequestParam(value = "offset", defaultValue = "0") int offset,
                                  @RequestParam(value = "limit", defaultValue = "100") int limit,
                                 @RequestParam(value = "workspace", required = false) String workspaceName ){
        
        List<Schedule> findSchedules = scheduleService.findSchedules(offset, limit, workspaceName);
        
        // if (workspaceName == null){
        //     findSchedules = scheduleService.findAll(offset, limit);
        // }
        // else{
        //     findSchedules = scheduleService.findByWorkspaceName(offset, limit, workspaceName);
        // }
            
        List<ScheduleDto> collect = findSchedules.stream()
            .map(schedule -> new ScheduleDto(schedule.getId(), schedule.getWorkspace(), schedule.getName(), schedule.getUserSchedules()))
            .collect(Collectors.toList());
        
        return new Response(0,"",collect);
    }
    
    @PostMapping("/schedules")
    public Response registerUser(@RequestBody CreateSchedulesRequest request){
        Long scheduleId = scheduleService.createSchedule(
            request.getWorkspace(),
            request.getName(),
            request.getStartDate(),
            request.getEndDate(),
            request.getUsers()
        );
        return new Response(0,"",scheduleId);
    }
    
    @Getter
    @AllArgsConstructor
    static class CreateSchedulesRequest{
        private String workspace;
        private String name;
        private LocalDateTime startDate;
        private LocalDateTime endDate;
        private List<String> users;
    }
    
    @Getter
    @AllArgsConstructor
    static class ScheduleDto{
        private Long schedule_id;
        private String workspace;
        private String name;
        private List<String> users = new ArrayList <> ();
        
        public ScheduleDto(Long id, Workspace workspace, String name, List<UserSchedule> userSchedules){
            this.schedule_id = id;
            this.workspace = workspace.getName();
            this.name = name;
            // this.users = userSchedules.stream()
            //     .map(userSchedule -> userSchedule.getUser().getName())
            //     .collect(Collectors.toList());
            
            for (UserSchedule userSchedule : userSchedules){
                users.add(userSchedule.getUser().getAccountId());
            }
        }
    }
}