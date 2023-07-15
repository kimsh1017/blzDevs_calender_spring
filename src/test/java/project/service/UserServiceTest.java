package project.service;

import static org.mockito.BDDMockito.*;
import static org.mockito.ArgumentMatchers.any;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import static org.assertj.core.api.Assertions.assertThat;

import project.repository.UserRepository;
import project.domain.User;
import java.util.Optional;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceTest{
    
    @Mock
    private UserRepository userRepository;
    
    @InjectMocks
    private UserServiceImpl userService;
    
    @Test
    public void findOneTest(){
        User user = new User("test1", "test1234", "kim");
        given(userRepository.findById(any(Long.class))).willReturn(Optional.of(user));
        
        User result = userService.findOne(1L);
        
        assertThat(result.getAccountId()).isEqualTo("test1");
    }
}