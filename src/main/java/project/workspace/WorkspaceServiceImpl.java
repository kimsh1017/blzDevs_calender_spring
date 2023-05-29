package project.workspace;

import project.domain.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.ArrayList;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class WorkspaceServiceImpl implements WorkspaceService{
    
    private final WorkspaceRepository workspaceRepository;
    private final UserRepository userRepository;
        
    // 성능 개선 필요
    @Transactional
    public Long makeWorkspace(String name, List<String> userAccountIds){
        //중복 이름 검증
        validateWorkspace(name);
        
        List <User> findUsers = userRepository.findUsersByAccounIdList(userAccountIds);
        
        // 중간 테이블 만들기
        List<UserWorkspace> userWorkspaces = findUsers.stream()
            .map(user -> {
                UserWorkspace userWorkspace = new UserWorkspace();
                userWorkspace.setUser(user);
                return userWorkspace;
            })
            .collect(Collectors.toList());
        
        //Workspace 생성
        Workspace workspace = Workspace.createWorkspace(name, userWorkspaces);
        workspaceRepository.save(workspace);
        return workspace.getId();
    }
    
    
    public List<Workspace> findAll(){
        return workspaceRepository.findAll();
    }
    
    //bool 형으로 바꿔서 검증 함수 만들면 재사용성 더 좋을듯
    private void validateWorkspace(String name){
        List<Workspace> findWorkspaces = workspaceRepository.findByName(name);
        if (!findWorkspaces.isEmpty()){
            throw new IllegalStateException("이미 존재하는 Id 입니다");
        }
    }
}