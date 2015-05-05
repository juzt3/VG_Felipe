package managers;

public class GameStateManager {
	private boolean EN_JUEGO, EN_MENU, EN_OPCIONES;
	
	public GameStateManager(){
		EN_JUEGO = false;
		EN_MENU = true;
		EN_OPCIONES = false;
	}

	public boolean isEN_JUEGO() {
		return EN_JUEGO;
	}

	public void setEN_JUEGO(boolean eN_JUEGO) {
		EN_JUEGO = eN_JUEGO;
	}

	public boolean isEN_OPCIONES() {
		return EN_OPCIONES;
	}

	public void setEN_OPCIONES(boolean eN_OPCIONES) {
		EN_OPCIONES = eN_OPCIONES;
	}

	public boolean isEN_MENU() {
		return EN_MENU;
	}

	public void setEN_MENU(boolean eN_MENU) {
		EN_MENU = eN_MENU;
	}
	
	
}
