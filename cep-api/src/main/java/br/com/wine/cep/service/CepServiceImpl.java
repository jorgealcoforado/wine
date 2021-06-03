package br.com.wine.cep.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.wine.cep.exception.ConstraintsViolationException;
import br.com.wine.cep.exception.EntityNotFoundException;
import br.com.wine.cep.model.Cep;
import br.com.wine.cep.repository.CepRepository;

@Service
public class CepServiceImpl implements CepService {

	@Autowired
	private CepRepository cepRepository;
	
	@Override
	public Cep find(Long id) {
		return cepRepository.findById(id).orElse(null);
	}

	@Override
	public List<Cep> findAll() throws EntityNotFoundException {
		return cepRepository.findAll();
	}

	@Override
	@Transactional
	public Cep create(Cep cep) throws ConstraintsViolationException {
		
		findByFaixa(cep.getFaixaInicio());
		findByFaixa(cep.getFaixaFim());
		
		return cepRepository.save(cep);
	}

	@Override
	@Transactional
	public Cep update(Long id, Cep cepNovo) throws ConstraintsViolationException, EntityNotFoundException {
		
		Cep cepAtual = find(id);
		
		if (cepAtual == null) {
			throw new EntityNotFoundException("Erro! Não pode encontrar a Entidade com o id: " + id);
		}
		
		findByIdAndFaixa(cepAtual.getId(), cepNovo.getFaixaInicio());
		findByIdAndFaixa(cepAtual.getId(), cepNovo.getFaixaFim());
		
		cepAtual.setCodigoLoja(cepNovo.getCodigoLoja());
		cepAtual.setFaixaInicio(cepNovo.getFaixaInicio());
		cepAtual.setFaixaFim(cepNovo.getFaixaFim());
		
		return cepRepository.save(cepAtual);
	}

	@Override
	@Transactional
	public Cep delete(Long id) throws EntityNotFoundException {
		
		Cep cep = find(id);
		
		if (cep == null) {
			throw new EntityNotFoundException("Erro! Não pode encontrar a Entidade com o id: " + id);
		}
		
		cepRepository.delete(cep);
		return cep;
	}
	
	private Cep findByFaixa(Integer faixa) throws ConstraintsViolationException {
		
		Cep cep = cepRepository.findByFaixa(faixa);
		
		if (cep != null) {
			throw new ConstraintsViolationException("Erro! Essa faixa de CEP conflita com a faixa de CEP da loja de " + cep.getCodigoLoja());
		}
		
		return cep;
	}
	
	private Cep findByIdAndFaixa(Long id, Integer faixa) throws ConstraintsViolationException {
		
		Cep cep = cepRepository.findByIdAndFaixa(id, faixa);
		
		if (cep != null) {
			throw new ConstraintsViolationException("Erro! Essa faixa de CEP conflita com a faixa de CEP da loja de " + cep.getCodigoLoja());
		}
		
		return cep;
	}
	
	@Override
	public Cep localizarPorFaixa(Integer faixa) throws EntityNotFoundException {
		
		Cep cep = cepRepository.findByFaixa(faixa);
		
		if (cep == null) {
			throw new EntityNotFoundException("Erro! Não pode encontrar a entidade com valor de faixa de cep: " + faixa);
		}
		
		return cep;
	}

}
