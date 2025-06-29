package model.bean;

public class OrdemCliente {

    private int osid;
    private String desc;
    private double total;
    private String status;
    private Cliente cliente;
    private double desconto;
    private double extras;
    private int idusuario;

    public int getOsid() {
        return osid;
    }

    public void setOsid(int osid) {
        this.osid = osid;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public double getDesconto() {
        return desconto;
    }

    public void setDesconto(double desconto) {
        this.desconto = desconto;
    }

    public double getExtras() {
        return extras;
    }

    public void setExtras(double extras) {
        this.extras = extras;
    }

    public int getIdusuario() {
        return idusuario;
    }

    public void setIdusuario(int idusuario) {
        this.idusuario = idusuario;
    }
}
