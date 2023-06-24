package project.repository;

import project.domain.*;
import org.springframework.stereotype.Repository;
import javax.persistence.EntityManager;
import java.util.List;
import java.util.ArrayList;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import static project.domain.QSchedule.schedule;
import static project.domain.QUserSchedule.userSchedule;
import static project.domain.QWorkspace.workspace;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.querydsl.core.types.dsl.BooleanExpression;


@Repository
@RequiredArgsConstructor
public class ScheduleRepositoryImpl implements ScheduleRepository{
    
    private final EntityManager em;
    private final JPAQueryFactory qf;
    
    @Override
    public void save(Schedule schedule){
        em.persist(schedule);
    }
    
    @Override
    public void remove(Schedule schedule){
        em.remove(schedule);
    }
    
    @Override
    public Schedule findOne(Long id){
        return em.find(Schedule.class, id);
    }
    
    @Override
    public Optional<Schedule> findOneOptional (Long id){
        return Optional.ofNullable(em.find(Schedule.class, id));
    }
    
    @Override
    public List<Schedule> findAll(int offset, int limit, User user){
        // 메모리 문제 가능..?
        return qf.selectFrom(schedule)
            .join(schedule.workspace, workspace).fetchJoin()
            .join(schedule.userSchedules, userSchedule)
            .where(userEq(user))
            .distinct()
            .fetch();
    }
    
    @Override
    public List<Schedule> findByWorkspaceName(int offset, int limit, String workspaceName){
        return em.createQuery(
            "select s from Schedule s" +
            " join fetch s.workspace w" +
            " where w.name = :workspaceName", Schedule.class)
            .setParameter("workspaceName", workspaceName)
            .setFirstResult(offset)
            .setMaxResults(limit)
            .getResultList();
    }
    
    private BooleanExpression userEq(User user){
        if (user == null){
            return null;
        }
        return userSchedule.user.eq(user);
    }
}