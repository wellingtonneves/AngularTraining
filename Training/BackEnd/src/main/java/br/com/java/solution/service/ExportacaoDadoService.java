package br.com.java.solution.service;


import br.com.java.solution.model.BatchExportacao;
import br.com.java.solution.model.ExportacaoDado;
import br.com.java.solution.model.ExportacaoMidia;
import br.com.java.solution.repository.*;
import br.com.java.solution.util.ImageUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Description;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/dados")
@Description("Exportacao Dados")
public class ExportacaoDadoService {

    @Autowired
    ExportacaoDadoRepository exportacaoDadoRepository;

    @Autowired
    ExportacaoMidiaRepository exportacaoMidiaRepository;

    @Autowired
    MidiaCategoriaRepository categoriaRepository;

    @Autowired
    CampoRepository campoRepository;

    @Autowired
    FormularioRepository formularioRepository;

    /**
     * Metodo GET para listagem de todos as exportacao dados
     *
     * @return {@link List < ExportacaoDado >}
     */
    @RequestMapping(method = RequestMethod.GET)
    public List<ExportacaoDado> buscaCampo() {
        return exportacaoDadoRepository.findAll();
    }

    /**
     * Metodo GET campo por formulario
     *
     * @param id
     * @return {@link Optional <List<ExportacaoDado>>}
     */
    @RequestMapping(method = RequestMethod.GET, value = "by-formulario/{id}")
    public Optional<List<ExportacaoDado>> findByFormularioId(@PathVariable(value = "id") Long id) {
        return exportacaoDadoRepository.findAllByFormularioId(id);
    }

    /**
     * Metodo GET campo por cmapo
     *
     * @param id
     * @return {@link Optional<List<ExportacaoDado>>}
     */
    @RequestMapping(method = RequestMethod.GET, value = "by-campo/{id}")
    public Optional<List<ExportacaoDado>> findByCampoId(@PathVariable(value = "id") Long id) {
        return exportacaoDadoRepository.findAllByCampoId(id);
    }

    /**
     * Metodo GET campo por id
     *
     * @param id
     * @return {@link ExportacaoDado}
     */
    @RequestMapping(method = RequestMethod.GET, value = "/{id}")
    public ExportacaoDado find(@PathVariable(value = "id") Long id) {
        return exportacaoDadoRepository.findById(id).get();
    }

    /**
     * Requisicao DELETE de delecoo logica
     *
     * @param id
     */
    @ResponseBody
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @ResponseStatus(value = HttpStatus.OK)
    public void delete(@PathVariable Long id) {
        ExportacaoDado entity = exportacaoDadoRepository.getOne(id);
        entity.setDeletado(true);
        exportacaoDadoRepository.save(entity);
    }

