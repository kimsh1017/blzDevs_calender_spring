// package project.controller;

// import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
// import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
// import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
// import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

// import org.junit.runner.RunWith;
// import org.springframework.test.context.junit4.SpringRunner;
// import org.junit.jupiter.api.Test;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
// import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
// import org.springframework.test.web.servlet.MockMvc;
// import org.springframework.boot.test.mock.mockito.MockBean;
// import project.service.DevLogService;
// import project.repository.DevLogRepository;

// @RunWith(SpringRunner.class)
// @WebMvcTest(DevLogController.class)
// @AutoConfigureMockMvc
// class DevLogControllerTest{
//     @Autowired
//     MockMvc mvc;
    
//     @MockBean
//     private DevLogService devLogService;
    
//     @MockBean
//     private DevLogRepository devLogRepository;
    
//     @Test
//     void getAllDevLogTest() throws Exception {
//         //given
//         //when
//         //then
//         mvc.perform(get("/devLogs"))
//             .andExpect(status().isOk())
//             // .andExpect(jsonPath("$.data[0].scheduleId").value("12"));
//     }
// }