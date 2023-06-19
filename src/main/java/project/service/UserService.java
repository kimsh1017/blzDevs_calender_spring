package project.service;

import project.domain.*;
import project.dto.user.*;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;


public interface UserService{

    public Long register(User user);
    
    public User findOne(Long id); 
    
    public List<User> findAllBySearch(int offset, int limit, String accountId, String name);
    
    public User updateUser(Long id, String password, String name);
        
    public void deleteUser(Long id);
}