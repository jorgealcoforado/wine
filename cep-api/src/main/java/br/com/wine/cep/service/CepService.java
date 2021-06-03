package br.com.wine.cep.service;

import java.util.List;

import br.com.wine.cep.exception.ConstraintsViolationException;
import br.com.wine.cep.exception.EntityNotFoundException;
import br.com.wine.cep.model.Cep;

public interface CepService {

	Cep find(Long id);
	
	List<Cep> findAll() throws EntityNotFoundException;
	
	Cep create(Cep cep) throws ConstraintsViolationException;
	
	Cep update(Long id, Cep cep) throws ConstraintsViolationException, EntityNotFoundException;

	Cep delete(Long id) throws EntityNotFoundException;
	
	Cep localizarPorFaixa(Integer faixa) throws EntityNotFoundException;
}
