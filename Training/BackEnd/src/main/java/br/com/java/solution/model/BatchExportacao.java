package br.com.java.solution.model;


import java.util.List;

public class BatchExportacao {
    private List<ExportacaoDado> dados;

    private List<ExportacaoMidia> midias;

    // Constructors

    /**
     * Construtor com parametros
     *
     * @param dados
     * @param midias
     */
    public BatchExportacao(List<ExportacaoDado> dados, List<ExportacaoMidia> midias) {
        this.dados = dados;
        this.midias = midias;
    }

    /**
     * Construtor vazio
     */
    public BatchExportacao() {
    }

    // Getters and Setters

    public List<ExportacaoDado> getDados() {
        return dados;
    }

    public void setDados(List<ExportacaoDado> dados) {
        this.dados = dados;
    }

    public List<ExportacaoMidia> getMidias() {
        return midias;
    }

    public void setMidias(List<ExportacaoMidia> midias) {
        this.midias = midias;
    }

    // To List<ExportacaoDado>

    /**
     * Metodo ToList<ExportacaoDado>
     *
     * @return {@link List<ExportacaoDado>}
     */
    @Override
    public String toString() {
        return "BatchExportacao{" + "dados='" + dados + '\'' + ", midias='" + midias + "'}";
    }
}