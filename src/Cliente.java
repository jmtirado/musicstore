import java.util.Scanner;



public class Cliente extends AbstractModelObject{
	private String Nombre;
	private String Apellidos;
	private String Direccion;
	private String Poblacion;
	private String CP;
	
	Cliente()
	{
		
	}
	
	Cliente(String nombre, String apellidos, String dir, String pobl, String cp)
	{
		this.Nombre=nombre;
		this.Apellidos=apellidos;
		this.Direccion=dir;
		this.Poblacion=pobl;
		this.CP=cp;
	}
	
	public Cliente pedirDatos()
	{
		Cliente client = new Cliente();
		Scanner leer = new Scanner(System.in);
		String buffer;
		
		System.out.println("Escriba el nombre: ");
		buffer = leer.nextLine();
		//this.Nombre = buffer;
		client.setNombre(buffer);
		
		System.out.println("Escribe los apellidos: ");
		buffer = leer.nextLine();
		//this.Apellidos = buffer;
		client.setApellidos(buffer);
		
		System.out.println("Escribe la dirrecion: ");
		buffer = leer.nextLine();
		//this.Direccion = buffer;
		client.setDireccion(buffer);
		
		System.out.println("Escribe la poblacion: ");
		buffer = leer.nextLine();
		//this.Direccion = buffer;
		client.setPoblacion(buffer);
		
		System.out.println("Escribe su CP");
		buffer = leer.nextLine();
		//this.DNI = buffer;
		client.setCP(buffer);

		
		return client;
		
	}
	
	//GETTERS AND SETTERS
	public String getNombre() {
		return Nombre;
	}

	public void setNombre(String nombre) {
		Nombre = nombre;
	}

	public String getApellidos() {
		return Apellidos;
	}

	public void setApellidos(String apellidos) {
		Apellidos = apellidos;
	}

	public String getDireccion() {
		return Direccion;
	}

	public void setDireccion(String direccion) {
		Direccion = direccion;
	}

	public String getPoblacion() {
		return Poblacion;
	}

	public void setPoblacion(String poblacion) {
		Poblacion = poblacion;
	}

	public String getCP() {
		return CP;
	}

	public void setCP(String cP) {
		CP = cP;
	}
	
	
	

}
