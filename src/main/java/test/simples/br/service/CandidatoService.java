package test.simples.br.service;

import org.springframework.data.repository.CrudRepository;

import test.simples.br.model.Candidato;


public interface CandidatoService extends CrudRepository<Candidato, Long>{

}
