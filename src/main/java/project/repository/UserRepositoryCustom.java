package project.repository;

import java.util.List;
import project.domain.User;

public interface UserRepositoryCustom{
    public List<User> searchUsers(int offset, int limit, String accountId, String name);
}