package ar.com.ada.api.billeteravirtual.services;

import java.math.BigDecimal;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.com.ada.api.billeteravirtual.entities.*;
import ar.com.ada.api.billeteravirtual.excepciones.CuentaPorMonedaException;
import ar.com.ada.api.billeteravirtual.repo.BilleteraRepository;

/**
 * BilleteraService
 */
@Service
public class BilleteraService {

    @Autowired
    BilleteraRepository repo;

    @Autowired
    MovimientoService ms;

    public void save(Billetera b) {
        repo.save(b);
    }

    public Billetera buscarPorId(int id) {

        Optional<Billetera> b = repo.findById(id);

        if (b.isPresent()) {
            return b.get();
        }
        return null;
    }

    public Billetera buscarPorPersona(Persona p) {

        return repo.findByPersona(p);
    }

    public double consultarSaldo(int billeteraId, String moneda) {
        Billetera b = this.buscarPorId(billeteraId);
        double s = 0;

        for (Cuenta c : b.getCuentas()) {

            if (c.getMoneda().equals(moneda)) {

                s = c.getSaldo();
            }
        }

        return s;

    }

    public int transferir(int billeteraIdOrigen, int billeteraIdDestino, double importe) throws CuentaPorMonedaException
    
    {
        Billetera b1 = this.buscarPorId(billeteraIdOrigen);
        Billetera b2 = this.buscarPorId(billeteraIdDestino);
        int mov = b1.movimientoTransferir(-importe, b1.getCuenta(0), b2.getCuenta(0));
        b2.movimientoTransferir(importe, b2.getCuenta(0), b1.getCuenta(0));
        repo.save(b1);
        repo.save(b2);
        return mov;
    }
}
