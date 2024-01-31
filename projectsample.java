import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.util.Calendar;
import org.jdesktop.swingx.JXDatePicker;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.table.*;
import javax.swing.ImageIcon;
import java.awt.BorderLayout;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.awt.Image;
import java.awt.FlowLayout;
import java.awt.Dimension;
import java.sql.*;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.*;
import java.util.Random;
import java.util.List;
import java.util.ArrayList;
import java.awt.Toolkit;
import java.text.DecimalFormat;
import java.util.concurrent.atomic.AtomicBoolean;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.table.AbstractTableModel;
import java.text.ParseException;
import java.awt.Font;
import java.awt.GraphicsEnvironment;
import javax.swing.table.JTableHeader;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellRenderer;
import org.krysalis.barcode4j.impl.code128.Code128Bean;
import org.krysalis.barcode4j.HumanReadablePlacement;
import org.krysalis.barcode4j.output.bitmap.BitmapCanvasProvider;
import org.krysalis.barcode4j.tools.UnitConv;
import java.util.Set;
import java.util.LinkedHashSet;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Collections;
import java.lang.Math;
import java.util.Arrays;

public class projectsample {
	public static String fname, mname, lname, contact, email;
    public static JCheckBox A1,A2,A3,A4,A5;
    public static JCheckBox B1,B2,B3,B4,B5;
    public static JCheckBox C1,C2,C3,C4,C5;
    public static JCheckBox D1,D2,D3,D4,D5;
    public static int limit;
	public static double f4_bundle, f4_depret_stotal, f4_adminfee, f4_fuel, f4_psc, f4_phvat, f4_taxstotal, f4_rtrip_total, f4_oneway_total;
	public static String bundle_choice;
	public static double depart_price, return_price;
	public static int db_flightid, db_passenger, db_amount, db_transid;
	public static String db_timestamp, db_refno, db_customername, db_depflightno, db_retflightno, db_departplace, db_arriveplace, db_paymentmethod, db_bundle, db_departdate, db_returndate;
	public static String db_departtime, db_returntime;
	public static String db_depseats, db_retseats;
	public static java.sql.Date db_bookdate;
	public static Font calsans, underrated;
	public static String receipt_refno;
	public static String depart_time, return_time, departplace, arriveplace;
	public static String[]guestname;
	public static Set<String> selected_depart, selected_return;
	public static ArrayList<String> departureSeats, returnSeats;
	public static double[] coordinates1;
	public static double[] coordinates2;
	public static double distance;
	public static String airport1, airport2;
	public static JFrame frame, frame2, frame_seats, guestFrame, frame4summary, frame5, frame6;
	public static JLabel bcode_refno;
	public static ArrayList<String> taken_seats;
	public static ArrayList<String> taken_retseats;
	public static boolean bundle3optionpane = false;
	public static ImageIcon seatTaken;
	public static boolean btn_seatClicked = false;
	//public static JFrame f1, f2, f3, f4, f5, f6;
	public static void main(String[]args){
        String url = "jdbc:mysql://localhost:3306/lakbayairlines?serverTimezone=UTC";
        String username = "root";
        String password = "";

    	try{

	        // Create a Font instance from the font file
	        calsans = Font.createFont(Font.TRUETYPE_FONT, new File("C:\\Users\\romxcob\\Documents\\JCreator LE\\MyProjects\\projectsample\\fonts\\CalSans-SemiBold.otf"));
			underrated = Font.createFont(Font.TRUETYPE_FONT, new File("C:\\Users\\romxcob\\Documents\\JCreator LE\\MyProjects\\projectsample\\fonts\\UNDERRATED-UltraBold Personal Use.otf"));

	        // Set the font size
	        calsans = calsans.deriveFont(25f);
	        underrated = underrated.deriveFont(25f);

	        // Register the font with the Graphics Environment
	        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
	        ge.registerFont(calsans);
	        ge.registerFont(underrated);

        } catch (Exception e) {
            // Handle exception
            e.printStackTrace();
        }

	//PROCESSING (LOADING) SCREEN
		JFrame load_frame = new JFrame("Processing");
        load_frame.setSize(450,250);
        load_frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        load_frame.setLayout(null);
        load_frame.setLocationRelativeTo(null);
        load_frame.setVisible(false);
        load_frame.setUndecorated(true);

        JLabel home_bgimage = new JLabel() {
            @Override
            public void paintComponent(Graphics g) {
                super.paintComponent(g);
                try {
                    Image home_backgroundImage = ImageIO.read(new File("C:\\Users\\romxcob\\Documents\\JCreator LE\\MyProjects\\projectsample\\photos\\processing.png"));
                    g.drawImage(home_backgroundImage, 0, 0, getWidth(), getHeight(), this);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };
        load_frame.setResizable(false);
        load_frame.setContentPane(home_bgimage);

        // Set a timer to close the JFrame after 3 seconds
        new Timer(0000, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                load_frame.dispose();
            }
        }).start();

	//HOME PAGE

		ImageIcon bgImage = new ImageIcon("C:\\Users\\romxcob\\Documents\\JCreator LE\\MyProjects\\projectsample\\photos\\photo.jpg");
		JLabel bgLabel = new JLabel();

        JFrame homeFrame = new JFrame("Home");
        RoundedButton homebutton = new RoundedButton("Book now");
    	homebutton.setBackground(Color.decode("#ed7d31"));
    	homebutton.setForeground(Color.WHITE);
    	homebutton.setFont(underrated);

		//JLabel homelbl = new JLabel("<html>Welcome to<br>Lakbay Airlines!</html>",SwingConstants.CENTER);

        // Set the layout manager to null for absolute positioning
        homeFrame.setLayout(new BorderLayout());

        // Add the button to the frame
        homeFrame.add(homebutton, BorderLayout.SOUTH);
        //homeFrame.add(homelbl, BorderLayout.CENTER);
        homeFrame.add(bgLabel, BorderLayout.CENTER);

        // Set the frame size
        homeFrame.setSize(1000, 600);

        int homeFrame_width = homeFrame.getWidth();

        Dimension buttonSize = new Dimension(homeFrame_width, 50); // 100 is the width, 50 is the height
        homebutton.setPreferredSize(buttonSize);

        // Close the frame when the user clicks the close button
        homeFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Make the frame visible
        homeFrame.setVisible(true);
        homeFrame.setLocationRelativeTo(null);

        // Add a component listener to the frame
        homeFrame.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                super.componentResized(e);
                Image img = bgImage.getImage();
                Image newImg = img.getScaledInstance(homeFrame.getWidth(), homeFrame.getHeight(), Image.SCALE_SMOOTH);
                bgLabel.setIcon(new ImageIcon(newImg));
            }
        });


	//FLIGHT PAGE 1

		frame = new JFrame("Flights");
		frame.setLayout(null);
		frame.setBounds(0,0,1000,600);

        JLabel f1_bgimage = new JLabel() {
            @Override
            public void paintComponent(Graphics g) {
                super.paintComponent(g);
                try {
                    Image f1_backgroundImage = ImageIO.read(new File("C:\\Users\\romxcob\\Documents\\JCreator LE\\MyProjects\\projectsample\\photos\\framebackground.png"));
                    g.drawImage(f1_backgroundImage, 0, 0, getWidth(), getHeight(), this);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };
        frame.setResizable(false);
        frame.setContentPane(f1_bgimage);

    	JLabel frame1_progressLabel1_flights = new JLabel("Flights");
    	RoundedButton frame1_progressLabel1 = new RoundedButton("");
    	JLabel frame1_progressLabel2 = new JLabel("  • • • • • • • •     Details     • • • • • • • •");
    	JLabel frame1_progressLabel3 = new JLabel("Payments       • • • • • • • •");
    	JLabel frame1_progressLabel4 = new JLabel("Confirmation");

		JLabel loc_from = new JLabel("From");
		JLabel loc_to = new JLabel("To");
		JLabel flighttype = new JLabel("Flight Type");
 		JLabel type = new JLabel("Type");
 		JLabel type_content = new JLabel("");
 		JLabel guestnumber = new JLabel("Guest");
		JLabel guest = new JLabel("Guests");
		JLabel guest_content = new JLabel("");
		JLabel departuredate = new JLabel("Departure Date");
		JLabel departure = new JLabel("Departure");
		JLabel departure_content = new JLabel("");
		JLabel returningdate = new JLabel("Returning Date");
		JLabel returning = new JLabel("Return");
		JLabel returning_content = new JLabel("");

		//radiobuttons

		int rtrip_oway_width = 75; // Desired width
		int rtrip_oway_height = 75; // Desired height

		ImageIcon rtripSelectedOriginal = new ImageIcon("C:\\Users\\romxcob\\Documents\\JCreator LE\\MyProjects\\projectsample\\photos\\rtripselected.png");
		Image rtripSelectedImage = rtripSelectedOriginal.getImage().getScaledInstance(rtrip_oway_width, rtrip_oway_height, Image.SCALE_DEFAULT);
		ImageIcon rtripSelected = new ImageIcon(rtripSelectedImage);

		ImageIcon rtripDeselectedOriginal = new ImageIcon("C:\\Users\\romxcob\\Documents\\JCreator LE\\MyProjects\\projectsample\\photos\\rtripdeselected.png");
		Image rtripDeselectedImage = rtripDeselectedOriginal.getImage().getScaledInstance(rtrip_oway_width, rtrip_oway_height, Image.SCALE_DEFAULT);
		ImageIcon rtripDeselected = new ImageIcon(rtripDeselectedImage);

		ImageIcon onewaySelectedOriginal = new ImageIcon("C:\\Users\\romxcob\\Documents\\JCreator LE\\MyProjects\\projectsample\\photos\\onewayselected.png");
		Image onewaySelectedImage = onewaySelectedOriginal.getImage().getScaledInstance(rtrip_oway_width, rtrip_oway_height, Image.SCALE_DEFAULT);
		ImageIcon onewaySelected = new ImageIcon(onewaySelectedImage);

		ImageIcon onewayDeselectedOriginal = new ImageIcon("C:\\Users\\romxcob\\Documents\\JCreator LE\\MyProjects\\projectsample\\photos\\onewaydeselected.png");
		Image onewayDeselectedImage = onewayDeselectedOriginal.getImage().getScaledInstance(rtrip_oway_width, rtrip_oway_height, Image.SCALE_DEFAULT);
		ImageIcon onewayDeselected = new ImageIcon(onewayDeselectedImage);

		JRadioButton rtrip = new JRadioButton(rtripDeselected);
 		rtrip.setSelectedIcon(rtripSelected);
		JRadioButton oneway = new JRadioButton(onewayDeselected);
		oneway.setSelectedIcon(onewaySelected);
		ButtonGroup flight_type = new ButtonGroup();
		flight_type.add(rtrip);
		flight_type.add(oneway);

		HashMap<String, double[]> from = new HashMap<>();
		from.put("Bacolod BCD", new double[]{10.776389, 123.015278});
		from.put("Bohol TAG", new double[]{9.566667, 123.775});
		from.put("Boracay MPH", new double[]{11.924722, 121.955});
		from.put("Butuan BXU", new double[]{8.951389, 125.478056});
		from.put("CDO CGY", new double[]{8.6125, 124.457222});
		from.put("Cauayan CYZ", new double[]{16.93, 121.753056});
		from.put("Cebu CEB", new double[]{10.307222, 123.978889});
		from.put("Clark CRK", new double[]{15.185833, 120.559722});
		from.put("Coron USU", new double[]{12.121389, 120.1});
		from.put("Cotabato CBO", new double[]{7.165278, 124.209722});
		from.put("Davao DVO", new double[]{7.125278, 125.645833});
		from.put("Dipolog DPL", new double[]{8.601389, 123.341944});
		from.put("Dumaguete DGT", new double[]{9.334167, 123.301111});
		from.put("GenSan GES", new double[]{6.057778, 125.095833});
		from.put("Iloilo ILO", new double[]{10.832778, 122.493333});
		from.put("Kalibo KLO", new double[]{11.679167, 122.375833});
		from.put("Laoag LAO", new double[]{18.178056, 120.531944});
		from.put("Legazpi DRP", new double[]{13.112222, 123.677222});
		from.put("Manila MNL", new double[]{14.508333, 121.019722});
		from.put("Masbate MBT", new double[]{12.369444, 123.629167});
		from.put("Ozamiz OZC", new double[]{8.178525, 123.841386});
		from.put("Pagadian PAG", new double[]{7.827222, 123.458333});
		from.put("Puerto Princesa PPS", new double[]{9.740696, 118.730072});
		from.put("Roxas RXS", new double[]{11.597778, 122.751667});
		from.put("Siargao IAO", new double[]{9.858889, 126.013889});
		from.put("Surigao SUG", new double[]{9.7575, 125.479444});
		from.put("Tacloban TAC", new double[]{11.2275, 125.027778});
		from.put("Tawi-Tawi TWT", new double[]{5.046944, 119.742778});
		from.put("Tuguegarao TUG", new double[]{17.638333, 121.730556});
		from.put("Virac VRC", new double[]{13.576389, 124.205556});
		from.put("Zamboanga ZAM", new double[]{6.922222, 122.059722});

		List<String> from_sortedKeys = new ArrayList<>(from.keySet());
		Collections.sort(from_sortedKeys);
		JComboBox<String> wherefrom = new JComboBox<>(from_sortedKeys.toArray(new String[0]));

		HashMap<String, double[]> to = new HashMap<>();
		to.put("Bacolod BCD", new double[]{10.776389, 123.015278});
		to.put("Bohol TAG", new double[]{9.566667, 123.775});
		to.put("Boracay MPH", new double[]{11.924722, 121.955});
		to.put("Butuan BXU", new double[]{8.951389, 125.478056});
		to.put("CDO CGY", new double[]{8.6125, 124.457222});
		to.put("Cauayan CYZ", new double[]{16.93, 121.753056});
		to.put("Cebu CEB", new double[]{10.307222, 123.978889});
		to.put("Clark CRK", new double[]{15.185833, 120.559722});
		to.put("Coron USU", new double[]{12.121389, 120.1});
		to.put("Cotabato CBO", new double[]{7.165278, 124.209722});
		to.put("Davao DVO", new double[]{7.125278, 125.645833});
		to.put("Dipolog DPL", new double[]{8.601389, 123.341944});
		to.put("Dumaguete DGT", new double[]{9.334167, 123.301111});
		to.put("GenSan GES", new double[]{6.057778, 125.095833});
		to.put("Iloilo ILO", new double[]{10.832778, 122.493333});
		to.put("Kalibo KLO", new double[]{11.679167, 122.375833});
		to.put("Laoag LAO", new double[]{18.178056, 120.531944});
		to.put("Legazpi DRP", new double[]{13.112222, 123.677222});
		to.put("Manila MNL", new double[]{14.508333, 121.019722});
		to.put("Masbate MBT", new double[]{12.369444, 123.629167});
		to.put("Ozamiz OZC", new double[]{8.178525, 123.841386});
		to.put("Pagadian PAG", new double[]{7.827222, 123.458333});
		to.put("Puerto Princesa PPS", new double[]{9.740696, 118.730072});
		to.put("Roxas RXS", new double[]{11.597778, 122.751667});
		to.put("Siargao IAO", new double[]{9.858889, 126.013889});
		to.put("Surigao SUG", new double[]{9.7575, 125.479444});
		to.put("Tacloban TAC", new double[]{11.2275, 125.027778});
		to.put("Tawi-Tawi TWT", new double[]{5.046944, 119.742778});
		to.put("Tuguegarao TUG", new double[]{17.638333, 121.730556});
		to.put("Virac VRC", new double[]{13.576389, 124.205556});
		to.put("Zamboanga ZAM", new double[]{6.922222, 122.059722});

		List<String> to_sortedKeys = new ArrayList<>(to.keySet());
		Collections.sort(to_sortedKeys);
		JComboBox<String> whereto = new JComboBox<>(to_sortedKeys.toArray(new String[0]));

		wherefrom.setSelectedIndex(0);
		whereto.setSelectedIndex(1);

 		JTextField guestno = new JTextField("0");

        //plus / minus buttons

		int plus_width = 50; // Desired width
		int plus_height = 35; // Desired height

		//plus
		ImageIcon plusOriginal = new ImageIcon("C:\\Users\\romxcob\\Documents\\JCreator LE\\MyProjects\\projectsample\\photos\\plus.png");
		Image plusImage = plusOriginal.getImage().getScaledInstance(plus_width, plus_height, Image.SCALE_DEFAULT);
		ImageIcon plusSelected = new ImageIcon(plusImage);

		//minus
		ImageIcon minusOriginal = new ImageIcon("C:\\Users\\romxcob\\Documents\\JCreator LE\\MyProjects\\projectsample\\photos\\minus.png");
		Image minusImage = minusOriginal.getImage().getScaledInstance(plus_width, plus_height, Image.SCALE_DEFAULT);
		ImageIcon minusSelected = new ImageIcon(minusImage);

		RoundedButton plus = new RoundedButton("");
		plus.setIcon(plusSelected);

		RoundedButton minus = new RoundedButton("");
		minus.setIcon(minusSelected);

 		RoundedButton search = new RoundedButton("Search flights");
 		RoundedButton frame1back = new RoundedButton("Back");

        JXDatePicker origdate = new JXDatePicker();
        origdate.setDate(Calendar.getInstance().getTime());

        JXDatePicker departdate = new JXDatePicker();
        departdate.setDate(Calendar.getInstance().getTime());
        departdate.setFormats(new SimpleDateFormat("MM.dd.yyyy"));

        JXDatePicker returndate = new JXDatePicker();
        Calendar calendar1 = Calendar.getInstance();
        calendar1.add(Calendar.DAY_OF_MONTH, 1);
        returndate.setDate(calendar1.getTime());
        returndate.setFormats(new SimpleDateFormat("MM.dd.yyyy"));


	//FLIGHT PAGE 2

		frame2 = new JFrame("Flights");
		frame2.setLayout(null);

        JLabel f2_bgimage = new JLabel() {
            @Override
            public void paintComponent(Graphics g) {
                super.paintComponent(g);
                try {
                    Image f2_backgroundImage = ImageIO.read(new File("C:\\Users\\romxcob\\Documents\\JCreator LE\\MyProjects\\projectsample\\photos\\frame2background.png"));
                    g.drawImage(f2_backgroundImage, 0, 0, getWidth(), getHeight(), this);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };
        frame2.setResizable(false);
        frame2.setContentPane(f2_bgimage);

    	JLabel f2_progressLabel1_flights = new JLabel("Flights");
    	RoundedButton f2_progressLabel1 = new RoundedButton("");
    	JLabel f2_progressLabel2 = new JLabel("  • • • • • • • •     Details     • • • • • • • •");
    	JLabel f2_progressLabel3 = new JLabel("Payments       • • • • • • • •");
    	JLabel f2_progressLabel4 = new JLabel("Confirmation");

 		JLabel select_departure = new JLabel("Select your departure flight");
 		JLabel select_returning = new JLabel("Select your returning flight");
 		JLabel select_bundle = new JLabel("Select your bundle");
 		JLabel bundle1 = new JLabel("<html>BASIC<br>- 1pc hand carry bag<br>- Random seat</html>");
 		JLabel bundle2 = new JLabel("<html>PREMIUM<br>- 1pc hand carry bag<br>- 1 baggage<br>- Random seat</html>");
 		JLabel bundle3 = new JLabel("<html>PREMIUM++<br>- 1pc hand carry bag<br>- 1 baggage<br>- Choose a seat</html>");

		DefaultTableModel tbmodel_departureflight = new DefaultTableModel(new String[]{"Depart Time","Arrive Time","Total Time","Flight No.","Price"}, 0);
		JTable table_departureflight = new JTable (tbmodel_departureflight);
		JScrollPane spane_departureflight = new JScrollPane(table_departureflight);

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection f2_dep_con = DriverManager.getConnection(url, username, password);
            Statement f2_dep_stmt = f2_dep_con.createStatement();
            ResultSet f2_dep_rs = f2_dep_stmt.executeQuery("SELECT * FROM flight_details_departure");

            while(f2_dep_rs.next()) {
            	SimpleDateFormat f2_dep_sdf = new SimpleDateFormat("hh:mm a");
                String d_time_str = f2_dep_rs.getString("DepartTime");
                String a_time_str = f2_dep_rs.getString("ArriveTime");

				SimpleDateFormat inputFormat = new SimpleDateFormat("HH:mm:ss");
				Date d_time = inputFormat.parse(d_time_str);
				Date a_time = inputFormat.parse(a_time_str);

                String formatted_dtime = f2_dep_sdf.format(d_time);
				String formatted_atime = f2_dep_sdf.format(a_time);

                String tot_time = f2_dep_rs.getString("TotalTime");
                String flight_no = f2_dep_rs.getString("FlightNo");
                String price = f2_dep_rs.getString("Price");
                tbmodel_departureflight.addRow(new Object[]{formatted_dtime,formatted_atime,tot_time,flight_no,price});
            }

            f2_dep_con.close();
        } catch(Exception e) {
            System.out.println(e);
        }

		DefaultTableModel tbmodel_returningflight = new DefaultTableModel(new String[]{"Depart Time","Arrive Time","Total Time","Flight No.","Price"}, 0);
		JTable table_returningflight = new JTable (tbmodel_returningflight);
		JScrollPane spane_returningflight = new JScrollPane(table_returningflight);

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection f2_ret_con = DriverManager.getConnection(url, username, password);
            Statement f2_ret_stmt = f2_ret_con.createStatement();
            ResultSet f2_ret_rs = f2_ret_stmt.executeQuery("SELECT * FROM flight_details_returning");

            while(f2_ret_rs.next()) {
            	SimpleDateFormat f2_ret_sdf = new SimpleDateFormat("hh:mm a");
                String d_time_str = f2_ret_rs.getString("DepartTime");
                String a_time_str = f2_ret_rs.getString("ArriveTime");

				SimpleDateFormat inputFormat = new SimpleDateFormat("HH:mm:ss");
				Date d_time = inputFormat.parse(d_time_str);
				Date a_time = inputFormat.parse(a_time_str);

                String formatted_dtime = f2_ret_sdf.format(d_time);
				String formatted_atime = f2_ret_sdf.format(a_time);

                String tot_time = f2_ret_rs.getString("TotalTime");
                String flight_no = f2_ret_rs.getString("FlightNo");
                String price = f2_ret_rs.getString("Price");
                tbmodel_returningflight.addRow(new Object[]{formatted_dtime,formatted_atime,tot_time,flight_no,price});
            }

            f2_ret_con.close();
        } catch(Exception e) {
            System.out.println(e);
        }

        //radiobuttons

		int bundle_width = 160; // Desired width
		int bundle_height = 70; // Desired height

		//basic
		ImageIcon basicSelectedOriginal = new ImageIcon("C:\\Users\\romxcob\\Documents\\JCreator LE\\MyProjects\\projectsample\\photos\\basicselected.png");
		Image basicSelectedImage = basicSelectedOriginal.getImage().getScaledInstance(bundle_width, bundle_height, Image.SCALE_DEFAULT);
		ImageIcon basicSelected = new ImageIcon(basicSelectedImage);

		ImageIcon basicDeselectedOriginal = new ImageIcon("C:\\Users\\romxcob\\Documents\\JCreator LE\\MyProjects\\projectsample\\photos\\basicdeselected.png");
		Image basicDeselectedImage = basicDeselectedOriginal.getImage().getScaledInstance(bundle_width, bundle_height, Image.SCALE_DEFAULT);
		ImageIcon basicDeselected = new ImageIcon(basicDeselectedImage);

		//premium
		ImageIcon premSelectedOriginal = new ImageIcon("C:\\Users\\romxcob\\Documents\\JCreator LE\\MyProjects\\projectsample\\photos\\premselected.png");
		Image premSelectedImage = premSelectedOriginal.getImage().getScaledInstance(bundle_width, bundle_height, Image.SCALE_DEFAULT);
		ImageIcon premSelected = new ImageIcon(premSelectedImage);

		ImageIcon premDeselectedOriginal = new ImageIcon("C:\\Users\\romxcob\\Documents\\JCreator LE\\MyProjects\\projectsample\\photos\\premdeselected.png");
		Image premDeselectedImage = premDeselectedOriginal.getImage().getScaledInstance(bundle_width, bundle_height, Image.SCALE_DEFAULT);
		ImageIcon premDeselected = new ImageIcon(premDeselectedImage);

		//premium+
		ImageIcon premplusSelectedOriginal = new ImageIcon("C:\\Users\\romxcob\\Documents\\JCreator LE\\MyProjects\\projectsample\\photos\\premplusselected.png");
		Image premplusSelectedImage = premplusSelectedOriginal.getImage().getScaledInstance(bundle_width, bundle_height, Image.SCALE_DEFAULT);
		ImageIcon premplusSelected = new ImageIcon(premplusSelectedImage);

		ImageIcon premplusDeselectedOriginal = new ImageIcon("C:\\Users\\romxcob\\Documents\\JCreator LE\\MyProjects\\projectsample\\photos\\premplusdeselected.png");
		Image premplusDeselectedImage = premplusDeselectedOriginal.getImage().getScaledInstance(bundle_width, bundle_height, Image.SCALE_DEFAULT);
		ImageIcon premplusDeselected = new ImageIcon(premplusDeselectedImage);

		JRadioButton rb_bundle1 = new JRadioButton(basicDeselected);
		rb_bundle1.setSelectedIcon(basicSelected);
		//rb_bundle1.setDisabledIcon(basicDeselected);
		JRadioButton rb_bundle2 = new JRadioButton(premDeselected);
		rb_bundle2.setSelectedIcon(premSelected);
		//rb_bundle2.setDisabledIcon(premDeselected);
		JRadioButton rb_bundle3 = new JRadioButton(premplusDeselected);
		rb_bundle3.setSelectedIcon(premplusSelected);
		//rb_bundle3.setDisabledIcon(premplusDeselected);
 		rb_bundle1.setName("0");
 		rb_bundle2.setName("250");
 		rb_bundle3.setName("500");
		ButtonGroup bg_bundles = new ButtonGroup();
		bg_bundles.add(rb_bundle1);
		bg_bundles.add(rb_bundle2);
		bg_bundles.add(rb_bundle3);

		//buttons
		RoundedButton frame2btn_back = new RoundedButton("Back");
		RoundedButton frame2btn_continue = new RoundedButton("Continue");


	//SEATS (FLIGHT PAGE 2.5)

		frame_seats = new JFrame("Choose your seat");
		frame_seats.setLayout(new BorderLayout());

        JLabel seats_label = new JLabel() {
            @Override
            public void paintComponent(Graphics g) {
                super.paintComponent(g);
                try {
                    Image seats_backgroundImage = ImageIO.read(new File("C:\\Users\\romxcob\\Documents\\JCreator LE\\MyProjects\\projectsample\\photos\\seatplan.png"));
                    g.drawImage(seats_backgroundImage, 0, 0, getWidth(), getHeight(), this);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };
        frame_seats.setResizable(false);
		RoundedButton btn_seat = new RoundedButton("Select seat");
        frame_seats.add(btn_seat);
        frame_seats.setContentPane(seats_label);

 		JLabel seat_A = new JLabel("A");
 		JLabel seat_B = new JLabel("B");
 		JLabel seat_C = new JLabel("C");
 		JLabel seat_1 = new JLabel("1");
		JLabel seat_2 = new JLabel("2");
		JLabel seat_3 = new JLabel("3");
		JLabel seat_4 = new JLabel("4");
		JLabel seat_5 = new JLabel("5");

		JLabel lbl_selectedseat = new JLabel("Selected Seat:");
		JTextField tf_selectedseat = new JTextField("");

        //checkboxes - seats

		int seats_width = 15; // Desired width
		int seats_height = 15; // Desired height

		ImageIcon seatSelectedOriginal = new ImageIcon("C:\\Users\\romxcob\\Documents\\JCreator LE\\MyProjects\\projectsample\\photos\\seatselected.png");
		Image seatSelectedImage = seatSelectedOriginal.getImage().getScaledInstance(seats_width, seats_height, Image.SCALE_DEFAULT);
		ImageIcon seatSelected = new ImageIcon(seatSelectedImage);

		ImageIcon seatDeselectedOriginal = new ImageIcon("C:\\Users\\romxcob\\Documents\\JCreator LE\\MyProjects\\projectsample\\photos\\seatdeselected.png");
		Image seatDeselectedImage = seatDeselectedOriginal.getImage().getScaledInstance(seats_width, seats_height, Image.SCALE_DEFAULT);
		ImageIcon seatDeselected = new ImageIcon(seatDeselectedImage);

		ImageIcon seatTakenOriginal = new ImageIcon("C:\\Users\\romxcob\\Documents\\JCreator LE\\MyProjects\\projectsample\\photos\\seattaken.png");
		Image seatTakenImage = seatTakenOriginal.getImage().getScaledInstance(seats_width, seats_height, Image.SCALE_DEFAULT);
		seatTaken = new ImageIcon(seatTakenImage);
		System.out.println("After Initialization, seatTaken: " + seatTaken);

		A1 = new JCheckBox(seatDeselected); A2 = new JCheckBox(seatDeselected); A3 = new JCheckBox(seatDeselected); A4 = new JCheckBox(seatDeselected); A5 = new JCheckBox(seatDeselected);
		B1 = new JCheckBox(seatDeselected); B2 = new JCheckBox(seatDeselected); B3 = new JCheckBox(seatDeselected); B4 = new JCheckBox(seatDeselected); B5 = new JCheckBox(seatDeselected);
		C1 = new JCheckBox(seatDeselected); C2 = new JCheckBox(seatDeselected); C3 = new JCheckBox(seatDeselected); C4 = new JCheckBox(seatDeselected); C5 = new JCheckBox(seatDeselected);
		D1 = new JCheckBox(seatDeselected); D2 = new JCheckBox(seatDeselected); D3 = new JCheckBox(seatDeselected); D4 = new JCheckBox(seatDeselected); D5 = new JCheckBox(seatDeselected);

		A1.setSelectedIcon(seatSelected); A2.setSelectedIcon(seatSelected); A3.setSelectedIcon(seatSelected); A4.setSelectedIcon(seatSelected); A5.setSelectedIcon(seatSelected);
		B1.setSelectedIcon(seatSelected); B2.setSelectedIcon(seatSelected); B3.setSelectedIcon(seatSelected); B4.setSelectedIcon(seatSelected); B5.setSelectedIcon(seatSelected);
		C1.setSelectedIcon(seatSelected); C2.setSelectedIcon(seatSelected); C3.setSelectedIcon(seatSelected); C4.setSelectedIcon(seatSelected); C5.setSelectedIcon(seatSelected);
		D1.setSelectedIcon(seatSelected); D2.setSelectedIcon(seatSelected); D3.setSelectedIcon(seatSelected); D4.setSelectedIcon(seatSelected); D5.setSelectedIcon(seatSelected);

        JCheckBox[] checkBoxes = {A1,A2,A3,A4,A5,B1,B2,B3,B4,B5,C1,C2,C3,C4,C5,D1,D2,D3,D4,D5};

		checkBoxes[0].setName("A1"); checkBoxes[1].setName("A2"); checkBoxes[2].setName("A3"); checkBoxes[3].setName("A4"); checkBoxes[4].setName("A5");
		checkBoxes[5].setName("B1"); checkBoxes[6].setName("B2"); checkBoxes[7].setName("B3"); checkBoxes[8].setName("B4"); checkBoxes[9].setName("B5");
		checkBoxes[10].setName("C1"); checkBoxes[11].setName("C2"); checkBoxes[12].setName("C3"); checkBoxes[13].setName("C4"); checkBoxes[14].setName("C5");
		checkBoxes[15].setName("D1"); checkBoxes[16].setName("D2"); checkBoxes[17].setName("D3"); checkBoxes[18].setName("D4"); checkBoxes[19].setName("D5");

        for (JCheckBox checkBox : checkBoxes) {
            checkBox.addItemListener(new ItemListener() {
                public void itemStateChanged(ItemEvent e) {
                    int checkedCount = 0;
                    for (JCheckBox checkBox : checkBoxes) {
                        if (checkBox.isSelected()) {
                            checkedCount++;
                        }
                    }
                    if (checkedCount > limit) {
                        ((JCheckBox)e.getSource()).setSelected(false);
                        if (limit==1){
                        	JOptionPane.showMessageDialog(null, "You can only choose " + limit + " seat.");
                        }else{
                        	JOptionPane.showMessageDialog(null, "You can only choose " + limit + " seats.");
                        }
                    }
                }
            });
            frame_seats.add(checkBox);
        }

	//GUEST DETAILS (FRAME 3)

    	guestFrame = new JFrame("Guest Details");
    	guestFrame.setLayout(null);

        JLabel f3_bgimage = new JLabel() {
            @Override
            public void paintComponent(Graphics g) {
                super.paintComponent(g);
                try {
                    Image f3_backgroundImage = ImageIO.read(new File("C:\\Users\\romxcob\\Documents\\JCreator LE\\MyProjects\\projectsample\\photos\\frame3background.png"));
                    g.drawImage(f3_backgroundImage, 0, 0, getWidth(), getHeight(), this);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };
        guestFrame.setResizable(false);
        guestFrame.setContentPane(f3_bgimage);

    	JLabel guestDetlabel = new JLabel("GUEST DETAILS");
    	JLabel f3_progressLabel1 = new JLabel("Flights      • • • • • • • •");
    	JLabel f3_progressLabel2_details = new JLabel("Details");
    	RoundedButton f3_progressLabel2 = new RoundedButton("");
    	JLabel f3_progressLabel3 = new JLabel(" • • • • • • • •      Payments      • • • • • • • •");
    	JLabel f3_progressLabel4 = new JLabel("Confirmation");

    	JTextField fname_tf = new JTextField("Juan"); //DELETE SOON
    	JTextField mname_tf = new JTextField("Dela"); //DELETE SOON
    	JTextField lname_tf = new JTextField("Cruz"); //DELETE SOON
    	JLabel fname_label = new JLabel("First Name*");
    	JLabel mname_label = new JLabel("Middle Name (Optional)");
    	JLabel lname_label = new JLabel("Last Name*");

    	JLabel bdate_label = new JLabel("Date of Birth* (MM.DD.YYYY)");
		JXDatePicker birthdate = new JXDatePicker();
        birthdate.setDate(Calendar.getInstance().getTime());
        birthdate.setFormats(new SimpleDateFormat("MM.dd.yyyy"));

    	String[] userNationality = {"Filipino", "Korean", "Indian", "American", "Japanese", "Chinese", "Australian"};
    	JLabel nationality_label = new JLabel("Nationality*");
    	JComboBox <String> nationality_cbox = new JComboBox<>(userNationality);

    	JLabel contactNum_label = new JLabel("Contact Number* (09XXXXXXXXX)");
    	JTextField contactNum_tf = new JTextField("09123456789"); //DELETE SOON
    	JLabel email_label = new JLabel("Email*");
    	JTextField email_tf = new JTextField("sample@gmail.com"); //DELETE SOON

    	String agreementPolicy = "<html>I confirm that I have read, understood, and agree to the updated Lakbay Airlines Privacy Policy, which provides additional information on how my on how my Personal Information is used.</html>";
    	JCheckBox policy_chb = new JCheckBox(agreementPolicy);
		RoundedButton back_btn = new RoundedButton("Back");
    	RoundedButton cont_btn = new RoundedButton("Continue");

    //BOOKING SUMMARY AND PAYMENT (FRAME 4)

		frame4summary = new JFrame("Payment");
		frame4summary.setLayout(null);

        JLabel f4_bgimage = new JLabel() {
            @Override
            public void paintComponent(Graphics g) {
                super.paintComponent(g);
                try {
                    Image f4_backgroundImage = ImageIO.read(new File("C:\\Users\\romxcob\\Documents\\JCreator LE\\MyProjects\\projectsample\\photos\\frame4background.png"));
                    g.drawImage(f4_backgroundImage, 0, 0, getWidth(), getHeight(), this);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };
        frame4summary.setResizable(false);
        frame4summary.setContentPane(f4_bgimage);

		//progress bar
    	JLabel f4_progressLabel1 = new JLabel("Flights      • • • • • • • •");
    	JLabel f4_progressLabel2 = new JLabel("Details      • • • • • • • •");
    	RoundedButton f4_progressLabel3 = new RoundedButton("");
    	JLabel f4_progressLabel3_payment = new JLabel("Payments");
    	JLabel f4_progressLabel4 = new JLabel("• • • • • • • •      Confirmation");

 		//label for booking summary
 		JLabel bs = new JLabel("BOOKING SUMMARY");
 		JLabel st_flightdep = new JLabel("");
 		JLabel st_flightret = new JLabel("");
 		JLabel st1 = new JLabel("SUBTOTAL: ");
 		JLabel st2 = new JLabel("SUBTOTAL: ");
 		JLabel tax = new JLabel("TAXES/FEES: ");

 		JLabel lbl_bundle = new JLabel("Bundle: ");
 		JLabel af = new JLabel("Administrative Fee: ");
 		JLabel fs = new JLabel("Fuel Surcharge: ");
		JLabel dpsc = new JLabel("Passenger Service Charge:");
		JLabel phv = new JLabel("PH Value Added Tax:");
		JLabel st3 = new JLabel("SUBTOTAL:");
		JLabel total = new JLabel("TOTAL: ");
		JLabel change_label = new JLabel("TOTAL: ");

		JLabel payment_lbl = new JLabel("Payment:");
		JLabel pay_alert = new JLabel("");
		JLabel change_lbl = new JLabel("Change:");

 		//payment method
 		JLabel pm = new JLabel("PAYMENT METHOD");

 		//button
 		RoundedButton paybtn = new RoundedButton("Pay");
 		RoundedButton paybtncontinue = new RoundedButton("Continue");
 		RoundedButton paybtnback = new RoundedButton("Back");

 		//textfields
		JTextField payment_tf = new JTextField("");
		JTextField change_tf = new JTextField("");

		//radiobutton

		int width = 160; // Desired width
		int height = 70; // Desired height

		ImageIcon cashSelectedOriginal = new ImageIcon("C:\\Users\\romxcob\\Documents\\JCreator LE\\MyProjects\\projectsample\\photos\\cashselected.png");
		Image cashSelectedImage = cashSelectedOriginal.getImage().getScaledInstance(width, height, Image.SCALE_DEFAULT);
		ImageIcon cashSelected = new ImageIcon(cashSelectedImage);

		ImageIcon cashDeselectedOriginal = new ImageIcon("C:\\Users\\romxcob\\Documents\\JCreator LE\\MyProjects\\projectsample\\photos\\cashdeselected.png");
		Image cashDeselectedImage = cashDeselectedOriginal.getImage().getScaledInstance(width, height, Image.SCALE_DEFAULT);
		ImageIcon cashDeselected = new ImageIcon(cashDeselectedImage);

 		JRadioButton cash = new JRadioButton(cashDeselected);
 		cash.setSelectedIcon(cashSelected);


    //CONFIRMATION PAGE (FRAME 5)
    	//frame.setExtendedState(java.awt.Frame.MAXIMIZED_BOTH); USE FOR FRAME 6
		frame5 = new JFrame("Confirmation");

        JLabel f5_bgimage = new JLabel() {
            @Override
            public void paintComponent(Graphics g) {
                super.paintComponent(g);
                try {
                    Image f5_backgroundImage = ImageIO.read(new File("C:\\Users\\romxcob\\Documents\\JCreator LE\\MyProjects\\projectsample\\photos\\frame5background.png"));
                    g.drawImage(f5_backgroundImage, 0, 0, getWidth(), getHeight(), this);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };
        frame5.setResizable(false);
        frame5.setContentPane(f5_bgimage);

		frame5.setLayout(null);
		frame5.setBounds(0,0,1000,600);
		frame5.setVisible(false);
		frame5.setLocationRelativeTo(null);

		RoundedButton f5_btn_viewreceipt = new RoundedButton("View receipt");
		RoundedButton f5_btn_bookanother = new RoundedButton("Book another flight");

		//progress bar
    	JLabel f5_progressLabel1 = new JLabel("Flights      • • • • • • • •");
    	JLabel f5_progressLabel2 = new JLabel("Details      • • • • • • • •");
    	JLabel f5_progressLabel3 = new JLabel("Payments      • • • • • • • •");
    	JLabel f5_progressLabel4_confirm = new JLabel("Confirmation");
    	RoundedButton f5_progressLabel4 = new RoundedButton("");

	//VIEW RECEIPT (FRAME 6)
		frame6 = new JFrame("Itinerary Receipt");

        JLabel f6_bgimage = new JLabel() {
            @Override
            public void paintComponent(Graphics g) {
                super.paintComponent(g);
                try {
                    Image f6_backgroundImage = ImageIO.read(new File("C:\\Users\\romxcob\\Documents\\JCreator LE\\MyProjects\\projectsample\\photos\\frame6background.png"));
                    g.drawImage(f6_backgroundImage, 0, 0, getWidth(), getHeight(), this);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };
        frame6.setResizable(false);
        frame6.setContentPane(f6_bgimage);

		frame6.setLayout(null);
		frame6.setBounds(0,0,1000,680);
		frame6.setVisible(false);
		frame6.setLocationRelativeTo(null);

    	Container f6_contentPane = frame6.getContentPane();
        f6_contentPane.setBackground(Color.WHITE);

		//RECEIPT

		//booking details
		DefaultTableModel receipt_tblmodel1 = new DefaultTableModel(new String[]{"Booking Date","Booking Reference No.","Type","Bundle"}, 0);
		JTable receipt_tbl1 = new JTable(receipt_tblmodel1);
		JScrollPane receipt_spane1 = new JScrollPane(receipt_tbl1);

        receipt_spane1.setBounds(50,120,880,53);
        receipt_spane1.getViewport().setBackground(Color.WHITE);
		receipt_tbl1.setEnabled(false);
    	frame6.add(receipt_spane1);

    	DefaultTableCellRenderer centerRenderer1 = new DefaultTableCellRenderer();
		centerRenderer1.setHorizontalAlignment(SwingConstants.CENTER);

		for (int i = 0; i < receipt_tbl1.getColumnCount(); i++) {
		    receipt_tbl1.getColumnModel().getColumn(i).setCellRenderer(centerRenderer1);
		}

		//guest details
		DefaultTableModel receipt_tblmodel_guest = new DefaultTableModel(new String[]{"No. of Passenger/s","Name"}, 0);
		JTable receipt_tbl_guest = new JTable(receipt_tblmodel_guest);
		JScrollPane receipt_spane_guest = new JScrollPane(receipt_tbl_guest);

        receipt_spane_guest.setBounds(50,205,880,55);
        receipt_spane_guest.getViewport().setBackground(Color.WHITE);
		receipt_tbl_guest.setEnabled(false);
    	frame6.add(receipt_spane_guest);

    	DefaultTableCellRenderer centerRenderer_guest = new DefaultTableCellRenderer();
		centerRenderer_guest.setHorizontalAlignment(SwingConstants.CENTER);

		limit = Integer.parseInt(guestno.getText());

		if (limit>2){
			for (int i = 0; i < receipt_tbl_guest.getColumnCount(); i++) {
				if (i != 1) { // Exclude the column with word wrap
			    	receipt_tbl_guest.getColumnModel().getColumn(i).setCellRenderer(centerRenderer_guest);
				}
			}
		}else{
			for (int i = 0; i < receipt_tbl_guest.getColumnCount(); i++) {
			    receipt_tbl_guest.getColumnModel().getColumn(i).setCellRenderer(centerRenderer_guest);
			}
		}

		//flight details
		DefaultTableModel receipt_tblmodel2 = new DefaultTableModel(new String[]{"--","Flight No.","Date","Time","Destination","Seat/s"}, 0);
		JTable receipt_tbl2 = new JTable(receipt_tblmodel2);
		JScrollPane receipt_spane2 = new JScrollPane(receipt_tbl2);

        receipt_spane2.setBounds(50,293,880,83);
        receipt_spane2.getViewport().setBackground(Color.WHITE);
		receipt_tbl2.setEnabled(false);
		receipt_tbl2.getColumnModel().getColumn(4).setPreferredWidth(200);
    	frame6.add(receipt_spane2);

    	DefaultTableCellRenderer centerRenderer2 = new DefaultTableCellRenderer();
		centerRenderer2.setHorizontalAlignment(SwingConstants.CENTER);

		for (int i = 0; i < receipt_tbl2.getColumnCount(); i++) {
		    receipt_tbl2.getColumnModel().getColumn(i).setCellRenderer(centerRenderer2);
		}

		//payment details
		DefaultTableModel receipt_tblmodel3 = new DefaultTableModel(new String[]{"Fare","Amount"}, 0);
		JTable receipt_tbl3 = new JTable(receipt_tblmodel3);
		JScrollPane receipt_spane3 = new JScrollPane(receipt_tbl3);

        receipt_spane3.setBounds(50,409,400,151);
        receipt_spane3.getViewport().setBackground(Color.WHITE);
		receipt_tbl3.setEnabled(false);
    	frame6.add(receipt_spane3);

    	DefaultTableCellRenderer centerRenderer3 = new DefaultTableCellRenderer();
		centerRenderer3.setHorizontalAlignment(SwingConstants.CENTER);

		for (int i = 0; i < receipt_tbl3.getColumnCount(); i++) {
		    receipt_tbl3.getColumnModel().getColumn(i).setCellRenderer(centerRenderer3);
		}

		/*
		DefaultTableCellRenderer rightRenderer = new DefaultTableCellRenderer();
		rightRenderer.setHorizontalAlignment(JLabel.RIGHT);
		receipt_tbl3.getColumnModel().getColumn(0).setCellRenderer(rightRenderer);

		DefaultTableCellRenderer leftRenderer = new DefaultTableCellRenderer();
		leftRenderer.setHorizontalAlignment(JLabel.LEFT);
		receipt_tbl3.getColumnModel().getColumn(1).setCellRenderer(leftRenderer);
		*/

    	//table header
    	JTableHeader header1 = receipt_tbl1.getTableHeader();
    	JTableHeader header2 = receipt_tbl2.getTableHeader();
    	JTableHeader header3 = receipt_tbl3.getTableHeader();
    	JTableHeader header4 = receipt_tbl_guest.getTableHeader();
		header1.setOpaque(true);
    	header1.setBackground(Color.WHITE);
		header2.setOpaque(true);
    	header2.setBackground(Color.WHITE);
		header3.setOpaque(true);
    	header3.setBackground(Color.WHITE);
		header4.setOpaque(true);
    	header4.setBackground(Color.WHITE);

		//barcode
 		ImageIcon bcode_imgicon = new ImageIcon("C:\\Users\\romxcob\\Documents\\JCreator LE\\MyProjects\\projectsample\\photos\\barcode.png");
        Image bcode_img = bcode_imgicon.getImage(); // Get the ImageIcon's Image
        Image bcode_resizedImage = bcode_img.getScaledInstance(180,100, java.awt.Image.SCALE_SMOOTH); // Scale it
        bcode_imgicon = new ImageIcon(bcode_resizedImage);
        JLabel bcode_imgicon_lbl = new JLabel();
        bcode_imgicon_lbl.setIcon(bcode_imgicon);
        bcode_imgicon_lbl.setBounds(755,0,180,100);
		frame6.add(bcode_imgicon_lbl);

		/*
		//logo for receipt
 		ImageIcon logo_imgicon = new ImageIcon("C:\\Users\\romxcob\\Documents\\JCreator LE\\MyProjects\\projectsample\\photos\\logo.png");
        Image logo_img = logo_imgicon.getImage(); // Get the ImageIcon's Image
        Image logo_resizedImage = logo_img.getScaledInstance(400,225, java.awt.Image.SCALE_SMOOTH); // Scale it
        logo_imgicon = new ImageIcon(logo_resizedImage);
        JLabel logo_imgicon_lbl = new JLabel();
        logo_imgicon_lbl.setIcon(logo_imgicon);
        logo_imgicon_lbl.setBounds(525,355,500,300);
		frame6.add(logo_imgicon_lbl);
		*/

		//button
		RoundedButton receipt_download = new RoundedButton("Save receipt");
		receipt_download.setFont(calsans.deriveFont(18f));
		receipt_download.setBackground(Color.decode("#ed7d31"));
		receipt_download.setForeground(Color.WHITE);
		receipt_download.setBounds(50,585,200,30);
		frame6.add(receipt_download);

	//LISTENER (HOME)

		homebutton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				load_frame.setVisible(true);

		        load_frame.addWindowListener(new WindowAdapter() {
		            public void windowClosed(WindowEvent e) {
		            	homeFrame.setVisible(false);
						frame.setVisible(true);
		            }
		        });
			}
		});

	//LISTENER (FRAME 1)

		rtrip.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				if(rtrip.isSelected()){
					type_content.setText("Round-trip");
					returndate.setVisible(true);
					returningdate.setVisible(true);
					returning.setVisible(true);
					returning_content.setVisible(true);
					returndate.setEnabled(true);
				}
		        if (table_departureflight.getSelectedRow() != -1 && table_returningflight.getSelectedRow() != -1) {
		            rb_bundle1.setEnabled(true);
		            rb_bundle2.setEnabled(true);
		            rb_bundle3.setEnabled(true);
		        } else {
		            rb_bundle1.setEnabled(false);
		            rb_bundle2.setEnabled(false);
		            rb_bundle3.setEnabled(false);
		        }
			}
		});

		oneway.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				if(oneway.isSelected()){
		        	Date date2 = (Date) returndate.getDate();
					type_content.setText("One-way");
					returndate.setVisible(false);
					returning.setVisible(false);
					returning_content.setVisible(false);
					returningdate.setVisible(false);
					returndate.setEnabled(!oneway.isSelected());
				}
		        if (table_departureflight.getSelectedRow() != -1) {
		            rb_bundle1.setEnabled(true);
		            rb_bundle2.setEnabled(true);
		            rb_bundle3.setEnabled(true);
		        } else {
		            rb_bundle1.setEnabled(false);
		            rb_bundle2.setEnabled(false);
		            rb_bundle3.setEnabled(false);
		        }
			}
		});

		wherefrom.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				if(wherefrom.getSelectedItem().equals(whereto.getSelectedItem())){
					JOptionPane.showMessageDialog(frame,"Invalid Departure/Return Location","ERROR",JOptionPane.ERROR_MESSAGE);
				}

				airport1 = (String)wherefrom.getSelectedItem();
				coordinates1 = from.get(airport1);
			}
		});

		whereto.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				if(wherefrom.getSelectedItem().equals(whereto.getSelectedItem())){
					JOptionPane.showMessageDialog(frame,"Invalid Departure/Return Location","ERROR",JOptionPane.ERROR_MESSAGE);
				}

				airport2 = (String)whereto.getSelectedItem();
				coordinates2 = to.get(airport2);
			}
		});

        plus.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
            	int count = Integer.parseInt(guestno.getText());
				if(!rtrip.isSelected() && !oneway.isSelected()){
					JOptionPane.showMessageDialog(frame,"Choose a flight type","ERROR",JOptionPane.ERROR_MESSAGE);
				}else{
	                count+=1;
	                guestno.setText(String.valueOf(count));
	                guest_content.setText(String.valueOf(count));
				}
                if(count>5){
					JOptionPane.showMessageDialog(frame,"Maximum limit for this booking","LIMIT REACHED",JOptionPane.INFORMATION_MESSAGE);
                	guestno.setText("5");
                	guest_content.setText("5");
                }
            }
        });

        minus.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
				int count = Integer.parseInt(guestno.getText());
                count-=1;
                guestno.setText(String.valueOf(count));
                guest_content.setText(String.valueOf(count));

                if(count<1){
                	JOptionPane.showMessageDialog(frame,"Invalid number of guest","ERROR",JOptionPane.ERROR_MESSAGE);
                	guestno.setText("1");
                	guest_content.setText("1");
                }
            }
        });

        departdate.addActionListener(e -> {
            // Get the selected date
            java.util.Date selectedDate = departdate.getDate();
            // Format the date as a string
            String dateString = new SimpleDateFormat("MMM dd yyyy").format(selectedDate);
            // Set the text of the label
			String a = String.valueOf(wherefrom.getSelectedItem());
			String b = String.valueOf(whereto.getSelectedItem());
			//departure_content.setBounds(525,125,150,50);
			if(!rtrip.isSelected() && !oneway.isSelected()){
				JOptionPane.showMessageDialog(frame,"Choose a flight type","ERROR",JOptionPane.ERROR_MESSAGE);
			}else if(wherefrom.getSelectedItem().equals(whereto.getSelectedItem())){
				JOptionPane.showMessageDialog(frame,"Invalid Departure/Return Location","ERROR",JOptionPane.ERROR_MESSAGE);
			}else if(guest_content.getText().equals("")){
				JOptionPane.showMessageDialog(frame,"Enter number of guest","ERROR",JOptionPane.ERROR_MESSAGE);
			}else{
				Date date1 = (Date) departdate.getDate();
		        Date date2 = (Date) returndate.getDate();
		        Date ogdate = (Date) origdate.getDate();
		        // Check if date2 is on or past date1
		        if (date1.before(ogdate)) {
		            JOptionPane.showMessageDialog(null, "The selected date is behind the current date.", "Invalid Date", JOptionPane.ERROR_MESSAGE);
		        }else{
					departure_content.setText("<html>"+a+" to"+"<br>"+b+"<br>"+dateString+"</html>");
		        }
			}
        });

        returndate.addActionListener(e -> {
            // Get the selected date
            java.util.Date selectedDate = returndate.getDate();
            // Format the date as a string
            String dateString = new SimpleDateFormat("MMM dd yyyy").format(selectedDate);
            // Set the text of the label
			String a = String.valueOf(whereto.getSelectedItem());
			String b = String.valueOf(wherefrom.getSelectedItem());
			//returning_content.setBounds(705,125,150,50);
			if(!rtrip.isSelected() && !oneway.isSelected()){
				JOptionPane.showMessageDialog(frame,"Choose a flight type","ERROR",JOptionPane.ERROR_MESSAGE);
			}else if(wherefrom.getSelectedItem().equals(whereto.getSelectedItem())){
				JOptionPane.showMessageDialog(frame,"Invalid Departure/Return Location","ERROR",JOptionPane.ERROR_MESSAGE);
			}else if(guest_content.getText().equals("")){
				JOptionPane.showMessageDialog(frame,"Enter number of guest","ERROR",JOptionPane.ERROR_MESSAGE);
			}else{
				Date date1 = (Date) departdate.getDate();
		        Date date2 = (Date) returndate.getDate();
		        Date ogdate = (Date) origdate.getDate();
		        // Check if date2 is on or past date1
		        if (date2.compareTo(date1) <= 0) {
		            JOptionPane.showMessageDialog(null, "Error: The selected date is on or behind the first date.", "Date Error", JOptionPane.ERROR_MESSAGE);
		        }else{
		            returning_content.setText("<html>"+a+" to"+"<br>"+b+"<br>"+dateString+"</html>");
		        }
			}
        });

        wherefrom.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                	if (departure_content.getText().isEmpty()){
                		departure_content.setText("");
                	}else{
                		departure_content.setText("");
                		returning_content.setText("");
                		JOptionPane.showMessageDialog(frame,"Please reselect the date to confirm");
                	}
                }
            }
        });

        whereto.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                	if (departure_content.getText().isEmpty()){
                		departure_content.setText("");
                	}else{
                		departure_content.setText("");
                		returning_content.setText("");
                		JOptionPane.showMessageDialog(frame,"Please reselect the date to confirm");
                	}
                }
            }
        });

		frame1back.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				homeFrame.setVisible(true);
				frame.setVisible(false);
			}
		});

		search.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				Date date1 = (Date) departdate.getDate();
		        Date date2 = (Date) returndate.getDate();
		        Date ogdate = (Date) origdate.getDate();
		        String x = guest_content.getText();
		        String y = departure_content.getText();
		        String z = returning_content.getText();
				if(!rtrip.isSelected() && !oneway.isSelected()){
					JOptionPane.showMessageDialog(frame,"Choose a flight type","ERROR",JOptionPane.ERROR_MESSAGE);
				}else if(wherefrom.getSelectedItem().equals(whereto.getSelectedItem())){
					JOptionPane.showMessageDialog(frame,"Invalid Departure/Return Location","ERROR",JOptionPane.ERROR_MESSAGE);
				}else if (oneway.isSelected()){
					if (x.isEmpty()||y.isEmpty()){
						JOptionPane.showMessageDialog(frame,"Complete the details","ERROR",JOptionPane.ERROR_MESSAGE);
					}else{
						frame2.setVisible(true);
						frame.setVisible(false);
					}
				}else if (date1.compareTo(date2) >= 0) {
		            JOptionPane.showMessageDialog(null, "Invalid Date", "Date Error", JOptionPane.ERROR_MESSAGE);
				}else if (oneway.isSelected()&&date2.equals(date1)) {
					frame2.setVisible(true);
					frame.setVisible(false);
				}else if (x.isEmpty()||y.isEmpty()||z.isEmpty()){
					JOptionPane.showMessageDialog(frame,"Complete the details","ERROR",JOptionPane.ERROR_MESSAGE);
				}else{
					frame2.setVisible(true);
					frame.setVisible(false);

				}
			}
		});

	//LISTENER (FRAME 2)

        frame2.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentShown(ComponentEvent e) {
                if (oneway.isSelected()) {
                    table_returningflight.setEnabled(false);
                    //table_returningflight.clearSelection();
                    select_returning.setBounds(210,220,400,25);
                    select_returning.setText("*Not available for one-way flight");
                } else {
                    table_returningflight.setEnabled(true);
                    select_returning.setBounds(210,220,200,25);
                    select_returning.setText("Select your returning flight");
                }
            }
        });

		table_departureflight.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
	            if (rtrip.isSelected()) {
	                if (table_departureflight.getSelectedRow() != -1 && table_returningflight.getSelectedRow() != -1) {
	                    rb_bundle1.setEnabled(true);
	                    rb_bundle2.setEnabled(true);
	                    rb_bundle3.setEnabled(true);
	                }else{
	                    rb_bundle1.setEnabled(false);
	                    rb_bundle2.setEnabled(false);
	                    rb_bundle3.setEnabled(false);
	                }
	            }

	            if (oneway.isSelected()) {
	                if (table_departureflight.getSelectedRow() != -1) {
	                    rb_bundle1.setEnabled(true);
	                    rb_bundle2.setEnabled(true);
	                    rb_bundle3.setEnabled(true);
	                }else{
	                    rb_bundle1.setEnabled(false);
	                    rb_bundle2.setEnabled(false);
	                    rb_bundle3.setEnabled(false);
	                }
	            }
			}
		});

		table_returningflight.addMouseListener(new MouseAdapter() {
		    public void mouseClicked(MouseEvent e) {
		        if (rtrip.isSelected()) {
		            if (table_departureflight.getSelectedRow() != -1 && table_returningflight.getSelectedRow() != -1) {
		                rb_bundle1.setEnabled(true);
		                rb_bundle2.setEnabled(true);
		                rb_bundle3.setEnabled(true);
		            } else {
		                rb_bundle1.setEnabled(false);
		                rb_bundle2.setEnabled(false);
		                rb_bundle3.setEnabled(false);
		            }
		        }

		        // No need to check for oneway here because it doesn't depend on table_returningflight
		    }
		});

		rb_bundle1.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				for (JCheckBox checkbox : checkBoxes) {
				    checkbox.setSelected(false);
				}
			}
		});

		rb_bundle2.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				for (JCheckBox checkbox : checkBoxes) {
				    checkbox.setSelected(false);
				}
			}
		});

		rb_bundle3.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				//DEPARTURE TAKEN SEATS
		        try {
		        	java.util.Date selectedDate = departdate.getDate();
		        	SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
					String formattedDate = formatter.format(selectedDate);

					String selectedPlace = wherefrom.getSelectedItem().toString();

					int selectedRow = table_departureflight.getSelectedRow();
					Object depart_time_obj = null;
					Object arrive_time_obj = null;

					if (selectedRow >= 0) {
					    depart_time_obj = table_departureflight.getValueAt(selectedRow, 0);
					    arrive_time_obj = table_departureflight.getValueAt(selectedRow, 1);
					}

					int selectedRow2 = table_returningflight.getSelectedRow();
					Object depart_time2_obj = null;
					Object arrive_time2_obj = null;

					if (selectedRow2 >= 0) {
					    depart_time2_obj = table_returningflight.getValueAt(selectedRow2, 0);
					    arrive_time2_obj = table_returningflight.getValueAt(selectedRow2, 1);
					}

					SimpleDateFormat displayFormat = new SimpleDateFormat("HH:mm:ss");
					SimpleDateFormat parseFormat = new SimpleDateFormat("hh:mm a");
					Date date1 = null;
					Date date2 = null;
					Date date3 = null;
					Date date4 = null;

					String dep_time = "N/A", arr_time = "N/A", dep_time2 = "N/A", arr_time2 = "N/A";
					depart_time = "N/A";
					return_time = "N/A";

					if (depart_time_obj != null) {
					    date1 = parseFormat.parse((String) depart_time_obj);
					}
					if (arrive_time_obj != null) {
					    date2 = parseFormat.parse((String) arrive_time_obj);
					}
					if (date1 != null) {
					    dep_time = displayFormat.format(date1);
					}
					if (date2 != null) {
					    arr_time = displayFormat.format(date2);
					}
					depart_time = dep_time + " - " + arr_time;

					if (depart_time2_obj != null) {
					    date3 = parseFormat.parse((String) depart_time2_obj);
					}
					if (arrive_time2_obj != null) {
					    date4 = parseFormat.parse((String) arrive_time2_obj);
					}
					if (date3 != null) {
					    dep_time2 = displayFormat.format(date3);
					}
					if (date4 != null) {
					    arr_time2 = displayFormat.format(date4);
					}
					return_time = dep_time2 + " - " + arr_time2;

					String selectedTime = depart_time;
					System.out.println("Departure: "+" "+selectedPlace+" "+formattedDate+" "+depart_time);

		            Class.forName("com.mysql.cj.jdbc.Driver");
		            Connection seats_con = DriverManager.getConnection(url, username, password);
					String query = "SELECT * FROM transaction WHERE DepartDate = ? AND DepartPlace = ? AND DepartureTime = ?";
					PreparedStatement seats_stmt = seats_con.prepareStatement(query);
					seats_stmt.setString(1, formattedDate);
					seats_stmt.setString(2, selectedPlace);
					seats_stmt.setString(3, selectedTime);
					ResultSet seats_rs = seats_stmt.executeQuery();

					A1.setEnabled(true); A1.setIcon(seatDeselected); A2.setEnabled(true); A2.setIcon(seatDeselected);
					A3.setEnabled(true); A3.setIcon(seatDeselected); A4.setEnabled(true); A4.setIcon(seatDeselected);
					A5.setEnabled(true); A5.setIcon(seatDeselected);

					B1.setEnabled(true); B1.setIcon(seatDeselected); B2.setEnabled(true); B2.setIcon(seatDeselected);
					B3.setEnabled(true); B3.setIcon(seatDeselected); B4.setEnabled(true); B4.setIcon(seatDeselected);
					B5.setEnabled(true); B5.setIcon(seatDeselected);

					C1.setEnabled(true); C1.setIcon(seatDeselected); C2.setEnabled(true); C2.setIcon(seatDeselected);
					C3.setEnabled(true); C3.setIcon(seatDeselected); C4.setEnabled(true); C4.setIcon(seatDeselected);
					C5.setEnabled(true); C5.setIcon(seatDeselected);

					D1.setEnabled(true); D1.setIcon(seatDeselected); D2.setEnabled(true); D2.setIcon(seatDeselected);
					D3.setEnabled(true); D3.setIcon(seatDeselected); D4.setEnabled(true); D4.setIcon(seatDeselected);
					D5.setEnabled(true); D5.setIcon(seatDeselected);

		            boolean allSeatsTaken = false;  // Initialize to false
		            List<String> allTakenSeats = new ArrayList<>();

		            boolean allRetSeatsTaken = false;  // Initialize to false
		            List<String> allTakenRetSeats = new ArrayList<>();

		            taken_seats = new ArrayList<>();

		            while(seats_rs.next()) {
		            	allTakenSeats.clear();
		            	allTakenRetSeats.clear();
                        // DEPARTURE TAKEN SEATS
                        String fromdb_seats = seats_rs.getString("SeatsDeparture");  // This should be retrieved from your SQL table

					    // Add null check before processing the result set values
					    if (fromdb_seats != null) {
	                        System.out.println("SeatsDeparture: " + fromdb_seats);  // Print the content of SeatsDeparture
	                        fromdb_seats = fromdb_seats.substring(1, fromdb_seats.length() - 1);  // Remove the brackets
	                        String[] seats = fromdb_seats.split(",");
	                        for (int i = 0; i < seats.length; i++) {
	                            seats[i] = seats[i].trim();  // Remove leading and trailing spaces
	                        }
	                        taken_seats.addAll(Arrays.asList(seats));  // Use addAll to append to the existing list
	                        int sizetaken = taken_seats.size();
	                        System.out.println("Taken seats: " + sizetaken);

	                        allTakenSeats.addAll(taken_seats);

					        /*if (oneway.isSelected()) {
					            // Disable the checkboxes for the taken seats for one-way flights
					            for (String seat : taken_seats) {
					                disableSeatBySeatName(seat, true);
					            }
					        } else if (rtrip.isSelected()) {
					            // Disable the checkboxes for the taken departure seats before btn_seat is clicked
					            if (!btn_seatClicked) {
					                for (String seat : taken_seats) {
					                    disableSeatBySeatName(seat, true);
					                }
					            } else {
					                // Show only the returning seats when btn_seat is clicked
					                for (String retseat : taken_retseats) {
					                    disableSeatBySeatName(retseat, false);
					                }
					            }
					        }*/

						    // Check if all seats are taken
						    if (allTakenSeats.size() == 20) {
						        allSeatsTaken = true;
						        // Optionally break the loop if you want to stop checking other flights when you find a full one
						        break;
						    }

							// Check if all seats are taken
	                        if (allTakenRetSeats.size() == 20) {
	                            allRetSeatsTaken = true;
	                            // Optionally break the loop if you want to stop checking other flights when you find a full one
	                            break;
	                        }
					    }
		            }

		            seats_con.close();

		        } catch(Exception a) {
		            System.out.println(a);
		        }

				//RETURNING TAKEN SEATS
		        try {
		        	java.util.Date selectedDate = returndate.getDate();
		        	SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
					String formattedDate = formatter.format(selectedDate);

					String selectedPlace = whereto.getSelectedItem().toString();

					int selectedRow = table_departureflight.getSelectedRow();
					Object depart_time_obj = null;
					Object arrive_time_obj = null;

					if (selectedRow >= 0) {
					    depart_time_obj = table_departureflight.getValueAt(selectedRow, 0);
					    arrive_time_obj = table_departureflight.getValueAt(selectedRow, 1);
					}

					int selectedRow2 = table_returningflight.getSelectedRow();
					Object depart_time2_obj = null;
					Object arrive_time2_obj = null;

					if (selectedRow2 >= 0) {
					    depart_time2_obj = table_returningflight.getValueAt(selectedRow2, 0);
					    arrive_time2_obj = table_returningflight.getValueAt(selectedRow2, 1);
					}

					SimpleDateFormat displayFormat = new SimpleDateFormat("HH:mm:ss");
					SimpleDateFormat parseFormat = new SimpleDateFormat("hh:mm a");
					Date date1 = null;
					Date date2 = null;
					Date date3 = null;
					Date date4 = null;

					String dep_time = "N/A", arr_time = "N/A", dep_time2 = "N/A", arr_time2 = "N/A";
					depart_time = "N/A";
					return_time = "N/A";

					if (depart_time_obj != null) {
					    date1 = parseFormat.parse((String) depart_time_obj);
					}
					if (arrive_time_obj != null) {
					    date2 = parseFormat.parse((String) arrive_time_obj);
					}
					if (date1 != null) {
					    dep_time = displayFormat.format(date1);
					}
					if (date2 != null) {
					    arr_time = displayFormat.format(date2);
					}
					depart_time = dep_time + " - " + arr_time;

					if (depart_time2_obj != null) {
					    date3 = parseFormat.parse((String) depart_time2_obj);
					}
					if (arrive_time2_obj != null) {
					    date4 = parseFormat.parse((String) arrive_time2_obj);
					}
					if (date3 != null) {
					    dep_time2 = displayFormat.format(date3);
					}
					if (date4 != null) {
					    arr_time2 = displayFormat.format(date4);
					}
					return_time = dep_time2 + " - " + arr_time2;

					String selectedTime = return_time;
					System.out.println("Returning: "+" "+selectedPlace+" "+formattedDate+" "+return_time);

		            Class.forName("com.mysql.cj.jdbc.Driver");
		            Connection seats_con = DriverManager.getConnection(url, username, password);
					String query = "SELECT * FROM transaction WHERE ReturnDate = ? AND ArrivePlace = ? AND ReturningTime = ?";
					PreparedStatement seats_stmt = seats_con.prepareStatement(query);
					seats_stmt.setString(1, formattedDate);
					seats_stmt.setString(2, selectedPlace);
					seats_stmt.setString(3, selectedTime);
					ResultSet seats_rs = seats_stmt.executeQuery();

					A1.setEnabled(true); A1.setIcon(seatDeselected); A2.setEnabled(true); A2.setIcon(seatDeselected);
					A3.setEnabled(true); A3.setIcon(seatDeselected); A4.setEnabled(true); A4.setIcon(seatDeselected);
					A5.setEnabled(true); A5.setIcon(seatDeselected);

					B1.setEnabled(true); B1.setIcon(seatDeselected); B2.setEnabled(true); B2.setIcon(seatDeselected);
					B3.setEnabled(true); B3.setIcon(seatDeselected); B4.setEnabled(true); B4.setIcon(seatDeselected);
					B5.setEnabled(true); B5.setIcon(seatDeselected);

					C1.setEnabled(true); C1.setIcon(seatDeselected); C2.setEnabled(true); C2.setIcon(seatDeselected);
					C3.setEnabled(true); C3.setIcon(seatDeselected); C4.setEnabled(true); C4.setIcon(seatDeselected);
					C5.setEnabled(true); C5.setIcon(seatDeselected);

					D1.setEnabled(true); D1.setIcon(seatDeselected); D2.setEnabled(true); D2.setIcon(seatDeselected);
					D3.setEnabled(true); D3.setIcon(seatDeselected); D4.setEnabled(true); D4.setIcon(seatDeselected);
					D5.setEnabled(true); D5.setIcon(seatDeselected);

		            boolean allSeatsTaken = false;  // Initialize to false
		            List<String> allTakenSeats = new ArrayList<>();

		            boolean allRetSeatsTaken = false;  // Initialize to false
		            List<String> allTakenRetSeats = new ArrayList<>();

		            taken_retseats = new ArrayList<>();

		            while(seats_rs.next()) {
		            	allTakenSeats.clear();
		            	allTakenRetSeats.clear();

                        // RETURNING TAKEN SEATS
                      	String fromdb_retseats = seats_rs.getString("SeatsReturning");  // This should be retrieved from your SQL table

                      	if (fromdb_retseats != null) {
	                        System.out.println("SeatsReturning: " + fromdb_retseats);  // Print the content of SeatsDeparture
	                        fromdb_retseats = fromdb_retseats.substring(1, fromdb_retseats.length() - 1);  // Remove the brackets
	                        String[] ret_seats = fromdb_retseats.split(",");
	                        for (int i = 0; i < ret_seats.length; i++) {
	                            ret_seats[i] = ret_seats[i].trim();  // Remove leading and trailing spaces
	                        }
	                        taken_retseats.addAll(Arrays.asList(ret_seats));  // Use addAll to append to the existing list
	                        int retsizetaken = taken_retseats.size();
	                        System.out.println("Taken seats: " + retsizetaken);

	                        allTakenRetSeats.addAll(taken_retseats);

					        /*if (oneway.isSelected()) {
					            // Disable the checkboxes for the taken seats for one-way flights
					            for (String seat : taken_seats) {
					                disableSeatBySeatName(seat, true);
					            }
					        } else if (rtrip.isSelected()) {
					            // Disable the checkboxes for the taken departure seats before btn_seat is clicked
					            if (!btn_seatClicked) {
					                for (String seat : taken_seats) {
					                    disableSeatBySeatName(seat, true);
					                }
					            } else {
					                // Show only the returning seats when btn_seat is clicked
					                for (String retseat : taken_retseats) {
					                    disableSeatBySeatName(retseat, false);
					                }
					            }
					        }*/

						    // Check if all seats are taken
						    if (allTakenSeats.size() == 20) {
						        allSeatsTaken = true;
						        // Optionally break the loop if you want to stop checking other flights when you find a full one
						        break;
						    }

							// Check if all seats are taken
	                        if (allTakenRetSeats.size() == 20) {
	                            allRetSeatsTaken = true;
	                            // Optionally break the loop if you want to stop checking other flights when you find a full one
	                            break;
	                        }
                        } else {
						    // Handle the case where SeatsReturning is null (assuming no seats are taken)
						    // For example, you can initialize taken_retseats as an empty list
						    taken_retseats = new ArrayList<>();
                        }
		            }

		            seats_con.close();

		        } catch(Exception a) {
		            System.out.println(a);
		        }

		        // Display message outside the try-catch blocks
		        if (!bundle3optionpane) {
		            try {
		                limit = Integer.parseInt(guestno.getText());
		                if (limit == 1) {
		                    JOptionPane.showMessageDialog(null, "You can select up to " + limit + " seat.");
		                } else {
		                    JOptionPane.showMessageDialog(null, "You can select up to " + limit + " seats.");
		                }
		            } catch (NumberFormatException ex) {
		                JOptionPane.showMessageDialog(null, "Please enter a valid number.");
		            }
		            frame_seats.setVisible(true);
		        }

			}
		});

		frame2btn_back.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				frame.setVisible(true);
				frame2.setVisible(false);
			}
		});

        JCheckBox[] checkboxes_count = new JCheckBox[checkBoxes.length];
        for (int i = 0; i < checkBoxes.length; i++) {
            checkboxes_count[i] = (checkBoxes[i]);
        }

		frame2btn_continue.addActionListener(new ActionListener() {
		    @Override
		    public void actionPerformed(ActionEvent e) {
		    	//BUNDLE 1 OR 2
		    	if (rb_bundle1.isSelected() || rb_bundle2.isSelected()) {
			    	try {
			        	java.util.Date selectedDate = departdate.getDate();
			        	SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
						String formattedDate = formatter.format(selectedDate);

						String selectedPlace = wherefrom.getSelectedItem().toString();

						int selectedRow = table_departureflight.getSelectedRow();
						Object depart_time_obj = null;
						Object arrive_time_obj = null;

						if (selectedRow >= 0) {
						    depart_time_obj = table_departureflight.getValueAt(selectedRow, 0);
						    arrive_time_obj = table_departureflight.getValueAt(selectedRow, 1);
						}

						SimpleDateFormat displayFormat = new SimpleDateFormat("HH:mm:ss");
						SimpleDateFormat parseFormat = new SimpleDateFormat("hh:mm a");
						Date date1 = null;
						Date date2 = null;

						String dep_time = "N/A", arr_time = "N/A";
						depart_time = "N/A";
						return_time = "N/A";

						if (depart_time_obj != null) {
						    date1 = parseFormat.parse((String) depart_time_obj);
						}
						if (arrive_time_obj != null) {
						    date2 = parseFormat.parse((String) arrive_time_obj);
						}
						if (date1 != null) {
						    dep_time = displayFormat.format(date1);
						}
						if (date2 != null) {
						    arr_time = displayFormat.format(date2);
						}
						depart_time = dep_time + " - " + arr_time;

						String selectedTime = depart_time;
						System.out.println("Departure: "+selectedPlace+formattedDate+depart_time);

			            Class.forName("com.mysql.cj.jdbc.Driver");
			            Connection seats_con = DriverManager.getConnection(url, username, password);
						String query = "SELECT * FROM transaction WHERE DepartDate = ? AND DepartPlace = ? AND DepartureTime = ?";
						PreparedStatement seats_stmt = seats_con.prepareStatement(query);
						seats_stmt.setString(1, formattedDate);
						seats_stmt.setString(2, selectedPlace);
						seats_stmt.setString(3, selectedTime);
						ResultSet seats_rs = seats_stmt.executeQuery();

			            boolean allSeatsTaken = false;  // Initialize to false
			            List<String> allTakenSeats = new ArrayList<>();

			            boolean allRetSeatsTaken = false;  // Initialize to false
			            List<String> allTakenRetSeats = new ArrayList<>();

			            boolean dialogsShown = false;

			            limit = 0;

			            while(seats_rs.next()) {
			            	//DEPARTURE TAKEN SEATS
							String fromdb_seats = seats_rs.getString("SeatsDeparture");  // This should be retrieved from your SQL table
							System.out.println("SeatsDeparture: " + fromdb_seats);  // Print the content of SeatsDeparture
							fromdb_seats = fromdb_seats.substring(1, fromdb_seats.length()-1);  // Remove the brackets
							String[] seats = fromdb_seats.split(",");
							for (int i = 0; i < seats.length; i++) {
							    seats[i] = seats[i].trim();  // Remove leading and trailing spaces
							}
							taken_seats = new ArrayList<>(Arrays.asList(seats));
							int sizetaken = taken_seats.size();
							System.out.println("Taken seats: "+sizetaken);

							allTakenSeats.addAll(taken_seats);

							//RETURNING TAKEN SEATS
							String fromdb_retseats = seats_rs.getString("SeatsReturning");  // This should be retrieved from your SQL table
							System.out.println("SeatsReturning: " + fromdb_retseats);  // Print the content of SeatsDeparture
							fromdb_retseats = fromdb_retseats.substring(1, fromdb_retseats.length()-1);  // Remove the brackets
							String[] ret_seats = fromdb_retseats.split(",");
							for (int i = 0; i < ret_seats.length; i++) {
							    ret_seats[i] = ret_seats[i].trim();  // Remove leading and trailing spaces
							}
							taken_retseats = new ArrayList<>(Arrays.asList(ret_seats));
							int retsizetaken = taken_retseats.size();
							System.out.println("Taken seats: "+retsizetaken);

							allTakenRetSeats.addAll(taken_retseats);

						    // Check if all seats are taken
						    if (allTakenSeats.size() == 20) {
						        allSeatsTaken = true;
						        // Optionally break the loop if you want to stop checking other flights when you find a full one
						        break;
						    }

						    // Check if all seats are taken
						    if (allTakenRetSeats.size() == 20) {
						        allRetSeatsTaken = true;
						        // Optionally break the loop if you want to stop checking other flights when you find a full one
						        break;
						    }

						    int totalSeats = 20;  // Adjust this based on your total number of seats
							int remainingSeats = totalSeats - allTakenSeats.size();
							int remainingRetSeats = totalSeats - allTakenRetSeats.size();
							limit = Integer.parseInt(guestno.getText());

							// Check if the number of guests is greater than the remaining seats
							if (limit > 0 && limit > remainingSeats) {
							    JOptionPane.showMessageDialog(frame, "Number of guests exceeds available seats. Please choose a smaller number of guests.", "Invalid Number of Guests", JOptionPane.ERROR_MESSAGE);
							    return;  // Stop further processing if the number of guests exceeds available seats
							}

							if (limit > 0 && limit > remainingRetSeats) {
							    JOptionPane.showMessageDialog(frame, "Number of guests exceeds available seats. Please choose a smaller number of guests.", "Invalid Number of Guests", JOptionPane.ERROR_MESSAGE);
							    return;  // Stop further processing if the number of guests exceeds available seats
							}

						    if (rb_bundle1.isSelected() || rb_bundle2.isSelected()) {
					            int limit = Integer.parseInt(guestno.getText());
					            Random rand = new Random();
					            selected_depart = new LinkedHashSet<>();
					            selected_return = new LinkedHashSet<>();

					            while (selected_depart.size() < limit) {
									int randomIndex = rand.nextInt(checkBoxes.length);
									String seat = checkBoxes[randomIndex].getName();

									if (!allTakenSeats.contains(seat)) {
									    selected_depart.add(seat);
									}
					            }

					            while (selected_return.size() < limit) {
									int randomIndex = rand.nextInt(checkBoxes.length);
									String seat = checkBoxes[randomIndex].getName();

									if (!allTakenRetSeats.contains(seat)) {
									    selected_return.add(seat);
									}
					            }
					        }

			            }


			            if (rtrip.isSelected()) {
			            	if (!dialogsShown && !allSeatsTaken && !allRetSeatsTaken) {
				                if (table_departureflight.getSelectedRow() != -1 && table_returningflight.getSelectedRow() != -1) {
				                    JOptionPane.showMessageDialog(frame, "Your seat for departure: " + String.join(", ", selected_depart) +
				                            "\nYour seat for returning: " + String.join(", ", selected_return));
				                    guestFrame.setVisible(true);
				                    frame2.setVisible(false);
				                }
			            	}
			            }

			            if (oneway.isSelected()) {
			            	if (!dialogsShown && !allSeatsTaken && !allRetSeatsTaken) {
				                if (table_departureflight.getSelectedRow() != -1) {
				                    JOptionPane.showMessageDialog(frame, "Your seat is: " + String.join(", ", selected_depart));
				                    guestFrame.setVisible(true);
				                    frame2.setVisible(false);
				                }
			            	}
			            }

			            seats_con.close();

			            if (allSeatsTaken) {
					        JOptionPane.showMessageDialog(null, "All seats are taken for the selected flight.", "No Available Seats", JOptionPane.INFORMATION_MESSAGE);
					    }

			        } catch(Exception a) {
			            System.out.println(a);
			        }
		    	}

		    	//BUNDLE 3
		    	if (rb_bundle3.isSelected()) {
			        int depart_price_row = table_departureflight.getSelectedRow();

			        if (depart_price_row != -1) {
			            int checkedCount = 0;
			            int count = Integer.parseInt(guestno.getText());

			            for (JCheckBox checkBox : checkBoxes) {
			                if (checkBox.isSelected()) {
			                    checkedCount++;
			                }
			            }

			            if (!bundle3optionpane) {
			                if (rtrip.isSelected() && table_returningflight.getSelectedRow() == -1) {
			                    JOptionPane.showMessageDialog(null, "Please select a returning flight.");
			                } else if (!rb_bundle3.isSelected()) {
			                    JOptionPane.showMessageDialog(null, "Please select a flight bundle.");
			                } else if (rb_bundle3.isSelected()) {
			                    if (checkedCount == 0) {
			                        JOptionPane.showMessageDialog(null, "Please select " + count + " seat" + (count > 1 ? "s" : "") + ".");
			                    } else if (checkedCount != count) {
			                        JOptionPane.showMessageDialog(null, "Please select exactly " + count + " seat" + (count > 1 ? "s" : "") + ".");
			                    } else {
			                        try {
			                        	Date selectedDate = departdate.getDate();
		                                String formattedDate = formatDate(selectedDate);
		                                String selectedPlace = wherefrom.getSelectedItem().toString();
		                                Object depart_time_obj = table_departureflight.getValueAt(table_departureflight.getSelectedRow(), 0);
		                                Object arrive_time_obj = table_departureflight.getValueAt(table_departureflight.getSelectedRow(), 1);
		                                String selectedTime = formatTime((String) depart_time_obj, (String) arrive_time_obj);

			                            Class.forName("com.mysql.cj.jdbc.Driver");
			                            Connection seats_con = DriverManager.getConnection(url, username, password);
			                            String query = "SELECT * FROM transaction WHERE DepartDate = ? AND DepartPlace = ? AND DepartureTime = ?";
			                            PreparedStatement seats_stmt = seats_con.prepareStatement(query);
			                            seats_stmt.setString(1, formattedDate);
			                            seats_stmt.setString(2, selectedPlace);
			                            seats_stmt.setString(3, selectedTime);
			                            ResultSet seats_rs = seats_stmt.executeQuery();

		                                List<String> allTakenSeats = new ArrayList<>();
		                                List<String> allTakenRetSeats = new ArrayList<>();

		                                boolean allSeatsTaken = processSeatsResultSet(seats_rs, allTakenSeats, allTakenRetSeats);

		                                int remainingSeats = 20 - allTakenSeats.size();
		                                int remainingRetSeats = 20 - allTakenRetSeats.size();

		                                if (limit > 0 && limit > remainingSeats) {
		                                    JOptionPane.showMessageDialog(null, "Number of guests exceeds available seats. Please choose a smaller number of guests.", "Invalid Number of Guests", JOptionPane.ERROR_MESSAGE);
		                                    return;
		                                }

		                                if (limit > 0 && limit > remainingRetSeats) {
		                                    JOptionPane.showMessageDialog(null, "Number of guests exceeds available seats. Please choose a smaller number of guests.", "Invalid Number of Guests", JOptionPane.ERROR_MESSAGE);
		                                    return;
		                                }

			                            seats_con.close();

			                            if (allSeatsTaken) {
			                                JOptionPane.showMessageDialog(null, "All seats are taken for the selected flight.", "No Available Seats", JOptionPane.INFORMATION_MESSAGE);
			                            } else {
		                                    guestFrame.setVisible(true);
		                                    frame2.setVisible(false);
		                                    return;  // Added return to prevent the code below from being executed unnecessarily
		                                }

			                        } catch (Exception a) {
			                            System.out.println("Exception caught: " + a.getMessage());
			                            a.printStackTrace(); // Print the stack trace for detailed information
			                        }
			                    }
			                }
			            } else if (checkedCount == 0){
			            	if (!rb_bundle1.isSelected() && !rb_bundle2.isSelected()){
			            		JOptionPane.showMessageDialog(null, "Please select " + limit + " seat.");
			            	}else{
			            		guestFrame.setVisible(true);
		                    	frame2.setVisible(false);
			            	}
			            }else {
		                    guestFrame.setVisible(true);
		                    frame2.setVisible(false);
			            }
			        }
		    	}

				if (rtrip.isSelected()) {
				    if (table_departureflight.getSelectedRow() == -1 || table_returningflight.getSelectedRow() == -1) {
				        JOptionPane.showMessageDialog(null, "Please select both a departure and returning flight for round trip.");
				    } else if (!rb_bundle1.isSelected() && !rb_bundle2.isSelected() && !rb_bundle3.isSelected()) {
				        JOptionPane.showMessageDialog(null, "Please select a bundle.");
				    }
				} else if (oneway.isSelected()) {
				    if (table_departureflight.getSelectedRow() == -1) {
				        JOptionPane.showMessageDialog(null, "Please select a departure flight for one way.");
				    } else if (!rb_bundle1.isSelected() && !rb_bundle2.isSelected() && !rb_bundle3.isSelected()) {
				        JOptionPane.showMessageDialog(null, "Please select a bundle.");
				    }
				}
		    }
		});

	//LISTENER (FRAME 2.5)
		AtomicBoolean isDeparture = new AtomicBoolean(true);  // Start with departure seat selection
		departureSeats = new ArrayList<>();
		returnSeats = new ArrayList<>();

		rb_bundle3.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        // Reset the selected seats and the isDeparture flag
		        departureSeats.clear();
		        returnSeats.clear();
		        isDeparture.set(true);

		        // Deselect all checkboxes
		        for (JCheckBox checkBox : checkBoxes) {
		            checkBox.setSelected(false);
		        }
		    }
		});

		frame_seats.addComponentListener(new ComponentAdapter() {
		    @Override
		    public void componentShown(ComponentEvent e) {
		        // Disable the checkboxes for the taken departure seats
		        if (!rtrip.isSelected() && taken_seats != null) {
		            for (String seat : taken_seats) {
		                disableSeatBySeatName(seat, true);
		            }
		        } else if (rtrip.isSelected() && btn_seatClicked && taken_retseats != null) {
		            // Show only the returning seats when btn_seat is clicked
		            for (String retseat : taken_retseats) {
		                disableSeatBySeatName(retseat, false);
		            }
		        }
		    }
		});

		frame_seats.addComponentListener(new ComponentAdapter() {
		    @Override
		    public void componentShown(ComponentEvent e) {

				btn_seat.addActionListener(new ActionListener() {
				    public void actionPerformed(ActionEvent e) {
				        // When btn_seat is clicked, set the flag to true
				        btn_seatClicked = true;

				        // Hide all taken departure seats
				        if (taken_seats != null) {
				            for (String seat : taken_seats) {
				                disableSeatBySeatName(seat, true);
				            }
				        }

				        // Show only the taken returning seats
				        if (taken_retseats != null) {
				            for (String retseat : taken_retseats) {
				                disableSeatBySeatName(retseat, false);
				            }
				        }
				    }
				});

		        if (rtrip.isSelected()) {
		            // Show all the taken departure seats
		            for (String seat : taken_seats) {
		                disableSeatBySeatName(seat, false);
		            }

		            // Disable the checkboxes for the taken departure seats before btn_seat is clicked
		            if (btn_seatClicked) {
		                // Hide all taken departure seats
		                boolean isequal = taken_seats.equals(taken_retseats);

		                if(isequal){
			                for (String seat : taken_seats) {
			                    disableSeatBySeatName(seat, true);
			                }
		                }

		                // Show only the returning seats when btn_seat is clicked
		                for (String retseat : taken_retseats) {
		                    disableSeatBySeatName(retseat, false);
		                }
		            }
		        }
		    }
		});

		btn_seat.addActionListener(new ActionListener(){
		    public void actionPerformed(ActionEvent e){
		        int checkedCount = 0;
		        ArrayList<String> selectedSeats = new ArrayList<>();
		        for (JCheckBox checkBox : checkBoxes) {
		            if (checkBox.isSelected()) {
		                checkedCount++;
		                selectedSeats.add(checkBox.getName());
		            }
		        }
		        if (checkedCount == limit) {
		            if (isDeparture.get()) {
		                departureSeats.clear();
		                departureSeats.addAll(selectedSeats);
		                if (rtrip.isSelected()) {
		                    isDeparture.set(false);  // Switch to return seat selection
		                    // Deselect all checkboxes
		                    for (JCheckBox checkBox : checkBoxes) {
		                        checkBox.setSelected(false);
		                    }
		                    JOptionPane.showMessageDialog(null, "Your selected departure seats: " + String.join(", ", departureSeats) + ". \nPlease select your return seats.");
		                } else {
		                    JOptionPane.showMessageDialog(null, "Your selected seats: " + String.join(", ", departureSeats));
		                    frame_seats.setVisible(false);
		                }
		            } else {
		                returnSeats.clear();
		                returnSeats.addAll(selectedSeats);
		                JOptionPane.showMessageDialog(null, "Your selected departure seats: " + String.join(", ", departureSeats) + ". \nYour selected return seats: " + String.join(", ", returnSeats));
		                frame_seats.setVisible(false);
		            }
		        } else {
		            if (limit == 1){
		                JOptionPane.showMessageDialog(null, "Please select " + limit + " seat.");
		            } else {
		                JOptionPane.showMessageDialog(null, "Please select " + limit + " seats.");
		            }
		        }
		    }
		});


	//LISTENER FRAME 3

    	policy_chb.addActionListener(new ActionListener(){
    		public void actionPerformed(ActionEvent e){

    			if (policy_chb.isSelected()){
    				cont_btn.setEnabled(true);
				} else {
    				cont_btn.setEnabled(false);
				}

    		}
    	});

    	cont_btn.addActionListener(new ActionListener() {
    		public void actionPerformed(ActionEvent e) {
    			Date bdate = (Date) birthdate.getDate();
    			fname = fname_tf.getText();
    			mname = mname_tf.getText();
		        lname = lname_tf.getText();
		        contact = contactNum_tf.getText();
		        email = email_tf.getText();

		        boolean isValidInput = true; // Add this line

		        if (!fname.matches("[a-zA-Z]+")){
		            JOptionPane.showMessageDialog(guestFrame, "Invalid first name", "Invalid Input", JOptionPane.ERROR_MESSAGE);
		            isValidInput = false; // Add this line
		        }

		        /*if (!mname.matches("[a-zA-Z]+")){
		            JOptionPane.showMessageDialog(guestFrame, "Invalid middle name", "Invalid Input", JOptionPane.ERROR_MESSAGE);
		            isValidInput = false; // Add this line
		        }*/

		        if (!lname.matches("[a-zA-Z]+")){
		            JOptionPane.showMessageDialog(guestFrame, "Invalid last name", "Invalid Input", JOptionPane.ERROR_MESSAGE);
		            isValidInput = false; // Add this line
		        }

		        if (!contact.matches("\\d{11}")){
		            JOptionPane.showMessageDialog(guestFrame, "Invalid contact number", "Invalid Input", JOptionPane.ERROR_MESSAGE);
		            isValidInput = false; // Add this line
		        }

		        if (!email.matches("[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}")){
		            JOptionPane.showMessageDialog(guestFrame, "Invalid email", "Invalid Input", JOptionPane.ERROR_MESSAGE);
		            isValidInput = false; // Add this line
		        }

    			if (fname.isEmpty() || lname.isEmpty() || contact.isEmpty() || email.isEmpty()) {
					JOptionPane.showMessageDialog(guestFrame, "Please complete all the necessary details. Thank you.", "Error", JOptionPane.ERROR_MESSAGE);
    				isValidInput = false;
    			}

    			if (isValidInput){
    				String no_of_guests = guestno.getText();
    				guestname = new String[Integer.parseInt(no_of_guests)];
    				int guest = Integer.parseInt(no_of_guests);

					if(guest > 1){
					    for(int i = 1; i < guest; i++){
					        do {
					            guestname[i] = JOptionPane.showInputDialog(guestFrame,"Enter name of Guest "+(i+1));
					            if(guestname[i]==null){
					                // User clicked cancel, exit the loop
					                break;
					            } else if(guestname[i].trim().isEmpty()){
					                JOptionPane.showMessageDialog(guestFrame, "Enter a name.", "Error", JOptionPane.ERROR_MESSAGE);
					            }
					        } while (guestname[i]==null||guestname[i].trim().isEmpty());
					        if(guestname[i]==null){
					            // User clicked cancel, exit the loop
					            break;
					        }
					    }
					    if(guestname[guest-1]!=null){
					        JOptionPane.showMessageDialog(guestFrame, "Success! Thank you. ", "Success", JOptionPane.INFORMATION_MESSAGE);
					        frame4summary.setVisible(true);
					        guestFrame.setVisible(false);
					    }
					}else{
						JOptionPane.showMessageDialog(guestFrame, "Success! Thank you. ", "Success", JOptionPane.INFORMATION_MESSAGE); //tanggalin once complete na lahat ng frames
						frame4summary.setVisible(true);
						guestFrame.setVisible(false);
					}
    			}
    		}
    	});

    	back_btn.addActionListener(new ActionListener() {
    		public void actionPerformed(ActionEvent e) {
				frame2.setVisible(true);
				guestFrame.setVisible(false);
    		}
    	});

	//LISTENER FRAME 4

        frame4summary.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentShown(ComponentEvent e) {

		//DISTANCE COMPUTATION
	        airport1 = (String)wherefrom.getSelectedItem();
	        airport2 = (String)whereto.getSelectedItem();

	        coordinates1 = from.get(airport1);
	        coordinates2 = to.get(airport2);

	        int earthRadiusKm = 6371;

			if (coordinates1 != null && coordinates2 != null) {

			    double lat1 = Math.toRadians(coordinates1[0]);
			    double lon1 = Math.toRadians(coordinates1[1]);
			    double lat2 = Math.toRadians(coordinates2[0]);
			    double lon2 = Math.toRadians(coordinates2[1]);

			    double dLat = lat2 - lat1;
			    double dLon = lon2 - lon1;

			    double x = Math.sin(dLat/2) * Math.sin(dLat/2) + Math.sin(dLon/2) * Math.sin(dLon/2) * Math.cos(lat1) * Math.cos(lat2);
			    double y = 2 * Math.atan2(Math.sqrt(x), Math.sqrt(1-x));

			    distance = earthRadiusKm * y;

	        }

				System.out.println(airport1);
				System.out.println(airport2);
				System.out.println(Arrays.toString(coordinates1));
				System.out.println(Arrays.toString(coordinates2));
            	System.out.println("Distance: " + distance + " km");

	            // Get the selected date
	            java.util.Date selectedreturnDate = returndate.getDate();
	            java.util.Date selecteddepartDate = departdate.getDate();
	            // Format the date as a string
	            String dateString_return = new SimpleDateFormat("MM dd yy").format(selectedreturnDate);
	            String dateString_depart = new SimpleDateFormat("MM dd yy").format(selecteddepartDate);
	            int numberofguests = Integer.parseInt(guestno.getText());
				String a = String.valueOf(wherefrom.getSelectedItem());
				String b = String.valueOf(whereto.getSelectedItem());
	            if(rb_bundle1.isSelected()){
	            	f4_bundle=Integer.parseInt(rb_bundle1.getName());
	            	bundle_choice="Basic";
	            }else if(rb_bundle2.isSelected()){
	            	f4_bundle=Integer.parseInt(rb_bundle2.getName())*numberofguests;
	            	bundle_choice="Premium";
	            }else if(rb_bundle3.isSelected()){
	            	f4_bundle=Integer.parseInt(rb_bundle3.getName())*numberofguests;
	            	bundle_choice="Premium+";
	            }
				if(rtrip.isSelected()){
					st_flightdep.setText(a+" to "+b+" "+dateString_depart);
					st_flightret.setText(b+" to "+a+" "+dateString_return);

					int depart_price_row = table_departureflight.getSelectedRow();
					Object depart_price_obj = table_departureflight.getValueAt(depart_price_row,4);
					if (depart_price_obj instanceof String) {
					    depart_price = Double.parseDouble((String) depart_price_obj);
					    if(rtrip.isSelected()){
							depart_price = depart_price*numberofguests;
							String depart_price_str = String.format("%.2f", depart_price);
							st1.setText("SUBTOTAL ("+numberofguests+" passenger)"+": PHP "+ depart_price_str);
					    }else{
							depart_price = depart_price*numberofguests;
					    	String depart_price_str = String.format("%.2f", depart_price);
					    	st1.setText("SUBTOTAL ("+numberofguests+" passenger)"+": PHP "+ depart_price_str);
					    }
					} else {
					    System.out.println("depart_price_obj is not a String");
					}

					int return_price_row = table_returningflight.getSelectedRow();
					Object return_price_obj = table_returningflight.getValueAt(return_price_row,4);
					if (return_price_obj instanceof String) {
					    return_price = Double.parseDouble((String) return_price_obj);
					    if(rtrip.isSelected()){
							return_price = return_price*numberofguests;
							String return_price_str = String.format("%.2f", return_price);
							st2.setText("SUBTOTAL ("+numberofguests+" passenger)"+": PHP "+ return_price_str);
					    }else{
							return_price = return_price*numberofguests;
					    	String return_price_str = String.format("%.2f", return_price);
					    	st2.setText("SUBTOTAL ("+numberofguests+" passenger)"+": PHP "+ return_price_str);
					    }
					} else {
					    System.out.println("depart_price_obj is not a String");
					}
					depart_price = depart_price;
					return_price = return_price;
					f4_depret_stotal = depart_price+return_price;

					if(numberofguests==1){
						f4_adminfee = 250.0;
						f4_psc = 200.0;

					}else{
						f4_adminfee = 250.0*numberofguests;
						f4_psc = 200.0*numberofguests;
					}

					if(distance<200.0){
						distance = 200.0*numberofguests;
					}else if(distance>=700.0){
						distance = 700.0*numberofguests;
					}else{
						distance = Math.floor(distance*numberofguests);
					}

					f4_bundle = f4_bundle*2;
					f4_adminfee = f4_adminfee;
					f4_fuel = distance*2;
					f4_psc = f4_psc*2;
					f4_phvat = f4_depret_stotal*0.12;
					f4_taxstotal = f4_bundle+f4_adminfee+f4_fuel+f4_psc+f4_phvat;
					f4_rtrip_total = f4_depret_stotal+f4_taxstotal;

					DecimalFormat dec_format = new DecimalFormat("0.00");
					String bundle = dec_format.format(f4_bundle);
					String adminfee = dec_format.format(f4_adminfee);
					String fuel = dec_format.format(f4_fuel);
					String servcharge = dec_format.format(f4_psc);
					String phvat = dec_format.format(f4_phvat);
					String totaltax = dec_format.format(f4_taxstotal);
					String finaltotal = dec_format.format(f4_rtrip_total);

					af.setText("Administrative Fee: PHP "+adminfee);
					fs.setText("Fuel Surcharge: PHP "+fuel);
					dpsc.setText("Passenger Service Charge: "+"PHP "+servcharge);
					phv.setText("PH Value Added Tax: PHP "+phvat);
					st3.setText("SUBTOTAL (Taxes/Fees): PHP "+totaltax);
					total.setText("TOTAL: PHP "+finaltotal);

				}else{ //if oneway.isSelected()
					int depart_price_row = table_departureflight.getSelectedRow();
					Object depart_price_obj = table_departureflight.getValueAt(depart_price_row,4);
					if (depart_price_obj instanceof String) {
					    depart_price = Double.parseDouble((String) depart_price_obj);
					    if(rtrip.isSelected()){
							int guestnoXtwo = numberofguests*2;
							depart_price = depart_price*guestnoXtwo;
							String depart_price_str = String.format("%.2f", depart_price);
							st1.setText("SUBTOTAL ("+numberofguests+" passenger)"+": PHP "+ depart_price_str);
					    }else{
							depart_price = depart_price*numberofguests;
					    	String depart_price_str = String.format("%.2f", depart_price);
					    	st1.setText("SUBTOTAL ("+numberofguests+" passenger)"+": PHP "+ depart_price_str);
					    }
					} else {
					    System.out.println("depart_price_obj is not a String");
					}

					st_flightdep.setText(a+" to "+b+" "+dateString_depart);
					st_flightret.setText("[N/A]");
					st2.setText("SUBTOTAL: [N/A]");

					depart_price = depart_price;

					if(numberofguests==1){
						f4_adminfee = 250.0;
						f4_psc = 200.0;

					}else{
						f4_adminfee = 250.0*numberofguests;
						f4_psc = 200.0*numberofguests;
					}

					if(distance<200.0){
						distance = 200.0*numberofguests;
					}else if(distance>=700.0){
						distance = 700.0*numberofguests;
					}else{
						distance = Math.floor(distance*numberofguests);
					}

					f4_adminfee = f4_adminfee;
					f4_fuel = distance;
					f4_psc = f4_psc;
					f4_phvat = depart_price*0.12;
					f4_taxstotal = f4_bundle+f4_adminfee+f4_fuel+f4_psc+f4_phvat;
					f4_oneway_total = depart_price+f4_taxstotal;

					DecimalFormat dec_format = new DecimalFormat("0.00");
					String bundle = dec_format.format(f4_bundle);
					String adminfee = dec_format.format(f4_adminfee);
					String fuel = dec_format.format(f4_fuel);
					String servcharge = dec_format.format(f4_psc);
					String phvat = dec_format.format(f4_phvat);
					String totaltax = dec_format.format(f4_taxstotal);
					String finaltotal = dec_format.format(f4_oneway_total);

					af.setText("Administrative Fee: PHP "+adminfee);
					fs.setText("Fuel Surcharge: PHP "+fuel);
					dpsc.setText("Passenger Service Charge: "+"PHP "+servcharge);
					phv.setText("PH Value Added Tax: PHP "+phvat);
					st3.setText("SUBTOTAL (Taxes/Fees): PHP "+totaltax);
					total.setText("TOTAL: PHP "+finaltotal);

				}
				DecimalFormat dec_format = new DecimalFormat("0.00");

				String str_bundle = dec_format.format(f4_bundle);
				lbl_bundle.setText("Bundle ("+bundle_choice+"): PHP "+str_bundle);
            }
        });

        cash.addActionListener(new ActionListener() {
    		public void actionPerformed(ActionEvent e) {
				if(cash.isSelected()){
					payment_tf.setEnabled(true);
				}else{
					payment_tf.setEnabled(false);
					paybtn.setEnabled(false);
					payment_tf.setText("");
					change_tf.setText("");
					pay_alert.setBounds(560,270,200,25);
					pay_alert.setText("");

				}
    		}
        });

		payment_tf.addKeyListener(new KeyAdapter() {
    		public void keyReleased(KeyEvent e) {
		        String payment = payment_tf.getText();
		        pay_alert.setBounds(560,270,200,25);
		        if (!payment.matches("\\d+(\\.\\d*)?")) {
		            pay_alert.setText("Enter a valid amount.");
		            pay_alert.setForeground(Color.decode("#c00000"));
		            change_tf.setText("");
				    paybtn.setEnabled(false);
		        } else {
		            double pay = Double.parseDouble(payment);
		            if (pay>=f4_rtrip_total && pay>=f4_oneway_total){
			            if(rtrip.isSelected()){
				            pay_alert.setText("Pay now.");
				            pay_alert.setForeground(Color.decode("#70ad47"));
				            paybtn.setEnabled(true);
			            } else {
				            pay_alert.setText("Pay now.");
				            pay_alert.setForeground(Color.decode("#70ad47"));
				            paybtn.setEnabled(true);
			            }
		            }else{
			            pay_alert.setText("Insufficient amount.");
			            pay_alert.setForeground(Color.decode("#c00000"));
			            change_tf.setText("");
			            paybtn.setEnabled(false);
		            }
		        }
		    }
		});


		paybtn.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {

		    	int sureto_pay = JOptionPane.showConfirmDialog(frame4summary,"Are you sure with everything? You cannot go back once you have paid.","Confirm payment.",JOptionPane.YES_NO_OPTION);

		    	if (sureto_pay != 0){
		    		return;
		    	}

				cash.setEnabled(false);
				payment_tf.setEditable(false);
				paybtn.setEnabled(false);
				paybtnback.setEnabled(false);

		        String payment = payment_tf.getText();
		        if (!payment.matches("\\d+(\\.\\d*)?")) {
		            pay_alert.setText("Enter a valid amount.");
		            pay_alert.setForeground(Color.decode("#c00000"));
		            change_tf.setText("");
		        } else {
		            double pay = Double.parseDouble(payment);
		            if (pay>=f4_rtrip_total && pay>=f4_oneway_total){
		                if(rtrip.isSelected()){
		                    double change = pay - f4_rtrip_total;
		                    change_tf.setText(String.format("%.2f", change));
		                    pay_alert.setText("Payment successful.");
		                    pay_alert.setBounds(560,360,200,25);
		                    pay_alert.setForeground(Color.decode("#70ad47"));
		                } else {
		                    double change = pay - f4_oneway_total;
		                    change_tf.setText(String.format("%.2f", change));
		                    pay_alert.setText("Payment successful.");
		                    pay_alert.setBounds(560,360,200,25);
		                    pay_alert.setForeground(Color.decode("#70ad47"));
		                }
		            }else{
		                pay_alert.setText("Insufficient amount.");
		                pay_alert.setForeground(Color.decode("#c00000"));
		                change_tf.setText("");
		            }
		        }

		    }
		});

    	paybtnback.addActionListener(new ActionListener() {
    		public void actionPerformed(ActionEvent e) {

				cash.setSelected(false);
				payment_tf.setEnabled(false);
				payment_tf.setEditable(true);
				payment_tf.setText("");
				change_tf.setText("");
				pay_alert.setText("");
				paybtn.setEnabled(false);

				guestFrame.setVisible(true);
				frame4summary.setVisible(false);
    		}
    	});

		paybtncontinue.addActionListener(new ActionListener() {
    		public void actionPerformed(ActionEvent e) {

    			if (!change_tf.getText().isEmpty()){

					limit = Integer.parseInt(guestno.getText());
		            if (limit>2){
		            	//receipt_tbl_guest.getColumnModel().getColumn(1).setPreferredWidth(700);
		            }

			        try {
			            // Load the JDBC driver
			            Class.forName("com.mysql.cj.jdbc.Driver");

			            // Establish a connection
			            Connection db_conn = DriverManager.getConnection(url, username, password);

			            // Create a Statement
			            Statement db_stmt = db_conn.createStatement();

						//DB_CUSTOMER NAME
						String first_name = fname_tf.getText();
						String mid_name = mname_tf.getText();
						String last_name = lname_tf.getText();

						if(mid_name.isEmpty()){
							db_customername = first_name+" "+last_name;
						}else{
							db_customername = first_name+" "+mid_name+" "+last_name;
						}

						db_customername = db_customername;

				        //DB_REFERENCE NO
				        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
				        StringBuilder referenceNumber = new StringBuilder();
				        Random rnd = new Random();
				        while (referenceNumber.length() < 6) { // length of the random string.
				            int index = (int) (rnd.nextFloat() * characters.length());
				            referenceNumber.append(characters.charAt(index));
				        }
				        db_refno = referenceNumber.toString();

						//DB_BOOKING DATE
						java.util.Date origDate = origdate.getDate();
						db_bookdate = new java.sql.Date(origDate.getTime());

						//DB_TIMESTAMP
						db_timestamp = new SimpleDateFormat("HH:mm:ss").format(new Date());

						//DB_PASSENGER COUNT
						db_passenger = Integer.parseInt(guestno.getText());

						//DB_BUNDLE
						db_bundle = bundle_choice;

						//DB_DEPART DATE && RETURN DATE
			            java.util.Date selectedreturnDate = returndate.getDate();
			            java.util.Date selecteddepartDate = departdate.getDate();

			            String dateString_return, dateString_depart;

			            if(rtrip.isSelected()){
			            // Format the date as a string
			            	dateString_return = new SimpleDateFormat("YYYY-MM-DD").format(selectedreturnDate);
			            	dateString_depart = new SimpleDateFormat("YYYY-MM-DD").format(selecteddepartDate);
			            }else{
			            	dateString_return = "N/A";
			            	dateString_depart = new SimpleDateFormat("YYYY-MM-DD").format(selecteddepartDate);
			            }
		            	db_departdate = dateString_depart;
		            	db_returndate = dateString_return;

						//DB_DEPART AND ARRIVE TIME
						int selectedRow = table_departureflight.getSelectedRow();
						Object depart_time_obj = table_departureflight.getValueAt(selectedRow, 0);
						Object arrive_time_obj = table_departureflight.getValueAt(selectedRow, 1);

						int selectedRow2 = table_returningflight.getSelectedRow();
						Object depart_time2_obj = null;
						Object arrive_time2_obj = null;

						if (selectedRow2 >= 0) {
						    depart_time2_obj = table_returningflight.getValueAt(selectedRow2, 0);
						    arrive_time2_obj = table_returningflight.getValueAt(selectedRow2, 1);
						}

						SimpleDateFormat displayFormat = new SimpleDateFormat("HH:mm:ss");
						SimpleDateFormat parseFormat = new SimpleDateFormat("hh:mm a");
						Date date1 = null;
						Date date2 = null;
						Date date3 = null;
						Date date4 = null;

						String dep_time = "N/A", arr_time = "N/A", dep_time2 = "N/A", arr_time2 = "N/A";
						depart_time = "N/A";
						return_time = "N/A";

						try {
						    date1 = parseFormat.parse((String) depart_time_obj);
						    date2 = parseFormat.parse((String) arrive_time_obj);
						    if (date1 != null) dep_time = displayFormat.format(date1);
						    if (date2 != null) arr_time = displayFormat.format(date2);
							depart_time = dep_time+" - "+arr_time;

						    if (rtrip.isSelected() && selectedRow2 >= 0) {
						        date3 = parseFormat.parse((String) depart_time2_obj);
						        date4 = parseFormat.parse((String) arrive_time2_obj);
						        if (date3 != null) dep_time2 = displayFormat.format(date3);
						        if (date4 != null) arr_time2 = displayFormat.format(date4);
						        return_time = dep_time2+" - "+arr_time2;
						    }
						} catch (ParseException v) {
						    v.printStackTrace();
						    // handle exception, e.g., show error message to user
						}

						db_departtime = depart_time;
						db_returntime = return_time;

						//DB_SEATS DEPARTURE/RETURNING
						db_depseats = "N/A";
						db_retseats = "N/A";

						if(oneway.isSelected()){
							if(rb_bundle1.isSelected()||rb_bundle2.isSelected()){
								db_depseats = selected_depart.toString();
							}else{
								db_depseats = departureSeats.toString();
							}
						}else{
							if(rb_bundle1.isSelected()||rb_bundle2.isSelected()){
								db_depseats = selected_depart.toString();
								db_retseats = selected_return.toString();
							}else{
								db_depseats = departureSeats.toString();
								db_retseats = returnSeats.toString();
							}
						}

						db_depseats = db_depseats;
						db_retseats = db_retseats;

						//DB_DEPARTPLACE DB_ARRIVEPLACE
						departplace = wherefrom.getSelectedItem().toString();
						arriveplace = whereto.getSelectedItem().toString();
						db_departplace = departplace;
						db_arriveplace = arriveplace;

						//DB_ DEPARTURE FLIGHT NO
						int depfno_selectedRow = table_departureflight.getSelectedRow();
						Object depflightno_obj = table_departureflight.getValueAt(depfno_selectedRow, 3);
						db_depflightno = depflightno_obj.toString();

						if (oneway.isSelected()) {
						    db_retflightno = "N/A";
						} else {
						    int retfno_selectedRow = table_returningflight.getSelectedRow();
						    if (retfno_selectedRow != -1) {
						        Object retflightno_obj = table_returningflight.getValueAt(retfno_selectedRow, 3);
						        db_retflightno = retflightno_obj.toString();
						    }
						}

						//DB_TRANSACTION ID
				        String characters1 = "0123456789";
				        StringBuilder referenceNumber1 = new StringBuilder();
				        Random rnd1 = new Random();
				        while (referenceNumber1.length() < 9) { // length of the random string.
				            int index = (int) (rnd1.nextFloat() * characters1.length());
				            referenceNumber1.append(characters1.charAt(index));
				        }
				        db_transid = Integer.parseInt(referenceNumber1.toString());

				        //DB_PAYMENT
				        db_paymentmethod = "Cash";

				        //DB_AMOUNT
						if(rtrip.isSelected()){
							db_amount = (int) f4_rtrip_total;
						}else{
							db_amount = (int) f4_oneway_total;
						}
						db_amount = db_amount;

			            // SQL query
			            String db_sql = "INSERT INTO transaction (BookingDate, Time_Stamp, ReferenceNo, CustomerName, Passenger, Bundle, DepartPlace, ArrivePlace, Departure_FlightNo, DepartDate, DepartureTime, SeatsDeparture, Returning_FlightNo, ReturnDate, ReturningTime, SeatsReturning, TransactionID, PaymentMethod, Amount) VALUES "
							           + "('"+db_bookdate+"',"
							           + "'"+db_timestamp+"',"
							           + "'"+db_refno+"',"
							           + "'"+db_customername+"',"
							           + "'"+db_passenger+"',"
							           + "'"+db_bundle+"',"
							           + "'"+db_departplace+"',"
							           + "'"+db_arriveplace+"',"
							           + "'"+db_depflightno+"',"
							           + "'"+db_departdate+"',"
							           + "'"+db_departtime+"',"
							           + "'"+db_depseats+"',"
							           + "'"+db_retflightno+"',"
							           + "'"+db_returndate+"',"
							           + "'"+db_returntime+"',"
							           + "'"+db_retseats+"',"
							           + "'"+db_transid+"',"
							           + "'"+db_paymentmethod+"',"
							           + "'"+db_amount+"')";

			            // Execute the query
			            db_stmt.executeUpdate(db_sql);

			            // Close the Statement and Connection
			            db_stmt.close();
			            db_conn.close();

						//frame6.setVisible(true);
						frame5.setVisible(true);
						frame4summary.setVisible(false);

			        } catch (ClassNotFoundException a) {
			            System.out.println("JDBC Driver not found.");
			            a.printStackTrace();
			        } catch (SQLException b) {
			            System.out.println("Error connecting to the database.");
			            b.printStackTrace();
			        }

					//RECEIPT PAGE

					//booking details
			        try {
						SimpleDateFormat inputFormat = new SimpleDateFormat("MMM dd yyyy");
			            String receipt_bookdate = inputFormat.format(db_bookdate);
			            receipt_refno = db_refno;

						bcode_refno = new JLabel(receipt_refno);
						bcode_refno.setBounds(810,70,100,25);
						bcode_refno.setFont(new Font("Arial", Font.BOLD, 16));
						frame6.add(bcode_refno);

						String receipt_type;

						if(rtrip.isSelected()){
							receipt_type = "Round-trip";
						}else{
							receipt_type = "One-way";
						}
						receipt_type = receipt_type;

						String receipt_bundle = db_bundle;

			            receipt_tblmodel1.addRow(new Object[]{receipt_bookdate,receipt_refno, receipt_type, receipt_bundle});
			            receipt_tbl1.setRowHeight(0, 30);
			        } catch(Exception c) {
			            c.printStackTrace();
			        }

					//flight details
			        try {
			        	String receipt_depflightno = db_depflightno;
			        	String receipt_retflightno;

			        	if(rtrip.isSelected()){;
			        		receipt_retflightno = db_retflightno;
			        	}else{
			        		receipt_retflightno = "N/A";
			        	}

			        	String receipt_departure = "DEPARTURE:";
			            String receipt_departdate = db_departdate;
			            String receipt_departtime = depart_time;
			            String receipt_departplace = departplace+" - "+arriveplace;
						String receipt_depseats, receipt_retseats = "N/A";

			            String receipt_returning = "RETURNING:";
			            String receipt_returndate = db_returndate;
			            String receipt_returntime = return_time;
			            String receipt_returnplace;

			            if (rtrip.isSelected()){
			            	receipt_returnplace = arriveplace+" - "+departplace;
			            }else{
			            	receipt_returnplace = "N/A";
			            }

						receipt_returnplace = receipt_returnplace;

						if(oneway.isSelected()){
							if(rb_bundle1.isSelected()||rb_bundle2.isSelected()){
								receipt_depseats = selected_depart.toString();
							}else{
								receipt_depseats = departureSeats.toString();
							}
						}else{
							if(rb_bundle1.isSelected()||rb_bundle2.isSelected()){
								receipt_depseats = selected_depart.toString();
								receipt_retseats = selected_return.toString();
							}else{
								receipt_depseats = departureSeats.toString();
								receipt_retseats = returnSeats.toString();
							}
						}

						receipt_depseats = receipt_depseats;
						receipt_retseats = receipt_retseats;

			            receipt_tblmodel2.addRow(new Object[]{receipt_departure,receipt_depflightno,receipt_departdate,receipt_departtime,receipt_departplace,receipt_depseats});
			            receipt_tblmodel2.addRow(new Object[]{receipt_returning,receipt_retflightno,receipt_returndate,receipt_returntime,receipt_returnplace,receipt_retseats});
			            receipt_tbl2.setRowHeight(0, 30);
			            receipt_tbl2.setRowHeight(1, 30);
			        } catch(Exception d) {
			            d.printStackTrace();
			        }

					//guest details
			        try {
						// Initialize an empty String
						String receipt_customername = db_customername;
						StringBuilder allguestnames = new StringBuilder(db_customername);
						String receipt_guestno = guestno.getText();

						for(String name : guestname){
						    if(name != null){
						        allguestnames.append(", ").append(name);
						    }
						}

			            receipt_tblmodel_guest.addRow(new Object[]{receipt_guestno,allguestnames});
			            receipt_tbl_guest.setRowHeight(0, 32);

			            if(limit>2){
							// Create a JTextArea
							JTextArea name_textArea = new JTextArea();
							name_textArea.setLineWrap(true);
							name_textArea.setWrapStyleWord(true);

							// Create a TableCellRenderer
							TableCellRenderer renderer = new TableCellRenderer() {
							    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
							        if (row == 0) { // Check if it's the row you want to word wrap
							            name_textArea.setText(value.toString());
							            name_textArea.setSize(receipt_tbl_guest.getColumnModel().getColumn(1).getWidth(), 32);
							            if (receipt_tbl_guest.getRowHeight(row) != 32) {
							                receipt_tbl_guest.setRowHeight(row, 32);
							            }
							            return name_textArea;
							        } else {
							            // For other rows, return a default renderer
							            return new DefaultTableCellRenderer().getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
							        }
							    }
							};

							// Set the TableCellRenderer for a specific column
							receipt_tbl_guest.getColumnModel().getColumn(1).setCellRenderer(renderer);
			            }

			        } catch(Exception d) {
			            d.printStackTrace();
			        }

					//payment details
			        try {
			        	//base fare
						String receipt_bfare;

			        	if(rtrip.isSelected()){
			        		f4_depret_stotal = depart_price+return_price;
			        	}else{
			        		f4_depret_stotal = depart_price;
			        	}
						String bfare = "BASE FARE (SUBTOTAL):";
			        	String bfare_tostring = String.format("%.2f", f4_depret_stotal);
						receipt_bfare = "PHP "+bfare_tostring;

						//taxes
						String bundle = "Bundle:";
			        	String bundle_tostring = String.format("%.2f", f4_bundle);
						String receipt_bundle = "PHP "+bundle_tostring;

						String adminfee = "Administrative Fee:";
			        	String adminfee_tostring = String.format("%.2f", f4_adminfee);
						String receipt_adminfee = "PHP "+adminfee_tostring;

						String fuel = "Fuel Surcharge:";
			        	String fuel_tostring = String.format("%.2f", f4_fuel);
						String receipt_fuel = "PHP "+fuel_tostring;

						String psc = "Passenger Service Charge:";
			        	String psc_tostring = String.format("%.2f", f4_psc);
						String receipt_psc = "PHP "+psc_tostring;

						String phvat = "PH Value Added Tax:";
			        	String phvat_tostring = String.format("%.2f", f4_phvat);
						String receipt_phvat = "PHP "+phvat_tostring;

						String taxes = "TAXES (SUBTOTAL):";
			        	String taxes_tostring = String.format("%.2f", f4_taxstotal);
						String receipt_taxes = "PHP "+taxes_tostring;

						//total
						String total = "TOTAL:";
						double total_amount = f4_depret_stotal+f4_taxstotal;
			        	String total_tostring = String.format("%.2f", total_amount);
						String receipt_total = "PHP "+total_tostring;

			            receipt_tblmodel3.addRow(new Object[]{bfare,receipt_bfare});
			            receipt_tblmodel3.addRow(new Object[]{bundle,receipt_bundle});
			            receipt_tblmodel3.addRow(new Object[]{adminfee,receipt_adminfee});
			            receipt_tblmodel3.addRow(new Object[]{fuel,receipt_fuel});
			            receipt_tblmodel3.addRow(new Object[]{psc,receipt_psc});
			            receipt_tblmodel3.addRow(new Object[]{phvat,receipt_phvat});
			            receipt_tblmodel3.addRow(new Object[]{taxes,receipt_taxes});
			            receipt_tblmodel3.addRow(new Object[]{total,receipt_total});
			            //receipt_tbl3.setRowHeight(0, 10);
			            //receipt_tbl3.setRowHeight(1, 30);
			            //receipt_tbl3.setRowHeight(2, 30);
			        } catch(Exception d) {
			            d.printStackTrace();
			        }

    			}else{
    				JOptionPane.showMessageDialog(null, "Please pay for your ticket.");
    			}
    		}
    	});

    //LISTENER FRAME 5
    	f5_btn_viewreceipt.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
				frame6.setVisible(true);
		    }
		});

		f5_btn_bookanother.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
			// frame 1 flights
			type_content.setText("");
			guest_content.setText("");
			departure_content.setText("");
			returning_content.setText("");

			flight_type.clearSelection();

			int wherefrom_selected = wherefrom.getSelectedIndex();
			int whereto_selected = whereto.getSelectedIndex();
			wherefrom.setSelectedIndex(wherefrom_selected);
			whereto.setSelectedIndex(whereto_selected);

			guestno.setText("0");
			departdate.setDate(Calendar.getInstance().getTime()); departdate.setFormats(new SimpleDateFormat("MM.dd.yyyy"));
			returndate.setDate(calendar1.getTime()); returndate.setFormats(new SimpleDateFormat("MM.dd.yyyy"));

