package project.controller;

import project.domain.*;
import project.dto.workspace.*;
import project.service.WorkspaceService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.ArrayList;
import static java.util.stream.Collectors.toList;
import org.springframework.http.ResponseEntity;

@RequiredArgsConstructor
@RestController
public class WorkspaceController{
    
    private final WorkspaceService workspaceService;
    
    @GetMapping("/workspaces")
    public ResponseEntity<FindAllWorkspacesResponse> findAllWorkspaces(
                                @RequestParam(value = "offset", defaultValue = "0") int offset,
                                @RequestParam(value = "limit", defaultValue = "100") int limit){
        
        List<WorkspaceDto> responseData = workspaceService.findAll(offset, limit).stream()
            .map(WorkspaceDto::new)
            .collect(toList());
        
        FindAllWorkspacesResponse response = new FindAllWorkspacesResponse(responseData.size(), responseData);
        
        return ResponseEntity.ok(response);
    }
    
    @PostMapping("/workspaces")
    public ResponseEntity<Long> createWorkspace(@RequestBody CreateWorkspaceRequest request){
        
        Long workspaceId = workspaceService.makeWorkspace(request);
        
        return ResponseEntity.ok(workspaceId);
    }
    
    @GetMapping("/workspaces/{workspaceId}")
    public ResponseEntity<WorkspaceDto> findAllWorkspaces(@PathVariable Long workspaceId){
        
        Workspace findWorkspace = workspaceService.findOne(workspaceId);
        
        WorkspaceDto response = new WorkspaceDto(findWorkspace);
        
        return ResponseEntity.ok(response);
    }
    
    @PutMapping("/workspaces/{workspaceId}")
    public ResponseEntity<WorkspaceDto> findAllWorkspaces(@PathVariable Long workspaceId,
                                                         @RequestBody CreateWorkspaceRequest request){
        
        Workspace findWorkspace = workspaceService.updateWorkspace(workspaceId, request);
        
        WorkspaceDto response = new WorkspaceDto(findWorkspace);
        
        return ResponseEntity.ok(response);
    }
    
    @DeleteMapping("/workspaces/{workspaceId}")
    public ResponseEntity<DeleteWorkspaceResponse> deleteWorkspaces(@PathVariable Long workspaceId){
        
        workspaceService.removeWorkspace(workspaceId);
        
        return ResponseEntity.ok(new DeleteWorkspaceResponse());
    }
    
    
    
    // 나중에 유저에 역할 부여 가능
                 
    // @Getter
    // @AllArgsConstructor
    // static class UserWorkspaceDto{
    //     String userName;
    //     //String 역할?
    // }
}