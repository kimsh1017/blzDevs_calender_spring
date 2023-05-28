package project.domain;

import javax.persistence.*;
import lombok.Getter;
import java.util.List;
import java.util.ArrayList;
import java.time.LocalDateTime;

@Entity
@Table(name = "schedules")
@Getter
public class Schedule{
    
    @Id @GeneratedValue
    @Column(name = "schedule_id")
    private Long id;
    
    private String name;
    
    private LocalDateTime startDate;
    
    private LocalDateTime endDate;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "workspace_id")
    private Workspace workspace;
}