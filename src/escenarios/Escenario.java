package escenarios;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import javax.swing.JComponent;

import opciones.Constantes;
import Musica.HiloMusica;
import personajes.Casa;
import personajes.Enemigos;
import personajes.Jugador;
import personajes.Objetivo;
import personajes.Obstaculo;

public class Escenario extends JComponent implements ActionListener, Constantes{
	//Personajes
	public static Jugador jugador;
	public static ArrayList<Enemigos> enemigos;
	public static ArrayList<Obstaculo> obstaculos;
	public static ArrayList<Objetivo> objetivos;
	public static Casa casa;
	//Otros atributos
	private boolean enjuego;
	public HiloMusica player;
	private int num_objetivos;
	
	public Escenario() {
		this.jugador = new Jugador(64,64);
		this.setFocusable(true);
		this.requestFocus();
		this.setBackground(Color.BLACK);
		this.setDoubleBuffered(true);
		this.enjuego = true;
		
		player = new HiloMusica(ruta+"/music/music.wav", 2);
		
		initObstaculos();
		initEnemigos();
		initObjetivos();
		initCasa();

	}
	
	private void initCasa(){
		casa = new Casa(64,128);
	}
	
	private void initObstaculos() {
		int[][] pos = {
				//arriba
				{64*0,0}, {64*1,0}, {64*2,0}, {64*3,0}, {64*4,0}, {64*5,0}, {64*6,0}, {64*7,0}, {64*8,0}, {64*9,0}, {64*10,0},
				 {64*11,0}, {64*12,0}, {64*13,0}, {64*14,0}, {64*15,0}, {64*16,0}, {64*17,0}, {64*18,0}, {64*19,0}, {64*20,0},
				//abajo
				{0,64*10}, {64,64*10}, {128,64*10}, {192,64*10}, {256,64*10}
				,{320,64*10}, {384,64*10}, {448,64*10}, {512,64*10}, {576,64*10}
				, {640,64*10}, {704,64*10}, {768,64*10}, {832,64*10}, {896,64*10}
				, {960,64*10}, {1024,64*10}, {1088,64*10}, {1152,64*10}, {1216,64*10}
				, {1264,64*10},
				//izq
				{0,64}, {0,128}, {0,192}, {0,256}, {0,320}, {0,384}, {0,448}, {0,512}, {0,576},
				//der
				{1216,64}, {1216,128}, {1216,192}, {1216,256}, {1216,320}, {1216,384}, {1216,448}, {1216,512}, {1216,576},
				//puzzle
				{64*5,64*1}, {64*5,64*2}, {64*6,64*2}, {64*7,64*2}, {64*11,64*2}, {64*5,64*14}
				, {64*17,64*2}, {64*18,64*2}
				, {64*2,64*3}, {64*8,64*3}, {64*11,64*3}, {64*14,64*3}
				, {64*1,64*4}, {64*2,64*4}, {64*3,64*4}, {64*4,64*4}, {64*5,64*4}, {64*8,64*4}, {64*13,64*4}, {64*14,64*4}
				, {64*15,64*4}, {64*16,64*4}
				, {64*2,64*5}, {64*3,64*5}, {64*4,64*5}, {64*5,64*5}, {64*8,64*5}, {64*9,64*5}, {64*11,64*5}, {64*13,64*5}
				, {64*16,64*5}
				, {64*3,64*6}, {64*4,64*6}, {64*5,64*6}, {64*8,64*6}, {64*11,64*6}, {64*16,64*6}
				, {64*11,64*7}, {64*11,64*8}, {64*11,64*9}
		};
		
		obstaculos = new ArrayList<Obstaculo>();
		for (int i=0; i<pos.length; i++ ) {
            obstaculos.add(new Obstaculo(pos[i][0], pos[i][1]));
        }
	}
	
	private void initEnemigos() {
		enemigos = new ArrayList<Enemigos>();
		enemigos.add(new Enemigos(anchoMundo-128, 64));
		enemigos.add(new Enemigos(anchoMundo-128, 64*3));
	}
	
