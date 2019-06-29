package co.clientes.ibm.controladores;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.clientes.ibm.persistencia.entidad.Consumo;
import co.clientes.ibm.persistencia.entidad.Tarjeta;
import co.clientes.ibm.servicios.IConsumoService;
import co.clientes.ibm.servicios.ITarjetaService;
import co.clientes.ibm.utilidad.UtilMapper;

/**
 * Controlador de tipo Rest para exponer las operaciones asociadas a la tarjeta
 * y sus consumos
 * 
 * @author Roy López Cardona
 *
 */
@CrossOrigin
@RestController
@RequestMapping("/api")
public class TarjetaRestController {

	@Autowired
	private ITarjetaService tarjetaService;

	@Autowired
	private IConsumoService consumoService;

	/**
	 * Método que permite obtener la información de las tarjetas para un cliente
	 * 
	 * @param id El identificador del cliente
	 * @return Información obtenida tras la operación
	 */
	@GetMapping("/tarjetas/cliente/{id}")
	public List<Tarjeta> tarjetasCliente(@PathVariable Long id) {
		return tarjetaService.findByClienteId(id).stream().map(tarjeta -> {
			tarjeta.setConsumos(consumoService.findByTarjetaId(tarjeta.getId()));
			return tarjeta;
		}).collect(Collectors.toList());
	}

