package org.example.moonrover;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.moonrover.model.Coordinates;
import org.example.moonrover.model.Direction;
import org.example.moonrover.model.Rotation;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
public class MoonRoverRotateAndMoveIT {

    @Autowired
    private MockMvc mockMvc;

    ObjectMapper mapper = new ObjectMapper();

    @Test
    void testRotateAndMoveRoverNorth() throws Exception {

        Coordinates coordinatesPlace = new Coordinates(3,3, Direction.South);
        Rotation leftDirection = Rotation.Left;
        Coordinates endPlace = new Coordinates(3,4, Direction.North);

        mockMvc.perform( MockMvcRequestBuilders
                        .post("/api/rover/place")
                        .content( mapper.writeValueAsString(coordinatesPlace))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        mockMvc.perform( MockMvcRequestBuilders
                        .post("/api/rover/turn")
                        .content( mapper.writeValueAsString(leftDirection))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        mockMvc.perform( MockMvcRequestBuilders
                        .post("/api/rover/turn")
                        .content( mapper.writeValueAsString(leftDirection))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        mockMvc.perform( MockMvcRequestBuilders
                        .post("/api/rover/move")
                        .content( mapper.writeValueAsString(coordinatesPlace))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        mockMvc.perform( MockMvcRequestBuilders
                        .get("/api/rover/report")
                        .content( mapper.writeValueAsString(coordinatesPlace))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json(mapper.writeValueAsString(endPlace)));

        mockMvc.perform( MockMvcRequestBuilders
                        .post("/api/rover/turn")
                        .content( mapper.writeValueAsString(leftDirection))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        mockMvc.perform( MockMvcRequestBuilders
                        .post("/api/rover/turn")
                        .content( mapper.writeValueAsString(leftDirection))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        mockMvc.perform( MockMvcRequestBuilders
                        .post("/api/rover/move")
                        .content( mapper.writeValueAsString(coordinatesPlace))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        mockMvc.perform( MockMvcRequestBuilders
                        .get("/api/rover/report")
                        .content( mapper.writeValueAsString(coordinatesPlace))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json(mapper.writeValueAsString(coordinatesPlace)));
    }
}
