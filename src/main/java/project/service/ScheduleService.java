package project.service;

import project.domain.*;
import project.dto.schedule.*;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.time.LocalDateTime;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ScheduleService{
    
    public Long createSchedule(CreateScheduleRequest request);
    
    public Schedule findOne(Long id);
    
    public Page<Schedule> findSchedules(Pageable pageable);
    
    // public List<Schedule> searchSchedule(String accountId);
    
    public Schedule updateSchedule(Long scheduleId, UpdateScheduleRequest request);
    
    public void removeSchedule(Long id);
    
    public Schedule addUser(Long scheduleId, String accountId);
    
    public void removeUser(Long scheduleId, Long userId);
}