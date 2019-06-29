package co.clientes.ibm;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.powermock.modules.junit4.PowerMockRunner;

import co.clientes.ibm.persistencia.entidad.Asesor;
import co.clientes.ibm.persistencia.repositorio.IAsesorRepositorio;
import co.clientes.ibm.servicios.AsesorService;
import org.junit.Assert;

/**
 * Clase que implementa el conjunto de pruebas para validar el funcionamiento de
 * AsesorService
 * 
 * @author Roy López Cardona
 */
@RunWith(PowerMockRunner.class)
public class AsesorServiceTest {

	@InjectMocks
	private AsesorService asesorService;

	@Mock
	private IAsesorRepositorio asesorRepositorio;

	private Long id = 1L;
	private String nombre = "MockName";
	private String especialidad = "MockEspecialidad";

	@Before
	public void init() {
		MockitoAnnotations.initMocks(this);
	}

	/**
	 * Proposito: Este caso de prueba valida la creación de un Asesor
	 * 
	 * Entradas: id, nombre, especialidad
	 * 
	 * Salida Esperada: Ejecución del método encargado de la creación del Asesor
	 * 
	 * Tipo de Prueba: Positiva
	 */
	@Test
	public void testCrearAsesor() {
		Asesor asesor = new Asesor();
		asesor.setId(id);
		asesor.setNombre(nombre);
		asesor.setEspecialidad(especialidad);

		when(asesorRepositorio.save(any(Asesor.class))).thenReturn(asesor);
		Asesor asesorAlmacenado = asesorService.save(asesor);

		verify(asesorRepositorio).save(asesor);
		Assert.assertEquals(Long.valueOf(1L).intValue(), asesorAlmacenado.getId().intValue());
	}

	/**
	 * Proposito: Este caso de prueba valida la eliminación de un Asesor
	 * 
	 * Entradas: id, nombre, especialidad
	 * 
	 * Salida Esperada: Ejecución del método encargado de la eliminación del Asesor
	 * 
	 * Tipo de Prueba: Positiva
	 */
	@Test
	public void testEliminarAsesor() {
		Asesor asesor = new Asesor();
		asesor.setId(id);
		asesor.setNombre(nombre);
		asesor.setEspecialidad(especialidad);

		asesorService.delete(id);
		verify(asesorRepositorio).deleteById(asesor.getId());
	}

	/**
	 * Proposito: Este caso de prueba valida la búsqueda de un Asesor
	 * 
	 * Entradas: id, nombre, especialidad
	 * 
	 * Salida Esperada: Ejecución del método encargado de la búsqueda del Asesor
	 * 
	 * Tipo de Prueba: Positiva
	 */
	@Test
	public void testConsultarAsesor() {
		Asesor asesor = new Asesor();
		asesor.setId(id);
		asesor.setNombre(nombre);
		asesor.setEspecialidad(especialidad);

		when(asesorRepositorio.findById(any(Long.class))).thenReturn(Optional.of(asesor));
		Asesor asesorConsultado = asesorService.findById(id);

		verify(asesorRepositorio).findById(id);
		Assert.assertEquals(Long.valueOf(asesor.getId()).intValue(), Long.valueOf(asesorConsultado.getId()).intValue());
	}

}
