import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;

public class StudentRecordManager extends JFrame implements ActionListener {
    JTextField nameField, rollField, gradeField;
    JButton addBtn, deleteBtn;
    JTable table;
    DefaultTableModel model;

    public StudentRecordManager() {
        setTitle("Student Record Manager");
        setSize(550, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Input Panel
        JPanel panel = new JPanel(new GridLayout(4, 2, 5, 5));
        panel.setBorder(BorderFactory.createTitledBorder("Add New Student"));

        panel.add(new JLabel("Name:"));
        nameField = new JTextField();
        panel.add(nameField);

        panel.add(new JLabel("Roll No:"));
        rollField = new JTextField();
        panel.add(rollField);

        panel.add(new JLabel("Grade:"));
        gradeField = new JTextField();
        panel.add(gradeField);

        addBtn = new JButton("Add");
        addBtn.addActionListener(this);
        panel.add(addBtn);

        deleteBtn = new JButton("Delete Selected");
        deleteBtn.addActionListener(this);
        panel.add(deleteBtn);

        // Table
        String[] cols = { "Name", "Roll No", "Grade" };
        model = new DefaultTableModel(cols, 0);
        table = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(table);

        // Layout
        setLayout(new BorderLayout());
        add(panel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);

        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == addBtn) {
            String name = nameField.getText().trim();
            String roll = rollField.getText().trim();
            String grade = gradeField.getText().trim();

            if (!name.isEmpty() && !roll.isEmpty() && !grade.isEmpty()) {
                model.addRow(new Object[] { name, roll, grade });
                nameField.setText("");
                rollField.setText("");
                gradeField.setText("");
            } else {
                JOptionPane.showMessageDialog(this, "Please fill all fields.");
            }
        } else if (e.getSource() == deleteBtn) {
            int row = table.getSelectedRow();
            if (row != -1) {
                model.removeRow(row);
            } else {
                JOptionPane.showMessageDialog(this, "Select a row to delete.");
            }
        }
    }
