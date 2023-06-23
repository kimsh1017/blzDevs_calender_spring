package project.domain;

import javax.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import java.util.List;
import java.util.ArrayList;
import java.time.LocalDateTime;

@Entity
@Table(name = "schedules")
@Getter @Setter
@NoArgsConstructor
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
    
    @OneToMany(mappedBy="schedule", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<UserSchedule> userSchedules = new ArrayList <> ();
        
    @OneToMany(mappedBy="schedule", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<DevLog> devLogs = new ArrayList <> ();
    
    // < == 연관관계 설정 메소드 == >
    public void addUserSchedule(UserSchedule userSchedule){
        this.userSchedules.add(userSchedule);
        userSchedule.setSchedule(this);
    }
    
    // 생성자
    public Schedule(Workspace workspace, String name, List<UserSchedule> userSchedules){
        this.workspace = workspace;
        this.name = name;
        this.userSchedules.addAll(userSchedules);
        
        for (UserSchedule userSchedule : userSchedules){
            userSchedule.setSchedule(this);
        }
    }
    
    //수정 메소드
    public void update(String name, List<UserSchedule> userSchedules){
        this.name = name;
        this.userSchedules.clear();
        this.userSchedules.addAll(userSchedules);
        
        for (UserSchedule userSchedule : userSchedules){
            userSchedule.setSchedule(this);
        }
        
    }
}