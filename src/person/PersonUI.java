package person;

import java.awt.BorderLayout;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Collections;
import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class PersonUI extends JPanel{

	private static DefaultListModel<Person> personModel = new DefaultListModel<>();
    private JList<Person> personList;
    private static boolean modified = false;
    private static File currentFile = null;

    public PersonUI() {
        setLayout(new BorderLayout());

        // Toolbar
        JMenuBar menuBar = createMenuBar();
        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.add(menuBar, BorderLayout.NORTH);
        add(topPanel, BorderLayout.NORTH);

        // Center: JList of Persons
        personList = new JList<>(personModel);
        add(new JScrollPane(personList), BorderLayout.CENTER);

        // Bottom: action buttons
        JPanel bottomPanel = new JPanel();
        JButton addBtn = new JButton("Add Person");
        JButton deleteBtn = new JButton("Delete");
        bottomPanel.add(addBtn);
        bottomPanel.add(deleteBtn);
        add(bottomPanel, BorderLayout.SOUTH);

        
    }

    private JMenuBar createMenuBar() {
        JMenuBar bar = new JMenuBar();

        JMenu file = new JMenu("File");
        JMenuItem newFile = new JMenuItem("New");
        JMenuItem openFile = new JMenuItem("Open...");
        JMenuItem saveFile = new JMenuItem("Save");
        JMenuItem saveAsFile = new JMenuItem("Save As...");
        JMenuItem exitItem = new JMenuItem("Exit");

        file.add(newFile);
        file.add(openFile);
        file.add(saveFile);
        file.add(saveAsFile);
        file.addSeparator();
        file.add(exitItem);

        // Implement listeners (see below)
        newFile.addActionListener(e -> newFile());
        openFile.addActionListener(e -> openFile());
        saveFile.addActionListener(e -> saveFile());
        saveAsFile.addActionListener(e -> saveFileAs());
        exitItem.addActionListener(e -> System.exit(0));

        bar.add(file);
        return bar;
    }

    
    
    private void newFile() {
        personModel.clear();
        currentFile = null;
        modified = false;
    }

    private void openFile() {
        JFileChooser chooser = new JFileChooser();
        int res = chooser.showOpenDialog(this);
        if (res == JFileChooser.APPROVE_OPTION) {
            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(chooser.getSelectedFile()))) {
                personModel.clear();
                List<Person> list = (List<Person>) ois.readObject();
                for (Person p : list) personModel.addElement(p);
                currentFile = chooser.getSelectedFile();
                modified = false;
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Failed to load file.");
            }
        }
    }

    private static void saveFile() {
        if (currentFile != null) {
            try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(currentFile))) {
                oos.writeObject(Collections.list(personModel.elements()));
                modified = false;
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            saveFileAs();
        }
    }

    private static void saveFileAs() {
        JFileChooser chooser = new JFileChooser();
        int res = chooser.showSaveDialog(null);
        if (res == JFileChooser.APPROVE_OPTION) {
            currentFile = chooser.getSelectedFile();
            saveFile();
        }
    }

    public static boolean isModified() {
        return modified;
    }

    public static void saveData() {
        saveFile();
    }
}
