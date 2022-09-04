package model.repository;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.Banco;
import model.entities.Pesquisador;


public class PesquisadorRepository {

	//TODO criar métodos para 
	// alterar e 
	
	public Pesquisador inserir(Pesquisador novoPesquisador) {
		Connection conexao = Banco.getConnection();

		String sql = " INSERT INTO pesquisador (nome_Pesquisador, cpf_Pesquisador, matricula_Pesquisador, dataDeNascimento ) " + " VALUES (?, ?, ?, ?) ";
		PreparedStatement query = Banco.getPreparedStatementWithPk(conexao, sql);
		try {
			query.setString(1, novoPesquisador.getNome());
			query.setString(2, novoPesquisador.getCpf());
			query.setInt(3, novoPesquisador.getMatricula());
			query.setDate(4,new java.sql.Date(novoPesquisador.getDataDeNascimento().getTime()));
			query.execute();

			ResultSet chavesGeradas = query.getGeneratedKeys();
			if (chavesGeradas.next()) {
				novoPesquisador.setId(chavesGeradas.getInt(1));
			}
		} catch (SQLException e) {
			System.out.println("Erro ao inserir Pesquisador.\nCausa: " + e.getCause());
		} finally {
			Banco.closePreparedStatement(query);
			Banco.closeConnection(conexao);
		}

		return novoPesquisador;
	}
	
	public boolean atualizar(Pesquisador Pesquisador) {
		Connection conexao = Banco.getConnection();
		boolean atualizou = false;

		String sql = " UPDATE PESQUISADOR SET " + " nome_Pesquisador = ?, cpf_Pesquisador = ?, "
				+ " matricula_Pesquisador = ?, dataDeNascimento = ? " + " WHERE ID = ? ";
		PreparedStatement query = Banco.getPreparedStatement(conexao, sql);

		try {
			query.setString(1, Pesquisador.getNome());
			query.setString(2, Pesquisador.getCpf());
			query.setInt(3, Pesquisador.getMatricula());
			query.setDate(4,new java.sql.Date(Pesquisador.getDataDeNascimento().getTime()));
			query.setInt(5, Pesquisador.getId());

			int linhasAfetadas = query.executeUpdate();
			atualizou = linhasAfetadas > 0;
		} catch (SQLException e) {
			System.out.println("Erro ao atualizar vacina.\nCausa: " + e.getCause());
		} finally {
			Banco.closePreparedStatement(query);
			Banco.closeConnection(conexao);
		}

		return atualizou;
	}
	
	public boolean excluir(int id) {
		boolean excluiu = false;

		// Conectar no banco
		Connection conexao = Banco.getConnection();
		String sql = " DELETE FROM PESQUISADOR " + " WHERE ID_PESQUISADOR = ? ";
		// Obter o preparedStatement
		PreparedStatement stmt = Banco.getPreparedStatement(conexao, sql);
		try {
			// Executar
			stmt.setInt(1, id);
			int registrosExcluidos = stmt.executeUpdate();
			excluiu = (registrosExcluidos > 0);
		} catch (SQLException e) {
			System.out.println("Erro ao excluir pesquisador.\nCausa: " + e.getMessage());
		} finally {
			Banco.closePreparedStatement(stmt);
			Banco.closeConnection(conexao);
		}

		return excluiu;
	}
	
	public Pesquisador consultarPorId(int id) {
		Pesquisador pesquisadorBuscado = null;
		Connection conexao = Banco.getConnection();
		String sql = " SELECT * FROM PESQUISADOR WHERE id_Pesquisador = ? ";
		PreparedStatement stmt = Banco.getPreparedStatement(conexao, sql);
		try {
			stmt.setInt(1, id);
			ResultSet resultado = stmt.executeQuery();
			if(resultado.next()) {
				pesquisadorBuscado = new Pesquisador();
				pesquisadorBuscado.setId(resultado.getInt("id_Pesquisador"));
				pesquisadorBuscado.setNome(resultado.getString("nome_Pesquisador"));
				pesquisadorBuscado.setCpf(resultado.getString("cpf_Pesquisador"));
				pesquisadorBuscado.setMatricula(resultado.getInt("matricula_Pesquisador"));
				pesquisadorBuscado.setDataDeNascimento(resultado.getDate("dataDeNascimento"));
			}
		} catch (SQLException e) {
			System.out.println("Erro ao buscar pesquisador com id = " + id + " .\nCausa: "+ e.getMessage());
		} finally {
			Banco.closePreparedStatement(stmt);
			Banco.closeConnection(conexao);
		}
		return pesquisadorBuscado;
	}
}
