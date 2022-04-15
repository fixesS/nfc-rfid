package com.fixes.GUI;

import com.fixes.Main.Main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class MainFrame extends JFrame {
    private Main main;

    private JFileChooser fileChooser = new JFileChooser();

    private JPanel Mainpanel = new JPanel();
    private JPanel panel1 = new JPanel();
    private JPanel panel2 = new JPanel();
    private JPanel panel3 = new JPanel();
    private JPanel panel4 = new JPanel();

    private JLabel clear_reader = new JLabel("Считыватель");
    private JLabel clear_item = new JLabel("Ноутбук");
    private JLabel clear_staff = new JLabel("Работник");

    private JLabel clear_label1 = new JLabel("");
    private JLabel clear_label2 = new JLabel("");
    private JLabel clear_label3 = new JLabel("");
    private JLabel clear_label4 = new JLabel("");
    private JLabel clear_label5 = new JLabel("");
    private JLabel clear_label6 = new JLabel("");

    private JButton export_log = new JButton("Экспорт логов");

    //reader
    private JLabel label_reader_id = new JLabel("sensor id:");
    private JLabel label_reader_name = new JLabel("Название:");
    private JLabel label_reader_hostname = new JLabel("IP-Адресс:");
    private JLabel label_reader_port = new JLabel("Порт:");
    private JLabel label_reader_timeout = new JLabel("Тайм-аут:");
    private JLabel label_reader_location = new JLabel("Местоположение:");

    private JTextField textField_reader_id = new JTextField(3);
    private JTextField textField_reader_name = new JTextField(8);
    private JTextField textField_reader_hostname = new JTextField(10);
    private JTextField textField_reader_port = new JTextField(8);
    private JTextField textField_reader_timeout = new JTextField(4);
    private JTextField textField_reader_location = new JTextField(10);

    private JButton button_reader = new JButton("Зарегестрировать");

    //item
    private JLabel label_item_id = new JLabel("tag id:");
    private JLabel label_item_name = new JLabel("Название:");
    private JLabel label_item_location = new JLabel("Сотрудник:");////


    private JTextField textField_item_id = new JTextField(3);
    private JTextField textField_item_name = new JTextField(10);
    private JTextField textField_item_location = new JTextField(10);

    private JButton button_item = new JButton("Зарегестрировать");

    //staff
    private JLabel label_staff_id = new JLabel("worker id:");
    private JLabel label_staff_name = new JLabel("Имя:");
    private JLabel label_staff_surname = new JLabel("Фамилия:");
    private JLabel label_staff_position = new JLabel("Должность:");

    private JTextField textField_staff_id = new JTextField(3);
    private JTextField textField_staff_name = new JTextField(12);
    private JTextField textField_staff_surname = new JTextField(12);
    private JTextField textField_staff_position = new JTextField(15);

    private JButton button_staff = new JButton("Зарегестрировать");

    public void setMain(Main main){
        this.main = main;
    }

    public void start(){
        setLayout(new BorderLayout());

        getContentPane().add(Mainpanel,BorderLayout.NORTH);

        Mainpanel.setLayout(new GridLayout(8,0));

        Mainpanel.add(clear_reader);
        Mainpanel.add(panel1);
        Mainpanel.add(clear_item);
        Mainpanel.add(panel2);
        //Mainpanel.add(clear_staff);
        //Mainpanel.add(panel3);
        Mainpanel.add(panel4);



        panel1.setLayout(new GridLayout(2,5,8,0));
        panel2.setLayout(new GridLayout(2,3,8,0));
        panel3.setLayout(new GridLayout(2,4,8,0));
        panel4.setLayout(new FlowLayout());
        //reader
        panel1.add(label_reader_id);
        panel1.add(label_reader_name);
        panel1.add(label_reader_hostname);
        panel1.add(label_reader_port);
        panel1.add(label_reader_timeout);
        panel1.add(label_reader_location);
        panel1.add(clear_label1);

        panel1.add(textField_reader_id);
        panel1.add(textField_reader_name);
        panel1.add(textField_reader_hostname);
        panel1.add(textField_reader_port);
        panel1.add(textField_reader_timeout);
        panel1.add(textField_reader_location);


        panel1.add(button_reader);

        //item
        panel2.add(label_item_id);
        panel2.add(label_item_name);
        panel2.add(label_item_location);
        panel2.add(clear_label2);

        panel2.add(textField_item_id);
        panel2.add(textField_item_name);
        panel2.add(textField_item_location);

        panel2.add(button_item);

        //staff
        panel3.add(label_staff_id);
        panel3.add(label_staff_name);
        panel3.add(label_staff_surname);
        panel3.add(label_staff_position);
        panel3.add(clear_label3);

        panel3.add(textField_staff_id);
        panel3.add(textField_staff_name);
        panel3.add(textField_staff_surname);
        panel3.add(textField_staff_position);

        panel3.add(button_staff);

        //export
        panel4.add(export_log);

        button_reader.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                main.registerReader(textField_reader_id.getText(),
                        textField_reader_name.getText(),
                        textField_reader_location.getText(),
                        textField_reader_hostname.getText(),
                        Integer.parseInt(textField_reader_port.getText()),
                        Integer.parseInt(textField_reader_timeout.getText()));
                textField_reader_id.setText("");
                textField_reader_name.setText("");
                textField_reader_location.setText("");
                textField_reader_hostname.setText("");
                textField_reader_port.setText("");
                textField_reader_timeout.setText("");
            }
        });

        button_item.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                main.registerItem(textField_item_id.getText(),textField_item_name.getText(),"");
                textField_item_id.setText("");
                textField_item_name.setText("");
                textField_item_location.setText("");
            }
        });

        export_log.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                fileChooser.setDialogTitle("Экспорт'");
                fileChooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
                int result = fileChooser.showSaveDialog(Mainpanel);
                if (result == JFileChooser.APPROVE_OPTION ){
                    try {
                        File file = new File("src/main/resources/Log/Mainlog.txt");

                        FileWriter fw = new FileWriter(fileChooser.getSelectedFile()+".txt");
                        fw.write(readFile(file));
                        fw.close();
                    } catch (IOException exception) { exception.printStackTrace(); }

                }
            }
        });

        pack();
        setSize(new Dimension(1220,500));
        setResizable(true);
        setVisible(true);
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
    }
    private String readFile(File file) throws FileNotFoundException {
        List<String> lines = new ArrayList<>();
        Scanner myReader = new Scanner(file);
        while (myReader.hasNextLine()) {
            String line = myReader.nextLine();
            lines.add(line);
        }

        StringBuilder stringBuilder = new StringBuilder();
        for(String line :lines){
            stringBuilder.append(line+"\n");
        }

        lines.clear();
        return stringBuilder.toString();
    }

}
