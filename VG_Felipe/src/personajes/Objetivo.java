package personajes;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import escenarios.Escenario;

public class Objetivo {
	//Coordenadas
	private int i, j;
	private int di, dj;
	private int lasti, lastj;
	//Nombre imagen
	private String img = "objetivo.png";
	//Otros atributos
	private int ancho, alto;
	private boolean visible;
	private BufferedImage imagen;
	
	public Objetivo(int i, int j){
		this.i = i;
		this.j = j;
		try{
			this.imagen = ImageIO.read(new File("images/"+this.img));
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
	
	public int getJ() {
		return j;
	}
	
	public void setI(int i){
		this.i = i;
	}
	
	public void setJ(int j){
		this.j = j;
	}
	
	public Rectangle getBounds() {
        return new Rectangle(this.i, this.j, this.ancho, this.alto);
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
	
	public void move() {
			
			lasti=i;
			lastj=j;
			i += di;
	        j += dj;
			
			Rectangle rj = this.getBounds();
	        //Hay que mejorar la implementacion en las esquinas
	        for(int x = 0; x<Escenario.obstaculos.size(); x++){
	        	Obstaculo o = (Obstaculo)Escenario.obstaculos.get(x);
	        	if(rj.contains(o.getBounds())){
	        		//Colision con lado izq
	        		if(Escenario.jugador.getLastmove() == "der"){
	        			this.mover_izq();
	        			this.move();
	        			this.move();
	        			this.detener_mover();
	        		}
	        		//Colision der
	        		if(Escenario.jugador.getLastmove() == "izq"){
	        			this.mover_der();
	        			this.move();
	        			this.move();
	        			this.detener_mover();
	        		}
	        		//Colision arriba
	        		if(Escenario.jugador.getLastmove() == "aba"){
	        			this.mover_arriba();
	        			this.move();
	        			this.move();
	        			this.detener_mover();
	        		}
	        		//Colision abajo
	        		if(Escenario.jugador.getLastmove() == "arr"){
	        			this.mover_abajo();
	        			this.move();
	        			this.move();
	        			this.detener_mover();
	        		}
	        	}
	        }
	        
	}
}
