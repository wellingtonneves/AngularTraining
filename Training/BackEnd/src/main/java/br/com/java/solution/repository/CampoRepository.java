package br.com.java.solution.repository;


import br.com.java.solution.model.Campo;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface CampoRepository extends RepositoryGenerico<Campo> {
    /**
     * Metodo para carregar campo pelo id da secao
     *
     * @param id
     * @return {@link Optional<List<Campo>>}
     */
    Optional<List<Campo>> findAllBySecaoId(Long id);

    /**
     * Metodo para carregar um campo pelo id da secao
     *
     * @param id
     * @return {@link Campo}
     */
    Campo findOneBySecaoId(Long id);

    /**
     * Metodo para carregar campo pelo seu id
     *
     * @param id
     * @return {@link Optional<Campo>}
     */
    Optional<Campo> findById(Long id);

    /**
     * Metodo para carregar todos os ids dos campos contidos numa secao
     *
     * @param id
     * @return {@link Optional<List<Long>>}
     */
    @Query(value = "SELECT c.id FROM campos AS c WHERE secao_id = :id", name = "Select Ids da Secao", nativeQuery = true)
    List<Long> findAllIdsBySecaoId(Long id);
}
