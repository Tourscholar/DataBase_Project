package com.artisan.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;
import java.util.Set;
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
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.StandardChartTheme;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;

import com.artisan.dao.AttendanceDao;
import com.artisan.dao.CourseDao;
import com.artisan.model.Course;
import com.artisan.model.Teacher;
import com.artisan.util.Chooser;
import com.artisan.util.StringUtil;

public class StatsAttendanceFrm extends JInternalFrame {
    private JTextField dateTextField;
    private JTable statsListTable;
    private List<Course> courseList = new ArrayList<Course>();
    private JComboBox courseComboBox;
    private JScrollPane statsListScrollPane;
    private JPanel statsListPanel;
    private ChartPanel chartPanel ;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    StatsAttendanceFrm frame = new StatsAttendanceFrm();
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
    public StatsAttendanceFrm() {
        setClosable(true);
        setIconifiable(true);
        setTitle("???????????? ??????");
        setBounds(100, 100, 701, 696);

        JLabel label = new JLabel("????????????:");
        label.setIcon(new ImageIcon(StatsAttendanceFrm.class.getResource("/images/\u8BFE\u7A0B.png")));
        label.setFont(new Font("AppleMyungjo", Font.PLAIN, 14));

        courseComboBox = new JComboBox();
        courseComboBox.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent ie) {
                courseChangedAct(ie);
            }
        });

        JLabel label_1 = new JLabel("??????:");
        label_1.setIcon(new ImageIcon(StatsAttendanceFrm.class.getResource("/images/\u65E5\u671F.png")));
        label_1.setFont(new Font("AppleMyungjo", Font.PLAIN, 14));

        dateTextField = new JTextField();
        dateTextField.setColumns(10);

        JButton searchButton = new JButton("??????");
        searchButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                searchAttendanceAct(ae);
            }
        });
        searchButton.setIcon(new ImageIcon(StatsAttendanceFrm.class.getResource("/images/\u641C\u7D22.png")));
        searchButton.setFont(new Font("AppleMyungjo", Font.PLAIN, 14));

        statsListPanel = new JPanel();
        statsListPanel.setBorder(new TitledBorder(null, "???????????? ??????", TitledBorder.LEADING, TitledBorder.TOP, null, null));

        JPanel panel_1 = new JPanel();
        panel_1.setBorder(new TitledBorder(null, "?????? ?????? ?????????", TitledBorder.LEADING, TitledBorder.TOP, null, null));
        GroupLayout groupLayout = new GroupLayout(getContentPane());
        groupLayout.setHorizontalGroup(
                groupLayout.createParallelGroup(Alignment.LEADING)
                        .addGroup(groupLayout.createSequentialGroup()
                                .addGap(60)
                                .addGroup(groupLayout.createParallelGroup(Alignment.TRAILING, false)
                                        .addComponent(panel_1, Alignment.LEADING, 0, 0, Short.MAX_VALUE)
                                        .addComponent(statsListPanel, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addGroup(Alignment.LEADING, groupLayout.createSequentialGroup()
                                                .addComponent(label)
                                                .addGap(18)
                                                .addComponent(courseComboBox, GroupLayout.PREFERRED_SIZE, 135, GroupLayout.PREFERRED_SIZE)
                                                .addGap(27)
                                                .addComponent(label_1)
                                                .addPreferredGap(ComponentPlacement.RELATED)
                                                .addComponent(dateTextField, GroupLayout.PREFERRED_SIZE, 150, GroupLayout.PREFERRED_SIZE)
                                                .addGap(27)
                                                .addComponent(searchButton)))
                                .addContainerGap(89, Short.MAX_VALUE))
        );
        groupLayout.setVerticalGroup(
                groupLayout.createParallelGroup(Alignment.LEADING)
                        .addGroup(groupLayout.createSequentialGroup()
                                .addGap(36)
                                .addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
                                        .addComponent(label)
                                        .addComponent(courseComboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(label_1)
                                        .addComponent(dateTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(searchButton))
                                .addGap(46)
                                .addComponent(statsListPanel, GroupLayout.PREFERRED_SIZE, 455, GroupLayout.PREFERRED_SIZE)
                                .addGap(18)
                                .addComponent(panel_1, GroupLayout.PREFERRED_SIZE, 47, Short.MAX_VALUE)
                                .addContainerGap())
        );

        JButton button = new JButton("?????? ??????");
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                //????????????
                clearPanel();
                statsListPanel.setLayout(new BorderLayout());
                statsListPanel.add(statsListScrollPane);
            }
        });
        button.setIcon(new ImageIcon(StatsAttendanceFrm.class.getResource("/images/\u5217\u8868.png")));

        JButton button_1 = new JButton("??????????????? ??????");
        button_1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                String dateString = dateTextField.getText().toString();
                if(StringUtil.isEmpty(dateString)){
                    JOptionPane.showMessageDialog(StatsAttendanceFrm.this, "?????? ??????!");
                    return;
                }
                //???????????????
                clearPanel();
                Course course = (Course)courseComboBox.getSelectedItem();
                drawBar(getAttendanceNum(course, dateString),course.getSelected_num(),dateString);
            }
        });
        button_1.setIcon(new ImageIcon(StatsAttendanceFrm.class.getResource("/images/\u67F1\u72B6\u56FE.png")));

        JButton button_2 = new JButton("????????? ??????");
        button_2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                //???????????????
                String dateString = dateTextField.getText().toString();
                if(StringUtil.isEmpty(dateString)){
                    JOptionPane.showMessageDialog(StatsAttendanceFrm.this, "?????? ??????!");
                    return;
                }
                //???????????????
                clearPanel();
                Course course = (Course)courseComboBox.getSelectedItem();
                drawCircle(getAttendanceNum(course, dateString),course.getSelected_num(),dateString);
            }
        });
        button_2.setIcon(new ImageIcon(StatsAttendanceFrm.class.getResource("/images/\u997C\u72B6\u56FE.png")));
        GroupLayout gl_panel_1 = new GroupLayout(panel_1);
        gl_panel_1.setHorizontalGroup(
                gl_panel_1.createParallelGroup(Alignment.LEADING)
                        .addGroup(gl_panel_1.createSequentialGroup()
                                .addGap(53)
                                .addComponent(button)
                                .addGap(47)
                                .addComponent(button_1)
                                .addGap(60)
                                .addComponent(button_2)
                                .addGap(67))
        );
        gl_panel_1.setVerticalGroup(
                gl_panel_1.createParallelGroup(Alignment.TRAILING)
                        .addGroup(Alignment.LEADING, gl_panel_1.createSequentialGroup()
                                .addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE)
                                        .addComponent(button)
                                        .addComponent(button_1)
                                        .addComponent(button_2))
                                .addContainerGap(25, Short.MAX_VALUE))
        );
        panel_1.setLayout(gl_panel_1);

        statsListScrollPane = new JScrollPane();
        GroupLayout gl_statsListPanel = new GroupLayout(statsListPanel);
        gl_statsListPanel.setHorizontalGroup(
                gl_statsListPanel.createParallelGroup(Alignment.LEADING)
                        .addComponent(statsListScrollPane, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 554, Short.MAX_VALUE)
        );
        gl_statsListPanel.setVerticalGroup(
                gl_statsListPanel.createParallelGroup(Alignment.LEADING)
                        .addGroup(gl_statsListPanel.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(statsListScrollPane, GroupLayout.DEFAULT_SIZE, 411, Short.MAX_VALUE)
                                .addContainerGap())
        );

        statsListTable = new JTable();
        statsListTable.setModel(new DefaultTableModel(
                new Object[][] {
                },
                new String[] {
                        "\u8BFE\u7A0B\u540D\u79F0", "\u7B7E\u5230\u4EBA\u6570", "\u7F3A\u5E2D\u4EBA\u6570", "\u9009\u8BFE\u4EBA\u6570", "\u65E5\u671F"
                }
        ) {
            boolean[] columnEditables = new boolean[] {
                    false, false, false, false, false
            };
            public boolean isCellEditable(int row, int column) {
                return columnEditables[column];
            }
        });
        statsListScrollPane.setViewportView(statsListTable);
        statsListPanel.setLayout(gl_statsListPanel);
        getContentPane().setLayout(groupLayout);
        Chooser.getInstance().register(dateTextField);
        setCourseCombox();
        setTable();
    }
    protected void drawCircle(int attendanceNum, int selected_num,
                              String dateString) {
        // TODO Auto-generated method stub
        setLanuage();
        DefaultPieDataset dataSet = new DefaultPieDataset();//???????????????
        dataSet.setValue("??????",attendanceNum);//????????????
        dataSet.setValue("?????? ??????",selected_num-attendanceNum);
        dataSet.setValue("?????????",selected_num);
        JFreeChart chart = ChartFactory.createPieChart3D("?????? ???????????? ??????", dataSet, true, true, false);
        chartPanel = new ChartPanel(chart);
        chartPanel.setPreferredSize(new Dimension(540,420));
        statsListPanel.add(chartPanel,BorderLayout.CENTER);
        statsListPanel.setLayout(new FlowLayout());
        statsListPanel.updateUI();
        statsListPanel.repaint();
    }

    protected void courseChangedAct(ItemEvent ie) {
        // TODO Auto-generated method stub
        if(ie.getStateChange() == ItemEvent.SELECTED){
            setTable();
        }
    }

    private void searchAttendanceAct(ActionEvent ae){
        setTable();
    }
    private void setCourseCombox(){
        CourseDao courseDao = new CourseDao();
        courseList = courseDao.getCourseList(new Course());
        courseDao.closeDao();
        for (Course course : courseList) {
            if("?????????".equals(MainFrm.userType.getName())){
                Teacher teacher = (Teacher)MainFrm.userObject;
                if(course.getTeacher_id() == teacher.getId()){
                    courseComboBox.addItem(course);
                }
                continue;
            }
            //?????????????????????????????????????????????
            courseComboBox.addItem(course);
        }

    }
    private void setTable(){
        Course course = (Course)courseComboBox.getSelectedItem();
        String dateString = dateTextField.getText().toString();
        AttendanceDao attendanceDao = new AttendanceDao();
        List<HashMap<Integer, String>> attendanceStatsList = attendanceDao.getAttendanceStatsList(course.getId(), dateString);
        DefaultTableModel dft = (DefaultTableModel) statsListTable.getModel();
        dft.setRowCount(0);
        for(HashMap<Integer, String> attendanceStats : attendanceStatsList){
            Set<Entry<Integer, String>> entrySet = attendanceStats.entrySet();
            int attendanceNum = 0 ;
            String dateString2 = "";
            for(Entry<Integer, String> entry : entrySet){
                attendanceNum = entry.getKey();
                dateString2 = entry.getValue();
            }
            Vector v = new Vector();
            v.add(course.getName());
            v.add(attendanceNum);
            v.add(course.getSelected_num()-attendanceNum);
            v.add(course.getSelected_num());
            v.add(dateString2);
            dft.addRow(v);
        }
    }
    private void drawBar(int attendanceNum,int studentNum, String date){
        setLanuage();
        DefaultCategoryDataset dataSet = new DefaultCategoryDataset();//?????????????????????
        dataSet.addValue(attendanceNum, date, "?????? ?????????");//????????????
        dataSet.addValue(studentNum-attendanceNum, date, "?????? ??????");
        dataSet.addValue(studentNum, date, "?????????");
        //????????????chart??????????????????????????????
        JFreeChart chart = ChartFactory.createBarChart3D("?????? ?????? ?????? ??????", "?????? ??????", "??????", dataSet, PlotOrientation.VERTICAL, true, false, false);
        //??????????????????panel
        chartPanel= new ChartPanel(chart);
        //?????????panel?????????????????????panel???
        chartPanel.setPreferredSize(new Dimension(500,420));
        statsListPanel.add(chartPanel,BorderLayout.CENTER);
        statsListPanel.setLayout(new FlowLayout());
        statsListPanel.updateUI();
        statsListPanel.repaint();
    }
    private void clearPanel(){
        statsListPanel.removeAll();
        statsListPanel.updateUI();
        statsListPanel.repaint();
    }
    private void setLanuage(){
        //??????????????????
        StandardChartTheme standardChartTheme=new StandardChartTheme("CN");
        //??????????????????
        standardChartTheme.setExtraLargeFont(new Font("AppleMyungjo",Font.BOLD,20));
        //?????????????????????
        standardChartTheme.setRegularFont(new Font("AppleMyungjo",Font.PLAIN,15));
        //?????????????????????
        standardChartTheme.setLargeFont(new Font("AppleMyungjo",Font.PLAIN,15));
        //??????????????????
        ChartFactory.setChartTheme(standardChartTheme);
    }
    private int getAttendanceNum(Course course,String dateString){
        AttendanceDao attendanceDao = new AttendanceDao();
        List<HashMap<Integer, String>> attendanceStatsList = attendanceDao.getAttendanceStatsList(course.getId(), dateString);
        int attendanceNum = 0 ;
        for(HashMap<Integer, String> attendanceStats : attendanceStatsList){
            Set<Entry<Integer, String>> entrySet = attendanceStats.entrySet();
            for(Entry<Integer, String> entry : entrySet){
                attendanceNum = entry.getKey();
            }
        }
        return attendanceNum;
    }
}
