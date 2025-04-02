package tablemodel;

import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;
import model.bean.Servico;

public class ServicoTableModel extends AbstractTableModel {
    
    private List<Servico> listaServicos;
    private String[] colunas = {"ID", "Servico", "Descrição", "Valor"};

    public ServicoTableModel() {
        listaServicos = new ArrayList<>();
    }

    public ServicoTableModel(List<Servico> servico) {
        this();
        this.listaServicos.addAll(servico);
    }

    @Override
    public int getRowCount() {
        return listaServicos.size();
    }

    @Override
    public int getColumnCount() {
        return colunas.length;
    }

    @Override
    public Object getValueAt(int linha, int coluna) {
        Servico servico = listaServicos.get(linha);
        switch (coluna) {
            case 0:
                return servico.getIdservico();
            case 1:
                return servico.getNome();
            case 2:
                return servico.getDescricao();
            case 3:
                return servico.getValor();
            default:
                return "";
        }
    }

    @Override
    public String getColumnName(int column) {
        return colunas[column];
    }

    public Servico getServico(int linha) {
        if (linha >= listaServicos.size()) {
            return null;
        }
        return listaServicos.get(linha);
    }
    
}
