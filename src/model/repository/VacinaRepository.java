package model.repository;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.Banco;
import model.entities.Vacina;

public class VacinaRepository {

	public Vacina inserir(Vacina novaVacina) {
		Connection conexao = Banco.getConnection();

		String sql = " INSERT INTO VACINAS(PAIS_ORIGEM, ESTAGIO_PESQUISA, "
				+ "                  DATA_INICIO_PESQUISA, NOME_RESPONSAVEL) " + " VALUES (?, ?, ?, ?) ";
		PreparedStatement query = Banco.getPreparedStatementWithPk(conexao, sql);
		try {
			query.setString(1, novaVacina.getPais());
			query.setInt(2, novaVacina.getEstagioDePesquisa());
			query.setDate(3, new java.sql.Date(novaVacina.getInicioDaPesquisa().getTime()));
			query.setString(4, novaVacina.getPesquisadorResponsavel());
			query.execute();

			ResultSet chavesGeradas = query.getGeneratedKeys();
			if (chavesGeradas.next()) {
				novaVacina.setId(chavesGeradas.getInt(1));
			}
		} catch (SQLException e) {
			System.out.println("Erro ao inserir vacina.\nCausa: " + e.getCause());
		} finally {
			Banco.closePreparedStatement(query);
			Banco.closeConnection(conexao);
		}

		return novaVacina;
	}

	public boolean atualizar(Vacina vacina) {
		Connection conexao = Banco.getConnection();
		boolean atualizou = false;

		String sql = " UPDATE VACINAS SET " + " PAIS_ORIGEM = ?, ESTAGIO_PESQUISA = ?, "
				+ " DATA_INICIO_PESQUISA = ?, NOME_RESPONSAVEL = ? " + " WHERE ID = ? ";
		PreparedStatement query = Banco.getPreparedStatement(conexao, sql);

		try {
			query.setString(1, vacina.getPais());
			query.setInt(2, vacina.getEstagioDePesquisa());
			query.setDate(3, new java.sql.Date(vacina.getInicioDaPesquisa().getTime()));
			query.setString(4, vacina.getPesquisadorResponsavel());
			query.setInt(5, vacina.getId());

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

	// Delete
	public boolean excluir(int id) {
		boolean excluiu = false;

		// Conectar no banco
		Connection conexao = Banco.getConnection();
		String sql = " DELETE FROM VACINAS " + " WHERE ID = ? ";
		// Obter o preparedStatement
		PreparedStatement stmt = Banco.getPreparedStatement(conexao, sql);
		try {
			// Executar
			stmt.setInt(1, id);
			int registrosExcluidos = stmt.executeUpdate();
			excluiu = (registrosExcluidos > 0);
		} catch (SQLException e) {
			System.out.println("Erro ao excluir vacina.\nCausa: " + e.getMessage());
		} finally {
			Banco.closePreparedStatement(stmt);
			Banco.closeConnection(conexao);
		}

		return excluiu;
	}

	// Retrieve
	public Vacina pesquisarPorId(int id) {
		Vacina vacinaBuscada = null;

		Connection conexao = Banco.getConnection();
		String sql = "SELECT * FROM VACINAS\r\n" + "		//WHERE ID = ?";

		PreparedStatement stmt = Banco.getPreparedStatement(conexao, sql);

		try {
			stmt.setInt(1, id);
			ResultSet resultado = stmt.executeQuery();

			if (resultado.next()) {
				vacinaBuscada = new Vacina();
				vacinaBuscada.setId(resultado.getInt("id"));
				vacinaBuscada.setEstagioDePesquisa(resultado.getInt("estagio_pesquisa"));
				vacinaBuscada.setPesquisadorResponsavel(resultado.getString("nome_responsavel"));
				vacinaBuscada.setPais(resultado.getString("pais_origem"));
				vacinaBuscada.setInicioDaPesquisa(resultado.getDate("data_inicio_pesquisa"));

			}
		} catch (SQLException e) {
			System.out.println("Erro ao buscar vacina com id = " + id + ".\nCausa" + e.getCause());
		} finally {
			Banco.closePreparedStatement(stmt);
			Banco.closeConnection(conexao);
		}

		return null;
	}

	public ArrayList<Vacina> pesquisarTodas() {

		ArrayList<Vacina> vacinas = new ArrayList<Vacina>();

		Connection conexao = Banco.getConnection();
		String sql = " SELECT * FROM VACINAS ";

		PreparedStatement stmt = Banco.getPreparedStatement(conexao, sql);

		try {
			ResultSet resultado = stmt.executeQuery();

			while (resultado.next()) {

				Vacina vacinaBuscada = new Vacina();
				vacinaBuscada.setId(resultado.getInt("id"));
				vacinaBuscada.setEstagioDePesquisa(resultado.getInt("estagio_pesquisa"));
				vacinaBuscada.setPesquisadorResponsavel(resultado.getString("nome_responsavel"));
				vacinaBuscada.setPais(resultado.getString("pais_origem"));
				vacinaBuscada.setInicioDaPesquisa(resultado.getDate("data_inicio_pesquisa"));
				vacinas.add(vacinaBuscada);
			}
		} catch (SQLException e) {
			System.out.println("Erro ao buscar vacinas =.\nCausa" + e.getCause());
		} finally {
			Banco.closePreparedStatement(stmt);
			Banco.closeConnection(conexao);
		}

		return vacinas;
	}

}