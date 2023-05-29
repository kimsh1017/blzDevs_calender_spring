package project.devLog;

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
    
    @Override
    public void save(DevLog devLog){
        em.persist(devLog);
    }
    
    @Override
    public List<DevLog> findAll(){
        return em.createQuery(
            "select d from DevLog d" +
            " join fetch d.schedule s" +
            " join fetch d.user u", DevLog.class)
            .getResultList();
    }
}