package project.dto.user;

import lombok.Getter;
import lombok.AllArgsConstructor;
import java.util.List;
import project.domain.User;
import static java.util.stream.Collectors.toList;

@Getter
@AllArgsConstructor
public class FindSingleUserResponse {
    private String accountId;
    private String name;
    private List<String> workspaces;
    // private List<Long> schedules;
    // private List<Long> devLogs;
        
    public FindSingleUserResponse(User user){
        accountId = user.getAccountId();
        name = user.getName();
            
        workspaces = user.getUserWorkspaces().stream()
            .map(userWorkspace -> userWorkspace.getWorkspace().getName())
            .collect(toList());
    }
}