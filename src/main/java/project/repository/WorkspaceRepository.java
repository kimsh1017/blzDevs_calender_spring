package project.repository;

import project.domain.*;
import java.util.List;

public interface WorkspaceRepository{
    
    public void save(Workspace workspace);
    
    public Workspace findOne(Long id);
    
    public List<Workspace> findAll(int offset, int limit);
    
    public List<Workspace> findByName(String name);
    
    public List<Workspace> findByUserAccountId(String accountId);
}