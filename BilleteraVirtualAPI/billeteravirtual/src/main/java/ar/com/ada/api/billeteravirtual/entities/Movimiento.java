package ar.com.ada.api.billeteravirtual.entities;
import java.math.BigDecimal;
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
        private Integer movimientoId;
    
        @ManyToOne
        @JoinColumn(name = "cuenta_id", referencedColumnName = "cuenta_id")
        private Cuenta cuenta;
    
        @Column(name = "fechamovimiento")
        private Date fechaMovimiento;
    
        private BigDecimal importe;
        @Column(name = "tipooperacion")
        private String tipoOperacion;
    
        @Column(name = "conceptooperacion")
        private String conceptoOperacion;
    
        private String detalle;
    
        private String estado;
    
        @Column(name = "deusuario_id")
        private Integer deUsuarioId;
    
        @Column(name = "ausuario_id")
        private Integer aUsuarioId;
    
        @Column(name = "decuenta_id")
        private Integer deCuentaId;
    
        @Column(name = "acuenta_id")
        private Integer aCuentaId;
    
        public Integer getMovimientoId() {
            return movimientoId;
        }
    
        public void setMovimientoId(Integer movimientoId) {
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
    
        public BigDecimal getImporte() {
            return importe;
        }

        public void setImporte(BigDecimal importe){
            this.importe= importe;
        }

        
    
        public String getconceptoOperacion() {
            return conceptoOperacion;
        }
    
        public void setconceptoOperacion(String conceptoOperacion) {
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
    
        public Integer getDeUsuarioId() {
            return deUsuarioId;
        }
    
        public void setDeUsuarioId(Integer deUsuarioId) {
            this.deUsuarioId = deUsuarioId;
        }
    
        public Integer getaUsuarioId() {
            return aUsuarioId;
        }
    
        public void setaUsuarioId(Integer aUsuarioId) {
            this.aUsuarioId = aUsuarioId;
        }
    
        public Integer getDeCuentaId() {
            return deCuentaId;
        }
    
        public void setDeCuentaId(Integer deCuentaId) {
            this.deCuentaId = deCuentaId;
        }
    
        public Integer getaCuentaId() {
            return aCuentaId;
        }
    
        public void setaCuentaId(Integer aCuentaId) {
            this.aCuentaId = aCuentaId;
        }
    
        public String getTipoOperacion() {
            return tipoOperacion;
        }
    
        public void setTipoOperacion(String tipoOperacion) {
            this.tipoOperacion = tipoOperacion;
        }

       
    
    
    
        public Movimiento(Cuenta c, Usuario u) {
            Date f = new Date();
            this.setconceptoOperacion(conceptoOperacion);
            this.setImporte(importe);
            this.setTipoOperacion(tipoOperacion);
            this.setFechaMovimiento(f);
            this.setDetalle(detalle);
            this.setDeCuentaId(c.getCuentaId());
            this.setaCuentaId(c.getCuentaId());
            this.setaUsuarioId(u.getUsuarioId());
            this.setDeUsuarioId(u.getUsuarioId());
            if (this.getTipoOperacion().equals("Entrada")) {
                c.setSaldo(c.getSaldo().add(this.getImporte()));
                c.setSaldoDisponible(c.getSaldo());
            } else {
                c.setSaldo(c.getSaldo().add(this.getImporte()));
                c.setSaldoDisponible(c.getSaldo());
            }
            this.setCuenta(c);
        }



  

    public Movimiento() {
		}

	
    
    }
    