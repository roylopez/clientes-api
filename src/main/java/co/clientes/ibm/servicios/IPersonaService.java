package co.clientes.ibm.servicios;

import java.util.List;

/**
 * Interfaz que define el contrato relacionado a las operaciones para los
 * objetos de tipo Persona
 * 
 * @author Roy López Cardona
 *
 * @param <E> Cliente o Asesor
 */
public interface IPersonaService<E> {

	/**
	 * Método que permite obtener todas las personas
	 * 
	 * @return Lista de personas
	 */
	public List<E> findAll();

	/**
	 * Método que permite obtener una Persona por su identificador
	 * 
	 * @param id El identificador de la persona
	 * @return La Persona
	 */
	public E findById(Long id);

	/**
	 * Método que permite almacenar una persona
	 * 
	 * @param persona La persona
	 * @return La persona almacenada
	 */
	public E save(E persona);

	/**
	 * Método que permite eliminar una persona por su identificador
	 * 
	 * @param id El identificador de la persona
	 */
	public void delete(Long id);

}
