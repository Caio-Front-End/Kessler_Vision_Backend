package br.com.kesslervision.api.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "T_KV_MANOBRA_EVASIVA")
public class ManobraEvasiva {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_MANOBRA")
    private Long idManobra;

    @Column(name = "VL_ALTITUDE_ANTERIOR", nullable = false)
    private Double altitudeAnterior;

    @Column(name = "VL_ALTITUDE_NOVA", nullable = false)
    private Double altitudeNova;

    @Column(name = "DT_EXECUCAO", nullable = false)
    private LocalDateTime dataExecucao;

    // Relacionamento: Muitas manobras (histórico) podem estar associadas a Um alerta
    @ManyToOne
    @JoinColumn(name = "ID_ALERTA", nullable = false)
    private AlertaConjuncao alerta;

    public ManobraEvasiva() {}

    public ManobraEvasiva(Double altitudeAnterior, Double altitudeNova, LocalDateTime dataExecucao, AlertaConjuncao alerta) {
        this.altitudeAnterior = altitudeAnterior;
        this.altitudeNova = altitudeNova;
        this.dataExecucao = dataExecucao;
        this.alerta = alerta;
    }

    // Getters e Setters
    public Long getIdManobra() { return idManobra; }
    public void setIdManobra(Long idManobra) { this.idManobra = idManobra; }
    public Double getAltitudeAnterior() { return altitudeAnterior; }
    public void setAltitudeAnterior(Double altitudeAnterior) { this.altitudeAnterior = altitudeAnterior; }
    public Double getAltitudeNova() { return altitudeNova; }
    public void setAltitudeNova(Double altitudeNova) { this.altitudeNova = altitudeNova; }
    public LocalDateTime getDataExecucao() { return dataExecucao; }
    public void setDataExecucao(LocalDateTime dataExecucao) { this.dataExecucao = dataExecucao; }
    public AlertaConjuncao getAlerta() { return alerta; }
    public void setAlerta(AlertaConjuncao alerta) { this.alerta = alerta; }
}