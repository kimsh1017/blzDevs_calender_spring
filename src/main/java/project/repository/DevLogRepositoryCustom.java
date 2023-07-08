package project.repository;

import java.util.List;
import project.domain.*;

public interface DevLogRepositoryCustom{
    public List<DevLog> searchDevLogs(int offset, int limit, Schedule schedule, User user);
}