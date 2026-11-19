package com.example.prueba_seguridad;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class InventarioControllerTest {

    @Autowired
    private MockMvc mockMvc;


    @Test
    @WithMockUser(roles = "VENDEDOR")
    public void vendedorTest() throws Exception{
        mockMvc.perform(get("/inventario/eliminar"))
                .andExpect(status().isForbidden()); // 403
    }

    @Test
    @WithMockUser(roles = "BODEGA")
    public void bodegaTest() throws Exception{
        mockMvc.perform(get("/inventario/modificar"))
                .andExpect(status().isOk()); // 200
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    public void adminTest() throws Exception{
        mockMvc.perform(get("/inventario/eliminar"))
                .andExpect(status().isOk()); // 200
    }

    @Test
    public void vacioTest() throws Exception{
        mockMvc.perform(get("/inventario/ver"))
                .andExpect(status().isUnauthorized()); // 401
    }
}
