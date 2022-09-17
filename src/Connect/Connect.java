
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Connect;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

/**
 *
 * @author Mohamed
 */
public class Connect {

    public static Connection con;
    public static PreparedStatement pre;
    public static ResultSet res;

    public static Connection connect() throws SQLException {
        return DriverManager.getConnection("jdbc:sqlite:Container.db");
    }

    //Login And Check
    public static ArrayList<Check_admin> login() {
        String Sql = "select * from admin";
        ArrayList<Check_admin> list = new ArrayList<>();
        try (Connection con = connect();
                PreparedStatement pre = con.prepareStatement(Sql)) {
            res = pre.executeQuery();

            while (res.next()) {
                list.add(new Check_admin(res.getString("user"), res.getString("pass")));
            }

        } catch (Exception e) {
        }
        return list;
    }

    public static int check(JTextField user, JTextField pass) {
        int x = 0;
        ArrayList<Check_admin> array = login();
        for (int i = 0; i < array.size(); i++) {
            if (array.get(i).getUser().equalsIgnoreCase(user.getText()) && array.get(i).getPass().equals(pass.getText().toString())) {
                x = 0;
                break;
            } else {
                x = 1;
            }
        }
        return x;
    }

    //Display
    public static void Std(JTable t) {
        String Sql = "Select * from Student";
        try (Connection Con = connect();
                PreparedStatement pre = Con.prepareStatement(Sql);) {
            res = pre.executeQuery();
            DefaultTableModel tab = (DefaultTableModel) t.getModel();
            Object[] row = new Object[8];
            while (res.next()) {
                row[0] = res.getString("Name");
                row[1] = res.getString("Address");
                row[2] = res.getString("Age");
                row[4] = res.getString("Class");
                row[3] = res.getString("Subject");
                row[5] = res.getString("Mark1");
                row[6] = res.getString("Mark2");
                row[7] = res.getString("Result");
                tab.addRow(row);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    public static void Teacher(JTable t) {
        String Sql = "Select * from Teacher";
        try (Connection Con = connect();
                PreparedStatement pre = Con.prepareStatement(Sql);) {
            DefaultTableModel tab = (DefaultTableModel) t.getModel();
            res = pre.executeQuery();
            Object[] row = new Object[7];
            while (res.next()) {
                row[0] = res.getString("Name");
                row[1] = res.getString("Address");
                row[2] = res.getString("phone");
                row[3] = res.getString("age");
                row[4] = res.getString("qualification");
                row[5] = res.getString("specialization");
                row[6] = res.getString("salary");
                tab.addRow(row);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    public static void Subject(JTable t) {
        String Sql = "Select * from Subject";
        try (Connection Con = connect();
                PreparedStatement pre = Con.prepareStatement(Sql);) {
            res = pre.executeQuery();
            DefaultTableModel tab = (DefaultTableModel) t.getModel();
            Object[] row = new Object[8];
            while (res.next()) {
                row[0] = res.getString("Class_name");
                row[1] = res.getString("s1");
                row[2] = res.getString("s2");
                row[3] = res.getString("s3");
                row[4] = res.getString("s4");
                row[5] = res.getString("s5");
                row[6] = res.getString("s6");
                row[7] = res.getString("s7");
                tab.addRow(row);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    //ArrayList of Student,Teacher,Subject
    public static ArrayList<Users> Move_std(){
        String Sql = "select * from Student";
         ArrayList<Users> list = new ArrayList();
        try (Connection Con = connect();
                PreparedStatement pre = Con.prepareStatement(Sql)) {
            res = pre.executeQuery();
            while (res.next()) {
                Users user = new Users( Integer.parseInt(res.getString("id")),res.getString("name"), res.getString("address"), Integer.parseInt(res.getString("age")),
                        res.getString("class"), res.getString("subject"),
                        Integer.valueOf(res.getString("mark1")), Integer.valueOf(res.getString("mark2")),
                        Integer.valueOf(res.getString("result")));
                list.add(user);
            }
            return list;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        }
        return null;
    }

    public static ArrayList<Users> Move_tech() {
        String Sql = "Select * from Teacher";
        ArrayList<Users> list = new ArrayList<>();
        try (Connection Con = connect();
                PreparedStatement pre = Con.prepareStatement(Sql);) {
            res = pre.executeQuery();
            while (res.next()) {
                Users tech = new Users( Integer.parseInt(res.getString("id")),res.getString("name"), res.getString("address"), res.getString("specialization"),
                        res.getString("qualification"), Integer.parseInt(res.getString("age")),
                        (res.getString("phone")), Integer.parseInt(res.getString("salary")));
                list.add(tech);
            }
            return list;
        } catch (SQLException e) {
        }

        return null;
    }

    public static ArrayList<Users> Move_sub() {
        String Sql = "Select * from Subject";
        ArrayList<Users> list = new ArrayList<Users>();
        try (Connection Con = connect();
                PreparedStatement pre = Con.prepareStatement(Sql);) {
            res = pre.executeQuery();
            while (res.next()) {
                Users sub = new Users(Integer.parseInt(res.getString("id")),res.getString("class_name"), res.getString("s1"), res.getString("s2"),
                        res.getString("s3"), res.getString("s4"), res.getString("s5"),
                        res.getString("s6"), res.getString("s7"));
                list.add(sub);
            }
            return list;
        } catch (SQLException e) {
        }
        return null;
    }
    // Next And Previous Button

    public static void std_Button(JTextField id,JTextField name, JTextField address, JTextField age, JComboBox clas, JComboBox subject, JTextField mark1, JTextField mark2, JTextField result, int index)  {
        id.setText(Integer.toString(Move_std().get(index).getid()));
        name.setText(Move_std().get(index).getName());
        address.setText(Move_std().get(index).getAddress());
        age.setText(Integer.toString(Move_std().get(index).getAge()));
        clas.setSelectedItem(Move_std().get(index).getClass_name());
        subject.setSelectedItem(Move_std().get(index).getSubject());
        mark1.setText(Integer.toString(Move_std().get(index).getMark1()));
        mark2.setText(Integer.toString(Move_std().get(index).getMark2()));
        result.setText(Integer.toString(Move_std().get(index).getResult()));
    }

    public static void teach_button(JTextField id,JTextField name, JTextField address, JTextField phone, JTextField age, JComboBox qualificaton, JComboBox specialization, JTextField salary, int index) {
        id.setText(Integer.toString(Move_tech().get(index).getid()));
        name.setText(Move_tech().get(index).getName());
        address.setText(Move_tech().get(index).getAddress());
        phone.setText((Move_tech().get(index).getPhone()));
        age.setText(Integer.toString(Move_tech().get(index).getAge()));
        salary.setText(Integer.toString(Move_tech().get(index).getSalary()));
        specialization.setSelectedItem(Move_tech().get(index).getSpecialization());
        qualificaton.setSelectedItem(Move_tech().get(index).getQualification());

    }

    public static void sub_Button(JComboBox name, JTextField id, JTextField s1, JTextField s2, JTextField s3, JTextField s4, JTextField s5, JTextField s6, JTextField s7, int index) {
        name.setSelectedItem(Move_sub().get(index).getClass_name());
        id.setText(Integer.toString(Move_sub().get(index).getid()));
        s1.setText(Move_sub().get(index).getS1());
        s2.setText(Move_sub().get(index).getS2());
        s3.setText(Move_sub().get(index).getS3());
        s4.setText(Move_sub().get(index).getS4());
        s5.setText(Move_sub().get(index).getS5());
        s6.setText(Move_sub().get(index).getS6());
        s7.setText(Move_sub().get(index).getS7());
    }

    //Insert 
    public static void insert_std(JTable table, JTextField name, JTextField address, JTextField age, JComboBox clas, JComboBox subject, JTextField mark1, JTextField mark2, JTextField result) {
        String Sql = "insert into Student(name,address,age,class,subject,mark1,mark2,result) values(?,?,?,?,?,?,?,?)";
        try (Connection Co = connect();
                PreparedStatement pre = Co.prepareStatement(Sql);) {
            pre.setString(1, name.getText());
            pre.setString(2, address.getText());
            pre.setString(3, age.getText());
            pre.setString(4, (String) clas.getSelectedItem());
            pre.setString(5, (String) subject.getSelectedItem());
            pre.setString(6, mark1.getText());
            pre.setString(7, mark2.getText());
            pre.setString(8, result.getText());
            pre.execute();
            table_std_update(table);

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    public static void insert_teacher(JTable table, JTextField name, JTextField address, JTextField phone, JTextField age, JComboBox qualificaton, JComboBox specialization, JTextField salary) {
        String Sql = "insert into Teacher(name,address,phone,age,qualification,specialization,salary) values(?,?,?,?,?,?,?)";
        try (Connection Co = connect();
                PreparedStatement pre = Co.prepareStatement(Sql);) {
            pre.setString(1, name.getText());
            pre.setString(2, address.getText());
            pre.setString(3, phone.getText());
            pre.setString(4, age.getText());
            pre.setString(5, (String) qualificaton.getSelectedItem());
            pre.setString(6, (String) specialization.getSelectedItem());
            pre.setString(7, salary.getText());
            pre.execute();
            table_teacher_update(table);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    public static void insert_subject(JTable table, JComboBox name, JTextField s1, JTextField s2, JTextField s3, JTextField s4, JTextField s5, JTextField s6, JTextField s7) {
        String Sql = "insert into Subject(class_name,s1,s2,s3,s4,s5,s6,s7) values(?,?,?,?,?,?,?,?)";
        try (Connection Co = connect();
                PreparedStatement pre = Co.prepareStatement(Sql);) {
            pre.setString(1, (String) name.getSelectedItem());
            pre.setString(2, s1.getText());
            pre.setString(3, s2.getText());
            pre.setString(4, s3.getText());
            pre.setString(5, s4.getText());
            pre.setString(6, s5.getText());
            pre.setString(7, s6.getText());
            pre.setString(8, s7.getText());

            pre.execute();
            table_subject_update(table);

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    //Update
    public static void update_std(JTable table, JTextField name, JTextField address, JTextField age, JComboBox clas, JComboBox subject, JTextField mark1, JTextField mark2, JTextField result, JTextField id) {
        String Sql = "update Student set name=?,address=?,age=?,class=?,subject=?,mark1=?,mark2=?,result=? where id=? ";
        try (Connection Co = connect();
                PreparedStatement pre = Co.prepareStatement(Sql);) {
            pre.setString(1, name.getText());
            pre.setString(2, address.getText());
            pre.setString(3, age.getText());
            pre.setString(5, (String) clas.getSelectedItem());
            pre.setString(4, (String) subject.getSelectedItem());
            pre.setString(6, mark1.getText());
            pre.setString(7, mark2.getText());
            pre.setString(8, result.getText());
            pre.setString(9, id.getText());
            pre.execute();
            table_std_update(table);

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    public static void update_techer(JTable table, JTextField name, JTextField address, JTextField phone, JTextField age, JComboBox special, JComboBox quali, JTextField salary, JTextField id) {
        String Sql = "update Teacher set name=?,address=?,phone=?,age=?,specialization=?,qualification=?,salary=? where id=? ";
        try (Connection Co = connect();
                PreparedStatement pre = Co.prepareStatement(Sql);) {
            pre.setString(1, name.getText());
            pre.setString(2, address.getText());
            pre.setString(3, phone.getText());
            pre.setString(4, age.getText());
            pre.setString(5, (String) special.getSelectedItem());
            pre.setString(6, (String) quali.getSelectedItem());
            pre.setString(7, salary.getText());
            pre.setString(8, id.getText());
            pre.execute();
            table_teacher_update(table);

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    public static void update_subject(JTable table, JComboBox name, JTextField s1, JTextField s2, JTextField s3, JTextField s4, JTextField s5, JTextField s6, JTextField s7, JTextField id) {
        String Sql = "update Subject set class_name=?,s1=?,s2=?,s3=?,s4=?,s5=?,s6=?,s7=? where id=? ";
        try (Connection Co = connect();
                PreparedStatement pre = Co.prepareStatement(Sql);) {
            pre.setString(1, (String)name.getSelectedItem());
            pre.setString(2, s1.getText());
            pre.setString(3, s2.getText());
            pre.setString(4, s3.getText());
            pre.setString(5, s4.getText());
            pre.setString(6, s5.getText());
            pre.setString(7, s6.getText());
            pre.setString(8, s7.getText());
            pre.setString(9, id.getText());
            pre.execute();
            table_subject_update(table);

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    //Search
    public static void search(JTextField search, JTable table) {
        table.setShowGrid(true);
        String sear = search.getText().toString();
        DefaultTableModel tab = (DefaultTableModel) table.getModel();
        TableRowSorter<DefaultTableModel> t = new TableRowSorter<DefaultTableModel>(tab);
        table.setRowSorter(t);
        t.setRowFilter(RowFilter.regexFilter(search.getText()));

    }

    //Delete
    public static void delete_std(JTable table, JTextField name) {
        String sql = "delete from Student where name=?";
        try (Connection con = connect(); PreparedStatement pre = con.prepareStatement(sql);) {
            pre.setString(1, name.getText());
            pre.execute();
            table_std_update(table);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    public static void delete_teacher(JTable table, JTextField name) {
        String sql = "delete from Teacher where name=?";
        try (Connection con = connect(); PreparedStatement pre = con.prepareStatement(sql);) {
            pre.setString(1, name.getText());
            pre.execute();
            table_teacher_update(table);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    public static void delete_subject(JTable table, JComboBox name) {
        String sql = "delete from Subject where Class_name=?";
        try (Connection con = connect(); PreparedStatement pre = con.prepareStatement(sql);) {
            pre.setString(1, (String) name.getSelectedItem());
            pre.execute();
            table_subject_update(table);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    //Mouse Click
    public static void get_std(JTable table, JTextField id, JTextField name, JTextField address, JTextField age, JComboBox clas, JComboBox subject, JTextField mark1, JTextField mark2, JTextField result) {
        String sql = "select * from Student where name=?";
        try (
                Connection con = Connect.connect();
                PreparedStatement pre = con.prepareStatement(sql);) {
            int row = table.getSelectedRow();
            String row_table = table.getModel().getValueAt(row, 0).toString();
            pre.setString(1, row_table);
            res = pre.executeQuery();
            while (res.next()) {
                name.setText(res.getString("name"));
                address.setText(res.getString("address"));
                age.setText(res.getString("age"));
                clas.setSelectedItem(res.getString("class"));
                subject.setSelectedItem(res.getString("subject"));
                mark1.setText(res.getString("mark1"));
                mark2.setText(res.getString("mark2"));
                result.setText(res.getString("result"));
                id.setText(res.getString("id"));

            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    public static void get_teacher(JTable table, JTextField name, JTextField address, JTextField phone, JTextField age, JComboBox qualificaton, JComboBox specialization, JTextField salary, JTextField id) {
        String sql = "select * from Teacher where name=?";
        try (
                Connection con = Connect.connect();
                PreparedStatement pre = con.prepareStatement(sql);) {
            int row = table.getSelectedRow();
            String row_table = table.getModel().getValueAt(row, 0).toString();
            pre.setString(1, row_table);
            res = pre.executeQuery();
            while (res.next()) {
                name.setText(res.getString("name"));
                address.setText(res.getString("address"));
                phone.setText(res.getString("phone"));
                age.setText(res.getString("age"));
                qualificaton.setSelectedItem(res.getString("qualification"));
                specialization.setSelectedItem(res.getString("specialization"));
                salary.setText(res.getString("salary"));
                id.setText(res.getString("id"));

            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    public static void get_subject(JTable table, JComboBox name, JTextField s1, JTextField s2, JTextField s3, JTextField s4, JTextField s5, JTextField s6, JTextField s7, JTextField id) {
        String sql = "select * from Subject where Class_name=?";
        try (
                Connection con = Connect.connect();
                PreparedStatement pre = con.prepareStatement(sql);) {
            int row = table.getSelectedRow();
            String row_table = table.getModel().getValueAt(row, 0).toString();
            pre.setString(1, row_table);
            res = pre.executeQuery();
            while (res.next()) {
                name.setSelectedItem(res.getString("class_name"));
                s1.setText(res.getString("s1"));
                s2.setText(res.getString("s2"));
                s3.setText(res.getString("s3"));
                s4.setText(res.getString("s4"));
                s5.setText(res.getString("s5"));
                s6.setText(res.getString("s6"));
                s7.setText(res.getString("s7"));
                id.setText(res.getString("id"));
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    //Table Update
    public static void table_std_update(JTable table) {
        table.setShowGrid(true);
        DefaultTableModel tab = (DefaultTableModel) table.getModel();
        tab.setRowCount(0);
        Std(table);
    }

    public static void table_teacher_update(JTable table) {
        table.setShowGrid(true);
        DefaultTableModel tab = (DefaultTableModel) table.getModel();
        tab.setRowCount(0);
        Teacher(table);
    }

    public static void table_subject_update(JTable table) {
        table.setShowGrid(true);
        DefaultTableModel tab = (DefaultTableModel) table.getModel();
        tab.setRowCount(0);
        Subject(table);
    }

    public static void std_std(JTextField name, JTextField address, JTextField age, JComboBox<String> clas, JComboBox<String> subject, JTextField mark1, JTextField mark2, JTextField result, int ptr) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
