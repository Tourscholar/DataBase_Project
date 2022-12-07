package com.artisan.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Desktop;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URI;

import javax.swing.ImageIcon;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.artisan.model.UserType;

public class MainFrm extends JFrame {

    private JPanel contentPane;
    private JDesktopPane desktopPane;
    public static UserType userType;
    public static Object userObject;
    private JMenuItem addStudentMenuItem ;
    private JMenu manageClassMenu ;
    private JMenu manageTeacherMenu;
    private JMenuItem addTeacherMenuItem;
    private JMenu courseMenu;
    private JMenuItem studentAttdentanceMenuItem;
    private JMenuItem manageAttendanceMenuItem;
    private JMenuItem statsAttendanceMenuItem;
    private JMenuItem addScoreMenuItem;
    private JMenuItem viewScoreMenuItem;
    private JMenuItem manageScoreMenuItem;
    private JMenuItem scoreStatsMenuItem;
    /**
     * Launch the application.
     */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					MainFrm frame = new MainFrm(null,null);
//					frame.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}

    /**
     * Create the frame.
     */
    public MainFrm(UserType userType,Object userObject) {
        this.userType = userType;
        this.userObject = userObject;
        setTitle("학생 정보 시스템 메인 인터페이스");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 936, 774);

        JMenuBar menuBar = new JMenuBar();
        setJMenuBar(menuBar);

        JMenu menu = new JMenu("시스템 설정");
        menu.setIcon(new ImageIcon(MainFrm.class.getResource("")));
        menuBar.add(menu);

