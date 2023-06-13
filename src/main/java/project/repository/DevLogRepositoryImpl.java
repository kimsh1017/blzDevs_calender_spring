package project.repository;

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
    
    @Override
    public List<DevLog> searchDevLogs(int offset, int limit, String scheduleId, String accountId){
        // <== 여기 구현해야함 ==>
        // queryDSL 써서 구현해보자
        
        return em.createQuery(
            "select d from DevLog d" , DevLog.class)
            .setFirstResult(offset)
            .setMaxResults(limit)
            .getResultList();
    }
}