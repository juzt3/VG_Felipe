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
import Pantallas.PantallaPrincipal;

public class GameManager extends JPanel implements ActionListener {
	private GameStateManager gsm;
	private PantallaPrincipal pp;
	private Escenario es;
	
	private Timer timer;
	
	public GameManager(){
		this.addKeyListener(new TAdapter());
		gsm = new GameStateManager();
		pp = new PantallaPrincipal();
		es = new Escenario();
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
        	}
        	
            if(gsm.isEN_MENU()){
            	pp.keyPressed(e);
            }
            
            if(gsm.isEN_JUEGO()){
            	es.keyPressed(e);
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
		this.repaint();
	}
}