//			//frame 2 flights
			table_departureflight.clearSelection();
			table_returningflight.clearSelection();
			bg_bundles.clearSelection();
	        departureSeats.clear();
	        returnSeats.clear();
	        isDeparture.set(true);

			//frame 3 guest
			fname_tf.setText("Juan");
			mname_tf.setText("Dela");
			lname_tf.setText("Cruz");
			birthdate.setDate(Calendar.getInstance().getTime()); departdate.setFormats(new SimpleDateFormat("MM.dd.yyyy"));
			nationality_cbox.setSelectedIndex(0);
			contactNum_tf.setText("09123456789");
			email_tf.setText("sample@gmail.com");
			policy_chb.setSelected(false);

			//frame 4
			cash.setEnabled(true);
			cash.setSelected(false);
			payment_tf.setEditable(true);
			payment_tf.setText("");
			change_tf.setText("");
			pay_alert.setBounds(560,270,200,25);
			pay_alert.setText("");
			paybtnback.setEnabled(true);
			f4_rtrip_total=0;
			f4_oneway_total=0;

			//frame 6
			receipt_tblmodel1.setRowCount(0);
			receipt_tblmodel_guest.setRowCount(0);
			receipt_tblmodel2.setRowCount(0);
			receipt_tblmodel3.setRowCount(0);
			bcode_refno.setText("");
			receipt_tbl_guest.getColumnModel().getColumn(0).setCellRenderer(centerRenderer_guest);
			receipt_tbl_guest.getColumnModel().getColumn(1).setCellRenderer(centerRenderer_guest);

			homeFrame.setVisible(true);
			frame5.setVisible(false);

		    }
		});

    //LISTENER FRAME 6
		receipt_download.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {

		        try {
		            // Create a BufferedImage with the same dimensions as the JFrame
		            BufferedImage image = new BufferedImage(frame6.getWidth(), frame6.getHeight(), BufferedImage.TYPE_INT_RGB);

		            // Paint the JFrame to the BufferedImage
		            frame6.paintAll(image.getGraphics());

		            // Get the current timestamp
					String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());

		            // Save the BufferedImage to a file
		            ImageIO.write(image, "png", new File("C:\\Users\\romxcob\\Documents\\JCreator LE\\MyProjects\\projectsample\\receipts\\receipt"+timestamp+".png"));
		        } catch (Exception f) {
		            f.printStackTrace();
		        }

		        // Close the JFrame
		        frame6.dispose();
		    }
		});


