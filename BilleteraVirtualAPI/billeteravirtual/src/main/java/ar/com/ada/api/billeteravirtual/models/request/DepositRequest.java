package ar.com.ada.api.billeteravirtual.models.request;

import java.math.BigDecimal;

public class DepositRequest {

    public String moneda;
    public BigDecimal importeADepositar;
    public String tipo;
    public String concepto;
    public String detalle;
}