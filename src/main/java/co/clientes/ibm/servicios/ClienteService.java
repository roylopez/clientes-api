package co.clientes.ibm.servicios;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import co.clientes.ibm.persistencia.entidad.Cliente;
import co.clientes.ibm.persistencia.repositorio.IClienteRepositorio;

/**
 * Servicio que realiza las operaciones CRUD sobre la base de datos para el
 * objeto de tipo Cliente
 * 
 * @author Roy López Cardona
 */
@Service
public class ClienteService implements IClienteService {

	@Autowired
	private IClienteRepositorio clienteRepositorio;

	/**
	 * {@inheritDoc}
	 */
	@Override
	@Transactional(readOnly = true)
	public List<Cliente> findAll() {
		return (List<Cliente>) clienteRepositorio.findAll();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	@Transactional(readOnly = true)
	public Cliente findById(Long id) {
		return clienteRepositorio.findById(id).orElse(null);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	@Transactional
	public Cliente save(Cliente cliente) {
		return clienteRepositorio.save(cliente);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	@Transactional
	public void delete(Long id) {
		clienteRepositorio.deleteById(id);
	}

}
