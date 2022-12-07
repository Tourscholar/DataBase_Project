package com.artisan.view;

import java.awt.EventQueue;
import java.awt.Font;
import java.util.List;
import java.util.Vector;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.table.DefaultTableModel;

import com.artisan.dao.ClassDao;
import com.artisan.dao.StudentDao;
import com.artisan.model.Student;
import com.artisan.model.StudentClass;
import com.artisan.util.StringUtil;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ManageStudentFrm extends JInternalFrame {
    private JTextField serachStudentNameTextField;
    private JTable studentListTable;
    private JTextField editStudentNameTextField;
    private JPasswordField editStudentPasswordPasswordField;
    private JComboBox searchStudentComboBox;
    private List<StudentClass> studentClassList ;
    private JComboBox editStudentClassComboBox;
    private ButtonGroup editSexButtonGroup;
    private JRadioButton editStudentSexManRadioButton;
    private JRadioButton editStudentSexFemalRadioButton;
    private JRadioButton editStudentSexUnkonwRadioButton;
    private JButton deleteStudentButton;
    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    ManageStudentFrm frame = new ManageStudentFrm();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Create the frame.
     */
    public ManageStudentFrm() {
        setClosable(true);
        setIconifiable(true);
        setTitle("학생 정보 관리");
        setBounds(100, 100, 842, 561);

        JScrollPane scrollPane = new JScrollPane();

        JLabel label = new JLabel("학생 이름:");
        label.setIcon(new ImageIcon(ManageStudentFrm.class.getResource("/images/\u5B66\u751F\u7BA1\u7406.png")));
        label.setFont(new Font("AppleMyungjo", Font.PLAIN, 14));

        serachStudentNameTextField = new JTextField();
        serachStudentNameTextField.setColumns(10);

        JLabel label_1 = new JLabel("소속 학급:");
        label_1.setIcon(new ImageIcon(ManageStudentFrm.class.getResource("/images/\u73ED\u7EA7\u540D\u79F0.png")));
        label_1.setFont(new Font("AppleMyungjo", Font.PLAIN, 14));

        searchStudentComboBox = new JComboBox();

        JButton searchButton = new JButton("검색:");
        searchButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                searchStudent(ae);
            }
        });
        searchButton.setIcon(new ImageIcon(ManageStudentFrm.class.getResource("/images/\u641C\u7D22.png")));
        searchButton.setFont(new Font("AppleMyungjo", Font.PLAIN, 14));

        JLabel label_2 = new JLabel("학생 이름:");
        label_2.setIcon(new ImageIcon(ManageStudentFrm.class.getResource("/images/\u5B66\u751F\u7BA1\u7406.png")));
        label_2.setFont(new Font("AppleMyungjo", Font.PLAIN, 14));

        editStudentNameTextField = new JTextField();
        editStudentNameTextField.setColumns(10);

        JLabel label_3 = new JLabel("소속 학급:");
        label_3.setIcon(new ImageIcon(ManageStudentFrm.class.getResource("/images/\u73ED\u7EA7\u540D\u79F0.png")));
        label_3.setFont(new Font("AppleMyungjo", Font.PLAIN, 14));

        editStudentClassComboBox = new JComboBox();

        JLabel label_4 = new JLabel("학생 성별:");
        label_4.setIcon(new ImageIcon(ManageStudentFrm.class.getResource("/images/\u6027\u522B.png")));
        label_4.setFont(new Font("AppleMyungjo", Font.PLAIN, 14));

        editSexButtonGroup = new ButtonGroup();
        editStudentSexManRadioButton = new JRadioButton("남자");
        editStudentSexManRadioButton.setSelected(true);

        editStudentSexFemalRadioButton = new JRadioButton("여자");

        editStudentSexUnkonwRadioButton = new JRadioButton("비밀을 지키");
        editSexButtonGroup.add(editStudentSexManRadioButton);
        editSexButtonGroup.add(editStudentSexFemalRadioButton);
        editSexButtonGroup.add(editStudentSexUnkonwRadioButton);

        JLabel label_5 = new JLabel("로그인 비밀번호:");
        label_5.setIcon(new ImageIcon(ManageStudentFrm.class.getResource("/images/password.png")));
        label_5.setFont(new Font("AppleMyungjo", Font.PLAIN, 14));

        editStudentPasswordPasswordField = new JPasswordField();

        JButton submitEditButton = new JButton("확인 수정");
        submitEditButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                submitEditAct(ae);
            }
        });
        submitEditButton.setIcon(new ImageIcon(ManageStudentFrm.class.getResource("/images/\u786E\u8BA4.png")));
        submitEditButton.setFont(new Font("AppleMyungjo", Font.PLAIN, 14));

        deleteStudentButton = new JButton("학생 삭제");
        deleteStudentButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                deleteStudent(ae);
            }
        });
        deleteStudentButton.setIcon(new ImageIcon(ManageStudentFrm.class.getResource("/images/\u5220\u9664.png")));
        deleteStudentButton.setFont(new Font("AppleMyungjo", Font.PLAIN, 14));
        GroupLayout groupLayout = new GroupLayout(getContentPane());
        groupLayout.setHorizontalGroup(
                groupLayout.createParallelGroup(Alignment.TRAILING)
                        .addGroup(groupLayout.createSequentialGroup()
                                .addGap(140)
                                .addComponent(label)
                                .addPreferredGap(ComponentPlacement.RELATED)
                                .addComponent(serachStudentNameTextField, GroupLayout.PREFERRED_SIZE, 122, GroupLayout.PREFERRED_SIZE)
                                .addGap(27)
                                .addComponent(label_1)
                                .addGap(18)
                                .addComponent(searchStudentComboBox, GroupLayout.PREFERRED_SIZE, 145, GroupLayout.PREFERRED_SIZE)
                                .addGap(40)
                                .addComponent(searchButton)
                                .addContainerGap(69, Short.MAX_VALUE))
                        .addGroup(groupLayout.createSequentialGroup()
                                .addContainerGap(65, Short.MAX_VALUE)
                                .addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
                                        .addComponent(label_3)
                                        .addComponent(label_2))
                                .addGap(18)
                                .addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
                                        .addComponent(editStudentClassComboBox, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(editStudentNameTextField, GroupLayout.DEFAULT_SIZE, 130, Short.MAX_VALUE))
                                .addGap(62)
                                .addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
                                        .addGroup(groupLayout.createSequentialGroup()
                                                .addPreferredGap(ComponentPlacement.RELATED)
                                                .addComponent(label_4)
                                                .addGap(18)
                                                .addComponent(editStudentSexManRadioButton)
                                                .addGap(2)
                                                .addComponent(editStudentSexFemalRadioButton)
                                                .addPreferredGap(ComponentPlacement.UNRELATED)
                                                .addComponent(editStudentSexUnkonwRadioButton))
                                        .addGroup(groupLayout.createSequentialGroup()
                                                .addComponent(label_5)
                                                .addGap(18)
                                                .addComponent(editStudentPasswordPasswordField)))
                                .addGap(30)
                                .addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
                                        .addComponent(submitEditButton)
                                        .addComponent(deleteStudentButton))
                                .addGap(33))
                        .addGroup(groupLayout.createSequentialGroup()
                                .addContainerGap(126, Short.MAX_VALUE)
                                .addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 577, GroupLayout.PREFERRED_SIZE)
                                .addGap(123))
        );
        groupLayout.setVerticalGroup(
                groupLayout.createParallelGroup(Alignment.LEADING)
                        .addGroup(groupLayout.createSequentialGroup()
                                .addGap(34)
                                .addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
                                        .addComponent(label)
                                        .addComponent(serachStudentNameTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(searchStudentComboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(label_1)
                                        .addComponent(searchButton))
                                .addGap(36)
                                .addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 307, GroupLayout.PREFERRED_SIZE)
                                .addGap(41)
                                .addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
                                        .addComponent(editStudentSexManRadioButton)
                                        .addComponent(editStudentSexFemalRadioButton)
                                        .addComponent(editStudentSexUnkonwRadioButton)
                                        .addComponent(submitEditButton)
                                        .addComponent(editStudentNameTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(label_4)
                                        .addComponent(label_2))
                                .addPreferredGap(ComponentPlacement.RELATED, 12, Short.MAX_VALUE)
                                .addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
                                        .addComponent(editStudentClassComboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(label_5)
                                        .addComponent(editStudentPasswordPasswordField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(deleteStudentButton)
                                        .addComponent(label_3))
                                .addGap(20))
        );

        studentListTable = new JTable();
        studentListTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent arg0) {
                selectedTableRow(arg0);
            }
        });
        studentListTable.setModel(new DefaultTableModel(
                new Object[][] {
                },
                new String[] {
                        "학생번호", "학생이름", "소속반", "학생성별", "로그인 비밀번호"
                }
        ) {
            boolean[] columnEditables = new boolean[] {
                    false, true, true, true, false
            };
            public boolean isCellEditable(int row, int column) {
                return columnEditables[column];
            }
        });
        scrollPane.setViewportView(studentListTable);
        getContentPane().setLayout(groupLayout);
        setStudentClassInfo();
        setTable(new Student());
        setAuthority();
    }
    protected void submitEditAct(ActionEvent ae) {
        // TODO Auto-generated method stub
        int row = studentListTable.getSelectedRow();
        if(row == -1){
            JOptionPane.showMessageDialog(this, "수정할 데이터를 선택하십시오!");
            return;
        }
        String studentName = editStudentNameTextField.getText().toString();
        String studentPassword = editStudentPasswordPasswordField.getText().toString();
        if(StringUtil.isEmpty(studentName)){
            JOptionPane.showMessageDialog(this, "학생 성함 작성해주세요!");
            return;
        }
        if(StringUtil.isEmpty(studentPassword)){
            JOptionPane.showMessageDialog(this, "비밀번호 입력해주세요!");
            return;
        }

        Student student = new Student();
        student.setName(studentName);
        student.setPassword(studentPassword);
        StudentClass sc = (StudentClass)editStudentClassComboBox.getSelectedItem();
        student.setClassId(sc.getId());
        student.setId(Integer.parseInt(studentListTable.getValueAt(row, 0).toString()));
        if(editStudentSexManRadioButton.isSelected())student.setSex(editStudentSexManRadioButton.getText().toString());
        if(editStudentSexFemalRadioButton.isSelected())student.setSex(editStudentSexFemalRadioButton.getText().toString());
        if(editStudentSexUnkonwRadioButton.isSelected())student.setSex(editStudentSexUnkonwRadioButton.getText().toString());
        StudentDao studentDao = new StudentDao();
        if(studentDao.update(student)){
            JOptionPane.showMessageDialog(this, "업데이트 성공!");
        }else{
            JOptionPane.showMessageDialog(this, "업데이트 실패!");
        }
        studentDao.closeDao();
        setTable(new Student());
    }

    protected void deleteStudent(ActionEvent ae) {
        // TODO Auto-generated method stub

        int row = studentListTable.getSelectedRow();
        if(row == -1){
            JOptionPane.showMessageDialog(this, "삭제할 데이터를 선택하십시오!");
            return;
        }
        if(JOptionPane.showConfirmDialog(this, "삭제하시겠습니까?") != JOptionPane.OK_OPTION){
            return;
        }
        StudentDao studentDao = new StudentDao();
        if(studentDao.delete(Integer.parseInt(studentListTable.getValueAt(row, 0).toString()))){
            JOptionPane.showMessageDialog(this, "삭제 성공!");
        }else{
            JOptionPane.showMessageDialog(this, "삭제 실패!");
        }
        studentDao.closeDao();
        setTable(new Student());
    }
    protected void selectedTableRow(MouseEvent me) {
        // TODO Auto-generated method stub
        DefaultTableModel dft = (DefaultTableModel) studentListTable.getModel();
        editStudentNameTextField.setText(dft.getValueAt(studentListTable.getSelectedRow(), 1).toString());
        editStudentPasswordPasswordField.setText(dft.getValueAt(studentListTable.getSelectedRow(), 4).toString());
        String className = dft.getValueAt(studentListTable.getSelectedRow(), 2).toString();
        for(int i=0;i<editStudentClassComboBox.getItemCount();i++){
            StudentClass sc = (StudentClass)editStudentClassComboBox.getItemAt(i);
            if(className.equals(sc.getName())){
                editStudentClassComboBox.setSelectedIndex(i);
            }
        }
        String sex = dft.getValueAt(studentListTable.getSelectedRow(), 3).toString();
        editSexButtonGroup.clearSelection();
        if(sex.equals(editStudentSexManRadioButton.getText()))editStudentSexManRadioButton.setSelected(true);
        if(sex.equals(editStudentSexFemalRadioButton.getText()))editStudentSexFemalRadioButton.setSelected(true);
        if(sex.equals(editStudentSexUnkonwRadioButton.getText()))editStudentSexUnkonwRadioButton.setSelected(true);
    }
    protected void searchStudent(ActionEvent ae) {
        // TODO Auto-generated method stub
        Student student = new Student();
        student.setName(serachStudentNameTextField.getText().toString());
        StudentClass sc = (StudentClass)searchStudentComboBox.getSelectedItem();
        student.setClassId(sc.getId());
        setTable(student);
    }

    private void setTable(Student student){
        if("학생".equals(MainFrm.userType.getName())){
            Student s = (Student)MainFrm.userObject;
            student.setName(s.getName());
        }
        DefaultTableModel dft = (DefaultTableModel) studentListTable.getModel();
        dft.setRowCount(0);
        StudentDao studentDao = new StudentDao();
        List<Student> studentList = studentDao.getStudentList(student);
        for (Student s : studentList) {
            Vector v = new Vector();
            v.add(s.getId());
            v.add(s.getName());
            v.add(getClassNameById(s.getClassId()));
            v.add(s.getSex());
            v.add(s.getPassword());
            dft.addRow(v);
        }
        studentDao.closeDao();
    }
    private void setStudentClassInfo(){
        ClassDao classDao = new ClassDao();
        studentClassList = classDao.getClassList(new StudentClass());
        for (StudentClass sc : studentClassList) {
            searchStudentComboBox.addItem(sc);
            editStudentClassComboBox.addItem(sc);
        }
        classDao.closeDao();
    }
    private String getClassNameById(int id){
        for (StudentClass sc : studentClassList) {
            if(sc.getId() == id)return sc.getName();
        }
        return "";
    }
    private void setAuthority(){
        if("학생".equals(MainFrm.userType.getName())){
            Student s = (Student)MainFrm.userObject;
            serachStudentNameTextField.setText(s.getName());
            serachStudentNameTextField.setEnabled(false);
            deleteStudentButton.setEnabled(false);
            for(int i=0;i<searchStudentComboBox.getItemCount();i++){
                StudentClass sc = (StudentClass) searchStudentComboBox.getItemAt(i);
                if(sc.getId() == s.getClassId()){
                    searchStudentComboBox.setSelectedIndex(i);
                    break;
                }
            }
            searchStudentComboBox.setEnabled(false);
            for(int i=0;i<editStudentClassComboBox.getItemCount();i++){
                StudentClass sc = (StudentClass) editStudentClassComboBox.getItemAt(i);
                if(sc.getId() == s.getClassId()){
                    editStudentClassComboBox.setSelectedIndex(i);
                    break;
                }
            }
            editStudentClassComboBox.setEnabled(false);
        }
    }
}
