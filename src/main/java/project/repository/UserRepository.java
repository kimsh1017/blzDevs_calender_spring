package project.repository;

import project.domain.*;
import java.util.List;
import java.util.Optional;

public interface UserRepository{
    
    public void save(User user);
    
    //여기도 Optinal로 바꿔줘야함
    public User findOne(Long id);
    
    public List<User> findAll();
    
    public List<User> findByAccountId(String accountId);
    
    public List<User> findUsersByAccounIdList(List<String> userAccountIds);
}