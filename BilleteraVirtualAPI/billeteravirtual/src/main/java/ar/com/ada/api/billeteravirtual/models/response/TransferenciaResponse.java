package ar.com.ada.api.billeteravirtual.models.response;

public class TransferenciaResponse {


    public boolean isOk = false;
    public String message = "";
    public int billeteraIdOrigen;
    public int billeteraIdDestino;
    public double importe;
    public int operacionId;
}