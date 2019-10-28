package ar.com.ada.api.billeteravirtual.entities;


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


    private double saldo;
// cuando se declara en BigDecimal se tiene que declarar  private Bigdecimal saldo =BigDecimal(0) ya que es un objeto

    @Column(name = "saldodisponible")
    private double saldoDisponible;

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

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double d) {
        this.saldo = d;
    }

    public double getSaldoDisponible() throws CuentaPorMonedaException {
        if (saldo > 0 ) {
            return saldo;
        } else { 

            return 0;

        }

    }

    public void setSaldoDisponible(double d) {
        this.saldoDisponible = 0;
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

}