//SETBOUNDS

	//FRAME 1 (FLIGHT 1)

 		//frame
		frame.setVisible(false);
		frame.setLocationRelativeTo(null);

		//label
		loc_from.setBounds(450,285,50,25); //right side
		frame.add(loc_from);

		loc_to.setBounds(610,285,50,25); //right side
		frame.add(loc_to);

		flighttype.setBounds(450,100,100,25); //right side
		frame.add(flighttype);

		type.setBounds(225,100,100,25);
		frame.add(type);

		type_content.setBounds(225,125,100,25);
		frame.add(type_content);

		guestnumber.setBounds(450,210,100,25); //right side
		frame.add(guestnumber);

		guest.setBounds(225,175,100,25);
		frame.add(guest);

		guest_content.setBounds(225,200,100,25);
		frame.add(guest_content);

		departuredate.setBounds(450,360,150,25); //right side
		frame.add(departuredate);

		departure.setBounds(225,240,100,25);
		frame.add(departure);

		departure_content.setBounds(225,265,200,50);
		frame.add(departure_content);

		returningdate.setBounds(610,360,150,25); //right side
		frame.add(returningdate);

		returning.setBounds(225,325,100,25);
		frame.add(returning);

		returning_content.setBounds(225,350,200,50);
		frame.add(returning_content);

		//radiobutton

		rtrip.setBounds(450,125,100,80); //right side
		frame.add(rtrip);

		oneway.setBounds(550,125,100,80); //right side
		frame.add(oneway);

		//combobox

		wherefrom.setBounds(450,310,150,35); //right side
		frame.add(wherefrom);

		whereto.setBounds(610,310,150,35); //right side
		frame.add(whereto);

		//textfield

		guestno.setBounds(500,235,50,35); //right side
		guestno.setEditable(false);
		frame.add(guestno);

		//button

		minus.setBounds(450,235,50,35); //right side
		frame.add(minus);

		plus.setBounds(550,235,50,35); //right side
		frame.add(plus);

		frame1back.setBounds(535,475,125,30);
		frame.add(frame1back);

		search.setBounds(675,475,160,30);
		frame.add(search);

		//datepicker
		departdate.setBounds(450,385,150,35); //right side
		frame.add(departdate);

		returndate.setBounds(610,385,150,35); //right side
		frame.add(returndate);

    	//progress bars
		frame1_progressLabel1_flights.setBounds(203,50,75,30);
		frame1_progressLabel1.setBounds(190,50,75,30);
		frame1_progressLabel1.setEnabled(false);
		frame1_progressLabel2.setBounds(290,50,300,30);
		frame1_progressLabel3.setBounds(510,50,200,30);
		frame1_progressLabel4.setBounds(680,50,200,30);

		frame.add(frame1_progressLabel1_flights);
    	frame.add(frame1_progressLabel1);
    	frame.add(frame1_progressLabel2);
    	frame.add(frame1_progressLabel3);
    	frame.add(frame1_progressLabel4);

    	//colors
    	Container f1_contentPane = frame.getContentPane();
        f1_contentPane.setBackground(Color.WHITE);

    	search.setBackground(Color.decode("#ed7d31"));
    	search.setForeground(Color.WHITE);
    	frame1back.setBackground(Color.WHITE);

    	frame1_progressLabel1.setBackground(Color.decode("#ed7d31"));
    	frame1_progressLabel1_flights.setForeground(Color.WHITE);
    	rtrip.setBackground(Color.WHITE);
    	rtrip.setForeground(Color.BLACK);
    	oneway.setBackground(Color.WHITE);
    	oneway.setForeground(Color.BLACK);
    	guestno.setBackground(Color.WHITE);
    	guestno.setForeground(Color.BLACK);
    	plus.setBackground(Color.WHITE);
    	plus.setForeground(Color.BLACK);
    	minus.setBackground(Color.WHITE);
    	minus.setForeground(Color.BLACK);
    	wherefrom.setBackground(Color.WHITE);
    	wherefrom.setForeground(Color.BLACK);
    	whereto.setBackground(Color.WHITE);
    	whereto.setForeground(Color.BLACK);

    	//font
        frame1_progressLabel1.setFont(new Font("Arial", Font.BOLD, 14));
		frame1_progressLabel1_flights.setFont(calsans.deriveFont(Font.BOLD, 16f));
		frame1_progressLabel2.setFont(calsans.deriveFont(Font.BOLD, 16f));
		frame1_progressLabel3.setFont(calsans.deriveFont(Font.BOLD, 16f));
		frame1_progressLabel4.setFont(calsans.deriveFont(Font.BOLD, 16f));

        type_content.setFont(new Font("Arial", Font.PLAIN, 16));
        guest_content.setFont(new Font("Arial", Font.PLAIN, 16));
		departure_content.setFont(new Font("Arial", Font.PLAIN, 16));
		returning_content.setFont(new Font("Arial", Font.PLAIN, 16));

		type.setFont(calsans.deriveFont(16f));
		guest.setFont(calsans.deriveFont(16f));
		departure.setFont(calsans.deriveFont(16f));
		returning.setFont(calsans.deriveFont(16f));

		loc_from.setFont(calsans.deriveFont(16f));
		loc_to.setFont(calsans.deriveFont(16f));
		flighttype.setFont(calsans.deriveFont(16f));
		guestnumber.setFont(calsans.deriveFont(16f));
		departuredate.setFont(calsans.deriveFont(16f));
		returningdate.setFont(calsans.deriveFont(16f));

		rtrip.setFont(new Font("Arial", Font.PLAIN, 14));
		oneway.setFont(new Font("Arial", Font.PLAIN, 14));
		guestno.setFont(new Font("Arial", Font.PLAIN, 14));
		wherefrom.setFont(new Font("Arial", Font.PLAIN, 14));
		whereto.setFont(new Font("Arial", Font.PLAIN, 14));
		departdate.setFont(new Font("Arial", Font.PLAIN, 14));
		returndate.setFont(new Font("Arial", Font.PLAIN, 14));

    	search.setFont(calsans.deriveFont(18f));
    	frame1back.setFont(calsans.deriveFont(18f));

	//FRAME 2 (FLIGHT 2)

		//frame2
		frame2.setBounds(0,0,1000,600);
		frame2.setVisible(false);
		frame2.setLocationRelativeTo(null);

		//label
		select_departure.setBounds(210,90,200,25);
		frame2.add(select_departure);

		select_returning.setBounds(210,225,200,25);//GO TO LISTENER
		frame2.add(select_returning);

		select_bundle.setBounds(210,350,200,25);
		frame2.add(select_bundle);

		bundle1.setBounds(210,355,200,100);
		//frame2.add(bundle1);

		bundle2.setBounds(410,355,200,100);
		//frame2.add(bundle2);

		bundle3.setBounds(610,355,200,100);
		//frame2.add(bundle3);

		//scrollpane
		spane_departureflight.setBounds(210,115,550,100);
		frame2.add(spane_departureflight);
		//table_departureflight.setRowHeight(0,40);

		spane_returningflight.setBounds(210,245,550,100);
		frame2.add(spane_returningflight);

		//radio button
		rb_bundle1.setBounds(210,365,180,100);
		frame2.add(rb_bundle1);
		rb_bundle1.setEnabled(false);

		rb_bundle2.setBounds(405,365,180,100);
		frame2.add(rb_bundle2);
		rb_bundle2.setEnabled(false);

		rb_bundle3.setBounds(597,365,180,100);
		frame2.add(rb_bundle3);
		rb_bundle3.setEnabled(false);

		//button
		frame2btn_back.setBounds(535,475,125,30);
		frame2.add(frame2btn_back);

		frame2btn_continue.setBounds(685,475,125,30);
		frame2.add(frame2btn_continue);

		//progress bars
		f2_progressLabel1_flights.setBounds(203,50,75,30);
		f2_progressLabel1.setBounds(190,50,75,30);
		f2_progressLabel1.setEnabled(false);
		f2_progressLabel2.setBounds(290,50,300,30);
		f2_progressLabel3.setBounds(510,50,200,30);
		f2_progressLabel4.setBounds(680,50,200,30);

		frame2.add(f2_progressLabel1_flights);
		frame2.add(f2_progressLabel1);
		frame2.add(f2_progressLabel2);
		frame2.add(f2_progressLabel3);
		frame2.add(f2_progressLabel4);

    	//colors
    	Container f2_contentPane = frame2.getContentPane();
        f2_contentPane.setBackground(Color.WHITE);

    	frame2btn_continue.setBackground(Color.decode("#ed7d31"));
    	frame2btn_continue.setForeground(Color.WHITE);
    	frame2btn_back.setBackground(Color.WHITE);

    	f2_progressLabel1.setBackground(Color.decode("#ed7d31"));
    	f2_progressLabel1_flights.setForeground(Color.WHITE);
    	rb_bundle1.setOpaque(false);
    	rb_bundle2.setBackground(Color.WHITE);
    	rb_bundle3.setBackground(Color.WHITE);

		//font
		f2_progressLabel1.setFont(new Font("Arial", Font.BOLD, 14));
		f2_progressLabel1_flights.setFont(calsans.deriveFont(Font.BOLD, 16f));
		f2_progressLabel2.setFont(calsans.deriveFont(Font.BOLD, 16f));
		f2_progressLabel3.setFont(calsans.deriveFont(Font.BOLD, 16f));
		f2_progressLabel4.setFont(calsans.deriveFont(Font.BOLD, 16f));
    	frame2btn_continue.setFont(new Font("Arial", Font.BOLD, 14));
    	frame2btn_back.setFont(new Font("Arial", Font.BOLD, 14));

		select_departure.setFont(calsans.deriveFont(16f));
		select_returning.setFont(calsans.deriveFont(16f));
		select_bundle.setFont(calsans.deriveFont(16f));

		frame2btn_continue.setFont(calsans.deriveFont(18f));
		frame2btn_back.setFont(calsans.deriveFont(18f));

	//FRAME 2.5 (SEATS)

		//seats frame
		frame_seats.setBounds(0,0,700,450);
		frame_seats.setVisible(false);
		frame_seats.setLocationRelativeTo(null);

		//button
		btn_seat.setBounds(50,350,150,30);
		btn_seat.setBackground(Color.decode("#ed7d31"));
		btn_seat.setForeground(Color.WHITE);
		btn_seat.setFont(calsans.deriveFont(18f));
		frame_seats.add(btn_seat);

		//CHECKBOXES
		A1.setBounds(230,157,25,25);
		A2.setBounds(295,157,25,25);
		A3.setBounds(360,157,25,25);
		A4.setBounds(425,157,25,25);
		A5.setBounds(490,157,25,25);
		B1.setBounds(230,177,25,25);
		B2.setBounds(295,177,25,25);
		B3.setBounds(360,177,25,25);
		B4.setBounds(425,177,25,25);
		B5.setBounds(490,177,25,25);
		C1.setBounds(230,208,25,25);
		C2.setBounds(295,208,25,25);
		C3.setBounds(360,208,25,25);
		C4.setBounds(425,208,25,25);
		C5.setBounds(490,208,25,25);
		D1.setBounds(230,230,25,25);
		D2.setBounds(295,230,25,25);
		D3.setBounds(360,230,25,25);
		D4.setBounds(425,230,25,25);
		D5.setBounds(490,230,25,25);
		frame_seats.add(A1);
		frame_seats.add(A2);
		frame_seats.add(A3);
		frame_seats.add(A4);
		frame_seats.add(A5);
		frame_seats.add(B1);
		frame_seats.add(B2);
		frame_seats.add(B3);
		frame_seats.add(B4);
		frame_seats.add(B5);
		frame_seats.add(C1);
		frame_seats.add(C2);
		frame_seats.add(C3);
		frame_seats.add(C4);
		frame_seats.add(C5);
		frame_seats.add(D1);
		frame_seats.add(D2);
		frame_seats.add(D3);
		frame_seats.add(D4);
		frame_seats.add(D5);

		A1.setOpaque(false);
		A2.setOpaque(false);
		A3.setOpaque(false);
		A4.setOpaque(false);
		A5.setOpaque(false);
		B1.setOpaque(false);
		B2.setOpaque(false);
		B3.setOpaque(false);
		B4.setOpaque(false);
		B5.setOpaque(false);
		C1.setOpaque(false);
		C2.setOpaque(false);
		C3.setOpaque(false);
		C4.setOpaque(false);
		C5.setOpaque(false);
		D1.setOpaque(false);
		D2.setOpaque(false);
		D3.setOpaque(false);
		D4.setOpaque(false);
		D5.setOpaque(false);

	//FRAME 3

    	f3_progressLabel1.setBounds(180,50,200,30);
    	f3_progressLabel2.setBounds(325,50,100,30);
    	f3_progressLabel2_details.setBounds(348,50,100,30);
    	f3_progressLabel2.setEnabled(false);
    	f3_progressLabel3.setBounds(447,50,300,30);
    	f3_progressLabel4.setBounds(685,50,200,30);

		guestDetlabel.setBounds(180,140,300,30);
		fname_label.setBounds(180,170,200,30);
		mname_label.setBounds(390,170,200,30);
		lname_label.setBounds(600,170,200,30);
		fname_tf.setBounds(180,200,200,30);
		mname_tf.setBounds(390,200,200,30);
		lname_tf.setBounds(600,200,200,30);

		bdate_label.setBounds(180,240,200,30);
		birthdate.setBounds(180,270,300,30);
		nationality_label.setBounds(500,240,200,30);
		nationality_cbox.setBounds(500,270,300,30);

		contactNum_label.setBounds(180,310,250,30);
		contactNum_tf.setBounds(180,340,300,30);
		email_label.setBounds(500,310,200,30);
		email_tf.setBounds(500,340,300,30);
		policy_chb.setBounds(180,380,625,60);

		//button
		back_btn.setBounds(535,475,125,30);
		cont_btn.setBounds(685,475,125,30);
    	cont_btn.setEnabled(false);

		// guess frame properties
    	guestFrame.setBounds(0,0,1000,600);
    	guestFrame.setVisible(false);
		guestFrame.setLocationRelativeTo(null);

    	guestFrame.add(guestDetlabel);

    	//progress bars

    	guestFrame.add(f3_progressLabel1);
    	guestFrame.add(f3_progressLabel2_details);
    	guestFrame.add(f3_progressLabel2);
    	guestFrame.add(f3_progressLabel3);
    	guestFrame.add(f3_progressLabel4);

    	//

    	guestFrame.add(fname_tf);
    	guestFrame.add(mname_tf);
    	guestFrame.add(lname_tf);
    	guestFrame.add(fname_label);
    	guestFrame.add(mname_label);
    	guestFrame.add(lname_label);

    	guestFrame.add(bdate_label);
    	guestFrame.add(birthdate);
    	guestFrame.add(nationality_label);
    	guestFrame.add(nationality_cbox);

    	guestFrame.add(contactNum_label);
    	guestFrame.add(contactNum_tf);
    	guestFrame.add(email_label);
    	guestFrame.add(email_tf);
    	guestFrame.add(policy_chb);
    	guestFrame.add(back_btn);
    	guestFrame.add(cont_btn);

    	//colors
    	Container contentPane = guestFrame.getContentPane();
        contentPane.setBackground(Color.WHITE);

    	cont_btn.setBackground(Color.decode("#ed7d31"));
    	cont_btn.setForeground(Color.WHITE);
    	back_btn.setBackground(Color.WHITE);

    	f3_progressLabel1.setBackground(Color.WHITE);
    	f3_progressLabel2.setBackground(Color.decode("#ed7d31"));
    	f3_progressLabel2_details.setForeground(Color.WHITE);
    	f3_progressLabel3.setBackground(Color.WHITE);
    	f3_progressLabel4.setBackground(Color.WHITE);
    	nationality_cbox.setBackground(Color.WHITE);
    	nationality_cbox.setForeground(Color.BLACK);
    	policy_chb.setBackground(Color.WHITE);

    	//font
		f3_progressLabel1.setFont(calsans.deriveFont(Font.BOLD, 16f));
		f3_progressLabel2.setFont(calsans.deriveFont(Font.BOLD, 16f));
		f3_progressLabel2_details.setFont(calsans.deriveFont(Font.BOLD, 16f));
		f3_progressLabel3.setFont(calsans.deriveFont(Font.BOLD, 16f));
		f3_progressLabel4.setFont(calsans.deriveFont(Font.BOLD, 16f));

        guestDetlabel.setFont(underrated.deriveFont(25f));
		fname_label.setFont(calsans.deriveFont(16f));
		mname_label.setFont(calsans.deriveFont(16f));
		lname_label.setFont(calsans.deriveFont(16f));
        fname_tf.setFont(new Font("Arial", Font.PLAIN, 14));
        mname_tf.setFont(new Font("Arial", Font.PLAIN, 14));
        lname_tf.setFont(new Font("Arial", Font.PLAIN, 14));

        bdate_label.setFont(calsans.deriveFont(16f));
        birthdate.setFont(new Font("Arial", Font.PLAIN, 14));
        nationality_label.setFont(calsans.deriveFont(16f));
        nationality_cbox.setFont(new Font("Arial", Font.PLAIN, 14));

        contactNum_label.setFont(calsans.deriveFont(16f));
        contactNum_tf.setFont(new Font("Arial", Font.PLAIN, 14));
        email_label.setFont(calsans.deriveFont(16f));
        email_tf.setFont(new Font("Arial", Font.PLAIN, 14));

        policy_chb.setFont(new Font("Arial", Font.PLAIN, 15));
        back_btn.setFont(calsans.deriveFont(18f));
        cont_btn.setFont(calsans.deriveFont(18f));

    //FRAME 4

 		//frame
		frame4summary.setBounds(0,0,1000,600);
		frame4summary.setVisible(false);
		frame4summary.setLocationRelativeTo(null);

		//label
		bs.setBounds(230,110,300,25);
		frame4summary.add(bs);

		st_flightdep.setBounds(230,135,300,25);
		frame4summary.add(st_flightdep);

		st1.setBounds(230,155,300,25);
		frame4summary.add(st1);

		st_flightret.setBounds(230,180,300,25);
		frame4summary.add(st_flightret);

		st2.setBounds(230,200,300,25);
		frame4summary.add(st2);

		tax.setBounds(230,195,300,100);
		frame4summary.add(tax);

		lbl_bundle.setBounds(230,220,300,100);
		frame4summary.add(lbl_bundle);

		af.setBounds(230,245,300,100);
		frame4summary.add(af);

		fs.setBounds(230,270,300,100);
		frame4summary.add(fs);

		dpsc.setBounds(230,295,300,100);
		frame4summary.add(dpsc);

		phv.setBounds(230,320,300,100);
		frame4summary.add(phv);

		st3.setBounds(230,345,300,100);
		frame4summary.add(st3);

		total.setBounds(230,370,300,100);
		frame4summary.add(total);

		pm.setBounds(560,110,200,25);
		frame4summary.add(pm);

		payment_lbl.setBounds(560,210,100,25);
		frame4summary.add(payment_lbl);

		pay_alert.setBounds(560,270,200,25);
		frame4summary.add(pay_alert);

		change_lbl.setBounds(560,295,100,25);
		frame4summary.add(change_lbl);

    	//progress bars

		f4_progressLabel1.setBounds(195,50,200,30);
		f4_progressLabel2.setBounds(335,50,200,30);
		f4_progressLabel3.setBounds(482,50,100,30);
		f4_progressLabel3_payment.setBounds(495,50,100,30);
		f4_progressLabel4.setBounds(610,50,250,30);
    	f4_progressLabel3.setEnabled(false);

    	frame4summary.add(f4_progressLabel1);
    	frame4summary.add(f4_progressLabel2);
    	frame4summary.add(f4_progressLabel3_payment);
    	frame4summary.add(f4_progressLabel3);
    	frame4summary.add(f4_progressLabel4);

		//radiobutton
		cash.setBounds(560,145,200,60);
		frame4summary.add(cash);

		//button
		paybtn.setBounds(675,240,75,30);
		paybtn.setEnabled(false);
		frame4summary.add(paybtn);

		paybtncontinue.setBounds(665,485,125,30);
		frame4summary.add(paybtncontinue);

		paybtnback.setBounds(515,485,125,30);
		frame4summary.add(paybtnback);

		//textfields
		payment_tf.setBounds(560,240,100,30);
		payment_tf.setEnabled(false);
		frame4summary.add(payment_tf);

		change_tf.setBounds(560,325,100,30);
		change_tf.setEditable(false);
		frame4summary.add(change_tf);

    	//colors
    	Container f4_contentPane = frame4summary.getContentPane();
        f4_contentPane.setBackground(Color.WHITE);

    	paybtncontinue.setBackground(Color.decode("#ed7d31"));
    	paybtncontinue.setForeground(Color.WHITE);
    	paybtnback.setBackground(Color.WHITE);
    	f4_progressLabel3.setBackground(Color.decode("#ed7d31"));
    	f4_progressLabel3_payment.setForeground(Color.WHITE);
    	cash.setBackground(Color.WHITE);

    	paybtn.setBackground(Color.decode("#ed7d31"));
    	paybtn.setForeground(Color.WHITE);

    	//font
    	f4_progressLabel1.setFont(calsans.deriveFont(Font.BOLD,16f));
    	f4_progressLabel2.setFont(calsans.deriveFont(Font.BOLD,16f));
    	f4_progressLabel3.setFont(new Font("Arial", Font.BOLD, 14));
    	f4_progressLabel4.setFont(calsans.deriveFont(Font.BOLD,16f));
    	f4_progressLabel3_payment.setFont(calsans.deriveFont(Font.BOLD,16f));

    	payment_lbl.setFont(calsans.deriveFont(18f));
    	pay_alert.setFont(calsans.deriveFont(14f));
    	change_lbl.setFont(calsans.deriveFont(18f));

		st_flightdep.setFont(new Font("Arial", Font.PLAIN, 14));
		st1.setFont(calsans.deriveFont(16f));
		st_flightret.setFont(new Font("Arial", Font.PLAIN, 14));
		st2.setFont(calsans.deriveFont(16f));
		lbl_bundle.setFont(new Font("Arial", Font.PLAIN, 14));
		af.setFont(new Font("Arial", Font.PLAIN, 14));
		fs.setFont(new Font("Arial", Font.PLAIN, 14));
		dpsc.setFont(new Font("Arial", Font.PLAIN, 14));
		phv.setFont(new Font("Arial", Font.PLAIN, 14));
		tax.setFont(calsans.deriveFont(16f));
		st3.setFont(calsans.deriveFont(16f));

        bs.setFont(calsans.deriveFont(22f));
        pm.setFont(calsans.deriveFont(22f));
		total.setFont(calsans.deriveFont(Font.BOLD,20f));

		payment_tf.setFont(new Font("Arial", Font.PLAIN, 14));
		change_tf.setFont(new Font("Arial", Font.PLAIN, 14));

		paybtncontinue.setFont(calsans.deriveFont(18f));
		paybtn.setFont(calsans.deriveFont(18f));
		paybtnback.setFont(calsans.deriveFont(18f));
		cash.setFont(calsans.deriveFont(18f));

	//FRAME 5

    	//progress bars

		f5_progressLabel1.setBounds(195,50,200,30);
		f5_progressLabel2.setBounds(335,50,200,30);
		f5_progressLabel3.setBounds(482,50,200,30);
		f5_progressLabel4.setBounds(650,50,125,30);//button
		f5_progressLabel4_confirm.setBounds(662,50,100,30); //label
    	f5_progressLabel4.setEnabled(false);

    	frame5.add(f5_progressLabel1);
    	frame5.add(f5_progressLabel2);
    	frame5.add(f5_progressLabel3);
    	frame5.add(f5_progressLabel4_confirm);
    	frame5.add(f5_progressLabel4);

    	//color
    	f5_progressLabel4.setBackground(Color.decode("#ed7d31"));
    	f5_progressLabel4_confirm.setForeground(Color.WHITE);

    	f5_btn_viewreceipt.setBackground(Color.decode("#ed7d31"));
    	f5_btn_viewreceipt.setForeground(Color.WHITE);

    	f5_btn_bookanother.setBackground(Color.decode("#ed7d31"));
    	f5_btn_bookanother.setForeground(Color.WHITE);

    	//font
    	f5_progressLabel1.setFont(calsans.deriveFont(Font.BOLD,16f));
    	f5_progressLabel2.setFont(calsans.deriveFont(Font.BOLD,16f));
    	f5_progressLabel3.setFont(calsans.deriveFont(Font.BOLD,16f));
    	f5_progressLabel4.setFont(new Font("Arial", Font.BOLD, 14));
    	f5_progressLabel4_confirm.setFont(calsans.deriveFont(Font.BOLD,16f));

    	f5_btn_viewreceipt.setFont(calsans.deriveFont(Font.BOLD,30f));
    	f5_btn_bookanother.setFont(calsans.deriveFont(Font.BOLD,30f));

		//setbounds
		f5_btn_viewreceipt.setBounds(300,310,400,50);
		f5_btn_bookanother.setBounds(300,385,400,50);

		//frame5 add
		frame5.add(f5_btn_viewreceipt);
		frame5.add(f5_btn_bookanother);

		//fonts
	}

	// Add these methods to your class
	private static String formatDate(Date date) {
	    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
	    return formatter.format(date);
	}

	private static String formatTime(String departTime, String arriveTime) {
	    SimpleDateFormat displayFormat = new SimpleDateFormat("HH:mm:ss");
	    SimpleDateFormat parseFormat = new SimpleDateFormat("hh:mm a");

	    try {
	        Date date1 = parseFormat.parse(departTime);
	        Date date2 = parseFormat.parse(arriveTime);

	        String depTime = displayFormat.format(date1);
	        String arrTime = displayFormat.format(date2);

	        return depTime + " - " + arrTime;
	    } catch (ParseException e) {
	        e.printStackTrace();
	        return "N/A";
	    }
	}

	private static boolean processSeatsResultSet(ResultSet seats_rs, List<String> allTakenSeats, List<String> allTakenRetSeats) throws SQLException {
	    // Your existing logic for processing seats result set goes here
	    // ...
	    return false; // Replace this with your actual return value
	}

	private static void disableSeatBySeatName(String seatName, boolean isDeparture) {
	    // Disable the checkboxes for the taken seats based on isDeparture
	    switch (seatName) {
	        case "A1": case "A2": case "A3": case "A4": case "A5":
	        case "B1": case "B2": case "B3": case "B4": case "B5":
	        case "C1": case "C2": case "C3": case "C4": case "C5":
	        case "D1": case "D2": case "D3": case "D4": case "D5":
	            disableSeat(seatName, isDeparture);
	            break;
	        // Add similar cases for other checkboxes
	        // ...
	    }
	}

	private static void disableSeat(String seatName, boolean isDeparture) {
	    // Map seatName to the actual JCheckBox object
	    JCheckBox seatCheckBox = getSeatCheckBox(seatName);

	    if (seatCheckBox != null) {
	        if (isDeparture) {
	            disableDepartureSeat(seatCheckBox);
	        } else {
	            disableReturningSeat(seatCheckBox);
	        }
	    }
	}

	private static JCheckBox getSeatCheckBox(String seatName) {
	    // Implement logic to retrieve the JCheckBox based on the seat name
	    // For example, if you have individual variables (A1, B1, etc.), you can use a switch statement
	    // Replace this with your actual logic
	    switch (seatName) {
	        case "A1": return A1;
	        case "A2": return A2;
	        case "A3": return A3;
	        case "A4": return A4;
	        case "A5": return A5;

	        case "B1": return B1;
	        case "B2": return B2;
	        case "B3": return B3;
	        case "B4": return B4;
	        case "B5": return B5;

	        case "C1": return C1;
	        case "C2": return C2;
	        case "C3": return C3;
	        case "C4": return C4;
	        case "C5": return C5;

	        case "D1": return D1;
	        case "D2": return D2;
	        case "D3": return D3;
	        case "D4": return D4;
	        case "D5": return D5;

	        default: return null; // Handle the case when the seat name is not found
	    }
	}

	private static void disableDepartureSeat(JCheckBox seatCheckBox) {
	    seatCheckBox.setIcon(seatTaken);
	    seatCheckBox.addActionListener(new ActionListener() {
	        @Override
	        public void actionPerformed(ActionEvent e) {
	            if (isSeatTaken(seatCheckBox)) {
	                // Prevent the checkbox from being selected for taken seats
	                seatCheckBox.setSelected(false);
	            } else {
	                // Allow selection for available seats
	                // You can add your logic for seat selection here
	            }
	        }
	    });
	}

	private static void disableReturningSeat(JCheckBox seatCheckBox) {
	    seatCheckBox.setIcon(seatTaken);
	    seatCheckBox.addActionListener(new ActionListener() {
	        @Override
	        public void actionPerformed(ActionEvent e) {
	            if (isSeatTaken(seatCheckBox)) {
	                // Prevent the checkbox from being selected for taken seats
	                seatCheckBox.setSelected(false);
	            } else {
	                // Allow selection for available seats
	                // You can add your logic for seat selection here
	            }
	        }
	    });
	}

	private static boolean isSeatTaken(JCheckBox seatCheckBox) {
	    // Implement logic to determine whether the seat is taken
	    // For example, you can check if the seat icon is set to seatTaken
	    return seatCheckBox.getIcon() == seatTaken;
	}

