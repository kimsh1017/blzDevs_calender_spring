package project.repository;

import project.domain.*;
import org.springframework.stereotype.Repository;
import javax.persistence.EntityManager;
import java.util.List;
import java.util.ArrayList;
import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class ScheduleRepositoryImpl implements ScheduleRepository{
    
    private final EntityManager em;
    
    @Override
    public void save(Schedule schedule){
        em.persist(schedule);
    }
    
    @Override
    public Schedule findOne(Long id){
        return em.find(Schedule.class, id);
    }
    
    @Override
    public List<Schedule> findAll(int offset, int limit){
        return em.createQuery(
            "select s from Schedule s" +
            " join fetch s.workspace w", Schedule.class)
            .setFirstResult(offset)
            .setMaxResults(limit)
            .getResultList();
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
}