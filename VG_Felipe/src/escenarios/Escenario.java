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
	private Jugador jugador;
	private ArrayList<Enemigos> enemigos;
	public static ArrayList<Obstaculo> obstaculos;
	public static ArrayList<Objetivo> objetivos;
	public static Casa casa;
	//Otros atributos
	private boolean enjuego;
	public HiloMusica player;
	
	public Escenario() {
		this.jugador = new Jugador(64,64);
		this.setFocusable(true);
		this.requestFocus();
		this.setBackground(Color.BLACK);
		this.setDoubleBuffered(true);
		this.enjuego = true;
		this.setSize(altoMundo, anchoMundo);
		
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
				{0,0}, {64,0}, {128,0}, {192,0}, {256,0}, {320,0}, {384,0}, {448,0}, {512,0}, {576,0}, {640,0}, {704,0}
				, {768,0}, {832,0}, {896,0}, {960,0}, {1024,0}, {1088,0}, {1152,0}, {1216,0},
				//abajo
				{0,altoMundo-80}, {64,altoMundo-80}, {128,altoMundo-80}, {192,altoMundo-80}, {256,altoMundo-80}
				,{320,altoMundo-80}, {384,altoMundo-80}, {448,altoMundo-80}, {512,altoMundo-80}, {576,altoMundo-80}
				, {640,altoMundo-80}, {704,altoMundo-80}, {768,altoMundo-80}, {832,altoMundo-80}, {896,altoMundo-80}
				, {960,altoMundo-80}, {1024,altoMundo-80}, {1088,altoMundo-80}, {1152,altoMundo-80}, {1216,altoMundo-80}
				, {1280,altoMundo-80},
				//izq
				{0,64}, {0,128}, {0,192}, {0,256}, {0,320}, {0,384}, {0,448}, {0,512}, {0,576},
				//der
				{1216,64}, {1216,128}, {1216,192}, {1216,256}, {1216,320}, {1216,384}, {1216,448}, {1216,512}, {1216,576},
				//puzzle
				{64*5,64*1}, {64*2,64*2}, {64*5,64*2}, {64*6,64*2}, {64*7,64*2}, {64*11,64*2}, {64*5,64*14}
				, {64*17,64*2}, {64*18,64*2}
				, {64*2,64*3}, {64*8,64*3}, {64*10,64*3}, {64*11,64*3}, {64*14,64*3}
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
		enemigos.add(new Enemigos(anchoMundo-128, 64*9));
	}
	
	private void initObjetivos() {
		objetivos = new ArrayList<Objetivo>();
		objetivos.add(new Objetivo(64*6, 64*1));
		objetivos.add(new Objetivo(64*10, 64*5));
		objetivos.add(new Objetivo(64*1, 64*7));
		objetivos.add(new Objetivo(64*13, 64*7));
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
            if (ene.isVisible()) 
                ene.move();
            else enemigos.remove(i);
        }
		
		jugador.move();
		this.checkCollisions();
		this.repaint();
		
	}

	private void checkCollisions() {
		Rectangle rj = jugador.getBounds();
		

        for (int j = 0; j<enemigos.size(); j++) {
            Enemigos e = (Enemigos) enemigos.get(j);
            Rectangle re = e.getBounds();

            if (rj.intersects(re)) {
                jugador.setVisible(false);
                e.setVisible(false);
                this.enjuego = false;
                this.player.parar();
            }
        }
        
        for (int j = 0; j<objetivos.size(); j++) {
            Objetivo o = (Objetivo) objetivos.get(j);
            Rectangle ro = o.getBounds();

            if (rj.intersects(ro)) {
                o.setVisible(false);
                //sonido aqui
            	/*
              //Colision con lado izq
        		if(rj.getX()<o.getI()){
        			o.setI(o.getI()+1);
        		}
        		//Colision der
        		if(rj.getX()>o.getI()){
        			o.setI(o.getI()-1);
        		}
        		//Colision arriba
        		if(this.j<c.getJ()){
        			j=lastj;
        		}
        		//Colision abajo
        		if(this.j>c.getJ()){
        			j=lastj;
        		}
        		*/
            }
        }
        
	}
	
}