        JMenuItem menuItem = new JMenuItem("비밀번호 변경");
        menuItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                editPassword(ae);
            }
        });
        menuItem.setIcon(new ImageIcon(MainFrm.class.getResource("")));
        menu.add(menuItem);

        JMenuItem menuItem_1 = new JMenuItem("시스템 종료");
        menuItem_1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if(JOptionPane.showConfirmDialog(MainFrm.this, "确定退出么？") == JOptionPane.OK_OPTION){
                    System.exit(0);
                }
            }
        });
        menuItem_1.setIcon(new ImageIcon(MainFrm.class.getResource("")));
        menu.add(menuItem_1);

        JMenu menu_1 = new JMenu("학생 관리");
        menu_1.setIcon(new ImageIcon(MainFrm.class.getResource("")));
        menuBar.add(menu_1);

        addStudentMenuItem = new JMenuItem("학생 추가");
        addStudentMenuItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                AddStudentFrm addStudentFrm = new AddStudentFrm();
                addStudentFrm.setVisible(true);
                desktopPane.add(addStudentFrm);
            }
        });
        addStudentMenuItem.setIcon(new ImageIcon(MainFrm.class.getResource("")));
        menu_1.add(addStudentMenuItem);

        JMenuItem menuItem_3 = new JMenuItem("학생 목록");
        menuItem_3.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ManageStudentFrm studentManageFrm = new ManageStudentFrm();
                studentManageFrm.setVisible(true);
                desktopPane.add(studentManageFrm);
            }
        });
        menuItem_3.setIcon(new ImageIcon(MainFrm.class.getResource("")));
        menu_1.add(menuItem_3);

        manageClassMenu = new JMenu("학급 관리");
        manageClassMenu.setIcon(new ImageIcon(MainFrm.class.getResource("")));
        menuBar.add(manageClassMenu);

        JMenuItem menuItem_4 = new JMenuItem("학급 추가");
        menuItem_4.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                addStudentClass(ae);
            }
        });
        menuItem_4.setIcon(new ImageIcon(MainFrm.class.getResource("")));
        manageClassMenu.add(menuItem_4);

        JMenuItem menuItem_5 = new JMenuItem("학급 관리");
        menuItem_5.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                ManageClassFrm classManageFrm = new ManageClassFrm();
                classManageFrm.setVisible(true);
                desktopPane.add(classManageFrm);
            }
        });
        menuItem_5.setIcon(new ImageIcon(MainFrm.class.getResource("")));
        manageClassMenu.add(menuItem_5);

        manageTeacherMenu = new JMenu("교사 관리");
        manageTeacherMenu.setIcon(new ImageIcon(MainFrm.class.getResource("")));
        menuBar.add(manageTeacherMenu);

        addTeacherMenuItem = new JMenuItem("교사 추가");
        addTeacherMenuItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                AddTeacherFrm addTeacherFrm = new AddTeacherFrm();
                addTeacherFrm.setVisible(true);
                desktopPane.add(addTeacherFrm);
            }
        });
        addTeacherMenuItem.setIcon(new ImageIcon(MainFrm.class.getResource("")));
        manageTeacherMenu.add(addTeacherMenuItem);

        JMenuItem menuItem_8 = new JMenuItem("교사 목록");
        menuItem_8.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ManageTeacherFrm manageTeacherFrm = new ManageTeacherFrm();
                manageTeacherFrm.setVisible(true);
                desktopPane.add(manageTeacherFrm);
            }
        });
        menuItem_8.setIcon(new ImageIcon(MainFrm.class.getResource("")));
        manageTeacherMenu.add(menuItem_8);

        courseMenu = new JMenu("커리큘럼 관리");
        courseMenu.setIcon(new ImageIcon(MainFrm.class.getResource("")));
        menuBar.add(courseMenu);

        JMenuItem addCourseMenuItem = new JMenuItem("커리큘럼 추가");
        addCourseMenuItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                AddCourseFrm addCourseFrm = new AddCourseFrm();
                addCourseFrm.setVisible(true);
                desktopPane.add(addCourseFrm);
            }
        });
        addCourseMenuItem.setIcon(new ImageIcon(MainFrm.class.getResource("")));
        courseMenu.add(addCourseMenuItem);

        JMenuItem courseListMenuItem = new JMenuItem("커리큘럼");
        courseListMenuItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                ManageCourseFrm manageCourseFrm = new ManageCourseFrm();
                manageCourseFrm.setVisible(true);
                desktopPane.add(manageCourseFrm);
            }
        });
        courseListMenuItem.setIcon(new ImageIcon(MainFrm.class.getResource("")));
        courseMenu.add(courseListMenuItem);

        JMenu menu_4 = new JMenu("수강신청관리");
        menu_4.setIcon(new ImageIcon(MainFrm.class.getResource("")));
        menuBar.add(menu_4);

        JMenuItem menuItem_2 = new JMenuItem("수강신청관리");
        menuItem_2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                ManageSelectedCourseFrm manageSelectedCourseFrm = new ManageSelectedCourseFrm();
                manageSelectedCourseFrm.setVisible(true);
                desktopPane.add(manageSelectedCourseFrm);
            }
        });
        menuItem_2.setIcon(new ImageIcon(MainFrm.class.getResource("")));
        menu_4.add(menuItem_2);

        JMenu menu_2 = new JMenu("출석 체크");
        menu_2.setIcon(new ImageIcon(MainFrm.class.getResource("")));
        menuBar.add(menu_2);

        studentAttdentanceMenuItem = new JMenuItem("학생 출석체크");
        studentAttdentanceMenuItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                AttendanceStudentFrm attendanceStudentFrm = new AttendanceStudentFrm();
                attendanceStudentFrm.setVisible(true);
                desktopPane.add(attendanceStudentFrm);
            }
        });
        studentAttdentanceMenuItem.setIcon(new ImageIcon(MainFrm.class.getResource("")));
        studentAttdentanceMenuItem.setEnabled(false);
        menu_2.add(studentAttdentanceMenuItem);

        manageAttendanceMenuItem = new JMenuItem("출석 관리");
        manageAttendanceMenuItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                ManageAttendanceFrm manageAttendanceFrm = new ManageAttendanceFrm();
                manageAttendanceFrm.setVisible(true);
                desktopPane.add(manageAttendanceFrm);
            }
        });
        manageAttendanceMenuItem.setIcon(new ImageIcon(MainFrm.class.getResource("")));
        menu_2.add(manageAttendanceMenuItem);

        statsAttendanceMenuItem = new JMenuItem("출석체크");
        statsAttendanceMenuItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                StatsAttendanceFrm statsAttendanceFrm = new StatsAttendanceFrm();
                statsAttendanceFrm.setVisible(true);
                desktopPane.add(statsAttendanceFrm);
            }
        });
        statsAttendanceMenuItem.setIcon(new ImageIcon(MainFrm.class.getResource("")));
        menu_2.add(statsAttendanceMenuItem);

        JMenu menu_5 = new JMenu("성적관리");
        menu_5.setIcon(new ImageIcon(MainFrm.class.getResource("")));
        menuBar.add(menu_5);

        addScoreMenuItem = new JMenuItem("성적을 입력");
        addScoreMenuItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                AddScoreFrm addScoreFrm = new AddScoreFrm();
                addScoreFrm.setVisible(true);
                desktopPane.add(addScoreFrm);
            }
        });
        addScoreMenuItem.setIcon(new ImageIcon(MainFrm.class.getResource("")));
        menu_5.add(addScoreMenuItem);

        viewScoreMenuItem = new JMenuItem("성적 보기");
        viewScoreMenuItem.setEnabled(false);
        viewScoreMenuItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                ViewScoreFrm viewScoreFrm = new ViewScoreFrm();
                viewScoreFrm.setVisible(true);
                desktopPane.add(viewScoreFrm);
            }
        });
        viewScoreMenuItem.setIcon(new ImageIcon(MainFrm.class.getResource("")));
        menu_5.add(viewScoreMenuItem);

        manageScoreMenuItem = new JMenuItem("성적관리");
        manageScoreMenuItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                ManageScoreFrm manageScoreFrm = new ManageScoreFrm();
                manageScoreFrm.setVisible(true);
                desktopPane.add(manageScoreFrm);
            }
        });
        manageScoreMenuItem.setIcon(new ImageIcon(MainFrm.class.getResource("")));
        menu_5.add(manageScoreMenuItem);

        scoreStatsMenuItem = new JMenuItem("성적통계");
        scoreStatsMenuItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                StatsScoreFrm statsScoreFrm = new StatsScoreFrm();
                statsScoreFrm.setVisible(true);
                desktopPane.add(statsScoreFrm);
            }
        });
        scoreStatsMenuItem.setIcon(new ImageIcon(MainFrm.class.getResource("")));
        menu_5.add(scoreStatsMenuItem);

        JMenu menu_3 = new JMenu("도움");
        menu_3.setIcon(new ImageIcon(MainFrm.class.getResource("")));
        menuBar.add(menu_3);

        JMenuItem menuItem_6 = new JMenuItem("우리에 대해서");
        menuItem_6.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
            }
        });
        menuItem_6.setIcon(new ImageIcon(MainFrm.class.getResource("")));
        menu_3.add(menuItem_6);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setLayout(new BorderLayout(0, 0));
        setContentPane(contentPane);

        desktopPane = new JDesktopPane();
        desktopPane.setBackground(new Color(0, 128, 128));
        contentPane.add(desktopPane, BorderLayout.CENTER);
        setLocationRelativeTo(null);
        setAuthority();
    }

    protected void addStudentClass(ActionEvent ae) {
        // TODO Auto-generated method stub
        AddStudentClassFrm sca = new AddStudentClassFrm();
        sca.setVisible(true);
        desktopPane.add(sca);
    }

    protected void editPassword(ActionEvent ae) {
        // TODO Auto-generated method stub
        EditPasswordFrm editPasswordFrm = new EditPasswordFrm();
        editPasswordFrm.setVisible(true);
        desktopPane.add(editPasswordFrm);
    }
    private void setAuthority(){
        if("학생".equals(userType.getName())){
            addStudentMenuItem.setEnabled(false);
            manageClassMenu.setEnabled(false);
            manageTeacherMenu.setEnabled(false);
            courseMenu.setEnabled(false);
            studentAttdentanceMenuItem.setEnabled(true);
            manageAttendanceMenuItem.setEnabled(false);
            statsAttendanceMenuItem.setEnabled(false);
            addScoreMenuItem.setEnabled(false);
            viewScoreMenuItem.setEnabled(true);
            manageScoreMenuItem.setEnabled(false);
            scoreStatsMenuItem.setEnabled(false);
        }
        if("선생님".equals(userType.getName())){
            addTeacherMenuItem.setEnabled(false);
        }
    }
}
