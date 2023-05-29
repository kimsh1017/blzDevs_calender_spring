package project.schedule;

import project.domain.*;
import java.util.List;

public interface ScheduleRepository{
    
    public void save(Schedule schedule);
    
    // public Workspace findOne(Long id);
    
    public List<Schedule> findAll();
    
    // public List<Workspace> findByName(String name);
}