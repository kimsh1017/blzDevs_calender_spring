package project.service;

import project.domain.*;
import project.dto.user.*;
import project.exception.user.*;
import project.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;
import static java.util.stream.Collectors.toList;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{
    
    private final UserRepository userRepository;
    
    //회원가입
    @Override
    @Transactional
    public Long register(User user){
        //중복 회원 검증
        validateUserAccountId(user.getAccountId());
        
        userRepository.save(user);
        return user.getId();
    }
    
    //유저 단건 조회
    @Override
    public User findOne(Long id){
        User findUser = userRepository.findById(id)
            .orElseThrow(NoSuchUserException::new);
        
        return findUser;
    }
    
    //유저 리스트 조회
    @Override
    public Page<User> findAllBySearch(Pageable pageable, String accountId, String name){
        return userRepository.searchUsers(pageable, accountId, name);
    }
    
    //유저 정보 수정
    @Override
    @Transactional
    public User updateUser(Long id, String password, String name){
        User user = userRepository.findById(id)
            .orElseThrow(NoSuchUserException::new);
        
        user.update(password,name);
        return user;
    }
    
    //유저 삭제
    @Override
    @Transactional
    public void deleteUser(Long id){
        User user = userRepository.findById(id)
            .orElseThrow(NoSuchUserException::new);
        
        userRepository.delete(user);
    }
    
    // < == validate logic ==> //
    private void validateUserAccountId(String accountId){
        
        if (userRepository.existsByAccountId(accountId)){
            throw new UserAlreadyExistException();
        }
    }
    
    //로그인
    
    //로그아웃
}