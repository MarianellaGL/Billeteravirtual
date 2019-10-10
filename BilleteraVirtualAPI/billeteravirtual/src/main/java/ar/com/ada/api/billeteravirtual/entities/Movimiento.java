package ar.com.ada.api.billeteravirtual.entities;

import java.util.*;

import javax.persistence.*;

/**
     * Movimiento
     */
    @Entity
    @Table(name = "movimiento")
    public class Movimiento {
    
        @Id
        @Column(name = "movimiento_id")
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private int movimientoId;
    
        @ManyToOne
        @JoinColumn(name = "cuenta_id", referencedColumnName = "cuenta_id")
        private Cuenta cuenta;
    
        @Column(name = "fechamovimiento")
        private Date fechaMovimiento;
    
        private double importe;
    
        @Column(name = "tipooperacion")
        private String tipoOperacion;
    
        @Column(name = "conceptooperacion")
        private String conceptoOperacion;
    
        private String detalle;
    
        private String estado;
    
        @Column(name = "deusuario_id")
        private int deUsuarioId;
    
        @Column(name = "ausuario_id")
        private int aUsuarioId;
    
        @Column(name = "decuenta_id")
        private int deCuentaId;
    
        @Column(name = "acuenta_id")
        private int aCuentaId;
    
        public int getMovimientoId() {
            return movimientoId;
        }
    
        public void setMovimientoId(int movimientoId) {
            this.movimientoId = movimientoId;
        }
    
        public Cuenta getCuenta() {
            return cuenta;
        }
    
        public void setCuenta(Cuenta cuenta) {
            this.cuenta = cuenta;
            this.cuenta.getMovimientos().add(this);
        }
    
        public Date getFechaMovimiento() {
            return fechaMovimiento;
        }
    
        public void setFechaMovimiento(Date fechaMovimiento) {
            this.fechaMovimiento = fechaMovimiento;
        }
    
        public double getImporte() {
            return importe;
        }

        
    
        public String getConceptoOperacion() {
            return conceptoOperacion;
        }
    
        public void setConceptoOperacion(String conceptoOperacion) {
            this.conceptoOperacion = conceptoOperacion;
        }
    
        public String getDetalle() {
            return detalle;
        }
    
        public void setDetalle(String detalle) {
            this.detalle = detalle;
        }
    
        public String getEstado() {
            return estado;
        }
    
        public void setEstado(String estado) {
            this.estado = estado;
        }
    
        public int getDeUsuarioId() {
            return deUsuarioId;
        }
    
        public void setDeUsuarioId(int deUsuarioId) {
            this.deUsuarioId = deUsuarioId;
        }
    
        public int getaUsuarioId() {
            return aUsuarioId;
        }
    
        public void setaUsuarioId(int aUsuarioId) {
            this.aUsuarioId = aUsuarioId;
        }
    
        public int getDeCuentaId() {
            return deCuentaId;
        }
    
        public void setDeCuentaId(int deCuentaId) {
            this.deCuentaId = deCuentaId;
        }
    
        public int getaCuentaId() {
            return aCuentaId;
        }
    
        public void setaCuentaId(int aCuentaId) {
            this.aCuentaId = aCuentaId;
        }
    
        public String getTipoOperacion() {
            return tipoOperacion;
        }
    
        public void setTipoOperacion(String tipoOperacion) {
            this.tipoOperacion = tipoOperacion;
        }

       
    
    
    
    
        /**
         * Este constructor crea un movimiento inicial. Hay que sacar el print y
         * adaptarlo para generar distintos movimientos, no solo inicial.
         * @param c
         * @param u
         */
        public Movimiento(Cuenta c, Usuario u) {
            Date f = new Date();
            this.setConceptoOperacion("Carga inicial");
            this.setImporte(100);
            this.setTipoOperacion("Entrada");
            this.setFechaMovimiento(f);
            this.setCuentaOrigenId(c.getCuentaId());
            this.setCuentaDestinoId(c.getCuentaId());
            this.setaUsuarioId(u.getUsuarioId());
            this.setDeUsuarioId(u.getUsuarioId());
            if (this.getTipoOperacion().equals("Entrada")) {
                c.setSaldo(c.getSaldo() + this.getImporte());
                c.setSaldoDisponible(c.getSaldo());
            } else {
                c.setSaldo(c.getSaldo() - this.getImporte());
                c.setSaldoDisponible(c.getSaldo());
            }
            this.setCuenta(c);
        }



    public void setImporte(double i) {
    }

    public Movimiento() {
		}

	public void setCuentaDestinoId(int cuentaId) {
    }

    public void setCuentaOrigenId(int cuentaId) {
		}

	public Object getTipo() {
		return null;
	}


	
    
    }
    