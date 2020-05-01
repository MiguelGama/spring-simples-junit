package test.simples.br.service;

import org.springframework.data.repository.CrudRepository;

import test.simples.br.model.Cargo;

public interface CargoService extends CrudRepository<Cargo, Long>{

}
