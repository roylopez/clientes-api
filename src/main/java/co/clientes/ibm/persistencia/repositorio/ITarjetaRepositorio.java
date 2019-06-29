package co.clientes.ibm.persistencia.repositorio;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import co.clientes.ibm.persistencia.entidad.Tarjeta;

/**
 * Interfaz para el acceso a las operaciones CRUD de las tarjetas
 * 
 * @author Roy López Cardona
 */
public interface ITarjetaRepositorio extends CrudRepository<Tarjeta, Long> {

	/**
	 * Método que permite obtener las tarjetas asociadas a un cliente
	 * 
	 * @param clienteId El identificador del cliente
	 * @return Lista de tarjetas
	 */
	public List<Tarjeta> findByClienteId(Long clienteId);

	/**
	 * Método que permite obtener una tarjeta a partir de su número
	 * 
	 * @param numero El número de la tarjeta
	 * @return La tarjeta obtenida
	 */
	public Tarjeta findByNumero(String numero);

}
