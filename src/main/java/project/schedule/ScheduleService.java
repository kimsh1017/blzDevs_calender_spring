package project.schedule;

import project.domain.*;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.time.LocalDateTime;


public interface ScheduleService{

    public Long createSchedule(String Workspace, String name, LocalDateTime startDate, LocalDateTime endDate, List<String> users);
    
    public List<Schedule> findAll();
}