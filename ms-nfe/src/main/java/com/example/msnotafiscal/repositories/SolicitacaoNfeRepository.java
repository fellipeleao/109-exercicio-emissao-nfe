package com.example.msnotafiscal.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.example.msnotafiscal.models.SolicitacaoNfe;

public interface SolicitacaoNfeRepository extends CrudRepository<SolicitacaoNfe, Integer> 
{
	SolicitacaoNfe findById(int id);
	
	List<SolicitacaoNfe> findAllByIdentidade(String identidade);
}
