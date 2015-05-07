package managers;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JPanel;
import javax.swing.Timer;

import escenarios.Escenario;
import Pantallas.PantallaOpciones;
import Pantallas.PantallaPrincipal;

public class GameManager extends JPanel implements ActionListener {
	private GameStateManager gsm;
	
	private PantallaPrincipal pp;
	private Escenario es;
	private PantallaOpciones op;
	
	private boolean esperar = true;
	
	private Timer timer;
	
	public GameManager(){
		this.addKeyListener(new TAdapter());
		gsm = new GameStateManager();
		pp = new PantallaPrincipal();
		es = new Escenario();
		op = new PantallaOpciones();
		this.setFocusable(true);
		this.setBackground(Color.BLACK);
		
		timer = new Timer(3, this);
        timer.start();
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
        				es.player.run();
        			}else{
        				System.exit(0);
        			}
        		}
        		if(gsm.isEN_OPCIONES()){
        			if(op.getSeleccion() == 3){
        				System.exit(0);
        			}
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
        			es.player.reanudar();
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
