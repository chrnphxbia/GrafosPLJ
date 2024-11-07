/*  
    INTEGRANTES DO PROJETO ARAGRAPH
    Jônatas Garcia de Oliveira      10396490
    Livia Alabarse dos Santos       10403046
    Pedro Henrique Araujo Farias    10265432

    Este arquivo apresenta a implementação da classe de Ave, que representará
	um objeto Ave a ser adicionado ao grafo.

    DATA            AUTOR       ATUALIZAÇÃO       
	30/10/2024;		Pedro		Adicionando classe Ave ao projeto
	06/11/2024		Pedro		Finalizando parte 2 do projeto
*/

package aves;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Ave {
	private String idGraph;
	private String taxon;
	private String ordem;
	private String familia;
	private String genero;
	private String especie;

	public Ave(String taxon, String ordem, String familia) {
		this.taxon = taxon;
		this.idGraph = taxon;
		this.ordem = ordem;
		this.familia = familia;

		String[] generoEspecie = taxon.split(" ");

		this.genero = generoEspecie[0];
		this.especie = generoEspecie[1];
	}

	public Ave(String taxon) {
		this.taxon = taxon;
		this.idGraph = taxon;

		try {
			Scanner scanner = new Scanner(new File("assets/aves.txt"));
			String taxonAtual = scanner.nextLine();

			while(!taxonAtual.equals(taxon)) {
				taxonAtual = scanner.nextLine();
			}

			this.ordem = scanner.nextLine();
			this.familia = scanner.nextLine();
			this.genero = scanner.nextLine();
			this.especie = scanner.nextLine();

			scanner.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	public String getIDGraph() {
		return this.idGraph;
	}

	public void setIDGraph(String idGraph) {
		this.idGraph = idGraph;
	}

	public String getTaxon() {
		return this.taxon;
	}

	public void setTaxon(String taxon) {
		this.taxon = taxon;
	}

	public String getOrdem() {
		return this.ordem;
	}

	public void setOrdem(String ordem) {
		this.ordem = ordem;
	}

	public String getFamilia() {
		return this.familia;
	}

	public void setFamilia(String familia) {
		this.familia = familia;
	}

	public String getGenero() {
		return this.genero;
	}

	public void setGenero(String genero) {
		this.genero = genero;
	}

	public String getEspecie() {
		return this.especie;
	}

	public void setEspecie(String especie) {
		this.especie = especie;
	}
}
