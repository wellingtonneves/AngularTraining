package br.com.java.solution.repository;

import br.com.java.solution.model.Formulario;

import java.util.List;
import java.util.Optional;

public interface FormularioRepository extends RepositoryGenerico<Formulario> {
    /**
     * Metodo para encontrar todos os formularios pela ordem de servico
     *
     * @param id
     * @return {@link Optional<List< Formulario >>}
     */
    Optional<List<Formulario>> findAllByOrdem(Long id);
}
