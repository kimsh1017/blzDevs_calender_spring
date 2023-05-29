package project.controller;

import project.domain.*;
import project.schedule.ScheduleService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.stream.Collectors;
import java.time.LocalDateTime;

@RestController
@RequiredArgsConstructor
public class ScheduleController {
    
    private final ScheduleService scheduleService;
    
    @GetMapping("/schedules")
    public Response findAllSchedule(){
        List<Schedule> findSchedules = scheduleService.findAll();
            
        List<ScheduleDto> collect = findSchedules.stream()
            .map(schedule -> new ScheduleDto(schedule.getWorkspace(), schedule.getName(), schedule.getUserSchedules()))
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
        private String workspace;
        private String name;
        private List<String> users;
        
        public ScheduleDto(Workspace workspace, String name, List<UserSchedule> userSchedules){
            this.workspace = workspace.getName();
            this.name = name;
            this.users = userSchedules.stream()
                .map(userSchedule -> userSchedule.getUser().getName())
                .collect(Collectors.toList());
        }
    }
}