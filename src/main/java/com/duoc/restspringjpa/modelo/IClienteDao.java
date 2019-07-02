package com.duoc.restspringjpa.modelo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface IClienteDao extends JpaRepository<Cliente, String> {
	

}
