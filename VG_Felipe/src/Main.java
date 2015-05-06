import javax.swing.JFrame;

import managers.GameManager;
import opciones.Constantes;

public class Main extends JFrame implements Constantes {
	private	GameManager gm;
	
	public Main() {
		
		gm = new GameManager();
		add(gm);
		
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
