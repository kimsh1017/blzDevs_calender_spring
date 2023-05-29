package project.workspace;

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
    
    public List<Workspace> findAll(){
        return em.createQuery(
            "select w from Workspace w", Workspace.class)
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
}