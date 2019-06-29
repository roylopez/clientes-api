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

import co.clientes.ibm.persistencia.entidad.Asesor;
import co.clientes.ibm.servicios.IAsesorService;
import co.clientes.ibm.utilidad.UtilMapper;

/**
 * Controlador de tipo Rest para exponer las operaciones asociadas al asesor
 * 
 * @author Roy López Cardona
 *
 */
@CrossOrigin
@RestController
@RequestMapping("/api")
public class AsesorRestController {

	@Autowired
	private IAsesorService asesorService;

	@GetMapping("/asesores")
	public List<Asesor> index() {
		return asesorService.findAll();
	}

	/**
	 * Método que permite obtener un asesor a partir de su identificador
	 * 
	 * @param id El identificador del asesor
	 * @return Información obtenida tras la operación
	 */
	@GetMapping("/asesores/{id}")
	public ResponseEntity<?> show(@PathVariable Long id) {
		Asesor asesor = null;
		Map<String, Object> mensajes = new HashMap<>();

		try {
			asesor = asesorService.findById(id);
		} catch (DataAccessException e) {
			mensajes.put("mensaje", "Error al realizar la consulta en la base de datos");
			mensajes.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(mensajes, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		if (asesor == null) {
			mensajes.put("mensaje",
					"El asesor con ID: ".concat(id.toString()).concat(" no existe en la base de datos"));
			return new ResponseEntity<Map<String, Object>>(mensajes, HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<Asesor>(asesor, HttpStatus.OK);
	}

	/**
	 * Método que permite crear un asesor en el sistema
	 * 
	 * @param asesor La información del asesor
	 * @param result Resultado de validaciones javax
	 * @return Información obtenida tras la operación
	 */
	@PostMapping("/asesores")
	public ResponseEntity<?> create(@Valid @RequestBody Asesor asesor, BindingResult result) {
		Asesor asesorNuevo = null;
		Map<String, Object> mensajes = new HashMap<>();

		if (result.hasErrors()) {
			List<String> erros = result.getFieldErrors().stream()
					.map(s -> "El campo '" + s.getField() + "' " + s.getDefaultMessage())
					.collect(Collectors.toCollection(ArrayList::new));
			mensajes.put("errors", erros);
			return new ResponseEntity<Map<String, Object>>(mensajes, HttpStatus.BAD_REQUEST);
		}

		try {
			asesorNuevo = asesorService.save(asesor);
		} catch (DataAccessException e) {
			mensajes.put("mensaje", "Error al realizar el registro en la base de datos");
			mensajes.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(mensajes, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		mensajes.put("mensaje", "El asesor ha sido creado con éxito");
		mensajes.put("asesor", asesorNuevo);
		return new ResponseEntity<Map<String, Object>>(mensajes, HttpStatus.OK);
	}

	/**
	 * Método que permite actualizar un asesor
	 * 
	 * @param asesor La información del asesor
	 * @param result Resultados de las validaciones javax
	 * @param id     Identificador del asesor
	 * @return Información obtenida tras la operación
	 */
	@PutMapping("/asesores/{id}")
	public ResponseEntity<?> update(@Valid @RequestBody Asesor asesor, BindingResult result, @PathVariable Long id) {
		Map<String, Object> mensajes = new HashMap<>();
		Asesor asesorActual = asesorService.findById(id);
		Asesor asesorActualizado = null;

		if (result.hasErrors()) {
			List<String> erros = result.getFieldErrors().stream()
					.map(s -> "El campo '" + s.getField() + "' " + s.getDefaultMessage())
					.collect(Collectors.toCollection(ArrayList::new));
			mensajes.put("errors", erros);
			return new ResponseEntity<Map<String, Object>>(mensajes, HttpStatus.BAD_REQUEST);
		}

		if (asesorActual == null) {
			mensajes.put("mensaje",
					"El asesor con ID: ".concat(id.toString()).concat(" no existe en la base de datos"));
			return new ResponseEntity<Map<String, Object>>(mensajes, HttpStatus.NOT_FOUND);
		}

		try {
			UtilMapper.copiarAsesor(asesorActual, asesor);
			asesorActualizado = asesorService.save(asesorActual);
		} catch (DataAccessException e) {
			mensajes.put("mensaje", "Error al actualizar el registro en la base de datos");
			mensajes.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(mensajes, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		mensajes.put("mensaje", "El asesor ha sido actualizado con éxito");
		mensajes.put("asesor", asesorActualizado);
		return new ResponseEntity<Map<String, Object>>(mensajes, HttpStatus.OK);
	}

	/**
	 * Método que permite eliminar un asesor
	 * 
	 * @param id El identificador del asesor
	 * @return Información obtenida tras la operación
	 */
	@DeleteMapping("/asesores/{id}")
	public ResponseEntity<?> delete(@PathVariable Long id) {
		Map<String, Object> mensajes = new HashMap<>();
		Asesor asesor = null;

		try {
			asesor = asesorService.findById(id);
			if (asesor == null) {
				mensajes.put("mensaje",
						"El asesor con ID: ".concat(id.toString()).concat(" no existe en la base de datos"));
				return new ResponseEntity<Map<String, Object>>(mensajes, HttpStatus.NOT_FOUND);
			}

			asesorService.delete(id);
		} catch (DataAccessException e) {
			mensajes.put("mensaje", "Error al eliminar el registro en la base de datos");
			mensajes.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(mensajes, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		mensajes.put("mensaje", "El asesor ha sido eliminado con éxito");
		mensajes.put("asesor", asesor);
		return new ResponseEntity<Map<String, Object>>(mensajes, HttpStatus.OK);
	}

}
