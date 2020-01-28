package br.com.java.solution.repository;


import br.com.java.solution.model.ExportacaoMidia;

import java.util.List;
import java.util.Optional;

public interface ExportacaoMidiaRepository extends RepositoryGenerico<ExportacaoMidia> {
    /**
     * Metodo criado apra carregar exportacao midia pela categoria
     *
     * @param id
     * @return {@link Optional<List< ExportacaoMidia >>}
     */
    Optional<List<ExportacaoMidia>> findByCategoriaId(Long id);

    /**
     * Metodo criado apra carregar exportacao midia pelo formulario
     *
     * @param id
     * @return {@link Optional<List< ExportacaoMidia >>}
     */
    Optional<List<ExportacaoMidia>> findByFormularioId(Long id);
}
