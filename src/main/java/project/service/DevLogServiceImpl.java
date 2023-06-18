package project.service;

import project.domain.*;
import project.dto.devLog.*;
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
import static java.util.stream.Collectors.toList;


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
        
        User user = userRepository.findOneOptional(createDevLogRequest.getUserAccountId())
            .orElseThrow(NoSuchUserException::new);
        
        String content = createDevLogRequest.getContent();
        
        validateDevLog(schedule, user);
        
        DevLog devLog = new DevLog(schedule,user,content);
        devLogRepository.save(devLog);
        
        return devLog.getId(); // id만 반환하는거 맞을까?
    }
    
    @Override
    public DevLogFindAllResponse findAllBySearch(int offset,
                                                int limit, 
                                                Long scheduleId, 
                                                String accountId){
        
        Schedule schedule = validateScheduleId(scheduleId);
        User user = validateAccountId(accountId);
            
        List<DevLogDto> collect = devLogRepository.searchDevLogs(limit, offset, schedule, user)
            .stream()
            .map(DevLogDto::toDto)
            .collect(toList());
        
        return new DevLogFindAllResponse(collect.size(), collect);
    }
    
    private void validateDevLog(Schedule schedule, User user){
        
        //검색용 페이징 설정
        int offset = 0;
        int limit = 100;
        
        List<DevLog> findDevLog = devLogRepository.searchDevLogs(offset, limit, schedule, user);

        if (!findDevLog.isEmpty()){
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
        return userRepository.findOneOptional(accountId)
                .orElseThrow(NoSuchUserException::new);
    }
}