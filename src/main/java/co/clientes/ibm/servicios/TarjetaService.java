package co.clientes.ibm.servicios;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import co.clientes.ibm.persistencia.entidad.Tarjeta;
import co.clientes.ibm.persistencia.repositorio.ITarjetaRepositorio;

/**
 * Servicio que realiza las operaciones CRUD sobre la base de datos para el
 * objeto de tipo Tarjeta
 * 
 * @author Roy López Cardona
 */
@Service
public class TarjetaService implements ITarjetaService {

	@Autowired
	private ITarjetaRepositorio repositiorioTarjeta;

	/**
	 * {@inheritDoc}
	 */
	@Override
	@Transactional(readOnly = true)
	public Tarjeta findById(Long id) {
		return repositiorioTarjeta.findById(id).orElse(null);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	@Transactional
	public Tarjeta save(Tarjeta tarjeta) {
		return repositiorioTarjeta.save(tarjeta);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	@Transactional
	public void delete(Long id) {
		repositiorioTarjeta.deleteById(id);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	@Transactional(readOnly = true)
	public List<Tarjeta> findByClienteId(Long id) {
		return repositiorioTarjeta.findByClienteId(id);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	@Transactional(readOnly = true)
	public Tarjeta findByNumero(String numero) {
		return repositiorioTarjeta.findByNumero(numero);
	}

}
