package co.clientes.ibm;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.powermock.modules.junit4.PowerMockRunner;

import co.clientes.ibm.persistencia.entidad.Consumo;
import co.clientes.ibm.persistencia.entidad.Tarjeta;
import co.clientes.ibm.persistencia.repositorio.IConsumoRepositorio;
import co.clientes.ibm.persistencia.repositorio.ITarjetaRepositorio;
import co.clientes.ibm.servicios.ConsumoService;
import co.clientes.ibm.servicios.TarjetaService;

/**
 * Clase que implementa el conjunto de pruebas para validar el funcionamiento de
 * TarjetaService y las operaciones de registro que relacionan un Cliente como
 * poseedor de la tarjeta y una lista de Consumos
 * 
 * @author Roy López Cardona
 */
@RunWith(PowerMockRunner.class)
public class TarjetasServiceTest {

	@InjectMocks
	private TarjetaService tarjetaService;

	@InjectMocks
	private ConsumoService consumoService;

	@Mock
	private ITarjetaRepositorio tarjetaRepositorio;

	@Mock
	private IConsumoRepositorio consumoRepositorio;

	private Long id = 1L;
	private String numero = "MockName";
	private Long ccv = 1234L;
	private String tipo = "MockCiudad";
	private List<Consumo> consumos = new ArrayList<>();

	@Before
	public void init() {
		MockitoAnnotations.initMocks(this);
		initConsumos();
	}

	/**
	 * Proposito: Este caso de prueba valida la creación de una Tarjeta
	 * 
	 * Entradas: id, numero, ccv, tipo
	 * 
	 * Salida Esperada: Ejecución del método encargado de la creación de la Tarjeta
	 * 
	 * Tipo de Prueba: Positiva
	 */
	@Test
	public void testCrearTarjeta() {
		Tarjeta tarjeta = new Tarjeta();
		tarjeta.setId(id);
		tarjeta.setNumero(numero);
		tarjeta.setCcv(ccv);
		tarjeta.setTipo(tipo);

		when(tarjetaRepositorio.save(any(Tarjeta.class))).thenReturn(tarjeta);
		Tarjeta tarjetaAlmacenada = tarjetaService.save(tarjeta);

		verify(tarjetaRepositorio).save(tarjeta);
		Assert.assertEquals(Long.valueOf(1L).intValue(), tarjetaAlmacenada.getId().intValue());
	}

	/**
	 * Proposito: Este caso de prueba valida la eliminación de una Tarjeta
	 * 
	 * Entradas: Entradas: id, numero, ccv, tipo
	 * 
	 * Salida Esperada: Ejecución del método encargado de la eliminación de la
	 * Tarjeta
	 * 
	 * Tipo de Prueba: Positiva
	 */
	@Test
	public void testEliminarTarjeta() {
		Tarjeta tarjeta = new Tarjeta();
		tarjeta.setId(id);
		tarjeta.setNumero(numero);
		tarjeta.setCcv(ccv);
		tarjeta.setTipo(tipo);

		tarjetaService.delete(id);
		verify(tarjetaRepositorio).deleteById(tarjeta.getId());
	}

	/**
	 * Proposito: Este caso de prueba valida la búsqueda de una Tarjeta
	 * 
	 * Entradas: id, numero, ccv, tipo
	 * 
	 * Salida Esperada: Ejecución del método encargado de la búsqueda de la Tarjeta
	 * 
	 * Tipo de Prueba: Positiva
	 */
	@Test
	public void testConsultarTarjeta() {
		Tarjeta tarjeta = new Tarjeta();
		tarjeta.setId(id);
		tarjeta.setNumero(numero);
		tarjeta.setCcv(ccv);
		tarjeta.setTipo(tipo);

		when(tarjetaRepositorio.findById(any(Long.class))).thenReturn(Optional.of(tarjeta));
		Tarjeta tarjetaConsultada = tarjetaService.findById(id);

		verify(tarjetaRepositorio).findById(id);
		Assert.assertEquals(Long.valueOf(tarjeta.getId()).intValue(),
				Long.valueOf(tarjetaConsultada.getId()).intValue());
	}

	/**
	 * Proposito: Este caso de prueba valida la creación de consumos para una
	 * Tarjeta
	 * 
	 * Entradas: id, numero, ccv, tipo, consumos
	 * 
	 * Salida Esperada: Ejecución del método encargado de registro de los Consumos
	 * 
	 * Tipo de Prueba: Positiva
	 */
	@Test
	public void testConsumosTarjeta() {
		Tarjeta tarjeta = new Tarjeta();
		tarjeta.setId(id);
		tarjeta.setNumero(numero);
		tarjeta.setCcv(ccv);
		tarjeta.setTipo(tipo);

		tarjeta.setConsumos(consumos);

		tarjeta.getConsumos().forEach(consumo -> {
			consumoService.save(consumo);
			verify(consumoRepositorio).save(consumo);
		});

		verify(consumoRepositorio, times(tarjeta.getConsumos().size())).save(any(Consumo.class));
	}

	/**
	 * Método utilitario para la generación de consumos
	 */
	public void initConsumos() {
		Consumo consumo1 = new Consumo();
		consumo1.setId(1l);
		consumo1.setDescripcion("Descripcion1");
		consumo1.setMonto(5000000d);
		consumo1.setFecha(new Date());
		consumo1.setTarjetaId(id);

		Consumo consumo2 = new Consumo();
		consumo2.setId(2l);
		consumo2.setDescripcion("Descripcion2");
		consumo2.setMonto(5000000d);
		consumo2.setFecha(new Date());
		consumo2.setTarjetaId(id);

		consumos.add(consumo1);
		consumos.add(consumo2);
	}

}
