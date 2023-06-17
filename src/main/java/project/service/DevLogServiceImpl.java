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
        
        List<User> findUsers = userRepository.findByAccountId(createDevLogRequest.getUserAccountId());
        if (findUsers.isEmpty()){
            throw new NoSuchUserException();
        }
        User user = findUsers.get(0);
        
        // 이미 로그 작성한 적 있는지 예외처리
        validateDevLog(schedule, user);
        
        String content = createDevLogRequest.getContent();
        
        DevLog devLog = new DevLog(schedule,user,content);
        devLogRepository.save(devLog);
        
        return devLog.getId();
    }
    
    @Override
    public DevLogFindAllResponse findAll(){
        
        List<DevLogDto> collect = devLogRepository.findAll().stream()
            .map(DevLogDto::toDto)
            .collect(toList());
        
        return new DevLogFindAllResponse(collect.size(), collect);
    }
    
    @Override
    public DevLogFindAllResponse findAllBySearch(int offset,
                                                int limit, 
                                                Long scheduleId, 
                                                String accountId){
        
        
        // <== 여기 수정해야함 ==> 동적 파라미터 ??
        Schedule schedule = scheduleRepository.findOne(scheduleId);
        
        User user = userRepository.findByAccountId(accountId).get(0);
        
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
            throw new IllegalStateException("이미 존재하는 Id 입니다");
        }
    }
}