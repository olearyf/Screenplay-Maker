import javax.swing.*;
import javax.swing.plaf.metal.MetalLookAndFeel;
import javax.swing.plaf.metal.OceanTheme;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import org.apache.pdfbox.*;

class ScreenplayMaker extends JFrame implements ActionListener {

    JTextArea textArea;
    JFrame frame;
    JMenuBar mb;
    JMenu m1, m2, m3;
    JMenuItem mi1, mi2, mi3, mi4, mi5, mi6, mi7, mc, mi13, mi14, mi15,
    mi8, mi9, mi10, mi11, mi12;
    ImageIcon icon;
    Font font;

    ScreenplayMaker(){

        //create frame, add icon, set font of text area
        frame = new JFrame("Screenplay Maker");
        icon = new ImageIcon("C:\\Users\\fraol\\IdeaProjects\\Screenplay-Maker\\typewriter.png");
        frame.setIconImage(icon.getImage());
        textArea = new JTextArea();
        JScrollPane sp = new JScrollPane(textArea);
        font = new Font("DialogInput", Font.PLAIN, 12);
        textArea.setFont(font);

        //set look and feel
        try {
            UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");
            MetalLookAndFeel.setCurrentTheme(new OceanTheme());
        }
        catch(Exception e){

        }

        //create menus
        mb = new JMenuBar();
        m1 = new JMenu("File");

        mi1 = new JMenuItem("New");
        mi2 = new JMenuItem("Open");
        mi3 = new JMenuItem("Save");
        mi4 = new JMenuItem("Print");
        mi15 = new JMenuItem("Exp. Formatted PDF");

        mi1.addActionListener(this);
        mi2.addActionListener(this);
        mi3.addActionListener(this);
        mi4.addActionListener(this);
        mi15.addActionListener(this);

        m1.add(mi1);
        m1.add(mi2);
        m1.add(mi3);
        m1.add(mi4);
        m1.add(mi15);

        m2 = new JMenu("Edit");

        mi5 = new JMenuItem("cut");
        mi6 = new JMenuItem("copy");
        mi7 = new JMenuItem("paste");

        mi5.addActionListener(this);
        mi6.addActionListener(this);
        mi7.addActionListener(this);

        m2.add(mi5);
        m2.add(mi6);
        m2.add(mi7);

        m3 = new JMenu("Add");

        mi8 = new JMenuItem("INT.");
        mi9 = new JMenuItem("EXT.");
        mi10 = new JMenuItem("Setting");
        mi11 = new JMenuItem("Character");
        mi13 = new JMenuItem("Character Action");
        mi12 = new JMenuItem("Dialogue");
        mi14 = new JMenuItem("New Line");

        mi8.setMnemonic(KeyEvent.VK_I);
        mi9.setMnemonic(KeyEvent.VK_E);
        mi10.setMnemonic(KeyEvent.VK_S);
        mi11.setMnemonic(KeyEvent.VK_C);
        mi12.setMnemonic(KeyEvent.VK_D);
        mi13.setMnemonic(KeyEvent.VK_T);
        mi14.setMnemonic(KeyEvent.VK_N);

        Action intAction = new AbstractAction("INT."){
            public void actionPerformed(ActionEvent c){
                textArea.append("I(");
            }
        };
        intAction.putValue(Action.ACCELERATOR_KEY,
                KeyStroke.getKeyStroke(KeyEvent.VK_I,
                        KeyEvent.CTRL_DOWN_MASK));
        mi8.setAction(intAction);

        Action extAction = new AbstractAction("EXT."){
            public void actionPerformed(ActionEvent c){
                textArea.append("E(");
            }
        };
        extAction.putValue(Action.ACCELERATOR_KEY,
                KeyStroke.getKeyStroke(KeyEvent.VK_E,
                        KeyEvent.CTRL_DOWN_MASK));
        mi9.setAction(extAction);

        Action settingAction = new AbstractAction("Setting"){
            public void actionPerformed(ActionEvent c){
                textArea.append("S(");
            }
        };
        settingAction.putValue(Action.ACCELERATOR_KEY,
                KeyStroke.getKeyStroke(KeyEvent.VK_S,
                        KeyEvent.CTRL_DOWN_MASK));
        mi10.setAction(settingAction);

        Action charAction = new AbstractAction("Character"){
            public void actionPerformed(ActionEvent c){
                textArea.append("C(");
            }
        };
        charAction.putValue(Action.ACCELERATOR_KEY,
                KeyStroke.getKeyStroke(KeyEvent.VK_C,
                        KeyEvent.CTRL_DOWN_MASK));
        mi11.setAction(charAction);

        Action dialAction = new AbstractAction("Dialogue"){
            public void actionPerformed(ActionEvent c){
                textArea.append("D(");
            }
        };
        dialAction.putValue(Action.ACCELERATOR_KEY,
                KeyStroke.getKeyStroke(KeyEvent.VK_D,
                        KeyEvent.CTRL_DOWN_MASK));
        mi12.setAction(dialAction);

        Action actAction = new AbstractAction("Character Action"){
            public void actionPerformed(ActionEvent c){
                textArea.append("CA(");
            }
        };
        actAction.putValue(Action.ACCELERATOR_KEY,
                KeyStroke.getKeyStroke(KeyEvent.VK_T,
                        KeyEvent.CTRL_DOWN_MASK));
        mi13.setAction(actAction);

        Action newLineAction = new AbstractAction("New Line"){
            public void actionPerformed(ActionEvent c){
                textArea.append("\n");
            }
        };
        newLineAction.putValue(Action.ACCELERATOR_KEY,
                KeyStroke.getKeyStroke(KeyEvent.VK_N,
                        KeyEvent.CTRL_DOWN_MASK));
        mi14.setAction(newLineAction);


        m3.add(mi8);
        m3.add(mi9);
        m3.add(mi10);
        m3.add(mi11);
        m3.add(mi12);
        m3.add(mi13);
        m3.add(mi14);

        mc = new JMenuItem("Close");

        mc.addActionListener(this);

        mb.add(m1);
        mb.add(m2);
        mb.add(m3);
        mb.add(mc);

        //complete construction
        frame.setJMenuBar(mb);
        frame.add(sp);
        frame.setSize(500,500);
        frame.setResizable(false);
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int h = (int)screenSize.getHeight();
        int w = (int)screenSize.getWidth();
        frame.setLocation(w/2-250, h/2-250);
        frame.show();

    }

