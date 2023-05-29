package project.user;

import project.domain.*;
import org.springframework.stereotype.Repository;
import javax.persistence.EntityManager;
import java.util.List;
import java.util.ArrayList;
import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class UserRepositoryImpl implements UserRepository{
    
    private final EntityManager em;
    
    @Override
    public void save(User user){
        em.persist(user);
    }
    
    @Override
    public User findOne(Long id){
        return em.find(User.class, id);
    }
    
    @Override
    public List<User> findAll(){
        return em.createQuery(
            "select u from User u", User.class)
            .getResultList();
    }
    
    @Override
    public List<User> findByAccountId(String accountId){
        return em.createQuery(
            "select u from User u" +
            " where u.accountId =:accountId", User.class)
            .setParameter("accountId", accountId)
            .getResultList();
    }
}