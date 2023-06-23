package project.service;

import project.domain.*;
import project.dto.schedule.*;
import project.exception.schedule.*;
import project.exception.devLog.NoSuchUserException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.ArrayList;
import static java.util.stream.Collectors.toList;
import project.repository.ScheduleRepository;
import project.repository.UserRepository;
import project.repository.WorkspaceRepository;
import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class ScheduleServiceImpl implements ScheduleService{
    
    private final ScheduleRepository scheduleRepository;
    private final UserRepository userRepository;
    private final WorkspaceRepository workspaceRepository;
    
    @Override
    @Transactional(readOnly = true)
    public Schedule findOne(Long id){
        return scheduleRepository.findOne(id);
    }
    
    @Override
    @Transactional(readOnly = true)
    public List<Schedule> findSchedules(int offset, int limit,String accountId){
        
        User user = validateUser(accountId);
        
        return scheduleRepository.findAll(offset, limit, user);
    }
    
    @Override
    @Transactional
    public Long createSchedule(CreateScheduleRequest request){
    
        Workspace workspace = workspaceRepository.findByName(request.getWorkspace())
            .orElseThrow(NoSuchWorkspaceException::new);
        
        String name = request.getName();    
        // 중간 테이블 만들기
        List<UserSchedule> userSchedules = userRepository.findUsersByAccounIdList(request.getUsers())
            .stream()
            .map(UserSchedule::new)
            .collect(toList());
        
        Schedule schedule = new Schedule(workspace, name, userSchedules);
        scheduleRepository.save(schedule);
        return schedule.getId();
    }
    
    @Override
    @Transactional
    public Schedule updateSchedule(Long scheduleId, UpdateScheduleRequest request){
        Schedule schedule = scheduleRepository.findOne(scheduleId);
        
        String name = request.getName();
        
        List<UserSchedule> userSchedules = userRepository.findUsersByAccounIdList(request.getUsers())
            .stream()
            .map(UserSchedule::new)
            .collect(toList());
        
        schedule.update(name,userSchedules);
        
        return schedule;
    }
    
    @Override
    @Transactional
    public void removeSchedule(Long id){
        Schedule schedule = scheduleRepository.findOne(id);
        scheduleRepository.remove(schedule);
    }
    
    private User validateUser(String accountId){
        if (accountId == null){
            return null;
        }
        return userRepository.findOneOptional(accountId)
                .orElseThrow(NoSuchUserException::new);
    }
}