package ee.ut.math.tvt.Team_of_4;

import org.apache.log4j.Logger;

import javax.swing.*;
import java.awt.*;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class IntroUI {

    Properties applicationProp = new Properties();
    Properties versionProp = new Properties();

    private static final Logger logger = Logger.getLogger(Intro.class);

    public IntroUI() {
        try {
            applicationProp.load(new FileInputStream("application.properties"));
            versionProp.load(new FileInputStream("version.properties"));
        } catch (IOException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }

    }

    public void launch(){
        JFrame jFrame = new JFrame("Information");
        jFrame.setSize(500, 400);
        jFrame.setLocationRelativeTo(null);
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel jPanel = new JPanel();
        jPanel.setLayout(new BoxLayout(jPanel, BoxLayout.Y_AXIS));

        JPanel jPanel1 = new JPanel();
        Font font = new Font("Courier", Font.BOLD, 18);

        JLabel jLabel = new JLabel("Team Name: " + applicationProp.getProperty("Name"));
        jLabel.setFont(font);
        JLabel jLabel1 = new JLabel("Team Leader: " + applicationProp.getProperty("Team_leader"));
        JLabel jLabel2 = new JLabel("Team Leader Mail: " + applicationProp.getProperty("Team_leader_mail"));
        JLabel jLabel3 = new JLabel("Team Members: " + applicationProp.getProperty("Team_members"));
        ImageIcon logo = new ImageIcon("/Users/iljasmirnov/Desktop/TVT/team_of_4.png");
        logo = new ImageIcon(logo.getImage().getScaledInstance(150, 150, Image.SCALE_SMOOTH));

        JLabel jLabel4 = new JLabel(logo, JLabel.RIGHT);
        JLabel jLabel5 = new JLabel("Build Number: " + versionProp.getProperty("build.number"));


        jPanel.add(jLabel);
        jPanel.add(jLabel1);
        jPanel.add(jLabel2);
        jPanel.add(jLabel3);
        jPanel1.add(jLabel4);
        jPanel.add(jLabel5);
        jPanel.setBackground(Color.WHITE);



        jFrame.add(jPanel, BorderLayout.NORTH);
        jFrame.add(jPanel1, BorderLayout.CENTER);

        jFrame.setResizable(false);
        jFrame.setVisible(true);
        logger.info("System started");
    }
}
