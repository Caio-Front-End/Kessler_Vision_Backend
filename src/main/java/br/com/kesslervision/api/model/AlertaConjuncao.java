package br.com.kesslervision.api.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "T_KV_ALERTA_CONJUNCAO")
public class AlertaConjuncao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_ALERTA")
    private Long idAlerta;

    @Column(name = "CD_NORAD_DETRITO", nullable = false)
    private Integer cdNoradDetrito;

    @Column(name = "VL_PROBABILIDADE", nullable = false)
    private Double probabilidade;

    @Column(name = "TP_NIVEL_ALERTA", length = 20, nullable = false)
    private String nivelAlerta;

    @Column(name = "DT_GERACAO_ALERTA", nullable = false)
    private LocalDateTime dataGeracao;

    // Relacionamento: Muitos alertas podem pertencer a Um satélite
    @ManyToOne
    @JoinColumn(name = "ID_SATELITE", nullable = false)
    private Satelite satelite;

    public AlertaConjuncao() {}

    public AlertaConjuncao(Integer cdNoradDetrito, Double probabilidade, String nivelAlerta, LocalDateTime dataGeracao, Satelite satelite) {
        this.cdNoradDetrito = cdNoradDetrito;
        this.probabilidade = probabilidade;
        this.nivelAlerta = nivelAlerta;
        this.dataGeracao = dataGeracao;
        this.satelite = satelite;
    }

    // Getters e Setters
    public Long getIdAlerta() { return idAlerta; }
    public void setIdAlerta(Long idAlerta) { this.idAlerta = idAlerta; }
    public Integer getCdNoradDetrito() { return cdNoradDetrito; }
    public void setCdNoradDetrito(Integer cdNoradDetrito) { this.cdNoradDetrito = cdNoradDetrito; }
    public Double getProbabilidade() { return probabilidade; }
    public void setProbabilidade(Double probabilidade) { this.probabilidade = probabilidade; }
    public String getNivelAlerta() { return nivelAlerta; }
    public void setNivelAlerta(String nivelAlerta) { this.nivelAlerta = nivelAlerta; }
    public LocalDateTime getDataGeracao() { return dataGeracao; }
    public void setDataGeracao(LocalDateTime dataGeracao) { this.dataGeracao = dataGeracao; }
    public Satelite getSatelite() { return satelite; }
    public void setSatelite(Satelite satelite) { this.satelite = satelite; }
}