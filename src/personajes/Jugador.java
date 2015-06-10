package personajes;

import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import escenarios.Escenario;
import opciones.Constantes;


public class Jugador implements Constantes {
	//Coordenadas
	private int i, j;
	private int lasti, lastj;
	//Modificadores de coordenadas
	private int di, dj;
	//Nombre imagen
	private String imgJugador = "jugador.png";
	//Otros atributos
	private int ancho, alto;
	private boolean visible;
	private BufferedImage imagen;
	
	private Objetivo o;
	
	public Jugador(int i, int j){
		this.i = i;
		this.j = j;
		try{
			this.imagen = ImageIO.read(new File("images/"+this.imgJugador));
		}
		catch(IOException e){
			System.out.println(e.toString());
		}
		this.ancho = this.imagen.getWidth();
		this.alto = this.imagen.getHeight();
		this.setVisible(true);
	}
	
	public void setObjetivo(Objetivo o){
		this.o = o;
	}
	
	public void resetObjetivo(){
		this.o = null;
	}
	
	public boolean hasObjetivo(){
		if(this.o != null)
			return true;
		else
			return false;
	}
	
	public Objetivo getObjetivo(){
		return this.o;
	}

	public boolean isVisible() {
		return visible;
	}

	public void setVisible(boolean visible) {
		this.visible = visible;
	}

	public int getAlto() {
		return alto;
	}

	public int getAncho() {
		return ancho;
	}
	
	public void setAlto(int alto) {
		this.alto = alto;
	}

	public void setAncho(int ancho) {
		this.ancho = ancho;
	}
	
	public BufferedImage getImagen() {
		return imagen;
	}
	
	public int getI() {
		return i;
	}
	
	public int getJ() {
		return j;
	}
	
	public int getLasti() {
		return lasti;
	}
	
	public int getLastj() {
		return lastj;
	}
	
	public Rectangle getBounds() {
        return new Rectangle(this.i, this.j, this.ancho, this.alto);
    }
	
	public void move() {
        lasti = i;
		lastj = j;
        i += di;
        j += dj;
        
        if(this.hasObjetivo()){
        	this.o.setBackPJ();
        }
        
        Rectangle rj = this.getBounds();
        //Hay que mejorar la implementacion en las esquinas
        for(int x = 0; x<Escenario.obstaculos.size(); x++){
        	Obstaculo o = (Obstaculo)Escenario.obstaculos.get(x);
        	if(rj.contains(o.getBounds())){
        		//Colision con lado izq
        		if(lasti<o.getI()){
        			i=lasti;
        		}
        		//Colision der
        		if(lasti>o.getI()){
        			i=lasti;
        		}
        		//Colision arriba
        		if(lastj<o.getJ()){
        			j=lastj;
        		}
        		//Colision abajo
        		if(lastj>o.getJ()){
        			j=lastj;
        		}
        		if(this.hasObjetivo()){
    				this.o.setBack();
    			}
        	}
        }
        
        
		
        if (i < 1) {
            i = 1;
        }
        
        if(i>anchoMundo - this.getAncho()){
        	i=anchoMundo - this.getAncho();
        }

        if (j < 1) {
            j = 1;
        }
        
        if(j>altoMundo - this.getAlto() - this.getAlto()*1/2){
        	j=altoMundo - this.getAlto() - this.getAlto()*1/2;
        }
        
    }
	
	public void mover_izq() {
		di = -1*64;
		this.move();
	}
	
	public void mover_der() {
		di = 1*64;
		this.move();
	}
	
	public void mover_arriba() {
		dj = -1*64;
		this.move();
	}
	
	public void mover_abajo() {
		dj = 1*64;
		this.move();
	}
	
	public void keyPressed(KeyEvent e) {

        int key = e.getKeyCode();
        
        if (key == KeyEvent.VK_LEFT) {
        	mover_izq();
        }

        if (key == KeyEvent.VK_RIGHT) {
            mover_der();
        }

        if (key == KeyEvent.VK_UP) {
        	mover_arriba();
        }

        if (key == KeyEvent.VK_DOWN) {
        	mover_abajo();
        }
    }
	
	public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();

        if (key == KeyEvent.VK_LEFT) {
            di = 0;
        }

        if (key == KeyEvent.VK_RIGHT) {
            di = 0;
        }

        if (key == KeyEvent.VK_UP) {
            dj = 0;
        }

        if (key == KeyEvent.VK_DOWN) {
            dj = 0;
        }
        
    }
}
