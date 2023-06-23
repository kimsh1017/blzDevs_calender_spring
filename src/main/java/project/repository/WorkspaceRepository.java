package project.repository;

import project.domain.*;
import java.util.List;
import java.util.Optional;

public interface WorkspaceRepository{
    
    public void save(Workspace workspace);
    
    public void remove(Workspace workspace);
    
    public Workspace findOne(Long id);
    
    public List<Workspace> findAll(int offset, int limit);
    
    public Optional<Workspace> findByName(String name);
    
    public List<Workspace> findByUserAccountId(String accountId);
}