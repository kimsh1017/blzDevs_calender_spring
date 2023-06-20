package project.repository;

import project.domain.*;
import static project.domain.QUser.user;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.querydsl.core.types.dsl.BooleanExpression;
import org.springframework.stereotype.Repository;
import javax.persistence.EntityManager;
import java.util.List;
import java.util.ArrayList;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import com.querydsl.core.types.dsl.BooleanExpression;

@Repository
@RequiredArgsConstructor
public class UserRepositoryImpl implements UserRepository{
    
    private final EntityManager em;
    private final JPAQueryFactory qf;
    
    @Override
    public void save(User user){
        em.persist(user);
    }
    
    @Override
    public void remove(User user){
        em.remove(user);
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
        return em.createQuery(
            "select u from User u" +
            " where u.accountId in :userAccountIds", User.class)
            .setParameter("userAccountIds",userAccountIds)
            .getResultList();
    }
    
    @Override
    public List<User> searchUsers(int offset, int limit, String accountId, String name){
        
        return qf.selectFrom(user)
            .where(accountIdEq(accountId), nameEq(name))
            .fetch();     
    }
    
    // < == eq method == >
    private BooleanExpression accountIdEq(String accountId){
        if (accountId == null){
            return null;
        }
        return user.accountId.eq(accountId);
    }
    
    private BooleanExpression nameEq(String name){
        if (name == null){
            return null;
        }
        return user.name.eq(name);
    }
}