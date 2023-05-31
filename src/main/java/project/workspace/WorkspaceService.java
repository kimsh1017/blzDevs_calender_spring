package project.workspace;

import project.domain.*;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;


public interface WorkspaceService{

    public Long makeWorkspace(String name, List<String> users);
    
    public List<Workspace> findAll(int offset, int limit);
}