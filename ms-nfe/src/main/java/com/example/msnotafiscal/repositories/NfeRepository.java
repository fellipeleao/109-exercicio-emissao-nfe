package com.example.msnotafiscal.repositories;

import org.springframework.data.repository.CrudRepository;

import com.example.msnotafiscal.models.Nfe;

public interface NfeRepository extends CrudRepository<Nfe, Integer> 
{	
	Nfe findById(int id);
}