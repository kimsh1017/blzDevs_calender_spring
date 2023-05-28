package project.domain;

import javax.persistence.*;
import lombok.Getter;
import java.util.List;
import java.util.ArrayList;
import java.time.LocalDateTime;

@Entity
@Table(name = "workspaces")
@Getter
public class Workspace{
    
    @Id @GeneratedValue
    @Column(name = "workspace_id")
    private Long id;

    private String name;
}