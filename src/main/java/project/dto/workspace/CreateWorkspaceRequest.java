package project.dto.workspace;

import lombok.Getter;
import lombok.AllArgsConstructor;
import java.util.List;
import java.util.ArrayList;


@Getter
@AllArgsConstructor
public class CreateWorkspaceRequest{
    private String name;
    private List<String> users = new ArrayList<> ();
}