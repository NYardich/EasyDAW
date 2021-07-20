package Iteration1;

import java.awt.*;
import java.sql.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;

// Inheritance of JPanel
public class MusicApp extends JPanel {

	// Database [Use your own user + pass to connect, db running on localhost using mySQL]
	private static final String user = "";
	private static final String pass = "";
	private static final String dbURL = "jdbc:mysql://localhost:3306/easydaw?user=" + user
			+ "&password=" + pass;

	// Fonts and Colors
	static Color darkTurquoise = Color.decode("#006768");
	static Color green = Color.decode("#cbffcb");
	static Font LSlarge = new Font("Lucida Sans", Font.BOLD, 60);
	static Font LSlargeitalics = new Font("Lucida Sans", Font.BOLD + Font.ITALIC, 60);
	static Font LS = new Font("Lucida Sans", Font.PLAIN, 15);
	static Font LSitalics = new Font("Lucida Sans", Font.ITALIC, 15);

	// Images
	static ImageIcon Staff = new ImageIcon("Staff.png");

	// Keys [ARRAYS]
	static String[] A = { "A", "B", "C#", "D", "E", "F#", "G#" };
	static String[] B = { "B", "C#", "D#", "E", "F#", "G#", "A#" };
	static String[] C = { "C", "D", "E", "F", "G", "A", "B" };
	static String[] D = { "D", "E", "F#", "G", "A", "B", "C#" };
	static String[] E = { "E", "F#", "G#", "A", "B", "C#", "D#" };
	static String[] F = { "F", "G", "A", "Bb", "C", "D", "E" };
	static String[] G = { "G", "A", "B", "C", "D", "E", "F#" };
	// JComboBox keys = new JComboBox(key);

	// Global Variables
	static int BPM;
	static String Key = "C";
	static boolean Metronome = true;
	static String Instrument = "Piano";

	/* START */

	public static void main(String[] args) throws Exception {
		mainMenu();
	}

	/* -----LOGIN SCREEN----- */

