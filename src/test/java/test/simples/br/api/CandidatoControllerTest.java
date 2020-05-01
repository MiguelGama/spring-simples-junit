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

import test.simples.br.controller.CandidatoController;
import test.simples.br.model.Candidato;
import test.simples.br.service.CandidatoService;

/*
public class CandidatoControllerTest {

}
*/

@RunWith(SpringRunner.class)
@WebMvcTest(CandidatoController.class)
public class CandidatoControllerTest {

    @Autowired 
    MockMvc mvc;
    @MockBean 
    CandidatoService candidatoService;

    @Test
    public void add_candidato_test() throws Exception {

        Candidato candidato = new Candidato();
        candidato.setNome("Fulano 485968");
        candidato.setIdCargo(1);        

        mvc.perform(post("/candidato/save")
            .contentType(MediaType.APPLICATION_JSON)
            .content(asJsonString(candidato)))
            .andExpect(status().isOk());
    }
    
    @Test
    public void update_candidato_test() throws Exception {

        Candidato candidato = new Candidato();
        candidato.setId(1L);
        candidato.setNome("Fulano 1 alterado");
        candidato.setIdCargo(2);
        
        mvc.perform(put("/candidato/{id}", candidato.getId())
            .contentType(MediaType.APPLICATION_JSON)            
            .content(asJsonString(candidato)))        
            .andExpect(status().isOk());
    }
    
    @Test
    public void delete_candidato_test() throws Exception {
    	this.mvc.perform(MockMvcRequestBuilders
                .delete("/candidato/{id}", 1L))
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
