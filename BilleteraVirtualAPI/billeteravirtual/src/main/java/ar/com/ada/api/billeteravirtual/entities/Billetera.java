package ar.com.ada.api.billeteravirtual.entities;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.*;

/**
 * BilleteraVirtual
 */
        @Entity
        @Table(name = "billetera")
public class Billetera {

    @Id
    @Column(name = "billetera_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int billeteraId;

    @OneToOne
    @JoinColumn(name = "persona_id", referencedColumnName = "persona_id")
    private Persona persona;

    @OneToMany(mappedBy = "billetera", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Cuenta> cuentas = new ArrayList<Cuenta>();
    
    public Billetera(Persona p) {
        this.setPersona(p);
        p.setBilletera(this);
    }

	public int getBilleteraId() {
        return billeteraId;
    }

    public void setBilleteraId(int billeteraId) {
        this.billeteraId = billeteraId;
        
    }
    
    
    public Persona getPersona() {
        return persona;
    }

    public void setPersona(Persona persona) {
        this.persona = persona;

    }


    
    public Cuenta getCuenta(int index){
        return getCuentas().get(index);
    }


    public List<Cuenta> getCuentas() {
        return cuentas;
    }

    public void setCuentas(List<Cuenta> cuentas) {
        this.cuentas = cuentas;
    }

    public void agregarCuenta(Cuenta cuenta) {
        this.cuentas.add(cuenta);
        cuenta.setBilletera(this);
    }


    public double consultarSaldoDisponible(Billetera b, String moneda)
    {
        double s = 0;

        for (Cuenta c : b.getCuentas()){
            if (c.getMoneda().equals(moneda)){
                s = c.getSaldoDisponible();
            }
        }

        return s;
      
    }

    
    public Billetera(){

    }

 /**
     * Hace una transferencia entre cuentas principales.
     * 
     * @param importe
     * @param bOrigen
     * @param bDestino
     */
    public int movimientoTransferir(double importe, Cuenta deCuentaId, Cuenta aCuentaId) {
        Movimiento m = new Movimiento();
        m.setImporte(importe);
        m.setCuenta(this.getCuenta(0));
        Date f = new Date();
        m.setconceptoOperacion("Transferencia");
        m.setTipoOperacion("Transferencia");
        m.setDetalle("");
        m.setFechaMovimiento(f);
        m.setDeCuentaId(deCuentaId.getCuentaId());
        m.setaCuentaId(aCuentaId.getCuentaId());
        m.setDeUsuarioId(deCuentaId.getUsuario().getUsuarioId());
        m.setaUsuarioId(aCuentaId.getUsuario().getUsuarioId());
        deCuentaId.setSaldo(deCuentaId.getSaldo() + importe);
        deCuentaId.setSaldoDisponible(deCuentaId.getSaldoDisponible() + importe);
        return m.getMovimientoId();
    }
    
}


    
