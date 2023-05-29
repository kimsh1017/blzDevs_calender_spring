package project.devLog;

import project.domain.*;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;


public interface DevLogService{

    public Long createDevLog(Long scheduleId, String userAccountId, String content);
    
    public List<DevLog> findAll();
}