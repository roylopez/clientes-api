package co.clientes.ibm.servicios;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import co.clientes.ibm.persistencia.entidad.Asesor;
import co.clientes.ibm.persistencia.repositorio.IAsesorRepositorio;

/**
 * Servicio que realiza las operaciones CRUD sobre la base de datos para el
 * objeto de tipo Asesor
 * 
 * @author Roy López Cardona
 */
@Service
public class AsesorService implements IAsesorService {

	@Autowired
	private IAsesorRepositorio asesorRepositorio;

	/**
	 * {@inheritDoc}
	 */
	@Override
	@Transactional(readOnly = true)
	public List<Asesor> findAll() {
		return (List<Asesor>) asesorRepositorio.findAll();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	@Transactional(readOnly = true)
	public Asesor findById(Long id) {
		return asesorRepositorio.findById(id).orElse(null);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	@Transactional
	public Asesor save(Asesor cliente) {
		return asesorRepositorio.save(cliente);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	@Transactional
	public void delete(Long id) {
		asesorRepositorio.deleteById(id);
	}

}
