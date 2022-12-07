package com.artisan.view;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;

import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JComboBox;
import javax.swing.JTextArea;
import javax.swing.JButton;

import com.artisan.dao.CourseDao;
import com.artisan.dao.TeacherDao;
import com.artisan.model.Course;
import com.artisan.model.Teacher;
import com.artisan.util.StringUtil;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.List;

public class AddCourseFrm extends JInternalFrame {
    private JTextField courseNameTextField;
    private JTextField studentNumTextField;
    private JComboBox teacherListComboBox;
    private JTextArea courseInfoTextArea;
    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    AddCourseFrm frame = new AddCourseFrm();
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
    public AddCourseFrm() {
        setClosable(true);
        setIconifiable(true);
        setTitle("과정 추가");
        setBounds(100, 100, 453, 471);

        JLabel label = new JLabel("과정 이름:");
        label.setIcon(new ImageIcon(AddCourseFrm.class.getResource("")));
        label.setFont(new Font("AppleMyungjo", Font.PLAIN, 14));

        courseNameTextField = new JTextField();
        courseNameTextField.setColumns(10);

        JLabel label_1 = new JLabel("수업 선생님:");
        label_1.setIcon(new ImageIcon(AddCourseFrm.class.getResource("")));
        label_1.setFont(new Font("AppleMyungjo", Font.PLAIN, 14));

        teacherListComboBox = new JComboBox();

        JLabel label_2 = new JLabel("학생 수:");
        label_2.setIcon(new ImageIcon(AddCourseFrm.class.getResource("")));
        label_2.setFont(new Font("AppleMyungjo", Font.PLAIN, 14));

        studentNumTextField = new JTextField();
        studentNumTextField.setColumns(10);

        JLabel label_3 = new JLabel("코스 소개:");
        label_3.setIcon(new ImageIcon(AddCourseFrm.class.getResource("")));
        label_3.setFont(new Font("AppleMyungjo", Font.PLAIN, 14));

        courseInfoTextArea = new JTextArea();

        JButton addCourseButton = new JButton("추가 확인");
        addCourseButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                addCourseAct(ae);
            }
        });
        addCourseButton.setIcon(new ImageIcon(AddCourseFrm.class.getResource("")));
        addCourseButton.setFont(new Font("AppleMyungjo", Font.PLAIN, 14));

        JButton resetButton = new JButton("정보 재설정");
        resetButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                resetValue(ae);
            }
        });
        resetButton.setIcon(new ImageIcon(AddCourseFrm.class.getResource("")));
        resetButton.setFont(new Font("AppleMyungjo", Font.PLAIN, 14));
        GroupLayout groupLayout = new GroupLayout(getContentPane());
        groupLayout.setHorizontalGroup(
                groupLayout.createParallelGroup(Alignment.LEADING)
                        .addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
                                .addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
                                        .addGroup(groupLayout.createSequentialGroup()
                                                .addGap(88)
                                                .addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
                                                        .addGroup(groupLayout.createSequentialGroup()
                                                                .addComponent(label_2)
                                                                .addPreferredGap(ComponentPlacement.UNRELATED)
                                                                .addComponent(studentNumTextField, GroupLayout.DEFAULT_SIZE, 149, Short.MAX_VALUE))
                                                        .addGroup(groupLayout.createSequentialGroup()
                                                                .addComponent(label_1)
                                                                .addPreferredGap(ComponentPlacement.UNRELATED)
                                                                .addComponent(teacherListComboBox, 0, 149, Short.MAX_VALUE))
                                                        .addGroup(groupLayout.createSequentialGroup()
                                                                .addComponent(label)
                                                                .addPreferredGap(ComponentPlacement.UNRELATED)
                                                                .addComponent(courseNameTextField, GroupLayout.DEFAULT_SIZE, 155, Short.MAX_VALUE))
                                                        .addGroup(groupLayout.createSequentialGroup()
                                                                .addComponent(label_3)
                                                                .addPreferredGap(ComponentPlacement.UNRELATED)
                                                                .addComponent(courseInfoTextArea, GroupLayout.DEFAULT_SIZE, 149, Short.MAX_VALUE))))
                                        .addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
                                                .addGap(103)
                                                .addComponent(addCourseButton)
                                                .addGap(18)
                                                .addComponent(resetButton)))
                                .addGap(117))
        );
        groupLayout.setVerticalGroup(
                groupLayout.createParallelGroup(Alignment.LEADING)
                        .addGroup(groupLayout.createSequentialGroup()
                                .addGap(19)
                                .addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
                                        .addComponent(label)
                                        .addComponent(courseNameTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                .addGap(35)
                                .addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
                                        .addComponent(label_1)
                                        .addComponent(teacherListComboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                .addGap(37)
                                .addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
                                        .addComponent(label_2)
                                        .addComponent(studentNumTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                .addGap(38)
                                .addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
                                        .addComponent(label_3)
                                        .addComponent(courseInfoTextArea, GroupLayout.PREFERRED_SIZE, 120, GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(ComponentPlacement.RELATED, 46, Short.MAX_VALUE)
                                .addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
                                        .addComponent(addCourseButton)
                                        .addComponent(resetButton))
                                .addGap(57))
        );
        getContentPane().setLayout(groupLayout);
        setTeacherCombox();
    }

    protected void resetValue(ActionEvent ae) {
        // TODO Auto-generated method stub
        courseNameTextField.setText("");
        courseInfoTextArea.setText("");
        studentNumTextField.setText("");
        teacherListComboBox.setSelectedIndex(0);
    }

    protected void addCourseAct(ActionEvent ae) {
        // TODO Auto-generated method stub
        String couserName = courseNameTextField.getText().toString();
        String courseInfo = courseInfoTextArea.getText().toString();
        Teacher selectedTeacher = (Teacher)teacherListComboBox.getSelectedItem();
        int studentMaxNum = 0;
        try {
            studentMaxNum = Integer.parseInt(studentNumTextField.getText());
        } catch (Exception e) {
            // TODO: handle exception
            JOptionPane.showMessageDialog(this, "학생 수는 숫자만 입력할 수 있습니다!");
            return;
        }
        if(StringUtil.isEmpty(couserName)){
            JOptionPane.showMessageDialog(this, "과정명을 입력하세요!");
            return;
        }
        if(studentMaxNum <= 0){
            JOptionPane.showMessageDialog(this, "학생 수는 0보다 큰 숫자만 입력할 수 있습니다!");
            return;
        }
        Course course = new Course();
        course.setName(couserName);
        course.setMax_student_num(studentMaxNum);
        course.setInfo(courseInfo);
        course.setTeacher_id(selectedTeacher.getId());
        CourseDao courseDao = new CourseDao();
        if(courseDao.addCourse(course)){
            JOptionPane.showMessageDialog(this, "추가 성공!");
        }else{
            JOptionPane.showMessageDialog(this, "추가 실패했습니다!");
        }
        courseDao.closeDao();
        resetValue(ae);
    }
    private void setTeacherCombox(){
        TeacherDao teacherDao = new TeacherDao();
        List<Teacher> teacherList = teacherDao.getTeacherList(new Teacher());
        teacherDao.closeDao();
        for (Teacher teacher : teacherList) {
            teacherListComboBox.addItem(teacher);
        }
    }
}
