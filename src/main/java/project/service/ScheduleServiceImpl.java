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
import java.util.Optional;
import static java.util.stream.Collectors.toList;
import project.repository.ScheduleRepository;
import project.repository.UserRepository;
import project.repository.WorkspaceRepository;
import java.time.LocalDateTime;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

@Service
@RequiredArgsConstructor
public class ScheduleServiceImpl implements ScheduleService{
    
    private final ScheduleRepository scheduleRepository;
    private final UserRepository userRepository;
    private final WorkspaceRepository workspaceRepository;
    
    @Override
    @Transactional(readOnly = true)
    public Schedule findOne(Long id){
        return scheduleRepository.findById(id)
            .orElseThrow(NoSuchScheduleException::new);
    }
    
    @Override
    @Transactional(readOnly = true)
    public Page<Schedule> findSchedules(Pageable pageable){
        
        return scheduleRepository.findAll(pageable);
    }
    
    // @Override
    // public List<Schedule> searchSchedule(String accountId){
    //     return scheduleRepository.searchSchedulesByUserAccountId(accountId);        
    // }
    
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
        Schedule schedule = scheduleRepository.findById(scheduleId)
            .orElseThrow(NoSuchScheduleException::new);
        
        String name = request.getName();
        
        List <User> users = userRepository.findUsersByAccounIdList(request.getUsers());
        
        schedule.update(name,users);
        
        return schedule;
    }
    
    @Override
    @Transactional
    public void removeSchedule(Long id){
        Schedule schedule = scheduleRepository.findById(id)
            .orElseThrow(NoSuchScheduleException::new);
        
        scheduleRepository.delete(schedule);
    }
    
    @Override
    @Transactional
    public Schedule addUser(Long scheduleId, String accountId){
        Schedule schedule = scheduleRepository.findById(scheduleId)
            .orElseThrow(NoSuchScheduleException::new);
        //유저 중복 검증해야함
        User user = userRepository.findByAccountId(accountId)
            .orElseThrow(NoSuchUserException::new);
        
        schedule.addUser(user);
        
        return schedule;
    }
    
    @Override
    @Transactional
    public void removeUser(Long scheduleId, Long userId){
        Schedule schedule = scheduleRepository.findById(scheduleId)
            .orElseThrow(NoSuchScheduleException::new);

        User user = userRepository.findById(userId)
            .orElseThrow(NoSuchUserException::new);
        
        schedule.removeUser(user);
    }
}