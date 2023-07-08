package project.controller;

import project.domain.*;
import project.dto.devLog.*;
import project.exception.devLog.*;
import project.service.DevLogService;
import project.repository.DevLogRepository;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import static java.util.stream.Collectors.toList;
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

        List <DevLogDto> responseData = devLogService.findAllBySearch(offset, limit, scheduleId, accountId)
            .stream()
            .map(DevLogDto::new)
            .collect(toList());
        
        DevLogFindAllResponse response = new DevLogFindAllResponse(responseData.size(), responseData);
        
        return ResponseEntity.ok(response);
    }
    
    @PostMapping("/devLogs")
    public ResponseEntity<Long> createDevLog(@RequestBody CreateDevLogRequest createDevLogRequest){
        
        Long devLogId = devLogService.createDevLog(createDevLogRequest);
        
        return ResponseEntity.ok(devLogId);
    }
    
    @GetMapping("/devLogs/{devLogId}")
    public ResponseEntity<DevLogDto> findSingleDevLog(@PathVariable("devLogId") Long devLogId){
        DevLog devLog = devLogRepository.findById(devLogId)
            .orElseThrow(NoSuchDevLogException::new);
        
        DevLogDto response = new DevLogDto(devLog);
        
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