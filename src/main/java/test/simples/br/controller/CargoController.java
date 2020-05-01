package test.simples.br.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import test.simples.br.model.Cargo;
import test.simples.br.service.CargoService;

@RestController
@RequestMapping(path ="/cargo")
public class CargoController {
	@Autowired
	private CargoService cargoService;
		
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public Cargo save(@Valid @RequestBody Cargo candidato) {
	    return cargoService.save(candidato);
	}
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
    public List<Cargo> getAll() {
		return StreamSupport.stream(cargoService.findAll().spliterator(), false) 
        .collect(Collectors.toList()); 
    }
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Cargo finById(@PathVariable(value = "id") long id) {
		return cargoService.findById(id).get();
    }
	
	@RequestMapping(value = "/{id}", method =  RequestMethod.PUT)
    public ResponseEntity<Cargo> update(@PathVariable(value = "id") long id, @Valid @RequestBody Cargo candidato)
    {
		try {
			cargoService.save(candidato);
			return new ResponseEntity<Cargo>(candidato, HttpStatus.OK);
		}catch (Exception e) {
			return new ResponseEntity<Cargo>(candidato, HttpStatus.NOT_FOUND);
		}
    }
	
	@RequestMapping(value = "/{id}", method =  RequestMethod.DELETE)
    public ResponseEntity<Cargo> delete(@PathVariable(value = "id") long id)
    {
		try {
			cargoService.deleteById(id);
			return new ResponseEntity<Cargo>(HttpStatus.OK);
		}catch (Exception e) {
			return new ResponseEntity<Cargo>( HttpStatus.NOT_FOUND);
		}
    }

}
