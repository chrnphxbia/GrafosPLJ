package Coloracao;

import java.util.ArrayList;

public class ClasseColoracao {
	private ArrayList<Integer> vertices;

	public ClasseColoracao() {
		this.vertices = new ArrayList<Integer>();
	}

	public ArrayList<Integer> getVertices() {
		return this.vertices;
	}

	public void setVertices(ArrayList<Integer> vertices) {
		this.vertices = vertices;
	}

	public void addVertice(Integer vertice) {
		this.vertices.add(vertice);
	}
}