    public void actionPerformed(ActionEvent e){
        String s = e.getActionCommand();

        if(s.equals("cut")){
            textArea.cut();
        }
        else if(s.equals("copy")){
            textArea.copy();
        }
        else if(s.equals("paste")){
            textArea.paste();
        }
        else if(s.equals("Save")){
            JFileChooser j = new JFileChooser("f:");

            int r = j.showSaveDialog(null);

            if(r == JFileChooser.APPROVE_OPTION){

                File fi = new File(j.getSelectedFile().getAbsolutePath());

                try{
                    FileWriter wr = new FileWriter(fi, false);

                    BufferedWriter w = new BufferedWriter(wr);

                    w.write(textArea.getText());

                    w.flush();
                    w.close();

                }
                catch(Exception evt){
                    JOptionPane.showMessageDialog(frame, evt.getMessage());
                }
            }
            else{
                JOptionPane.showMessageDialog(frame, "the user cancelled the operation");
            }
        }
        else if(s.equals("Print")){
            try {
                textArea.print();
            }
            catch(Exception evt){
                JOptionPane.showMessageDialog(frame, evt.getMessage());

            }
        } else if (s.equals("Open")) {
            JFileChooser j = new JFileChooser("f:");
            int r = j.showOpenDialog(null);

            if(r == JFileChooser.APPROVE_OPTION){
                File fi = new File(j.getSelectedFile().getAbsolutePath());

                try{
                    String s1 = "", sl = "";

                    FileReader fr = new FileReader(fi);

                    BufferedReader br = new BufferedReader(fr);

                    s1 = br.readLine();

                    while((s1 = br.readLine()) != null){
                        s1 = s1 + "\n" + s1;
                    }
                    textArea.setText(s1);
                }
                catch(Exception evt){
                    JOptionPane.showMessageDialog(frame, evt.getMessage());
                }
            }
            else{
                JOptionPane.showMessageDialog(frame, "the user cancelled the operation");
            }
        }
        else if(s.equals("New")){
            textArea.setText("");
        }
        else if(s.equals("Close")){
            frame.setVisible(false);
            frame.dispose();
        }
        else if(s.equals("Exp. Formatted PDF")){

        }

    }

    public static void main(String args[]){
        ScreenplayMaker m = new ScreenplayMaker();
    }
}
