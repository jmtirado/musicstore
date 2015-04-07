import java.util.ArrayList;
import java.util.List;

public class GrupoMusicas extends AbstractModelObject{
	private List<Musica> g_musica = new ArrayList<Musica>();
	private String nom_mus;
	
	public GrupoMusicas()
	{
		
	}
	public GrupoMusicas(String nom)
	{
		nom_mus=nom;
	}
	public String getNom_mus() {
		return nom_mus;
	}
	public void setNom_mus(String nom) {
		String oldValue = nom_mus;
		nom_mus = nom;
		firePropertyChange("nom_mus", oldValue, nom_mus);
	}
	public void addMusic(Musica music) {
		List<Musica> oldValue = g_musica;
		g_musica = new ArrayList<Musica>(g_musica);
		g_musica.add(music);
		firePropertyChange("musica", oldValue, g_musica);
		firePropertyChange("musicCount", oldValue.size(), g_musica.size());
	}

	public void removeMusic(Musica music) {
		List<Musica> oldValue = g_musica;
		g_musica = new ArrayList<Musica>(g_musica);
		g_musica.remove(music);
		firePropertyChange("musica", oldValue, g_musica);
		firePropertyChange("musicCount", oldValue.size(), g_musica.size());
	}

	public List<Musica> getMusica() {
		return g_musica;
	}
	
	public int getMusicCount() {
		return g_musica.size();
	}
	

}
