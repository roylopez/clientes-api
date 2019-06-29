package co.clientes.ibm.persistencia.entidad;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Clase que representa el objeto Tarjeta
 * 
 * @author Roy López Cardona
 *
 */
@Entity
@Table(name = "tarjetas")
public class Tarjeta implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotEmpty(message = "debe establecer un valor")
	@Size(min = 16, max = 16, message = "El tamaño debe ser de 16 caracteres")
	@Column(length = 16, unique = true, nullable = false)
	private String numero;

	@NotNull(message = "debe establecer un valor")
	@Column(nullable = false)
	private Long ccv;

	@NotNull(message = "debe establecer un valor")
	@Size(max = 50, message = "El tamaño debe ser máximo de 50 caracteres")
	@Column(length = 50)
	private String tipo;

	@NotNull
	@Column(nullable = false)
	private Long clienteId;

	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
	private List<Consumo> consumos;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public Long getCcv() {
		return ccv;
	}

	public void setCcv(Long ccv) {
		this.ccv = ccv;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public List<Consumo> getConsumos() {
		return consumos;
	}

	public void setConsumos(List<Consumo> consumos) {
		this.consumos = consumos;
	}

	public Long getClienteId() {
		return clienteId;
	}

	public void setClienteId(Long clienteId) {
		this.clienteId = clienteId;
	}

}
