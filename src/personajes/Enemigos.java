package personajes;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import escenarios.Escenario;
import opciones.Constantes;


public class Enemigos implements Constantes {
	//Coordenadas
	private int i, j;
	private int di, dj;
	private int lasti, lastj;
	//Nombre imagen
	private String imgEnemigo = "enemigos.png";
	//Otros atributos
	private int ancho, alto;
	private boolean visible;
	private BufferedImage imagen;
	
	public Enemigos(int i, int j){
		this.i = i;
		this.j = j;
		try{
			this.imagen = ImageIO.read(new File("images/"+this.imgEnemigo));
		}
		catch(IOException e){
			System.out.println(e.toString());
		}
		this.ancho = this.imagen.getWidth();
		this.alto = this.imagen.getHeight();
		this.setVisible(true);
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
	
	public BufferedImage getImagen() {
		return imagen;
	}
	
	public int getI() {
		return i;
	}
	
	public void setI(int i) {
		this.i = i;
	}
	
	public int getJ() {
		return j;
	}
	
	public void setJ(int j) {
		this.j = j;
	}
	
	public Rectangle getBounds() {
        return new Rectangle(this.i, this.j, this.ancho, this.alto);
    }
	
	public void move() {
		
		lasti=i;
		lastj=j;
		i += di;
        j += dj;
		
		Rectangle rj = this.getBounds();
        //Hay que mejorar la implementacion en las esquinas
        for(int x = 0; x<Escenario.obstaculos.size(); x++){
        	Obstaculo o = (Obstaculo)Escenario.obstaculos.get(x);
        	if(rj.intersects(o.getBounds())){
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
        	}
        }
        
        for(int x = 0; x<Escenario.objetivos.size(); x++){
        	Objetivo o = (Objetivo)Escenario.objetivos.get(x);
        	if(rj.intersects(o.getBounds())){
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

	}
	
	public void mover_der() {
		di = 1*64;

	}
	
	public void mover_arriba() {
		dj = -1*64;

	}
	
	public void mover_abajo() {
		dj = 1*64;

	}
	
	public void detener_mover(){
		di = 0;
		dj = 0;
	}

}
