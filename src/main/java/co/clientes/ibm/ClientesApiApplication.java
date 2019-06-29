package co.clientes.ibm;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Clase principal de la aplicación que se encarga de ejecutar los procesos
 * delegados a Spring Boot para inicializar y registrar todos los componentes
 * creados
 * 
 * @author Roy López Cardona
 *
 */
@SpringBootApplication
public class ClientesApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(ClientesApiApplication.class, args);
	}

}
