package project.workspace;

import project.domain.*;
import java.util.List;

public interface WorkspaceRepository{
    
    public void save(Workspace workspace);
    
    public Workspace findOne(Long id);
    
    public List<Workspace> findAll();
    
    public List<Workspace> findByName(String name);
}