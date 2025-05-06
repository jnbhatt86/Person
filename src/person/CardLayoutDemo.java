package person;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class CardLayoutDemo extends JFrame{
	private int currentCard = 1;
    private JPanel cardPanel;
    private CardLayout cl;
    
    public CardLayoutDemo() {
        
        setTitle("Card Layout Example - Student JPanel Widgets");
        setSize(800, 800);
        cardPanel = new JPanel();
        cl = new CardLayout();
        cardPanel.setLayout(cl);

/////////////////////////////////////////////////////

        Vector<JPanel> jp  = new Vector<JPanel>();
        jp.add(new PersonUI());
        
////////////////////////////////////////////////////

        // SAMPLE

        // jp.add(new JDG_MusicPlayer());
        // jp.add(new JDG_EmployeeHourlyPayDemo());
        // jp.add(new JDG_ISSFinder());
        // jp.add(new JDG_GoogleMapsDemo());

///////////////////////////////////////////////////////

        // ADD WIDGETS HERE


///////////////////////////////////////////////////////

        for(int i = 0; i < jp.size(); i++){
           cardPanel.add(jp.get(i), (i+1)+"");
        }
 
//////////////////////////////////////////////////////

        JPanel buttonPanel = new JPanel();
        JButton firstBtn = new JButton("First");
        JButton nextBtn = new JButton("Next");
        JButton previousBtn = new JButton("Previous");
        JButton lastBtn = new JButton("Last");
        buttonPanel.add(firstBtn);
        buttonPanel.add(previousBtn);
        buttonPanel.add(nextBtn);
        buttonPanel.add(lastBtn);
        
        firstBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                cl.first(cardPanel);
                currentCard = 1;
            }
        });
        
        lastBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                cl.last(cardPanel);
                currentCard = jp.size();
            }
        });
        
        nextBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                if (currentCard < jp.size()) {
                    currentCard += 1;
                    cl.show(cardPanel, "" + (currentCard));
                }
            }
        });
        
        previousBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                if (currentCard > 1) {
                    currentCard -= 1;
                    cl.show(cardPanel, "" + (currentCard));
                }
            }
        });
        
        getContentPane().add(cardPanel, BorderLayout.NORTH);
        getContentPane().add(buttonPanel, BorderLayout.SOUTH);
    }

    public static void main(String[] args) {
        CardLayoutDemo cl = new CardLayoutDemo();
        cl.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        cl.setVisible(true);
    }
}
