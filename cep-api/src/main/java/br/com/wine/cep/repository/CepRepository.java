package br.com.wine.cep.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.wine.cep.model.Cep;

public interface CepRepository extends JpaRepository<Cep, Long>{

	@Query("select c from Cep c where ?1 between c.faixaInicio and c.faixaFim")
	Cep findByFaixa(Integer faixa);
	
	@Query("select c from Cep c where c.id <> ?1 and ?2 between c.faixaInicio and c.faixaFim")
	Cep findByIdAndFaixa(Long id, Integer faixa);
}
