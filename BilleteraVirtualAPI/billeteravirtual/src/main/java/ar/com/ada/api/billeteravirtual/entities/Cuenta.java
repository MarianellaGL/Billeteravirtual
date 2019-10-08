package ar.com.ada.api.billeteravirtual.entities;

import java.util.*;

import javax.persistence.*;

/**
 * Cuenta
 */
@Entity
@Table(name = "cuenta")
public class Cuenta {

    @Id
    @Column(name = "cuenta_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int cuentaId;

    private String moneda;

    private int saldo;

    @Column(name = "saldodisponible")
    private int saldoDisponible;

    @ManyToOne
    @JoinColumn(name = "billetera_id", referencedColumnName = "billetera_id")
    private Billetera billetera;

    @OneToMany(mappedBy = "cuenta", cascade = CascadeType.ALL)
    private List<Movimiento> movimientos = new ArrayList<Movimiento>();

    public Cuenta(Billetera b, String moneda2) {
	}

	public int getCuentaId() {
        return cuentaId;
    }

    public void setCuentaId(int cuentaId) {
        this.cuentaId = cuentaId;
    }

    public String getMoneda() {
        return moneda;
    }

    public void setMoneda(String moneda) {
        this.moneda = moneda;
    }

    public int getSaldo() {
        return saldo;
    }

    public void setSaldo(int saldo) {
        this.saldo = saldo;
    }

    public int getSaldoDisponible() {
        return saldoDisponible;
    }

    public void setSaldoDisponible(int saldoDisponible) {
        this.saldoDisponible = saldoDisponible;
    }

    public Billetera getBilletera() {
        return billetera;
    }

    public void setBilletera(Billetera billetera) {
        this.billetera = billetera;
    }

    public List<Movimiento> getMovimientos() {
        return movimientos;
    }

    public void setMovimientos(List<Movimiento> movimientos) {
        this.movimientos = movimientos;
    }

    public void agregarPlata(int usuarioDe, String concepto, int plata, String detalle) {
        Movimiento m = new Movimiento();

        m.setCuenta(this);
        m.setTipoOperacion("INGRESO");
        m.setImporte(plata);
        m.setConceptoOperacion(concepto);
        m.setDetalle(detalle);
        m.setFechaMovimiento(new Date());
        m.setDeUsuarioId(usuarioDe);
        m.setaUsuarioId(usuarioDe);
        m.setDeCuentaId(this.cuentaId);
        m.setaCuentaId(this.cuentaId);

        this.movimientos.add(m);
    }

    public void agregarMovimiento(Movimiento m2) {
    }
}
