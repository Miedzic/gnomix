package com.example.gnomix.controller;

import com.example.gnomix.domain.dao.Gender;
import com.example.gnomix.domain.dao.Guest;
import com.example.gnomix.domain.dto.GuestDto;
import com.example.gnomix.service.GuestService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

import java.time.LocalDate;
import java.util.Arrays;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(GuestController.class)
public class GuestControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private GuestService guestService;

    @Test
    public void testBasicExample() throws Exception {
        Guest guest = new Guest("Mateusz", "Dziedzic", LocalDate.of(1998, 4, 28), Gender.MALE);

        Mockito.when(guestService.findAll()).thenReturn(Arrays.asList(guest));
        mockMvc.perform(get("/guests"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("guests"))
                .andExpect(view().name("guests"))
                .andExpect(content().string(containsString("1998-04-28")));
    }

    @Test
    public void handlePost() throws Exception {
        String postContent = "firstName=Pawel&lastName=Cwik&dateOfBirth=2021-09-15&gender=FEMALE";
        MockHttpServletRequestBuilder request =
                post("/guests")
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED_VALUE)
                        .content(postContent);
        mockMvc.perform(request)
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("guests"));
        GuestDto dto = new GuestDto(
                "Pawel",
                "Cwik",
                LocalDate.parse("2021-09-15"),
                Gender.FEMALE
        );
        Mockito.verify(guestService, Mockito.times(1)).createNewGuest(dto);
    }
    @Test
    public void handleDelete() throws Exception {
        MockHttpServletRequestBuilder request =
                get("/guests/delete/21");
        mockMvc.perform(request)
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/guests"));
        Mockito.verify(guestService, Mockito.times(1)).removeById(21L);
    }
    @Test
    public void handleShowEditForm() throws Exception {
        MockHttpServletRequestBuilder request =
                get("/guests/update/21");
        Guest guest = new Guest("Paweł", "Cwik", LocalDate.of(1986, 11, 13), Gender.MALE);
        Mockito.when(guestService.getById(21)).thenReturn(guest);
        mockMvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("guest"))
                .andExpect(view().name("updateGuest"));
        Mockito.verify(guestService, Mockito.times(1)).getById(21L);
    }
}
