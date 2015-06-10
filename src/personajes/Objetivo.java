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
	
	public void setBackPJ(){
		lasti = i;
		lastj = j;
		i = Escenario.jugador.getLasti();
		j = Escenario.jugador.getLastj();
	}
	
	public void setBack(){
		i = this.lasti;
		j = this.lastj;
	}
	
}
