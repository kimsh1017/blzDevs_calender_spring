package project.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.querydsl.core.types.dsl.BooleanExpression;
import static project.domain.QDevLog.devLog;
import project.domain.*;
import org.springframework.stereotype.Repository;
import javax.persistence.EntityManager;
import java.util.List;
import java.util.ArrayList;
import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class DevLogRepositoryImpl implements DevLogRepository{
    
    private final EntityManager em;
    private final JPAQueryFactory qf;
    
    @Override
    public void save(DevLog devLog){
        em.persist(devLog);
    }
    
    @Override
    public List<DevLog> searchDevLogs(int offset, int limit, Schedule schedule, User user){
        return qf.selectFrom(devLog)
            .where(scheduleEq(schedule), userEq(user))
            .fetch();
    }
    
    private BooleanExpression scheduleEq(Schedule schedule){
        if (schedule == null){
            return null;
        }
        return devLog.schedule.eq(schedule);
    }
    
    private BooleanExpression userEq(User user){
        if (user == null){
            return null;
        }
        return devLog.user.eq(user);
    }
}

