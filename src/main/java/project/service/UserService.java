package project.service;

import project.domain.*;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;


public interface UserService{

    public Long register(User user);
    
    public User findOne(Long id); 
    
    public List<User> findAll();
    
    public User findByAccountId(String accountId);
}