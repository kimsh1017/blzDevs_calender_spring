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
        //없는 아이디 아닌지 검증해봐야함 -> springData 사용하자
        return userRepository.findOne(id);
    }
    
    @Override
    public List<User> findAllBySearch(int offset, int limit, String accountId, String name){
        return userRepository.searchUsers(offset,limit,accountId,name);
    }
    
    //유저 정보 수정
    @Override
    @Transactional
    public User updateUser(Long id, String password, String name){
        User user = userRepository.findOne(id);
        user.update(password,name);
        return user;
    }
    
    //유저 삭제
    @Override
    @Transactional
    public void deleteUser(Long id){
        
        User user = userRepository.findOne(id);
        
        userRepository.remove(user);
    }
    
    // < == validate logic ==> //
    private void validateUserAccountId(String accountId){
        Optional<User> findUser = userRepository.findOneOptional(accountId);
        
        if (!findUser.isEmpty()){
            throw new UserAlreadyExistException();
        }
    }
    
    //로그인
    
    //로그아웃
}