package com.itpu.internship2.digital_cinema.integration;

import com.itpu.internship2.digital_cinema.controller.SeatController;
import com.itpu.internship2.digital_cinema.controller.SessionSeatController;
import com.itpu.internship2.digital_cinema.service.SeatService;
import com.itpu.internship2.digital_cinema.service.SessionSeatService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest({SeatController.class, SessionSeatController.class})
public class SeatIT {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private SeatService seatService;

    @MockitoBean
    private SessionSeatService sessionSeatService;

    @Test
    void shouldReturnSeatsWithGaps() throws Exception {
        when(seatService.getAllPlacesByHallId(101L)).thenReturn(Collections.emptyList());
        mockMvc.perform(get("/seats/hall/101"))
               .andExpect(status().isOk());
    }

    @Test
    void shouldReturnSessionSeats() throws Exception {
        when(sessionSeatService.getAllBySessionId(101L)).thenReturn(Collections.emptyList());
        mockMvc.perform(get("/session-seats/session/101"))
               .andExpect(status().isOk());
    }
}
