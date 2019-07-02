package com.duoc.restspringjpa.test;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import com.duoc.restspringjpa.modelo.Cliente;
import com.duoc.restspringjpa.modelo.IClienteDao;

@RunWith(SpringRunner.class)
@DataJpaTest

public class ClientesTest {

	@Autowired
	TestEntityManager manager;

	@Autowired
	IClienteDao dao;

	@Before
	public void setUp() throws Exception {

	Cliente cliente = new Cliente("16628103-2", "Gonzalo", "Aguiar", "gonzalo.aguiar@outlook.com", "+56987460306");
	this.manager.persist(cliente);
	cliente = new Cliente("17706619-2", "Diego", "Aguiar", "diego.aguiar@hotmail.com", "+56987460307");
	this.manager.persist(cliente);
	}
	
	@Test
	public void buscarUnClienteConSuIdArrojaTrue() {
		System.out.println(this.dao.findById("16628103-2").get());
		assertTrue(true);
	}
	
	@Test
	public void buscarTodosLosClientes2InsertadosTrue() {
		int cuantos = this.dao.findAll().size();
		assertTrue("son " + cuantos + " pero deberia ser 2", cuantos == 2);
	}

	@Test
	public void eliminar1ClienteArrojaTrue() {
		this.dao.deleteById("17706619-2");
		int cuantos = this.dao.findAll().size();
		assertTrue("Son " + cuantos + "usuarios y deberian ser 1", cuantos == 1);
	}

	@Test
	public void cuandoModificamosClienteEntoncesSeObtieneModificacion() {
		this.dao.save(new Cliente("99999-9", "Peter", "Parker", "peter.parker@dailybugle.com", "+11293241244"));
		Cliente spiderman = this.dao.findById("99999-9").get();
		assertNotNull(spiderman);
		assertTrue("Es " + spiderman.getNombres() + " pero deberia ser Peter ", spiderman.getNombres().equals("Peter"));
	}
	
	@Test
	public void cuandoInsertamos1ArrojaTrue() {
		this.dao.save(new Cliente("99999-9", "Peter", "Parker", "peter.parker@dailybugle.com", "+11293241244"));
		int cuantos = this.dao.findAll().size();
		assertTrue("Son " + cuantos + " pero deberían ser 3", cuantos==3);
	}
	
}