	public static void Login() {

		JFrame login = new JFrame();
		JPanel top = new JPanel();
		JPanel middle = new JPanel();
		login.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		login.setLayout(new BorderLayout());

		// Header
		JLabel loginText = new JLabel("Login");
		loginText.setFont(LSlargeitalics);
		loginText.setForeground(darkTurquoise);
		top.add(loginText);

		// Menu Items
		JLabel A = new JLabel("Username: ");
		A.setFont(LS);
		A.setBackground(green);
		A.setForeground(darkTurquoise);

		JTextField At = new JTextField(80);

		JLabel B = new JLabel("Password: ");
		B.setFont(LS);
		B.setBackground(green);
		B.setForeground(darkTurquoise);

		JPasswordField Bt = new JPasswordField(65);

		JButton C = new JButton("Forgot Password?");
		C.setFont(LS);
		C.setBorderPainted(false);
		C.setBackground(green);
		C.setForeground(darkTurquoise);
		C.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ForgotPass();
				login.dispose();
			}
		});

		JButton D = new JButton("Back");
		D.setFont(LS);
		D.setBorderPainted(false);
		D.setBackground(green);
		D.setForeground(Color.red);
		D.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mainMenu();
				login.dispose();
			}
		});

		JButton E = new JButton("(Need an Account?)");
		E.setFont(LS);
		E.setBorderPainted(false);
		E.setBackground(green);
		E.setForeground(darkTurquoise);
		E.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Register();
				login.dispose();
			}
		});

		JButton F = new JButton("Login");
		F.setFont(LS);
		F.setBorderPainted(false);
		F.setBackground(green);
		F.setForeground(Color.red);
		F.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				String USERNAME = At.getText();
				String PASSWORD = Bt.getText();

				try (Connection con = DriverManager.getConnection(dbURL); Statement stmt = con.createStatement();) {
					Statement stmtt = con.createStatement();
					ResultSet Account = stmtt.executeQuery("SELECT TEACHER FROM users WHERE USERNAME = \'" + USERNAME
							+ "\' AND PASSWORD = \'" + PASSWORD + "\';");

					// NESTED CONDITIONAL
					if (Account.next()) {
						if (Account.getInt(1) == 0) {
							Students Gamer = new Students();
							Gamer.setUsername(USERNAME);
							Gamer.setPassword(PASSWORD);
							ProjectSelect(Gamer);
							login.dispose();
						} else if (Account.getInt(1) == 1) {
							Teachers YeeHaw = new Teachers();
							YeeHaw.setUsername(USERNAME);
							YeeHaw.setPassword(PASSWORD);

							adminMenu(YeeHaw);
							login.dispose();
						}
					}
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});

		// Adding Items
		middle.add(A);
		middle.add(At);
		middle.add(B);
		middle.add(Bt);
		middle.add(C);
		middle.add(F);
		middle.add(D);
		middle.add(E);

		// Creating Frame
		top.setBackground(green);
		middle.setBackground(green);
		login.add(top, BorderLayout.NORTH);
		login.add(middle, BorderLayout.CENTER);
		login.setSize(1000, 600);
		login.setResizable(false);
		login.setTitle("EasyDAW - Login Screen");

		// Centering Window
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		login.setLocation(dim.width / 2 - login.getSize().width / 2, dim.height / 2 - login.getSize().height / 2);

		login.setVisible(true);
	}

	/* -----REGISTER SCREEN----- */

	public static void Register() {
		try (Connection con = DriverManager.getConnection(dbURL); Statement stmt = con.createStatement();) {

			JFrame register = new JFrame();
			JPanel top = new JPanel();
			JPanel middle = new JPanel();
			JPanel bottom = new JPanel();
			register.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			register.setLayout(new BorderLayout());

			// Header
			JLabel registerText = new JLabel("Register");
			registerText.setFont(LSlargeitalics);
			registerText.setForeground(darkTurquoise);
			top.add(registerText);

			// Menu Items
			JLabel A = new JLabel("Username: ");
			A.setFont(LS);
			A.setBackground(green);
			A.setForeground(darkTurquoise);

			JTextField At = new JTextField(80);

			JLabel B = new JLabel("Password: ");
			B.setFont(LS);
			B.setBackground(green);
			B.setForeground(darkTurquoise);

			JPasswordField Bt = new JPasswordField(65);

			JButton EA = new JButton("Existing Account?");
			EA.setFont(LS);
			EA.setBorderPainted(false);
			EA.setBackground(green);
			EA.setForeground(darkTurquoise);
			EA.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					Login();
					register.dispose();
				}
			});
			JCheckBox C = new JCheckBox("Are you a teacher?");
			C.setSelected(false);
			C.setFont(LS);
			C.setBackground(green);
			C.setForeground(darkTurquoise);

			JButton D = new JButton("Back");
			D.setFont(LS);
			D.setBorderPainted(false);
			D.setBackground(green);
			D.setForeground(Color.red);
			D.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					register.dispose();
					mainMenu();
				}
			});

			JLabel F = new JLabel("Teacher: ");
			F.setFont(LS);
			F.setBackground(green);
			F.setForeground(darkTurquoise);

			// SORTING
			String qTeachers = "SELECT USERNAME FROM users WHERE TEACHER = 1 ORDER BY USERNAME ASC;";
			Statement qTstmt = con.createStatement();
			ResultSet qTrset = qTstmt.executeQuery(qTeachers);
			JComboBox teachers = new JComboBox();
			// LOOP
			while (qTrset.next()) {
				teachers.addItem(qTrset.getString(1));
			}

			JLabel G = new JLabel("\nSecurity Question: ");
			G.setFont(LS);
			G.setBackground(green);
			G.setForeground(darkTurquoise);

			String[] security = { "What is your mother's maiden name?", "Where did you go to elementary school?",
					"In what year did you buy your first car?", "What was your first word?",
					"What hospital were you born in?" };
			// SORTING
			Arrays.sort(security);
			JComboBox securityQuestions = new JComboBox(security);

			JLabel H = new JLabel("Answer: ");
			H.setFont(LS);
			H.setBackground(green);
			H.setForeground(darkTurquoise);

			JTextField Ht = new JTextField(40);

			JButton E = new JButton("Register");
			E.setFont(LS);
			E.setBorderPainted(false);
			E.setBackground(green);
			E.setForeground(Color.red);
			E.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {

					// Checking if Username is unique
					try (Connection con = DriverManager.getConnection(dbURL); Statement stmt = con.createStatement();) {
						
						String qUsernames = "SELECT USERNAME FROM users WHERE USERNAME = \'" + At.getText() + "\';";
						Statement qUstmt = con.createStatement();
						ResultSet qUrset = qUstmt.executeQuery(qUsernames);
						
						if (!qUrset.next()) {
							mainMenu();
							register.dispose();
							int teach = C.isSelected() ? 1 : 0;
							PreparedStatement instuser;
							instuser = con.prepareStatement("INSERT INTO users(USERNAME, PASSWORD, SECQUES, SECANS, "
									+ "TEACHER, TEACH, FORGOTPASS) VALUES (?, ?, ?, ?, ?, ?, ?)");
							instuser.setString(1, At.getText());
							instuser.setString(2, Bt.getText());
							instuser.setString(3, (String) securityQuestions.getSelectedItem());
							instuser.setString(4, Ht.getText());
							instuser.setInt(5, teach);
							instuser.setString(6, (String) teachers.getSelectedItem());
							instuser.setInt(7, 0);
							instuser.executeUpdate();
						}
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
				}
			});

			// Adding Items
			middle.add(A);
			middle.add(At);
			middle.add(B);
			middle.add(Bt);
			middle.add(EA);
			middle.add(F);
			middle.add(teachers);
			middle.add(G);
			middle.add(securityQuestions);
			middle.add(H);
			middle.add(Ht);
			middle.add(E);
			middle.add(D);

			bottom.add(C);
			// Creating Frame
			top.setBackground(green);
			middle.setBackground(green);
			bottom.setBackground(green);
			register.add(top, BorderLayout.NORTH);
			register.add(middle, BorderLayout.CENTER);
			register.add(bottom, BorderLayout.SOUTH);
			register.setSize(1000, 600);
			register.setResizable(false);
			register.setTitle("EasyDAW - Register Screen");

			// Centering Window
			Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
			register.setLocation(dim.width / 2 - register.getSize().width / 2,
					dim.height / 2 - register.getSize().height / 2);

			register.setVisible(true);
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
	}

	/* -----FORGOT PASSWORD SCREEN----- */

	public static void ForgotPass() {

		JFrame forgotpass = new JFrame();
		JPanel top = new JPanel();
		JPanel middle = new JPanel();
		forgotpass.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		forgotpass.setLayout(new BorderLayout());

		// Header
		JLabel fpassText = new JLabel("Forgot Password");
		fpassText.setFont(LSlargeitalics);
		fpassText.setForeground(darkTurquoise);
		top.add(fpassText);

		// Menu Items
		JLabel A = new JLabel("Username: ");
		A.setFont(LS);
		A.setBackground(green);
		A.setForeground(darkTurquoise);

		JTextField At = new JTextField(80);

		JButton D = new JButton("Back");
		D.setFont(LS);
		D.setBorderPainted(false);
		D.setBackground(green);
		D.setForeground(Color.red);
		D.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				forgotpass.dispose();
				Login();
			}
		});

		JLabel G = new JLabel("\nSecurity Question: ");
		G.setFont(LS);
		G.setBackground(green);
		G.setForeground(darkTurquoise);

		String[] security = { "What is your mother's maiden name?", "Where did you go to elementary schoo?l",
				"In what year did you buy your first car?", "What was your first word?",
				"What hospital were you born in?" };
		// SORTING
		Arrays.sort(security);
		JComboBox securityQuestions = new JComboBox(security);

		JLabel H = new JLabel("Answer: ");
		H.setFont(LS);
		H.setBackground(green);
		H.setForeground(darkTurquoise);

		JTextField Ht = new JTextField(55);

		JButton E = new JButton("Reset Password");
		E.setFont(LS);
		E.setBorderPainted(false);
		E.setBackground(green);
		E.setForeground(Color.red);
		E.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String SECANS = Ht.getText();
				String SECQUES = (String) securityQuestions.getSelectedItem();
				String USERNAME = At.getText();
				try (Connection con = DriverManager.getConnection(dbURL); Statement stmt = con.createStatement();) {
					Statement stmtt = con.createStatement();
					ResultSet Account = stmtt.executeQuery("SELECT USERNAME FROM users WHERE USERNAME = \'" + USERNAME
							+ "\' AND SECQUES = \'" + SECQUES + "\' AND SECANS = \'" + SECANS + "\';");
					if (Account.next()) {
						PreparedStatement ForgtPass = con.prepareStatement(
								"UPDATE users SET FORGOTPASS = \'1\' WHERE USERNAME = \'" + USERNAME + "\';");
						ForgtPass.executeUpdate();
						mainMenu();
						forgotpass.dispose();
					}
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});

		// Adding Items
		middle.add(A);
		middle.add(At);
		middle.add(G);
		middle.add(securityQuestions);
		middle.add(H);
		middle.add(Ht);
		middle.add(E);
		middle.add(D);

		// Creating Frame
		top.setBackground(green);
		middle.setBackground(green);
		forgotpass.add(top, BorderLayout.NORTH);
		forgotpass.add(middle, BorderLayout.CENTER);
		forgotpass.setSize(1000, 600);
		forgotpass.setResizable(false);
		forgotpass.setTitle("EasyDAW - Forgot Password");

		// Centering Window
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		forgotpass.setLocation(dim.width / 2 - forgotpass.getSize().width / 2,
				dim.height / 2 - forgotpass.getSize().height / 2);

		forgotpass.setVisible(true);
	}

	/* -----New Project Screen----- */

	public static void NewProject(Students STUDENT) {

		JFrame createProject = new JFrame();
		JPanel top = new JPanel();
		JPanel middle = new JPanel();
		createProject.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		createProject.setLayout(new BorderLayout());

		// Header
		JLabel fpassText = new JLabel("Create Project");
		fpassText.setFont(LSlargeitalics);
		fpassText.setForeground(darkTurquoise);
		top.add(fpassText);

		// Menu Items
		JLabel A = new JLabel("Key:");
		A.setFont(LS);
		A.setBackground(green);
		A.setForeground(darkTurquoise);

		String[] key = { "A", "B", "C", "D", "E", "F", "G" };
		JComboBox keys = new JComboBox(key);

		JLabel B = new JLabel("BPM: ");
		B.setFont(LS);
		B.setBackground(green);
		B.setForeground(darkTurquoise);

		JTextField Bt = new JTextField(3);

		JLabel C = new JLabel("Project Name: ");
		C.setFont(LS);
		C.setBackground(green);
		C.setForeground(darkTurquoise);

		JTextField Ct = new JTextField(10);

		JButton D = new JButton("Cancel");
		D.setFont(LS);
		D.setBorderPainted(false);
		D.setBackground(green);
		D.setForeground(Color.red);
		D.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				createProject.dispose();
				ProjectSelect(STUDENT);
			}
		});

		JButton E = new JButton("Create");
		E.setFont(LS);
		E.setBorderPainted(false);
		E.setBackground(green);
		E.setForeground(Color.red);
		E.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (Integer.parseInt(Bt.getText()) > 0) {
					BPM = (Integer.parseInt(Bt.getText()) / 60) * 1000;
					Key = (String) keys.getSelectedItem();
					String PROJNAME = Ct.getText();

					try (Connection con = DriverManager.getConnection(dbURL); Statement stmt = con.createStatement();) {

						String qProjects = "SELECT PROJNAME FROM projects WHERE USERNAME = \'" + STUDENT.getUsername()
								+ "\' AND" + " PROJNAME =\'" + Ct.getText() + "\';";
						Statement qPstmt = con.createStatement();
						ResultSet qPrset = qPstmt.executeQuery(qProjects);
						if (!qPrset.next()) {
							PreparedStatement instproj = con
									.prepareStatement("INSERT INTO projects (PROJNAME, USERNAME, SONGKEY, TEMPO) "
											+ "VALUES (\'" + Ct.getText() + "\',\'" + STUDENT.getUsername() + "\',\'"
											+ Key.charAt(0) + "\',\'" + BPM + "\')");
							instproj.executeUpdate();
						}
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
					MusicPlay(STUDENT, PROJNAME);
					createProject.dispose();

				}
			}
		});

		// Adding Items
		middle.add(A);
		middle.add(keys);
		middle.add(B);
		middle.add(Bt);
		middle.add(C);
		middle.add(Ct);
		middle.add(E);
		middle.add(D);

		// Creating Frame
		top.setBackground(green);
		middle.setBackground(green);
		createProject.add(top, BorderLayout.NORTH);
		createProject.add(middle, BorderLayout.CENTER);
		createProject.setSize(1000, 600);
		createProject.setResizable(false);
		createProject.setTitle("EasyDAW - New Project");

		// Centering Window
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		createProject.setLocation(dim.width / 2 - createProject.getSize().width / 2,
				dim.height / 2 - createProject.getSize().height / 2);

		createProject.setVisible(true);
	}

	/* -----ADMIN MENU----- */

	public static void adminMenu(Teachers TEACHER) {

		JFrame aMenu = new JFrame();
		JPanel top = new JPanel();
		JPanel middle = new JPanel();
		aMenu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		aMenu.setLayout(new BorderLayout());

		// Header
		JLabel easyDAW = new JLabel("Admin Menu");
		easyDAW.setFont(LSlargeitalics);
		easyDAW.setForeground(darkTurquoise);
		top.add(easyDAW);

		// Menu Items
		JButton A = new JButton("Retrieve Account");
		A.setFont(LS);
		A.setBorderPainted(false);
		A.setBackground(green);
		A.setForeground(darkTurquoise);
		A.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				RetrieveAcct(TEACHER);
				aMenu.dispose();
			}
		});

		JButton B = new JButton("Upgrade Account");
		B.setFont(LS);
		B.setBorderPainted(false);
		B.setBackground(green);
		B.setForeground(darkTurquoise);
		B.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				UpgradeAcct(TEACHER);
				aMenu.dispose();
			}
		});

		JButton C = new JButton("Transfer Account");
		C.setFont(LS);
		C.setBorderPainted(false);
		C.setBackground(green);
		C.setForeground(darkTurquoise);
		C.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TransferAcct(TEACHER);
				aMenu.dispose();
			}
		});

		JButton D = new JButton("Signout");
		D.setFont(LS);
		D.setBorderPainted(false);
		D.setBackground(green);
		D.setForeground(Color.red);
		D.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mainMenu();
				aMenu.dispose();
			}
		});

		// Adding Items
		middle.add(A);
		middle.add(B);
		middle.add(C);
		middle.add(D);

		// Creating Frame
		top.setBackground(green);
		middle.setBackground(green);
		aMenu.add(top, BorderLayout.NORTH);
		aMenu.add(middle, BorderLayout.CENTER);
		aMenu.setSize(1000, 600);
		aMenu.setResizable(false);
		aMenu.setTitle("EasyDAW - Admin Menu");

		// Centering Window
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		aMenu.setLocation(dim.width / 2 - aMenu.getSize().width / 2, dim.height / 2 - aMenu.getSize().height / 2);

		aMenu.setVisible(true);
	}

	/* -----RETRIEVE ACCOUNT----- */
	public static void RetrieveAcct(Teachers TEACHER) {
		try (Connection con = DriverManager.getConnection(dbURL); Statement stmt = con.createStatement();) {
			JFrame accountretrieval = new JFrame();
			JPanel top = new JPanel();
			JPanel middle = new JPanel();
			accountretrieval.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			accountretrieval.setLayout(new BorderLayout());

			// Header
			JLabel fpassText = new JLabel("Account Retrieval");
			fpassText.setFont(LSlargeitalics);
			fpassText.setForeground(darkTurquoise);
			top.add(fpassText);

			// Menu Items

			// SORTING
			String qLStudents = "SELECT USERNAME FROM users WHERE FORGOTPASS = 1 ORDER BY USERNAME ASC;";
			Statement qLstmt = con.createStatement();
			ResultSet qLrset = qLstmt.executeQuery(qLStudents);
			JComboBox users = new JComboBox();
			while (qLrset.next()) {
				users.addItem(qLrset.getString(1));
			}

			JLabel B = new JLabel("New Password: ");
			B.setFont(LS);
			B.setBackground(green);
			B.setForeground(darkTurquoise);

			JTextField Bt = new JTextField(60);

			JButton A = new JButton("Reset Password");
			A.setFont(LS);
			A.setBorderPainted(false);
			A.setBackground(green);
			A.setForeground(Color.red);
			A.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					try (Connection con = DriverManager.getConnection(dbURL); Statement stmt = con.createStatement();) {
						PreparedStatement upduserpasslost = con
								.prepareStatement("UPDATE users SET PASSWORD = ? WHERE USERNAME = ?");
						upduserpasslost.setString(1, Bt.getText());
						upduserpasslost.setString(2, (String) users.getSelectedItem());
						upduserpasslost.executeUpdate();
						adminMenu(TEACHER);
						accountretrieval.dispose();
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
				}
			});

			JButton C = new JButton("Back");
			C.setFont(LS);
			C.setBorderPainted(false);
			C.setBackground(green);
			C.setForeground(Color.red);
			C.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					accountretrieval.dispose();
					adminMenu(TEACHER);
				}
			});

			// Adding Items
			middle.add(users);
			middle.add(B);
			middle.add(Bt);
			middle.add(A);
			middle.add(C);

			// Creating Frame
			top.setBackground(green);
			middle.setBackground(green);
			accountretrieval.add(top, BorderLayout.NORTH);
			accountretrieval.add(middle, BorderLayout.CENTER);
			accountretrieval.setSize(1000, 600);
			accountretrieval.setResizable(false);
			accountretrieval.setTitle("EasyDAW - Retrieve Account");

			// Centering Window
			Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
			accountretrieval.setLocation(dim.width / 2 - accountretrieval.getSize().width / 2,
					dim.height / 2 - accountretrieval.getSize().height / 2);

			accountretrieval.setVisible(true);
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
	}

	/* -----TRANSFER ACCOUNT----- */

	public static void TransferAcct(Teachers TEACHER) {
		try (Connection con = DriverManager.getConnection(dbURL); Statement stmt = con.createStatement();) {
			JFrame accounttransfer = new JFrame();
			JPanel top = new JPanel();
			JPanel middle = new JPanel();
			accounttransfer.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			accounttransfer.setLayout(new BorderLayout());

			// Header
			JLabel fpassText = new JLabel("Transfer Account");
			fpassText.setFont(LSlargeitalics);
			fpassText.setForeground(darkTurquoise);
			top.add(fpassText);

			// Menu Items

			JLabel A = new JLabel("Student: ");
			A.setFont(LS);
			A.setBackground(green);
			A.setForeground(darkTurquoise);

			// Sorting
			String qStudents = "SELECT USERNAME FROM users WHERE TEACHER = 0 ORDER BY USERNAME ASC;";
			Statement qSstmt = con.createStatement();
			ResultSet qSrset = qSstmt.executeQuery(qStudents);
			JComboBox users = new JComboBox();
			while (qSrset.next()) {
				users.addItem(qSrset.getString(1));
			}

			JLabel B = new JLabel("New Teacher: ");
			B.setFont(LS);
			B.setBackground(green);
			B.setForeground(darkTurquoise);

			// Sorting
			String qTeachers = "SELECT USERNAME FROM users WHERE TEACHER = 1 ORDER BY USERNAME ASC;";
			Statement qTstmt = con.createStatement();
			ResultSet qTrset = qTstmt.executeQuery(qTeachers);
			JComboBox teachers = new JComboBox();
			while (qTrset.next()) {
				teachers.addItem(qTrset.getString(1));
			}

			JButton C = new JButton("Confirm");
			C.setFont(LS);
			C.setBorderPainted(false);
			C.setBackground(green);
			C.setForeground(Color.red);
			C.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					try (Connection con = DriverManager.getConnection(dbURL); Statement stmt = con.createStatement();) {
						PreparedStatement upduserpassword = con
								.prepareStatement("UPDATE users SET TEACH = ? WHERE USERNAME = ?");
						upduserpassword.setString(1, (String) teachers.getSelectedItem());
						upduserpassword.setString(2, (String) users.getSelectedItem());
						upduserpassword.executeUpdate();
						adminMenu(TEACHER);
						accounttransfer.dispose();
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
				}
			});

			JButton D = new JButton("Back");
			D.setFont(LS);
			D.setBorderPainted(false);
			D.setBackground(green);
			D.setForeground(Color.red);
			D.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					accounttransfer.dispose();
					adminMenu(TEACHER);
				}
			});

			// Adding Items
			middle.add(A);
			middle.add(users);
			middle.add(B);
			middle.add(teachers);
			middle.add(C);
			middle.add(D);

			// Creating Frame
			top.setBackground(green);
			middle.setBackground(green);
			accounttransfer.add(top, BorderLayout.NORTH);
			accounttransfer.add(middle, BorderLayout.CENTER);
			accounttransfer.setSize(1000, 600);
			accounttransfer.setResizable(false);
			accounttransfer.setTitle("EasyDAW - Account Transfer");

			// Centering Window
			Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
			accounttransfer.setLocation(dim.width / 2 - accounttransfer.getSize().width / 2,
					dim.height / 2 - accounttransfer.getSize().height / 2);

			accounttransfer.setVisible(true);
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
	}
	/* -----UPGRADE ACCOUNT----- */

	public static void UpgradeAcct(Teachers TEACHER) {
		try (Connection con = DriverManager.getConnection(dbURL); Statement stmt = con.createStatement();) {

			JFrame accountupgrade = new JFrame();
			JPanel top = new JPanel();
			JPanel middle = new JPanel();
			accountupgrade.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			accountupgrade.setLayout(new BorderLayout());

			// Header
			JLabel fpassText = new JLabel("Upgrade Account");
			fpassText.setFont(LSlargeitalics);
			fpassText.setForeground(darkTurquoise);
			top.add(fpassText);

			// Menu Items

			JLabel A = new JLabel("Student: ");
			A.setFont(LS);
			A.setBackground(green);
			A.setForeground(darkTurquoise);

			// Sorting
			String qTStudents = "SELECT USERNAME FROM users WHERE TEACH = \'" + TEACHER.getUsername()
					+ "\' ORDER BY USERNAME ASC;";
			Statement qTstmt = con.createStatement();
			ResultSet qTrset = qTstmt.executeQuery(qTStudents);
			JComboBox users = new JComboBox();
			while (qTrset.next()) {
				users.addItem(qTrset.getString(1));
			}

			JLabel B = new JLabel("Project: ");
			B.setFont(LS);
			B.setBackground(green);
			B.setForeground(darkTurquoise);

			String qSProjects = "SELECT ProjName FROM projects WHERE USERNAME = \'" + (String) users.getSelectedItem()
					+ "\' ORDER BY ProjName ASC;";
			Statement qSstmt = con.createStatement();
			ResultSet qSrset = qSstmt.executeQuery(qSProjects);
			JComboBox projects = new JComboBox();
			while (qSrset.next()) {
				projects.addItem(qSrset.getString(1));
			}

			JLabel C = new JLabel("Student Rating: ");
			C.setFont(LS);
			C.setBackground(green);
			C.setForeground(darkTurquoise);

			String[] ratings = { "1", "2", "3", "4", "5", "6", "7", "8", "9", "10" };
			JComboBox grade = new JComboBox(ratings);

			JLabel D = new JLabel("Project Grade: ");
			D.setFont(LS);
			D.setBackground(green);
			D.setForeground(darkTurquoise);

			JTextField Dt = new JTextField(10);

			JButton E = new JButton("Confirm");
			E.setFont(LS);
			E.setBorderPainted(false);
			E.setBackground(green);
			E.setForeground(Color.red);
			E.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					try (Connection con = DriverManager.getConnection(dbURL); Statement stmt = con.createStatement();) {
						PreparedStatement updprojscore = con
								.prepareStatement("UPDATE  projects SET Grade = ? WHERE USERNAME = ?");
						updprojscore.setString(1, Dt.getText()); // Parse Int
						updprojscore.setString(2, (String) users.getSelectedItem());
						updprojscore.executeUpdate();
						PreparedStatement upduserscore = con
								.prepareStatement("UPDATE users SET Rating = ? WHERE USERNAME = ?");
						upduserscore.setString(1, (String) grade.getSelectedItem());
						upduserscore.setString(2, (String) users.getSelectedItem());
						upduserscore.executeUpdate();
						adminMenu(TEACHER);
						accountupgrade.dispose();
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
				}
			});

			JButton F = new JButton("Back");
			F.setFont(LS);
			F.setBorderPainted(false);
			F.setBackground(green);
			F.setForeground(Color.red);
			F.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					accountupgrade.dispose();
					adminMenu(TEACHER);
				}
			});

			// Adding Items
			middle.add(A);
			middle.add(users);
			middle.add(B);
			middle.add(projects);
			middle.add(C);
			middle.add(grade);
			middle.add(D);
			middle.add(Dt);
			middle.add(E);
			middle.add(F);

			// Creating Frame
			top.setBackground(green);
			middle.setBackground(green);
			accountupgrade.add(top, BorderLayout.NORTH);
			accountupgrade.add(middle, BorderLayout.CENTER);
			accountupgrade.setSize(1000, 600);
			accountupgrade.setResizable(false);
			accountupgrade.setTitle("EasyDAW - Upgrade Account");

			// Centering Window
			Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
			accountupgrade.setLocation(dim.width / 2 - accountupgrade.getSize().width / 2,
					dim.height / 2 - accountupgrade.getSize().height / 2);

			accountupgrade.setVisible(true);
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
	}

	/* -----MAIN MENU----- */

	public static void mainMenu() {

		JFrame mMenu = new JFrame();
		JPanel top = new JPanel();
		JPanel bottom = new JPanel();
		JPanel middle = new JPanel();
		mMenu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mMenu.setLayout(new BorderLayout());

		// Header
		JLabel easyDAW = new JLabel("EasyDAW");
		easyDAW.setFont(LSlarge);
		easyDAW.setForeground(darkTurquoise);
		top.add(easyDAW);

		// South
		JLabel credits = new JLabel("Created by NICHOLAS YARDICH");
		credits.setFont(LSitalics);
		credits.setForeground(darkTurquoise);
		bottom.add(credits);

		// Menu Items
		JButton A = new JButton("Login");
		A.setFont(LS);
		A.setBorderPainted(false);
		A.setBackground(green);
		A.setForeground(darkTurquoise);
		A.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Login();
				mMenu.dispose();
			}
		});

		JButton B = new JButton("Register");
		B.setFont(LS);
		B.setBorderPainted(false);
		B.setBackground(green);
		B.setForeground(darkTurquoise);
		B.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Register();
				mMenu.dispose();
			}
		});

		JButton C = new JButton("Guest");
		C.setFont(LS);
		C.setBorderPainted(false);
		C.setBackground(green);
		C.setForeground(darkTurquoise);
		C.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Students GUEST = new Students();
				GUEST.setUsername("GUEST");
				GUEST.setPassword("TEMP");
				GUEST.setTeacherUsername("GUEST");
				NewProject(GUEST);
				mMenu.dispose();
			}
		});

		JButton D = new JButton("Exit");
		D.setFont(LS);
		D.setBorderPainted(false);
		D.setBackground(green);
		D.setForeground(darkTurquoise);
		D.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mMenu.dispose();
			}
		});

		// Adding Items
		middle.add(A);
		middle.add(B);
		middle.add(C);
		middle.add(D);

		// Creating Frame
		top.setBackground(green);
		bottom.setBackground(green);
		middle.setBackground(green);
		mMenu.add(top, BorderLayout.NORTH);
		mMenu.add(middle, BorderLayout.CENTER);
		mMenu.add(bottom, BorderLayout.SOUTH);
		mMenu.setSize(1000, 600);
		mMenu.setResizable(false);
		mMenu.setTitle("EasyDAW");

		// Centering Window
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		mMenu.setLocation(dim.width / 2 - mMenu.getSize().width / 2, dim.height / 2 - mMenu.getSize().height / 2);

		mMenu.setVisible(true);

	}

	/* -----PROJECT SELECT----- */

	public static void ProjectSelect(Students STUDENT) {
		try (Connection con = DriverManager.getConnection(dbURL); Statement stmt = con.createStatement();) {

			JFrame pselect = new JFrame();
			JPanel top = new JPanel();
			JPanel middle = new JPanel();
			JPanel bottom = new JPanel();
			pselect.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			pselect.setLayout(new BorderLayout());

			// Header
			JLabel pselectText = new JLabel("Project Selection");
			pselectText.setFont(LSlargeitalics);
			pselectText.setForeground(darkTurquoise);
			top.add(pselectText);

			// Menu Items

			JLabel A = new JLabel("Select Project: ");
			A.setFont(LS);
			A.setBackground(green);
			A.setForeground(darkTurquoise);

			// Sorting
			String qUProjects = "SELECT ProjName FROM projects WHERE USERNAME = \'" + STUDENT.getUsername()
					+ "\' ORDER BY ProjName ASC;";
			Statement qUstmt = con.createStatement();
			ResultSet qUrset = qUstmt.executeQuery(qUProjects);
			JComboBox Projects = new JComboBox();
			while (qUrset.next()) {
				Projects.addItem(qUrset.getString(1));
			}
			
			JButton A2 = new JButton("Open Selected Project");
			A2.setFont(LS);
			A2.setBorderPainted(false);
			A2.setBackground(green);
			A2.setForeground(darkTurquoise);
			A2.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					MusicPlay(STUDENT, (String)Projects.getSelectedItem());
					pselect.dispose();
				}
			});
			
			JButton B = new JButton("New Project");
			B.setFont(LS);
			B.setBorderPainted(false);
			B.setBackground(green);
			B.setForeground(darkTurquoise);
			B.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					NewProject(STUDENT);
					pselect.dispose();
				}
			});

			JButton C = new JButton("Delete Projects");
			C.setFont(LS);
			C.setBorderPainted(false);
			C.setBackground(green);
			C.setForeground(darkTurquoise);
			C.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					ProjectDelete(STUDENT);
					pselect.dispose();
				}
			});

			JButton D = new JButton("Grade Report");
			D.setFont(LS);
			D.setBorderPainted(false);
			D.setBackground(green);
			D.setForeground(darkTurquoise);
			D.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					GradeReport(STUDENT);
					pselect.dispose();
				}
			});

			JButton E = new JButton("Log Out");
			E.setFont(LS);
			E.setBorderPainted(false);
			E.setBackground(green);
			E.setForeground(darkTurquoise);
			E.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					mainMenu();
					pselect.dispose();
				}
			});

			// Adding Items
			middle.add(A);
			middle.add(Projects);
			middle.add(A2);
			middle.add(B);
			middle.add(C);
			middle.add(D);
			middle.add(E);

			// Creating Frame
			top.setBackground(green);
			middle.setBackground(green);
			bottom.setBackground(green);
			pselect.add(top, BorderLayout.NORTH);
			pselect.add(middle, BorderLayout.CENTER);
			pselect.add(bottom, BorderLayout.SOUTH);
			pselect.setSize(1000, 600);
			pselect.setResizable(false);
			pselect.setTitle("EasyDAW - Project Select Screen");

			// Centering Window
			Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
			pselect.setLocation(dim.width / 2 - pselect.getSize().width / 2,
					dim.height / 2 - pselect.getSize().height / 2);

			pselect.setVisible(true);
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
	}

	/* -----GRADE REPORT----- */

	public static void GradeReport(Students STUDENT) {
		try (Connection con = DriverManager.getConnection(dbURL); Statement stmt = con.createStatement();) {
			JFrame grapeort = new JFrame();
			JPanel top = new JPanel();
			JPanel middle = new JPanel();
			JPanel bottom = new JPanel();
			grapeort.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			grapeort.setLayout(new BorderLayout());

			// Header
			JLabel grapeortText = new JLabel("Grade Report");
			grapeortText.setFont(LSlargeitalics);
			grapeortText.setForeground(darkTurquoise);
			top.add(grapeortText);

			// Menu Items

			JLabel A = new JLabel("Performance Rating: X/10");
			A.setFont(LS);
			A.setBackground(green);
			A.setForeground(darkTurquoise);

			String qURating = "SELECT RATING FROM users WHERE USERNAME = \'" + STUDENT.getUsername() + "\';";
			Statement qUmstmt = con.createStatement();
			ResultSet qUmrset = qUmstmt.executeQuery(qURating);
			qUmrset.next();
			JLabel PRating = new JLabel(qUmrset.getString(1));

			// Sorting
			String qUProjects = "SELECT ProjName FROM projects WHERE USERNAME = \'" + STUDENT.getUsername()
					+ "\' ORDER BY ProjName ASC;";
			Statement qUstmt = con.createStatement();
			ResultSet qUrset = qUstmt.executeQuery(qUProjects);
			JComboBox Projects = new JComboBox();
			while (qUrset.next()) {
				Projects.addItem(qUrset.getString(1));
			}
			JLabel B = new JLabel("Project Grade: X/100");
			B.setFont(LS);
			B.setBackground(green);
			B.setForeground(darkTurquoise);

			String qPGrade = "SELECT GRADE FROM projects WHERE PROJNAME = \'" + (String) Projects.getSelectedItem()
					+ "\';";
			Statement qPmstmt = con.createStatement();
			ResultSet qPmrset = qPmstmt.executeQuery(qPGrade);
			qPmrset.next();
			JLabel PGrade = new JLabel(qPmrset.getString(1));

			JButton C = new JButton("Back");
			C.setFont(LS);
			C.setBorderPainted(false);
			C.setBackground(green);
			C.setForeground(darkTurquoise);
			C.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					ProjectSelect(STUDENT);
					grapeort.dispose();
				}
			});

			// Adding Items
			middle.add(A);
			middle.add(PRating);
			middle.add(Projects);
			middle.add(B);
			middle.add(PGrade);
			middle.add(C);

			// Creating Frame
			top.setBackground(green);
			middle.setBackground(green);
			bottom.setBackground(green);
			grapeort.add(top, BorderLayout.NORTH);
			grapeort.add(middle, BorderLayout.CENTER);
			grapeort.add(bottom, BorderLayout.SOUTH);
			grapeort.setSize(1000, 600);
			grapeort.setResizable(false);
			grapeort.setTitle("EasyDAW - Grade Report Screen");

			// Centering Window
			Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
			grapeort.setLocation(dim.width / 2 - grapeort.getSize().width / 2,
					dim.height / 2 - grapeort.getSize().height / 2);

			grapeort.setVisible(true);
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
	}

	/* -----PROJECT DELETE----- */

	public static void ProjectDelete(Students STUDENT) {
		try (Connection con = DriverManager.getConnection(dbURL); Statement stmt = con.createStatement();) {
			JFrame pdelete = new JFrame();
			JPanel top = new JPanel();
			JPanel middle = new JPanel();
			JPanel bottom = new JPanel();
			pdelete.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			pdelete.setLayout(new BorderLayout());

			// Header
			JLabel pdeleteText = new JLabel("Project Delete");
			pdeleteText.setFont(LSlargeitalics);
			pdeleteText.setForeground(darkTurquoise);
			top.add(pdeleteText);

			// Menu Items

			JLabel A = new JLabel("Select Project: ");
			A.setFont(LS);
			A.setBackground(green);
			A.setForeground(darkTurquoise);

			// Sorting
			String qUProjects = "SELECT ProjName FROM projects WHERE USERNAME = \'" + STUDENT.getUsername()
					+ "\' ORDER BY ProjName ASC;";
			Statement qUstmt = con.createStatement();
			ResultSet qUrset = qUstmt.executeQuery(qUProjects);
			JComboBox Projects = new JComboBox();
			while (qUrset.next()) {
				Projects.addItem(qUrset.getString(1));
			}

			JLabel B = new JLabel("Type �Y� to confirm deletion");
			B.setFont(LS);
			B.setBackground(green);
			B.setForeground(darkTurquoise);

			JTextField Bt = new JTextField(10);

			JButton C = new JButton("Delete Project");
			C.setFont(LS);
			C.setBorderPainted(false);
			C.setBackground(green);
			C.setForeground(darkTurquoise);
			C.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					try (Connection con = DriverManager.getConnection(dbURL); Statement stmt = con.createStatement();) {
						PreparedStatement updprojscore = con.prepareStatement(
								"DELETE projects WHERE PROJNAME = \'" + Projects.getSelectedItem() + "\';");
						updprojscore.executeUpdate();
						ProjectSelect(STUDENT);
						pdelete.dispose();
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
				}
			});

			JButton D = new JButton("Back");
			D.setFont(LS);
			D.setBorderPainted(false);
			D.setBackground(green);
			D.setForeground(darkTurquoise);
			D.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					ProjectSelect(STUDENT);
					pdelete.dispose();
				}
			});

			// Adding Items
			middle.add(A);
			middle.add(Projects);
			middle.add(B);
			middle.add(Bt);
			middle.add(C);
			middle.add(D);

			// Creating Frame
			top.setBackground(green);
			middle.setBackground(green);
			bottom.setBackground(green);
			pdelete.add(top, BorderLayout.NORTH);
			pdelete.add(middle, BorderLayout.CENTER);
			pdelete.add(bottom, BorderLayout.SOUTH);
			pdelete.setSize(1000, 600);
			pdelete.setResizable(false);
			pdelete.setTitle("EasyDAW - Project Deletion Screen");

			// Centering Window
			Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
			pdelete.setLocation(dim.width / 2 - pdelete.getSize().width / 2,
					dim.height / 2 - pdelete.getSize().height / 2);

			pdelete.setVisible(true);
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
	}

	/* -----MUSIC WRITING----- */

	public static void MusicWrite(Students STUDENT, String PROJNAME) {
		try (Connection con = DriverManager.getConnection(dbURL); Statement stmt = con.createStatement();) {

			JFrame mwrite = new JFrame();
			JPanel top = new JPanel();
			JPanel middle = new JPanel();
			JPanel bottom = new JPanel();
			mwrite.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			mwrite.setLayout(new BorderLayout());

			// Header
			JLabel mwriteText = new JLabel("Music Writing");
			mwriteText.setFont(LSlargeitalics);
			mwriteText.setForeground(Color.black);
			top.add(mwriteText);

			// Menu Items

			JLabel A = new JLabel("Notepad:");
			A.setFont(LS);
			A.setForeground(Color.black);

			JTextArea Notepad = new JTextArea(5, 20);

			//APPEND SAVED TEXT TO NOTEPAD
			String qUNotes = "SELECT NOTEPAD FROM projects WHERE USERNAME = \'" + STUDENT.getUsername()
					+ "\' AND PROJNAME = \'" + PROJNAME + "\';";
			Statement qUstmt = con.createStatement();
			ResultSet qUrset = qUstmt.executeQuery(qUNotes);
			qUrset.next();
			String appen = qUrset.getString(1);
			if (appen != null) {
				Notepad.append(appen);
			}

			JLabel C = new JLabel("Instrument: ");
			C.setFont(LS);
			C.setForeground(Color.black);

			JButton E = new JButton("Play Screen");
			E.setFont(LS);
			E.setBorderPainted(false);
			E.setBackground(Color.black);
			E.setForeground(Color.white);
			E.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					try (Connection con = DriverManager.getConnection(dbURL); Statement stmt = con.createStatement();) {
						PreparedStatement updprojnotepad = con.prepareStatement(
								"UPDATE projects SET Notepad = \'" + Notepad.getText() + "\'" + " WHERE USERNAME = \'"
										+ STUDENT.getUsername() + "\' AND PROJNAME = \'" + PROJNAME + "\';");
						updprojnotepad.executeUpdate();
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
					MusicPlay(STUDENT, PROJNAME);
					mwrite.dispose();
				}
			});

			JLabel staffo = new JLabel(Staff);

			// Adding Items
			middle.add(A);
			middle.add(Notepad);
			middle.add(C);
			middle.add(E);
			bottom.add(staffo);

			// Creating Frame
			mwrite.add(top, BorderLayout.NORTH);
			mwrite.add(middle, BorderLayout.CENTER);
			mwrite.add(bottom, BorderLayout.SOUTH);
			mwrite.setSize(1000, 600);
			mwrite.setResizable(false);
			mwrite.setTitle("EasyDAW - Notes Screen");

			// Centering Window
			Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
			mwrite.setLocation(dim.width / 2 - mwrite.getSize().width / 2,
					dim.height / 2 - mwrite.getSize().height / 2);

			mwrite.setVisible(true);

		} catch (SQLException e1) {
			e1.printStackTrace();
		}
	}

	/* -----MUSIC PLAYING----- */

	public static void MusicPlay(Students STUDENT, String PROJNAME) {

		JFrame mplay = new JFrame();
		JPanel top = new JPanel();
		JPanel south = new JPanel();
		JPanel bottom = new JPanel();
		mplay.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mplay.setLayout(new BorderLayout());

		// Header
		JLabel mplayText = new JLabel("Music Playing");
		mplayText.setFont(LSlargeitalics);
		mplayText.setForeground(Color.black);
		top.add(mplayText);

		// PIANO HERE
		VirtualPianoView letsPlay = new VirtualPianoView();

		// STAFF HERE
		JLabel staffo = new JLabel(Staff);
		staffo.setBackground(green);

		// KEYS
		JComboBox key = null;
		switch (Key) {
		case "A":
			key = new JComboBox(A);
			break;
		case "B":
			key = new JComboBox(B);
			break;
		case "C":
			key = new JComboBox(C);
			break;
		case "D":
			key = new JComboBox(D);
			break;
		case "E":
			key = new JComboBox(E);
			break;
		case "F":
			key = new JComboBox(F);
			break;
		case "G":
			key = new JComboBox(G);
			break;
		}

		JButton C = new JButton("Notepad");
		C.setFont(LS);
		C.setBorderPainted(false);
		C.setBackground(Color.black);
		C.setForeground(Color.white);
		C.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MusicWrite(STUDENT, PROJNAME);
				mplay.dispose();
			}
		});

		JButton D = new JButton("Back");
		D.setFont(LS);
		D.setBorderPainted(false);
		D.setBackground(Color.black);
		D.setForeground(Color.white);
		D.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ProjectSelect(STUDENT);
				mplay.dispose();
			}
		});

		// Adding Item
		bottom.add(C);
		bottom.add(D);
		bottom.add(key);
		south.add(staffo);

		// Creating Frame
		mplay.add(top, BorderLayout.NORTH);
		mplay.getContentPane().add(letsPlay, BorderLayout.CENTER);
		mplay.add(south, BorderLayout.SOUTH);
		mplay.add(bottom, BorderLayout.EAST);
		mplay.setSize(1000, 600);
		mplay.setResizable(false);
		mplay.setTitle("EasyDAW - Playing Screen");

		// Centering Window
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		mplay.setLocation(dim.width / 2 - mplay.getSize().width / 2, dim.height / 2 - mplay.getSize().height / 2);

		mplay.setVisible(true);
		letsPlay.requestFocus();

	}

}
