package co.clientes.ibm.persistencia.entidad;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

/**
 * Clase que representa el objeto Persona centralizando los atributos comunes
 * para las especializaciones Cliente y Asesor
 * 
 * @author Roy López Cardona
 *
 */
@MappedSuperclass
public class Persona implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotEmpty(message = "debe establecer un valor")
	@Size(max = 50, message = "El tamaño debe ser máximo de 50 caracteres")
	@Column(length = 50, nullable = false)
	private String nombre;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

}
