package project.schedule;

import project.domain.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.ArrayList;
import java.util.stream.Collectors;
import project.user.UserRepository;
import project.workspace.WorkspaceRepository;
import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class ScheduleServiceImpl implements ScheduleService{
    
    private final ScheduleRepository scheduleRepository;
    private final UserRepository userRepository;
    private final WorkspaceRepository workspaceRepository;
    
    @Override
    public List<Schedule> findAll(int offset, int limit){
        return scheduleRepository.findAll(offset, limit);
    }
    
    @Override
    public List<Schedule> findSchedules(int offset, int limit, String workspaceName){
        if (workspaceName == null){
            return scheduleRepository.findAll(offset, limit);
        }
        else{
            return scheduleRepository.findByWorkspaceName(offset, limit, workspaceName);
        }
    }
    
    @Override
    @Transactional
    public Long createSchedule(String workspace, String name, LocalDateTime startDate, LocalDateTime endDate, List<String> userAccountIds){
        //구현 시작
        //예외처리 필요할듯? nullPointerException 가능
        Workspace findWorkspace = workspaceRepository.findByName(workspace).get(0);
        
        List <User> findUsers = userRepository.findUsersByAccounIdList(userAccountIds);
        
        // 중간 테이블 만들기
        List<UserSchedule> userSchedules = findUsers.stream()
            .map(user -> {
                UserSchedule userSchedule = new UserSchedule();
                userSchedule.setUser(user);
                return userSchedule;
            })
            .collect(Collectors.toList());
        
        Schedule schedule = Schedule.createSchedule(findWorkspace, name, startDate, endDate, userSchedules);
        scheduleRepository.save(schedule);
        return schedule.getId();
    }
}