package com.example.nttdatatallerjunitfjgv;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import model.Articulo;
import services.BaseDatosI;

@SpringBootApplication
public class NttdataTallerJunitFjgvApplication implements CommandLineRunner {

	@Autowired
	private BaseDatosI baseDatosService;
	
	public static void main(String[] args) {
		SpringApplication.run(NttdataTallerJunitFjgvApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
		baseDatosService.iniciar();
        Articulo articulo = new Articulo("Calcetines", 5.95);
        baseDatosService.insertarArticulo(articulo);
        baseDatosService.buscarArticulo(baseDatosService.lastIndex());
		
	}

}
