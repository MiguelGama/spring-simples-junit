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
import test.simples.br.model.Cargo;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = SalesManager.class)
public class CargoServiceTest {
	@Autowired
	private CargoService cargoService;

	@Test
	public void get_all_cargo() {
		assertNotNull(StreamSupport.stream(cargoService.findAll().spliterator(), false).collect(Collectors.toList()));
	}

	@Test
	public void get_cargo_by_id() {
		Cargo cargo = new Cargo();
		cargo.setNome("Fulano");
		Cargo saved = cargoService.save(cargo);
		assertNotNull(cargoService.findById(saved.getId()));
	}

	@Test
	public void update_cargo_by_id() {
		Cargo cargo = new Cargo();
		cargo.setNome("Fulano");
		Cargo saved = cargoService.save(cargo);
		saved.setNome("Fulano Alter");
		saved.setId(1L);
		assertNotNull(cargoService.save(saved));
	}

	@Test
	public void delete_by_id() {
		Cargo cargo = new Cargo();
		cargo.setNome("Fulano");
		Cargo saved = cargoService.save(cargo);
		cargoService.deleteById(saved.getId());
		Optional<Cargo> c = cargoService.findById(saved.getId());
		assertEquals(false, c.isPresent());
	}

}
