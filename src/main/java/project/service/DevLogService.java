package project.service;

import project.dto.devLog.*;
import project.domain.*;
import project.dto.devLog.CreateDevLogRequest;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;


public interface DevLogService{

    public Long createDevLog(CreateDevLogRequest createDevLogRequest);
    
    public DevLogFindAllResponse findAllBySearch(int offset, int limit, String scheduleId, String accountId);
    
    public DevLogFindAllResponse findAll();
}