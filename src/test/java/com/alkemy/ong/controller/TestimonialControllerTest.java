package com.alkemy.ong.controller;

import com.alkemy.ong.config.SwaggerConfig;
import com.alkemy.ong.dto.PageableResponse;
import com.alkemy.ong.dto.TestimonialDTO;
import com.alkemy.ong.exception.ParamNotFound;
import com.alkemy.ong.security.service.JwtUtils;
import com.alkemy.ong.security.service.UserDetailsCustomService;
import com.alkemy.ong.service.TestimonialService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import springfox.documentation.swagger.web.SecurityConfiguration;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

@WebMvcTest(TestimonialController.class)
@Import({SecurityConfiguration.class, BCryptPasswordEncoder.class, SwaggerConfig.class})
class TestimonialControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    UserDetailsCustomService userDetailsService;
    @MockBean
    JwtUtils jwtUtils;
    @MockBean
    private TestimonialService testimonialService;
    ObjectMapper jsonMapper = new ObjectMapper();

    @Nested
    class createTestimonialTest {
        @Test
        @DisplayName("Valid case")
        @WithMockUser(username = "mock.user@mockmail.mock", authorities = "ROLE_ADMIN")
        void save() throws Exception {
            TestimonialDTO request = generateTestimonialDTORequest();
            TestimonialDTO expectedResponse = generateTestimonialDTOResponse();
            Mockito.when(testimonialService.save(Mockito.any())).thenReturn(expectedResponse);

            mockMvc.perform(MockMvcRequestBuilders.post("/testimonials")
                            .content(jsonMapper.writeValueAsString(request))
                            .contentType(MediaType.APPLICATION_JSON)
                    )
                    .andExpect(MockMvcResultMatchers.status().isCreated())
                    .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                    .andExpect(MockMvcResultMatchers.content().json(jsonMapper.writeValueAsString(expectedResponse)))
                    .andDo(MockMvcResultHandlers.print());
            Mockito.verify(testimonialService).save(Mockito.any());
        }

        @Test
        @DisplayName("Invalid role")
        @WithMockUser(username = "mock.user@mockmail.mock", authorities = "ROLE_USER")
        void test3() throws Exception {
            TestimonialDTO request = generateTestimonialDTORequest();
            TestimonialDTO expectedResponse = generateTestimonialDTOResponse();
            Mockito.when(testimonialService.save(Mockito.any())).thenReturn(expectedResponse);

            mockMvc.perform(MockMvcRequestBuilders.post("/testimonials")
                            .content(jsonMapper.writeValueAsString(request))
                            .contentType(MediaType.APPLICATION_JSON)
                    )
                    .andExpect(MockMvcResultMatchers.status().isForbidden())
                    .andExpect(MockMvcResultMatchers.content().string(Matchers.not(Matchers.containsString("Testimonial Test"))))
                    .andDo(MockMvcResultHandlers.print());

            Mockito.verify(testimonialService, Mockito.never()).save(Mockito.any());
        }

        @Test
        @DisplayName("Token not valid")
        void test4() throws Exception {
            Mockito.when(jwtUtils.validateToken(Mockito.any(), Mockito.any())).thenReturn(true);
            Mockito.when(jwtUtils.isBearer(Mockito.any())).thenReturn((false));

            TestimonialDTO request = generateTestimonialDTORequest();
            TestimonialDTO expectedResponse = generateTestimonialDTOResponse();

            Mockito.when(testimonialService.save(Mockito.any())).thenReturn(expectedResponse);

            mockMvc.perform(MockMvcRequestBuilders.post("/testimonials")
                            .content(jsonMapper.writeValueAsString(request))
                            .contentType(MediaType.APPLICATION_JSON)
                    )
                    .andExpect(MockMvcResultMatchers.status().isForbidden())
                    .andExpect(MockMvcResultMatchers.content().string(Matchers.not(Matchers.containsString("Testimonial Test"))))
                    .andDo(MockMvcResultHandlers.print());

            Mockito.verify(testimonialService, Mockito.never()).save(Mockito.any());
        }

        @Test
        @DisplayName("Token not provided")
        void test5() throws Exception {
            Mockito.when(jwtUtils.isBearer(Mockito.any())).thenReturn(false);

            TestimonialDTO request = generateTestimonialDTORequest();
            TestimonialDTO expectedResponse = generateTestimonialDTOResponse();

            Mockito.when(testimonialService.save(Mockito.any())).thenReturn(expectedResponse);

            mockMvc.perform(MockMvcRequestBuilders.post("/testimonials")
                            .content(jsonMapper.writeValueAsString(request))
                            .contentType(MediaType.APPLICATION_JSON)
                    )
                    .andExpect(MockMvcResultMatchers.status().isForbidden())
                    .andExpect(MockMvcResultMatchers.content().string(Matchers.not(Matchers.containsString("Testimonial Test"))))
                    .andDo(MockMvcResultHandlers.print());

            Mockito.verify(testimonialService, Mockito.never()).save(Mockito.any());
        }

        @ParameterizedTest
        @MethodSource("com.alkemy.ong.controller.TestimonialControllerTest#generateRequestMissingMandatoryAttributes")
        @DisplayName("MissingAttributes")
        @WithMockUser(username = "mock.user@mockmail.mock", authorities = "ROLE_ADMIN")
        void test6(TestimonialDTO requestWithMissingAttribute) throws Exception {

            TestimonialDTO response = TestimonialControllerTest.generateTestimonialDTOResponse();
            Mockito.when(testimonialService.save(Mockito.any())).thenReturn(response);

            mockMvc.perform(MockMvcRequestBuilders.post("/testimonials")
                            .content(jsonMapper.writeValueAsString(requestWithMissingAttribute))
                            .contentType(MediaType.APPLICATION_JSON)
                    )
                    .andExpect(MockMvcResultMatchers.status().isBadRequest())
                    .andExpect(MockMvcResultMatchers.content().string(Matchers.not(Matchers.containsString("Somos"))))
                    .andDo(MockMvcResultHandlers.print());

            Mockito.verify(testimonialService, Mockito.never()).save(Mockito.any());
        }
    }

    @Nested
    class updateTestimonialTest {

        @Test
        @DisplayName("Valid case")
        @WithMockUser(username = "mock.user@mockmail.mock", authorities = "ROLE_ADMIN")
        void update() throws Exception {
            String id = "1707ID";
            TestimonialDTO request = generateTestimonialDTORequest();
            TestimonialDTO expectedResponse = generateTestimonialDTOResponse();

            Mockito.when(testimonialService.update(Mockito.any(), Mockito.any())).thenReturn(expectedResponse);

            mockMvc.perform(MockMvcRequestBuilders.put("/testimonials/{id}", id)
                            .content(jsonMapper.writeValueAsString(request))
                            .contentType(MediaType.APPLICATION_JSON)
                    )
                    .andExpect(MockMvcResultMatchers.status().isCreated())
                    .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                    .andExpect(MockMvcResultMatchers.content().json(jsonMapper.writeValueAsString(expectedResponse)))
                    .andDo(MockMvcResultHandlers.print());
            Mockito.verify(testimonialService).update(Mockito.any(), Mockito.any());
        }

        @Test
        @DisplayName("Non-existing ID")
        @WithMockUser(username = "mock.user@mockmail.mock", authorities = "ROLE_ADMIN")
        void test2() throws Exception {
            String id = "1707ID";
            TestimonialDTO request = generateTestimonialDTORequest();

            Mockito.when(testimonialService.update(Mockito.any(), Mockito.any())).thenThrow(new ParamNotFound("Error: Testimonial with id" + id + " was not found"));

            mockMvc.perform(MockMvcRequestBuilders.put("/testimonials/{id}", id)
                            .content(jsonMapper.writeValueAsString(request))
                            .contentType(MediaType.APPLICATION_JSON)
                    )
                    .andExpect(MockMvcResultMatchers.status().isBadRequest())
                    .andDo(MockMvcResultHandlers.print());

            Mockito.verify(testimonialService).update(Mockito.any(), Mockito.any());
        }

        @Test
        @DisplayName("Invalid role")
        @WithMockUser(username = "mock.user@mockmail.mock", authorities = "ROLE_USER")
        void test4() throws Exception {
            String id = "1707ID";
            TestimonialDTO request = generateTestimonialDTORequest();
            TestimonialDTO expectedResponse = generateTestimonialDTOResponse();

            Mockito.when(testimonialService.update(Mockito.any(), Mockito.any())).thenReturn(expectedResponse);


            mockMvc.perform(MockMvcRequestBuilders.put("/testimonials/{id}", id)
                            .content(jsonMapper.writeValueAsString(request))
                            .contentType(MediaType.APPLICATION_JSON)
                    )
                    .andExpect(MockMvcResultMatchers.status().isForbidden())
                    .andDo(MockMvcResultHandlers.print());

            Mockito.verify(testimonialService, Mockito.never()).update(Mockito.any(), Mockito.any());
        }

        @Test
        @DisplayName("Token not valid")
        void test5() throws Exception {
            Mockito.when(jwtUtils.isBearer(Mockito.any())).thenReturn(false);
            Mockito.when(jwtUtils.validateToken(Mockito.any(), Mockito.any())).thenReturn(true);

            String id = "1707ID";
            TestimonialDTO request = generateTestimonialDTORequest();
            TestimonialDTO expectedResponse = generateTestimonialDTOResponse();


            Mockito.when(testimonialService.update(Mockito.any(), Mockito.any())).thenReturn(expectedResponse);

            mockMvc.perform(MockMvcRequestBuilders.put("/testimonials/{id}", id)
                            .content(jsonMapper.writeValueAsString(request))
                            .contentType(MediaType.APPLICATION_JSON)
                    )
                    .andExpect(MockMvcResultMatchers.status().isForbidden())
                    .andDo(MockMvcResultHandlers.print());

            Mockito.verify(testimonialService, Mockito.never()).update(Mockito.any(), Mockito.any());
        }

        @Test
        @DisplayName("Token not provided")
        void test6() throws Exception {
            Mockito.when(jwtUtils.isBearer(Mockito.any())).thenReturn(false);
            String id = "1707ID";
            TestimonialDTO request = generateTestimonialDTORequest();
            TestimonialDTO expectedResponse = generateTestimonialDTOResponse();


            Mockito.when(testimonialService.update(Mockito.any(), Mockito.any())).thenReturn(expectedResponse);

            mockMvc.perform(MockMvcRequestBuilders.put("/testimonials/{id}", id)
                            .content(jsonMapper.writeValueAsString(request))
                            .contentType(MediaType.APPLICATION_JSON)
                    )
                    .andExpect(MockMvcResultMatchers.status().isForbidden())
                    .andDo(MockMvcResultHandlers.print());

            Mockito.verify(testimonialService, Mockito.never()).update(Mockito.any(), Mockito.any());
        }

        @ParameterizedTest
        @MethodSource("com.alkemy.ong.controller.TestimonialControllerTest#generateRequestMissingMandatoryAttributes")
        @DisplayName("Missing Attributes")
        @WithMockUser(username = "mock.user@mockmail.mock", authorities = "ROLE_ADMIN")
        void test7(TestimonialDTO requestWithMissingAttribute) throws Exception {
            String id = "1707ID";
            TestimonialDTO response = TestimonialControllerTest.generateTestimonialDTOResponse();
            Mockito.when(testimonialService.update(Mockito.any(), Mockito.any())).thenReturn(response);

            mockMvc.perform(MockMvcRequestBuilders.put("/testimonials/{id}", id)
                            .content(jsonMapper.writeValueAsString(requestWithMissingAttribute))
                            .contentType(MediaType.APPLICATION_JSON)
                    )
                    .andExpect(MockMvcResultMatchers.status().isBadRequest())
                    .andDo(MockMvcResultHandlers.print());
            Mockito.verify(testimonialService, Mockito.never()).save(Mockito.any());
        }
    }

    @Nested
    class testGetAllTestimonials {
        @Test
        @DisplayName("Valid Case")
        @WithMockUser(username = "mock.user@mockmail.mock", authorities = "ROLE_USER")
        void test1() throws Exception {
            PageableResponse response = new PageableResponse();
            response.setContent(TestimonialControllerTest.generateDTOList());

            Mockito.when(testimonialService.getAll(0, 10, "id")).thenReturn(response);
            mockMvc.perform(MockMvcRequestBuilders.get("/testimonials").param("page", "0"))
                    .andExpect(MockMvcResultMatchers.status().isOk())
                    .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                    .andDo(MockMvcResultHandlers.print());
            Mockito.verify(testimonialService).getAll(0, 10, "id");
        }

        @DisplayName("PageIndexOutOfBoundsException")
        @WithMockUser(username = "mock.user@mockmail.mock", authorities = "ROLE_USER")
        @Test
        void test2() throws Exception {

            int pageNumber = -10;
            Mockito.when(testimonialService.getAll(pageNumber, 10, "id")).thenThrow(new ParamNotFound(""));

            mockMvc.perform(MockMvcRequestBuilders.get("/testimonials").param("page", String.valueOf(pageNumber)))
                    .andExpect(MockMvcResultMatchers.status().isBadRequest())
                    .andExpect(MockMvcResultMatchers.content().string(Matchers.not(Matchers.containsString("test"))))
                    .andDo(MockMvcResultHandlers.print());

            Mockito.verify(testimonialService).getAll(pageNumber, 10, "id");

        }

        @Test
        @DisplayName("No token provided")
        void test3() throws Exception {

            Mockito.when(jwtUtils.isBearer(Mockito.any())).thenReturn(false);

            PageableResponse response = new PageableResponse();
            response.setContent(TestimonialControllerTest.generateDTOList());

            Mockito.when(testimonialService.getAll(0, 10, "id")).thenReturn(response);
            mockMvc.perform(MockMvcRequestBuilders.get("/testimonials").param("page", "0"))
                    .andExpect(MockMvcResultMatchers.status().isForbidden())
                    .andDo(MockMvcResultHandlers.print());
            Mockito.verify(testimonialService, Mockito.never()).getAll(0, 10, "id");
        }

        @Test
        @DisplayName("Token not valid")
        void test4() throws Exception {

            Mockito.when(jwtUtils.isBearer(Mockito.any())).thenReturn(false);
            Mockito.when(jwtUtils.validateToken(Mockito.any(), Mockito.any())).thenReturn(true);

            PageableResponse response = new PageableResponse();
            response.setContent(TestimonialControllerTest.generateDTOList());

            Mockito.when(testimonialService.getAll(0, 10, "id")).thenReturn(response);
            mockMvc.perform(MockMvcRequestBuilders.get("/testimonials").param("page", "0"))
                    .andExpect(MockMvcResultMatchers.status().isForbidden())
                    .andDo(MockMvcResultHandlers.print());
            Mockito.verify(testimonialService, Mockito.never()).getAll(0, 10, "id");

        }
    }

    @Nested
    class deleteTestimonialTest {
        @Test
        @DisplayName("Valid case")
        @WithMockUser(username = "mock.user@mockmail.mock", authorities = "ROLE_ADMIN")
        void test1() throws Exception {
            String id = "1707d";
            String expectedResponse = "Successfully deleted testimonial with id " + "id";

            Mockito.when(testimonialService.delete(Mockito.any())).thenReturn(expectedResponse);

            mockMvc.perform(MockMvcRequestBuilders.delete("/testimonials/{id}", id))
                    .andExpect(MockMvcResultMatchers.status().isNoContent())
                    .andExpect(MockMvcResultMatchers.content().string(expectedResponse))
                    .andDo(MockMvcResultHandlers.print());

            Mockito.verify(testimonialService).delete(Mockito.any());
        }

        @Test
        @DisplayName("Non-existing ID")
        @WithMockUser(username = "mock.user@mockmail.mock", authorities = "ROLE_ADMIN")
        void test2() throws Exception {
            String id = "d1707";
            Mockito.doThrow(new ParamNotFound("")).when(testimonialService).delete(id);

            mockMvc.perform(MockMvcRequestBuilders.delete("/testimonials/{id}", id))
                    .andExpect(MockMvcResultMatchers.status().isBadRequest())
                    .andDo(MockMvcResultHandlers.print());
            Mockito.verify(testimonialService).delete(id);
        }

        @Test
        @DisplayName("Invalid user role")
        @WithMockUser(username = "mock.user@mockmail.mock", authorities = "ROLE_USER")
        void test4() throws Exception {
            String id = "22";
            String expectedResponse = "Successfully deleted testimonial with id " + "id";

            Mockito.when(testimonialService.delete(Mockito.any())).thenReturn(expectedResponse);

            mockMvc.perform(MockMvcRequestBuilders.delete("/testimonials/{id}", id))
                    .andExpect(MockMvcResultMatchers.status().isForbidden())
                    .andExpect(MockMvcResultMatchers.content().string(Matchers.not(Matchers.containsString(expectedResponse))))
                    .andDo(MockMvcResultHandlers.print());

            Mockito.verify(testimonialService, Mockito.never()).delete(Mockito.any());
        }

        @Test
        @DisplayName("No token provided")
        void test5() throws Exception {
            Mockito.when(jwtUtils.isBearer(Mockito.any())).thenReturn(false);
            String id = "22";
            String expectedResponse = "Successfully deleted testimonial with id " + "id";

            Mockito.when(testimonialService.delete(Mockito.any())).thenReturn(expectedResponse);

            mockMvc.perform(MockMvcRequestBuilders.delete("/testimonials/{id}", id))
                    .andExpect(MockMvcResultMatchers.status().isForbidden())
                    .andExpect(MockMvcResultMatchers.content().string(Matchers.not(Matchers.containsString(expectedResponse))))
                    .andDo(MockMvcResultHandlers.print());

            Mockito.verify(testimonialService, Mockito.never()).delete(Mockito.any());
        }

        @Test
        @DisplayName("Token not valid")
        void test6() throws Exception {
            Mockito.when(jwtUtils.isBearer(Mockito.any())).thenReturn(true);
            Mockito.when(jwtUtils.validateToken(Mockito.any(), Mockito.any())).thenReturn(true);

            String id = "22";
            String expectedResponse = "Successfully deleted testimonial with id " + "id";

            Mockito.when(testimonialService.delete(Mockito.any())).thenReturn(expectedResponse);

            mockMvc.perform(MockMvcRequestBuilders.delete("/testimonials/{id}", id))
                    .andExpect(MockMvcResultMatchers.status().isForbidden())
                    .andExpect(MockMvcResultMatchers.content().string(Matchers.not(Matchers.containsString(expectedResponse))))
                    .andDo(MockMvcResultHandlers.print());

            Mockito.verify(testimonialService, Mockito.never()).delete(Mockito.any());
        }
    }

    private static TestimonialDTO generateTestimonialDTORequest() {
        TestimonialDTO request = new TestimonialDTO();
        request.setName("Testimonial Test request");
        request.setContent("Hi!");
        request.setImage("https://cohorte-agosto-38d749a7.s3.amazonaws.com/picture.png");
        return request;
    }

    private static TestimonialDTO generateTestimonialDTOResponse() {
        TestimonialDTO response = new TestimonialDTO();
        response.setName("Testimonial DTO tests");
        response.setContent("Hi!");
        response.setImage("https://cohorte-agosto-a192d78b.s3.amazonaws.com/1657325315906-tutorias.jpg");
        return response;
    }

    private static List<TestimonialDTO> generateRequestMissingMandatoryAttributes() {
        List<TestimonialDTO> requests = new ArrayList<>();
        TestimonialDTO singleRequest;

        // CASE 1: Missing name
        singleRequest = generateTestimonialDTORequest();
        singleRequest.setName(null);
        requests.add(singleRequest);

        // CASE 2: Missing content
        singleRequest = generateTestimonialDTORequest();
        singleRequest.setContent(null);
        requests.add(singleRequest);

        return requests;
    }

    static List<TestimonialDTO> generateDTOList() {
        TestimonialDTO dto = new TestimonialDTO();
        dto.setName("Testimonial DTO tests");
        dto.setContent("Hi!");
        dto.setImage("https://cohorte-agosto-a192d78b.s3.amazonaws.com/1657325315906-tutorias.jpg");
        return Collections.singletonList(dto);
    }

    static Stream<Integer> validRange() {
        return IntStream.range(0, 10).boxed();
    }
}