package br.com.kesslervision.api.model;

import jakarta.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class AtivoEspacial {
    private boolean estaAtivo = true;

    public boolean isEstaAtivo() { return estaAtivo; }
    public void setEstaAtivo(boolean estaAtivo) { this.estaAtivo = estaAtivo; }
}