package managers;

import ia.BusquedaAnchura;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JPanel;

import java.util.Timer;

import opciones.Constantes;
import escenarios.Escenario;
import Pantallas.PantallaOpciones;
import Pantallas.PantallaPrincipal;

public class GameManager extends JPanel implements ActionListener, Constantes {
	private GameStateManager gsm;
	
	private PantallaPrincipal pp;
	private Escenario es;
	private PantallaOpciones op;
	
	private boolean esperar = true;
	private boolean esperarmusica = true;
	
	private javax.swing.Timer timerUI;
	private java.util.Timer lanzadorTareas;
	
	BusquedaAnchura buscador_jugador;
	
	public GameManager(){
		this.addKeyListener(new TAdapter());
		gsm = new GameStateManager();
		pp = new PantallaPrincipal();
		es = new Escenario();
		op = new PantallaOpciones();
		this.setFocusable(true);
		this.setBackground(Color.BLACK);
		this.setSize(altoMundo, anchoMundo);
		
		timerUI = new javax.swing.Timer(120, this);
        timerUI.start();
        
		buscador_jugador = new BusquedaAnchura(es, es.jugador.getI(), es.jugador.getJ(), Escenario.objetivos.get(0).getI(), Escenario.objetivos.get(0).getJ(), gsm);
		Escenario.jugador.setInteligencia(buscador_jugador);
		Escenario.jugador.getInteligencia().buscar();
		Escenario.jugador.getInteligencia().calcularRuta();
		
		System.out.println(Escenario.jugador.getInteligencia().getPasos());
		
		lanzadorTareas = new java.util.Timer();
		//lanzadorTareas.scheduleAtFixedRate(buscador_jugador, 0, 500);
		//lanzadorTareas.scheduleAtFixedRate(adversario1, 0, 1000);
		//lanzadorTareas.scheduleAtFixedRate(adversario2, 0, 500);
	}
	
	private class TAdapter extends KeyAdapter {

        public void keyReleased(KeyEvent e) {
        	if(gsm.isEN_JUEGO()){
            	es.keyReleased(e);
            }
        }

        public void keyPressed(KeyEvent e) {
        	int key = e.getKeyCode();
        	
        	if(key == KeyEvent.VK_ENTER){
        		if(gsm.isEN_MENU()){
        			if(pp.getSeleccion() == 0){
        				gsm.setEN_JUEGO(true);
        				gsm.setEN_MENU(false);
        				gsm.setEN_OPCIONES(false);
        				lanzadorTareas.scheduleAtFixedRate(buscador_jugador, 0, 500);
        				es.player.run();
        			}else{
        				System.exit(0);
        			}
        		}
        		if(gsm.isEN_OPCIONES()){
        			if(op.getSeleccion() == 3){
        				System.exit(0);
        			}
        			if(op.getSeleccion() == 2 && esperarmusica){
        				op.setMusica(false);
        			}else{
        				if(op.getSeleccion() == 2 && !esperarmusica)
        					op.setMusica(true);
        			}
        			esperarmusica = !esperarmusica;
        		}
        	}
        	
        	if(key == KeyEvent.VK_ESCAPE){
        		if(gsm.isEN_JUEGO() && esperar){
        			gsm.setEN_JUEGO(false);
        			gsm.setEN_MENU(false);
        			gsm.setEN_OPCIONES(true);
        			es.player.parar();
        		}
        		if(gsm.isEN_OPCIONES() && !esperar){
        			gsm.setEN_OPCIONES(false);
        			gsm.setEN_MENU(false);
        			gsm.setEN_JUEGO(true);
        			
        			if(op.isMusica()){
        				es.player.reanudar();
        			}
        		}
        		esperar = !esperar;
        	}
        	
            if(gsm.isEN_MENU()){
            	pp.keyPressed(e);
            }
            
            if(gsm.isEN_JUEGO()){
            	es.keyPressed(e);
            }
            
            if(gsm.isEN_OPCIONES()){
            	op.keyPressed(e);
            }
        }
    }
	
	@Override
	public void paint(Graphics g) {
		super.paint(g);
		
		if(gsm.isEN_MENU()){
			pp.paint(g);
		}
		if(gsm.isEN_JUEGO()){
			es.paint(g);
		}
		if(gsm.isEN_OPCIONES()){
			op.paint(g);
		}
		
		Toolkit.getDefaultToolkit().sync();
		g.dispose();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(gsm.isEN_JUEGO()){
        	es.actionPerformed(e);
        }
		if(gsm.isEN_MENU()){
			pp.repaint();
        }
		if(gsm.isEN_OPCIONES()){
			op.repaint();
		}
		this.repaint();
	}
}
