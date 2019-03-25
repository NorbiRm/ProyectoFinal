import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Centros_Cuadrados {
    public JPanel panel1;
    private JTextField tfx;
    private JTextField tfn;
    private JButton generarButton;
    private JList list1;
    private JCheckBox normalizadosCheckBox;
    private int x, n;
    private CentrosCuadrados cc;

    public Centros_Cuadrados() {
        generarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    x = Integer.parseInt(tfx.getText());
                    n = Integer.parseInt(tfn.getText());
                    cc = new CentrosCuadrados(x,n);
                    if (normalizadosCheckBox.isSelected())
                        list1.setListData(cc.listaNormalizados.toArray());
                    else
                        list1.setListData(cc.lista.toArray());
                }catch(Exception ex){
                    JOptionPane.showMessageDialog(null,"Los Inputos no cumplen con el formato establecido");
                }




            }
        });
    }
}
