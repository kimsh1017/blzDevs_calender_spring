package project.repository;

import java.util.List;
import project.domain.*;
import lombok.RequiredArgsConstructor;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.querydsl.core.types.dsl.BooleanExpression;
import static project.domain.QDevLog.devLog;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

@RequiredArgsConstructor
public class DevLogRepositoryCustomImpl implements DevLogRepositoryCustom{
    
    private final JPAQueryFactory qf;
    
    @Override
    public Page<DevLog> searchDevLogs(Pageable pageable, Schedule schedule, User user){
        List<DevLog> content =  qf.selectFrom(devLog)
            .where(scheduleEq(schedule), userEq(user))
            .offset(pageable.getOffset())
            .limit(pageable.getPageSize())
            .fetch();
        
        Long count = qf.select(devLog.count())
            .from(devLog)
            .where(scheduleEq(schedule), userEq(user))
            .fetchOne();
        
        return new PageImpl<>(content, pageable, count);
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