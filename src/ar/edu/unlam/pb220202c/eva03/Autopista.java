package ar.edu.unlam.pb220202c.eva03;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.TreeSet;

public class Autopista {
	//Si es necesario Utilice herencia o implemente de Interfaces
	//Se debe crear contructeres getters y Setters y los atributos o metodos que crean convenientes
	
	private HashMap <Integer,Vehiculo> telapase;
	private HashSet <Vehiculo> vehiculosEnCirculacion;
	
	public Autopista() {
		telapase = new HashMap<>();
		vehiculosEnCirculacion = new HashSet<>();
	}
	
	public Boolean registrarTelepase (Integer numeroTelpase, Vehiculo vehiculo) {
		if(telapase.containsKey(numeroTelpase)) {
			return false;
		}else {
			telapase.put(numeroTelpase, vehiculo);
			return true;
		}
	}
	public Boolean ingresarAutopista (Integer numeroTelepase) throws VehiculoNotFounException {
		//si el telepase no esta registrado lanza una Exceptios del tipo VehiculoNotFounException
	   // y no permite ingresar al autopista	
		
		if(telapase.containsKey(numeroTelepase)) {
			Vehiculo v = telapase.get(numeroTelepase);
			vehiculosEnCirculacion.add(v);
			return true;
		}else {

			throw new VehiculoNotFounException("El vehiculo no esta registrado.");
		}
	}
	
	public void salirAutpista (Vehiculo vehiculo) throws VehiculoNotFounException {
		//lanza Una exception VehiculoNotFounException si no esta en circulacion
		
		if(telapase.containsKey(vehiculo.getPatente())) {
			telapase.remove(vehiculo.getPatente());
		} else {

			throw new VehiculoNotFounException("El vehiculo no esta  en la autopista.");
		}
	}
	
	public TreeSet<Vehiculo> obtenerVehiculosConExcesosDeVelocidadOrdenadosPorPatente(){
		TreeSet<Vehiculo> excesos = new TreeSet<>();
		for (Vehiculo vehiculo : vehiculosEnCirculacion) {
			if(vehiculo.enInfraccion()) {
				excesos.add(vehiculo);
			}
		}
		return excesos;
    }

	public Integer cantidadDeVehiculosENCirculacion() {
		return telapase.size();
	}
	
}
