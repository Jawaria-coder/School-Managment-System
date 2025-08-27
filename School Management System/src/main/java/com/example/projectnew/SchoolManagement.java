package com.example.projectnew;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

import java.io.*;
import java.util.InputMismatchException;
import java.util.Scanner;


public class SchoolManagement extends Application {
    private Scene mainScene;
    private Scene libraryscene;
    Scene studentscene;
    private Scene staffscene;
    private String selectedChoice2 = "lim";
    String  selectedChoice = "abc";
    String selectedChoice1 = "kli";
    private StudentManagement studentManagement;
    private StaffManagement staffManagement;
    private Student student;
    private Teacher teacher;
    private JuniorStaff juniorStaff;
    private Principle principle;
    private LibraryManagement libraryManagement;

    public SchoolManagement() {
        this.studentManagement = new StudentManagement();
        this.staffManagement = new StaffManagement();
        this.libraryManagement = new LibraryManagement();
    }

    @Override
    public void start(Stage stage) throws Exception {
        setMainScene(stage);
        StudentLogin(stage);
        StaffLogin(stage);
        LibraryLogin(stage);
        Studentscene(stage);
        Staffscene(stage);
        LibraryScene(stage);
        stage.setScene(mainScene);
        stage.setTitle("School Management System");
        stage.show();
    }

    private void setMainScene(Stage stage) throws Exception {

        Image image = new Image(getClass().getResource("/logo.png").toExternalForm());
        System.out.println("Image error? " + image.isError());
        ImageView imageView = new ImageView(image);
        imageView.setFitWidth(150);
        imageView.setFitHeight(150);



        Label label1 = new Label("THE FAITH SCHOOL");
        Label label2 = new Label("PALM TREE CAMPUS");
        label1.setTextFill(Color.BLACK);
        label2.setTextFill(Color.BLACK);
        stage.setTitle("School Management System");
        Label label = new Label("Choose an option:");
        label.setTextFill(Color.BLACK);
        Button studentButton = new Button("Student Management");
        Button staffButton =  new Button("Staff Management");
        Button libraryButton = new Button("Library Management");
        studentButton.setPrefSize(160,100);
        staffButton.setPrefSize(160,100);
        libraryButton.setPrefSize(160,100);
        studentButton.setFont(Font.font("Times New Roman", FontWeight.BOLD,15));
        studentButton.setStyle("-fx-background-color: #000000;");
        studentButton.setTextFill(Color.WHITE);
        staffButton.setFont(Font.font("Times New Roman", FontWeight.BOLD,15));
        staffButton.setStyle("-fx-background-color: #000000;");
        staffButton.setTextFill(Color.WHITE);
        libraryButton.setFont(Font.font("Times New Roman", FontWeight.BOLD,15));
        libraryButton.setStyle("-fx-background-color: #000000;");
        libraryButton.setTextFill(Color.WHITE);

        GridPane pane1 = new GridPane();
        pane1.setAlignment(Pos.TOP_CENTER);
        pane1.setVgap(5);
        pane1.setHgap(5);
        label1.setFont(Font.font("Times New Roman", FontWeight.BOLD, 25));
        label2.setFont(Font.font("Times New Roman", FontWeight.BOLD, 20));
        label.setFont(Font.font("Times New Roman", FontWeight.BOLD, 18));

        HBox hb = new HBox();
        HBox hb1 = new HBox();
        HBox hb2 = new HBox();
        HBox hb3 = new HBox();
        HBox hb4 = new HBox();
        hb4.getChildren().addAll(imageView);
        hb1.getChildren().addAll(label1);
        hb2.getChildren().addAll(label2);
        hb3.getChildren().addAll(label);
        hb1.setAlignment(Pos.CENTER);
        hb2.setAlignment(Pos.CENTER);
        hb3.setAlignment(Pos.CENTER);
        hb4.setAlignment(Pos.CENTER);
        hb.getChildren().addAll(studentButton,staffButton,libraryButton);
        hb.setSpacing(5);
        hb.setAlignment(Pos.CENTER);
        VBox vb = new VBox();
        VBox.setMargin(hb4, new Insets(100, 0, 0, 0)); // top=20px, right=0, bottom=0, left=0
        vb.getChildren().addAll(hb4,hb1,hb2,hb3,hb);
        vb.setAlignment(Pos.CENTER);
        vb.setSpacing(5);

        pane1.add(vb, 0,0);
        setBackgroundColor(pane1);


        studentButton.setOnAction(sb -> {
            StudentLogin(stage);
        });

        staffButton.setOnAction(st -> {
            StaffLogin(stage);
        });

        libraryButton.setOnAction(lib -> {
            LibraryLogin(stage);
        });

        mainScene = new Scene(pane1, 700, 700);
    }
    private void createLoginLayout(Stage stage, String title, String correctUsername, String correctPassword, Scene nextScene) {
        Label mainLabel = new Label(title);
        Label administratorLogin = new Label("Administrator Login");
        Label username = new Label("Username");
        TextField usernameText = new TextField();
        Label password = new Label("Password");
        PasswordField passwordField = new PasswordField();
        Button loginButton = new Button("Login");
        Button back = new Button("Back");

        GridPane pane = new GridPane();
        pane.setAlignment(Pos.CENTER);
        pane.setVgap(5);
        pane.setHgap(5);

        pane.add(mainLabel,3,7);
        pane.add(administratorLogin, 3, 8);
        pane.add(username, 3, 9);
        pane.add(usernameText, 3, 10);
        pane.add(password, 3, 11);
        pane.add(passwordField, 3, 12);
        pane.add(loginButton, 3, 13);
        pane.add(back,4,13);

        mainLabel.setFont(Font.font("Times New Roman", FontWeight.BOLD, 25));
        administratorLogin.setFont(Font.font("Times New Roman", FontWeight.BOLD, 20));
        username.setFont(Font.font("Times New Roman", FontWeight.BOLD, 15));
        password.setFont(Font.font("Times New Roman", FontWeight.BOLD, 15));
        loginButton.setFont(Font.font("Times New Roman", FontWeight.BOLD, 15));
        back.setFont(Font.font("Times New Roman", FontWeight.BOLD, 15));

        loginButton.setOnAction(event -> {
            String enteredUsername = usernameText.getText();
            String enteredPassword = passwordField.getText();

            if (enteredUsername.equals(correctUsername) && enteredPassword.equals(correctPassword)) {
                stage.setScene(nextScene);
                displaySuccessMessage();
            } else {
                displayAlert();
            }
        });
        logout(back,stage);

        Scene loginScene = new Scene(pane, 700, 700);
        setBackgroundColor(pane);
        stage.setScene(loginScene);
        stage.show();
    }
    private void StudentLogin(Stage stage) {
        createLoginLayout(stage, "Student Management", "Jawaria", "jaw", studentscene);
    }

    private void StaffLogin(Stage stage) {
        createLoginLayout(stage, "Staff Management", "Eesha", "eesha12", staffscene);
    }

    private void LibraryLogin(Stage stage) {
        createLoginLayout(stage, "Library Management", "Faiza", "faiza2", libraryscene);
    }

