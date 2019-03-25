import Util.Congruencial;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class Congruenciales {
    private JTextField tfx;
    public JPanel panel1;
    private JTextField tfa;
    private JTextField tfc;
    private JTextField tfm;
    private JButton generarButton;
    private JList list1;
    private JComboBox comboBox1;
    private JButton validarButton;
    private JCheckBox normalizadosCheckBox;
    private JTextField tfn;
    private JButton chiCuadradaButton;
    private JButton kolmogorovSmirnovButton;
    private JTextField tfalpha;
    private int x, a, c, m, n;
    private Congruencial congruencial;
    private Pruebas pruebas;

    public Congruenciales() {
        generarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                n = Integer.parseInt(tfn.getText());
                x = Integer.parseInt(tfx.getText());
                a = Integer.parseInt(tfa.getText());
                if (tfc.getText() != null)
                    c = Integer.parseInt(tfc.getText());
                else
                    c = 0;
                m = Integer.parseInt(tfm.getText());
                ArrayList resultados;
                chiCuadradaButton.setEnabled(true);
                kolmogorovSmirnovButton.setEnabled(true);
                switch(comboBox1.getSelectedIndex()){
                    case 0: //lineal
                         congruencial = new Congruencial(x,a,c,m);
                         congruencial.generarNumerosLineal(n);
                         if(normalizadosCheckBox.isSelected()){
                             resultados = congruencial.normalizados;
                         } else
                             resultados = congruencial.numerosGenerados;
                         list1.setListData(resultados.toArray());
                        break;
                    case 1: //multiplicativo
                        congruencial = new Congruencial(x,a,m);
                        congruencial.generarNumerosMultiplicativo(n);
                        if(normalizadosCheckBox.isSelected()){
                            resultados = congruencial.normalizados;
                        } else
                            resultados = congruencial.numerosGenerados;
                        list1.setListData(resultados.toArray());
                        break;
                    case 2: //mixto
                        congruencial = new Congruencial(x,a,c,m);
                        if(congruencial.hullDobel()){
                            congruencial.generarNumerosLineal(n);
                            if(normalizadosCheckBox.isSelected()){
                                resultados = congruencial.normalizados;
                            } else
                                resultados = congruencial.numerosGenerados;
                            list1.setListData(resultados.toArray());
                        }else{
                            JOptionPane.showMessageDialog(null,"Los parámetros no cumplen con HullDobel");
                        }

                        break;
                }


            }
        });
        chiCuadradaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                pruebas = new Pruebas();
                double[] numsDouble = new double[congruencial.normalizados.size()];
                for(int i=0;i<congruencial.normalizados.size();i++){
                    numsDouble[i] = Double.parseDouble(congruencial.normalizados.get(i).toString());
                }
                if(pruebas.ChiCuadrada(numsDouble,Double.parseDouble(tfalpha.getText())))
                    JOptionPane.showMessageDialog(null,"Prueba de Chi-Cuadrada aceptada");
                else
                    JOptionPane.showMessageDialog(null,"Prueba Chi-Cuadrada rechazada");
            }
        });
        kolmogorovSmirnovButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                pruebas = new Pruebas();
                double[] numsDouble = new double[congruencial.normalizados.size()];
                for(int i=0;i<congruencial.normalizados.size();i++){
                    numsDouble[i] = Double.parseDouble(congruencial.normalizados.get(i).toString());
                }

                if(pruebas.Kolmogorov(numsDouble,Double.parseDouble(tfalpha.getText())))
                    JOptionPane.showMessageDialog(null,"Prueba de Kolmogorov-Smirnov aceptada");
                else
                    JOptionPane.showMessageDialog(null,"Prueba Kolmogorov-Smirnov rechazada");

            }
        });
    }
}
