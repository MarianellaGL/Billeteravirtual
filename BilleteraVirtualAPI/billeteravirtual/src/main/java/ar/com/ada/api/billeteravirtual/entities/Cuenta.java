package ar.com.ada.api.billeteravirtual.entities;

import java.math.BigDecimal;
import java.util.*;

import javax.persistence.*;

import ar.com.ada.api.billeteravirtual.excepciones.CuentaPorMonedaException;

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

    private BigDecimal saldo;
    // cuando se declara en BigDecimal se tiene que declarar private Bigdecimal
    // saldo =BigDecimal(0) ya que es un objeto

    @Column(name = "saldodisponible")
    private BigDecimal saldoDisponible;

    @ManyToOne
    @JoinColumn(name = "billetera_id", referencedColumnName = "billetera_id")
    private Billetera billetera;

    @OneToMany(mappedBy = "cuenta", cascade = CascadeType.ALL)
    private List<Movimiento> movimientos = new ArrayList<Movimiento>();

    public static Scanner Teclado = new Scanner(System.in);

    public Cuenta(Billetera b, String moneda) {
        this.moneda = moneda;
        b.getCuentas().add(this);

    }

    public Cuenta() {

    }

    public Usuario getUsuario() {
        Usuario u = this.getBilletera().getPersona().getUsuario();
        return u;

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

    public BigDecimal getSaldo() {
        return saldo;
    }

    public void setSaldo(BigDecimal d) {
        this.saldo.equals(d);
    }

    public BigDecimal getSaldoDisponible() throws CuentaPorMonedaException {
        if (saldo.compareTo(BigDecimal.ZERO) > 0) {
            return saldo;
        } else {

            return BigDecimal.ZERO;

        }

    }

    public void setSaldoDisponible(BigDecimal bigDecimal) {
        this.saldoDisponible.compareTo(bigDecimal.ZERO);
    }

    public Billetera getBilletera() {
        return billetera;
    }

    public void setBilletera(Billetera billetera) {
        this.billetera = billetera;
        this.billetera.getCuentas().add(this);
    }

    public List<Movimiento> getMovimientos() {
        return movimientos;
    }

    public void setMovimientos(List<Movimiento> movimientos) {
        this.movimientos = movimientos;

    }

    public void agregarSaldo(Integer usuarioId, BigDecimal importe, String conceptoOperacion, String detalle) {

        Movimiento m = new Movimiento();

        m.setCuenta(this);
        m.setTipoOperacion("INGRESO");
        m.setImporte(importe);
        m.setconceptoOperacion(conceptoOperacion);
        m.setDetalle(detalle);
        m.setFechaMovimiento(new Date());
        m.setDeUsuarioId(usuarioId);
        m.setaUsuarioId(usuarioId);
        m.setDeCuentaId(this.cuentaId);
        m.setaCuentaId(this.cuentaId);

        this.movimientos.add(m);
    }
}
