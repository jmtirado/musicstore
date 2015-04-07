import java.awt.CardLayout;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
import java.util.Scanner;

import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListCellRenderer;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.text.html.HTMLDocument.Iterator;

import org.jdesktop.beansbinding.AutoBinding.UpdateStrategy;
import org.jdesktop.beansbinding.BeanProperty;
import org.jdesktop.beansbinding.ELProperty;
import org.jdesktop.swingbinding.JComboBoxBinding;
import org.jdesktop.swingbinding.JListBinding;
import org.jdesktop.swingbinding.JTableBinding;
import org.jdesktop.swingbinding.SwingBindings;
import org.jdesktop.beansbinding.ObjectProperty;
import org.jdesktop.beansbinding.AutoBinding;
import org.jdesktop.beansbinding.Bindings;

import java.awt.Color;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class nuevaventana {

	private JFrame frmMusicshop;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private static GrupoClientes gclientes = new GrupoClientes("gclientes");
	private static GrupoMusicas gmusicas = new GrupoMusicas("gmusica");
	private static GrupoVentas gventas = new GrupoVentas("gventa");
	private JTable table;
	private JList list;
	private JTextField textField_5;
	private JTextField textField_6;
	private JTextField textField_7;
	private JTextField textField_8;
	private JTable table_musica;
	private JComboBox combo_clientes;
	private JComboBox combo_clientes_1;
	private JTextField poblacion;
	private JTextField direccion;
	private JTextField textField_10;
	private JComboBox comboBox_1;
	private JTextField textField_9;
	private JTextField textField_11;
	private JTextField textField_12;
	private JTextField textField_13;
	private JTable venta;
	private JList list_mus;
	private JList list_vent;
	//FICHERO DE TEXTO
	private static FileWriter escribir;
	public static void escribirArchivo() throws IOException
	{
		escribir = new FileWriter("datos.txt");
		PrintWriter pw = new PrintWriter(escribir);
		//DATOS A ESCRIBIR
		List<Venta> listaventa = gventas.getVenta();
		ListIterator<Venta> itervent = listaventa.listIterator();
		int i = 1;
		while (itervent.hasNext()) {
			Venta vaux = itervent.next();
			Cliente caux = vaux.getClienteo();
			Musica maux = vaux.getMusicao();

			pw.print(i + " - CLIENTE: " + caux.getNombre()
					+ " ");
			pw.print(caux.getApellidos() + " ");
			pw.print(caux.getDireccion() + " ");
			pw.print(caux.getPoblacion() + " ");
			pw.print(caux.getCP());

			pw.print("  MUSICA: " + maux.getAutor() + " ");
			pw.print(maux.getDisco() + " ");
			pw.print(maux.getAnyo() + " ");
			pw.print(maux.getGenero() + " ");
			pw.println(maux.getFormato());
			i++;
		}
		
		
		escribir.close();
	}

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		
		
		
		
		// VARIABLES CONSOLA
		Scanner leer = new Scanner(System.in);
		String instruc;
		boolean pedirentrada = true;
		final String salir = "SALIR";
		// DATOS PARA CREAR VENTA
		Cliente auxclientv = new Cliente();
		Musica auxmusv = new Musica();
		boolean exito = false;
		JOptionPane.showMessageDialog(null,
				"AVISO: para abrir el entrono gráfico escriba: abrir");
		instruc = "start";
		do {
			if (pedirentrada) {
				System.out
						.println(" *MENU* Esperando instrucción. Para salir escriba salir");
				instruc = leer.nextLine();
			}

			switch (instruc.toUpperCase()) {

			case "CREAR CLIENTE":
				Cliente aux = new Cliente();
				aux = aux.pedirDatos();
				gclientes.addPerson(aux);
				System.out.println("Cliente creado correctamente con codigo "
						+ gclientes.getPersonCount());
				pedirentrada = true;
				exito=false;
				break;

			case "CREAR MUSICA":
				Musica auxmus = new Musica();
				auxmus = auxmus.pedirDatos();
				gmusicas.addMusic(auxmus);
				System.out.println("Musica creada correctamente con codigo "
						+ gmusicas.getMusicCount());
				exito=false;
				break;

			case "CREAR VENTA":
				String codcliente;
				String codmusica;
				int pos;
				boolean terminado = false;
				// buscamos cliente y musica si no existen
				if (gclientes.getPersonCount() < 1) {
					System.err.println("No existen clientes");
					exito=true;
					terminado=true;
				} 
				if (gmusicas.getMusicCount() < 1) {
					System.err.println("No existen musicas");
					exito=true;
					terminado=true;
				}

				while (!exito) {
					pos = 1;
					System.out
							.println("Escriba atras para volver al menu.Inserte el codigo del cliente: ");
					codcliente = leer.nextLine();
					if (isNumeric(codcliente)) {
						int numcliente = Integer.parseInt(codcliente);
						if (numcliente > gclientes.getPersonCount()) {
							System.err
									.println("El cliente introducido no existe.Vuelva a intentarlo");
							pedirentrada = false;
							instruc = "CREAR VENTA";
							break;
						} else {

							List<Cliente> listaclientes = gclientes
									.getPersons();
							ListIterator<Cliente> iter = listaclientes
									.listIterator();
							while (iter.hasNext()) {
								if (pos == numcliente) {
									Cliente clientaux = iter.next();
									auxclientv = clientaux;
									exito = true;
									break;

								} else {
									pos++;
									iter.next();
								}

							}
							System.err.println("Cliente " + numcliente
									+ " encontrado satisfactoriamente.");
						}

					} else {
						if (codcliente.equals("atras")) {
							pedirentrada = true;
							exito = false;
							break;
						} else {
							System.err
									.println("Codigo erroneo. Vuelva a insertarlo.");
							pedirentrada = false;
							instruc = "crear venta";
							break;

						}

					}
				}

				// encontramos musica
				while (exito && !terminado) {
					pos = 1;
					System.out
							.println("Escriba atras para volver al menu.Inserte el codigo de la musica: ");
					codmusica = leer.nextLine();
					if (isNumeric(codmusica)) {
						int numusic = Integer.parseInt(codmusica);
						if (numusic > gmusicas.getMusicCount()) {
							System.err
									.println("La musica introducida no existe.Vuelva a intentarlo");
							pedirentrada = false;
							instruc = "crear venta";
							break;
						} else {

							List<Musica> listamusica = gmusicas.getMusica();

							ListIterator<Musica> iter = listamusica
									.listIterator();
							while (iter.hasNext()) {
								if (pos == numusic) {
									Musica musaux = iter.next();
									auxmusv = musaux; // musica almacenada
														// exitosamente
									// ahora crearmos la venta
									Venta ventaux = new Venta(auxclientv,
											auxmusv);
									gventas.addVenta(ventaux);
									System.err
											.println("Musica "
													+ numusic
													+ " encontrada satisfactoriamente.");
									System.err
											.println("Venta creada satisfactoriament con codig: "
													+ gventas.getVentaCount());
									terminado = true;
									exito = false;// para que cuando vuelva a
													// entrar pida codigo
													// cliente de nuevo
									break;

								} else {
									pos++;
									iter.next();
								}

							}

							pedirentrada = true;

						}
					} else {
						if (codmusica.equals("atras")) {
							pedirentrada = true;
							exito = false;
							break;
						} else {
							System.err
									.println("Codigo erroneo. Vuelva a insertarlo.");
							pedirentrada = false;
							instruc = "crear venta";
							break;

						}

					}
				}
				
				break;

			case "LISTAR CLIENTE":
			case "LISTAR CLIENTES":

				if (gclientes.getPersonCount() < 1) {
					System.err.println("No existen clientes");
				} else {
					List<Cliente> listaclientes = gclientes.getPersons();
					ListIterator<Cliente> iter = listaclientes.listIterator();
					System.out.println("--- LISTA DE CLIENTE ---");
					int i = 1;
					while (iter.hasNext()) {
						Cliente caux = iter.next();
						System.out.print(i + " - " + caux.getNombre() + " ");
						System.out.print(caux.getApellidos() + " ");
						System.out.print(caux.getDireccion() + " ");
						System.out.print(caux.getPoblacion() + " ");
						System.out.println(caux.getCP());
						i++;
					}

					System.out.println("Total Clientes: "
							+ gclientes.getPersonCount());
				}
				pedirentrada = true;
				break;

			case "LISTAR MUSICA":
			case "LISTAR MUSICAS":
				if (gmusicas.getMusicCount() < 1) {
					System.err.println("No existen musicas");
				} else {
					List<Musica> listamusica = gmusicas.getMusica();
					ListIterator<Musica> itermus = listamusica.listIterator();
					System.out.println("--- LISTA DE MUSICA ---");
					int i = 1;
					while (itermus.hasNext()) {
						Musica maux = itermus.next();

						System.out.print(i + " - " + maux.getAutor() + " ");
						System.out.print(maux.getDisco() + " ");
						System.out.print(maux.getAnyo() + " ");
						System.out.print(maux.getGenero() + " ");
						System.out.println(maux.getFormato());
						i++;
					}

					System.out.println("Total Musica: "
							+ gmusicas.getMusicCount());
				}

				break;

			case "LISTAR VENTAS":
			case "LISTAR VENTA":
				if (gventas.getVentaCount() < 1) {
					System.err.println("No existen Ventas.");
				} else {
					List<Venta> listaventa = gventas.getVenta();
					ListIterator<Venta> itervent = listaventa.listIterator();
					System.out.println("--- LISTA DE VENTAS ---");
					int i = 1;
					while (itervent.hasNext()) {
						Venta vaux = itervent.next();
						Cliente caux = vaux.getClienteo();
						Musica maux = vaux.getMusicao();

						System.out.print(i + " - CLIENTE: " + caux.getNombre()
								+ " ");
						System.out.print(caux.getApellidos() + " ");
						System.out.print(caux.getDireccion() + " ");
						System.out.print(caux.getPoblacion() + " ");
						System.out.print(caux.getCP());

						System.out.print("  MUSICA: " + maux.getAutor() + " ");
						System.out.print(maux.getDisco() + " ");
						System.out.print(maux.getAnyo() + " ");
						System.out.print(maux.getGenero() + " ");
						System.out.println(maux.getFormato());
						i++;
					}

					System.out.println("Total Ventas: "
							+ gventas.getVentaCount());
				}

				break;

			case "ELIMINAR CLIENTE":
				if (gclientes.getPersonCount() < 1) {
					System.err.println("No existen clientes");
					pedirentrada = true;
				} else {

					String cliente;
					pos = 1;
					System.out
							.println("Escriba atras para volver al menu.Inserte el codigo del cliente: ");
					cliente = leer.nextLine();
					if (isNumeric(cliente)) {
						int numcliente = Integer.parseInt(cliente);
						if (numcliente > gclientes.getPersonCount()) {
							System.err
									.println("El cliente introducido no existe.Vuelva a intentarlo");
							pedirentrada = false;
							instruc = "Eliminar Cliente";
							break;
						} else {

							List<Cliente> listaclientes = gclientes
									.getPersons();
							ListIterator<Cliente> iter = listaclientes
									.listIterator();
							while (iter.hasNext()) {
								if (pos == numcliente) {
									Cliente clientaux = iter.next();
									gclientes.removePerson(clientaux);
									break;

								} else {
									pos++;
									iter.next();
								}

							}
							System.err.println("Cliente " + numcliente
									+ " eliminado satisfactoriamente.");
							pedirentrada = true;

						}

					} else {
						if (cliente.equals("atras")) {
							pedirentrada = true;
							break;
						} else {
							System.err
									.println("Codigo erroneo. Vuelva a insertarlo.");
							pedirentrada = false;
							instruc = "Eliminar Cliente";
							break;

						}

					}

				}

				break;

			case "ELIMINAR MUSICA":
				if (gmusicas.getMusicCount() < 1) {
					System.err.println("No existen musicas");
					pedirentrada = true;
				} else {

					String musica;
					pos = 1;
					System.out
							.println("Escriba atras para volver al menu.Inserte el codigo de la musica: ");
					musica = leer.nextLine();
					if (isNumeric(musica)) {
						int numusic = Integer.parseInt(musica);
						if (numusic > gmusicas.getMusicCount()) {
							System.err
									.println("La musica introducida no existe.Vuelva a intentarlo");
							pedirentrada = false;
							instruc = "Eliminar musica";
							break;
						} else {

							List<Musica> listamusica = gmusicas.getMusica();

							ListIterator<Musica> iter = listamusica
									.listIterator();
							while (iter.hasNext()) {
								if (pos == numusic) {
									Musica musaux = iter.next();
									gmusicas.removeMusic(musaux);
									break;

								} else {
									pos++;
									iter.next();
								}

							}
							System.err.println("Musica " + numusic
									+ " eliminado satisfactoriamente.");
							pedirentrada = true;

						}

					} else {
						if (musica.equals("atras")) {
							pedirentrada = true;
							break;
						} else {
							System.err
									.println("Codigo erroneo. Vuelva a insertarlo.");
							pedirentrada = false;
							instruc = "Eliminar Musica";
							break;

						}

					}

				}

				break;

			case "ELIMINAR VENTA":
				if (gventas.getVentaCount() < 1) {
					System.err.println("No existen ventas.");
					pedirentrada = true;
				} else {

					String venta;
					pos = 1;
					System.out
							.println("Escriba atras para volver al menu.Inserte el codigo de la venta: ");
					venta = leer.nextLine();
					if (isNumeric(venta)) {
						int numventa = Integer.parseInt(venta);
						if (numventa > gventas.getVentaCount()) {
							System.err
									.println("La venta introducida no existe.Vuelva a intentarlo");
							pedirentrada = false;
							instruc = "Eliminar Venta";
							break;
						} else {

							List<Venta> listaventa = gventas.getVenta();
							ListIterator<Venta> iter = listaventa
									.listIterator();
							while (iter.hasNext()) {
								if (pos == numventa) {
									Venta venaux = iter.next();
									gventas.removeVenta(venaux);
									break;

								} else {
									pos++;
									iter.next();
								}

							}
							System.err.println("Venta " + numventa
									+ " eliminada satisfactoriamente.");
							pedirentrada = true;

						}

					} else {
						if (venta.equals("atras")) {
							pedirentrada = true;
							break;
						} else {
							System.err
									.println("Codigo erroneo. Vuelva a insertarlo.");
							pedirentrada = false;
							instruc = "Eliminar Venta";
							break;

						}

					}

				}
				break;

			case "ABRIR":
				// ENTORNO GRAFICO
				EventQueue.invokeLater(new Runnable() {
					@Override
					public void run() {
						try {
							nuevaventana window = new nuevaventana();
							window.frmMusicshop.setVisible(true);
						} catch (Exception e) {
							e.printStackTrace();

						}
					}
				}); // FIN
				
				instruc = "SALIR";
				break;

			case "SALIR":
				//escribe en el archivo
				try{
					escribirArchivo();
				}catch (IOException e)
				{
					System.out.println("Error al escrbir en el archivo" + e.getMessage());
				}
				
			
				System.out.println("FIN DEL PROGRAMA");
				break;

			default:
				System.out
						.println("Instruccion no valida. Vuelva a intentarlo.");
			}// end switch
		} while (!instruc.toUpperCase().equals(salir));

	}

	/**
	 * Create the application.
	 */
	public nuevaventana() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 * 
	 * @wbp.parser.entryPoint
	 */
	private void initialize() {
		frmMusicshop = new JFrame();
		frmMusicshop
				.setIconImage(Toolkit
						.getDefaultToolkit()
						.getImage(
								nuevaventana.class
										.getResource("/com/sun/java/swing/plaf/windows/icons/Warn.gif")));
		frmMusicshop.setTitle("Tienda de Musica");
		frmMusicshop.setBounds(100, 100, 612, 437);
		frmMusicshop.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmMusicshop.getContentPane().setLayout(null);

		final JPanel panel = new JPanel();
		panel.setBounds(0, 21, 596, 378);
		frmMusicshop.getContentPane().add(panel);
		panel.setLayout(new CardLayout(0, 0));

		// JPanel panel_1 = new JPanel();
		ImagePanel panel_1 = new ImagePanel(
				new ImageIcon("background.jpg").getImage());
		panel.add(panel_1, "PRINCIPAL");
		panel_1.setLayout(null);

		JPanel panel_2 = new JPanel();
		panel.add(panel_2, "CREAR_CLIENTE");
		panel_2.setLayout(null);

		JLabel lblNombre = new JLabel("Nombre:");
		lblNombre.setBounds(10, 37, 50, 14);
		panel_2.add(lblNombre);

		JLabel lblCrearCliente = new JLabel("CREAR CLIENTE");
		lblCrearCliente.setBounds(239, 0, 128, 18);
		lblCrearCliente.setFont(new Font("Verdana", Font.BOLD, 14));
		panel_2.add(lblCrearCliente);

		textField = new JTextField();
		textField.setBounds(77, 34, 152, 20);
		panel_2.add(textField);
		textField.setColumns(10);

		JLabel lblApellidos = new JLabel("Apellidos:");
		lblApellidos.setBounds(239, 37, 66, 14);
		panel_2.add(lblApellidos);

		textField_1 = new JTextField();
		textField_1.setBounds(315, 34, 246, 20);
		panel_2.add(textField_1);
		textField_1.setColumns(10);

		JLabel lblDireccion = new JLabel("Direccion: ");
		lblDireccion.setBounds(10, 100, 74, 14);
		panel_2.add(lblDireccion);

		textField_2 = new JTextField();
		textField_2.setBounds(77, 97, 152, 20);
		panel_2.add(textField_2);
		textField_2.setColumns(10);

		JLabel lblPoblacion = new JLabel("Poblacion:");
		lblPoblacion.setBounds(239, 100, 66, 14);
		panel_2.add(lblPoblacion);

		textField_3 = new JTextField();
		textField_3.setBounds(315, 97, 139, 20);
		panel_2.add(textField_3);
		textField_3.setColumns(10);

		JLabel lblCp = new JLabel("C.P:");
		lblCp.setBounds(468, 100, 46, 14);
		panel_2.add(lblCp);

		textField_4 = new JTextField();
		textField_4.setBounds(500, 97, 86, 20);
		panel_2.add(textField_4);
		textField_4.setColumns(10);

		JButton btnNewButton = new JButton("Cancelar");
		btnNewButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				CardLayout cl = (CardLayout) panel.getLayout();
				cl.show(panel, "PRINCIPAL");
			}
		});
		btnNewButton.setBounds(497, 344, 89, 23);
		panel_2.add(btnNewButton);

		JButton btnGuardar = new JButton("Guardar");
		btnGuardar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// GUARDAMOS DATOS CLIENTES
				Cliente aux = new Cliente(textField.getText(), textField_1
						.getText(), textField_2.getText(), textField_3
						.getText(), textField_4.getText());
				gclientes.addPerson(aux);
				JOptionPane.showMessageDialog(
						null,
						"Cliente creado correctamente con codigo "
								+ gclientes.getPersonCount());
				limpiar(textField);
				limpiar(textField_1);
				limpiar(textField_2);
				limpiar(textField_3);
				limpiar(textField_4);
			}
		});
		btnGuardar.setBounds(398, 344, 89, 23);
		panel_2.add(btnGuardar);

		JPanel panel_3 = new JPanel();
		panel.add(panel_3, "ELIMINAR_CLIENTE");
		panel_3.setLayout(null);

		JLabel lblPanel = new JLabel("ELIMINAR CLIENTE");
		lblPanel.setFont(new Font("Verdana", Font.BOLD, 14));
		lblPanel.setBounds(252, 5, 201, 14);
		panel_3.add(lblPanel);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 39, 576, 292);
		panel_3.add(scrollPane);

		table = new JTable();
		scrollPane.setViewportView(table);

		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				CardLayout cl = (CardLayout) panel.getLayout();
				cl.show(panel, "PRINCIPAL");
			}
		});
		btnCancelar.setBounds(497, 342, 89, 23);
		panel_3.add(btnCancelar);

		JButton btnEliminar = new JButton("Eliminar");
		btnEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Cliente person = gclientes.getPersons().get(
						table.getSelectedRow());
				int j = JOptionPane.showConfirmDialog(null,
						"¿ Seguro que quiere borrar ?", "Eliminar",
						JOptionPane.YES_NO_OPTION);
				if (j == 0) {
					// eliminamos cliente
					gclientes.removePerson(person);
					table.repaint();
				}

			}
		});
		btnEliminar.setBounds(398, 342, 89, 23);
		panel_3.add(btnEliminar);

		JPanel panel_4 = new JPanel();
		panel.add(panel_4, "LISTAR_CLIENTE");
		panel_4.setLayout(null);

		JLabel lblListaClientes = new JLabel("LISTA CLIENTES");
		lblListaClientes.setFont(new Font("Verdana", Font.BOLD, 14));
		lblListaClientes.setBounds(221, 0, 146, 28);
		panel_4.add(lblListaClientes);

		list = new JList();
		list.setBounds(10, 32, 576, 296);
		panel_4.add(list);

		JButton btnSalir = new JButton("Salir");
		btnSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CardLayout cl = (CardLayout) panel.getLayout();
				cl.show(panel, "PRINCIPAL");
			}
		});
		btnSalir.setBounds(497, 339, 89, 23);
		panel_4.add(btnSalir);

		JPanel panel_5 = new JPanel();
		panel.add(panel_5, "CREAR_MUSICA");
		panel_5.setLayout(null);

		JLabel lblCrearMusica = new JLabel("CREAR MUSICA");
		lblCrearMusica.setBounds(240, 11, 157, 14);
		lblCrearMusica.setFont(new Font("Verdana", Font.BOLD, 14));
		panel_5.add(lblCrearMusica);

		JLabel lblNombreDisco = new JLabel("Nombre Disco:");
		lblNombreDisco.setBounds(56, 47, 85, 14);
		panel_5.add(lblNombreDisco);

		textField_5 = new JTextField();
		textField_5.setBounds(151, 44, 141, 20);
		panel_5.add(textField_5);
		textField_5.setColumns(10);

		JLabel lblAutor = new JLabel("Autor:");
		lblAutor.setBounds(302, 47, 46, 14);
		panel_5.add(lblAutor);

		textField_6 = new JTextField();
		textField_6.setBounds(343, 44, 175, 20);
		panel_5.add(textField_6);
		textField_6.setColumns(10);

		JLabel lblGenero = new JLabel("Genero:");
		lblGenero.setBounds(84, 89, 57, 14);
		panel_5.add(lblGenero);

		textField_7 = new JTextField();
		textField_7.setBounds(151, 86, 141, 20);
		panel_5.add(textField_7);
		textField_7.setColumns(10);

		JLabel lblAo = new JLabel("A\u00F1o:");
		lblAo.setBounds(302, 89, 46, 14);
		panel_5.add(lblAo);

		textField_8 = new JTextField();
		textField_8.setBounds(343, 86, 85, 20);
		panel_5.add(textField_8);
		textField_8.setColumns(10);

		final JComboBox comboBox = new JComboBox();
		comboBox.setMaximumRowCount(3);
		comboBox.setModel(new DefaultComboBoxModel(new String[] { "CD",
				"Vinilo", "Casete" }));
		comboBox.setBounds(151, 117, 57, 20);
		panel_5.add(comboBox);

		JLabel lblFormato = new JLabel("Formato:");
		lblFormato.setBounds(84, 120, 57, 14);
		panel_5.add(lblFormato);

		JButton btnCancelar_1 = new JButton("Cancelar");
		btnCancelar_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CardLayout cl = (CardLayout) panel.getLayout();
				cl.show(panel, "PRINCIPAL");
			}
		});
		btnCancelar_1.setBounds(497, 344, 89, 23);
		panel_5.add(btnCancelar_1);

		JButton btnGuardar_1 = new JButton("Guardar");
		btnGuardar_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// GUARDAMOS DATOS CLIENTES
				Musica aux = new Musica(textField_5.getText(), textField_6
						.getText(), textField_7.getText(), textField_8
						.getText(), (String) comboBox.getSelectedItem());
				gmusicas.addMusic(aux);
				JOptionPane.showMessageDialog(
						null,
						"Musica creada correctamente con codigo "
								+ gmusicas.getMusicCount());
				limpiar(textField);
				limpiar(textField_5);
				limpiar(textField_6);
				limpiar(textField_7);
				limpiar(textField_8);
			}
		});
		btnGuardar_1.setBounds(398, 344, 89, 23);
		panel_5.add(btnGuardar_1);

		JPanel panel_6 = new JPanel();
		panel.add(panel_6, "ELIMINAR_MUSICA");
		panel_6.setLayout(null);

		JLabel lblElminarMusica = new JLabel("ELMINAR MUSICA");
		lblElminarMusica.setFont(new Font("Verdana", Font.BOLD, 14));
		lblElminarMusica.setBounds(232, 11, 174, 14);
		panel_6.add(lblElminarMusica);

		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(10, 31, 576, 303);
		panel_6.add(scrollPane_1);

		table_musica = new JTable();
		scrollPane_1.setViewportView(table_musica);

		JButton btnCancelar_2 = new JButton("Cancelar");
		btnCancelar_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				CardLayout cl = (CardLayout) panel.getLayout();
				cl.show(panel, "PRINCIPAL");
			}
		});
		btnCancelar_2.setBounds(497, 345, 89, 23);
		panel_6.add(btnCancelar_2);

		JButton btnEliminar_1 = new JButton("Eliminar");
		btnEliminar_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Musica music = gmusicas.getMusica().get(
						table_musica.getSelectedRow());
				int j = JOptionPane.showConfirmDialog(null,
						"¿ Seguro que quiere borrar ?", "Eliminar",
						JOptionPane.YES_NO_OPTION);
				if (j == 0) {
					// eliminamos musica
					gmusicas.removeMusic(music);
					table_musica.repaint();
				}
			}
		});
		btnEliminar_1.setBounds(398, 345, 89, 23);
		panel_6.add(btnEliminar_1);

		JPanel panel_7 = new JPanel();
		panel.add(panel_7, "LISTAR_MUSICA");
		panel_7.setLayout(null);

		JLabel lblListaMusica = new JLabel("LISTA MUSICA");
		lblListaMusica.setFont(new Font("Verdana", Font.BOLD, 14));
		lblListaMusica.setBounds(218, 0, 146, 28);
		panel_7.add(lblListaMusica);

		JButton btnSalir_1 = new JButton("Salir");
		btnSalir_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CardLayout cl = (CardLayout) panel.getLayout();
				cl.show(panel, "PRINCIPAL");
			}
		});
		btnSalir_1.setBounds(497, 344, 89, 23);
		panel_7.add(btnSalir_1);

		list_mus = new JList();
		list_mus.setBounds(10, 27, 576, 294);
		panel_7.add(list_mus);

		JPanel panel_8 = new JPanel();
		panel.add(panel_8, "CREAR_VENTA");
		panel_8.setLayout(null);

		JLabel lblCrearVenta = new JLabel("CREAR VENTA");
		lblCrearVenta.setBounds(242, 0, 112, 18);
		lblCrearVenta.setFont(new Font("Verdana", Font.BOLD, 14));
		panel_8.add(lblCrearVenta);

		JLabel lblClientes = new JLabel("Clientes:");
		lblClientes.setBounds(10, 35, 55, 14);
		panel_8.add(lblClientes);

		combo_clientes_1 = new JComboBox();
		combo_clientes_1.setEditable(false);
		combo_clientes_1.setRenderer(new DefaultListCellRenderer() {
			@Override
			public Component getListCellRendererComponent(JList list,
					Object value, int index, boolean isSelected,
					boolean cellHasFocus) {
				super.getListCellRendererComponent(list, value, index,
						isSelected, cellHasFocus);
				if (value instanceof Cliente) {
					Cliente mec = (Cliente) value;
					String client = mec.getNombre() + " " + mec.getApellidos();
					setText(client);
				}
				return this;
			}
		});
		combo_clientes_1.setBounds(69, 29, 174, 27);
		panel_8.add(combo_clientes_1);

		poblacion = new JTextField();
		poblacion.setEnabled(false);
		poblacion.setBounds(318, 67, 167, 27);
		panel_8.add(poblacion);
		poblacion.setColumns(10);

		JLabel lblPoblacion_1 = new JLabel("Poblacion:");
		lblPoblacion_1.setBounds(253, 73, 65, 14);
		panel_8.add(lblPoblacion_1);

		JLabel lblDireccin = new JLabel("Direcci\u00F3n:");
		lblDireccin.setBounds(10, 73, 65, 14);
		panel_8.add(lblDireccin);

		direccion = new JTextField();
		direccion.setEnabled(false);
		direccion.setForeground(Color.BLACK);
		direccion.setBounds(69, 67, 174, 27);
		panel_8.add(direccion);
		direccion.setColumns(10);

		JLabel cp = new JLabel("CP:");
		cp.setBounds(497, 73, 35, 14);
		panel_8.add(cp);

		textField_10 = new JTextField();
		textField_10.setEnabled(false);
		textField_10.setBounds(519, 67, 55, 27);
		panel_8.add(textField_10);
		textField_10.setColumns(10);

		JLabel lblMusica = new JLabel("Musica:");
		lblMusica.setBounds(10, 145, 46, 14);
		panel_8.add(lblMusica);

		comboBox_1 = new JComboBox();
		comboBox_1.setBounds(69, 139, 174, 27);
		comboBox_1.setEditable(false);
		comboBox_1.setRenderer(new DefaultListCellRenderer() {
			@Override
			public Component getListCellRendererComponent(JList list,
					Object value, int index, boolean isSelected,
					boolean cellHasFocus) {
				super.getListCellRendererComponent(list, value, index,
						isSelected, cellHasFocus);
				if (value instanceof Musica) {
					Musica mec = (Musica) value;
					String client = mec.getDisco();
					setText(client);
				}
				return this;
			}
		});
		panel_8.add(comboBox_1);

		JLabel lblAutor_1 = new JLabel("Autor:");
		lblAutor_1.setBounds(10, 202, 46, 14);
		panel_8.add(lblAutor_1);

		textField_9 = new JTextField();
		textField_9.setEnabled(false);
		textField_9.setBounds(69, 196, 174, 27);
		panel_8.add(textField_9);
		textField_9.setColumns(10);

		JLabel lblGenero_1 = new JLabel("Genero:");
		lblGenero_1.setBounds(253, 202, 46, 14);
		panel_8.add(lblGenero_1);

		JLabel lblAo_1 = new JLabel("A\u00F1o:");
		lblAo_1.setBounds(476, 202, 46, 14);
		panel_8.add(lblAo_1);

		textField_11 = new JTextField();
		textField_11.setEnabled(false);
		textField_11.setBounds(318, 196, 148, 27);
		panel_8.add(textField_11);
		textField_11.setColumns(10);

		textField_12 = new JTextField();
		textField_12.setEnabled(false);
		textField_12.setBounds(519, 196, 67, 27);
		panel_8.add(textField_12);
		textField_12.setColumns(10);

		JLabel lblFormato_1 = new JLabel("Formato:");
		lblFormato_1.setBounds(10, 253, 55, 14);
		panel_8.add(lblFormato_1);

		textField_13 = new JTextField();
		textField_13.setEnabled(false);
		textField_13.setBounds(69, 247, 122, 27);
		panel_8.add(textField_13);
		textField_13.setColumns(10);

		JButton btnCancelar_3 = new JButton("Cancelar");
		btnCancelar_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				CardLayout cl = (CardLayout) panel.getLayout();
				cl.show(panel, "PRINCIPAL");
			}
		});
		btnCancelar_3.setBounds(497, 344, 89, 23);
		panel_8.add(btnCancelar_3);

		JButton btnGuardar_2 = new JButton("Guardar");
		btnGuardar_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// GUARDAMOS LA VENTA
				Cliente clienteselec = (Cliente) combo_clientes_1
						.getSelectedItem();

				Musica musicaselec = (Musica) comboBox_1.getSelectedItem();
				// System.err.println(clienteselec.getNombre());
				// System.err.println(musicaselec.getAutor());
				Venta ven = new Venta(clienteselec, musicaselec);
				gventas.addVenta(ven);
				try{
					escribirArchivo();
				}catch (IOException en)
				{
					System.out.println("Error al escrbir en el archivo" + en.getMessage());
				}
				JOptionPane.showMessageDialog(
						null,
						"Venta creada correctamente con codigo "
								+ gventas.getVentaCount());
				
			}
		});
		btnGuardar_2.setBounds(401, 344, 89, 23);
		panel_8.add(btnGuardar_2);

		JPanel panel_9 = new JPanel();
		panel.add(panel_9, "ELIMINAR_VENTA");
		panel_9.setLayout(null);

		JLabel elim_venta = new JLabel("ELIMINAR VENTA");
		elim_venta.setFont(new Font("Verdana", Font.BOLD, 14));
		elim_venta.setBounds(196, 11, 201, 14);
		panel_9.add(elim_venta);

		JScrollPane scrollPane_2 = new JScrollPane();
		scrollPane_2.setBounds(10, 37, 576, 296);
		panel_9.add(scrollPane_2);

		venta = new JTable();
		scrollPane_2.setColumnHeaderView(venta);

		JButton btnCancelar_4 = new JButton("Cancelar");
		btnCancelar_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				CardLayout cl = (CardLayout) panel.getLayout();
				cl.show(panel, "PRINCIPAL");
			}
		});
		btnCancelar_4.setBounds(497, 344, 89, 23);
		panel_9.add(btnCancelar_4);

		JButton btnEliminar_2 = new JButton("Eliminar");
		btnEliminar_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// eliminamos venta
				Venta vent = gventas.getVenta().get(venta.getSelectedRow());
				int j = JOptionPane.showConfirmDialog(null,
						"¿ Seguro que quiere borrar ?", "Eliminar",
						JOptionPane.YES_NO_OPTION);
				if (j == 0) {
					// eliminamos venta
					gventas.removeVenta(vent);
					try{
						escribirArchivo();
					}catch (IOException ek)
					{
						System.out.println("Error al escrbir en el archivo" + ek.getMessage());
					}
					venta.repaint();
				}

			}
		});
		btnEliminar_2.setBounds(398, 344, 89, 23);
		panel_9.add(btnEliminar_2);

		JPanel panel_10 = new JPanel();
		panel.add(panel_10, "LISTAR_VENTA");
		panel_10.setLayout(null);

		JLabel lblListaVentas = new JLabel("LISTA VENTAS");
		lblListaVentas.setFont(new Font("Verdana", Font.BOLD, 14));
		lblListaVentas.setBounds(233, 0, 146, 28);
		panel_10.add(lblListaVentas);

		JButton btnSalir_2 = new JButton("Salir");
		btnSalir_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CardLayout cl = (CardLayout) panel.getLayout();
				cl.show(panel, "PRINCIPAL");
			}
		});
		btnSalir_2.setBounds(497, 344, 89, 23);
		panel_10.add(btnSalir_2);

		list_vent = new JList();
		list_vent.setBounds(10, 27, 576, 306);
		panel_10.add(list_vent);

		JMenuBar menuBar = new JMenuBar();
		menuBar.setBounds(0, 0, 596, 21);
		frmMusicshop.getContentPane().add(menuBar);

		JMenu mnClientes = new JMenu("Clientes");
		menuBar.add(mnClientes);

		JMenuItem mntmCrear = new JMenuItem("Crear");
		mntmCrear.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				CardLayout cl = (CardLayout) panel.getLayout();
				cl.show(panel, "CREAR_CLIENTE");

			}
		});
		mnClientes.add(mntmCrear);

		JMenuItem mntmEliminar = new JMenuItem("Eliminar");
		mntmEliminar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				CardLayout cl = (CardLayout) panel.getLayout();
				cl.show(panel, "ELIMINAR_CLIENTE");
			}
		});
		mnClientes.add(mntmEliminar);

		JMenuItem mntmListar = new JMenuItem("Listar");
		mntmListar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CardLayout cl = (CardLayout) panel.getLayout();
				cl.show(panel, "LISTAR_CLIENTE");
			}
		});
		mnClientes.add(mntmListar);

		JMenu mnMusica = new JMenu("Musica");
		menuBar.add(mnMusica);

		JMenuItem mntmCrear_1 = new JMenuItem("Crear");
		mntmCrear_1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				CardLayout cl = (CardLayout) panel.getLayout();
				cl.show(panel, "CREAR_MUSICA");
			}
		});
		mnMusica.add(mntmCrear_1);

		JMenuItem mntmEliminar_1 = new JMenuItem("Eliminar");
		mntmEliminar_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CardLayout cl = (CardLayout) panel.getLayout();
				cl.show(panel, "ELIMINAR_MUSICA");
			}
		});
		mnMusica.add(mntmEliminar_1);

		JMenuItem mntmListar_1 = new JMenuItem("Listar");
		mntmListar_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CardLayout cl = (CardLayout) panel.getLayout();
				cl.show(panel, "LISTAR_MUSICA");
			}
		});
		mnMusica.add(mntmListar_1);

		JMenu mnVenta = new JMenu("Venta");
		menuBar.add(mnVenta);

		JMenuItem mntmCrear_2 = new JMenuItem("Crear");
		mntmCrear_2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				CardLayout cl = (CardLayout) panel.getLayout();
				cl.show(panel, "CREAR_VENTA");
			}
		});
		mnVenta.add(mntmCrear_2);

		JMenuItem mntmEliminar_2 = new JMenuItem("Eliminar");
		mntmEliminar_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CardLayout cl = (CardLayout) panel.getLayout();
				cl.show(panel, "ELIMINAR_VENTA");
			}
		});
		mnVenta.add(mntmEliminar_2);

		JMenuItem mntmListar_2 = new JMenuItem("Listar");
		mntmListar_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CardLayout cl = (CardLayout) panel.getLayout();
				cl.show(panel, "LISTAR_VENTA");
			}
		});
		mnVenta.add(mntmListar_2);
		initDataBindings();
	}

	private static void addPopup(Component component, final JPopupMenu popup) {
		component.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}

			private void showMenu(MouseEvent e) {
				popup.show(e.getComponent(), e.getX(), e.getY());
			}
		});
	}

	protected void limpiar(Component component) {
		if (component instanceof JTextField) {
			JTextField text = (JTextField) component;
			text.setText("");
		} else {
			if (component instanceof Container) {
				for (Component c : ((Container) component).getComponents()) {
					limpiar(c);
				}
			}
		}
	}

	class ImagePanel extends JPanel {

		private Image img;

		public ImagePanel(String img) {
			this(new ImageIcon(img).getImage());
		}

		public ImagePanel(Image img) {
			this.img = img;
			Dimension size = new Dimension(img.getWidth(null),
					img.getHeight(null));
			setPreferredSize(size);
			setMinimumSize(size);
			setMaximumSize(size);
			setSize(size);
			setLayout(null);
		}

		public void paintComponent(Graphics g) {
			g.drawImage(img, 0, 0, null);
		}

	}

	public JComboBox getCombo_clientes() {
		return combo_clientes;
	}

	public static boolean isNumeric(String cadena) {
		try {
			Integer.parseInt(cadena);
			return true;
		} catch (NumberFormatException nfe) {
			return false;
		}
	}

	protected void initDataBindings() {
		BeanProperty<GrupoClientes, List<Cliente>> grupoClientesBeanProperty = BeanProperty
				.create("persons");
		JTableBinding<Cliente, GrupoClientes, JTable> jTableBinding = SwingBindings
				.createJTableBinding(UpdateStrategy.READ, gclientes,
						grupoClientesBeanProperty, table);
		//
		BeanProperty<Cliente, String> clienteBeanProperty = BeanProperty
				.create("nombre");
		jTableBinding.addColumnBinding(clienteBeanProperty).setColumnName(
				"Nombre");
		//
		BeanProperty<Cliente, String> clienteBeanProperty_1 = BeanProperty
				.create("apellidos");
		jTableBinding.addColumnBinding(clienteBeanProperty_1).setColumnName(
				"Apellidos");
		//
		BeanProperty<Cliente, String> clienteBeanProperty_2 = BeanProperty
				.create("poblacion");
		jTableBinding.addColumnBinding(clienteBeanProperty_2).setColumnName(
				"Poblacion");
		//
		jTableBinding.bind();
		//
		JListBinding<Cliente, GrupoClientes, JList> jListBinding = SwingBindings
				.createJListBinding(UpdateStrategy.READ, gclientes,
						grupoClientesBeanProperty, list);
		//
		ELProperty<Cliente, Object> clienteEvalutionProperty = ELProperty
				.create("Nombre:   ${nombre}   Apellidos:  ${apellidos}  Poblacion: ${poblacion}  CP: ${CP}");
		jListBinding.setDetailBinding(clienteEvalutionProperty);
		//
		jListBinding.bind();
		//
		BeanProperty<GrupoMusicas, List<Musica>> grupoMusicasBeanProperty = BeanProperty
				.create("musica");
		JTableBinding<Musica, GrupoMusicas, JTable> jTableBinding_1 = SwingBindings
				.createJTableBinding(UpdateStrategy.READ, gmusicas,
						grupoMusicasBeanProperty, table_musica);
		//
		BeanProperty<Musica, String> musicaBeanProperty = BeanProperty
				.create("disco");
		jTableBinding_1.addColumnBinding(musicaBeanProperty).setColumnName(
				"Disco");
		//
		BeanProperty<Musica, String> musicaBeanProperty_1 = BeanProperty
				.create("autor");
		jTableBinding_1.addColumnBinding(musicaBeanProperty_1).setColumnName(
				"Autor");
		//
		BeanProperty<Musica, String> musicaBeanProperty_2 = BeanProperty
				.create("genero");
		jTableBinding_1.addColumnBinding(musicaBeanProperty_2).setColumnName(
				"Genero");
		//
		BeanProperty<Musica, String> musicaBeanProperty_3 = BeanProperty
				.create("formato");
		jTableBinding_1.addColumnBinding(musicaBeanProperty_3).setColumnName(
				"Formato");
		//
		jTableBinding_1.bind();
		//
		JComboBoxBinding<Cliente, GrupoClientes, JComboBox> jComboBinding = SwingBindings
				.createJComboBoxBinding(UpdateStrategy.READ, gclientes,
						grupoClientesBeanProperty, combo_clientes_1);
		jComboBinding.bind();
		//
		BeanProperty<JComboBox, String> jComboBoxBeanProperty = BeanProperty
				.create("selectedItem.poblacion");
		BeanProperty<JTextField, String> jTextFieldBeanProperty = BeanProperty
				.create("text");
		AutoBinding<JComboBox, String, JTextField, String> autoBinding = Bindings
				.createAutoBinding(UpdateStrategy.READ, combo_clientes_1,
						jComboBoxBeanProperty, poblacion,
						jTextFieldBeanProperty);
		autoBinding.bind();
		//
		BeanProperty<JComboBox, String> jComboBoxBeanProperty_1 = BeanProperty
				.create("selectedItem.direccion");
		BeanProperty<JTextField, String> jTextFieldBeanProperty_1 = BeanProperty
				.create("text");
		AutoBinding<JComboBox, String, JTextField, String> autoBinding_1 = Bindings
				.createAutoBinding(UpdateStrategy.READ, combo_clientes_1,
						jComboBoxBeanProperty_1, direccion,
						jTextFieldBeanProperty_1);
		autoBinding_1.bind();
		//
		BeanProperty<JComboBox, String> jComboBoxBeanProperty_2 = BeanProperty
				.create("selectedItem.CP");
		BeanProperty<JTextField, String> jTextFieldBeanProperty_2 = BeanProperty
				.create("text");
		AutoBinding<JComboBox, String, JTextField, String> autoBinding_2 = Bindings
				.createAutoBinding(UpdateStrategy.READ, combo_clientes_1,
						jComboBoxBeanProperty_2, textField_10,
						jTextFieldBeanProperty_2);
		autoBinding_2.bind();
		//
		JComboBoxBinding<Musica, GrupoMusicas, JComboBox> jComboBinding_1 = SwingBindings
				.createJComboBoxBinding(UpdateStrategy.READ, gmusicas,
						grupoMusicasBeanProperty, comboBox_1);
		jComboBinding_1.bind();
		//
		BeanProperty<JComboBox, String> jComboBoxBeanProperty_3 = BeanProperty
				.create("selectedItem.autor");
		BeanProperty<JTextField, String> jTextFieldBeanProperty_3 = BeanProperty
				.create("text");
		AutoBinding<JComboBox, String, JTextField, String> autoBinding_3 = Bindings
				.createAutoBinding(UpdateStrategy.READ, comboBox_1,
						jComboBoxBeanProperty_3, textField_9,
						jTextFieldBeanProperty_3);
		autoBinding_3.bind();
		//
		BeanProperty<JComboBox, String> jComboBoxBeanProperty_4 = BeanProperty
				.create("selectedItem.genero");
		BeanProperty<JTextField, String> jTextFieldBeanProperty_4 = BeanProperty
				.create("text");
		AutoBinding<JComboBox, String, JTextField, String> autoBinding_4 = Bindings
				.createAutoBinding(UpdateStrategy.READ, comboBox_1,
						jComboBoxBeanProperty_4, textField_11,
						jTextFieldBeanProperty_4);
		autoBinding_4.bind();
		//
		BeanProperty<JComboBox, String> jComboBoxBeanProperty_5 = BeanProperty
				.create("selectedItem.anyo");
		BeanProperty<JTextField, String> jTextFieldBeanProperty_5 = BeanProperty
				.create("text");
		AutoBinding<JComboBox, String, JTextField, String> autoBinding_5 = Bindings
				.createAutoBinding(UpdateStrategy.READ, comboBox_1,
						jComboBoxBeanProperty_5, textField_12,
						jTextFieldBeanProperty_5);
		autoBinding_5.bind();
		//
		BeanProperty<JComboBox, String> jComboBoxBeanProperty_6 = BeanProperty
				.create("selectedItem.formato");
		BeanProperty<JTextField, String> jTextFieldBeanProperty_6 = BeanProperty
				.create("text");
		AutoBinding<JComboBox, String, JTextField, String> autoBinding_6 = Bindings
				.createAutoBinding(UpdateStrategy.READ, comboBox_1,
						jComboBoxBeanProperty_6, textField_13,
						jTextFieldBeanProperty_6);
		autoBinding_6.bind();
		//
		BeanProperty<GrupoVentas, List<Venta>> grupoVentasBeanProperty = BeanProperty
				.create("venta");
		JTableBinding<Venta, GrupoVentas, JTable> jTableBinding_2 = SwingBindings
				.createJTableBinding(UpdateStrategy.READ, gventas,
						grupoVentasBeanProperty, venta);
		//
		ELProperty<Venta, Object> ventaEvalutionProperty = ELProperty
				.create("Nombre: ${clienteo.nombre} Apellidos: ${clienteo.apellidos} ");
		jTableBinding_2.addColumnBinding(ventaEvalutionProperty).setColumnName(
				"Cliente");
		//
		ELProperty<Venta, Object> ventaEvalutionProperty_1 = ELProperty
				.create("Disco: ${musicao.disco} Autor: ${musicao.autor} Formato: ${musicao.formato}");
		jTableBinding_2.addColumnBinding(ventaEvalutionProperty_1)
				.setColumnName("Musica");
		//
		jTableBinding_2.setEditable(false);
		jTableBinding_2.bind();
		//
		JListBinding<Musica, GrupoMusicas, JList> jListBinding_1 = SwingBindings
				.createJListBinding(UpdateStrategy.READ, gmusicas,
						grupoMusicasBeanProperty, list_mus);
		//
		ELProperty<Musica, Object> musicaEvalutionProperty = ELProperty
				.create("Disco: ${disco}   Autor: ${autor}  Genero: ${genero}  Formato: ${formato}");
		jListBinding_1.setDetailBinding(musicaEvalutionProperty);
		//
		jListBinding_1.bind();
		//
		JListBinding<Venta, GrupoVentas, JList> jListBinding_2 = SwingBindings
				.createJListBinding(UpdateStrategy.READ, gventas,
						grupoVentasBeanProperty, list_vent);
		//
		ELProperty<Venta, Object> ventaEvalutionProperty_2 = ELProperty
				.create("CLIENTE - Nombre: ${clienteo.nombre} ${clienteo.apellidos}  MUSICA- Disco: ${musicao.disco}  Autor: ${musicao.autor} Formato: ${musicao.formato}");
		jListBinding_2.setDetailBinding(ventaEvalutionProperty_2);
		//
		jListBinding_2.bind();
	}
}
