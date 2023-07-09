package project.controller;

import project.domain.*;
import project.dto.schedule.*;
import project.service.ScheduleService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.ArrayList;
import static java.util.stream.Collectors.toList;
import org.springframework.http.ResponseEntity;
import java.time.LocalDateTime;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

@RestController
@RequiredArgsConstructor
public class ScheduleController {
    
    private final ScheduleService scheduleService;
    
    @GetMapping("/schedules")
    public ResponseEntity<FindAllScheduleResponse> findAllSchedule(Pageable pageable){
        // ,@RequestParam(value = "accountId", required = false) String accountId){
            
        Page<Schedule> page = scheduleService.findSchedules(pageable);
        
        List<ScheduleDto> responseData = page.getContent()
            .stream()
            .map(ScheduleDto::new) 
            .collect(toList());
        
        FindAllScheduleResponse response = new FindAllScheduleResponse(page.getTotalPages(), page.getNumber(), responseData);
        
        return ResponseEntity.ok(response);
    }
    
    @PostMapping("/schedules")
    public ResponseEntity<Long> registerUser(@RequestBody CreateScheduleRequest request){
        
        Long scheduleId = scheduleService.createSchedule(request);
        
        return ResponseEntity.ok(scheduleId);
    }
    
    //꼭 필요할까 이게?
    // @GetMapping("/schedules/users")
    // public ResponseEntity<FindAllScheduleResponse> searchScheduleByAccountId(
    //     @RequestParam(value = "accountId", required = false) String accountId){ 
        
    //     List<ScheduleDto> responseData = scheduleService.searchSchedule(accountId)
    //         .stream()
    //         .map(ScheduleDto::new) 
    //         .collect(toList());
        
    //     FindAllScheduleResponse response = new FindAllScheduleResponse(page.getTotalPages(), page.getNumber(), responseData);
        
    //     return ResponseEntity.ok(response);
    // }
    
    @GetMapping("/schedules/{scheduleId}") 
    public ResponseEntity<FindSingleScheduleResponse> findSingleSchedule(@PathVariable Long scheduleId){
            
        Schedule schedule = scheduleService.findOne(scheduleId);
        
        FindSingleScheduleResponse response = new FindSingleScheduleResponse(schedule);
        
        return ResponseEntity.ok(response);
    }
    
    @PutMapping("/schedules/{scheduleId}") 
    public ResponseEntity<ScheduleDto> updateSchedule(@PathVariable Long scheduleId,
                                                    @RequestBody UpdateScheduleRequest request){
            
        Schedule schedule = scheduleService.updateSchedule(scheduleId, request);
        
        ScheduleDto response = new ScheduleDto(schedule);
        
        return ResponseEntity.ok(response);
    }
    
    @DeleteMapping("/schedules/{scheduleId}")
    public ResponseEntity<DeleteScheduleResponse> deleteSchedule(@PathVariable Long scheduleId){
            
        scheduleService.removeSchedule(scheduleId);
        
        return ResponseEntity.ok(new DeleteScheduleResponse());
    }
    
    @GetMapping("/schedules/{scheduleId}/users")
    public ResponseEntity<FindScheduleUsersResponse> findScheduleUsers(@PathVariable Long scheduleId){
            
        Schedule schedule = scheduleService.findOne(scheduleId);
        
        FindScheduleUsersResponse response = new FindScheduleUsersResponse(schedule);
        
        return ResponseEntity.ok(response);
    }
    
    @PostMapping("/schedules/{scheduleId}/users")
    public ResponseEntity<Long> addScheduleUsers(
                                                        @PathVariable Long scheduleId,
                                                        @RequestBody AddUserReqeust request){
            
        Schedule schedule = scheduleService.addUser(scheduleId,request.getAccountId());
        
        Long responseId = schedule.getId();
        
        return ResponseEntity.ok(responseId);
    }
    
    @DeleteMapping("/schedules/{scheduleId}/users/{userId}")
    public ResponseEntity<DeleteScheduleUserResponse> addScheduleUsers(
                                                        @PathVariable Long scheduleId,
                                                        @PathVariable Long userId){
            
        scheduleService.removeUser(scheduleId, userId);
        
        DeleteScheduleUserResponse response = new DeleteScheduleUserResponse();
        
        return ResponseEntity.ok(response);
    }
    
    @Getter
    static class AddUserReqeust{
        private String accountId;
    }
}