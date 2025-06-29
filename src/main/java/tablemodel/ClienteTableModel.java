package tablemodel;

import java.util.ArrayList;
import java.util.List;
import model.bean.Cliente;
import javax.swing.table.AbstractTableModel;

public class ClienteTableModel extends AbstractTableModel {
    
    private List<Cliente> listaClientes;
    private String[] colunas = {"ID", "Nome", "Telefone", "E-mail", "Endereço"};

    public ClienteTableModel() {
        listaClientes = new ArrayList<>();
    }

    public ClienteTableModel(List<Cliente> cliente) {
        this();
        this.listaClientes.addAll(cliente);
    }

    @Override
    public int getRowCount() {
        return listaClientes.size();
    }

    @Override
    public int getColumnCount() {
        return colunas.length;
    }

    @Override
    public Object getValueAt(int linha, int coluna) {
        Cliente cliente = listaClientes.get(linha);
        switch (coluna) {
            case 0:
                return cliente.getIdcliente();
            case 1:
                return cliente.getNome();
            case 2:
                return cliente.getTelefone();
            case 3:
                return cliente.getEmail();
            case 4:
                return cliente.getEndereco();
            default:
                return "";
        }
    }

    @Override
    public String getColumnName(int column) {
        return colunas[column];
    }

    public Cliente getCliente(int linha) {
        if (linha >= listaClientes.size()) {
            return null;
        }
        return listaClientes.get(linha);
    }
    
}
