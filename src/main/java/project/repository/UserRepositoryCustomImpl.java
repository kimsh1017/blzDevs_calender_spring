package project.repository;

import java.util.List;
import project.domain.*;
import lombok.RequiredArgsConstructor;
import com.querydsl.core.types.dsl.BooleanExpression;
import static project.domain.QUser.user;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.querydsl.core.types.dsl.BooleanExpression;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

@RequiredArgsConstructor
public class UserRepositoryCustomImpl implements UserRepositoryCustom{
    
    private final JPAQueryFactory qf;
    
    @Override
    public Page<User> searchUsers(Pageable pageable, String accountId, String name){
        
        List<User> content =  qf.selectFrom(user)
            .where(accountIdEq(accountId), nameEq(name))
            .offset(pageable.getOffset())
            .limit(pageable.getPageSize())
            .fetch();  
        
        Long count = qf.select(user.count())
            .from(user)
            .where(accountIdEq(accountId), nameEq(name))
            .fetchOne();
        
        return new PageImpl<>(content, pageable, count);
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