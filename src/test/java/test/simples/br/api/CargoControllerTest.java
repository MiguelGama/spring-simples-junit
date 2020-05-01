package test.simples.br.api;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;

import test.simples.br.controller.CargoController;
import test.simples.br.model.Cargo;
import test.simples.br.service.CargoService;

/*
public class CargoControllerTest {

}
*/

@RunWith(SpringRunner.class)
@WebMvcTest(CargoController.class)
public class CargoControllerTest {

    @Autowired 
    MockMvc mvc;
    @MockBean 
    CargoService cargoService;

    @Test
    public void add_cargo_test() throws Exception {

        Cargo cargo = new Cargo();
        cargo.setNome("Diretor Splente");      

        mvc.perform(post("/cargo/save")
            .contentType(MediaType.APPLICATION_JSON)
            .content(asJsonString(cargo)))
            .andExpect(status().isOk());
    }
    
    @Test
    public void update_cargo_test() throws Exception {

        Cargo cargo = new Cargo();
        cargo.setId(1L);
        cargo.setNome("FDiretor Splente");
        
        mvc.perform(put("/cargo/{id}", cargo.getId())
            .contentType(MediaType.APPLICATION_JSON)            
            .content(asJsonString(cargo)))        
            .andExpect(status().isOk());
    }
    
    @Test
    public void delete_cargo_test() throws Exception {
    	this.mvc.perform(MockMvcRequestBuilders
                .delete("/cargo/{id}", 1L))
                .andExpect(status().isOk());
    }
    
    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
