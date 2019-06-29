package co.clientes.ibm.utilidad;

import co.clientes.ibm.persistencia.entidad.Asesor;
import co.clientes.ibm.persistencia.entidad.Cliente;
import co.clientes.ibm.persistencia.entidad.Consumo;
import co.clientes.ibm.persistencia.entidad.Tarjeta;

/**
 * Clase que provee un conjunto de métodos utilitarios utilizados por otros
 * componentes
 * 
 * @author Roy López Cardona
 */
public class UtilMapper {

	/**
	 * Método que permite realizar la copia de atributos para un asesor
	 * 
	 * @param asesorActual Asesor anterior
	 * @param asesorNuevo  Asesor Nuevo
	 */
	public static void copiarAsesor(Asesor asesorActual, Asesor asesorNuevo) {
		asesorActual.setNombre(asesorNuevo.getNombre());
		asesorActual.setEspecialidad(asesorNuevo.getEspecialidad());
	}

	/**
	 * Método que permite realizar la copia de atributos para un cliente
	 * 
	 * @param clienteActual Cliente anterior
	 * @param clienteNuevo  Cliente nuevo
	 */
	public static void copiarCliente(Cliente clienteActual, Cliente clienteNuevo) {
		clienteActual.setNombre(clienteNuevo.getNombre());
		clienteActual.setDireccion(clienteNuevo.getDireccion());
		clienteActual.setCiudad(clienteNuevo.getCiudad());
		clienteActual.setTelefono(clienteNuevo.getTelefono());
	}

	/**
	 * Método que permite realizar la copia de atributos para una tarjeta
	 * 
	 * @param tarjetaActual Tarjeta anterior
	 * @param tarjetaNueva  Tarjeta nueva
	 */
	public static void copiarTarjeta(Tarjeta tarjetaActual, Tarjeta tarjetaNueva) {
		tarjetaActual.setNumero(tarjetaNueva.getNumero());
		tarjetaActual.setCcv(tarjetaNueva.getCcv());
		tarjetaActual.setTipo(tarjetaNueva.getTipo());
	}

	/**
	 * Método que permite realizar la copia de atributos para un consumo
	 * 
	 * @param consumoActual Consumo anterior
	 * @param consumoNuevo  Consumo nuevo
	 */
	public static void copiarConsumo(Consumo consumoActual, Consumo consumoNuevo) {
		consumoActual.setFecha(consumoNuevo.getFecha());
		consumoActual.setDescripcion(consumoNuevo.getDescripcion());
		consumoActual.setMonto(consumoNuevo.getMonto());
	}

}
