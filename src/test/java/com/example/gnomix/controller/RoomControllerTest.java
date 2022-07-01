package com.example.gnomix.controller;

import com.example.gnomix.domain.dao.BedType;
import com.example.gnomix.domain.dao.Room;
import com.example.gnomix.service.GuestService;
import com.example.gnomix.service.RoomService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@WebMvcTest(RoomController.class)
public class RoomControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private RoomService roomService;
     @Test
  public void basic() throws Exception {
        Room r = new Room("1408", Arrays.asList(BedType.DOUBLE));
        Mockito.when(roomService.findAll()).thenReturn(Arrays.asList(r));
        mockMvc.perform(get("/rooms"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("rooms"))
                .andExpect(view().name("rooms"))
                .andExpect(content().string(containsString("1408")));
    }
    @Test
    public void handlePost() throws Exception {
        String postContent = "number=139&bedsDesc=2%2B1";
        MockHttpServletRequestBuilder request =
                post("/rooms/create")
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED_VALUE)
                        .content(postContent);
        mockMvc.perform(request)
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/rooms"));
        Mockito.verify(roomService, Mockito.times(1)).createNewRoom("139", "2+1");
    }
    @Test
    public void handleDelete() throws Exception {
        MockHttpServletRequestBuilder request =
                get("/rooms/delete/21");
        mockMvc.perform(request)
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/rooms"));
        Mockito.verify(roomService, Mockito.times(1)).removeById(21);
    }
    @Test
    public void handleShowEditForm() throws Exception {
        MockHttpServletRequestBuilder request =
                get("/rooms/edit/21");
        Room r = new Room("1408", Arrays.asList(BedType.DOUBLE, BedType.SINGLE));
        Mockito.when(roomService.findById(21)).thenReturn(r);
        mockMvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("room"))
                .andExpect(model().attribute("bedsAsStr", "2+1"))
                .andExpect(view().name("editRoom"));
        Mockito.verify(roomService, Mockito.times(1)).findById(21);
    }
    @Test
    public void handleUpdate() throws Exception {
        String postContent = "id=21&number=139&bedsDesc=2%2B1";
        MockHttpServletRequestBuilder request =
                post("/rooms/edit")
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED_VALUE)
                        .content(postContent);
        mockMvc.perform(request)
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/rooms"));
        Mockito.verify(roomService, Mockito.times(1)).update(21, "139", "2+1");
    }
}
