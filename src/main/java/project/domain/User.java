package project.domain;

import javax.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import java.util.List;
import java.util.ArrayList;
import java.time.LocalDateTime;

@Entity
@Table(name = "users")
@Getter
@NoArgsConstructor
public class User{
    
    @Id @GeneratedValue
    @Column(name = "user_id")
    private Long id;
    
    private String accountId;
    
    private String password;
    
    private String name;
    
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<UserWorkspace> userWorkspaces = new ArrayList<> ();
    
    public User(String accountId, String password, String name){
        this.accountId = accountId;
        this.password = password;
        this.name = name;
    }
    
    public void update(String password, String name){
        this.password = password;
        this.name = name;
    }
}