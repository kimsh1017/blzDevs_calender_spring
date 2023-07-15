package project.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
// import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import org.springframework.http.MediaType;

import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.boot.test.mock.mockito.MockBean;
import static org.mockito.BDDMockito.given;
import static org.mockito.ArgumentMatchers.any;

import project.domain.*;
import project.dto.user.*;
import project.service.UserService;
import project.repository.UserRepository;
import java.util.List;  
import java.util.ArrayList;


@RunWith(SpringRunner.class)
@WebMvcTest(UserController.class)
@AutoConfigureMockMvc
public class UserControllerTest{
    @Autowired
    MockMvc mvc;
    
    @MockBean
    private UserService userService;
    
    @MockBean
    private UserRepository userRepository;
    
    @Test
    public void getSingleUserTest() throws Exception {
        //given
        User user = new User("test1", "test1234", "kim");
        
        given(userService.findOne(any(Long.class))).willReturn(user);
        
        //when
        mvc.perform(get("/users/1"))
        //then
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.accountId").value("test1"));
    }
    
    @Test
    public void createUserTest() throws Exception {
        //given
        String request = "{ \"accountId\" : \"test1\" , \"password\" : \"Test1234\" , \"name\" : \"kim\" }";
        
        given(userService.register(any(User.class))).willReturn(1L);
        
        //when
        mvc.perform(post("/users")
        //then
            .content(request)
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$").value("1"));
    }
    
    // @Test
    // void createUserTest() throws Exception {
    //     //given
    //     String request = "{ \"accountId\" : \"test1\" , \"password\" : \"Test1234\" , \"name\" : \"kim\" }";
        
    //     given(userService.register(any(User.class))).willReturn(1L);
        
    //     //when
    //     mvc.perform(post("/users")
    //     //then
    //         .content(request)
    //         .contentType(MediaType.APPLICATION_JSON))
    //         .andExpect(status().isOk())
    //         .andExpect(jsonPath("$").value("1"));
    // }
}