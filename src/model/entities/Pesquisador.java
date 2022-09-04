package model.entities;

import java.util.Date;

public class Pesquisador {

		private int id;
		private String nome;
		private String cpf;
		private int matricula;
		private Date dataDeNascimento;
		
		
		public Pesquisador() {
			super();
		}
		public Pesquisador(int id, String nome, String cpf, int matricula, Date inicioDaPesquisa) {
			super();
			this.id = id;
			this.nome = nome;
			this.cpf = cpf;
			this.matricula = matricula;
			this.dataDeNascimento = inicioDaPesquisa;
		}
		
		public Pesquisador( String nome, String cpf, int matricula) {
			super();
			this.nome = nome;
			this.cpf = cpf;
			this.matricula = matricula;
			this.dataDeNascimento = new Date();
			}
		@Override
		public String toString() {
			return "Pesquisador [id=" + id + ", nome=" + nome + ", cpf=" + cpf + ", matricula=" + matricula
					+ ", dataDeNascimento=" + dataDeNascimento + "]";
		}
		public int getId() {
			return id;
		}
		public void setId(int id) {
			this.id = id;
		}
		public String getNome() {
			return nome;
		}
		public void setNome(String nome) {
			this.nome = nome;
		}
		public String getCpf() {
			return cpf;
		}
		public void setCpf(String cpf) {
			this.cpf = cpf;
		}
		public int getMatricula() {
			return matricula;
		}
		public void setMatricula(int matricula) {
			this.matricula = matricula;
		}
		public Date getDataDeNascimento() {
			return dataDeNascimento;
		}
		public void setDataDeNascimento(Date dataDeNascimento) {
			this.dataDeNascimento = dataDeNascimento;
		}
		
		
		
}
