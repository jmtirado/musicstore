
public class Venta extends AbstractModelObject{
	private Cliente clienteo;
	private Musica musicao;
	
	
	Venta()
	{
		
	}
	Venta(Cliente caux, Musica cmus)
	{
		this.clienteo=caux;
		this.musicao=cmus;
	}
	
	
	//GETTERS AN SETTER
	public Cliente getClienteo() {
		return clienteo;
	}
	public void setClienteo(Cliente clienteo) {
		this.clienteo = clienteo;
	}
	public Musica getMusicao() {
		return musicao;
	}
	public void setMusicao(Musica musicao) {
		this.musicao = musicao;
	}
	
	

}
