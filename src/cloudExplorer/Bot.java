/**
 * This program is free software: you can redistribute it and/or modify it under
 * the terms of the GNU General Public License as published by the Free Software
 * Foundation, either version 3 of the License, or (at your option) any later
 * version.
 *
 * This program is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU General Public License for more
 * details.
 *
 * You should have received a copy of the GNU General Public License along with
 * this program. If not, see <http://www.gnu.org/licenses/>.package
 * cloudExplorer
 *
 */
package cloudExplorer;

import static cloudExplorer.NewJFrame.jTextArea1;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class Bot implements Runnable {

    String Home = System.getProperty("user.home");
    NewJFrame mainFrame;
    Thread bot;
    String room = null;
    String server = null;
    String bucket = null;
    String nick = null;
    int port;
    Put put;
    String password = null;

    public static JTextArea ircarea;
    public static JButton irc_send_button;
    public static JButton irc_close_button;
    public static JTextField irc_input_text;
    public static JScrollPane irc_scrollpane;
    public static MyBot botobject;
    String what = null;
    String access_key = null;
    String endpoint = null;
    String ObjectKey = null;
    String secret_key = null;
    String temp_file = (Home + File.separator + "object.tmp");

    public Bot(String Aaccess_key, String Asecret_key, String Abucket, String Aendpoint) {
        access_key = Aaccess_key;
        secret_key = Asecret_key;
        bucket = Abucket;
        endpoint = Aendpoint;
    }

    void calibrateTextArea() {
        mainFrame.jTextArea1.append("\n");
        try {
            mainFrame.jTextArea1.setCaretPosition(jTextArea1.getLineStartOffset(jTextArea1.getLineCount() - 1));
        } catch (Exception e) {

        }
    }

    public static void sendText() {
        botobject.sendMessage(botobject.getRoom(), irc_input_text.getText());
        ircarea.append("\n" + botobject.getNick() + ": " + irc_input_text.getText());
        irc_input_text.setText("");
    }

    void buttons() {

    }

    public void run() {

        File checkBotConfig = new File(Home + File.separator + ".cloudExplorerIRC");
        if (checkBotConfig.exists()) {
            try {
                FileReader frr = new FileReader(Home + File.separator + ".cloudExplorerIRC");
                BufferedReader bfrr = new BufferedReader(frr);
                String read = null;
                while ((read = bfrr.readLine()) != null) {
                    String cut[] = read.split("=");
                    if (cut[0].contains("server")) {
                        server = (cut[1]);
                    }
                    if (cut[0].contains("channel")) {
                        room = (cut[1]);
                    }

                    if (cut[0].contains("port")) {
                        port = (Integer.parseInt(cut[1]));
                    }

                    if (cut[0].contains("password")) {
                        if (read.length() > 10) {
                            password = (cut[1]);
                        }
                    }
                    if (cut[0].contains("nick")) {
                        nick = (cut[1]);
                    }
                }
            } catch (IOException e) {
                mainFrame.jTextArea1.append("\nConfig file not found!. Please create .cloudExplorerIRC in your home directory by running the GUI.\n\nAfter a config is created, you can use the bot in CLI mode by adding \n\ngui=no \n\nto the configuration file.\n\n");
                calibrateTextArea();
            }

            load_gui_components();

        } else {
            mainFrame.jTextArea1.append("\nConfig file not found!. Please create .cloudExplorerIRC in your home directory by running the GUI.\n\nAfter a config is created, you can use the bot in CLI mode by adding \n\ngui=no \n\nto the configuration file.\n\n");
            calibrateTextArea();
        }

    }

    void load_gui_components() {
        mainFrame.jPanel11.removeAll();
        mainFrame.jPanel11.setLayout(new BoxLayout(mainFrame.jPanel11, BoxLayout.Y_AXIS));
        mainFrame.jPanel14.setLayout(new BoxLayout(mainFrame.jPanel14, BoxLayout.Y_AXIS));
        final JLabel blank = new JLabel(" ");
        ircarea = new JTextArea("\nConnecting to server......");
        irc_input_text = new JTextField("");
        irc_send_button = new JButton("Save to bucket");
        irc_close_button = new JButton("Close");
        irc_scrollpane = new JScrollPane(ircarea);

        irc_send_button.setBackground(Color.white);
        irc_send_button.setForeground(Color.blue);
        irc_send_button.setBorder(null);

        irc_close_button.setBackground(Color.white);
        irc_close_button.setForeground(Color.blue);
        irc_close_button.setBorder(null);

        ircarea.setMaximumSize(new Dimension(1300, 500));
        irc_send_button.setMaximumSize(new Dimension(150, 15));
        irc_close_button.setMaximumSize(new Dimension(150, 15));
        irc_input_text.setMaximumSize(new Dimension(1400, 20));
        irc_scrollpane.setMaximumSize(new Dimension(1500, 500));

        ircarea.setVisible(true);
        irc_input_text.setVisible(true);
        irc_send_button.setVisible(true);
        ircarea.setAutoscrolls(true);

        mainFrame.jPanel14.add(blank);
        mainFrame.jPanel11.add(irc_scrollpane);
        mainFrame.jPanel11.add(irc_input_text);
        mainFrame.jPanel14.add(irc_send_button);
        mainFrame.jPanel14.add(irc_close_button);

        mainFrame.jPanel11.repaint();
        mainFrame.jPanel11.revalidate();
        mainFrame.jPanel11.validate();

        mainFrame.jPanel14.repaint();
        mainFrame.jPanel14.revalidate();
        mainFrame.jPanel14.validate();

        irc_send_button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                save();
            }
        });

        irc_close_button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                botobject.disconnect();
                save();
                mainFrame.jPanel14.removeAll();
                mainFrame.jPanel14.repaint();
                mainFrame.jPanel14.revalidate();
                mainFrame.jPanel14.validate();
            }
        });

        irc_input_text.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                sendText();
            }
        });

        start_bot();
    }

    public void writer(String message, String Location) {
        try {
            FileWriter meeting = new FileWriter(Location);
            BufferedWriter bmeeting = new BufferedWriter(meeting);
            bmeeting.write("\n" + message);
            bmeeting.close();
        } catch (Exception writer) {
        }
    }

    String date() {
        Date date = new Date();
        return date.toString();
    }

    void save() {

        writer(ircarea.getText(), temp_file);
        put = new Put(temp_file, access_key, secret_key, bucket, endpoint, "IRC Transcript-" + date() + ".txt", false, false);
        put.startc(temp_file, access_key, secret_key, bucket, endpoint, "IRC Transcript-" + date() + ".txt", false, false);
    }

    void start_bot() {

        NewJFrame.jTextArea1.append("\nStarting IRC bot......");
        calibrateTextArea();

        botobject = new MyBot(nick);
        botobject.setVerbose(false);
        botobject.setServername(server);
        botobject.setRoom(room);

        try {
            if (password == null) {
                botobject.connect(server, port);
            } else {
                botobject.connect(server, port, password);
            }
            botobject.joinChannel(room);
            ircarea.append("\n\nJoined channel: " + room + ". \n\nCurrent Time is: " + date() + "\n\nType your message in the message box and then press enter to send the message.\n\n");

        } catch (Exception connect) {
            NewJFrame.jTextArea1.append("\n" + connect.getMessage());
            calibrateTextArea();
        }
    }

    void startc(String Aaccess_key, String Asecret_key, String Abucket, String Aendpoint) {
        bot = new Thread(new Bot(Aaccess_key, Asecret_key, Abucket, Aendpoint));
        bot.start();
    }

    void stop() {
        bot.stop();
        mainFrame.jTextArea1.setText("\nDownload completed or aborted.\n");

    }
}