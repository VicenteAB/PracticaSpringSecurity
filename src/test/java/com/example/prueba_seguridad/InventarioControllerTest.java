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
    public void vendedorNoPuedeEliminarInventario() throws Exception{
        mockMvc.perform(get("/inventario/eliminar"))
                .andExpect(status().isForbidden()); // 403
    }

    @Test
    @WithMockUser(roles = "BODEGA")
    public void bodegaPuedeModificarInventario() throws Exception{
        mockMvc.perform(get("/inventario/modificar"))
                .andExpect(status().isOk()); // 200
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    public void adminPuedeEliminarInventario() throws Exception{
        mockMvc.perform(get("/inventario/eliminar"))
                .andExpect(status().isOk()); // 200
    }

    @Test
    public void usuarioSinLoginNoPuedeVerInventario() throws Exception{
        mockMvc.perform(get("/inventario/ver"))
                .andExpect(status().isUnauthorized()); // 401
    }

    //Test para parte 5
    @Test
    @WithMockUser(roles = "VENDEDOR")
    public void vendedorNoPuedeModificarInventario() throws Exception{
        mockMvc.perform(get("/inventario/modificar"))
                .andExpect(status().isForbidden());
    }

    @Test
    @WithMockUser(roles = "BODEGA")
    public void bodegaNoPuedeEliminarInventario() throws Exception{
        mockMvc.perform(get("/inventario/eliminar"))
                .andExpect(status().isForbidden());
    }

    @Test
    @WithMockUser(roles = "VENDEDOR")
    public void vendedorPuedeVerInventario() throws Exception{
        mockMvc.perform(get("/inventario/ver"))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    public void adminPuedeAccederAlPanel() throws Exception{
        mockMvc.perform(get("/admin/panel"))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(roles = "VENDEDOR")
    public void vendedorNoPuedeAccederAlPanel() throws Exception{
        mockMvc.perform(get("/admin/panel"))
                .andExpect(status().isForbidden());
    }
}
