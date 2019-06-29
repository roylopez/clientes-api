package co.clientes.ibm.persistencia.repositorio;

import org.springframework.data.repository.CrudRepository;

import co.clientes.ibm.persistencia.entidad.Asesor;

/**
 * Interfaz para el acceso a las operaciones CRUD de los asesores
 * 
 * @author Roy López Cardona
 */
public interface IAsesorRepositorio extends CrudRepository<Asesor, Long> {

}
