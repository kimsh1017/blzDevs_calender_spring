package project.repository;

import java.util.List;
import project.domain.*;
import lombok.RequiredArgsConstructor;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.querydsl.core.types.dsl.BooleanExpression;
import static project.domain.QDevLog.devLog;

@RequiredArgsConstructor
public class DevLogRepositoryCustomImpl implements DevLogRepositoryCustom{
    
    private final JPAQueryFactory qf;
    
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