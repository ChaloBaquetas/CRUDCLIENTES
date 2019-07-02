package com.duoc.restspringjpa.controlador;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.duoc.restspringjpa.modelo.Cliente;
import com.duoc.restspringjpa.modelo.IClienteDao;

@RestController

public class RESTControlador {

	@Autowired
	IClienteDao clienteDao;

	@GetMapping("/cliente")
	public List<Cliente> listar() {
		// OBTENER TODOS LOS CLIENTES

		return clienteDao.findAll();
	}

	@GetMapping("/cliente/{rut}")
	public Cliente get(@PathVariable String rut) {

		return clienteDao.findById(rut).orElse(null);
	}

	@PostMapping("/cliente")
	public boolean add(@RequestBody Cliente nuevo) {
		if (!clienteDao.existsById(nuevo.getRut())) {
			clienteDao.save(nuevo);
			return true;
		}
		return false;

	}

	@PutMapping("/cliente")
	public boolean modify(@RequestBody Cliente modificado) {

		if(clienteDao.existsById(modificado.getRut())) {
			clienteDao.save(modificado);
			return true;
		}
		return false;
	}

	@DeleteMapping("/cliente/{rut}")
	public boolean delete(@PathVariable String rut) { 
		if(rut.equalsIgnoreCase("99999999-9")) {
			if (clienteDao.count()!=0) {
			clienteDao.deleteAll();
			return true;
			}else {
				return false;
			}
			
		}else if(clienteDao.existsById(rut)) {
			clienteDao.deleteById(rut);
			return true;
		}
		return false;
		
	}

	
}
