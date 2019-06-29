package co.clientes.ibm.servicios;

import java.util.List;

import co.clientes.ibm.persistencia.entidad.Consumo;

/**
 * Interfaz que define el contrato relacionado a las operaciones para el objeto
 * Consumo
 * 
 * @author Roy López Cardona
 */
public interface IConsumoService {

	/**
	 * Método que permite obtener un Consumo por su identificador
	 * 
	 * @param id El identificador del consumo
	 * @return El consumo
	 */
	public Consumo findById(Long id);

	/**
	 * Método que permite registrar un consumo
	 * 
	 * @param consumo Información del consumo
	 * @return El consumo registrado
	 */
	public Consumo save(Consumo consumo);

	/**
	 * Método que permite eliminar un consumo por su identificador
	 * 
	 * @param id El identificador del consumo
	 */
	public void delete(Long id);

	/**
	 * Métodoq ue permite obtener la lista de consumos asociados a una tarjeta
	 * 
	 * @param id El identificador de la tarjeta
	 * @return Lista de consumos
	 */
	public List<Consumo> findByTarjetaId(Long id);

}
