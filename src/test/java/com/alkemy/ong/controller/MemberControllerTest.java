package com.alkemy.ong.controller;

import com.alkemy.ong.dto.MemberDTO;
import com.alkemy.ong.dto.PageableResponse;
import com.alkemy.ong.exception.ParamNotFound;
import com.alkemy.ong.security.service.JwtUtils;
import com.alkemy.ong.security.service.UserDetailsCustomService;
import com.alkemy.ong.service.MemberService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.*;
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

import java.util.Collections;
import java.util.List;


@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@WebMvcTest(MemberController.class)
@Import({SecurityConfiguration.class, BCryptPasswordEncoder.class})
class MemberControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    JwtUtils jwtService;

    @MockBean
    MemberService memberService;

    ObjectMapper jsonMapper;

    @BeforeAll
    public void settings() {
        this.jsonMapper = new ObjectMapper();
    }

    static List<MemberDTO> generateDTOList() {
        MemberDTO dto = new MemberDTO();
        dto.setImage("https://cohorte-junio-a192d78b.s3.amazonaws.com/1657462814446-ezeTest.txt");
        dto.setDescription("TestDescription");
        dto.setName("TestName");
        dto.setFacebookUrl("TestFacebookUrl");
        dto.setInstagramUrl("TestInstagramUrl");
        dto.setLinkedinUrl("TestLinkedinUrl");

        return Collections.singletonList(dto);
    }

    static List<MemberDTO> generateRequestWithMissingMandatoryAttribute() {
        List<MemberDTO> list = generateDTOList();
        list.get(0).setName(null);

        return list;
    }

    @Nested
    class getAll {
        @Test
        @DisplayName("Valid Case")
        @WithMockUser(username = "mock.user@mockmail.mock", authorities = "ROLE_USER")
        void getAllMembers() throws Exception {
            PageableResponse response = new PageableResponse();
            response.setContent(MemberControllerTest.generateDTOList());

            Mockito.when(memberService.getAllMembers(0, 10, "id")).thenReturn(response);
            mockMvc.perform(MockMvcRequestBuilders.get("/members").param("page", "0"))
                    .andExpect(MockMvcResultMatchers.status().isOk())
                    .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                    .andDo(MockMvcResultHandlers.print());
            Mockito.verify(memberService).getAllMembers(0, 10, "id");
        }

        @Test
        @DisplayName("No Token Provided")
        void getAllMembersNoTokenProvided() throws Exception {

            Mockito.when(jwtService.isBearer(Mockito.any())).thenReturn(false);

            PageableResponse response = new PageableResponse();
            response.setContent(MemberControllerTest.generateDTOList());

            mockMvc.perform(MockMvcRequestBuilders.get("/members").param("page", "0"))
                    .andExpect(MockMvcResultMatchers.status().isForbidden())
                    .andExpect(MockMvcResultMatchers.content().string(Matchers.not(Matchers.containsString("TestName"))))
                    .andDo(MockMvcResultHandlers.print());

            Mockito.verify(memberService, Mockito.never()).getAllMembers(0, 10, "id");
        }

        @Test
        @DisplayName("Token not Valid")
        void getAllMembersTokenNotValid() throws Exception {

            Mockito.when(jwtService.isBearer(Mockito.any())).thenReturn(true);
            Mockito.when(jwtService.validateToken(Mockito.any(), Mockito.any())).thenReturn(false);

            PageableResponse response = new PageableResponse();
            List<MemberDTO> memberResultList = MemberControllerTest.generateDTOList();
            response.setContent(memberResultList);

            Mockito.when(memberService.getAllMembers(0, 10, "id")).thenReturn(response);

            mockMvc.perform(MockMvcRequestBuilders.get("/member").param("page", "0"))
                    .andExpect(MockMvcResultMatchers.status().isForbidden())
                    .andExpect(MockMvcResultMatchers.content().string(Matchers.not(Matchers.containsString("TestName"))))
                    .andDo(MockMvcResultHandlers.print());

            Mockito.verify(memberService, Mockito.never()).getAllMembers(0, 10, "id");
        }
    }

    @Nested
    class saveMember {
        @Test
        @DisplayName("Valid Case")
        @WithMockUser(username = "mock.user@mockmail.mock", authorities = "ROLE_USER")
        void test_1() throws Exception {

            MemberDTO member = MemberControllerTest.generateDTOList().get(0);

            Mockito.when(memberService.saveMember(Mockito.any())).thenReturn(member);

            mockMvc.perform(MockMvcRequestBuilders.post("/members")
                            .content(jsonMapper.writeValueAsString(member))
                            .contentType(MediaType.APPLICATION_JSON))

                    .andExpect(MockMvcResultMatchers.status().isOk())
                    .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                    .andExpect(MockMvcResultMatchers.content().json(jsonMapper.writeValueAsString(member)))
                    .andDo(MockMvcResultHandlers.print());

            Mockito.verify(memberService).saveMember(Mockito.any());
        }

        @Test
        @DisplayName("No Token Provided")
        void test_2() throws Exception {

            Mockito.when(jwtService.isBearer(Mockito.any())).thenReturn(false);

            MemberDTO member = MemberControllerTest.generateDTOList().get(0);

            Mockito.when(memberService.saveMember(Mockito.any())).thenReturn(member);

            mockMvc.perform(MockMvcRequestBuilders.post("/members")
                            .content(jsonMapper.writeValueAsString(member))
                            .contentType(MediaType.APPLICATION_JSON))

                    .andExpect(MockMvcResultMatchers.status().isForbidden())
                    .andExpect(MockMvcResultMatchers.content().string(Matchers.not(Matchers.containsString("TestName"))))
                    .andDo(MockMvcResultHandlers.print());

            Mockito.verify(memberService, Mockito.never()).saveMember(Mockito.any());

        }

        @Test
        @DisplayName("Token not valid")
        void test_3() throws Exception {

            Mockito.when(jwtService.isBearer(Mockito.any())).thenReturn(true);
            Mockito.when(jwtService.validateToken(Mockito.any(), Mockito.any())).thenReturn(false);

            MemberDTO member = MemberControllerTest.generateDTOList().get(0);

            Mockito.when(memberService.saveMember(Mockito.any())).thenReturn(member);

            mockMvc.perform(MockMvcRequestBuilders.post("/members")
                            .content(jsonMapper.writeValueAsString(member))
                            .contentType(MediaType.APPLICATION_JSON))

                    .andExpect(MockMvcResultMatchers.status().isForbidden())
                    .andExpect(MockMvcResultMatchers.content().string(Matchers.not(Matchers.containsString("TestName"))))
                    .andDo(MockMvcResultHandlers.print());

            Mockito.verify(memberService, Mockito.never()).saveMember(Mockito.any());
        }

        @ParameterizedTest
        @MethodSource("com.alkemy.ong.controller.MemberControllerTest#generateRequestWithMissingMandatoryAttribute")
        @DisplayName("Mandatory attr missing")
        @WithMockUser(username = "mock.user@mockmail.mock", authorities = "ROLE_USER")
        void test_4(MemberDTO requestMissingAttr) throws Exception {
            MemberDTO response = MemberControllerTest.generateDTOList().get(0);
            Mockito.when(memberService.saveMember(Mockito.any())).thenReturn(response);

            mockMvc.perform(MockMvcRequestBuilders.post("/members")
                            .content(jsonMapper.writeValueAsString(requestMissingAttr))
                            .contentType(MediaType.APPLICATION_JSON))

                    .andExpect(MockMvcResultMatchers.status().isBadRequest())
                    .andExpect(MockMvcResultMatchers.content().string(Matchers.not(Matchers.containsString("TestName"))))
                    .andDo(MockMvcResultHandlers.print());

            Mockito.verify(memberService, Mockito.never()).saveMember(Mockito.any());
        }
    }

    @Nested
    class deleteMember {
        @Test
        @DisplayName("Valid case")
        @WithMockUser(username = "mock.user@mockmail.mock", authorities = "ROLE_ADMIN")
        void test1() throws Exception {
            String id = "1707d";
            String expectedResponse = "Successfully deleted testimonial with id " + "id";

            Mockito.when(memberService.deleteMember(Mockito.any())).thenReturn(expectedResponse);

            mockMvc.perform(MockMvcRequestBuilders.delete("/members/{id}", id))
                    .andExpect(MockMvcResultMatchers.status().isNoContent())
                    .andExpect(MockMvcResultMatchers.content().string(expectedResponse))
                    .andDo(MockMvcResultHandlers.print());

            Mockito.verify(memberService).deleteMember(Mockito.any());
        }

        @Test
        @DisplayName("No Token provided")
        void test_2() throws Exception {

            Mockito.when(jwtService.isBearer(Mockito.any())).thenReturn(false);

            String id = "1707d";
            String result = "Successfully deleted member with id 123";

            Mockito.when(memberService.deleteMember(Mockito.any())).thenReturn(result);

            mockMvc.perform(MockMvcRequestBuilders.delete("/members/{id}", id))
                    .andExpect(MockMvcResultMatchers.status().isForbidden())
                    .andDo(MockMvcResultHandlers.print());

            Mockito.verify(memberService, Mockito.never()).deleteMember(Mockito.any());
        }

        @Test
        @DisplayName("Invalid Token")
        void test_3() throws Exception {

            Mockito.when(jwtService.isBearer(Mockito.any())).thenReturn(true);
            Mockito.when(jwtService.validateToken(Mockito.any(), Mockito.any())).thenReturn(false);

            String id = "1707d";
            String result = "Successfully deleted member with id 123";

            Mockito.when(memberService.deleteMember(Mockito.any())).thenReturn(result);

            mockMvc.perform(MockMvcRequestBuilders.delete("/members/{id}", id))
                    .andExpect(MockMvcResultMatchers.status().isForbidden())
                    .andDo(MockMvcResultHandlers.print());

            Mockito.verify(memberService, Mockito.never()).deleteMember(Mockito.any());

        }

        @Test
        @DisplayName("Non-existing ID")
        @WithMockUser(username = "mock.user@mockmail.mock", authorities = "ROLE_ADMIN")
        void test2() throws Exception {
            String id = "d1707";
            Mockito.doThrow(new ParamNotFound("")).when(memberService).deleteMember(id);

            mockMvc.perform(MockMvcRequestBuilders.delete("/members/{id}", id))
                    .andExpect(MockMvcResultMatchers.status().isBadRequest())
                    .andDo(MockMvcResultHandlers.print());
            Mockito.verify(memberService).deleteMember(id);
        }

        @Test
        @DisplayName("Invalid user role")
        @WithMockUser(username = "mock.user@mockmail.mock", authorities = "ROLE_USER")
        void test_6() throws Exception {

            String id = "1707d";
            String result = "Successfully deleted member with id 123";

            Mockito.when(memberService.deleteMember(Mockito.any())).thenReturn(result);

            mockMvc.perform(MockMvcRequestBuilders.delete("/members/{id}", id))
                    .andExpect(MockMvcResultMatchers.status().isForbidden())
                    .andDo(MockMvcResultHandlers.print());

            Mockito.verify(memberService, Mockito.never()).deleteMember(Mockito.any());
        }
    }

    @Nested
    class updateMember {

        @Test
        @DisplayName("Valid Case")
        @WithMockUser(username = "mock.user@mockmail.mock", authorities = "ROLE_USER")
        void test_1() throws Exception {
            String id = "123";
            MemberDTO request = MemberControllerTest.generateDTOList().get(0);

            Mockito.when(memberService.updateMember(Mockito.any(), Mockito.any())).thenReturn(request);

            mockMvc.perform(MockMvcRequestBuilders.put("/members/{id}", id)
                            .content(jsonMapper.writeValueAsString(request))
                            .contentType(MediaType.APPLICATION_JSON))

                    .andExpect(MockMvcResultMatchers.status().isOk())
                    .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
                    .andExpect(MockMvcResultMatchers.content().json(jsonMapper.writeValueAsString(request)))
                    .andDo(MockMvcResultHandlers.print());

            Mockito.verify(memberService).updateMember(Mockito.any(), Mockito.any());
        }

        @Test
        @DisplayName("No Token provided")
        void test_2() throws Exception {

            String id = "123";
            MemberDTO response = MemberControllerTest.generateDTOList().get(0);

            Mockito.when(jwtService.isBearer(Mockito.any())).thenReturn(false);

            Mockito.when(memberService.updateMember(Mockito.any(), Mockito.any())).thenReturn(response);

            mockMvc.perform(MockMvcRequestBuilders.put("/members/{id}", id)
                            .content(jsonMapper.writeValueAsString(response))
                            .contentType(MediaType.APPLICATION_JSON))

                    .andExpect(MockMvcResultMatchers.status().isForbidden())
                    .andDo(MockMvcResultHandlers.print());

            Mockito.verify(memberService, Mockito.never()).updateMember(Mockito.any(), Mockito.any());
        }

        @Test
        @DisplayName("Invalid Token")
        void test_3() throws Exception {

            String id = "123";
            MemberDTO response = MemberControllerTest.generateDTOList().get(0);

            Mockito.when(jwtService.isBearer(Mockito.any())).thenReturn(true);
            Mockito.when(jwtService.validateToken(Mockito.any(), Mockito.any())).thenReturn(false);

            Mockito.when(memberService.updateMember(Mockito.any(), Mockito.any())).thenReturn(response);

            mockMvc.perform(MockMvcRequestBuilders.put("/members/{id}", id)
                            .content(jsonMapper.writeValueAsString(response))
                            .contentType(MediaType.APPLICATION_JSON))

                    .andExpect(MockMvcResultMatchers.status().isForbidden())
                    .andDo(MockMvcResultHandlers.print());

            Mockito.verify(memberService, Mockito.never()).updateMember(Mockito.any(), Mockito.any());
        }

        @Test
        @DisplayName("Invalid Member ID")
        @WithMockUser(username = "mock.user@mockmail.mock", authorities = "ROLE_USER")
        void test_4() throws Exception {

            String id = "123";

            MemberDTO response = MemberControllerTest.generateDTOList().get(0);

            Mockito.when(memberService.updateMember(Mockito.any(), Mockito.any())).thenThrow(new ParamNotFound("" +
                    "Member with the provided ID was not found over the system"));

            mockMvc.perform(MockMvcRequestBuilders.put("/members/{id}", id)
                            .content(jsonMapper.writeValueAsString(response))
                            .contentType(MediaType.APPLICATION_JSON))

                    .andExpect(MockMvcResultMatchers.status().isBadRequest())
                    .andDo(MockMvcResultHandlers.print());

            Mockito.verify(memberService).updateMember(Mockito.any(), Mockito.any());
        }

        @Test
        @DisplayName("Member name already present")
        @WithMockUser(username = "mock.user@mockmail.mock", authorities = "ROLE_USER")
        void test_5() throws Exception {

            String id = "123";
            MemberDTO response = MemberControllerTest.generateDTOList().get(0);

            Mockito.when(memberService.updateMember(Mockito.any(), Mockito.any())).thenThrow(new ParamNotFound("Name format invalid"));

            mockMvc.perform(MockMvcRequestBuilders.put("/members/{id}", id)
                            .content(jsonMapper.writeValueAsString(response))
                            .contentType(MediaType.APPLICATION_JSON))

                    .andExpect(MockMvcResultMatchers.status().isBadRequest())
                    .andDo(MockMvcResultHandlers.print());

            Mockito.verify(memberService).updateMember(Mockito.any(), Mockito.any());
        }

        @ParameterizedTest
        @MethodSource("com.alkemy.ong.controller.MemberControllerTest#generateRequestWithMissingMandatoryAttribute")
        @DisplayName("Mandatory attr missing")
        @WithMockUser(username = "mock.user@mockmail.mock", authorities = "ROLE_USER")
        void test_6(MemberDTO request) throws Exception {

            String id = "123";
            MemberDTO response = MemberControllerTest.generateDTOList().get(0);

            Mockito.when(memberService.updateMember(Mockito.any(), Mockito.any())).thenReturn(response);

            mockMvc.perform(MockMvcRequestBuilders.put("/members/{id}", id)
                            .content(jsonMapper.writeValueAsString(request))
                            .contentType(MediaType.APPLICATION_JSON))

                    .andExpect(MockMvcResultMatchers.status().isBadRequest())
                    .andExpect(MockMvcResultMatchers.content().string(Matchers.not(Matchers.containsString("TestName"))))
                    .andDo(MockMvcResultHandlers.print());

            Mockito.verify(memberService, Mockito.never()).updateMember(Mockito.any(), Mockito.any());
        }
    }
}