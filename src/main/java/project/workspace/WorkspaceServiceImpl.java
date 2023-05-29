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
        
    @Transactional
    public Long makeWorkspace(String name, List<String> admins, List<String> users){
        //중복 이름 검증
        validateWorkspace(name);
        
        //회원 찾아서 연결하기 -> 쿼리 개복잡해짐 -> queryDSL 써야할듯..?
        List <User> adminUsers = admins.stream().
            map(userId -> userRepository.findByAccountId(userId).get(0))
            .collect(Collectors.toList());
        
        List <User> nomalUsers = users.stream().
            map(userId -> userRepository.findByAccountId(userId).get(0))
            .collect(Collectors.toList());
        
        // 중간 테이블 만들기
        List <UserWorkspace> userWorkspaces = new ArrayList<> ();
        
        for (User user : adminUsers){
            UserWorkspace userWorkspace = new UserWorkspace();
            userWorkspace.setUser(user);
            userWorkspaces.add(userWorkspace);
        }
        for (User user : nomalUsers){
            UserWorkspace userWorkspace = new UserWorkspace();
            userWorkspace.setUser(user);
            userWorkspaces.add(userWorkspace);
        }
        
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