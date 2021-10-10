package br.com.wine.cep.controller;

import java.util.Collection;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.wine.cep.exception.ConstraintsViolationException;
import br.com.wine.cep.exception.EntityNotFoundException;
import br.com.wine.cep.model.Cep;
import br.com.wine.cep.service.CepService;

@RestController
@RequestMapping("/cep")
public class CepController {

	@Autowired
	private CepService cepService;
	
	@GetMapping
    public @ResponseBody ResponseEntity<Collection<Cep>> listaCep() throws EntityNotFoundException {
		return ResponseEntity.ok().body(cepService.findAll());
    }
	
	@PostMapping()
	@ResponseStatus(HttpStatus.CREATED) 
	public ResponseEntity<Cep> cadastraCep(@RequestBody @Valid Cep cep) throws EntityNotFoundException, ConstraintsViolationException {
		return ResponseEntity.ok().body(cepService.create(cep));
	}
	
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<Cep> deletaCep(@Valid @PathVariable Long id) throws EntityNotFoundException {
		return ResponseEntity.ok().body(cepService.delete(id));
	}
	
	@PutMapping("/{id}")
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<Cep> atualizaCep(@Valid @PathVariable Long id, @Valid @RequestBody Cep cep) throws EntityNotFoundException, ConstraintsViolationException {
		return ResponseEntity.ok().body(cepService.update(id, cep));
	}
	
	@GetMapping("/{faixa}")
	@ResponseStatus(HttpStatus.OK)
	public @ResponseBody ResponseEntity<Cep> localizarPorFaixa(@Valid @PathVariable Integer faixa) throws EntityNotFoundException {
		return ResponseEntity.ok().body(cepService.localizarPorFaixa(faixa));
	}
}
