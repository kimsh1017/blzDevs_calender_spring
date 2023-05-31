package project.schedule;

import project.domain.*;
import java.util.List;

public interface ScheduleRepository{
    
    public void save(Schedule schedule);
    
    public Schedule findOne(Long id);
    
    public List<Schedule> findAll(int offset, int limit);
    
    public List<Schedule> findByWorkspaceName(int offset, int limit, String workspaceName);
}