// NEW VALIDATION OF GUEST DETAILSSSS
    private static String validateInputs(
    	JTextField fname_tf,
        JTextField mname_tf,
        JTextField lname_tf,
        JTextField contactNum_tf,
        JTextField email_tf
    	) {

	    // Get the text from the JTextField objects
	    String fname = fname_tf.getText();
	    String mname = mname_tf.getText();
	    String lname = lname_tf.getText();
	    String contact = contactNum_tf.getText();
	    String email = email_tf.getText();

	    List<String> invalidInputs = new ArrayList<>();

	    // Validate fname
	    if (!isValidName(fname)) {
	        invalidInputs.add("Invalid first name.");
	        fname_tf.setText("");
	    }

	    // Validate mname
	    if (!isValidName(mname)) {
	        invalidInputs.add("Invalid middle name.");
	        mname_tf.setText("");
	    }

	    // Validate lname
	    if (!isValidName(lname)) {
	        invalidInputs.add("Invalid last name.");
	        lname_tf.setText("");
	    }

	    // Validate contactNum
	    if (!isValidContactNumber(contact)) {
	        invalidInputs.add("Invalid contact number.");
	        contactNum_tf.setText("");
	    }

	    // Validate email address
	    if (!isValidEmail(email)) {
	        invalidInputs.add("Invalid email address.");
	        email_tf.setText("");
	    }

	    // Check if there are any invalid inputs
	    if (!invalidInputs.isEmpty()) {
	        // merges all the invalid inputs
	        return String.join("\n", invalidInputs);
	    }

	    // No invalid inputs
	    return null;
		}

		// Validation func
		private static boolean isValidName(String name) {
		    return name.matches("[a-zA-Z]+");
		}

		private static boolean isValidContactNumber(String contact) {
		    return contact.matches("\\d+");
		}

		private static boolean isValidEmail(String email) {
		    return email.matches("[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}");
		}

	// NEW CODE FOR BORDER RADIUSSSS

	public static class RoundedButton extends JButton {
    public RoundedButton(String text) {
        super(text);
        setContentAreaFilled(false);
    }

    @Override
    protected void paintComponent(Graphics g) {
        if (getModel().isArmed()) {
            g.setColor(Color.lightGray);
        } else {
            g.setColor(getBackground());
        }
        g.fillRoundRect(0, 0, getWidth(), getHeight(), 10, 10);
        super.paintComponent(g);
    }

    @Override
    protected void paintBorder(Graphics g) {
        g.setColor(getForeground());
        g.drawRoundRect(0, 0, getWidth() - 1, getHeight() - 1, 10, 10);
    }
}

}