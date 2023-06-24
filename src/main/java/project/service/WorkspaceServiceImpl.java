package project.service;

import project.domain.*;
import project.dto.workspace.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import project.repository.WorkspaceRepository;
import project.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.ArrayList;
import java.util.Optional;
import static java.util.stream.Collectors.toList;
import project.exception.devLog.NoSuchUserException;

@Service
@RequiredArgsConstructor
public class WorkspaceServiceImpl implements WorkspaceService{
    
    private final WorkspaceRepository workspaceRepository;
    private final UserRepository userRepository;
        
    // 성능 개선 필요
    @Transactional
    public Long makeWorkspace(CreateWorkspaceRequest request){
        
        String name = request.getName();
        //중복 이름 검증
        validateWorkspace(name);
        
        List<User> users = userRepository.findUsersByAccounIdList(request.getUsers());
        
        //Workspace 생성
        Workspace workspace = new Workspace(name, users);
        workspaceRepository.save(workspace);
        return workspace.getId();
    }
    
    @Override
    @Transactional
    public void removeWorkspace(Long id){
        Workspace findWorkspace = workspaceRepository.findOne(id);
        
        workspaceRepository.remove(findWorkspace);
    }
    
    @Override
    public Workspace findOne(Long id){
        return workspaceRepository.findOne(id);
    }
    
    @Override
    public List<Workspace> findAll(int offset, int limit){
        return workspaceRepository.findAll(offset, limit);
    }
    
    @Override
    public List<Workspace> findByUserAccountId(String accountId){
        return workspaceRepository.findByUserAccountId(accountId);
    }
    
    @Override
    @Transactional
    public Workspace updateWorkspace(Long id, CreateWorkspaceRequest request){
        Workspace workspace = workspaceRepository.findOne(id);
        String name = request.getName();
        //중복 이름 검증
        if (!workspace.getName().equals(name)){
            validateWorkspace(name);
        }
        
        List<User> users = userRepository.findUsersByAccounIdList(request.getUsers());
        
        workspace.updateWorkspace(name, users);
        return workspace;
    }
    
    @Override
    @Transactional
    public Workspace addUser(Long workspaceId, String userAccountId){
        Workspace workspace = workspaceRepository.findOne(workspaceId);
        User user = userRepository.findOneOptional(userAccountId)
            .orElseThrow(NoSuchUserException::new);
        
        workspace.addUser(user);
        return workspace;
    }
    
    @Override
    @Transactional
    public void removeUser(Long workspaceId, Long userId){
        Workspace workspace = workspaceRepository.findOne(workspaceId);
        User user = userRepository.findOne(userId);
        
        workspace.removeUser(user);
    }
    
    //bool 형으로 바꿔서 검증 함수 만들면 재사용성 더 좋을듯
    private void validateWorkspace(String name){
        Optional<Workspace> findWorkspace = workspaceRepository.findByName(name);
        if (!findWorkspace.isEmpty()){
            throw new IllegalStateException("이미 존재하는 Workspace 입니다");
        }
    }
}