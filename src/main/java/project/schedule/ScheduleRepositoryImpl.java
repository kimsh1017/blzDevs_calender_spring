package project.schedule;

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
    public List<Schedule> findAll(){
        return em.createQuery(
            "select s from Schedule s" +
            " join fetch s.workspace w", Schedule.class)
            .getResultList();
    }
}