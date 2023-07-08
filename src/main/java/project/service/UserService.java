package project.service;

import project.domain.*;
import project.dto.user.*;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface UserService{

    public Long register(User user);
    
    public User findOne(Long id); 
    
    public Page<User> findAllBySearch(Pageable pageable, String accountId, String name);
    
    public User updateUser(Long id, String password, String name);
        
    public void deleteUser(Long id);
}