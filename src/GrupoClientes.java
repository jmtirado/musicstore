import java.util.ArrayList;
import java.util.List;

public class GrupoClientes extends AbstractModelObject {
	private List<Cliente> g_personas = new ArrayList<Cliente>();
	private String nombre_g;
	
	public GrupoClientes() {
	}

	public GrupoClientes(String name) {
		nombre_g = name;
	}

	public String getName() {
		return nombre_g;
	}

	public void setName(String name) {
		String oldValue = nombre_g;
		nombre_g = name;
		firePropertyChange("name", oldValue, nombre_g);
	}

	public void addPerson(Cliente client) {
		List<Cliente> oldValue = g_personas;
		g_personas = new ArrayList<Cliente>(g_personas);
		g_personas.add(client);
		firePropertyChange("persons", oldValue, g_personas);
		firePropertyChange("personCount", oldValue.size(), g_personas.size());
	}

	public void removePerson(Cliente cliente) {
		List<Cliente> oldValue = g_personas;
		g_personas = new ArrayList<Cliente>(g_personas);
		g_personas.remove(cliente);
		firePropertyChange("persons", oldValue, g_personas);
		firePropertyChange("personCount", oldValue.size(), g_personas.size());
	}

	public List<Cliente> getPersons() {
		return g_personas;
	}
	
	public int getPersonCount() {
		return g_personas.size();
	}

}
