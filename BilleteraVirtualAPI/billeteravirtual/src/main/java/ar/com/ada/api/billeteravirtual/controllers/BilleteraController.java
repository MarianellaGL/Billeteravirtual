package ar.com.ada.api.billeteravirtual.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import ar.com.ada.api.billeteravirtual.excepciones.CuentaPorMonedaException;
import ar.com.ada.api.billeteravirtual.models.request.MovimientoRequest;
import ar.com.ada.api.billeteravirtual.models.request.TransferenciaRequest;
import ar.com.ada.api.billeteravirtual.models.response.MovimientoResponse;
import ar.com.ada.api.billeteravirtual.models.response.TransferenciaResponse;
import ar.com.ada.api.billeteravirtual.services.*;

//los controllers son los que van a tener los requestmapping y los autowired que llaman a los services 


@RestController
public class BilleteraController {

    @Autowired
    BilleteraService bs;

    @Autowired
    MovimientoService ms;

    @Autowired
    CuentaService cs;

    @GetMapping("billeteras/{billeteraId}/saldos/{moneda}")
    public double getConsultarSaldo(@PathVariable int billeteraId,@PathVariable String moneda){

        double saldo = bs.consultarSaldo(billeteraId, moneda);
        return saldo;


    }

    @PostMapping("billeteras/depositos")
    public MovimientoResponse postAgregarPlata(@RequestBody MovimientoRequest req)
            throws CuentaPorMonedaException{
        MovimientoResponse r = new MovimientoResponse();
      //se agrega plata en un nuevo movimiento

        int movimientoId = ms.depositar(req.billeteraId, req.moneda, req.conceptoOperacion, req.importe, req.tipoOperacion, req.detalle);

        r.isOk = true;
        r.message = "Transacción realizada";
        r.billeteraId = req.billeteraId;
        r.detalle = req.detalle;
        r.moneda = req.moneda;
        r.movimientoId = movimientoId;
        
    
    
        return r;

    }

    @PostMapping("billeteras/transferencias")
    public TransferenciaResponse postTransferencia(@RequestBody TransferenciaRequest req){
        TransferenciaResponse r = new TransferenciaResponse();

        int operacionId = bs.transferir(req.billeteraIdOrigen, req.billeteraIdDestino, req.importe);

        r.isOk = true;
        r.message = "Transferencia realizada";
        r.billeteraIdOrigen = req.billeteraIdOrigen;
        r.billeteraIdDestino = req.billeteraIdDestino;
        r.importe = req.importe;
        r.operacionId = operacionId;
        return r; 
    }
}


     

    

