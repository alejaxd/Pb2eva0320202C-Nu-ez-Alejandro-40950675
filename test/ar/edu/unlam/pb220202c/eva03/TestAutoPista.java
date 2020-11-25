package ar.edu.unlam.pb220202c.eva03;

import static org.junit.Assert.*;

import java.util.TreeSet;

import org.junit.Test;

public class TestAutoPista {
	
	private static final int TreeSet = 0;

	@Test
	public void queSePuedaRegistrarTelepase () {
		Autopista autopistaEzeiza = new Autopista();
		Vehiculo auto = new Automovil("AAA123", 100);
		
		assertTrue(autopistaEzeiza.registrarTelepase(5554, auto));
	}
	
	@Test(expected = VehiculoNotFounException.class)
	public void queAlSalirDelAutopistaNoestaEncirculacionLanceUnaExcepcion() throws VehiculoNotFounException{
		Autopista autopistaEzeiza = new Autopista();
		Vehiculo auto = new Automovil("AAA123", 100);

		autopistaEzeiza.salirAutpista(auto);
		
	}
	@Test
	public void queVerifiqueQueSeObtengaUnaListaDeAutosInsfractoresOrdenadaPorPatente() throws VehiculoNotFounException{
		Autopista autopistaEzeiza = new Autopista();
		Vehiculo auto1 = new Automovil("AAA123", 100);
		Vehiculo auto2 = new Automovil("BBB123", 150);
		Vehiculo auto3 = new Automovil("ZZZ456", 180);
		Vehiculo auto4 = new Automovil("CCC456", 160);
		
		autopistaEzeiza.registrarTelepase(1234, auto1);
		autopistaEzeiza.registrarTelepase(4654, auto2);
		autopistaEzeiza.registrarTelepase(5464, auto3);
		autopistaEzeiza.registrarTelepase(1212, auto4);
		
		autopistaEzeiza.ingresarAutopista(1234);
		autopistaEzeiza.ingresarAutopista(4654);
		autopistaEzeiza.ingresarAutopista(5464);
		autopistaEzeiza.ingresarAutopista(1212);
		
		TreeSet<Vehiculo> listaOrdenada = autopistaEzeiza.obtenerVehiculosConExcesosDeVelocidadOrdenadosPorPatente();
		String valorEsperado = "BBB123,CCC456,ZZZ456,";
		String valorObtenido = "";
		for (Vehiculo vehiculo : listaOrdenada) {
			if(vehiculo.enInfraccion()) {
				valorObtenido+=vehiculo.getPatente();
				valorObtenido+=",";
			}
		}
		
		assertEquals(valorEsperado, valorObtenido);
		
	}
	
	@Test(expected = VehiculoNotFounException.class)
	public void queNoIngreseALaAutopistaYLanceUnaExcepcion() throws VehiculoNotFounException {
		Autopista autopistaEzeiza = new Autopista();
		Vehiculo auto = new Automovil("AAA123", 100);

		autopistaEzeiza.ingresarAutopista(1234);
	}
	
	@Test
	public void queDevuelvaLaCantidadDeVehiculosEnCirculacion() throws VehiculoNotFounException {
		Autopista autopistaEzeiza = new Autopista();
		Vehiculo auto1 = new Automovil("AAA123", 100);
		Vehiculo auto2 = new Automovil("BBB123", 150);
		Vehiculo auto3 = new Automovil("ZZZ456", 180);
		Vehiculo auto4 = new Automovil("CCC456", 160);
		
		autopistaEzeiza.registrarTelepase(1234, auto1);
		autopistaEzeiza.registrarTelepase(4654, auto2);
		autopistaEzeiza.registrarTelepase(5464, auto3);
		autopistaEzeiza.registrarTelepase(1212, auto4);
		
		autopistaEzeiza.ingresarAutopista(1234);
		autopistaEzeiza.ingresarAutopista(4654);
		autopistaEzeiza.ingresarAutopista(5464);
		autopistaEzeiza.ingresarAutopista(1212);
		
		Integer valorEsperado = 4;
		Integer valorObtenido = autopistaEzeiza.cantidadDeVehiculosENCirculacion();
		
		assertEquals(valorEsperado, valorObtenido);
		
	}
	
}
