package project.repository;

import project.domain.*;
import java.util.List;
import java.util.Optional;

public interface ScheduleRepository{
    
    public void save(Schedule schedule);
    
    public void remove(Schedule schedule);
    
    public Schedule findOne(Long id);
    
    public Optional<Schedule> findOneOptional (Long id);
    
    public List<Schedule> findAll(int offset, int limit, User user);
    
    public List<Schedule> findByWorkspaceName(int offset, int limit, String workspaceName);
}