package ar.com.ada.api.billeteravirtual.models.response;

import java.math.BigDecimal;

public class TransferenciaResponse {


    public boolean isOk = false;
    public String message = "";
    public Integer billeteraIdOrigen;
    public Integer billeteraIdDestino;
    public BigDecimal importe;
    public Integer operacionId;
}