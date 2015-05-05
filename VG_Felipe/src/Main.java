import javax.swing.JFrame;

import Pantallas.PantallaPrincipal;
import opciones.Constantes;

public class Main extends JFrame implements Constantes {
	private	PantallaPrincipal pp;
	
	public Main() {
		
		pp = new PantallaPrincipal();
		add(pp);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(anchoMundo, altoMundo);
        setLocationRelativeTo(null);
        setTitle("VideoJuego I.A.");
        setResizable(false);
        setVisible(true);
	}
	
	public static void main(String[] args) {
		new Main();
	}

	

}
