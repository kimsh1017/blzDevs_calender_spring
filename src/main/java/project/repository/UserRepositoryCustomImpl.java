package project.repository;

import java.util.List;
import project.domain.*;
import lombok.RequiredArgsConstructor;
import com.querydsl.core.types.dsl.BooleanExpression;
import static project.domain.QUser.user;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.querydsl.core.types.dsl.BooleanExpression;

@RequiredArgsConstructor
public class UserRepositoryCustomImpl implements UserRepositoryCustom{
    
    private final JPAQueryFactory qf;
    
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