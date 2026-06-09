package br.com.kesslervision.api.model;

import jakarta.persistence.*;

@Entity
@Table(name = "T_KV_FROTA")
public class Frota {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_FROTA")
    private Long idFrota;

    @Column(name = "NM_FROTA", length = 100, nullable = false)
    private String nomeFrota;

    @Column(name = "DS_PROPOSITO", length = 255)
    private String proposito;

    //Chave Estrangeira (FK)
    @ManyToOne
    @JoinColumn(name = "ID_EMPRESA", nullable = false)
    private Empresa empresa;

    public Frota() {}

    // Getters e Setters
    public Long getIdFrota() { return idFrota; }
    public void setIdFrota(Long idFrota) { this.idFrota = idFrota; }
    public String getNomeFrota() { return nomeFrota; }
    public void setNomeFrota(String nomeFrota) { this.nomeFrota = nomeFrota; }
    public String getProposito() { return proposito; }
    public void setProposito(String proposito) { this.proposito = proposito; }
    public Empresa getEmpresa() { return empresa; }
    public void setEmpresa(Empresa empresa) { this.empresa = empresa; }
}