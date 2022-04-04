package services;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import model.Articulo;

@RunWith(MockitoJUnitRunner.class)
public class CarritoCompraServiceImplTest {

	@InjectMocks
	private CarritoCompraServiceImpl carritoService = new CarritoCompraServiceImpl();
	
	@Mock
	private BaseDatosImpl baseDatos;


	@Test
	public void testLimpiarCesta() {
		carritoService.addArticulo(new Articulo("Camiseta", 15.99D));
		assertFalse(carritoService.getArticulos().isEmpty());
		carritoService.limpiarCesta();
		assertTrue(carritoService.getArticulos().isEmpty());

	}

	@Test
	public void testAddArticulo() {
		int tamano = carritoService.getArticulos().size();
		carritoService.addArticulo(new Articulo("Camiseta", 15.99D));
		assertFalse(tamano == carritoService.getArticulos().size());
		assertTrue((tamano + 1) == carritoService.getArticulos().size());
	}

	@Test
	public void testGetNumArticulos() {
		int tamaño = carritoService.getNumArticulos();
		assertFalse(carritoService.getNumArticulos() == -1);
		assertTrue(carritoService.getNumArticulos() == tamaño);
	}

	@Test
	public void testGetArticulos() {
		List<Articulo> articulos = carritoService.getArticulos();
		assertFalse(articulos != carritoService.getArticulos());
		assertTrue(articulos == carritoService.getArticulos());
	}

	@Test
	public void testTotalPrice() {
		Double total = carritoService.totalPrice();
		carritoService.addArticulo(new Articulo("Camiseta", 15.99D));
		assertFalse(total == carritoService.totalPrice());
		assertTrue(15.99D == carritoService.totalPrice());

	}

	@Test
	public void testCalculadorDescuento() {
		assertEquals(Double.valueOf(90D), carritoService.calculadorDescuento(100D, 10D));
	}
	
	@Test
	public void testAplicarDescuento() {
		Articulo articulo = new Articulo("Camiseta",20.00);
		when(baseDatos.buscarArticulo(1)).thenReturn(articulo);
		Double res = carritoService.aplicarDescuento(1, 10D);
		assertEquals(Double.valueOf(18D),res);
		verify(baseDatos, atLeast(1)).buscarArticulo(1);
	}
	
	@Test
	public void testInsertarArticulo() {

		Articulo articulo = new Articulo("Zapatilla", 190.9D);
		int id = 1;
		
		when(baseDatos.insertarArticulo(articulo)).thenReturn(8);
		id = carritoService.insertar(articulo);
		
		assertEquals(8, id);
		assertTrue(carritoService.getArticulos().contains(articulo));
		verify(baseDatos, atLeast(1)).insertarArticulo(any(Articulo.class));
	}

}
