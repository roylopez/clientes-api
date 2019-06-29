package co.clientes.ibm.persistencia.repositorio;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import co.clientes.ibm.persistencia.entidad.Consumo;

/**
 * Interfaz para el acceso a las operaciones CRUD de los consumos
 * 
 * @author Roy López Cardona
 */
public interface IConsumoRepositorio extends CrudRepository<Consumo, Long> {

	/**
	 * Método que permite obtener la lista de consumos asociados a una tarjeta
	 * 
	 * @param tarjetaId El identificador de la tarjeta
	 * @return Lista de consumos
	 */
	public List<Consumo> findByTarjetaId(Long tarjetaId);

}
