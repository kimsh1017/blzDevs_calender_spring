package project.repository;

import project.domain.*;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserRepository extends JpaRepository<User, Long>, UserRepositoryCustom{
    
    // public void save(User user);
    
    // public void remove(User user);
    
    // //여기도 Optinal로 바꿔줘야함
    // public User findOne(Long id);
    
    // public List<User> findAll();
    
    // public Optional<User> findOneOptional(String accountId);
    
    public boolean existsByAccountId(String accountId);
    
    public Optional<User> findByAccountId(String accountId);
    
    @Query("select u from User u where u.accountId in :userAccountIds")
    public List<User> findUsersByAccounIdList(@Param("userAccountIds") List<String> userAccountIds);
    
    
    // public List<User> searchUsers(int offset, int limit, String accountId, String name);
}