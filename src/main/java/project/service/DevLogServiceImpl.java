package project.service;

import project.domain.*;
import project.dto.devLog.*;
import project.exception.user.*;
import project.exception.schedule.*;
import project.exception.devLog.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.ArrayList;
import java.util.Optional;
import project.repository.DevLogRepository;
import project.repository.UserRepository;
import project.repository.ScheduleRepository;
import project.dto.devLog.CreateDevLogRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


@Service
@RequiredArgsConstructor
public class DevLogServiceImpl implements DevLogService{

    private final ScheduleRepository scheduleRepository;
    private final UserRepository userRepository;
    private final DevLogRepository devLogRepository;
    
    @Transactional
    public Long createDevLog(CreateDevLogRequest createDevLogRequest){
        
        Schedule schedule = scheduleRepository.findOneOptional(createDevLogRequest.getScheduleId())
            .orElseThrow(NoSuchScheduleException::new);
        
        User user = userRepository.findByAccountId(createDevLogRequest.getUserAccountId())
            .orElseThrow(NoSuchUserException::new);
        
        String content = createDevLogRequest.getContent();
        
        validateDevLog(schedule, user);
        
        DevLog devLog = new DevLog(schedule,user,content);
        devLogRepository.save(devLog);
        
        return devLog.getId(); // id만 반환하는거 맞을까?
    }
    
    @Override
    public Page<DevLog> findAllBySearch(Pageable pageable, Long scheduleId, String accountId){
        
        Schedule schedule = validateScheduleId(scheduleId); //이부분 없으면 오류 띄우는거 맞나? 그냥 [] 보내주면 되는거 아닐까
        User user = validateAccountId(accountId);
            
        return devLogRepository.searchDevLogs(pageable, schedule, user);
    }
    
    @Override
    @Transactional
    public DevLog updateDevLog(Long devLogId, String content){
        DevLog findDevLog = devLogRepository.findById(devLogId)
            .orElseThrow(NoSuchDevLogException::new);
        findDevLog.updateContent(content);
        
        return findDevLog;
    }
    
    @Override
    @Transactional
    public void deleteDevLog(Long devLogId){
        
        DevLog findDevLog = devLogRepository.findById(devLogId)
            .orElseThrow(NoSuchDevLogException::new);
        
        devLogRepository.delete(findDevLog);
    }
    
    // < ======== validate logic ======== > //
    private void validateDevLog(Schedule schedule, User user){
        
        if (devLogRepository.existsByScheduleAndUser(schedule, user)){
            throw new IllegalStateException("이미 존재하는 DevLog 입니다");
        }
    }
    
    private Schedule validateScheduleId (Long scheduleId){
        if (scheduleId == null){
            return null;
        }
        return scheduleRepository.findOneOptional(scheduleId)
            .orElseThrow(NoSuchScheduleException::new);
    }
    
    private User validateAccountId (String accountId){
        if (accountId == null){
            return null;
        }
        return userRepository.findByAccountId(accountId)
                .orElseThrow(NoSuchUserException::new);
    }
}