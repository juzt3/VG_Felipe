package Pantallas;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;

import javax.swing.JComponent;

import opciones.Constantes;

public class PantallaOpciones extends JComponent implements Constantes{
	
	private String opciones[] = {
			"I.A Jugador",
			"I.A Enemigos",
			"Música",
			"Cerrar Juego"
	};
	
	private int seleccion = 0;
	
	public PantallaOpciones() {
		this.setBackground(Color.BLACK);
	}
	
	@Override
	public void paint(Graphics g) {
		super.paint(g);
		
		//Titulo
		Font font1 = new Font("Arial", Font.BOLD, 50);
		g.setFont(font1);
		g.setColor(Color.WHITE);
		FontMetrics metr = this.getFontMetrics(font1);
        g.drawString("Opciones", (anchoMundo - metr.stringWidth("Opciones")) / 2, 100);
        //Opciones
        //I.A Jugador
        if(seleccion == 0){
        	g.setColor(Color.red);
        }else{
        	g.setColor(Color.white);
        }
        g.drawString(opciones[0], (anchoMundo - metr.stringWidth(opciones[0])) / 2, 300);
        //I.A Enemigos
        if(seleccion == 1){
        	g.setColor(Color.red);
        }else{
        	g.setColor(Color.white);
        }
        g.drawString(opciones[1], (anchoMundo - metr.stringWidth(opciones[0])) / 2, 350);
        //Música
        if(seleccion == 2){
        	g.setColor(Color.red);
        }else{
        	g.setColor(Color.white);
        }
        g.drawString(opciones[2], (anchoMundo - metr.stringWidth(opciones[0])) / 2, 400);
        //opciones de musica
        
        //Cerrar Juego
        if(seleccion == 3){
        	g.setColor(Color.red);
        }else{
        	g.setColor(Color.white);
        }
        g.drawString(opciones[3], (anchoMundo - metr.stringWidth(opciones[0])) / 2, 450);
        
        Toolkit.getDefaultToolkit().sync();
		g.dispose();
	}
	
	public void keyPressed(KeyEvent evt){
		int key = evt.getKeyCode();


        if (key == KeyEvent.VK_UP) {
            if(seleccion == 0){
            	seleccion = 1;
            }else{
            	seleccion -= 1;
            }
        	
        }

        if (key == KeyEvent.VK_DOWN) {
            if(seleccion == 3){
            	seleccion = 0;
            }else{
            	seleccion += 1;
            }
        }
	}
	
	public int getSeleccion(){
		return seleccion;
	}

}