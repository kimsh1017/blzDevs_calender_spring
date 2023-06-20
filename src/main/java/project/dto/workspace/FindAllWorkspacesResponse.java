package project.dto.workspace;

import lombok.Getter;
import lombok.AllArgsConstructor;
import java.util.List;

@Getter
@AllArgsConstructor
public class FindAllWorkspacesResponse{
    private int count;
    private List<WorkspaceDto> data;
}