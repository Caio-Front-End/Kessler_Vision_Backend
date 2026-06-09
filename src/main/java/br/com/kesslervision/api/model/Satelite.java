package br.com.kesslervision.api.model;

import jakarta.persistence.*;

@Entity
@Table(name = "T_KV_SATELITE")
public class Satelite extends AtivoEspacial implements Monitoravel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_SATELITE")
    private Long idSatelite;

    @Column(name = "CD_NORAD", nullable = false, unique = true)
    private Integer cdNorad;

    @Column(name = "NM_SATELITE", length = 100, nullable = false)
    private String nomeSatelite;

    @Column(name = "VL_ALTITUDE_ATUAL", nullable = false)
    private Double altitudeAtual;

    // Relacionamento: Muitos Satélites pertencem a Uma Frota
    @ManyToOne
    @JoinColumn(name = "ID_FROTA", nullable = false)
    private Frota frota;

    public Satelite() {}

    public Satelite(Integer cdNorad, String nomeSatelite, Double altitudeAtual, Frota frota) {
        this.cdNorad = cdNorad;
        this.nomeSatelite = nomeSatelite;
        this.altitudeAtual = altitudeAtual;
        this.frota = frota;
    }

    // Sobrescrita do método da interface Monitoravel
    @Override
    public void atualizarAltitude(String altitudeDaApi) {
        try {
            // Conversão de tipos (String para Double)
            this.altitudeAtual = Double.parseDouble(altitudeDaApi);
            System.out.println("Altitude atualizada para: " + this.altitudeAtual);
        } catch (NumberFormatException e) {
            System.out.println("Erro ao converter altitude.");
        }
    }

    // Getters e Setters
    public Long getIdSatelite() { return idSatelite; }
    public void setIdSatelite(Long idSatelite) { this.idSatelite = idSatelite; }
    public Integer getCdNorad() { return cdNorad; }
    public void setCdNorad(Integer cdNorad) { this.cdNorad = cdNorad; }
    public String getNomeSatelite() { return nomeSatelite; }
    public void setNomeSatelite(String nomeSatelite) { this.nomeSatelite = nomeSatelite; }
    public Double getAltitudeAtual() { return altitudeAtual; }
    public void setAltitudeAtual(Double altitudeAtual) { this.altitudeAtual = altitudeAtual; }
    public Frota getFrota() { return frota; }
    public void setFrota(Frota frota) { this.frota = frota; }
}