package co.clientes.ibm.persistencia.entidad;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

/**
 * Clase que representa el objeto Asesor, hereda los atributos de Persona
 * 
 * @author Roy López Cardona
 */
@Entity
@Table(name = "asesores")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class Asesor extends Persona {

	private static final long serialVersionUID = 1L;

	@NotEmpty(message = "debe establecer un valor")
	@Size(max = 50, message = "El tamaño debe ser máximo de 50 caracteres")
	@Column(length = 50, nullable = false)
	private String especialidad;

	public String getEspecialidad() {
		return especialidad;
	}

	public void setEspecialidad(String especialidad) {
		this.especialidad = especialidad;
	}

}
