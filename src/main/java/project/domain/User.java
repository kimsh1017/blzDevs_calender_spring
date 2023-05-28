package project.domain;

import javax.persistence.*;
import lombok.Getter;
import java.util.List;
import java.util.ArrayList;
import java.time.LocalDateTime;

@Entity
@Table(name = "users")
@Getter
public class User{
    
    @Id @GeneratedValue
    @Column(name = "user_id")
    private Long user_id;
    
    private String id;
    
    private String password;
    
    private String name;
}