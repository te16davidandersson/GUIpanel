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
    private String filePath;

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
                saveAsDialog();
            }
        });
        oppnaButton.addActionListener(e -> {
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.showOpenDialog(null);
            try {
                Scanner in = new Scanner(fileChooser.getSelectedFile());
                while (in.hasNext()) {
                    textArea1.setText(in.nextLine());
                }
            } catch (FileNotFoundException e1) {
                e1.printStackTrace();
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
                if (filePath == null) {
                    saveAsDialog();
                } else {
                    try
                            (PrintWriter output = new PrintWriter
                                    (new BufferedWriter
                                            (new OutputStreamWriter
                                                    (new FileOutputStream(filePath))))) {
                        output.println(textArea1.getText());
                    } catch (Exception e1) {
                        e1.printStackTrace();
                    }
                }
            }
        });
    }

    private void saveAsDialog() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.showSaveDialog(null);
        String filePath = fileChooser.getSelectedFile().getPath() + ".txt";
        try (PrintWriter output = new PrintWriter
                (new BufferedWriter
                        (new OutputStreamWriter
                                (new FileOutputStream(filePath))))) {
            output.println(textArea1.getText());
            this.filePath = filePath;
        } catch (FileNotFoundException e1) {
            e1.printStackTrace();
        }
    }
}