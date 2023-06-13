package project.service;

import project.domain.*;
import project.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{
    
    private final UserRepository userRepository;
    
    //회원가입
    @Transactional
    public Long register(User user){
        validateUser(user); //중복 회원 검증
        userRepository.save(user);
        return user.getId();
    }
    
    //유저 단건 조회
    @Transactional
    public User findOne(Long id){
        return userRepository.findOne(id);
    }
    
    //유저 전체 조회
    @Transactional
    public List<User> findAll(){
        return userRepository.findAll();
    }
    
    //중복 회원 검증 로직
    private void validateUser(User user){
        List<User> findUsers = userRepository.findByAccountId(user.getAccountId());
        if (!findUsers.isEmpty()){
            throw new IllegalStateException("이미 존재하는 Id 입니다");
        }
    }
    
    //이름으로 유저 조회
    
    //id로 유저 조회
    @Override
    @Transactional
    public User findByAccountId(String accountId){
        List<User> findUsers = userRepository.findByAccountId(accountId);
        
        if (findUsers.isEmpty()){
            throw new IllegalStateException("없는 Id 입니다");
        }
        return findUsers.get(0);
    }
    
    
    //로그인
    
    //로그아웃
}