	/**
	 * Método que permite obtener la información de una tarjeta a partir de su
	 * identificador
	 * 
	 * @param id El identificador de la tarjeta
	 * @return Información obtenida tras la operación
	 */
	@GetMapping("/tarjetas/{id}")
	public ResponseEntity<?> show(@PathVariable Long id) {
		Tarjeta tarjeta = null;
		Map<String, Object> mensajes = new HashMap<>();

		try {
			tarjeta = tarjetaService.findById(id);

		} catch (DataAccessException e) {
			mensajes.put("mensaje", "Error al realizar la consulta en la base de datos");
			mensajes.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(mensajes, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		if (tarjeta == null) {
			mensajes.put("mensaje",
					"La tarjeta con ID: ".concat(id.toString()).concat(" no existe en la base de datos"));
			return new ResponseEntity<Map<String, Object>>(mensajes, HttpStatus.NOT_FOUND);
		}
		tarjeta.setConsumos(consumoService.findByTarjetaId(id));

		return new ResponseEntity<Tarjeta>(tarjeta, HttpStatus.OK);
	}

	/**
	 * Método que permite crear una tarjeta
	 * 
	 * @param tarjeta Información de la tarjeta
	 * @param result  Resultado de las validaciones javax
	 * @return Información obtenida tras la operación
	 */
	@PostMapping("/tarjetas")
	public ResponseEntity<?> create(@Valid @RequestBody Tarjeta tarjeta, BindingResult result) {
		Tarjeta tarjetaNueva = null;
		Map<String, Object> mensajes = new HashMap<>();

		if (result.hasErrors()) {
			List<String> erros = result.getFieldErrors().stream()
					.map(s -> "El campo '" + s.getField() + "' " + s.getDefaultMessage())
					.collect(Collectors.toCollection(ArrayList::new));
			mensajes.put("errors", erros);
			return new ResponseEntity<Map<String, Object>>(mensajes, HttpStatus.BAD_REQUEST);
		}

		Tarjeta validacionNumero = tarjetaService.findByNumero(tarjeta.getNumero());
		if (validacionNumero != null) {
			mensajes.put("mensaje", "Se esta intentando asignar un número de tarjeta ya existente");
			return new ResponseEntity<Map<String, Object>>(mensajes, HttpStatus.BAD_REQUEST);
		}

		try {
			tarjetaNueva = tarjetaService.save(tarjeta);
		} catch (DataAccessException e) {
			mensajes.put("mensaje", "Error al realizar el registro en la base de datos");
			mensajes.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(mensajes, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		mensajes.put("mensaje", "La tarjeta ha sido creada con éxito");
		mensajes.put("tarjeta", tarjetaNueva);
		return new ResponseEntity<Map<String, Object>>(mensajes, HttpStatus.OK);
	}

	/**
	 * Método que permite actualizar una tarjeta
	 * 
	 * @param tarjeta La información de la tarjeta
	 * @param result  Resultado de las validaciones javax
	 * @param id      El identificador de la tarjeta
	 * @return Información obtenida tras la operación
	 */
	@PutMapping("/tarjetas/{id}")
	public ResponseEntity<?> update(@Valid @RequestBody Tarjeta tarjeta, BindingResult result, @PathVariable Long id) {
		Map<String, Object> mensajes = new HashMap<>();
		Tarjeta tarjetaActual = tarjetaService.findById(id);
		Tarjeta tarjetaActualizada = null;

		if (result.hasErrors()) {
			List<String> erros = result.getFieldErrors().stream()
					.map(s -> "El campo '" + s.getField() + "' " + s.getDefaultMessage())
					.collect(Collectors.toCollection(ArrayList::new));
			mensajes.put("errors", erros);
			return new ResponseEntity<Map<String, Object>>(mensajes, HttpStatus.BAD_REQUEST);
		}

		if (tarjetaActual == null) {
			mensajes.put("mensaje",
					"La tarjeta con ID: ".concat(id.toString()).concat(" no existe en la base de datos"));
			return new ResponseEntity<Map<String, Object>>(mensajes, HttpStatus.NOT_FOUND);
		}

		Tarjeta validacionNumero = tarjetaService.findByNumero(tarjeta.getNumero());
		if (validacionNumero != null && validacionNumero.getId().compareTo(id) != 0) {
			mensajes.put("mensaje", "Se esta intentando asignar un número de tarjeta ya existente");
			return new ResponseEntity<Map<String, Object>>(mensajes, HttpStatus.BAD_REQUEST);
		}

		try {
			UtilMapper.copiarTarjeta(tarjetaActual, tarjeta);
			tarjetaActualizada = tarjetaService.save(tarjetaActual);
		} catch (DataAccessException e) {
			mensajes.put("mensaje", "Error al actualizar el registro en la base de datos");
			mensajes.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(mensajes, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		mensajes.put("mensaje", "La tarjeta ha sido actualizado con éxito");
		mensajes.put("tarjeta", tarjetaActualizada);
		return new ResponseEntity<Map<String, Object>>(mensajes, HttpStatus.OK);
	}

	/**
	 * Método que permite eliminar una tarjeta
	 * 
	 * @param id El identificador de la tarjeta
	 * @return Información obtenida tras la operación
	 */
	@DeleteMapping("/tarjetas/{id}")
	public ResponseEntity<?> delete(@PathVariable Long id) {
		Map<String, Object> mensajes = new HashMap<>();
		Tarjeta tarjeta = null;

		try {
			tarjeta = tarjetaService.findById(id);
			if (tarjeta == null) {
				mensajes.put("mensaje",
						"La tarjeta con ID: ".concat(id.toString()).concat(" no existe en la base de datos"));
				return new ResponseEntity<Map<String, Object>>(mensajes, HttpStatus.NOT_FOUND);
			}

			tarjetaService.delete(id);
		} catch (DataAccessException e) {
			mensajes.put("mensaje", "Error al eliminar el registro en la base de datos");
			mensajes.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(mensajes, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		mensajes.put("mensaje", "La tarjeta ha sido eliminado con éxito");
		mensajes.put("tarjeta", tarjeta);
		return new ResponseEntity<Map<String, Object>>(mensajes, HttpStatus.OK);
	}

	/**
	 * Método que permite eliminar un consumo asociado a una tarjeta
	 * 
	 * @param id El identificador del consumo
	 * @return Información obtenida tras la operación
	 */
	@DeleteMapping("/tarjetas/consumos/{id}")
	public ResponseEntity<?> deleteConsumo(@PathVariable Long id) {
		Map<String, Object> mensajes = new HashMap<>();
		Consumo consumo = null;

		try {
			consumo = consumoService.findById(id);
			if (consumo == null) {
				mensajes.put("mensaje",
						"El consumo con ID: ".concat(id.toString()).concat(" no existe en la base de datos"));
				return new ResponseEntity<Map<String, Object>>(mensajes, HttpStatus.NOT_FOUND);
			}

			consumoService.delete(id);
		} catch (DataAccessException e) {
			mensajes.put("mensaje", "Error al eliminar el registro en la base de datos");
			mensajes.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(mensajes, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		mensajes.put("mensaje", "El consumo ha sido eliminado con éxito");
		mensajes.put("consumo", consumo);
		return new ResponseEntity<Map<String, Object>>(mensajes, HttpStatus.OK);
	}

	/**
	 * Método que permite crear un consumo asociado a una tarjeta
	 * 
	 * @param consumo La información del consumo
	 * @param result  Resultado de las validaciones javax
	 * @return Información obtenida tras la operación
	 */
	@PostMapping("/tarjetas/consumos")
	public ResponseEntity<?> createConsumo(@Valid @RequestBody Consumo consumo, BindingResult result) {
		Consumo consumoNuevo = null;
		Map<String, Object> mensajes = new HashMap<>();

		if (result.hasErrors()) {
			List<String> erros = result.getFieldErrors().stream()
					.map(s -> "El campo '" + s.getField() + "' " + s.getDefaultMessage())
					.collect(Collectors.toCollection(ArrayList::new));
			mensajes.put("errors", erros);
			return new ResponseEntity<Map<String, Object>>(mensajes, HttpStatus.BAD_REQUEST);
		}

		try {
			consumoNuevo = consumoService.save(consumo);
		} catch (DataAccessException e) {
			mensajes.put("mensaje", "Error al realizar el registro en la base de datos");
			mensajes.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(mensajes, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		mensajes.put("mensaje", "El consumo ha sido creada con éxito");
		mensajes.put("consumo", consumoNuevo);
		return new ResponseEntity<Map<String, Object>>(mensajes, HttpStatus.OK);
	}

	/**
	 * Método que permite actualizar la información de consumo asociada a una
	 * tarjeta
	 * 
	 * @param consumo La información del consumo
	 * @param result  Resultado de las validaciones javax
	 * @param id      El identificador del consumo
	 * @return Información obtenida tras la operación
	 */
	@PutMapping("/tarjetas/consumos/{id}")
	public ResponseEntity<?> updateConsumo(@Valid @RequestBody Consumo consumo, BindingResult result,
			@PathVariable Long id) {
		Map<String, Object> mensajes = new HashMap<>();
		Consumo consumoActual = consumoService.findById(id);
		Consumo consumoActualizado = null;

		if (result.hasErrors()) {
			List<String> erros = result.getFieldErrors().stream()
					.map(s -> "El campo '" + s.getField() + "' " + s.getDefaultMessage())
					.collect(Collectors.toCollection(ArrayList::new));
			mensajes.put("errors", erros);
			return new ResponseEntity<Map<String, Object>>(mensajes, HttpStatus.BAD_REQUEST);
		}

		if (consumoActual == null) {
			mensajes.put("mensaje",
					"El consumo con ID: ".concat(id.toString()).concat(" no existe en la base de datos"));
			return new ResponseEntity<Map<String, Object>>(mensajes, HttpStatus.NOT_FOUND);
		}

		try {
			UtilMapper.copiarConsumo(consumoActual, consumo);
			consumoActualizado = consumoService.save(consumoActual);
		} catch (DataAccessException e) {
			mensajes.put("mensaje", "Error al actualizar el registro en la base de datos");
			mensajes.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(mensajes, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		mensajes.put("mensaje", "La tarjeta ha sido actualizado con éxito");
		mensajes.put("consumo", consumoActualizado);
		return new ResponseEntity<Map<String, Object>>(mensajes, HttpStatus.OK);
	}

}
