package project.devLog;

import project.domain.*;
import java.util.List;

public interface DevLogRepository{
    
    public void save(DevLog devLog);
    
    public List<DevLog> findAll();

}