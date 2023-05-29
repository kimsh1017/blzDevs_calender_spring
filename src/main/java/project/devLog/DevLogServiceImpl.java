package project.devLog;

import project.domain.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.ArrayList;
import java.util.stream.Collectors;
import project.user.UserRepository;
import project.schedule.ScheduleRepository;

@Service
@RequiredArgsConstructor
public class DevLogServiceImpl implements DevLogService{

    private final ScheduleRepository scheduleRepository;
    private final UserRepository userRepository;
    private final DevLogRepository devLogRepository;
    
    @Transactional
    public Long createDevLog(Long scheduleId, String userAccountId, String content){
        //이미 로그 작성한 적 있는지 예외처리 해야함
        
        Schedule schedule = scheduleRepository.findOne(scheduleId);
        //예외처리 필요함
        User user = userRepository.findByAccountId(userAccountId).get(0);
        
        DevLog devLog = new DevLog(schedule,user,content);
        devLogRepository.save(devLog);
        
        return devLog.getId();
    }
    
    public List<DevLog> findAll(){
        return devLogRepository.findAll();
    }
}