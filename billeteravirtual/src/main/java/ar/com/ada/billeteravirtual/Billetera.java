package ar.com.ada.billeteravirtual;

import java.util.ArrayList;
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

    public void agregarPlata(double plata, String concepto, String detalle) {
        // Agarro el primero y le meto plata
        this.cuentas.get(0).agregarPlata(persona.getUsuario().getUsuarioId(), concepto, plata, detalle);
    }

    public double consultarSaldoCuentaUnica()
    {
        return this.cuentas.get(0).getSaldo();
    }
}