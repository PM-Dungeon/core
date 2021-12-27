package demo;

import javax.swing.*;
import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LayoutSwing extends JFrame {
    int y = 7;
    int x = 7;
    JButton[][] buttons = new JButton[y][x];
    GridLayout grid = new GridLayout(y, x);
    JPanel p = new JPanel();

    public LayoutSwing() {
        startup();
        init();
        update();
    }

    public static void main(String[] args) {
        new LayoutSwing();
    }

    void startup() {

        this.setTitle("CREATE LAYOUT");
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setSize(1280, 720);
        this.setVisible(true);
    }

    void init() {
        p.setLayout(grid);
        for (int j = 0; j < y; j++)
            for (int i = 0; i < x; i++) {
                buttons[j][i] = new JButton("SKIP");
                buttons[j][i].setBackground(Color.GRAY);
                buttons[j][i].addActionListener(
                        new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                String label = "";
                                JButton b = ((JButton) e.getSource());
                                String currentLabel = b.getText();

                                switch (currentLabel) {
                                    case "SKIP":
                                        label = "WALL";
                                        b.setBackground(Color.BLACK);
                                        break;
                                    case "WALL":
                                        label = "FLOOR";
                                        b.setBackground(Color.ORANGE);
                                        break;
                                    case "FLOOR":
                                        label = "WILD";
                                        b.setBackground(Color.RED);
                                        break;
                                    case "WILD":
                                        label = "SKIP";
                                        b.setBackground(Color.GRAY);
                                        break;
                                }
                                b.setText(label);
                            }
                        });
            }

        for (int j = 0; j < y; j++) for (int i = 0; i < x; i++) p.add(buttons[j][i]);

        this.add(BorderLayout.CENTER, p);

        JButton print = new JButton("PRINT");
        print.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        String text = "{";

                        for (int y = 0; y < buttons.length; y++) {
                            text += "{";

                            for (int x = 0; x < buttons[0].length; x++) {
                                if (x == buttons[0].length - 1)
                                    text += "LevelElement." + buttons[y][x].getText();
                                else text += "LevelElement." + buttons[y][x].getText() + ",";
                            }

                            if (y == buttons.length - 1) text += "}";
                            else text += "},";
                        }

                        text += "};";
                        System.out.println(text);
                        StringSelection stringSelection = new StringSelection(text);
                        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
                        clipboard.setContents(stringSelection, null);
                    }
                });

        this.add(BorderLayout.SOUTH, print);
    }

    void update() {
        this.repaint();
        this.revalidate();
    }
}
