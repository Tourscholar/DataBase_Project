package com.artisan.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Map;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.SwingConstants;
import javax.swing.border.TitledBorder;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.StandardChartTheme;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;

import com.artisan.dao.CourseDao;
import com.artisan.dao.ScoreDao;
import com.artisan.model.Course;
import com.artisan.model.Teacher;

public class StatsScoreFrm extends JInternalFrame {
    private JTextField maxScoreTextField;
    private JTextField minScoreTextField;
    private JTextField middScoreTextField;
    private JTextField studentNumTextField;
    private JComboBox courseComboBox;
    private JPanel viewPanel;
    private List<Course>courseList;
    private ChartPanel chartPanel ;
    private JPanel defaultPanel;
    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    StatsScoreFrm frame = new StatsScoreFrm();
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
    public StatsScoreFrm() {
        setClosable(true);
        setIconifiable(true);
        setTitle("?????? ?????? ???????????????");
        setBounds(100, 100, 765, 730);

        JLabel label = new JLabel("?????? ??????:");
        label.setIcon(new ImageIcon(StatsScoreFrm.class.getResource("/images/\u8BFE\u7A0B.png")));
        label.setFont(new Font("AppleMyungjo", Font.PLAIN, 14));

        courseComboBox = new JComboBox();

        JButton searchButton = new JButton("??????");
        searchButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                searchStatsAct(ae);
            }
        });
        searchButton.setIcon(new ImageIcon(StatsScoreFrm.class.getResource("/images/\u641C\u7D22.png")));
        searchButton.setFont(new Font("AppleMyungjo", Font.PLAIN, 14));

        viewPanel = new JPanel();
        viewPanel.setBorder(new TitledBorder(null, "??????????????????", TitledBorder.LEADING, TitledBorder.TOP, null, null));

        JPanel panel_1 = new JPanel();
        panel_1.setBorder(new TitledBorder(null, "?????? ?????? ?????????", TitledBorder.LEADING, TitledBorder.TOP, null, null));
        GroupLayout groupLayout = new GroupLayout(getContentPane());
        groupLayout.setHorizontalGroup(
                groupLayout.createParallelGroup(Alignment.LEADING)
                        .addGroup(groupLayout.createSequentialGroup()
                                .addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
                                        .addGroup(groupLayout.createSequentialGroup()
                                                .addGap(170)
                                                .addComponent(label)
                                                .addGap(18)
                                                .addComponent(courseComboBox, GroupLayout.PREFERRED_SIZE, 173, GroupLayout.PREFERRED_SIZE)
                                                .addGap(65)
                                                .addComponent(searchButton))
                                        .addGroup(groupLayout.createSequentialGroup()
                                                .addGap(79)
                                                .addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
                                                        .addComponent(viewPanel, GroupLayout.PREFERRED_SIZE, 585, GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(panel_1, 0, 0, Short.MAX_VALUE))))
                                .addContainerGap(85, Short.MAX_VALUE))
        );
        groupLayout.setVerticalGroup(
                groupLayout.createParallelGroup(Alignment.LEADING)
                        .addGroup(groupLayout.createSequentialGroup()
                                .addGap(53)
                                .addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
                                        .addComponent(label)
                                        .addComponent(courseComboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(searchButton))
                                .addGap(39)
                                .addComponent(viewPanel, GroupLayout.DEFAULT_SIZE, 465, Short.MAX_VALUE)
                                .addGap(18)
                                .addComponent(panel_1, GroupLayout.PREFERRED_SIZE, 65, GroupLayout.PREFERRED_SIZE)
                                .addGap(33))
        );

        JButton defaultViewButton = new JButton("?????? ??????");
        defaultViewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                defaultViewAct(ae);
            }
        });
        defaultViewButton.setIcon(new ImageIcon(StatsScoreFrm.class.getResource("/images/\u9ED8\u8BA4.png")));
        defaultViewButton.setFont(new Font("AppleMyungjo", Font.PLAIN, 14));

        JButton barViewButton = new JButton("??????????????? ??????");
        barViewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                barViewAct(ae);
            }
        });
        barViewButton.setIcon(new ImageIcon(StatsScoreFrm.class.getResource("/images/\u67F1\u72B6\u56FE.png")));
        barViewButton.setFont(new Font("AppleMyungjo", Font.PLAIN, 14));

        JButton pieViewButton = new JButton("????????? ??????");
        pieViewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                peiViewAct(ae);
            }
        });
        pieViewButton.setIcon(new ImageIcon(StatsScoreFrm.class.getResource("/images/\u997C\u72B6\u56FE.png")));
        pieViewButton.setFont(new Font("AppleMyungjo", Font.PLAIN, 14));
        GroupLayout gl_panel_1 = new GroupLayout(panel_1);
        gl_panel_1.setHorizontalGroup(
                gl_panel_1.createParallelGroup(Alignment.LEADING)
                        .addGroup(gl_panel_1.createSequentialGroup()
                                .addGap(62)
                                .addComponent(defaultViewButton)
                                .addGap(58)
                                .addComponent(barViewButton)
                                .addPreferredGap(ComponentPlacement.RELATED, 56, Short.MAX_VALUE)
                                .addComponent(pieViewButton)
                                .addGap(46))
        );
        gl_panel_1.setVerticalGroup(
                gl_panel_1.createParallelGroup(Alignment.TRAILING)
                        .addGroup(Alignment.LEADING, gl_panel_1.createSequentialGroup()
                                .addGroup(gl_panel_1.createParallelGroup(Alignment.BASELINE)
                                        .addComponent(barViewButton)
                                        .addComponent(defaultViewButton)
                                        .addComponent(pieViewButton))
                                .addContainerGap(14, Short.MAX_VALUE))
        );
        panel_1.setLayout(gl_panel_1);

        defaultPanel = new JPanel();
        GroupLayout gl_viewPanel = new GroupLayout(viewPanel);
        gl_viewPanel.setHorizontalGroup(
                gl_viewPanel.createParallelGroup(Alignment.LEADING)
                        .addGroup(gl_viewPanel.createSequentialGroup()
                                .addGap(26)
                                .addComponent(defaultPanel, GroupLayout.PREFERRED_SIZE, 526, GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(21, Short.MAX_VALUE))
        );
        gl_viewPanel.setVerticalGroup(
                gl_viewPanel.createParallelGroup(Alignment.LEADING)
                        .addGroup(gl_viewPanel.createSequentialGroup()
                                .addGap(36)
                                .addComponent(defaultPanel, GroupLayout.PREFERRED_SIZE, 367, GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(38, Short.MAX_VALUE))
        );

        JLabel label_1 = new JLabel("?????? ??????:");
        label_1.setIcon(new ImageIcon(StatsScoreFrm.class.getResource("/images/\u6700\u9AD8\u5206.png")));
        label_1.setFont(new Font("AppleMyungjo", Font.PLAIN, 14));

        maxScoreTextField = new JTextField();
        maxScoreTextField.setHorizontalAlignment(SwingConstants.CENTER);
        maxScoreTextField.setEditable(false);
        maxScoreTextField.setColumns(10);

        JLabel lblNewLabel = new JLabel("?????????:");
        lblNewLabel.setIcon(new ImageIcon(StatsScoreFrm.class.getResource("/images/\u6700\u4F4E\u5206.png")));
        lblNewLabel.setFont(new Font("AppleMyungjo", Font.PLAIN, 14));

        minScoreTextField = new JTextField();
        minScoreTextField.setHorizontalAlignment(SwingConstants.CENTER);
        minScoreTextField.setEditable(false);
        minScoreTextField.setColumns(10);

        JLabel label_2 = new JLabel("?????? ??????:");
        label_2.setIcon(new ImageIcon(StatsScoreFrm.class.getResource("/images/\u5E73\u5747.png")));
        label_2.setFont(new Font("AppleMyungjo", Font.PLAIN, 14));

        middScoreTextField = new JTextField();
        middScoreTextField.setHorizontalAlignment(SwingConstants.CENTER);
        middScoreTextField.setEditable(false);
        middScoreTextField.setColumns(10);

        JLabel label_3 = new JLabel("??? ?????????:");
        label_3.setIcon(new ImageIcon(StatsScoreFrm.class.getResource("/images/\u4EBA\u6570\u7EDF\u8BA1.png")));
        label_3.setFont(new Font("AppleMyungjo", Font.PLAIN, 14));

        studentNumTextField = new JTextField();
        studentNumTextField.setHorizontalAlignment(SwingConstants.CENTER);
        studentNumTextField.setEditable(false);
        studentNumTextField.setColumns(10);
        GroupLayout gl_defaultPanel = new GroupLayout(defaultPanel);
        gl_defaultPanel.setHorizontalGroup(
                gl_defaultPanel.createParallelGroup(Alignment.LEADING)
                        .addGroup(gl_defaultPanel.createSequentialGroup()
                                .addGap(57)
                                .addGroup(gl_defaultPanel.createParallelGroup(Alignment.LEADING)
                                        .addComponent(label_1)
                                        .addComponent(lblNewLabel)
                                        .addComponent(label_3)
                                        .addComponent(label_2))
                                .addGap(31)
                                .addGroup(gl_defaultPanel.createParallelGroup(Alignment.LEADING, false)
                                        .addComponent(middScoreTextField)
                                        .addComponent(maxScoreTextField, GroupLayout.DEFAULT_SIZE, 240, Short.MAX_VALUE)
                                        .addComponent(minScoreTextField)
                                        .addComponent(studentNumTextField))
                                .addContainerGap(112, Short.MAX_VALUE))
        );
        gl_defaultPanel.setVerticalGroup(
                gl_defaultPanel.createParallelGroup(Alignment.LEADING)
                        .addGroup(gl_defaultPanel.createSequentialGroup()
                                .addGap(25)
                                .addGroup(gl_defaultPanel.createParallelGroup(Alignment.BASELINE)
                                        .addComponent(label_1)
                                        .addComponent(maxScoreTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                .addGap(34)
                                .addGroup(gl_defaultPanel.createParallelGroup(Alignment.BASELINE)
                                        .addComponent(lblNewLabel)
                                        .addComponent(minScoreTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                .addGap(53)
                                .addGroup(gl_defaultPanel.createParallelGroup(Alignment.BASELINE)
                                        .addComponent(label_2)
                                        .addComponent(middScoreTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                .addGroup(gl_defaultPanel.createParallelGroup(Alignment.LEADING)
                                        .addGroup(gl_defaultPanel.createSequentialGroup()
                                                .addGap(50)
                                                .addComponent(label_3))
                                        .addGroup(gl_defaultPanel.createSequentialGroup()
                                                .addGap(40)
                                                .addComponent(studentNumTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
                                .addContainerGap(123, Short.MAX_VALUE))
        );
        defaultPanel.setLayout(gl_defaultPanel);
        viewPanel.setLayout(gl_viewPanel);
        getContentPane().setLayout(groupLayout);
        setCourseCombox();
    }
    protected void peiViewAct(ActionEvent ae) {
        // TODO Auto-generated method stub
        Course course = (Course)courseComboBox.getSelectedItem();
        ScoreDao scoreDao = new ScoreDao();
        Map<String, String> statsInfo = scoreDao.getStatsInfo(course.getId());
        clearPanel();
        drawCircle(Integer.parseInt(statsInfo.get("max_score")), Integer.parseInt(statsInfo.get("min_score")), Double.valueOf(statsInfo.get("mid_score")), course.getName());
    }

    protected void barViewAct(ActionEvent ae) {
        // TODO Auto-generated method stub
        Course course = (Course)courseComboBox.getSelectedItem();
        ScoreDao scoreDao = new ScoreDao();
        Map<String, String> statsInfo = scoreDao.getStatsInfo(course.getId());
        clearPanel();
        drawBar(Integer.parseInt(statsInfo.get("student_num")), Integer.parseInt(statsInfo.get("max_score")), Integer.parseInt(statsInfo.get("min_score")), Double.valueOf(statsInfo.get("mid_score")), course.getName());
    }

    protected void defaultViewAct(ActionEvent ae) {
        // TODO Auto-generated method stub
        clearPanel();
        Course course = (Course)courseComboBox.getSelectedItem();
        ScoreDao scoreDao = new ScoreDao();
        Map<String, String> statsInfo = scoreDao.getStatsInfo(course.getId());
        resetText();
        if(statsInfo.size() > 0){
            setDefaultPanel(statsInfo.get("student_num"),statsInfo.get("max_score"),statsInfo.get("min_score"),statsInfo.get("mid_score"));
        }
    }

    protected void searchStatsAct(ActionEvent ae) {
        // TODO Auto-generated method stub
        defaultViewAct(ae);
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
    private void resetText(){
        maxScoreTextField.setText("");
        minScoreTextField.setText("");
        middScoreTextField.setText("");
        studentNumTextField.setText("");
    }
    private void drawBar(int studentNum,int maxScore, int minScore,double midScore,String courseName){
        setLanuage();
        DefaultCategoryDataset dataSet = new DefaultCategoryDataset();//?????????????????????
        //dataSet.addValue(studentNum, courseName, "????????????");//????????????
        dataSet.addValue(maxScore, courseName+"(?????? ???:"+studentNum+")", "?????????");
        dataSet.addValue(minScore, courseName+"(?????? ???:"+studentNum+")", "?????????");
        dataSet.addValue(midScore, courseName+"(?????? ???:"+studentNum+")", "?????????");
        //????????????chart??????????????????????????????
        JFreeChart chart = ChartFactory.createBarChart3D("?????? ?????? ?????? ??????", "????????????", "?????? ??????", dataSet, PlotOrientation.VERTICAL, true, false, false);
        //??????????????????panel
        chartPanel= new ChartPanel(chart);
        //?????????panel?????????????????????panel???
        chartPanel.setPreferredSize(new Dimension(500,420));
        viewPanel.add(chartPanel,BorderLayout.CENTER);
        viewPanel.setLayout(new FlowLayout());
        viewPanel.updateUI();
        viewPanel.repaint();
    }
    protected void drawCircle(int maxScore, int minScore,double midScore,String courseName) {
        // TODO Auto-generated method stub
        setLanuage();
        DefaultPieDataset dataSet = new DefaultPieDataset();//???????????????
        dataSet.setValue("?????????",maxScore);//????????????
        dataSet.setValue("?????????",minScore);
        dataSet.setValue("?????????",midScore);
        JFreeChart chart = ChartFactory.createPieChart3D(courseName+"???????????? ?????? ?????? ??????", dataSet, true, true, false);
        chartPanel = new ChartPanel(chart);
        chartPanel.setPreferredSize(new Dimension(540,420));
        viewPanel.add(chartPanel,BorderLayout.CENTER);
        viewPanel.setLayout(new FlowLayout());
        viewPanel.updateUI();
        viewPanel.repaint();
    }
    private void setDefaultPanel(String studentNum,String maxScore, String minScore,String midScore){
        maxScoreTextField.setText(maxScore);
        minScoreTextField.setText(minScore);
        middScoreTextField.setText(midScore);
        studentNumTextField.setText(studentNum);
        //viewPanel.add(maxScoreTextField);
        //viewPanel.add(minScoreTextField);
        //viewPanel.add(middScoreTextField);
        //viewPanel.add(studentNumTextField);
        viewPanel.add(defaultPanel);
        viewPanel.updateUI();
        viewPanel.repaint();
    }
    private void clearPanel(){
        viewPanel.removeAll();
        viewPanel.updateUI();
        viewPanel.repaint();
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
}
