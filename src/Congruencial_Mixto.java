import Util.Congruencial;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Congruencial_Mixto {
    public JPanel panel1;
    private JButton agregarCongruencialButton;
    private JTextField tfx;
    private JButton generarButton;
    private JTable table1;
    private JList list1;
    private JTextField tfa;
    private JTextField tfm;
    private JLabel lb0;
    private JTextField tfn;
    public int x, a, m, n;
    private Congruencial congruencial;

    public Congruencial_Mixto() {

        congruencial = new Congruencial();
        agregarCongruencialButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DefaultTableModel temp = new DefaultTableModel();
                (temp).addColumn("x");
                (temp).addColumn("a");
                (temp).addColumn("m");

                x = Integer.parseInt(tfx.getText());
                a = Integer.parseInt(tfa.getText());
                m = Integer.parseInt(tfm.getText());

                tfx.setText("");
                tfa.setText("");
                tfm.setText("");
                congruencial.addCongruencial(x,a,m);

                (temp).setNumRows(congruencial.congruenciales.size()+1);
                temp.setValueAt("x",0,0);
                temp.setValueAt("a",0,1);
                temp.setValueAt("m",0,2);
                for(int i=0;i<congruencial.congruenciales.size();i++){
                    temp.setValueAt(congruencial.congruenciales.get(i).x,i+1,0);
                    temp.setValueAt(congruencial.congruenciales.get(i).a,i+1,1);
                    temp.setValueAt(congruencial.congruenciales.get(i).m,i+1,2);
                }

                lb0.setText(String.valueOf(congruencial.lecuyer()));
                table1.setModel(temp);

            }
        });
        generarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                n = Integer.parseInt(tfn.getText());
                congruencial.congruencialMixto(n);
                list1.setListData(congruencial.numerosGenerados.toArray());
            }
        });
    }
}
