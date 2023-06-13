package project.service;

import project.domain.*;
import project.dto.devLog.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.ArrayList;
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
        //이미 로그 작성한 적 있는지 예외처리 해야함
        // validateDevLog(scheduleId, userAccountId);
        
        //예외처리 필요함
        Schedule schedule = scheduleRepository.findOne(createDevLogRequest.getScheduleId());
        
        User user = userRepository.findByAccountId(createDevLogRequest.getUserAccountId()).get(0);
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
                                                String scheduleId, 
                                                String accountId){
        
        List<DevLogDto> collect = devLogRepository.searchDevLogs(limit,offset,scheduleId,accountId)
            .stream()
            .map(DevLogDto::toDto)
            .collect(toList());
        
        return new DevLogFindAllResponse(collect.size(), collect);
    }
    
    // private validateDevLog(Long scheduleId, String userAccountId){
    //     Long findUserId = userRepository.findByAccountId(userAccountId).get(0).getId();
    //     DevLog findDevLog = devLogRepository.findByScheduleIdAndUserId(scheduleId,findUserId);
    //     if (!findWorkspaces.isEmpty()){
    //         throw new IllegalStateException("이미 존재하는 Id 입니다");
    // }
}