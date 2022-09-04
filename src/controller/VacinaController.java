package controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import exception.CampoInvalidoException;
import exception.VacinaSemResponsavelException;
import model.entities.Vacina;
import model.repository.VacinaRepository;

@RestController

public class VacinaController {

	private VacinaRepository repositorio = new VacinaRepository();

	@GetMapping("/vacinas")
	public List<Vacina> pesquisarTodas() {
		return repositorio.pesquisarTodas();
	}

	public Vacina salvar(Vacina novaVacina) throws VacinaSemResponsavelException, CampoInvalidoException {

		if (novaVacina.getPesquisadorResponsavel() == null) {
			throw new VacinaSemResponsavelException("Informe o pesquisador" + " responsável");
		}

		if (novaVacina.getPais() == null || novaVacina.getPais().trim().isEmpty()) {
			throw new CampoInvalidoException("Informe o país de origem da vacina");
		}

		novaVacina = repositorio.inserir(novaVacina);

		return novaVacina;
	}
	// Single item
	@DeleteMapping("/vacinas/{id}")
	public void excluir(@PathVariable int id) {

		repositorio.excluir(id);

	}
}