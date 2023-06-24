package project.service;

import project.domain.*;
import project.dto.schedule.*;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.time.LocalDateTime;


public interface ScheduleService{
    
    public Long createSchedule(CreateScheduleRequest request);
    
    public Schedule findOne(Long id);
    
    public List<Schedule> findSchedules(int offset, int limit, String accountId);
    
    public Schedule updateSchedule(Long scheduleId, UpdateScheduleRequest request);
    
    public void removeSchedule(Long id);
    
    public Schedule addUser(Long scheduleId, String accountId);
    
    public void removeUser(Long scheduleId, Long userId);
}