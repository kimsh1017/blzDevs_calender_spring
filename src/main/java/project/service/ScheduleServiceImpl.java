package project.service;

import project.domain.*;
import project.dto.schedule.*;
import project.exception.schedule.*;
import project.exception.user.*;
import project.exception.workspace.*;
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

        List <User> users = userRepository.findUsersByAccounIdList(request.getUsers());
        
        Schedule schedule = new Schedule(workspace, name, users);
        scheduleRepository.save(schedule);
        return schedule.getId();
    }
    
    @Override
    @Transactional
    public Schedule updateSchedule(Long scheduleId, UpdateScheduleRequest request){
        Schedule schedule = scheduleRepository.findOne(scheduleId);
        
        String name = request.getName();
        
        List <User> users = userRepository.findUsersByAccounIdList(request.getUsers());
        
        schedule.update(name,users);
        
        return schedule;
    }
    
    @Override
    @Transactional
    public void removeSchedule(Long id){
        Schedule schedule = scheduleRepository.findOne(id);
        scheduleRepository.remove(schedule);
    }
    
    @Override
    @Transactional
    public Schedule addUser(Long scheduleId, String accountId){
        Schedule schedule = scheduleRepository.findOne(scheduleId);
        //유저 중복 검증해야함
        User user = validateUser(accountId);
        
        schedule.addUser(user);
        
        return schedule;
    }
    
    @Override
    @Transactional
    public void removeUser(Long scheduleId, Long userId){
        Schedule schedule = scheduleRepository.findOne(scheduleId);
        //유저 중복 검증해야함
        User user = userRepository.findOne(userId);
        
        schedule.removeUser(user);
    }
    
    private User validateUser(String accountId){
        if (accountId == null){
            return null;
        }
        return userRepository.findOneOptional(accountId)
                .orElseThrow(NoSuchUserException::new);
    }
    
}