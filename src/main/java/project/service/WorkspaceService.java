package project.service;

import project.domain.*;
import project.dto.workspace.*;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;


public interface WorkspaceService{

    public Long makeWorkspace(CreateWorkspaceRequest request);
    
    public List<Workspace> findAll(int offset, int limit);
    
    public List<Workspace> findByUserAccountId(String accountId);
}