package project.repository;

import project.domain.*;
import java.util.List;

public interface DevLogRepository{
        
    public void save(DevLog devLog);
    
    public void remove(DevLog devLog);
     
    public DevLog findOne(Long id);
        
    public List<DevLog> searchDevLogs(int offset, int limit, Schedule schedule, User user);
}