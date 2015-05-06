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
	private int monedas;
	
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
		this.setMonedas(0);
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
	
	public Rectangle getBounds() {
        return new Rectangle(this.i, this.j, this.ancho, this.alto);
    }
	
	public void move() {
		lasti = i;
		lastj = j;
        i += di;
        j += dj;
        
        Rectangle rj = this.getBounds();
        //Hay que mejorar la implementacion en las esquinas
        for(int x = 0; x<Escenario.obstaculos.size(); x++){
        	Obstaculo o = (Obstaculo)Escenario.obstaculos.get(x);
        	if(rj.intersects(o.getBounds())){
        		//Colision con lado izq
        		if(this.i<o.getI()){
        			i=lasti;
        		}
        		//Colision der
        		if(this.i>o.getI()){
        			i=lasti;
        		}
        		//Colision arriba
        		if(this.j<o.getJ()){
        			j=lastj;
        		}
        		//Colision abajo
        		if(this.j>o.getJ()){
        			j=lastj;
        		}
        	}
        }
        
        Casa c = (Casa)Escenario.casa;
        if(rj.intersects(c.getBounds())){
        	//Colision con lado izq
    		if(this.i<c.getI()){
    			i=lasti;
    		}
    		//Colision der
    		if(this.i>c.getI()){
    			i=lasti;
    		}
    		//Colision arriba
    		if(this.j<c.getJ()){
    			j=lastj;
    		}
    		//Colision abajo
    		if(this.j>c.getJ()){
    			j=lastj;
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
	
	public void keyPressed(KeyEvent e) {

        int key = e.getKeyCode();

        if (key == KeyEvent.VK_LEFT) {
            di = -1;
        }

        if (key == KeyEvent.VK_RIGHT) {
            di = 1;
        }

        if (key == KeyEvent.VK_UP) {
            dj = -1;
        }

        if (key == KeyEvent.VK_DOWN) {
            dj = 1;
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

	public int getMonedas() {
		return monedas;
	}

	public void setMonedas(int monedas) {
		this.monedas = monedas;
	}

}
