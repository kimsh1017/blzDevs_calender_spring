package project.controller;

import project.domain.*;
import project.dto.devLog.*;
import project.service.DevLogService;
import project.repository.DevLogRepository;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.http.ResponseEntity;

@RestController
@RequiredArgsConstructor
public class DevLogController {
    
    private final DevLogService devLogService;
    private final DevLogRepository devLogRepository;
    
    @GetMapping("/devLogs")
    public ResponseEntity<DevLogFindAllResponse> findAllDevlogs(@RequestParam(value = "offset", defaultValue = "0") int offset,
                                  @RequestParam(value = "limit", defaultValue = "100") int limit,
                                @RequestParam(value="scheduleId", required = false) Long scheduleId,
                                @RequestParam(value="accountId", required = false) String accountId){

        DevLogFindAllResponse response = devLogService.findAllBySearch(offset, limit, scheduleId, accountId);
        return ResponseEntity.ok(response);
    }
    
    @PostMapping("/devLogs")
    public ResponseEntity<Long> createDevLog(@RequestBody CreateDevLogRequest createDevLogRequest){
        
        Long devLogId = devLogService.createDevLog(createDevLogRequest);
        
        return ResponseEntity.ok(devLogId);
    }
    
    @GetMapping("/devLogs/{devLogId}")
    public ResponseEntity<DevLogDto> findSingleDevLog(@PathVariable("devLogId") Long devLogId){
        
        DevLogDto response = new DevLogDto(devLogRepository.findOne(devLogId));
        
        return ResponseEntity.ok(response);
    }
    
    @PutMapping("/devLogs/{devLogId}")
    public ResponseEntity<DevLogDto> updateDevLog(@PathVariable("devLogId") Long devLogId,
                                                 @RequestBody UpdateDevLogRequest request){
        
        DevLog devLog = devLogService.updateDevLog(devLogId, request.getContent());
        
        DevLogDto response = new DevLogDto(devLog);
            
        return ResponseEntity.ok(response);
    }
    
    @DeleteMapping("/devLogs/{devLogId}")
    public ResponseEntity<DeleteDevLogResponse> deleteSingleDevLog(@PathVariable("devLogId") Long devLogId){
        
        devLogService.deleteDevLog(devLogId);
        
        return ResponseEntity.ok(new DeleteDevLogResponse());
    }
}