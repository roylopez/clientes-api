package co.clientes.ibm.persistencia.entidad;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Clase que representa el objeto cliente, hereda los atributos de Persona
 * 
 * @author Roy López Cardona
 */
@Entity
@Table(name = "clientes")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class Cliente extends Persona {

	private static final long serialVersionUID = 1L;

	@NotEmpty(message = "debe establecer un valor")
	@Size(max = 100, message = "El tamaño debe ser máximo de 100 caracteres")
	@Column(length = 100, nullable = false)
	private String direccion;

	@NotEmpty(message = "debe establecer un valor")
	@Size(max = 30, message = "El tamaño debe ser máximo de 30 caracteres")
	@Column(length = 30, nullable = false)
	private String ciudad;

	@NotNull(message = "debe establecer un valor")
	@Column(precision = 20, nullable = false)
	private Long telefono;

	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
	private List<Tarjeta> tarjetas;

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getCiudad() {
		return ciudad;
	}

	public void setCiudad(String ciudad) {
		this.ciudad = ciudad;
	}

	public Long getTelefono() {
		return telefono;
	}

	public void setTelefono(Long telefono) {
		this.telefono = telefono;
	}

	public List<Tarjeta> getTarjetas() {
		return tarjetas;
	}

	public void setTarjetas(List<Tarjeta> tarjetas) {
		this.tarjetas = tarjetas;
	}

}
