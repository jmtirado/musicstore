import java.util.Scanner;


public class Musica extends AbstractModelObject {
	private String Disco;
	private String Autor;
	private String Genero;
	private String Anyo;
	private String Formato;
	
	
	Musica()
	{
		
	}
	Musica(String dis,String aut,String gen, String anyo, String form)
	{
		this.Disco=dis;
		this.Autor=aut;
		this.Genero=gen;
		this.Anyo=anyo;
		this.Formato=form;
	}
	
	public Musica pedirDatos()
	{
		Musica mus = new Musica();
		Scanner leer = new Scanner(System.in);
		String buffer;
		
		System.out.println("Escriba el nombre del disco: ");
		buffer = leer.nextLine();
		//this.Nombre = buffer;
		mus.setDisco(buffer);
		
		System.out.println("Escribe el autor: ");
		buffer = leer.nextLine();
		mus.setAutor(buffer);
		
		System.out.println("Escribe el genero: ");
		buffer = leer.nextLine();
		mus.setGenero(buffer);
		
		System.out.println("Escribe la anyo: ");
		buffer = leer.nextLine();
		mus.setAnyo(buffer);
		
		System.out.println("Escribe su Formato:");
		buffer = leer.nextLine();
		mus.setFormato(buffer);

		
		return mus;
		
	}

	//SETTERS AND GETTERS
	public String getDisco() {
		return Disco;
	}

	public void setDisco(String disco) {
		Disco = disco;
	}

	public String getAutor() {
		return Autor;
	}

	public void setAutor(String autor) {
		Autor = autor;
	}

	public String getGenero() {
		return Genero;
	}

	public void setGenero(String genero) {
		Genero = genero;
	}

	public String getAnyo() {
		return Anyo;
	}

	public void setAnyo(String anyo) {
		Anyo = anyo;
	}

	public String getFormato() {
		return Formato;
	}

	public void setFormato(String formato) {
		Formato = formato;
	}
	
	

}
