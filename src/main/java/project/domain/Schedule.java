package project.domain;

import javax.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.util.List;
import java.util.ArrayList;
import java.time.LocalDateTime;

@Entity
@Table(name = "schedules")
@Getter @Setter
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
    
    @OneToMany(mappedBy="schedule", cascade = CascadeType.ALL)
    private List<UserSchedule> userSchedules = new ArrayList <> ();
    
    
    // < == 연관관계 설정 메소드 == >
    public void addUserSchedule(UserSchedule userSchedule){
        this.userSchedules.add(userSchedule);
        userSchedule.setSchedule(this);
    }
    
    // <== 생성 메소드 ==>
    public static Schedule createSchedule(Workspace workspace, String name, LocalDateTime startDate, LocalDateTime endDate, List<UserSchedule> userSchedules){
        Schedule schedule = new Schedule();
        schedule.setWorkspace(workspace);
        schedule.setName(name);
        schedule.setStartDate(startDate);
        schedule.setEndDate(endDate);
        // schedule.setUserSchedules(userSchedules);
        
        // for (UserSchedule userSchedule : userSchedules){
        //     userSchedule.setSchedule(schedule);
        // }
        for (UserSchedule userSchedule : userSchedules){
            schedule.addUserSchedule(userSchedule);
        }
        
        
        return schedule;
    }
}