	private void initObjetivos() {
		objetivos = new ArrayList<Objetivo>();
		//objetivos.add(new Objetivo(64*6, 64*1));
		objetivos.add(new Objetivo(64*10, 64*5));
		objetivos.add(new Objetivo(64*1, 64*7));
		objetivos.add(new Objetivo(64*13, 64*7));
		num_objetivos = objetivos.size();
	}

	public void addNotify() {
        super.addNotify();  
    }
	
	public void paint(Graphics g) {
		
		Graphics2D g2d = (Graphics2D)g;
		
		if(this.enjuego){
			if(jugador.isVisible()){
				g2d.drawImage(jugador.getImagen(), jugador.getI(), jugador.getJ(), this);
			}
			
			for (int i = 0; i < enemigos.size(); i++) {
                Enemigos e = (Enemigos)enemigos.get(i);
                if (e.isVisible())
                    g2d.drawImage(e.getImagen(), e.getI(), e.getJ(), this);
            }
			
			for (int i = 0; i < obstaculos.size(); i++) {
                Obstaculo o = (Obstaculo)obstaculos.get(i);
                if (o.isVisible())
                    g2d.drawImage(o.getImagen(), o.getI(), o.getJ(), this);
            }
			
			for (int i = 0; i < objetivos.size(); i++) {
                Objetivo c = (Objetivo)objetivos.get(i);
                if (c.isVisible())
                    g2d.drawImage(c.getImagen(), c.getI(), c.getJ(), this);
            }
			
			g2d.drawImage(casa.getImagen(), casa.getI(), casa.getJ(), this);
			
		}else {
            String msg = "Game Over";
            Font normal = new Font("Helvetica", Font.BOLD, 52);
            FontMetrics metr = this.getFontMetrics(normal);

            g.setColor(Color.white);
            g.setFont(normal);
            g.drawString(msg, (anchoMundo - metr.stringWidth(msg)) / 2, altoMundo / 2);

        }
		Toolkit.getDefaultToolkit().sync();
        g.dispose();
	}

    public void keyReleased(KeyEvent e) {
        jugador.keyReleased(e);
    }

    public void keyPressed(KeyEvent e) {
        jugador.keyPressed(e);
    }

	@Override
	public void actionPerformed(ActionEvent e) {
		for (int i = 0; i < enemigos.size(); i++) {
            Enemigos ene = (Enemigos) enemigos.get(i);
            if (ene.isVisible()){
            	ene.mover_izq();
            	ene.move();
            } 
                
            else enemigos.remove(i);
        }
		
		jugador.move();
		this.checkCollisions();
		this.repaint();
		
	}

	private void checkCollisions() {
		Rectangle rj = jugador.getBounds();
		
		//Enemigos
        for (int j = 0; j<enemigos.size(); j++) {
            Enemigos e = (Enemigos) enemigos.get(j);
            Rectangle re = e.getBounds();

            if (rj.intersects(re)) {
                this.enjuego = false;
                this.player.parar();
            }
        }
        //Objetivos
        for (int j = 0; j<objetivos.size(); j++) {
            Objetivo o = (Objetivo) objetivos.get(j);
            Rectangle ro = o.getBounds();
            Rectangle rc = casa.getBounds();

            if (rj.intersects(ro)) { 	
              //Colision con lado izq
        		if(jugador.getLastmove() == "izq"){
        			o.mover_izq();
        			o.move();
        			o.detener_mover();
        		}
        		//Colision der
        		if(jugador.getLastmove() == "der"){
        			o.mover_der();
        			o.move();
        			o.detener_mover();
        		}
        		//Colision arriba
        		if(jugador.getLastmove() == "arr"){
        			o.mover_arriba();
        			o.move();
        			o.detener_mover();
        		}
        		//Colision abajo
        		if(jugador.getLastmove() == "aba"){
        			o.mover_abajo();
        			o.move();
        			o.detener_mover();
        		}
        		
        		
            }
            if(ro.contains(rc)){
    			o.setVisible(false);
    			objetivos.remove(o);
    			this.num_objetivos -= 1;
    			if(this.num_objetivos == 0){
    				this.enjuego = false;
    			}
    		}
        }
        
	}
	
}
