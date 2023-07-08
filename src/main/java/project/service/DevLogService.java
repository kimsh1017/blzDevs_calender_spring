package project.service;

import project.dto.devLog.*;
import project.domain.*;
import project.dto.devLog.CreateDevLogRequest;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface DevLogService{

    public Long createDevLog(CreateDevLogRequest createDevLogRequest);
    
    public Page<DevLog> findAllBySearch(Pageable pageable, Long scheduleId, String accountId);

    public DevLog updateDevLog(Long devLogId, String content);
    
    public void deleteDevLog(Long devLogId);
}