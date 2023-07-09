package project.repository;

import java.util.List;
import project.domain.*;

import lombok.RequiredArgsConstructor;
import static project.domain.QSchedule.schedule;
import static project.domain.QUserSchedule.userSchedule;
import static project.domain.QWorkspace.workspace;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.querydsl.core.types.dsl.BooleanExpression;


//이건 그냥 전용 리포지토리 만드는게 좋아보임
@RequiredArgsConstructor
public class ScheduleRepositoryCustomImpl implements ScheduleRepositoryCustom{
    
    private final JPAQueryFactory qf;
    
    @Override
    public List<Schedule> searchSchedulesByUserAccountId(String accountId){

        return qf.selectFrom(schedule)
            .join(schedule.workspace, workspace).fetchJoin()
            .join(schedule.userSchedules, userSchedule)
            .where(userEq(accountId))
            .distinct()
            .fetch();
    }
    
    private BooleanExpression userEq(String accountId){
        if (accountId == null){
            return null;
        }
        return userSchedule.user.accountId.eq(accountId);
    }
}