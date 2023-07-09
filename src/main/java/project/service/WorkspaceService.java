package project.service;

import project.domain.*;
import project.dto.workspace.*;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface WorkspaceService{

    public Long makeWorkspace(CreateWorkspaceRequest request);
    
    public void removeWorkspace(Long id);
    
    public Workspace findOne(Long id);
    
    public Page<Workspace> findAll(Pageable pageable);
    
    // public List<Workspace> findByUserAccountId(String accountId);
    
    public Workspace updateWorkspace(Long id, CreateWorkspaceRequest request);
    
    public Workspace addUser(Long workspaceId, String userAccountId);
    
    public void removeUser(Long workspaceId, Long userId);
}