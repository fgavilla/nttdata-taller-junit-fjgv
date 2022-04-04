package services;

import model.Articulo;

public interface BaseDatosI {
	
	public void iniciar();

	public Integer insertarArticulo(Articulo articulo);

	public Articulo buscarArticulo(Integer identificador);
	
	public Integer lastIndex();

}
