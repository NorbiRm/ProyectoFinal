import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Menu {
    private JButton centrosCuadradosButton;
    public JPanel panel1;
    private JButton congruencialMixtoButton;
    private JButton congruencialesButton;

    public Menu() {
        centrosCuadradosButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                JFrame frame = new JFrame("Centros Cuadrados");
                frame.setContentPane(new Centros_Cuadrados().panel1);
                frame.pack();
                frame.setVisible(true);
            }
        });
        congruencialMixtoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                JFrame frame = new JFrame("Congruencial Mixto");
                frame.setContentPane(new Congruencial_Mixto().panel1);
                frame.pack();
                frame.setVisible(true);
            }
        });
        congruencialesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                JFrame frame = new JFrame("Congruenciales");
                frame.setContentPane(new Congruenciales().panel1);
                frame.pack();
                frame.setVisible(true);
            }
        });
    }
}
