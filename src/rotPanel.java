import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.Scanner;

public class rotPanel {
    private JPanel rotPanelGUI;
    private JTextArea textArea1;
    private JButton sparasomButton;
    private JButton nyButton;
    private JButton oppnaButton;
    private JButton sparaButton;

    public static void main(String[] args) {
        JFrame frame = new JFrame("rotPanel");
        frame.setContentPane(new rotPanel().rotPanelGUI);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        frame.setSize(new Dimension(500, 400));

    }


    public rotPanel() {
        sparasomButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String filnamn = JOptionPane.showInputDialog(null, "Vad ska filen heta?");
                JFileChooser fc = new JFileChooser();
                int r = fc.showOpenDialog(null);
                fc.getSelectedFile();

                BufferedWriter writer = null;

                try {
                    PrintWriter utström = new PrintWriter
                            (new BufferedWriter
                                    (new OutputStreamWriter
                                            (new FileOutputStream(fc + filnamn + ".txt"))));
                    utström.println(textArea1.getText());
                } catch (FileNotFoundException e1) {
                    e1.printStackTrace();
                }
            }
        });
        oppnaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JButton open = new JButton();
                JFileChooser fc = new JFileChooser();
                int result = fc.showOpenDialog(null);
                String filename = fc.getSelectedFile().getAbsolutePath();
                try {
                    Scanner in = new Scanner(fc.getSelectedFile());
                    while (in.hasNext()) {
                        textArea1.setText(in.nextLine());
                    }
                } catch (FileNotFoundException e1) {
                    e1.printStackTrace();
                }
            }
        });
        nyButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                textArea1.setText("");
            }
        });
        sparaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String filnamn = JOptionPane.showInputDialog(null, "Vad ska filen heta?");

                BufferedWriter writer = null;

                try {
                    File logFile = new File(filnamn);
                    System.out.println("Sparade filen som: " + logFile.getAbsolutePath());

                    writer = new BufferedWriter(new FileWriter(logFile));
                    writer.write(textArea1.getText());
                } catch (Exception e1) {
                    e1.printStackTrace();
                } finally {
                    try {
                        writer.close();
                    } catch (IOException e1) {
                    }
                }
            }
        });
    }
}
