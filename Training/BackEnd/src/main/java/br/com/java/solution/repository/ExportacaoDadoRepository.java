package br.com.java.solution.repository;

import br.com.java.solution.model.ExportacaoDado;

import java.util.List;
import java.util.Optional;

public interface ExportacaoDadoRepository extends RepositoryGenerico<ExportacaoDado> {
    /**
     * Metodo para encontrar todas as exportacoes de dados pelo id formulario
     *
     * @param id
     * @return {@link Optional<List<ExportacaoDado>>}
     */
    Optional<List<ExportacaoDado>> findAllByFormularioId(Long id);

    /**
     * Metodo para encontrar todas as exportacoes de dados pelo id do campo
     *
     * @param id
     * @return {@link Optional<List<ExportacaoDado>>}
     */
    Optional<List<ExportacaoDado>> findAllByCampoId(Long id);

    /**

     * Metodo para encontrar uma exportaçãoDado que já esteja cadastrada na base para atualizar o seu valor
     *
     * @param ordemServicoId, moduloId, formularioId, atributoDestino, campoId
     * @return {@link Optional<ExportacaoDado>}
     */
    Optional<ExportacaoDado> findByOrdemServicoIdAndModuloIdAndFormularioIdAndAtributoDestinoAndCampoIdAndFilhoID(
            Long ordemServicoId, Long moduloId, Long formularioId,
            String atributoDestino, Long campoId, Long filhoId);


    Optional<ExportacaoDado> findByOrdemServicoIdAndModuloIdAndFormularioIdAndAtributoDestinoAndCampoIdAndPaiID(
            Long ordemServicoId, Long moduloId, Long formularioId,
            String atributoDestino, Long campoId, Long paiId);

    /**
     * Retorna um formulario pelo número
     * @param filhoNumero, ordemServicoId, formularioId
     * @return {@link Optional<ExportacaoDado>}
     */
    Optional<ExportacaoDado> findByFilhoIDAndOrdemServicoIdAndFormularioIdAndModuloIdAndAtributoDestinoAndCampoId(
            Long filhoId, Long ordemServicoId,
            Long formularioId, Long moduloId,
            String atributoDestino, Long campoId);
}
