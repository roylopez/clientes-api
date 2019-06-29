package co.clientes.ibm;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.powermock.modules.junit4.PowerMockRunner;

import co.clientes.ibm.persistencia.entidad.Cliente;
import co.clientes.ibm.persistencia.repositorio.IClienteRepositorio;
import co.clientes.ibm.servicios.ClienteService;

/**
 * Clase que implementa el conjunto de pruebas para validar el funcionamiento de
 * ClienteService
 * 
 * @author Roy López Cardona
 */
@RunWith(PowerMockRunner.class)
public class ClienteServiceTest {

	@InjectMocks
	private ClienteService clienteService;

	@Mock
	private IClienteRepositorio clienteRepositorio;

	private Long id = 1L;
	private String nombre = "MockName";
	private String direccion = "MockDireccion";
	private String ciudad = "MockCiudad";
	private Long telefono = 123456789L;

	@Before
	public void init() {
		MockitoAnnotations.initMocks(this);
	}

	/**
	 * Proposito: Este caso de prueba valida la creación de un Cliente
	 * 
	 * Entradas: id, nombre, direccion, ciudad, telefono
	 * 
	 * Salida Esperada: Ejecución del método encargado de la creación del Cliente
	 * 
	 * Tipo de Prueba: Positiva
	 */
	@Test
	public void testCrearCliente() {
		Cliente cliente = new Cliente();
		cliente.setId(id);
		cliente.setCiudad(ciudad);
		cliente.setDireccion(direccion);
		cliente.setNombre(nombre);
		cliente.setTelefono(telefono);

		when(clienteRepositorio.save(any(Cliente.class))).thenReturn(cliente);
		Cliente clienteAlmacenado = clienteService.save(cliente);

		verify(clienteRepositorio).save(cliente);
		Assert.assertEquals(Long.valueOf(1L).intValue(), clienteAlmacenado.getId().intValue());
	}

	/**
	 * Proposito: Este caso de prueba valida la eliminación de un Cliente
	 * 
	 * Entradas: Entradas: id, nombre, direccion, ciudad, telefono
	 * 
	 * Salida Esperada: Ejecución del método encargado de la eliminación del Cliente
	 * 
	 * Tipo de Prueba: Positiva
	 */
	@Test
	public void testEliminarCliente() {
		Cliente cliente = new Cliente();
		cliente.setId(id);
		cliente.setCiudad(ciudad);
		cliente.setDireccion(direccion);
		cliente.setNombre(nombre);
		cliente.setTelefono(telefono);

		clienteService.delete(id);
		verify(clienteRepositorio).deleteById(cliente.getId());
	}

	/**
	 * Proposito: Este caso de prueba valida la búsqueda de un Cliente
	 * 
	 * Entradas: id, nombre, especialidad
	 * 
	 * Salida Esperada: Ejecución del método encargado de la búsqueda del Cliente
	 * 
	 * Tipo de Prueba: Positiva
	 */
	@Test
	public void testConsultarCliente() {
		Cliente cliente = new Cliente();
		cliente.setId(id);
		cliente.setCiudad(ciudad);
		cliente.setDireccion(direccion);
		cliente.setNombre(nombre);
		cliente.setTelefono(telefono);

		when(clienteRepositorio.findById(any(Long.class))).thenReturn(Optional.of(cliente));
		Cliente clienteConsultado = clienteService.findById(id);

		verify(clienteRepositorio).findById(id);
		Assert.assertEquals(Long.valueOf(cliente.getId()).intValue(),
				Long.valueOf(clienteConsultado.getId()).intValue());
	}

}
