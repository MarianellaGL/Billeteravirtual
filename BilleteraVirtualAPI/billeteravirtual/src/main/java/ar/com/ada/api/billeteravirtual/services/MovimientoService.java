package ar.com.ada.api.billeteravirtual.services;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.com.ada.api.billeteravirtual.entities.*;
import ar.com.ada.api.billeteravirtual.excepciones.CuentaPorMonedaException;
import ar.com.ada.api.billeteravirtual.repo.MovimientoRepository;

@Service
public class MovimientoService {

    @Autowired
    MovimientoRepository mr;

    @Autowired
    BilleteraService bs;

    @Autowired
    CuentaService cs;
    
    public void save(Movimiento m){

        mr.save(m);
    }


    /**
     * Método para crear movimientos que viene desde fuera de la API, como cuenta
     * bancaria o tarjeta de crédito.
     * 
     * @param c
     * @param u
     * @param moneda
     * @param concepto por ejemplo "Carga inicial"
     * @param importe
     * @param tipo     "Entrada" o "Salida"
     * @throws CuentaPorMonedaException
     */
    public int depositar(int billeteraId, String moneda, String concepto, double importe, String tipoOperacion)
            throws CuentaPorMonedaException {
        Billetera b = bs.buscarPorId(billeteraId);
        Cuenta c = cs.getCuentaPorMoneda(billeteraId, moneda);
        Movimiento m = new Movimiento();
        Date f = new Date();
        m.setConceptoOperacion(concepto);
        m.setImporte(importe);
        m.setTipoOperacion(tipoOperacion);
        m.setFechaMovimiento(f);
        m.setCuentaOrigenId(c.getCuentaId());
        m.setCuentaDestinoId(c.getCuentaId());
        m.setaUsuarioId(c.getBilletera().getPersona().getUsuario().getUsuarioId());
        m.setDeUsuarioId(c.getBilletera().getPersona().getUsuario().getUsuarioId());
        if (m.getTipo().equals("Entrada")) {
            c.setSaldo(c.getSaldo() + m.getImporte());
            c.setSaldoDisponible(c.getSaldo());
        } else {
            c.setSaldo(c.getSaldo() - m.getImporte());
            c.setSaldoDisponible(c.getSaldo());
        }
        m.setCuenta(c);
        mr.save(m);
        bs.save(b);
        //RequestResponse no devuelve los ids, qué caranchos pasa?
        return m.getMovimientoId();
    }
}
    
