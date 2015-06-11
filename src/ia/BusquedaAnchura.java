package ia;

import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.TimerTask;

import escenarios.Escenario;
import opciones.Constantes;
import personajes.Obstaculo;

public class BusquedaAnchura extends TimerTask implements Constantes {
	private Escenario escenario;
	private ArrayList<Estado> colaEstados;
	private ArrayList<Estado> historial;
	private ArrayList<Character> pasos;
	private int index_pasos;
	private Estado inicial;
	private Estado objetivo;
	private Estado temp;
	private boolean exito;
	
	public BusquedaAnchura(Escenario escenario, int jx, int jy, int ox, int oy){
		this.escenario = escenario;
		colaEstados = new ArrayList<Estado>();
		historial = new ArrayList<Estado>();
		pasos = new ArrayList<Character>();
		index_pasos = 0;
		inicial = new Estado(jx,jy,'N',null);
		colaEstados.add(inicial);
		historial.add(inicial);
		objetivo = new Estado(ox,oy,'N',null);
		exito = false;
	}
	
	public void buscar(){
		if(inicial.equals(objetivo))
			exito = true;
		while(!colaEstados.isEmpty() && !exito){
			temp = colaEstados.get(0);
			colaEstados.remove(0);
			moverArriba(temp);
			moverAbajo(temp);
			moverIzquierda(temp);
			moverDerecha(temp);
		}
	}
	
	private void moverArriba(Estado e){
		if(e.getY() > 0){
			if(!this.verifyObstaculos(e.getX(), e.getY()-(1*64))){
				Estado arriba = new Estado(e.getX(),e.getY()-64,'U',e);
				if(!historial.contains(arriba)){
					colaEstados.add(arriba);
					historial.add(arriba);
					
					if(arriba.equals(objetivo)){
						
						objetivo = arriba;
						exito = true;
					}
				}
			}
		}
	}
	
	private void moverAbajo(Estado e){
		if(e.getY() > 0){
			if(!this.verifyObstaculos(e.getX(), e.getY()+(1*64))){
				Estado abajo = new Estado(e.getX(),e.getY()+64,'D',e);
				if(!historial.contains(abajo)){
					colaEstados.add(abajo);
					historial.add(abajo);
					
					if(abajo.equals(objetivo)){
						
						objetivo = abajo;
						exito = true;
					}
				}
			}
		}
	}
	
	private void moverIzquierda(Estado e){
		if(e.getY() > 0){
			if(!this.verifyObstaculos(e.getX()-64, e.getY())){
				Estado izquierda = new Estado(e.getX()-64,e.getY(),'L',e);
				if(!historial.contains(izquierda)){
					colaEstados.add(izquierda);
					historial.add(izquierda);
					
					if(izquierda.equals(objetivo)){
						
						objetivo = izquierda;
						exito = true;
					}
				}
			}
		}
	}
	
	private void moverDerecha(Estado e){
		if(e.getY() > 0){
			if(!this.verifyObstaculos(e.getX()+64, e.getY())){
				Estado derecha = new Estado(e.getX()+64,e.getY(),'R',e);
				if(!historial.contains(derecha)){
					colaEstados.add(derecha);
					historial.add(derecha);
					
					if(derecha.equals(objetivo)){
						
						objetivo = derecha;
						exito = true;
					}
				}
			}
		}
	}
	
	private boolean verifyObstaculos(int x, int y){
		Rectangle rj = new Rectangle(x,y,64,64);
		
		for(int i = 0; i < Escenario.obstaculos.size(); i++){
			Obstaculo o = (Obstaculo)Escenario.obstaculos.get(i);
			if(rj.contains(o.getBounds())){
				return true;
			}
		}
		return false;
	}
	
	public void calcularRuta(){
		Estado predecesor = objetivo;
		do{
			pasos.add(predecesor.oper);
			predecesor = predecesor.predecesor;
		}while(predecesor != null);
		index_pasos = pasos.size()-1;
	}

	@Override
	public void run() {
		if(index_pasos >= 0){
			switch (pasos.get(index_pasos)){
				case 'D': Escenario.jugador.mover_abajo();break;
				case 'U': Escenario.jugador.mover_arriba();break;
				case 'R': Escenario.jugador.mover_der();break;
				case 'L': Escenario.jugador.mover_izq();break;
			}
			escenario.repaint();
			index_pasos--;
		}else{
			this.cancel();
		}
	}
}
