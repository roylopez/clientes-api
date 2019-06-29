package co.clientes.ibm.persistencia.repositorio;

import org.springframework.data.repository.CrudRepository;

import co.clientes.ibm.persistencia.entidad.Cliente;

/**
 * Interfaz para el acceso a las operaciones CRUD de los clientes
 * 
 * @author Roy López Cardona
 */
public interface IClienteRepositorio extends CrudRepository<Cliente, Long> {

}
