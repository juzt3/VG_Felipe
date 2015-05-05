package Pantallas;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import opciones.Constantes;


public class PantallaPrincipal extends JPanel implements Constantes {
	
	BufferedImage fondo;
	String[] opciones = {
			"Jugar", "Salir"
	};
	int seleccion = 0;
	
	public PantallaPrincipal(){
		this.setFocusable(true);
		this.setBackground(Color.black);
		try{
			fondo = ImageIO.read(new File("images/backmain.png"));
		}
		catch(IOException e){
			System.out.println(e.toString());
		}
	}

	public void paint(Graphics g){
		
		super.paint(g);
		
		Graphics2D g2d = (Graphics2D)g;
		
		//Fondo
		g2d.drawImage(fondo, 0, 0, null);
		//Titulo
		Font font1 = new Font("Arial", Font.BOLD, 50);
		g.setFont(font1);
		g.setColor(Color.WHITE);
		FontMetrics metr = this.getFontMetrics(font1);
        g.drawString("I.A DEMO", (anchoMundo - metr.stringWidth("I.A DEMO")) / 2, 100);
        if(seleccion == 0){
        	g.setColor(Color.red);
        }else{
        	g.setColor(Color.white);
        }
        g.drawString(opciones[0], (anchoMundo - metr.stringWidth(opciones[0])) / 2, 500);
        if(seleccion == 1){
        	g.setColor(Color.red);
        }else{
        	g.setColor(Color.white);
        }
        g.drawString(opciones[1], (anchoMundo - metr.stringWidth(opciones[1])) / 2, 600);
		
		
        Toolkit.getDefaultToolkit().sync();
		g.dispose();
	}
	/*	Para el game manager
	private void select() {
		if(seleccion == 0){
			this.enjuego = true;
		}
		else{
			System.exit(0);
		}
	}
	*/
	
	public void keyPressed(KeyEvent evt){
		int key = evt.getKeyCode();


        if (key == KeyEvent.VK_UP) {
            if(seleccion == 0){
            	seleccion = 1;
            }else{
            	seleccion = 0;
            }
        	
        }

        if (key == KeyEvent.VK_DOWN) {
            if(seleccion == 1){
            	seleccion = 0;
            }else{
            	seleccion = 1;
            }
        }
        /*
        if (key == KeyEvent.VK_ENTER){
        	select();
        }
        */
	}
}
