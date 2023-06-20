package project.service;

import project.dto.devLog.*;
import project.domain.*;
import project.dto.devLog.CreateDevLogRequest;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;


public interface DevLogService{

    public Long createDevLog(CreateDevLogRequest createDevLogRequest);
    
    public List<DevLog> findAllBySearch(int offset, int limit, Long scheduleId, String accountId);

    public DevLog updateDevLog(Long devLogId, String content);
    
    public void deleteDevLog(Long devLogId);
}