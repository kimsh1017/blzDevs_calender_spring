package project.repository;

import project.domain.*;
import org.springframework.stereotype.Repository;
import javax.persistence.EntityManager;
import java.util.List;
import java.util.ArrayList;
import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class WorkspaceRepositoryImpl implements WorkspaceRepository{
    
    private final EntityManager em;
    
    @Override
    public void save(Workspace workspace){
        em.persist(workspace);
    }
    
    @Override
    public Workspace findOne(Long id){
        return em.find(Workspace.class, id);
    }
    
    public List<Workspace> findAll(int offset, int limit){
        return em.createQuery(
            "select w from Workspace w", Workspace.class)
            .setFirstResult(offset)
            .setMaxResults(limit)
            .getResultList();
    }
    
    @Override
    public List<Workspace> findByName(String name){
        return em.createQuery(
            "select w from Workspace w" +
            " where w.name = :name", Workspace.class)
            .setParameter("name", name)
            .getResultList();
    }
    
    //in 쿼리 쓰는 법 더 자세하게 알아보자, 중간 테이블에 accountId 넣는 방법도 생각해보자
    @Override
    public List<Workspace> findByUserAccountId(String accountId){
        return em.createQuery(
            "select distinct w from Workspace w" +
            " join fetch w.userWorkspaces uw" +
            " where :accountId in uw.user.accountId", Workspace.class)
            .setParameter("accountId", accountId)
            .getResultList();
    }
}