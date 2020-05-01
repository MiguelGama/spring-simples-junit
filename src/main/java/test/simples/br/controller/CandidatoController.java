package test.simples.br.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import test.simples.br.model.Candidato;
import test.simples.br.service.CandidatoService;

@RestController
@RequestMapping("/candidato")
public class CandidatoController {
	@Autowired
	private CandidatoService dao;
	
	//@Autowired
	//private CargoService cargoDao;
		
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public Candidato save(@Valid @RequestBody Candidato candidato) {
	    return dao.save(candidato);
	}
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
    public List<Candidato> getAll() {
		return StreamSupport.stream(dao.findAll().spliterator(), false) 
        .collect(Collectors.toList()); 
    }
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Candidato finById(@PathVariable(value = "id") long id) {
		return dao.findById(id).get();
    }
	
	@RequestMapping(value = "/{id}", method =  RequestMethod.PUT)
    public ResponseEntity<Candidato> update(@PathVariable(value = "id") long id, @Valid @RequestBody Candidato candidato)
    {
		try {
			dao.save(candidato);
			return new ResponseEntity<Candidato>(candidato, HttpStatus.OK);
		}catch (Exception e) {
			return new ResponseEntity<Candidato>(candidato, HttpStatus.NOT_FOUND);
		}
    }
	
	@RequestMapping(value = "/{id}", method =  RequestMethod.DELETE)
    public ResponseEntity<Candidato> delete(@PathVariable(value = "id") long id)
    {
		try {
			dao.deleteById(id);
			return new ResponseEntity<Candidato>(HttpStatus.OK);
		}catch (Exception e) {
			return new ResponseEntity<Candidato>( HttpStatus.NOT_FOUND);
		}
    }

}
