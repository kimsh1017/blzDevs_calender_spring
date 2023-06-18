package project.repository;

import project.domain.*;
import org.springframework.stereotype.Repository;
import javax.persistence.EntityManager;
import java.util.List;
import java.util.ArrayList;
import java.util.Optional;
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
    public Optional<User> findOneOptional(String accountId){
        
        List<User> findUsers = em.createQuery(
                                    "select u from User u" +
                                    " where u.accountId =:accountId", User.class)
                                    .setParameter("accountId", accountId)
                                    .getResultList();
        
        if (findUsers.isEmpty()){
            return Optional.empty();
        }
        return Optional.ofNullable(findUsers.get(0));
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
    
    @Override
    public List<User> findUsersByAccounIdList(List<String> userAccountIds){
        // <== 여기 in 쿼리 써서 리팩토링 하자" ==> //
        String QueryString = "select u from User u" + " where";
        
        for (int i = 0; i < userAccountIds.size() - 1; i++){
            QueryString += " u.accountId='";
            QueryString += userAccountIds.get(i);
            QueryString += "' or";
        }
        QueryString += " u.accountId='";
        QueryString += userAccountIds.get(userAccountIds.size() - 1);
        QueryString += "'";
        System.out.println(QueryString);
        
        return em.createQuery(
            QueryString, User.class)
            .getResultList();
    }
}