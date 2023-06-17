package project.repository;

import project.domain.*;
import java.util.List;

public interface DevLogRepository{
    
    public void save(DevLog devLog);
    
    public List<DevLog> findAll();
    
    public List<DevLog> searchDevLogs(int offset, int limit, Long scheduleId, String accountId);
}