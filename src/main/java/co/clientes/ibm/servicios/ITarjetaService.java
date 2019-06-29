package co.clientes.ibm.servicios;

import java.util.List;

import co.clientes.ibm.persistencia.entidad.Tarjeta;

/**
 * Interfaz que define el contrato relacionado a las operaciones para el objeto
 * Tarjeta
 * 
 * @author Roy López Cardona
 */
public interface ITarjetaService {

	/**
	 * Método que permite obtener una tarjeta por su identificador
	 * 
	 * @param id El identificador de la tarjeta
	 * @return La tarjeta
	 */
	public Tarjeta findById(Long id);

	/**
	 * Método que permite registrar una tarjeta
	 * 
	 * @param tarjeta Información de la tarjeta
	 * @return La tarjeta
	 */
	public Tarjeta save(Tarjeta tarjeta);

	/**
	 * Método que permite eliminar una tarjeta por su identificador
	 * 
	 * @param id El identificador de la tarjeta
	 */
	public void delete(Long id);

	/**
	 * Método que permite obtener la lista de tarjetas asociadas a un cliente
	 * 
	 * @param id El identificador del cliente
	 * @return Lista de tarjetas
	 */
	public List<Tarjeta> findByClienteId(Long id);

	/**
	 * Método que permite obtener una tarjeta a partir de su número
	 * 
	 * @param numero El número de la tarjeta
	 * @return La tarjeta
	 */
	public Tarjeta findByNumero(String numero);

}
