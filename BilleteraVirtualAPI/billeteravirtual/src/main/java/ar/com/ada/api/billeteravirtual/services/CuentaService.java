
package ar.com.ada.api.billeteravirtual.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.com.ada.api.billeteravirtual.entities.*;
import ar.com.ada.api.billeteravirtual.excepciones.CuentaPorMonedaException;
import ar.com.ada.api.billeteravirtual.repo.CuentaRepository;



@Service
public class CuentaService {

    @Autowired
    CuentaRepository repo;

    @Autowired
    BilleteraService bs;

    public Cuenta getCuentaPorMoneda(Integer billeteraId, String moneda) throws CuentaPorMonedaException {
        Billetera b = bs.buscarPorId(billeteraId);
        for (Cuenta c : b.getCuentas()) {
            if (c.getMoneda().equals(moneda)) {
                return c;
            }
        }
        throw new CuentaPorMonedaException("La billetera no posee una cuenta en " + moneda + ".");
    }

}
 