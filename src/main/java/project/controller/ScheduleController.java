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

@RestController
@RequiredArgsConstructor
public class ScheduleController {
    
    private final ScheduleService scheduleService;
    
    @GetMapping("/schedules")
    public ResponseEntity<FindAllScheduleResponse> findAllSchedule(
        @RequestParam(value = "offset", defaultValue = "0") int offset,
        @RequestParam(value = "limit", defaultValue = "100") int limit,
        @RequestParam(value = "accountId", required = false) String accountId){
            
        List<ScheduleDto> responseData = scheduleService.findSchedules(offset, limit, accountId).stream()
            .map(ScheduleDto::new) 
            .collect(toList());
        
        FindAllScheduleResponse response = new FindAllScheduleResponse(responseData.size(),responseData);
        
        return ResponseEntity.ok(response);
    }
    
    @PostMapping("/schedules")
    public ResponseEntity<Long> registerUser(@RequestBody CreateScheduleRequest request){
        
        Long scheduleId = scheduleService.createSchedule(request);
        
        return ResponseEntity.ok(scheduleId);
    }
    
    @GetMapping("/schedules/{scheduleId}") // join 쿼리 안나감, 수정할까 말까
    public ResponseEntity<FindSingleScheduleResponse> findSingleSchedule(@PathVariable Long scheduleId){
            
        Schedule schedule = scheduleService.findOne(scheduleId);
        
        FindSingleScheduleResponse response = new FindSingleScheduleResponse(schedule);
        
        return ResponseEntity.ok(response);
    }
    
    @PutMapping("/schedules/{scheduleId}") // join 쿼리 안나감, 수정할까 말까
    public ResponseEntity<ScheduleDto> updateSchedule(@PathVariable Long scheduleId,
                                                    @RequestBody UpdateScheduleRequest request){
            
        Schedule schedule = scheduleService.updateSchedule(scheduleId, request);
        
        ScheduleDto response = new ScheduleDto(schedule);
        
        return ResponseEntity.ok(response);
    }
    
    @DeleteMapping("/schedules/{scheduleId}") // join 쿼리 안나감, 수정할까 말까
    public ResponseEntity<DeleteScheduleResponse> deleteSchedule(@PathVariable Long scheduleId){
            
        scheduleService.removeSchedule(scheduleId);
        
        return ResponseEntity.ok(new DeleteScheduleResponse());
    }
    
    @GetMapping("/schedules/{scheduleId}/users") // join 쿼리 안나감, 수정할까 말까
    public ResponseEntity<FindScheduleUsersResponse> findScheduleUsers(@PathVariable Long scheduleId){
            
        Schedule schedule = scheduleService.findOne(scheduleId);
        
        FindScheduleUsersResponse response = new FindScheduleUsersResponse(schedule);
        
        return ResponseEntity.ok(response);
    }
    
    @PostMapping("/schedules/{scheduleId}/users") // join 쿼리 안나감, 수정할까 말까
    public ResponseEntity<Long> addScheduleUsers(
                                                        @PathVariable Long scheduleId,
                                                        @RequestBody addUserReqeust request){
            
        Schedule schedule = scheduleService.addUser(scheduleId,request.getAccountId());
        
        Long responseId = schedule.getId();
        
        return ResponseEntity.ok(responseId);
    }
    
    @DeleteMapping("/schedules/{scheduleId}/users/{userId}") // join 쿼리 안나감, 수정할까 말까
    public ResponseEntity<DeleteScheduleUserResponse> addScheduleUsers(
                                                        @PathVariable Long scheduleId,
                                                        @PathVariable Long userId){
            
        Schedule schedule = scheduleService.removeUser(scheduleId, userId);
        
        DeleteScheduleUserResponse response = new DeleteScheduleUserResponse();
        
        return ResponseEntity.ok(response);
    }
    
    @Getter
    static class addUserReqeust{
        private String accountId;
    }
}