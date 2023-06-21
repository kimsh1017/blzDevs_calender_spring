package project.domain;

import javax.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import java.util.List;
import java.util.ArrayList;
import java.time.LocalDateTime;

@Entity
@Table(name = "workspaces")
@Getter @Setter
@NoArgsConstructor
public class Workspace{
    
    @Id @GeneratedValue
    @Column(name = "workspace_id")
    private Long id;

    private String name;
    
    @OneToMany(mappedBy="workspace", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<UserWorkspace> userWorkspaces = new ArrayList<>();
    
    public Workspace(String name, List<UserWorkspace> userWorkspaces){
        this.name = name;
        this.userWorkspaces.addAll(userWorkspaces);
        
        for (UserWorkspace userWorkspace : userWorkspaces){
            userWorkspace.setWorkspace(this);
        }
    }
    // 수정 로직
    
    public void updateWorkspace(String name, List<UserWorkspace> userWorkspaces){
        this.name = name;
        this.userWorkspaces.clear();
        this.userWorkspaces.addAll(userWorkspaces);
        
        for (UserWorkspace userWorkspace : userWorkspaces){
            userWorkspace.setWorkspace(this);
        }
    }
}