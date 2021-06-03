package br.com.wine.cep.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
    public @ResponseBody List<Cep> listaCep() throws EntityNotFoundException {
      return cepService.findAll();
    }
	
	@PostMapping()
	@ResponseStatus(HttpStatus.CREATED) 
	public Cep cadastraCep(@RequestBody @Valid Cep cep) throws EntityNotFoundException, ConstraintsViolationException {
		return cepService.create(cep);
	}
	
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.OK)
	public Cep deletaCep(@Valid @PathVariable Long id) throws EntityNotFoundException {
		return cepService.delete(id);
	}
	
	@PutMapping("/{id}")
	@ResponseStatus(HttpStatus.OK)
	public Cep atualizaCep(@Valid @PathVariable Long id, @Valid @RequestBody Cep cep) throws EntityNotFoundException, ConstraintsViolationException {
		return cepService.update(id, cep);
	}
	
	@GetMapping("/{faixa}")
	@ResponseStatus(HttpStatus.OK)
	public @ResponseBody Cep localizarPorFaixa(@Valid @PathVariable Integer faixa) throws EntityNotFoundException {
		return cepService.localizarPorFaixa(faixa);
	}
}
