package f13;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class TestAVL extends JFrame{
    private ShowAVL<Integer,Integer> nodeView = new ShowAVL<Integer,Integer>( null, 500, 500 );
    private JButton newTree = new JButton("Nytt träd");
    private JButton btnPut = new JButton("Put");
    private JButton btnRemove = new JButton("Remove");
    private JTextField nodes = new JTextField();
    private JPanel southPanel = new JPanel(new GridLayout(1,4));
    private AVLTree<Integer,Integer> tree;
    
    public TestAVL() {
    	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        newTree.addActionListener(new NewTree());
        btnPut.addActionListener(new Put());
        btnRemove.addActionListener(new Remove());
        southPanel.add(newTree);
        southPanel.add(btnPut);
        southPanel.add(btnRemove);
        southPanel.add(nodes);
        add(southPanel,BorderLayout.SOUTH);
        add(nodeView,BorderLayout.CENTER);
        pack();
        setVisible(true);
    }
    
    private class NewTree implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            try {
                Integer[] heltal = new Integer[Integer.parseInt(nodes.getText())];
                tree = new AVLTree<Integer,Integer>();
                for(int i=0; i<heltal.length; i++) {
                    heltal[i] = new Integer((int)(Math.random()*90+10));
                    tree.put(heltal[i],heltal[i]);
                }
                nodeView.setNode( tree.root() );
            } catch(NumberFormatException ex) {}
        }
    }
    
    private class Put implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            try {
                Integer value = new Integer(Integer.parseInt(nodes.getText()));
                tree.put(value, value);
                nodeView.setNode( tree.root() );
            } catch(NumberFormatException ex) {}
        }
    }
    
    private class Remove implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            try {
                Integer value = new Integer(Integer.parseInt(nodes.getText()));
                System.out.println(tree.remove(value));
                nodeView.setNode( tree.root() );
            } catch(NumberFormatException ex) {}
        }
    }
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new TestAVL();                
            }
        });
    }
}

