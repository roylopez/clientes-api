package co.clientes.ibm.servicios;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import co.clientes.ibm.persistencia.entidad.Consumo;
import co.clientes.ibm.persistencia.repositorio.IConsumoRepositorio;

/**
 * Servicio que realiza las operaciones CRUD sobre la base de datos para el
 * objeto de tipo Consumo
 * 
 * @author Roy López Cardona
 */
@Service
public class ConsumoService implements IConsumoService {

	@Autowired
	private IConsumoRepositorio consumoRepositorio;

	/**
	 * {@inheritDoc}
	 */
	@Override
	@Transactional(readOnly = true)
	public Consumo findById(Long id) {
		return consumoRepositorio.findById(id).orElse(null);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	@Transactional
	public Consumo save(Consumo consumo) {
		return consumoRepositorio.save(consumo);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	@Transactional
	public void delete(Long id) {
		consumoRepositorio.deleteById(id);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	@Transactional(readOnly = true)
	public List<Consumo> findByTarjetaId(Long id) {
		return consumoRepositorio.findByTarjetaId(id);
	}

}
