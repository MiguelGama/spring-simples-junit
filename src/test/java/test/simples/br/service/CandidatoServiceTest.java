package test.simples.br.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.repository.CrudRepository;
import org.springframework.test.context.junit4.SpringRunner;

import test.simples.br.SalesManager;
import test.simples.br.model.Candidato;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = SalesManager.class)
public class CandidatoServiceTest{
	@Autowired
	private CandidatoService candidatoService;
	
	@Test
	public void get_all_candidato() {
		assertNotNull(StreamSupport.stream(candidatoService.findAll().spliterator(), false) 
        .collect(Collectors.toList()));
	}
	
	@Test
	public void get_candidato_by_id() {
		Candidato candidato = new Candidato();
		candidato.setNome("Fulano");		
		candidato.setIdCargo(1);
		Candidato saved = candidatoService.save(candidato);
		
		assertNotNull(candidatoService.findById(saved.getId()));
	}
	
	@Test
	public void update_candidato_by_id() {
		Candidato candidato = new Candidato();
		candidato.setNome("Fulano");		
		candidato.setIdCargo(1);
		Candidato saved = candidatoService.save(candidato);
		candidato.setNome("Fulano Alter");
		candidato.setIdCargo(2);
		assertNotNull(candidatoService.save(saved));
	}
	
	@Test
	public void delete_by_id() {
		Candidato candidato = new Candidato();
		candidato.setNome("Fulano");		
		candidato.setIdCargo(1);
		Candidato saved = candidatoService.save(candidato);	
		candidatoService.deleteById(saved.getId());
		Optional<Candidato> c = candidatoService.findById(saved.getId());
		assertEquals(false, c.isPresent());
	}
	
}
