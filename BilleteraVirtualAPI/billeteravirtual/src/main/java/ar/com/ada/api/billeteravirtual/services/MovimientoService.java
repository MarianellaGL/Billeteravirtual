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
    MovimientoRepository repo;

    @Autowired
    BilleteraService bs;

    @Autowired
    CuentaService cs;

    public void save(Movimiento m) {

        repo.save(m);
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
    public int depositar(int billeteraId, String moneda, String conceptoOperacion, double importe, String tipoOperacion, String detalle)
            throws CuentaPorMonedaException {
        Billetera b = bs.buscarPorId(billeteraId);
        Cuenta c = cs.getCuentaPorMoneda(billeteraId, moneda);
        Movimiento m = new Movimiento();
        Date f = new Date();
        m.setconceptoOperacion("Depósito");
        m.setImporte(importe);
        m.setTipoOperacion(tipoOperacion);
        m.setDetalle(detalle);
        m.setFechaMovimiento(f);
        m.setDeCuentaId(c.getCuentaId());
        m.setaCuentaId(c.getCuentaId());
        m.setDeUsuarioId(c.getBilletera().getPersona().getUsuario().getUsuarioId());
        m.setaUsuarioId(c.getBilletera().getPersona().getUsuario().getUsuarioId());
        if (m.getTipoOperacion().equals("Entrada")) {
            c.setSaldo(c.getSaldo() + m.getImporte());
            c.setSaldoDisponible(c.getSaldo());
        } else {
            c.setSaldo(c.getSaldo() - m.getImporte());
            c.setSaldoDisponible(c.getSaldo());
        }
        m.setCuenta(c);
        repo.save(m);

        return m.getMovimientoId();
    }

}
