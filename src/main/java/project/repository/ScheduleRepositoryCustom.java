package project.repository;

import java.util.List;
import project.domain.*;

public interface ScheduleRepositoryCustom{
    
    public List<Schedule> searchSchedulesByUserAccountId(String accountId);
}