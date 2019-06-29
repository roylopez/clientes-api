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

import co.clientes.ibm.persistencia.entidad.Cliente;
import co.clientes.ibm.servicios.IClienteService;
import co.clientes.ibm.servicios.ITarjetaService;
import co.clientes.ibm.utilidad.UtilMapper;

/**
 * Controlador de tipo Rest para exponer las operaciones asociadas a un cliente
 * 
 * @author Roy López Cardona
 *
 */
@CrossOrigin
@RestController
@RequestMapping("/api")
public class ClienteRestController {

	@Autowired
	private IClienteService clienteService;

	@Autowired
	private ITarjetaService tarjetaService;

	/**
	 * Método que permite obtener la lista de clientes en el sistema
	 * 
	 * @return La lista de clientes
	 */
	@GetMapping("/clientes")
	public List<Cliente> index() {
		return clienteService.findAll().stream().map(cliente -> {
			cliente.setTarjetas(tarjetaService.findByClienteId(cliente.getId()));
			return cliente;
		}).collect(Collectors.toList());
	}

	/**
	 * Método que permite obtener un cliente a partir de su identificador
	 * 
	 * @param id El identificador del cliente
	 * @return Inofrmación obtenida tras la operación
	 */
	@GetMapping("/clientes/{id}")
	public ResponseEntity<?> show(@PathVariable Long id) {
		Cliente cliente = null;
		Map<String, Object> mensajes = new HashMap<>();

		try {
			cliente = clienteService.findById(id);

		} catch (DataAccessException e) {
			mensajes.put("mensaje", "Error al realizar la consulta en la base de datos");
			mensajes.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(mensajes, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		if (cliente == null) {
			mensajes.put("mensaje",
					"El cliente con ID: ".concat(id.toString()).concat(" no existe en la base de datos"));
			return new ResponseEntity<Map<String, Object>>(mensajes, HttpStatus.NOT_FOUND);
		}
		cliente.setTarjetas(tarjetaService.findByClienteId(id));

		return new ResponseEntity<Cliente>(cliente, HttpStatus.OK);
	}

	/**
	 * Método que permite registrar un cliente
	 * 
	 * @param cliente Información del cliente
	 * @param result  Resultado de las validaciones javax
	 * @return Información obtenida tras la operación
	 */
	@PostMapping("/clientes")
	public ResponseEntity<?> create(@Valid @RequestBody Cliente cliente, BindingResult result) {
		Cliente clienteNuevo = null;
		Map<String, Object> mensajes = new HashMap<>();

		if (result.hasErrors()) {
			List<String> erros = result.getFieldErrors().stream()
					.map(s -> "El campo '" + s.getField() + "' " + s.getDefaultMessage())
					.collect(Collectors.toCollection(ArrayList::new));
			mensajes.put("errors", erros);
			return new ResponseEntity<Map<String, Object>>(mensajes, HttpStatus.BAD_REQUEST);
		}

		try {
			clienteNuevo = clienteService.save(cliente);
		} catch (DataAccessException e) {
			mensajes.put("mensaje", "Error al realizar el registro en la base de datos");
			mensajes.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(mensajes, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		mensajes.put("mensaje", "El asesor ha sido creado con éxito");
		mensajes.put("cliente", clienteNuevo);
		return new ResponseEntity<Map<String, Object>>(mensajes, HttpStatus.OK);
	}

	/**
	 * Método que permite actualizar un cliente
	 * 
	 * @param cliente La información del cliente
	 * @param result  Resultado de las validaciones javax
	 * @param id      El identificador del cliente
	 * @return Información obtenida tras la operación
	 */
	@PutMapping("/clientes/{id}")
	public ResponseEntity<?> update(@Valid @RequestBody Cliente cliente, BindingResult result, @PathVariable Long id) {
		Map<String, Object> mensajes = new HashMap<>();
		Cliente clienteActual = clienteService.findById(id);
		Cliente clienteActualizado = null;

		if (result.hasErrors()) {
			List<String> erros = result.getFieldErrors().stream()
					.map(s -> "El campo '" + s.getField() + "' " + s.getDefaultMessage())
					.collect(Collectors.toCollection(ArrayList::new));
			mensajes.put("errors", erros);
			return new ResponseEntity<Map<String, Object>>(mensajes, HttpStatus.BAD_REQUEST);
		}

		if (clienteActual == null) {
			mensajes.put("mensaje",
					"El cliente con ID: ".concat(id.toString()).concat(" no existe en la base de datos"));
			return new ResponseEntity<Map<String, Object>>(mensajes, HttpStatus.NOT_FOUND);
		}

		try {
			UtilMapper.copiarCliente(clienteActual, cliente);
			clienteActualizado = clienteService.save(clienteActual);
		} catch (DataAccessException e) {
			mensajes.put("mensaje", "Error al actualizar el registro en la base de datos");
			mensajes.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(mensajes, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		mensajes.put("mensaje", "El cliente ha sido actualizado con éxito");
		mensajes.put("cliente", clienteActualizado);
		return new ResponseEntity<Map<String, Object>>(mensajes, HttpStatus.OK);
	}

	/**
	 * Método que permite eliminar un ciente
	 * 
	 * @param id El identificador del cliente
	 * @return Información obtenida tras la operación
	 */
	@DeleteMapping("/clientes/{id}")
	public ResponseEntity<?> delete(@PathVariable Long id) {
		Map<String, Object> mensajes = new HashMap<>();
		Cliente cliente = null;

		try {
			cliente = clienteService.findById(id);
			if (cliente == null) {
				mensajes.put("mensaje",
						"El cliente con ID: ".concat(id.toString()).concat(" no existe en la base de datos"));
				return new ResponseEntity<Map<String, Object>>(mensajes, HttpStatus.NOT_FOUND);
			}

			clienteService.delete(id);
		} catch (DataAccessException e) {
			mensajes.put("mensaje", "Error al eliminar el registro en la base de datos");
			mensajes.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(mensajes, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		mensajes.put("mensaje", "El cliente ha sido eliminado con éxito");
		mensajes.put("cliente", cliente);
		return new ResponseEntity<Map<String, Object>>(mensajes, HttpStatus.OK);
	}

}
