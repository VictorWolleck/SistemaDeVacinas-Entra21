package model.entities;

import java.util.Date;

public class Vacina {
	
	private int id;
	private String pais;
	private int estagioDePesquisa;
	private Date inicioDaPesquisa;
	private String pesquisadorResponsavel;

	@Override
	public String toString() {
		return "Vacina [id=" + id + ", pais=" + pais + ", estagioDePesquisa=" + estagioDePesquisa
				+ ", inicioDaPesquisa=" + inicioDaPesquisa + ", pesquisadorResponsavel=" + pesquisadorResponsavel + "]";
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getPais() {
		return pais;
	}

	public void setPais(String pais) {
		this.pais = pais;
	}

	public int getEstagioDePesquisa() {
		return estagioDePesquisa;
	}

	public void setEstagioDePesquisa(int estagioDePesquisa) {
		this.estagioDePesquisa = estagioDePesquisa;
	}

	public Date getInicioDaPesquisa() {
		return inicioDaPesquisa;
	}

	public void setInicioDaPesquisa(Date inicioDaPesquisa) {
		this.inicioDaPesquisa = inicioDaPesquisa;
	}

	public String getPesquisadorResponsavel() {
		return pesquisadorResponsavel;
	}

	public void setPesquisadorResponsavel(String pesquisadorResponsavel) {
		this.pesquisadorResponsavel = pesquisadorResponsavel;
	}

}