    private void displaySuccessMessage() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Success");
        alert.setHeaderText(null);
        alert.setContentText("Login successful!");
        alert.showAndWait();
    }

    private void displayAlert() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Incorrect Credentials");
        alert.setHeaderText(null);
        alert.setContentText("Username or password is incorrect.");
        alert.showAndWait();
    }

    private void Staffscene(Stage stage) {
        GridPane pane2 = new GridPane();
        ComboBox<String> choice = new ComboBox<>();
        String arr[] = {"1. Add a Teacher", "2. Remove a Teacher",
                "3. Add Junior Staff", "4. Remove Junior Staff", "5. Calculate Salary of a Teacher",
                "6. Calculate Salary of a Junior Staff", "7. Update Teacher Information", "8. Display Teachers Information.",
                "9. Display Junior Staffs Information.", "10. Display Principle"};

        Label label = new Label("Choose an option:");
        label.setFont(Font.font("Times New Roman", FontWeight.BOLD, 20));
        choice.getItems().addAll(arr);
        Label staffManagement1 = new Label("Staff Management");
        pane2.add(label, 0, 1);
        pane2.add(staffManagement1, 1, 0);
        staffManagement1.setFont(Font.font("Times New Roman", FontWeight.BOLD, 25));
        pane2.setVgap(10);
        pane2.setHgap(10);
        pane2.setAlignment(Pos.CENTER);

        Button b1 = new Button("Submit");
        Button back = new Button("Log Out");
        pane2.add(back,3,4);
        pane2.add(b1, 2, 4);
        pane2.add(choice, 1, 1);
        pane2.setVgap(5);
        pane2.setHgap(5);
        setBackgroundColor(pane2);
        staffscene = new Scene(pane2, 700, 700);
        setBackgroundColor(pane2);

        b1.setOnAction(a -> {
            selectedChoice = choice.getValue();
            if (selectedChoice != null) {
                if (selectedChoice.equals("1. Add a Teacher")) {
                    addTeacher(stage);
                } else if (selectedChoice.equals("2. Remove a Teacher")) {
                    removeTeacher(stage);
                } else if (selectedChoice.equals("3. Add Junior Staff")) {
                    addJuniorStaff(stage);
                } else if (selectedChoice.equals("4. Remove Junior Staff")) {
                    removeJuniorStaff(stage);
                }  else if (selectedChoice.equals("5. Calculate Salary of a Teacher")) {
                    calculateSalaryOfTeacher(stage);
                } else if (selectedChoice.equals("6. Calculate Salary of a Junior Staff")) {
                    calculateSalaryOfJuniorStaff(stage);
                } else if (selectedChoice.equals("7. Update Teacher Information")) {
                    UpdatingTeacherInfo(stage);
                } else if (selectedChoice.equals("8. Display Teachers Information.")) {
                    displayTeacherInfo(stage);
                } else if (selectedChoice.equals("9. Display Junior Staffs Information.")) {
                    displayJuniorStaffInfo(stage);
                } else if (selectedChoice.equals("10. Display Principle")) {
                    displayPrincipleInfo(stage);
                }
            }
        });
        logout(back,stage);
    }
    private void Studentscene(Stage stage) {

        GridPane pane2 = new GridPane();

        ComboBox<String> choice2 = new ComboBox<>();
        String ar[] = {"1. Admit a Student", "2. Remove a Student", "3. Display Fee of Student.", "4. Display ScholarShip.",
                "5. Assign Courses", "6. View Assigned Courses", "7. See Result", "8. Update Student Information",
                "9. Display Students Information."};

        choice2.getItems().addAll(ar);
        Label label = new Label("Choose an option:");
        label.setFont(Font.font("Times New Roman", FontWeight.BOLD, 20));
        Label studentManagement1 = new Label("Student Management");
        studentManagement1.setFont(Font.font("Times New Roman", FontWeight.BOLD, 25));
        pane2.setAlignment(Pos.CENTER);

        pane2.add(label, 0, 1);
        pane2.add(studentManagement1, 1, 0);
        pane2.setVgap(5);
        pane2.setHgap(5);

        Button b1 = new Button("Submit");
        Button back = new Button("Log Out");
        pane2.add(back,3,4);
        pane2.add(b1, 2, 4);
        pane2.add(choice2, 1, 1);
        setBackgroundColor(pane2);
        studentscene = new Scene(pane2, 700, 700);
        setBackgroundColor(pane2);

        b1.setOnAction(a ->
        {
            selectedChoice1 = choice2.getValue();

            if (selectedChoice1 != null) {
                // Handle the second set of choices
                if (selectedChoice1.equals("1. Admit a Student")) {
                    admitStudent(stage);
                }if (selectedChoice1.equals("2. Remove a Student")) {
                    removeStudent(stage);
                } if (selectedChoice1.equals("3. Display Fee of Student.")) {
                    displayFee(stage);
                } if (selectedChoice1.equals("4. Display ScholarShip.")) {
                    seeScholarship(stage);
                } if (selectedChoice1.equals("5. Assign Courses")) {
                    assignCourses(stage);
                }  if (selectedChoice1.equals("6. View Assigned Courses")) {
                    viewAssignedCourses(stage);
                }  if (selectedChoice1.equals("7. See Result")) {
                    seeResult(stage);
                }  if (selectedChoice1.equals("8. Update Student Information")) {
                    UpdateStudentInformation(stage);
                }  if (selectedChoice1.equals("9. Display Students Information.")) {
                    displayStudentInfo(stage);
                }
            }
        });
        logout(back,stage);
    }
    private void LibraryScene(Stage stage) {
        GridPane pane2 = new GridPane();
        ComboBox<String> choice3 = new ComboBox<>();
        String arr[] = {"1. Add a book" , "2. Assign a book", "3. Return a book",
                "4. Display Books", "5. Display Checked Out Books"};

        Label label = new Label("Choose an option:");
        label.setFont(Font.font("Times New Roman", FontWeight.BOLD, 20));
        choice3.getItems().addAll(arr);
        Label staffManagement1 = new Label("Library Management");
        pane2.add(label, 0, 1);
        pane2.add(staffManagement1, 1, 0);
        staffManagement1.setFont(Font.font("Times New Roman", FontWeight.BOLD, 25));
        pane2.setVgap(10);
        pane2.setHgap(10);
        pane2.setAlignment(Pos.CENTER);

        Button b1 = new Button("Submit");
        Button back = new Button("Log Out");
        pane2.add(back,3,4);
        pane2.add(b1, 2, 4);
        pane2.add(choice3, 1, 1);
        pane2.setVgap(5);
        pane2.setHgap(5);
        setBackgroundColor(pane2);
        libraryscene = new Scene(pane2, 700, 700);
        setBackgroundColor(pane2);

        b1.setOnAction(a -> {
            selectedChoice2 = choice3.getValue();
            if (selectedChoice2 != null) {
                if (selectedChoice2.equals("1. Add a book")) {
                    addBook(stage);
                } else if (selectedChoice2.equals("2. Assign a book")) {
                    assignBook(stage);
                } else if (selectedChoice2.equals("3. Return a book")) {
                    returnBook(stage);
                } else if (selectedChoice2.equals("4. Display Books")) {
                    displayAvailableBooks(stage);
                } else if (selectedChoice2.equals("5. Display Checked Out Books")) {
                    displayCheckedOutBooks(stage);
                }
            }
        });
        logout(back,stage);
    }
    public void logout(Button logout, Stage stage)
    {
        logout.setOnAction(l -> {
            stage.setScene(mainScene);
        });
    }
    public void admitStudent(Stage stage) {
        Label l = new Label("Add Student:");
        Label l1 = new Label("Name");
        TextField text1 = new TextField();
        Label l2 = new Label("Age");
        TextField text2 = new TextField();
        Label l3 = new Label("id");
        TextField text3 = new TextField();
        Label l4 = new Label("Cnic");
        TextField text4 = new TextField();
        Label l5 = new Label("Gender");
        RadioButton r1 = new RadioButton("Male");
        RadioButton r2 = new RadioButton("Female");
        ToggleGroup tg = new ToggleGroup();
        r1.setToggleGroup(tg);
        r2.setToggleGroup(tg);
        Label l6 = new Label("Contact Info");
        TextField text6 = new TextField();
        Label l7 = new Label("Email");
        TextField text7 = new TextField();
        Label l8 = new Label("Address");
        TextField text8 = new TextField();
        Label l9 = new Label("Father Name");
        TextField text9 = new TextField();
        Label l10 = new Label("Mother Name");
        TextField text10 = new TextField();
        Label l11 = new Label("Father Contact");
        TextField text11 = new TextField();
        Label l12 = new Label("Mother Contact");
        TextField text12 = new TextField();
        Label l13 = new Label("Father Business");
        TextField text13 = new TextField();
        Label l14 = new Label("Father Income");
        TextField text14 = new TextField();
        Label l15 = new Label("Father Email");
        TextField text15 = new TextField();
        Button b = new Button("Submit");
        Button back = new Button("Back to Menu");
        GridPane pane2 = new GridPane();
        pane2.add(l,0,0);
        l.setFont(Font.font("Times New Roman", FontWeight.BOLD, 25));
        pane2.setAlignment(Pos.TOP_CENTER);
        pane2.add(l1, 0, 1);
        pane2.add(text1, 2, 1);
        pane2.add(l2, 0, 2);
        pane2.add(text2, 2, 2);
        pane2.add(l3, 0, 3);
        pane2.add(text3, 2, 3);
        pane2.add(l4, 0, 4);
        pane2.add(text4, 2, 4);
        pane2.add(l5, 0, 5);
        pane2.add(r1, 2, 5);
        pane2.add(r2, 3, 5);
        pane2.add(l6, 0, 6);
        pane2.add(text6, 2, 6);
        pane2.add(l7, 0, 7);
        pane2.add(text7, 2, 7);
        pane2.add(l8, 0, 8);
        pane2.add(text8, 2, 8);
        pane2.add(l9, 0, 9);
        pane2.add(text9, 2, 9);
        pane2.add(l10, 0, 10);
        pane2.add(text10, 2, 10);
        pane2.add(l11, 0, 11);
        pane2.add(text11, 2, 11);
        pane2.add(l12, 0, 12);
        pane2.add(text12, 2, 12);
        pane2.add(l13, 0, 13);
        pane2.add(text13, 2, 13);
        pane2.add(l14, 0, 14);
        pane2.add(text14, 2, 14);
        pane2.add(l15, 0, 15);
        pane2.add(text15, 2, 15);
        pane2.add(b, 4, 16);
        pane2.add(back, 4, 18);
        pane2.setHgap(5);
        pane2.setVgap(5);
        Scene s1 = new Scene(pane2, 700, 700);
        setBackgroundColor(pane2);
        stage.setScene(s1);
        Label admission = new Label();
        b.setOnAction(submitEvent ->
        {
            try {
                // make sure "data" folder exists
                File file = new File("data/Student.txt");
                file.getParentFile().mkdirs();
                try (FileWriter fileWriter = new FileWriter(file, true)) {
                    String name = text1.getText();
                    int age = Integer.parseInt(text2.getText());
                    int id = Integer.parseInt(text3.getText());
                    long cnic = Long.parseLong(text4.getText());
                    boolean gender = r1.isSelected();
                    long contact_info = Long.parseLong(text6.getText());
                    String email = text7.getText();
                    String address = text8.getText();
                    String f_n = text9.getText();
                    String m_n = text10.getText();
                    long f_contact_info = Long.parseLong(text11.getText());
                    long m_contact_info = Long.parseLong(text12.getText());
                    String f_bus = text13.getText();
                    double f_in = Double.parseDouble(text14.getText());
                    String f_em = text15.getText();

                    student = new Student(name, age, id, cnic, gender,
                            contact_info, email, address, f_n, m_n, f_contact_info,
                            m_contact_info, f_bus, f_in, f_em, true);

                    this.studentManagement.admitStudent(student);

                    admission.setText("Student admitted successfully.");
                    pane2.add(admission, 4, 17);

                    // Write the student data in text form
                    fileWriter.write("Name: " + name + "\n");
                    fileWriter.write("Age: " + age + "\n");
                    fileWriter.write("ID: " + id + "\n");
                    fileWriter.write("CNIC: " + cnic + "\n");
                    fileWriter.write("Gender: " + (gender ? "Male" : "Female") + "\n");
                    fileWriter.write("Contact Info: " + contact_info + "\n");
                    fileWriter.write("Email: " + email + "\n");
                    fileWriter.write("Address: " + address + "\n");
                    fileWriter.write("Father Name: " + f_n + "\n");
                    fileWriter.write("Mother Name: " + m_n + "\n");
                    fileWriter.write("Father Contact: " + f_contact_info + "\n");
                    fileWriter.write("Mother Contact: " + m_contact_info + "\n");
                    fileWriter.write("Father Business: " + f_bus + "\n");
                    fileWriter.write("Father Income: " + f_in + "\n");
                    fileWriter.write("Father Email: " + f_em + "\n");
                }

            } catch (IOException | NumberFormatException e) {
                throw new RuntimeException(e);
            }

        });
        backbutton(back,stage);
    }
    public void addTeacher(Stage stage) {
        Label l = new Label("Add Teacher");
        Label l01 = new Label("Name");
        TextField t01 = new TextField();
        Label l02 = new Label("Age");
        TextField t02 = new TextField();
        Label l03 = new Label("id");
        TextField t03 = new TextField();
        Label l04 = new Label("Cnic");
        TextField t04 = new TextField();
        Label l05 = new Label("Gender");
        RadioButton r11 = new RadioButton("Male");
        RadioButton r12 = new RadioButton("Female");
        ToggleGroup t111 = new ToggleGroup();
        r11.setToggleGroup(t111);
        r12.setToggleGroup(t111);
        Label l06 = new Label("Contact Info");
        TextField t06 = new TextField();
        Label l07 = new Label("Email");
        TextField t07 = new TextField();
        Label l08 = new Label("Address");
        TextField t08 = new TextField();
        Label l19 = new Label("Qualification");
        TextField t19 = new TextField();
        Label l110 = new Label("Experience Years");
        TextField t110 = new TextField();
        Label l111 = new Label("Certificate");
        RadioButton certYes = new RadioButton("Yes");
        RadioButton certNo = new RadioButton("No");
        ToggleGroup certGroup = new ToggleGroup();
        certYes.setToggleGroup(certGroup);
        certNo.setToggleGroup(certGroup);
        Label l112 = new Label("Marital Status");
        RadioButton single = new RadioButton("Single");
        RadioButton married = new RadioButton("Married");
        ToggleGroup statusGroup = new ToggleGroup();
        single.setToggleGroup(statusGroup);
        married.setToggleGroup(statusGroup);
        Button tb = new Button("Submit");
        Button tb1 = new Button("Back to Menu");
        GridPane p2 = new GridPane();
        p2.setVgap(5);
        p2.setHgap(5);
        p2.add(l,0,0);
        l.setFont(Font.font("Times New Roman", FontWeight.BOLD, 25));
        p2.setAlignment(Pos.TOP_CENTER);
        p2.add(l01, 0, 1);
        p2.add(t01, 1, 1);
        p2.add(l02, 0, 2);
        p2.add(t02, 1, 2);
        p2.add(l03, 0, 3);
        p2.add(t03, 1, 3);
        p2.add(l04, 0, 4);
        p2.add(t04, 1, 4);
        p2.add(l05, 0, 5);
        p2.add(r11, 1, 5);
        p2.add(r12, 2, 5);
        p2.add(l06, 0, 6);
        p2.add(t06, 1, 6);
        p2.add(l07, 0, 7);
        p2.add(t07, 1, 7);
        p2.add(l08, 0, 8);
        p2.add(t08, 1, 8);
        p2.add(l19, 0, 9);
        p2.add(t19, 1, 9);
        p2.add(l110, 0, 10);
        p2.add(t110, 1, 10);
        p2.add(l111, 0, 11);
        p2.add(certYes, 1, 11);
        p2.add(certNo, 2, 11);
        p2.add(l112, 0, 12);
        p2.add(single, 1, 12);
        p2.add(married, 2, 12);
        p2.add(tb, 3, 13);
        p2.add(tb1, 3, 15);
        Scene s1 = new Scene(p2, 700, 700);
        setBackgroundColor(p2);
        stage.setScene(s1);
        Label add = new Label();
        tb.setOnAction(submitEvent ->
        {
            try {
                File file = new File("data/Teacher.txt");
                file.getParentFile().mkdirs();

                try (FileWriter fileWriter = new FileWriter(file, true))
                {
                    String name = t01.getText();
                    int age = Integer.parseInt(t02.getText());
                    int id = Integer.parseInt(t03.getText());
                    long cnic = Long.parseLong(t04.getText());
                    boolean gender = r11.isSelected();
                    long contact_info = Long.parseLong(t06.getText());
                    String email = t07.getText();
                    String address = t08.getText();
                    String quali = t19.getText();
                    int exp = Integer.parseInt(t110.getText());
                    boolean cert = certYes.isSelected();
                    boolean m_s = single.isSelected();
                    teacher = new Teacher(name, age, id, cnic, gender,
                            contact_info, email, address, quali, exp, cert, m_s);
                    this.staffManagement.addTeacher(teacher);
                    add.setText("Teacher added successfully");
                    p2.add(add,3,14);

                    fileWriter.write("Name: " + name + "\n");
                    fileWriter.write("Age: " + age + "\n");
                    fileWriter.write("ID: " + id + "\n");
                    fileWriter.write("CNIC: " + cnic + "\n");
                    fileWriter.write("Gender: " + (gender ? "Male" : "Female") + "\n");
                    fileWriter.write("Contact Info: " + contact_info + "\n");
                    fileWriter.write("Email: " + email + "\n");
                    fileWriter.write("Address: " + address + "\n");
                    fileWriter.write("Experience: " + exp + "\n");

                    // Add a separator for better readability
                    fileWriter.write("---\n");

                }

            } catch (IOException | NumberFormatException e) {
                throw new RuntimeException(e);
            }
        });
        backbutton2(tb1,stage);
    }
    public void addJuniorStaff(Stage stage) {
        Label l = new Label("Add Junior Staff");
        Label l01 = new Label("Name");
        TextField t01 = new TextField();
        Label l02 = new Label("Age");
        TextField t02 = new TextField();
        Label l03 = new Label("id");
        TextField t03 = new TextField();
        Label l04 = new Label("Cnic");
        TextField t04 = new TextField();
        Label l05 = new Label("Gender");
        RadioButton r11 = new RadioButton("Male");
        RadioButton r12 = new RadioButton("Female");
        ToggleGroup t111 = new ToggleGroup();
        r11.setToggleGroup(t111);
        r12.setToggleGroup(t111);
        Label l06 = new Label("Contact Info");
        TextField t06 = new TextField();
        Label l07 = new Label("Email");
        TextField t07 = new TextField();
        Label l08 = new Label("Address");
        TextField t08 = new TextField();
        Label l110 = new Label("Experience Years");
        TextField t110 = new TextField();
        Button tb = new Button("Submit");
        Button tb1 = new Button("Back to Menu");
        GridPane p2 = new GridPane();
        p2.setVgap(5);
        p2.setHgap(5);
        l.setFont(Font.font("Times New Roman", FontWeight.BOLD, 25));
        p2.setAlignment(Pos.TOP_CENTER);
        p2.add(l,0,0);
        p2.add(l01, 0, 1);
        p2.add(t01, 1, 1);
        p2.add(l02, 0, 2);
        p2.add(t02, 1, 2);
        p2.add(l03, 0, 3);
        p2.add(t03, 1, 3);
        p2.add(l04, 0, 4);
        p2.add(t04, 1, 4);
        p2.add(l05, 0, 5);
        p2.add(r11, 1, 5);
        p2.add(r12, 2, 5);
        p2.add(l06, 0, 6);
        p2.add(t06, 1, 6);
        p2.add(l07, 0, 7);
        p2.add(t07, 1, 7);
        p2.add(l08, 0, 8);
        p2.add(t08, 1, 8);
        p2.add(l110, 0, 10);
        p2.add(t110, 1, 10);
        p2.add(tb, 3, 13);
        p2.add(tb1, 3, 15);
        Scene s3 = new Scene(p2, 700, 700);
        setBackgroundColor(p2);
        stage.setScene(s3);
        Label add = new Label();
        tb.setOnAction(submitEvent ->
        {
            try {
                File file = new File("data/JuniorStaff.txt");
                file.getParentFile().mkdirs();
                try (FileWriter fileWriter = new FileWriter(file, true)) {
                    String name = t01.getText();
                    int age = Integer.parseInt(t02.getText());
                    int id = Integer.parseInt(t03.getText());
                    long cnic = Long.parseLong(t04.getText());
                    boolean gender = r11.isSelected();
                    long contact_info = Long.parseLong(t06.getText());
                    String email = t07.getText();
                    String address = t08.getText();
                    int exp = Integer.parseInt(t110.getText());
                    juniorStaff = new JuniorStaff(name, age, id, cnic, gender,
                            contact_info, email, address, exp);
                    this.staffManagement.addJuniorStaff(juniorStaff);
                    add.setText("JuniorStaff added successfully.");
                    p2.add(add, 3, 14);

                    fileWriter.write("Name: " + name + "\n");
                    fileWriter.write("Age: " + age + "\n");
                    fileWriter.write("ID: " + id + "\n");
                    fileWriter.write("CNIC: " + cnic + "\n");
                    fileWriter.write("Gender: " + (gender ? "Male" : "Female") + "\n");
                    fileWriter.write("Contact Info: " + contact_info + "\n");
                    fileWriter.write("Email: " + email + "\n");
                    fileWriter.write("Address: " + address + "\n");
                    fileWriter.write("Experience: " + exp + "\n");

                    // Add a separator for better readability
                    fileWriter.write("---\n");
                }

            } catch (IOException | NumberFormatException e) {
                throw new RuntimeException(e);
            }
        });
        backbutton2(tb1,stage);
    }
    public void removeStudent(Stage stage) {
        Label l = new Label("Remove Student:");
        Label l2 = new Label("Enter the student ID to remove:");
        TextField text1 = new TextField();

        Button tb = new Button("Submit");
        Button tb1 = new Button("Back to Menu");

        GridPane pane2 = new GridPane();
        l.setFont(Font.font("Times New Roman", FontWeight.BOLD, 25));
        pane2.setAlignment(Pos.TOP_CENTER);
        pane2.add(l,0,0);
        pane2.add(l2, 0, 1);
        pane2.add(text1, 1, 1);
        pane2.add(tb, 2, 2);
        pane2.add(tb1, 2, 4);
        pane2.setVgap(5);
        pane2.setHgap(5);

        Scene s3 = new Scene(pane2, 700, 700);
        setBackgroundColor(pane2);
        stage.setScene(s3);

        Label add = new Label();

        tb.setOnAction(submitEvent -> {
            int id = Integer.parseInt(text1.getText());
            boolean removed = this.studentManagement.removeStudent(id);

            if (removed) {
                add.setText("Student with id " + id + " removed");
            } else {
                add.setText("No student found with the specified ID.");
            }

            pane2.add(add, 2, 3);
        });

        backbutton(tb1,stage);

    }
    public void removeTeacher(Stage stage) {
        Label l = new Label("Remove Teacher:");
        Label l2 = new Label("Enter the teacher ID to remove:");
        TextField text1 = new TextField();

        Button tb = new Button("Submit");
        Button tb1 = new Button("Back to Menu");

        GridPane pane2 = new GridPane();
        l.setFont(Font.font("Times New Roman", FontWeight.BOLD, 25));
        pane2.setAlignment(Pos.TOP_CENTER);
        pane2.add(l,0,0);
        pane2.add(l2, 0, 1);
        pane2.add(text1, 1, 1);
        pane2.add(tb, 2, 2);
        pane2.add(tb1, 2, 4);
        pane2.setVgap(5);
        pane2.setHgap(5);

        Scene s3 = new Scene(pane2, 700, 700);
        setBackgroundColor(pane2);
        stage.setScene(s3);

        Label add = new Label();

        tb.setOnAction(submitEvent -> {
            int id = Integer.parseInt(text1.getText());
            boolean removed = this.staffManagement.removeTeacher(id);

            if (removed) {
                add.setText("Teacher with id " + id + " removed");
            } else {
                add.setText("No teacher found with the specified ID.");
            }

            pane2.add(add, 2, 3);
        });

        backbutton2(tb1,stage);
    }

    public void removeJuniorStaff(Stage stage) {
        Label l = new Label("Remove Junior Staff:");
        Label l2 = new Label("Enter the junior staff ID to remove:");
        TextField text1 = new TextField();

        Button tb = new Button("Submit");
        Button tb1 = new Button("Back to Menu");

        GridPane pane2 = new GridPane();
        l.setFont(Font.font("Times New Roman", FontWeight.BOLD, 25));
        pane2.setAlignment(Pos.TOP_CENTER);
        pane2.add(l,0,0);
        pane2.add(l2, 0, 1);
        pane2.add(text1, 1, 1);
        pane2.add(tb, 2, 2);
        pane2.add(tb1, 2, 4);
        pane2.setVgap(5);
        pane2.setHgap(5);

        Scene s3 = new Scene(pane2, 700, 700);
        setBackgroundColor(pane2);
        stage.setScene(s3);

        Label add = new Label();

        tb.setOnAction(submitEvent -> {
            int id = Integer.parseInt(text1.getText());
            boolean removed = this.staffManagement.removeJuniorStaff(id);

            if (removed) {
                add.setText("Junior Staff with id " + id + " removed");
            } else {
                add.setText("No junior staff found with the specified ID.");
            }

            pane2.add(add, 2, 3);
        });

        backbutton2(tb1,stage);
    }

    public void calculateSalaryOfTeacher(Stage stage) {
        Label l = new Label("Salary of Teacher:");
        Label l2 = new Label("Enter the Teacher ID to calculate salary:");
        TextField text1 = new TextField();

        Button tb = new Button("Submit");
        Button tb1 = new Button("Back to Menu");

        GridPane pane2 = new GridPane();
        l.setFont(Font.font("Times New Roman", FontWeight.BOLD, 25));
        pane2.setAlignment(Pos.TOP_CENTER);
        pane2.add(l,0,0);
        pane2.add(l2, 0, 1);
        pane2.add(text1, 1, 1);
        pane2.add(tb, 2, 2);
        pane2.add(tb1, 2, 4);
        pane2.setVgap(5);
        pane2.setHgap(5);

        Scene s3 = new Scene(pane2, 700, 700);
        setBackgroundColor(pane2);
        stage.setScene(s3);

        Label add = new Label();

        tb.setOnAction(submitEvent -> {
            int id = Integer.parseInt(text1.getText());
            String info = this.staffManagement.TeacherSalary(id);
            add.setText(info);


            pane2.add(add, 2, 3);
        });

        backbutton2(tb1,stage);
    }

    public void calculateSalaryOfJuniorStaff(Stage stage) {
        Label l = new Label("Salary of Junior Staff:");
        Label l2 = new Label("Enter the Junior Staff ID to calculate salary:");
        TextField text1 = new TextField();

        Button tb = new Button("Submit");
        Button tb1 = new Button("Back to Menu");

        GridPane pane2 = new GridPane();
        l.setFont(Font.font("Times New Roman", FontWeight.BOLD, 25));
        pane2.setAlignment(Pos.TOP_CENTER);
        pane2.add(l,0,0);
        pane2.add(l2, 0, 1);
        pane2.add(text1, 1, 1);
        pane2.add(tb, 2, 2);
        pane2.add(tb1, 2, 4);
        pane2.setVgap(5);
        pane2.setHgap(5);

        Scene s3 = new Scene(pane2, 700, 700);
        setBackgroundColor(pane2);
        stage.setScene(s3);

        Label add = new Label();

        tb.setOnAction(submitEvent -> {
            int id = Integer.parseInt(text1.getText());
            String info = this.staffManagement.JuniorStaffSalary(id);
            add.setText(info);
            pane2.add(add, 2, 3);
        });
        backbutton2(tb1,stage);
    }
    public void displayFee(Stage stage) {
        Label l = new Label("Fee of Student:");
        Label l2 = new Label("Enter the Student ID to see the fee:");
        TextField text1 = new TextField();

        Button tb = new Button("Submit");
        Button tb1 = new Button("Back to Menu");

        GridPane pane2 = new GridPane();
        l.setFont(Font.font("Times New Roman", FontWeight.BOLD, 25));
        pane2.setAlignment(Pos.TOP_CENTER);
        pane2.add(l,0,0);
        pane2.add(l2, 0, 1);
        pane2.add(text1, 1, 1);
        pane2.add(tb, 2, 2);
        pane2.add(tb1, 2, 4);
        pane2.setVgap(5);
        pane2.setHgap(5);

        Scene s3 = new Scene(pane2, 700, 700);
        setBackgroundColor(pane2);
        stage.setScene(s3);

        Label add = new Label();

        tb.setOnAction(submitEvent -> {
            int id = Integer.parseInt(text1.getText());
            String info = this.studentManagement.DisplayingFee(id);

            add.setText(info);
            pane2.add(add, 2, 3);
        });

        backbutton(tb1,stage);
    }

    public void seeScholarship(Stage stage) {
        Label l = new Label("Scholarship of Student:");
        Label l2 = new Label("Enter the Student ID to apply for scholarship:");
        TextField text1 = new TextField();

        Button tb = new Button("Submit");
        Button tb1 = new Button("Back to Menu");

        GridPane pane2 = new GridPane();
        l.setFont(Font.font("Times New Roman", FontWeight.BOLD, 25));
        pane2.setAlignment(Pos.TOP_CENTER);
        pane2.add(l,0,0);
        pane2.add(l2, 0, 1);
        pane2.add(text1, 1, 1);
        pane2.add(tb, 2, 2);
        pane2.add(tb1, 2, 5);
        pane2.setVgap(5);
        pane2.setHgap(5);

        Scene s3 = new Scene(pane2, 700, 700);
        setBackgroundColor(pane2);
        stage.setScene(s3);

        Label add = new Label();

        tb.setOnAction(submitEvent -> {
            int id = Integer.parseInt(text1.getText());
            String info = this.studentManagement.ScholarShip(id);

            add.setText(info);
            pane2.add(add, 2, 3);
        });

        backbutton(tb1,stage);
    }

    public void UpdateStudentInformation(Stage stage) {
        Label l = new Label("Updating Student Information");
        Label l2 = new Label("Enter the Student ID to update information:");
        TextField text1 = new TextField();

        Label l6 = new Label("Contact Info");
        TextField text6 = new TextField();

        Label l7 = new Label("Email");
        TextField text7 = new TextField();

        Label l11 = new Label("Father Contact");
        TextField text11 = new TextField();

        Label l12 = new Label("Mother Contact");
        TextField text12 = new TextField();

        Label l15 = new Label("Father Email");
        TextField text15 = new TextField();

        Button tb = new Button("Submit");
        Button tb1 = new Button("Back to Menu");

        GridPane pane2 = new GridPane();
        l.setFont(Font.font("Times New Roman", FontWeight.BOLD, 25));
        pane2.setAlignment(Pos.TOP_CENTER);
        pane2.add(l,0,0);
        pane2.add(l2, 0, 1);
        pane2.add(text1, 1, 1);
        pane2.add(l6, 0, 2);
        pane2.add(text6, 1, 2);
        pane2.add(l7, 0, 3);
        pane2.add(text7, 1, 3);
        pane2.add(l11, 0, 5);
        pane2.add(text11, 1, 5);
        pane2.add(l12, 0, 6);
        pane2.add(text12, 1, 6);
        pane2.add(l15, 0, 7);
        pane2.add(text15, 1, 7);
        pane2.add(tb, 2, 8);
        pane2.add(tb1, 2, 10);
        pane2.setHgap(5);
        pane2.setVgap(5);

        Scene s3 = new Scene(pane2, 700, 700);
        setBackgroundColor(pane2);
        stage.setScene(s3);

        Label add = new Label();

        tb.setOnAction(submitEvent -> {
            int id = Integer.parseInt(text1.getText());
            long contact_info = Long.parseLong(text6.getText());
            String email = text7.getText();
            long f_contact_info = Long.parseLong(text11.getText());
            long m_contact_info = Long.parseLong(text12.getText());
            String f_em = text15.getText();

            String info = this.studentManagement.UpdatingInfoById(id, email, contact_info, f_contact_info, m_contact_info, f_em);

            add.setText(info);
            pane2.add(add, 0, 9);
        });

        backbutton(tb1,stage);

    }

    public void UpdatingTeacherInfo(Stage stage) {
        Label l = new Label("Updating Student Information");
        Label l2 = new Label("Enter the Student ID to update information:");
        TextField text1 = new TextField();
        Label l06 = new Label("Contact Info");
        TextField t06 = new TextField();

        Label l07 = new Label("Email");
        TextField t07 = new TextField();

        Button tb = new Button("Submit");
        Button tb1 = new Button("Back to Menu");

        GridPane p2 = new GridPane();
        l.setFont(Font.font("Times New Roman", FontWeight.BOLD, 25));
        p2.setAlignment(Pos.TOP_CENTER);
        p2.setVgap(5);
        p2.setHgap(5);
        p2.add(l,0,0);
        p2.add(l2, 0, 1);
        p2.add(text1, 1, 1);
        p2.add(l06, 0, 2);
        p2.add(t06, 1, 2);
        p2.add(l07, 0, 3);
        p2.add(t07, 1, 3);
        p2.add(tb, 2, 4);
        p2.add(tb1, 2, 6);

        Scene s3 = new Scene(p2, 700, 700);
        setBackgroundColor(p2);

        stage.setScene(s3);

        Label add = new Label();

        tb.setOnAction(submitEvent -> {
            int id = Integer.parseInt(text1.getText());
            long contact_info = Long.parseLong(t06.getText());
            String email = t07.getText();
            String info = this.staffManagement.UpdateTeacherInfoById(id, email, contact_info);

            add.setText(info);
            p2.add(add, 0, 9);
        });
        backbutton2(tb1,stage);
    }
    public void displayPrincipleInfo(Stage stage)
    {
        principle = new Principle("Zaviyar", 28, 1, 12345678, true, 0300-42560650, "Zavi@gmail.com", "Bahria Town");
        Button tb1 = new Button("Back to Menu");
        Label label = new Label("Displaying Principle Information");
        Label l = new Label();
        label.setFont(Font.font("Times New Roman", FontWeight.BOLD, 25));
        l.setText(String.valueOf(principle.toString()));
        GridPane p = new GridPane();
        p.setAlignment(Pos.TOP_CENTER);
        p.add(label,0,0);
        p.add(l,0,1);
        p.add(tb1,2,13);
        Scene s3 = new Scene(p, 700, 700);
        setBackgroundColor(p);

        stage.setScene(s3);
        backbutton2(tb1,stage);
    }
    public void displayStudentInfo(Stage stage) {
        Button tb1 = new Button("Back to Menu");

        Label label = new Label("Displaying Student Information");
        Label l = new Label();

        StringBuilder studentinfo = new StringBuilder();
        File file = new File("data/Student.txt");  // ✅ relative path

        if (!file.exists()) {
            studentinfo.append("No student records found.\n");
        } else {
            try (Scanner sc = new Scanner(file)) {
                while (sc.hasNextLine()) {
                    String line = sc.nextLine();
                    studentinfo.append(line).append("\n");
                }
            } catch (IOException e) {
                studentinfo.append("Error reading student records.");
                e.printStackTrace();
            }
        }

        label.setFont(Font.font("Times New Roman", FontWeight.BOLD, 25));
        l.setText(studentinfo.toString());

        GridPane p = new GridPane();
        p.setAlignment(Pos.TOP_CENTER);
        p.setVgap(10);
        p.setHgap(10);

        p.add(label, 0, 0);
        p.add(l, 0, 1);
        p.add(tb1, 0, 2);

        Scene s3 = new Scene(p, 700, 700);
        setBackgroundColor(p);
        stage.setScene(s3);

        backbutton(tb1, stage);
    }

    public void displayTeacherInfo(Stage stage) {
        Button tb1 = new Button("Back to Menu");

        Label label = new Label("Displaying Teacher Information");
        Label l = new Label();

        StringBuilder teacherinfo = new StringBuilder();
        File file = new File("data/Teacher.txt");  // ✅ relative path

        if (!file.exists()) {
            teacherinfo.append("No teacher records found.\n");
        } else {
            try (Scanner sc = new Scanner(file)) {
                while (sc.hasNextLine()) {
                    String line = sc.nextLine();
                    teacherinfo.append(line).append("\n");
                }
            } catch (IOException e) {
                teacherinfo.append("Error reading teacher records.");
                e.printStackTrace();
            }
        }

        l.setText(teacherinfo.toString());

        label.setFont(Font.font("Times New Roman", FontWeight.BOLD, 25));

        GridPane p = new GridPane();
        p.setAlignment(Pos.TOP_CENTER);
        p.setVgap(10);
        p.setHgap(10);

        p.add(label, 0, 0);
        p.add(l, 0, 1);
        p.add(tb1, 0, 2);

        Scene s3 = new Scene(p, 700, 700);
        setBackgroundColor(p);
        stage.setScene(s3);

        backbutton2(tb1, stage);
    }

    public void displayJuniorStaffInfo(Stage stage) {
        Button tb1 = new Button("Back to Menu");

        Label label = new Label("Displaying Junior Staff Information");
        Label l = new Label();

        StringBuilder juniorstaffInfo = new StringBuilder();
        File file = new File("data/Staff.txt");  // ✅ relative path

        if (!file.exists()) {
            juniorstaffInfo.append("No junior staff records found.\n");
        } else {
            try (Scanner sc = new Scanner(file)) {
                while (sc.hasNextLine()) {
                    String line = sc.nextLine();
                    juniorstaffInfo.append(line).append("\n");
                }
            } catch (IOException e) {
                juniorstaffInfo.append("Error reading junior staff records.");
                e.printStackTrace();
            }
        }

        l.setText(juniorstaffInfo.toString());

        label.setFont(Font.font("Times New Roman", FontWeight.BOLD, 25));

        GridPane p = new GridPane();
        p.setAlignment(Pos.TOP_CENTER);
        p.setVgap(10);
        p.setHgap(10);

        p.add(label, 0, 0);
        p.add(l, 0, 1);
        p.add(tb1, 0, 2);  // ✅ cleaner placement

        Scene s3 = new Scene(p, 700, 700);
        setBackgroundColor(p);
        stage.setScene(s3);

        backbutton2(tb1, stage);
    }

    public void assignCourses(Stage stage) {
        Label l = new Label("Assigning Courses to Student:");
        Label l2 = new Label("Enter the Student ID:");
        TextField text1 = new TextField();

        Label l3 = new Label("Enter the Class Name (e.g., 5, 6, 7, 8):");
        TextField classText = new TextField();

        Button tb = new Button("Submit");
        Button tb1 = new Button("Back to Menu");

        GridPane pane2 = new GridPane();
        l.setFont(Font.font("Times New Roman", FontWeight.BOLD, 25));
        pane2.setAlignment(Pos.TOP_CENTER);
        pane2.add(l, 0, 0);
        pane2.add(l2, 0, 1);
        pane2.add(text1, 1, 1);
        pane2.add(l3, 0, 2);
        pane2.add(classText, 1, 2);
        pane2.add(tb, 2, 3);
        pane2.add(tb1, 2, 6);
        pane2.setVgap(5);
        pane2.setHgap(5);

        Scene s3 = new Scene(pane2, 700, 700);
        setBackgroundColor(pane2);
        stage.setScene(s3);

        Label add = new Label();
        pane2.add(add, 2, 5); // Add the label once

        tb.setOnAction(submitEvent -> {
            try {
                int id = Integer.parseInt(text1.getText());
                int className = Integer.parseInt(classText.getText());
                Class studentClass = new Class(className);

                if (className >= 1 && className <= 8) {
                    String info = this.studentManagement.assignCoursesToStudent(id, studentClass);
                    Class class1 = new Class(5);
                    String i = this.studentManagement.assignCoursesToStudent(9,class1);
                    add.setText(info);
                } else {
                    throw new InputMismatchException("Class name must be between 1 and 8");
                }
            } catch (NumberFormatException e) {
                add.setText("Invalid input. Please enter numeric values for ID and Class Name.");
            } catch (InputMismatchException hi) {
                add.setText(hi.getMessage());
            }
        });

        backbutton(tb1, stage);
    }

    public void viewAssignedCourses(Stage stage) {
        Label l = new Label("View Assigned Courses:");
        Label l2 = new Label("Enter the Student ID to view assigned courses:");
        TextField text1 = new TextField();

        Button tb = new Button("Submit");
        Button tb1 = new Button("Back to Menu");

        GridPane pane2 = new GridPane();
        l.setFont(Font.font("Times New Roman", FontWeight.BOLD, 25));
        pane2.setAlignment(Pos.TOP_CENTER);
        pane2.add(l,0,0);
        pane2.add(l2, 0, 1);
        pane2.add(text1, 1, 1);
        pane2.add(tb, 2, 2);
        pane2.add(tb1, 2, 4);
        pane2.setVgap(5);
        pane2.setHgap(5);

        Scene s3 = new Scene(pane2, 700, 700);
        setBackgroundColor(pane2);
        stage.setScene(s3);

        Label add = new Label();

        tb.setOnAction(submitEvent -> {
            int id = Integer.parseInt(text1.getText());
            String info = this.studentManagement.viewAssignedCourses(id);

            add.setText(info);
            pane2.add(add, 2, 3);
        });

        backbutton(tb1,stage);
    }
    public void seeResult(Stage stage) {
        Label result = new Label("Students Result");
        Label see_grade = new Label("Enter Student Id to see result");
        TextField t1 = new TextField();

        Button tb = new Button("Submit");
        Button tb1 = new Button("Back to Menu");

        GridPane pane2 = new GridPane();
        result.setFont(Font.font("Times New Roman", FontWeight.BOLD, 25));
        pane2.setAlignment(Pos.TOP_CENTER);
        pane2.add(result,0,0);
        pane2.add(see_grade, 0, 1);
        pane2.add(t1, 1, 1);
        pane2.add(tb, 3, 2);
        pane2.add(tb1, 3, 20);
        pane2.setVgap(5);
        pane2.setHgap(5);

        Scene s3 = new Scene(pane2, 700, 700);
        setBackgroundColor(pane2);
        stage.setScene(s3);

        Label add = new Label();
        pane2.add(add, 0, 3);
        tb.setOnAction(submitEvent -> {
            int id = Integer.parseInt(t1.getText());
            Student student = this.studentManagement.getStudentById(id);

            if (student != null) {
                String info = this.studentManagement.viewAssignedCourses(id);

                if (student.isNewStudent()) {
                    add.setText("This student is a new admission");

                } else {
                    add.setText(info);

                    Label mid_final = new Label("Mid-Term or Final-Term");
                    RadioButton r1 = new RadioButton("Mid-Term");
                    RadioButton r2 = new RadioButton("Final-Term");

                    ToggleGroup tg1 = new ToggleGroup();
                    r1.setToggleGroup(tg1);
                    r2.setToggleGroup(tg1);
                    Button b1 = new Button("Next");
                    pane2.add(mid_final, 0, 7);
                    pane2.add(r1, 1, 7);
                    pane2.add(r2, 2, 7);
                    pane2.add(b1, 3, 8);
                    b1.setOnAction(b -> {
                        Label m1 = new Label("Enter marks for course 1");
                        TextField mt1 = new TextField();
                        Label m2 = new Label("Enter marks for course 2");
                        TextField mt2 = new TextField();
                        Label m3 = new Label("Enter marks for course 3");
                        TextField mt3 = new TextField();
                        Label m4 = new Label("Enter marks for course 4");
                        TextField mt4 = new TextField();
                        Label m5 = new Label("Enter marks for course 5");
                        TextField mt5 = new TextField();
                        Label m6 = new Label("Enter marks for course 6");
                        TextField mt6 = new TextField();
                        Label m7 = new Label("Enter marks for course 7");
                        TextField mt7 = new TextField();
                        Label m8 = new Label("Enter marks for course 8");
                        TextField mt8 = new TextField();
                        Button calculateButton = new Button(r1.isSelected() ?
                                "Calculate Mid-Term Results" : "Calculate Final Term Results");
                        pane2.add(m1, 0, 9);
                        pane2.add(mt1, 1, 9);
                        pane2.add(m2, 0, 10);
                        pane2.add(mt2, 1, 10);
                        pane2.add(m3, 0, 11);
                        pane2.add(mt3, 1, 11);
                        pane2.add(m4, 0, 12);
                        pane2.add(mt4, 1, 12);
                        pane2.add(m5, 0, 13);
                        pane2.add(mt5, 1, 13);
                        pane2.add(m6, 0, 14);
                        pane2.add(mt6, 1, 14);
                        pane2.add(m7, 0, 15);
                        pane2.add(mt7, 1, 15);
                        pane2.add(m8, 0, 16);
                        pane2.add(mt8, 1, 16);
                        pane2.add(calculateButton, 2, 17);
                        calculateButton.setOnAction(calculateEvent -> {
                            Grade g = new Grade();
                            g.setMarks1(Integer.parseInt(mt1.getText()));
                            g.setMarks2(Integer.parseInt(mt2.getText()));
                            g.setMarks3(Integer.parseInt(mt3.getText()));
                            g.setMarks4(Integer.parseInt(mt4.getText()));
                            g.setMarks5(Integer.parseInt(mt5.getText()));
                            g.setMarks6(Integer.parseInt(mt6.getText()));
                            g.setMarks7(Integer.parseInt(mt7.getText()));
                            g.setMarks8(Integer.parseInt(mt8.getText()));
                            boolean exceedMidtermLimit = g.getMarks1() > 50 || g.getMarks2() > 50 || g.getMarks3() > 50 || g.getMarks4() > 50 || g.getMarks5() > 50 || g.getMarks6() > 50 || g.getMarks7() > 50 || g.getMarks8() > 50;
                            boolean exceedFinalTermLimit = g.getMarks1() > 100 || g.getMarks2() > 100 || g.getMarks3() > 100 || g.getMarks4() > 100 || g.getMarks5() > 100 || g.getMarks6() > 100 || g.getMarks7() > 100 || g.getMarks8() > 100;

                            if (r1.isSelected() && exceedMidtermLimit) {
                                showMarksErrorAlert("Midterm Marks");
                            } else if (r2.isSelected() && exceedFinalTermLimit) {
                                showMarksErrorAlert("Final Marks");
                            } else {
                                if (r1.isSelected()) {
                                    g.setMidtermMarks();
                                    addResultLabels(pane2,
                                        String.valueOf(g.calculateMidtermPercentage()), g.getMidtermGrade(), 18);
                                } if (r2.isSelected()) {
                                    g.setFinalTermMarks();
                                    addResultLabels(pane2,
                                            String.valueOf(g.calculateFinalTermPercentage()), g.getFinalTermGrade(),
                                            19);
                                }
                            }
                        });
                    });

                }
            }
        });

        backbutton(tb1,stage);
    }
    private void showMarksErrorAlert(String term) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText("One or more individual marks for " + term + " exceed the limit.");
        alert.showAndWait();
    }
    private void addResultLabels(GridPane pane, String percentage, String grade, int row) {
        Label percentageLabel = new Label("Percentage: " + percentage);
        Label gradeLabel = new Label("Grade: " + grade);

        pane.add(percentageLabel, 0, row);
        pane.add(gradeLabel, 1, row);
    }
    private void addBook(Stage stage) {
        Label l = new Label("Adding a Book:");
        Label titleLabel = new Label("Enter Title:");
        TextField titleTextField = new TextField();

        Label authorLabel = new Label("Enter Author:");
        TextField authorTextField = new TextField();

        Label isbnLabel = new Label("Enter ISBN:");
        TextField isbnTextField = new TextField();

        Button addButton = new Button("Add Book");
        Button backButton = new Button("Back to Menu");

        GridPane pane = new GridPane();
        l.setFont(Font.font("Times New Roman", FontWeight.BOLD, 25));
        pane.setAlignment(Pos.TOP_CENTER);
        pane.add(l, 0, 0);
        pane.add(titleLabel, 0, 1);
        pane.add(titleTextField, 1, 1);
        pane.add(authorLabel, 0, 2);
        pane.add(authorTextField, 1, 2);
        pane.add(isbnLabel, 0, 3);
        pane.add(isbnTextField, 1, 3);
        pane.add(addButton, 2, 4);
        pane.add(backButton, 2, 6);
        pane.setVgap(5);
        pane.setHgap(5);

        Scene scene = new Scene(pane, 700, 700);
        setBackgroundColor(pane);
        stage.setScene(scene);

        Label resultLabel = new Label();

        addButton.setOnAction(event -> {
            try {
                String title = titleTextField.getText();
                String author = authorTextField.getText();
                int isbn = Integer.parseInt(isbnTextField.getText());

                Book newBook = new Book(title, author, isbn, true);

                String result = libraryManagement.addBook(newBook);

                resultLabel.setText(result);
                pane.add(resultLabel, 2, 5);
            } catch (NumberFormatException e) {
                resultLabel.setText("Invalid input. Please enter a numeric value for ISBN.");
                pane.add(resultLabel, 2, 5);
            }
        });

        backbutton3(backButton,stage);
    }
    private void assignBook(Stage stage) {
        Label l = new Label("Assigning Book to Student:");
        Label l2 = new Label("Enter Student ID:");
        TextField studentIdTextField = new TextField();

        Label l3 = new Label("Enter Book ISBN:");
        TextField isbnTextField = new TextField();

        Button assignButton = new Button("Assign");
        Button backButton = new Button("Back to Menu");

        GridPane pane = new GridPane();
        l.setFont(Font.font("Times New Roman", FontWeight.BOLD, 25));
        pane.setAlignment(Pos.TOP_CENTER);
        pane.add(l, 0, 0);
        pane.add(l2, 0, 1);
        pane.add(studentIdTextField, 1, 1);
        pane.add(l3, 0, 2);
        pane.add(isbnTextField, 1, 2);
        pane.add(assignButton, 2, 3);
        pane.add(backButton, 2, 5);
        pane.setVgap(5);
        pane.setHgap(5);

        Scene scene3 = new Scene(pane, 700, 700);
        setBackgroundColor(pane);
        stage.setScene(scene3);

        Label resultLabel = new Label();

        assignButton.setOnAction(event -> {
            try {
                int studentId = Integer.parseInt(studentIdTextField.getText());
                int isbn = Integer.parseInt(isbnTextField.getText());

                boolean success = libraryManagement.checkoutBook(isbn, studentId);

                if (success) {
                    resultLabel.setText("Book assigned successfully.");
                } else {
                    resultLabel.setText("Failed to assign book. Please check student ID and book ISBN.");
                }

                pane.add(resultLabel, 2, 4);
            } catch (NumberFormatException e) {
                resultLabel.setText("Invalid input. Please enter numeric values for Student ID and Book ISBN.");
                pane.add(resultLabel, 2, 4);
            }
        });

        backbutton3(backButton,stage);
    }
    private void returnBook(Stage stage) {
        Label l = new Label("Returning Book:");
        Label l2 = new Label("Enter Book ISBN:");
        TextField isbnTextField = new TextField();

        Button returnButton = new Button("Return");
        Button backButton = new Button("Back to Menu");

        GridPane pane = new GridPane();
        l.setFont(Font.font("Times New Roman", FontWeight.BOLD, 25));
        pane.setAlignment(Pos.TOP_CENTER);
        pane.add(l, 0, 0);
        pane.add(l2, 0, 1);
        pane.add(isbnTextField, 1, 1);
        pane.add(returnButton, 2, 2);
        pane.add(backButton, 2, 4);
        pane.setVgap(5);
        pane.setHgap(5);

        Scene scene3 = new Scene(pane, 700, 700);
        setBackgroundColor(pane);
        stage.setScene(scene3);

        Label resultLabel = new Label();

        returnButton.setOnAction(event -> {
            try {
                int isbn = Integer.parseInt(isbnTextField.getText());

                boolean success = libraryManagement.returnBook(isbn);

                if (success) {
                    resultLabel.setText("Book returned successfully.");
                } else {
                    resultLabel.setText("Failed to return book. Please check the book ISBN.");
                }

                pane.add(resultLabel, 2, 3);
            } catch (NumberFormatException e) {
                resultLabel.setText("Invalid input. Please enter a numeric value for Book ISBN.");
                pane.add(resultLabel, 2, 3);
            }
        });

        backbutton3(backButton, stage);
    }
    private void displayAvailableBooks(Stage stage) {
        Label l = new Label("Displaying Available Books:");
        Button backButton = new Button("Back to Menu");

        GridPane pane = new GridPane();
        l.setFont(Font.font("Times New Roman", FontWeight.BOLD, 25));
        pane.setAlignment(Pos.TOP_CENTER);
        pane.add(l, 0, 0);
        pane.add(backButton, 2, 1);
        pane.setVgap(5);
        pane.setHgap(5);

        Scene scene3 = new Scene(pane, 700, 700);
        setBackgroundColor(pane);
        stage.setScene(scene3);

        Label availableBooksLabel = new Label(libraryManagement.displayAvailableBooks());
        pane.add(availableBooksLabel, 0, 1);

        backbutton3(backButton, stage);
    }
    private void displayCheckedOutBooks(Stage stage) {
        Label l = new Label("Displaying Checked Out Books:");
        Button backButton = new Button("Back to Menu");

        GridPane pane = new GridPane();
        l.setFont(Font.font("Times New Roman", FontWeight.BOLD, 25));
        pane.setAlignment(Pos.TOP_CENTER);
        pane.add(l, 0, 0);
        pane.add(backButton, 2, 1);
        pane.setVgap(5);
        pane.setHgap(5);

        Scene scene3 = new Scene(pane, 700, 700);
        setBackgroundColor(pane);
        stage.setScene(scene3);

        Label checkedOutBooksLabel = new Label(libraryManagement.displayCheckedOutBooks());
        pane.add(checkedOutBooksLabel, 0, 1);

        backbutton3(backButton, stage);
    }
    public void setBackgroundColor(GridPane pane) {
        BackgroundFill backgroundFill2 = new BackgroundFill(Color.DARKGRAY, null, null);
        Background background2 = new Background(backgroundFill2);
        pane.setBackground(background2);
    }
    public void backbutton(Button tb1, Stage stage) {
        tb1.setOnAction(backEvent -> {
            stage.setScene(studentscene);
        });
    }
    public void backbutton2(Button tb1, Stage stage) {
        tb1.setOnAction(backEvent -> {
            stage.setScene(staffscene);
        });
    }
    public void backbutton3(Button tb1, Stage stage) {
        tb1.setOnAction(backEvent -> {
            stage.setScene(libraryscene);
        });
    }
}