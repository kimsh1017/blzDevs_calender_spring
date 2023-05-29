package project.domain;

import javax.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.util.List;
import java.util.ArrayList;
import java.time.LocalDateTime;

@Entity
@Table(name = "workspaces")
@Getter @Setter
public class Workspace{
    
    @Id @GeneratedValue
    @Column(name = "workspace_id")
    private Long id;

    private String name;
    
    @OneToMany(mappedBy="workspace", cascade = CascadeType.ALL)
    private List<UserWorkspace> userWorkspaces = new ArrayList<> ();
    
    
    // 연관관계 편의 메소드
    public void addUserWorkspace(UserWorkspace userWorkspace){
        this.userWorkspaces.add(userWorkspace);
        userWorkspace.setWorkspace(this);
    }
    
    
    // <== 생성 메소드 ==> //
    public static Workspace createWorkspace(String name, List<UserWorkspace> userWorkspaces){
        Workspace workspace = new Workspace();
        
        workspace.setName(name);
        
        for (UserWorkspace userWorkspace : userWorkspaces){
            workspace.addUserWorkspace(userWorkspace);
        }
        
        return workspace;
    }
}