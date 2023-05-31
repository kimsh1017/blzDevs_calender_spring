package project.controller;

import project.domain.*;
import project.workspace.WorkspaceService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.ArrayList;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@RestController
public class WorkspaceController{
    
    private final WorkspaceService workspaceService;
    
    @GetMapping("/workspaces")
    public Response findAllWorkspaces(@RequestParam(value = "offset", defaultValue = "0") int offset,
                                  @RequestParam(value = "limit", defaultValue = "100") int limit,
                                @RequestParam(value="accountId", required = false) String accountId){
        
        List<Workspace> findWorkspaces;
        
        if (accountId == null ){
            findWorkspaces = workspaceService.findAll(offset, limit);
        }
        else{
            findWorkspaces = workspaceService.findByUserAccountId(accountId);
        }
        
        List<WorkspaceDto> collect = findWorkspaces.stream()
            .map(workspace -> new WorkspaceDto(workspace.getName(), workspace.getUserWorkspaces()))
            .collect(Collectors.toList());
        
        return new Response(0,"",collect);
    }
    
    @PostMapping("/workspaces")
    public Response createWorkspace(@RequestBody CreateWorkspaceRequest request){
        Long workspaceId = workspaceService.makeWorkspace(request.getName(), request.getUsers());
        return new Response(0,"",workspaceId);
    }
    
    @Getter
    static class WorkspaceDto{
        private String name;
        private List<String> users = new ArrayList<> ();
        
        public WorkspaceDto(String name, List<UserWorkspace> userWorkspaces){
            this.name = name;
            for (UserWorkspace userWorkspace : userWorkspaces){
                users.add(userWorkspace.getUser().getAccountId());
            }
        }
    }
    
    // 나중에 유저에 역할 부여 가능
                 
    // @Getter
    // @AllArgsConstructor
    // static class UserWorkspaceDto{
    //     String userName;
    //     //String 역할?
    // }
    
    @Getter
    @AllArgsConstructor
    static class CreateWorkspaceRequest{
        private String name;
        
        // private List<String> admins = new ArrayList<> ();
        private List<String> users = new ArrayList<> ();
    }
}