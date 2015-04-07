import java.util.ArrayList;
import java.util.List;

public class GrupoVentas extends AbstractModelObject {
	private List<Venta> g_venta = new ArrayList<Venta>();
	private String nom_venta;

	public GrupoVentas() {

	}

	public GrupoVentas(String nom) {
		nom_venta = nom;
	}

	public String getNom_venta() {
		return nom_venta;
	}

	public void setNom_venta(String nom) {
		String oldValue = nom_venta;
		nom_venta = nom;
		firePropertyChange("nom_venta", oldValue, nom_venta);
	}

	public void addVenta(Venta vent) {
		List<Venta> oldValue = g_venta;
		g_venta = new ArrayList<Venta>(g_venta);
		g_venta.add(vent);
		firePropertyChange("venta", oldValue, g_venta);
		firePropertyChange("ventaCount", oldValue.size(), g_venta.size());
	}

	public void removeVenta(Venta vent) {
		List<Venta> oldValue = g_venta;
		g_venta = new ArrayList<Venta>(g_venta);
		g_venta.remove(vent);
		firePropertyChange("venta", oldValue, g_venta);
		firePropertyChange("ventaCount", oldValue.size(), g_venta.size());
	}

	public List<Venta> getVenta() {
		return g_venta;
	}

	public int getVentaCount() {
		return g_venta.size();
	}

}
