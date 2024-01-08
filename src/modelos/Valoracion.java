package modelos;

import java.util.ArrayList;

public class Valoracion {
	private final Plato plato;
	private final int nota;
	private final String comentario;
	 
	public Valoracion(Plato plato, int nota, String comentario) {
		this.plato = plato;
		this.nota = nota;
		this.comentario = comentario;
	}

	public Plato getPlato() {
		return plato;
	}

	public int getNota() {
		return nota;
	}

	public void borrarValoraciones(Plato plato, ArrayList<Valoracion> valoracions) {

		for (int j = 0; j <= valoracions.size() - 1; j++) {
			if (valoracions.get(j).getPlato() == plato) valoracions.remove(valoracions.get(j));
		}
	}

	@Override
	public String toString() {
		return "nota=" + nota +
				"; comentario=" + comentario;
	}
}
