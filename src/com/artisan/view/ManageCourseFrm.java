package com.artisan.view;

import java.awt.EventQueue;
import java.awt.Font;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import com.artisan.dao.CourseDao;
import com.artisan.dao.TeacherDao;
import com.artisan.model.Course;
import com.artisan.model.Teacher;
import com.artisan.util.StringUtil;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ManageCourseFrm extends JInternalFrame {
    private JTextField searchCourseNameTextField;
    private JTable courseListTable;
    private JTextField editCourseTextField;
    private JTextField editCourseStudentNumTextField;
    private JComboBox editCourseTeachComboBox;
    private JTextArea editCourseInfoTextArea;
    private List<Teacher> teacherList = new ArrayList<Teacher>();
    private JComboBox searchTeacherComboBox;
    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    ManageCourseFrm frame = new ManageCourseFrm();
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
    public ManageCourseFrm() {
        setTitle("커리큘럼 정보 관리");
        setBounds(100, 100, 819, 704);
        setClosable(true);
        setIconifiable(true);
        JLabel label = new JLabel("과정 이름:");
        label.setIcon(new ImageIcon(ManageCourseFrm.class.getResource("/images/\u8BFE\u7A0B.png")));
        label.setFont(new Font("AppleMyungjo", Font.PLAIN, 14));

        searchCourseNameTextField = new JTextField();
        searchCourseNameTextField.setColumns(10);

        JLabel label_1 = new JLabel("수업 선생님:");
        label_1.setIcon(new ImageIcon(ManageCourseFrm.class.getResource("/images/\u8001\u5E08.png")));
        label_1.setFont(new Font("AppleMyungjo", Font.PLAIN, 14));

        searchTeacherComboBox = new JComboBox();

        JButton searchButton = new JButton("검색");
        searchButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                searchCourse(ae);
            }
        });
        searchButton.setIcon(new ImageIcon(ManageCourseFrm.class.getResource("/images/\u641C\u7D22.png")));
        searchButton.setFont(new Font("AppleMyungjo", Font.PLAIN, 14));

        JScrollPane scrollPane = new JScrollPane();

        JPanel panel = new JPanel();
        panel.setBorder(new TitledBorder(null, "과정 정보 편집", TitledBorder.LEADING, TitledBorder.TOP, null, null));
        GroupLayout groupLayout = new GroupLayout(getContentPane());
        groupLayout.setHorizontalGroup(
                groupLayout.createParallelGroup(Alignment.LEADING)
                        .addGroup(groupLayout.createSequentialGroup()
                                .addGap(81)
                                .addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
                                        .addComponent(panel, Alignment.LEADING, 0, 0, Short.MAX_VALUE)
                                        .addGroup(Alignment.LEADING, groupLayout.createParallelGroup(Alignment.TRAILING, false)
                                                .addComponent(scrollPane, Alignment.LEADING)
                                                .addGroup(Alignment.LEADING, groupLayout.createSequentialGroup()
                                                        .addComponent(label)
                                                        .addGap(18)
                                                        .addComponent(searchCourseNameTextField, GroupLayout.PREFERRED_SIZE, 131, GroupLayout.PREFERRED_SIZE)
                                                        .addGap(32)
                                                        .addComponent(label_1)
                                                        .addPreferredGap(ComponentPlacement.UNRELATED)
                                                        .addComponent(searchTeacherComboBox, GroupLayout.PREFERRED_SIZE, 122, GroupLayout.PREFERRED_SIZE)
                                                        .addGap(50)
                                                        .addComponent(searchButton))))
                                .addContainerGap(116, Short.MAX_VALUE))
        );
        groupLayout.setVerticalGroup(
                groupLayout.createParallelGroup(Alignment.LEADING)
                        .addGroup(groupLayout.createSequentialGroup()
                                .addGap(38)
                                .addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
                                        .addComponent(label)
                                        .addComponent(searchCourseNameTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(label_1)
                                        .addComponent(searchTeacherComboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(searchButton))
                                .addGap(41)
                                .addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 266, GroupLayout.PREFERRED_SIZE)
                                .addGap(35)
                                .addComponent(panel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(31, Short.MAX_VALUE))
        );

        JLabel label_2 = new JLabel("과정 이름:");
        label_2.setIcon(new ImageIcon(ManageCourseFrm.class.getResource("/images/\u8BFE\u7A0B.png")));
        label_2.setFont(new Font("AppleMyungjo", Font.PLAIN, 14));

        editCourseTextField = new JTextField();
        editCourseTextField.setColumns(10);

        JLabel label_3 = new JLabel("수업 선생님:");
        label_3.setIcon(new ImageIcon(ManageCourseFrm.class.getResource("/images/\u8001\u5E08.png")));
        label_3.setFont(new Font("AppleMyungjo", Font.PLAIN, 14));

        editCourseTeachComboBox = new JComboBox();

        JLabel label_4 = new JLabel("학생 수:");
        label_4.setIcon(new ImageIcon(ManageCourseFrm.class.getResource("/images/\u4EBA\u6570.png")));
        label_4.setFont(new Font("AppleMyungjo", Font.PLAIN, 14));

        editCourseStudentNumTextField = new JTextField();
        editCourseStudentNumTextField.setColumns(10);

        JLabel label_5 = new JLabel("코스 소개:");
        label_5.setIcon(new ImageIcon(ManageCourseFrm.class.getResource("/images/\u4ECB\u7ECD.png")));
        label_5.setFont(new Font("AppleMyungjo", Font.PLAIN, 14));

        editCourseInfoTextArea = new JTextArea();

        JButton submitEditButton = new JButton("확인 수정");
        submitEditButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                editCourseSubmit(ae);
            }
        });
        submitEditButton.setIcon(new ImageIcon(ManageCourseFrm.class.getResource("/images/\u786E\u8BA4.png")));
        submitEditButton.setFont(new Font("AppleMyungjo", Font.PLAIN, 14));

        JButton deleteCourseButton = new JButton("과정 삭제");
        deleteCourseButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                deleteCourse(ae);
            }
        });
        deleteCourseButton.setIcon(new ImageIcon(ManageCourseFrm.class.getResource("/images/\u5220\u9664.png")));
        deleteCourseButton.setFont(new Font("AppleMyungjo", Font.PLAIN, 14));
        GroupLayout gl_panel = new GroupLayout(panel);
        gl_panel.setHorizontalGroup(
                gl_panel.createParallelGroup(Alignment.LEADING)
                        .addGroup(gl_panel.createSequentialGroup()
                                .addGap(37)
                                .addGroup(gl_panel.createParallelGroup(Alignment.LEADING, false)
                                        .addGroup(gl_panel.createSequentialGroup()
                                                .addComponent(label_4)
                                                .addPreferredGap(ComponentPlacement.RELATED)
                                                .addComponent(editCourseStudentNumTextField))
                                        .addGroup(gl_panel.createSequentialGroup()
                                                .addComponent(label_2)
                                                .addPreferredGap(ComponentPlacement.RELATED)
                                                .addComponent(editCourseTextField, GroupLayout.PREFERRED_SIZE, 112, GroupLayout.PREFERRED_SIZE)))
                                .addGap(18)
                                .addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
                                        .addGroup(gl_panel.createSequentialGroup()
                                                .addComponent(label_3)
                                                .addPreferredGap(ComponentPlacement.UNRELATED)
                                                .addComponent(editCourseTeachComboBox, GroupLayout.PREFERRED_SIZE, 153, GroupLayout.PREFERRED_SIZE))
                                        .addGroup(gl_panel.createSequentialGroup()
                                                .addComponent(label_5)
                                                .addGap(18)
                                                .addComponent(editCourseInfoTextArea)))
                                .addContainerGap(118, Short.MAX_VALUE))
                        .addGroup(gl_panel.createSequentialGroup()
                                .addGap(121)
                                .addComponent(submitEditButton)
                                .addGap(113)
                                .addComponent(deleteCourseButton)
                                .addContainerGap(160, Short.MAX_VALUE))
        );
        gl_panel.setVerticalGroup(
                gl_panel.createParallelGroup(Alignment.LEADING)
                        .addGroup(gl_panel.createSequentialGroup()
                                .addGap(27)
                                .addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
                                        .addComponent(label_2)
                                        .addComponent(editCourseTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(label_3)
                                        .addComponent(editCourseTeachComboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                .addGap(43)
                                .addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
                                        .addComponent(label_4)
                                        .addComponent(editCourseStudentNumTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(label_5)
                                        .addComponent(editCourseInfoTextArea, GroupLayout.PREFERRED_SIZE, 65, GroupLayout.PREFERRED_SIZE))
                                .addGap(18)
                                .addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
                                        .addComponent(submitEditButton)
                                        .addComponent(deleteCourseButton))
                                .addContainerGap(15, Short.MAX_VALUE))
        );
        panel.setLayout(gl_panel);

        courseListTable = new JTable();
        courseListTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent me) {
                selectedCourse(me);
            }
        });
        courseListTable.setModel(new DefaultTableModel(
                new Object[][] {
                },
                new String[] {
                        "과정번호", "과정명", "수업선생님", "과정최대인원", "선정된 인원", "과정안내"
                }
        ) {
            boolean[] columnEditables = new boolean[] {
                    false, true, false, false, false, false
            };
            public boolean isCellEditable(int row, int column) {
                return columnEditables[column];
            }
        });
        courseListTable.getColumnModel().getColumn(3).setPreferredWidth(90);
        courseListTable.getColumnModel().getColumn(5).setPreferredWidth(225);
        scrollPane.setViewportView(courseListTable);
        getContentPane().setLayout(groupLayout);
        setTeacherCombox();
        setCourseListTable(new Course());
    }
    protected void editCourseSubmit(ActionEvent ae) {
        // TODO Auto-generated method stub
        int row = courseListTable.getSelectedRow();
        if(row == -1){
            JOptionPane.showMessageDialog(this, "수정할 데이터를 선택하십시오!");
            return;
        }
        int course_id = Integer.parseInt(courseListTable.getValueAt(row, 0).toString());
        Teacher teacher = (Teacher) editCourseTeachComboBox.getSelectedItem();
        String courseName = editCourseTextField.getText().toString();
        if(StringUtil.isEmpty(courseName)){
            JOptionPane.showMessageDialog(this, "과정 이름은 비워둘 수 없습니다!");
            return;
        }
        int max_student_num = 0;
        try {
            max_student_num = Integer.parseInt(editCourseStudentNumTextField.getText().toString());
        } catch (Exception e) {
            // TODO: handle exception
            JOptionPane.showMessageDialog(this, "학생 수는 0보다 큰 정수를 입력하세요!");
            return;
        }
        if(max_student_num <= 0){
            JOptionPane.showMessageDialog(this, "학생 수는 0보다 큰 정수를 입력하세요!");
            return;
        }
        String courseInfo = editCourseInfoTextArea.getText().toString();
        Course course = new Course();
        course.setId(course_id);
        course.setName(courseName);
        course.setTeacher_id(teacher.getId());
        course.setMax_student_num(max_student_num);
        course.setInfo(courseInfo);
        CourseDao courseDao = new CourseDao();
        if(courseDao.update(course)){
            JOptionPane.showMessageDialog(this, "수정 성공!");
        }else{
            JOptionPane.showMessageDialog(this, "수정 실패!");
        }
        courseDao.closeDao();
        setCourseListTable(new Course());

    }

    protected void selectedCourse(MouseEvent me) {
        // TODO Auto-generated method stub
        int row = courseListTable.getSelectedRow();
        String couseName = courseListTable.getValueAt(row, 1).toString();
        int teacher_id = getTeacherIdByName(courseListTable.getValueAt(row, 2).toString());
        int max_student_num = Integer.parseInt(courseListTable.getValueAt(row, 3).toString());
        String couseInfo = courseListTable.getValueAt(row, 5).toString();
        editCourseTextField.setText(couseName);
        editCourseStudentNumTextField.setText(max_student_num+"");
        editCourseInfoTextArea.setText(couseInfo);
        for(int i=0;i<editCourseTeachComboBox.getItemCount();i++){
            Teacher t = (Teacher) editCourseTeachComboBox.getItemAt(i);
            if(t.getId() == teacher_id){
                editCourseTeachComboBox.setSelectedIndex(i);
                break;
            }
        }
    }

    protected void searchCourse(ActionEvent ae) {
        // TODO Auto-generated method stub
        String searchCourseName = searchCourseNameTextField.getText().toString();
        Teacher teacher = (Teacher) searchTeacherComboBox.getSelectedItem();
        Course course = new Course();
        course.setName(searchCourseName);
        course.setTeacher_id(teacher.getId());
        setCourseListTable(course);
    }

    protected void deleteCourse(ActionEvent ae) {
        // TODO Auto-generated method stub
        int row = courseListTable.getSelectedRow();
        if(row == -1){
            JOptionPane.showMessageDialog(this, "삭제할 데이터를 선택하십시오!");
            return;
        }
        int course_id = Integer.parseInt(courseListTable.getValueAt(row, 0).toString());
        CourseDao courseDao = new CourseDao();
        if(courseDao.delete(course_id)){
            JOptionPane.showMessageDialog(this, "삭제 성공!");
        }else{
            JOptionPane.showMessageDialog(this, "삭제 실패!");
        }
        courseDao.closeDao();
        setCourseListTable(new Course());
    }

    private void setCourseListTable(Course course){
        CourseDao courseDao = new CourseDao();
        List<Course> courseList = courseDao.getCourseList(course);
        DefaultTableModel dft = (DefaultTableModel) courseListTable.getModel();
        dft.setRowCount(0);
        for (Course c : courseList) {
            Vector v = new Vector();
            v.add(c.getId());
            v.add(c.getName());
            v.add(getTeacherNameById(c.getTeacher_id()));
            v.add(c.getMax_student_num());
            v.add(c.getSelected_num());
            v.add(c.getInfo());
            dft.addRow(v);
        }
        courseDao.closeDao();
    }
    private void setTeacherCombox(){
        TeacherDao teacherDao = new TeacherDao();
        teacherList = teacherDao.getTeacherList(new Teacher());
        teacherDao.closeDao();
        for (Teacher teacher : teacherList) {
            editCourseTeachComboBox.addItem(teacher);
            searchTeacherComboBox.addItem(teacher);
        }
    }
    private String getTeacherNameById(int teacher_id){
        String retString = "";
        for (Teacher teacher : teacherList) {
            if(teacher.getId() == teacher_id){
                retString = teacher.getName();
                break;
            }
        }
        return retString;
    }
    private int getTeacherIdByName(String teacher_name){
        int retId = -1;
        for (Teacher teacher : teacherList) {
            if(teacher_name.equals(teacher.getName())){
                retId = teacher.getId();
                break;
            }
        }
        return retId;
    }
}