    /**
     * Requisicao POST basica
     *
     * @param objeto
     * @param request
     * @return {@link ExportacaoDado}
     */
    @ResponseBody
    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.CREATED)
    public ExportacaoDado save(@RequestBody ExportacaoDado objeto, HttpServletRequest request) {

        objeto.setCampo(campoRepository.findById(objeto.getCampoId()).get());
        objeto.setFormulario(formularioRepository.findById(objeto.getFormularioId()).get());

        salvarDado(objeto);
        return exportacaoDadoRepository.save(objeto);
    }

    @ResponseBody
    @RequestMapping(value = "/batch", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.CREATED)
    public List<ExportacaoDado> saveBatch(@RequestBody BatchExportacao dados) {
        List<ExportacaoDado> exportacaoDadoList = new ArrayList<>();
        List<ExportacaoMidia> exportacaoMidiaList = new ArrayList<>();


        if (dados.getDados() != null) {
            dados.getDados().forEach(dado -> {
                Optional<ExportacaoDado> exportacaoDadoToUpdate = null;
                if (dado.getFilhoID() != null) {
                    exportacaoDadoToUpdate = exportacaoDadoRepository
                            .findByOrdemServicoIdAndModuloIdAndFormularioIdAndAtributoDestinoAndCampoIdAndFilhoID(
                                    dado.getOrdemServicoId(), dado.getModuloId(), dado.getFormularioId(),
                                    dado.getAtributoDestino(), dado.getCampoId(), dado.getFilhoID());

                } else if (dado.getPaiID() != null) {
                    exportacaoDadoToUpdate = exportacaoDadoRepository
                            .findByOrdemServicoIdAndModuloIdAndFormularioIdAndAtributoDestinoAndCampoIdAndPaiID(
                                    dado.getOrdemServicoId(), dado.getModuloId(), dado.getFormularioId(),
                                    dado.getAtributoDestino(), dado.getCampoId(), dado.getPaiID());
                }

                if (exportacaoDadoToUpdate.isPresent()) {
                    ExportacaoDado dadoUpdate = exportacaoDadoToUpdate.get();
                    dadoUpdate.setCampo(campoRepository.findById(dado.getCampoId()).get());
                    dadoUpdate.setFormulario(formularioRepository.findById(dado.getFormularioId()).get());
                    dadoUpdate.setValor(dado.getValor());
                    exportacaoDadoList.add(dadoUpdate);
                } else {
                    exportacaoDadoList.add(inserirDado(dado));
                }
            });

            exportacaoDadoRepository.saveAll(exportacaoDadoList);
        }

        if (dados.getMidias() != null) {
            dados.getMidias().forEach(midia -> {
                midia.setCategoria(categoriaRepository.findById(midia.getCategoriaId()).get());
                midia.setFormulario(formularioRepository.findById(midia.getFormularioId()).get());
                if (midia.getPaiID() != null) {
                    midia.setAtributoIdAssociado(midia.getPaiID());
                } else {
                    if (midia.getFilhoID() != null) {
                        midia.setAtributoIdAssociado(midia.getFilhoID());
                    }
                }

                if (midia.getBytes() == null || midia.getBytes().length == 0) {
                    midia.setBytes(ImageUtil.imageToByteArr(midia.getNome()));
                }

                exportacaoMidiaList.add(midia);
            });

            exportacaoMidiaRepository.saveAll(exportacaoMidiaList);
        }

        if (exportacaoDadoList.size() > 0 || exportacaoMidiaList.size() > 0) {
            salvarDados(new BatchExportacao(exportacaoDadoList, exportacaoMidiaList));
        }

        if (exportacaoMidiaList.size() > 0) {
            salvarFotos(exportacaoMidiaList);
        }

        return exportacaoDadoList;
    }

    private ExportacaoDado inserirDado(ExportacaoDado dado) {
        dado.setCampo(campoRepository.findById(dado.getCampoId()).get());
        dado.setFormulario(formularioRepository.findById(dado.getFormularioId()).get());
        return dado;
    }

    /**
     * Requisicao PUT basica
     *
     * @param id
     * @param objeto
     * @param request
     * @return {@link ExportacaoDado}
     */
    @ResponseBody
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    @ResponseStatus(value = HttpStatus.OK)
    public ExportacaoDado update(@PathVariable Long id, @RequestBody final ExportacaoDado objeto,
                                 HttpServletRequest request) {
        return exportacaoDadoRepository.save(objeto);
    }

    /**
     * Requisicao PATCH basica com id
     *
     * @param id
     * @param request
     * @return {@link ExportacaoDado}
     * @throws IOException
     */
    @ResponseBody
    @RequestMapping(value = "/{id}", method = RequestMethod.PATCH, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.OK)
    public ExportacaoDado updatePatch(@PathVariable Long id, HttpServletRequest request) throws IOException {
        ExportacaoDado objAtual = exportacaoDadoRepository.findById(id).get();
        ExportacaoDado objAtualizado = new ObjectMapper().readerForUpdating(objAtual).readValue(request.getReader());
        return exportacaoDadoRepository.saveAndFlush(objAtualizado);
    }

    public void salvarDados(final BatchExportacao dados) {

        ObjectMapper objectMapper = new ObjectMapper();
        String dadosStr;
        try {
            dadosStr = objectMapper.writeValueAsString(dados);
            String entidadeDestino = "";
            if (dados.getDados() != null && dados.getDados().size() > 0) {
                entidadeDestino = dados.getDados().get(0).getFormulario().getEntidadeDestino();
            } else {
                entidadeDestino = dados.getMidias().get(0).getFormulario().getEntidadeDestino();
            }
            RabbitService.sendRequest("ctm-service:" + entidadeDestino,
                    dadosStr);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }

    public void salvarDados(final List<ExportacaoDado> dados) {

        ObjectMapper objectMapper = new ObjectMapper();
        String dadosStr;
        try {
            dadosStr = objectMapper.writeValueAsString(dados);
            RabbitService.sendRequest("ctm-service:" + dados.get(0).getFormulario().getEntidadeDestino(), dadosStr);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }

    public void salvarDado(final ExportacaoDado dados) {

        ObjectMapper objectMapper = new ObjectMapper();
        String dadosStr;
        try {
            dadosStr = objectMapper.writeValueAsString(dados);
            RabbitService.sendRequest("ctm-service:" + dados.getFormulario().getEntidadeDestino(), dadosStr);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }

    public void salvarFotos(final List<ExportacaoMidia> midias) {

        ObjectMapper objectMapper = new ObjectMapper();
        String midiasStr;
        try {
            midiasStr = objectMapper.writeValueAsString(midias);
            RabbitService.sendRequest("midia-service:cadastro-tecnico-multifinalitario", midiasStr);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }

    public void salvarFoto(final ExportacaoMidia midia) {

        ObjectMapper objectMapper = new ObjectMapper();
        String midiaStr;
        try {
            midiaStr = objectMapper.writeValueAsString(midia);
            RabbitService.sendRequest("midia-service:cadastro-tecnico-multifinalitario", midiaStr);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }
}