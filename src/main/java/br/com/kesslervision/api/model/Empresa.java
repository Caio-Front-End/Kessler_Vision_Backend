package br.com.kesslervision.api.model;

import jakarta.persistence.*;

@Entity
@Table(name = "T_KV_EMPRESA")
public class Empresa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_EMPRESA")
    private Long idEmpresa;

    @Column(name = "NM_EMPRESA", length = 100, nullable = false, unique = true)
    private String nomeEmpresa;

    @Column(name = "NR_DOCUMENTO", length = 20, nullable = false, unique = true)
    private String numeroDocumento;

    public Empresa() {}

    public Empresa(String nomeEmpresa, String numeroDocumento) {
        this.nomeEmpresa = nomeEmpresa;
        this.numeroDocumento = numeroDocumento;
    }

    // Getters e Setters
    public Long getIdEmpresa() { return idEmpresa; }
    public void setIdEmpresa(Long idEmpresa) { this.idEmpresa = idEmpresa; }
    public String getNomeEmpresa() { return nomeEmpresa; }
    public void setNomeEmpresa(String nomeEmpresa) { this.nomeEmpresa = nomeEmpresa; }
    public String getNumeroDocumento() { return numeroDocumento; }
    public void setNumeroDocumento(String numeroDocumento) { this.numeroDocumento = numeroDocumento; }
}