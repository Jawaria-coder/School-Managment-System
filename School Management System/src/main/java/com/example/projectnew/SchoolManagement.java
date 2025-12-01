package com.example.projectnew;

import javafx.application.Application;
import javafx.geometry.HPos;
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
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

import java.io.*;
import java.util.ArrayList;
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
        // --- Logo ---
        Image image = new Image(getClass().getResource("/logo.png").toExternalForm());
        ImageView imageView = new ImageView(image);
        imageView.setFitWidth(150);
        imageView.setFitHeight(150);

        // --- Labels ---
        Label schoolName = new Label("THE FAITH SCHOOL");
        schoolName.setFont(Font.font("Arial", FontWeight.BOLD, 28));
        schoolName.setTextFill(Color.DARKBLUE);

        Label campusName = new Label("PALM TREE CAMPUS");
        campusName.setFont(Font.font("Arial", FontWeight.BOLD, 22));
        campusName.setTextFill(Color.DARKBLUE);

        Label instruction = new Label("Select a Management Section:");
        instruction.setFont(Font.font("Arial", FontWeight.BOLD, 18));
        instruction.setTextFill(Color.BLACK);

        // --- Buttons ---
        Button studentButton = new Button("Student Management");
        Button staffButton = new Button("Staff Management");
        Button libraryButton = new Button("Library Management");

        studentButton.setPrefSize(200, 60);
        staffButton.setPrefSize(200, 60);
        libraryButton.setPrefSize(200, 60);

        studentButton.setFont(Font.font("Arial", FontWeight.BOLD, 16));
        staffButton.setFont(Font.font("Arial", FontWeight.BOLD, 16));
        libraryButton.setFont(Font.font("Arial", FontWeight.BOLD, 16));

        studentButton.setStyle("-fx-background-color: royalblue; -fx-text-fill: white;");
        staffButton.setStyle("-fx-background-color: darkgreen; -fx-text-fill: white;");
        libraryButton.setStyle("-fx-background-color: darkred; -fx-text-fill: white;");

        // --- Button actions ---
        studentButton.setOnAction(sb -> StudentLogin(stage));
        staffButton.setOnAction(st -> StaffLogin(stage));
        libraryButton.setOnAction(lib -> LibraryLogin(stage));

        // --- Layouts ---
        VBox logoBox = new VBox(imageView);
        logoBox.setAlignment(Pos.CENTER);
        logoBox.setPadding(new Insets(30, 0, 20, 0));

        VBox titleBox = new VBox(schoolName, campusName);
        titleBox.setAlignment(Pos.CENTER);
        titleBox.setSpacing(5);

        VBox buttonBox = new VBox(studentButton, staffButton, libraryButton);
        buttonBox.setAlignment(Pos.CENTER);
        buttonBox.setSpacing(20);
        buttonBox.setPadding(new Insets(40, 0, 0, 0));

        VBox mainLayout = new VBox(logoBox, titleBox, instruction, buttonBox);
        mainLayout.setAlignment(Pos.TOP_CENTER);
        mainLayout.setSpacing(20);
        mainLayout.setPadding(new Insets(20));
        mainLayout.setStyle("-fx-background-color: #f0f8ff;");

        mainScene = new Scene(mainLayout, 700, 700);
    }

    private void createLoginLayout(Stage stage, String title, String correctUsername, String correctPassword, Runnable nextSceneAction) {
        // ------------------ Labels ------------------
        Label heading = new Label(title + " Login");
        heading.setFont(Font.font("Arial", FontWeight.BOLD, 26));
        heading.setTextFill(Color.DARKBLUE);

        Label instruction = new Label("Enter your credentials to continue:");
        instruction.setFont(Font.font(16));

        Label usernameLabel = new Label("Username:");
        usernameLabel.setFont(Font.font("Arial", 16));
        TextField usernameField = new TextField();
        usernameField.setPrefWidth(200);

        Label passwordLabel = new Label("Password:");
        passwordLabel.setFont(Font.font("Arial", 16));
        PasswordField passwordField = new PasswordField();
        passwordField.setPrefWidth(200);

        // ------------------ Buttons ------------------
        Button loginButton = new Button("Login");
        loginButton.setStyle("-fx-background-color: royalblue; -fx-text-fill: white;");
        loginButton.setPrefWidth(140);

        Button backButton = new Button("Back");
        backButton.setStyle("-fx-background-color: crimson; -fx-text-fill: white;");
        backButton.setPrefWidth(140);

        HBox buttonBox = new HBox(20, loginButton, backButton);
        buttonBox.setAlignment(Pos.CENTER);

        // ------------------ Feedback ------------------
        Label feedback = new Label();
        feedback.setFont(Font.font("Arial", 14));
        feedback.setTextFill(Color.DARKGREEN);
        feedback.setWrapText(true);
        feedback.setTextAlignment(TextAlignment.CENTER);
        feedback.setMaxWidth(350);

        // ------------------ Layout ------------------
        GridPane formGrid = new GridPane();
        formGrid.setAlignment(Pos.CENTER);
        formGrid.setHgap(10);
        formGrid.setVgap(15);
        formGrid.add(usernameLabel, 0, 0);
        formGrid.add(usernameField, 1, 0);
        formGrid.add(passwordLabel, 0, 1);
        formGrid.add(passwordField, 1, 1);

        VBox layout = new VBox(25, heading, instruction, formGrid, buttonBox, feedback);
        layout.setAlignment(Pos.TOP_CENTER);
        layout.setPadding(new Insets(30));
        layout.setStyle("-fx-background-color: #f0f8ff;");

        Scene loginScene = new Scene(layout, 700, 700);
        setBackgroundColor(layout);
        stage.setScene(loginScene);

        // ------------------ Actions ------------------
        loginButton.setOnAction(event -> {
            String enteredUsername = usernameField.getText();
            String enteredPassword = passwordField.getText();

            if (enteredUsername.equals(correctUsername) && enteredPassword.equals(correctPassword)) {
                feedback.setTextFill(Color.DARKGREEN);
                feedback.setText("Login successful!");
                nextSceneAction.run();  // call the appropriate scene creation
            } else {
                feedback.setTextFill(Color.RED);
                feedback.setText("Username or password is incorrect.");
            }
        });

        logout(backButton, stage);
    }

    private void StudentLogin(Stage stage) {
        createLoginLayout(stage, "Student Management", "Jiya@gmail.com", "jaw12345", () -> Studentscene(stage));
    }

    private void StaffLogin(Stage stage) {
        createLoginLayout(stage, "Staff Management", "Eesha@gmail.com", "eesha12345", () -> Staffscene(stage));
    }

    private void LibraryLogin(Stage stage) {
        createLoginLayout(stage, "Library Management", "Faiza@gmail.com", "faiza12345", () -> LibraryScene(stage));
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

    // ------------------ Staff Management Scene ------------------
    private void Staffscene(Stage stage) {
        Label heading = new Label("Staff Management");
        heading.setFont(Font.font("Arial", FontWeight.BOLD, 26));
        heading.setTextFill(Color.DARKBLUE);

        Label instruction = new Label("Choose an option:");
        instruction.setFont(Font.font(16));

        ComboBox<String> choice = new ComboBox<>();
        choice.getItems().addAll(
                "1. Add a Teacher", "2. Remove a Teacher",
                "3. Add Junior Staff", "4. Remove Junior Staff",
                "5. Calculate Salary of a Teacher", "6. Calculate Salary of a Junior Staff",
                "7. Update Teacher Information", "8. Display Teachers Information.",
                "9. Display Junior Staffs Information.", "10. Display Principle"
        );
        choice.setPrefWidth(350);

        Button submit = new Button("Submit");
        submit.setStyle("-fx-background-color: royalblue; -fx-text-fill: white;");
        submit.setPrefWidth(140);
        Button logoutBtn = new Button("Log Out");
        logoutBtn.setStyle("-fx-background-color: crimson; -fx-text-fill: white;");
        logoutBtn.setPrefWidth(140);

        HBox buttons = new HBox(20, submit, logoutBtn);
        buttons.setAlignment(Pos.CENTER);

        VBox layout = new VBox(25, heading, instruction, choice, buttons);
        layout.setAlignment(Pos.TOP_CENTER);
        layout.setPadding(new Insets(30));
        layout.setStyle("-fx-background-color: #f0f8ff;");

        staffscene = new Scene(layout, 700, 700);
        setBackgroundColor(layout);
        stage.setScene(staffscene);

        submit.setOnAction(a -> {
            String selected = choice.getValue();
            if (selected == null) return;
            switch (selected) {
                case "1. Add a Teacher" -> addTeacher(stage);
                case "2. Remove a Teacher" -> removeEntity(stage, "Remove Teacher", "teacher");
                case "3. Add Junior Staff" -> addJuniorStaff(stage);
                case "4. Remove Junior Staff" -> removeEntity(stage, "Remove Junior Staff", "junior");
                case "5. Calculate Salary of a Teacher" -> calculateSalaryOfTeacher(stage);
                case "6. Calculate Salary of a Junior Staff" -> calculateSalaryOfJuniorStaff(stage);
                case "7. Update Teacher Information" -> UpdatingTeacherInfo(stage);
                case "8. Display Teachers Information." -> displayTeacherInfo(stage);
                case "9. Display Junior Staffs Information." -> displayJuniorStaffInfo(stage);
                case "10. Display Principle" -> displayPrincipleInfo(stage);
            }
        });

        logout(logoutBtn, stage);
    }

    // ------------------ Student Management Scene ------------------
    private void Studentscene(Stage stage) {
        Label heading = new Label("Student Management");
        heading.setFont(Font.font("Arial", FontWeight.BOLD, 26));
        heading.setTextFill(Color.DARKBLUE);

        Label instruction = new Label("Choose an option:");
        instruction.setFont(Font.font(16));

        ComboBox<String> choice = new ComboBox<>();
        choice.getItems().addAll(
                "1. Admit a Student", "2. Remove a Student", "3. Display Fee of Student.",
                "4. Display ScholarShip.", "5. Assign Courses", "6. View Assigned Courses",
                "7. See Result", "8. Update Student Information", "9. Display Students Information."
        );
        choice.setPrefWidth(350);

        Button submit = new Button("Submit");
        submit.setStyle("-fx-background-color: royalblue; -fx-text-fill: white;");
        submit.setPrefWidth(140);
        Button logoutBtn = new Button("Log Out");
        logoutBtn.setStyle("-fx-background-color: crimson; -fx-text-fill: white;");
        logoutBtn.setPrefWidth(140);

        HBox buttons = new HBox(20, submit, logoutBtn);
        buttons.setAlignment(Pos.CENTER);

        VBox layout = new VBox(25, heading, instruction, choice, buttons);
        layout.setAlignment(Pos.TOP_CENTER);
        layout.setPadding(new Insets(30));
        layout.setStyle("-fx-background-color: #f0f8ff;");

        studentscene = new Scene(layout, 700, 700); // assign to class variable
        setBackgroundColor(layout);
        stage.setScene(studentscene);

        submit.setOnAction(a -> {
            String selected = choice.getValue();
            if (selected == null) return;
            switch (selected) {
                case "1. Admit a Student" -> admitStudent(stage);
                case "2. Remove a Student" -> removeEntity(stage, "Remove Student", "student");
                case "3. Display Fee of Student." -> displayFee(stage);
                case "4. Display ScholarShip." -> seeScholarship(stage);
                case "5. Assign Courses" -> assignCourses(stage);
                case "6. View Assigned Courses" -> viewAssignedCourses(stage);
                case "7. See Result" -> seeResult(stage);
                case "8. Update Student Information" -> UpdateStudentInformation(stage);
                case "9. Display Students Information." -> displayStudentInfo(stage);
            }
        });



        logout(logoutBtn, stage);
    }

    // ------------------ Library Management Scene ------------------
    private void LibraryScene(Stage stage) {
        Label heading = new Label("Library Management");
        heading.setFont(Font.font("Arial", FontWeight.BOLD, 26));
        heading.setTextFill(Color.DARKBLUE);

        Label instruction = new Label("Choose an option:");
        instruction.setFont(Font.font(16));

        ComboBox<String> choice = new ComboBox<>();
        choice.getItems().addAll(
                "1. Add a book", "2. Assign a book", "3. Return a book",
                "4. Display Books", "5. Display Checked Out Books"
        );
        choice.setPrefWidth(350);

        Button submit = new Button("Submit");
        submit.setStyle("-fx-background-color: royalblue; -fx-text-fill: white;");
        submit.setPrefWidth(140);
        Button logoutBtn = new Button("Log Out");
        logoutBtn.setStyle("-fx-background-color: crimson; -fx-text-fill: white;");
        logoutBtn.setPrefWidth(140);

        HBox buttons = new HBox(20, submit, logoutBtn);
        buttons.setAlignment(Pos.CENTER);

        VBox layout = new VBox(25, heading, instruction, choice, buttons);
        layout.setAlignment(Pos.TOP_CENTER);
        layout.setPadding(new Insets(30));
        layout.setStyle("-fx-background-color: #f0f8ff;");

        libraryscene = new Scene(layout, 700, 700);
        setBackgroundColor(layout);
        stage.setScene(libraryscene);

        submit.setOnAction(a -> {
            String selected = choice.getValue();
            if (selected == null) return;
            switch (selected) {
                case "1. Add a book" -> addBook(stage);
                case "2. Assign a book" -> assignBook(stage);
                case "3. Return a book" -> returnBook(stage);
                case "4. Display Books" -> displayAvailableBooks(stage);
                case "5. Display Checked Out Books" -> displayCheckedOutBooks(stage);
            }
        });

        logout(logoutBtn, stage);
    }

    // ------------------ Logout ------------------
    public void logout(Button logout, Stage stage) {
        logout.setOnAction(l -> stage.setScene(mainScene));
    }

    public void admitStudent(Stage stage) {
        // --- Heading ---
        Label heading = new Label("Add Student");
        heading.setFont(Font.font("Arial", FontWeight.BOLD, 28));
        heading.setTextFill(Color.DARKBLUE);

        // --- Grid for input fields ---
        GridPane grid = new GridPane();
        grid.setHgap(15);
        grid.setVgap(10);
        grid.setPadding(new Insets(20));

        // --- Personal Info ---
        Label nameLabel = new Label("Name:"); TextField nameField = new TextField();
        Label ageLabel = new Label("Age:"); TextField ageField = new TextField();
        Label idLabel = new Label("ID:"); TextField idField = new TextField();
        Label cnicLabel = new Label("CNIC:"); TextField cnicField = new TextField();
        Label genderLabel = new Label("Gender:");
        RadioButton male = new RadioButton("Male");
        RadioButton female = new RadioButton("Female");
        ToggleGroup genderGroup = new ToggleGroup();
        male.setToggleGroup(genderGroup); female.setToggleGroup(genderGroup);
        HBox genderBox = new HBox(10, male, female);

        // --- Contact Info ---
        Label contactLabel = new Label("Contact:"); TextField contactField = new TextField();
        Label emailLabel = new Label("Email:"); TextField emailField = new TextField();
        Label addressLabel = new Label("Address:"); TextField addressField = new TextField();

        // --- Parents Info ---
        Label fatherNameLabel = new Label("Father Name:"); TextField fatherField = new TextField();
        Label motherNameLabel = new Label("Mother Name:"); TextField motherField = new TextField();
        Label fatherContactLabel = new Label("Father Contact:"); TextField fatherContact = new TextField();
        Label motherContactLabel = new Label("Mother Contact:"); TextField motherContact = new TextField();
        Label fatherBusinessLabel = new Label("Father Business:"); TextField businessField = new TextField();
        Label fatherIncomeLabel = new Label("Father Income:"); TextField incomeField = new TextField();
        Label fatherEmailLabel = new Label("Father Email:"); TextField fatherEmail = new TextField();

        // --- Add to grid ---
        grid.add(nameLabel, 0, 0); grid.add(nameField, 1, 0);
        grid.add(ageLabel, 0, 1); grid.add(ageField, 1, 1);
        grid.add(idLabel, 0, 2); grid.add(idField, 1, 2);
        grid.add(cnicLabel, 0, 3); grid.add(cnicField, 1, 3);
        grid.add(genderLabel, 0, 4); grid.add(genderBox, 1, 4);
        grid.add(contactLabel, 0, 5); grid.add(contactField, 1, 5);
        grid.add(emailLabel, 0, 6); grid.add(emailField, 1, 6);
        grid.add(addressLabel, 0, 7); grid.add(addressField, 1, 7);
        grid.add(fatherNameLabel, 0, 8); grid.add(fatherField, 1, 8);
        grid.add(motherNameLabel, 0, 9); grid.add(motherField, 1, 9);
        grid.add(fatherContactLabel, 0, 10); grid.add(fatherContact, 1, 10);
        grid.add(motherContactLabel, 0, 11); grid.add(motherContact, 1, 11);
        grid.add(fatherBusinessLabel, 0, 12); grid.add(businessField, 1, 12);
        grid.add(fatherIncomeLabel, 0, 13); grid.add(incomeField, 1, 13);
        grid.add(fatherEmailLabel, 0, 14); grid.add(fatherEmail, 1, 14);

        // --- Feedback label ---
        Label feedback = new Label();
        feedback.setFont(Font.font("Arial", 16));

        // --- Buttons ---
        Button submitBtn = new Button("Submit");
        submitBtn.setStyle("-fx-background-color: #4CAF50; -fx-text-fill: white;");
        Button backBtn = new Button("Back");
        backBtn.setStyle("-fx-background-color: #f44336; -fx-text-fill: white;");
        HBox buttons = new HBox(15, submitBtn, backBtn);
        buttons.setAlignment(Pos.CENTER);

        // --- Main layout ---
        VBox layout = new VBox(20, heading, grid, buttons, feedback);
        layout.setPadding(new Insets(20));
        layout.setAlignment(Pos.TOP_CENTER);
        setBackgroundColor(layout);

        Scene scene = new Scene(layout, 700, 750);
        stage.setScene(scene);

        // --- Submit action ---
        submitBtn.setOnAction(e -> {
            try {
                Student student = new Student(
                        nameField.getText(),
                        Integer.parseInt(ageField.getText()),
                        Integer.parseInt(idField.getText()),
                        Long.parseLong(cnicField.getText()),
                        male.isSelected(),
                        Long.parseLong(contactField.getText()),
                        emailField.getText(),
                        addressField.getText(),
                        fatherField.getText(),
                        motherField.getText(),
                        Long.parseLong(fatherContact.getText()),
                        Long.parseLong(motherContact.getText()),
                        businessField.getText(),
                        Double.parseDouble(incomeField.getText()),
                        fatherEmail.getText()
                );

                this.studentManagement.admitStudent(student);

                // --- Write to file ---
                File file = new File("data/Student.txt");
                file.getParentFile().mkdirs();
                boolean writeHeader = !file.exists() || file.length() == 0;

                try (FileWriter writer = new FileWriter(file, true)) {
                    if (writeHeader) {
                        writer.write("ID,Name,Age,CNIC,Gender,Contact,Email,Address,FatherName,MotherName,FatherContact,MotherContact,FatherBusiness,FatherIncome,FatherEmail\n");
                    }
                    writer.write(
                            student.getId() + "," +
                                    student.getName() + "," +
                                    student.getAge() + "," +
                                    student.getCnic() + "," +
                                    (student.isGender() ? "Male" : "Female") + "," +
                                    student.getContact_info() + "," +
                                    student.getEmail() + "," +
                                    student.getAddress() + "," +
                                    student.getFather_name() + "," +
                                    student.getMother_name() + "," +
                                    student.getFather_contact() + "," +
                                    student.getMother_contact() + "," +
                                    student.getFather_business() + "," +
                                    student.getFather_income() + "," +
                                    student.getFather_email() + "\n"
                    );
                }

                feedback.setTextFill(Color.GREEN);
                feedback.setText("Student admitted successfully ✔");
            } catch (NumberFormatException ex) {
                feedback.setTextFill(Color.RED);
                feedback.setText("Please enter valid numeric values.");
            } catch (Exception ex) {
                feedback.setTextFill(Color.RED);
                feedback.setText("Error: " + ex.getMessage());
            }
        });

        backbutton(backBtn, stage);
    }


    // ---------- Utility methods ----------
    private HBox formRow(String label, Control input) {
        Label l = new Label(label);
        l.setMinWidth(150);
        l.setFont(Font.font("Arial", 16));
        HBox row = new HBox(10, l, input);
        row.setAlignment(Pos.CENTER_LEFT);
        return row;
    }

    private HBox radioRow(String label, RadioButton... radios) {
        Label l = new Label(label);
        l.setMinWidth(150);
        l.setFont(Font.font("Arial", 16));
        HBox radioBox = new HBox(10, radios);
        HBox row = new HBox(10, l, radioBox);
        row.setAlignment(Pos.CENTER_LEFT);
        return row;
    }

    private VBox pageLayout(String title) {
        Label heading = new Label(title);
        heading.setFont(Font.font("Arial", FontWeight.BOLD, 28));
        heading.setTextFill(Color.DARKBLUE);

        VBox box = new VBox(15);
        box.setPadding(new Insets(20));
        box.setAlignment(Pos.TOP_CENTER);
        box.getChildren().add(heading);

        return box;
    }

    // ---------- Add Teacher ----------
    public void addTeacher(Stage stage) {
        VBox layout = pageLayout("Add Teacher");

        TextField name = new TextField();
        TextField age = new TextField();
        TextField id = new TextField();
        TextField cnic = new TextField();
        TextField contact = new TextField();
        TextField email = new TextField();
        TextField address = new TextField();
        TextField quali = new TextField();
        TextField exp = new TextField();

        RadioButton male = new RadioButton("Male");
        RadioButton female = new RadioButton("Female");
        ToggleGroup genderGroup = new ToggleGroup();
        male.setToggleGroup(genderGroup);
        female.setToggleGroup(genderGroup);

        RadioButton certYes = new RadioButton("Yes");
        RadioButton certNo = new RadioButton("No");
        ToggleGroup certGroup = new ToggleGroup();
        certYes.setToggleGroup(certGroup);
        certNo.setToggleGroup(certGroup);

        RadioButton single = new RadioButton("Single");
        RadioButton married = new RadioButton("Married");
        ToggleGroup statusGroup = new ToggleGroup();
        single.setToggleGroup(statusGroup);
        married.setToggleGroup(statusGroup);

        layout.getChildren().addAll(
                formRow("Name", name),
                formRow("Age", age),
                formRow("ID", id),
                formRow("CNIC", cnic),
                radioRow("Gender", male, female),
                formRow("Contact", contact),
                formRow("Email", email),
                formRow("Address", address),
                formRow("Qualification", quali),
                formRow("Experience (Years)", exp),
                radioRow("Certificate", certYes, certNo),
                radioRow("Marital Status", single, married)
        );

        Button submit = new Button("Submit");
        Button back = new Button("Back");
        submit.setStyle("-fx-background-color: royalblue; -fx-text-fill: white;");
        back.setStyle("-fx-background-color: crimson; -fx-text-fill: white;");
        HBox buttons = new HBox(20, submit, back);
        buttons.setAlignment(Pos.CENTER);

        Label message = new Label();
        message.setFont(Font.font("Arial", 16));
        layout.getChildren().addAll(buttons, message);

        Scene scene = new Scene(layout, 700, 700);
        setBackgroundColor(layout);
        stage.setScene(scene);

        submit.setOnAction(e -> {
            try {
                Teacher t = new Teacher(
                        name.getText(),
                        Integer.parseInt(age.getText()),
                        Integer.parseInt(id.getText()),
                        Long.parseLong(cnic.getText()),
                        male.isSelected(),
                        Long.parseLong(contact.getText()),
                        email.getText(),
                        address.getText(),
                        quali.getText(),
                        Integer.parseInt(exp.getText()),
                        certYes.isSelected(),
                        single.isSelected()
                );
                this.staffManagement.addTeacher(t);

                // Append to file
                File file = new File("data/Teacher.txt");
                try (PrintWriter pw = new PrintWriter(new FileOutputStream(file, true))) {
                    String gender = male.isSelected() ? "Male" : "Female";
                    String line = t.getId() + "," + t.getName() + "," + t.getAge() + "," + t.getCnic() + "," +
                            gender + "," + t.getContact_info() + "," + t.getEmail() + "," + t.getAddress() + "," +
                            t.getQualification() + "," + t.getExperience_years() + "," + (t.isCertificate() ? "Yes" : "No") + "," +
                            (t.isMartial_status() ? "Single" : "Married");
                    pw.println(line);
                }

                message.setText("Teacher added successfully ✔");
                message.setTextFill(Color.GREEN);
            } catch (Exception ex) {
                message.setText("Invalid input! Please check your fields.");
                message.setTextFill(Color.RED);
            }
        });

        back.setOnAction(e -> backbutton2(back, stage));
    }

    public void addJuniorStaff(Stage stage) {
        VBox layout = pageLayout("Add Junior Staff");

        TextField name = new TextField();
        TextField age = new TextField();
        TextField id = new TextField();
        TextField cnic = new TextField();
        TextField contact = new TextField();
        TextField email = new TextField();
        TextField address = new TextField();
        TextField exp = new TextField();

        RadioButton male = new RadioButton("Male");
        RadioButton female = new RadioButton("Female");
        ToggleGroup genderGroup = new ToggleGroup();
        male.setToggleGroup(genderGroup);
        female.setToggleGroup(genderGroup);

        layout.getChildren().addAll(
                formRow("Name", name),
                formRow("Age", age),
                formRow("ID", id),
                formRow("CNIC", cnic),
                radioRow("Gender", male, female),
                formRow("Contact", contact),
                formRow("Email", email),
                formRow("Address", address),
                formRow("Experience Years", exp)
        );

        Button submit = new Button("Submit");
        Button back = new Button("Back");
        submit.setStyle("-fx-background-color: royalblue; -fx-text-fill: white;");
        back.setStyle("-fx-background-color: crimson; -fx-text-fill: white;");
        HBox buttons = new HBox(20, submit, back);
        buttons.setAlignment(Pos.CENTER);

        Label message = new Label();
        message.setFont(Font.font("Arial", 16));
        layout.getChildren().addAll(buttons, message);

        Scene scene = new Scene(layout, 700, 700);
        setBackgroundColor(layout);
        stage.setScene(scene);

        submit.setOnAction(e -> {
            try {
                JuniorStaff js = new JuniorStaff(
                        name.getText(),
                        Integer.parseInt(age.getText()),
                        Integer.parseInt(id.getText()),
                        Long.parseLong(cnic.getText()),
                        male.isSelected(),
                        Long.parseLong(contact.getText()),
                        email.getText(),
                        address.getText(),
                        Integer.parseInt(exp.getText())
                );
                this.staffManagement.addJuniorStaff(js);

                // Append to file
                File file = new File("data/Staff.txt");
                try (PrintWriter pw = new PrintWriter(new FileOutputStream(file, true))) {
                    String gender = male.isSelected() ? "Male" : "Female";
                    String line = js.getId() + "," + js.getName() + "," + js.getAge() + "," + js.getCnic() + "," +
                            gender + "," + js.getContact_info() + "," + js.getEmail() + "," + js.getAddress() + "," +
                            js.getExperience();
                    pw.println(line);
                }

                message.setText("Junior Staff added successfully ✔");
                message.setTextFill(Color.GREEN);
            } catch (Exception ex) {
                message.setText("Invalid input! Check your fields.");
                message.setTextFill(Color.RED);
            }
        });

        back.setOnAction(e -> backbutton2(back, stage));
    }


    // ---------- Remove Entity (Student / Teacher / Junior) ----------
    public void removeEntity(Stage stage, String title, String type) {
        // Heading
        Label heading = new Label(title);
        heading.setFont(Font.font("Arial", FontWeight.BOLD, 26));
        heading.setTextFill(Color.DARKBLUE);

        // ID Input
        Label idLabel = new Label("Enter " + type + " ID:");
        idLabel.setFont(Font.font(16));
        TextField idField = new TextField();
        idField.setPrefWidth(250);

        GridPane formGrid = new GridPane();
        formGrid.setHgap(20);
        formGrid.setVgap(15);
        formGrid.add(idLabel, 0, 0);
        formGrid.add(idField, 1, 0);
        formGrid.setAlignment(Pos.CENTER);

        // Buttons
        Button submitBtn = new Button("Submit");
        submitBtn.setStyle("-fx-background-color: royalblue; -fx-text-fill: white;");
        Button backBtn = new Button("Back");
        backBtn.setStyle("-fx-background-color: crimson; -fx-text-fill: white;");
        submitBtn.setPrefWidth(120); backBtn.setPrefWidth(120);

        HBox buttonBox = new HBox(20, submitBtn, backBtn);
        buttonBox.setAlignment(Pos.CENTER);

        // Feedback Label
        Label feedback = new Label();
        feedback.setFont(Font.font("Arial", 14));
        feedback.setWrapText(true);
        feedback.setTextAlignment(TextAlignment.CENTER);
        feedback.setMaxWidth(400);
        feedback.setAlignment(Pos.CENTER);

        // Layout
        VBox layout = new VBox(30, heading, formGrid, buttonBox, feedback);
        layout.setAlignment(Pos.TOP_CENTER);
        layout.setPadding(new Insets(30));
        layout.setStyle("-fx-background-color: #f0f8ff;");

        // Scene
        Scene scene = new Scene(layout, 700, 700);
        setBackgroundColor(layout);
        stage.setScene(scene);

        // Submit Button Action
        submitBtn.setOnAction(e -> {
            try {
                int id = Integer.parseInt(idField.getText());
                boolean removed = switch (type.toLowerCase()) {
                    case "student" -> this.studentManagement.removeStudent(id);
                    case "teacher" -> this.staffManagement.removeTeacher(id);
                    case "junior" -> this.staffManagement.removeJuniorStaff(id);
                    default -> false;
                };
                if (removed) {
                    feedback.setText(type + " with ID " + id + " removed successfully.");
                    feedback.setTextFill(Color.DARKGREEN);
                } else {
                    feedback.setText("No " + type + " found with the specified ID.");
                    feedback.setTextFill(Color.RED);
                }
            } catch (NumberFormatException ex) {
                feedback.setText("Please enter a valid numeric ID.");
                feedback.setTextFill(Color.RED);
            }
        });

        // Back Button Action
        backBtn.setOnAction(e -> {
            if (type.equalsIgnoreCase("student")) backbutton(backBtn, stage);
            else backbutton2(backBtn, stage);
        });
    }

    // 1. Calculate Salary of Teacher
    public void calculateSalaryOfTeacher(Stage stage) {
        Label heading = new Label("Teacher Salary");
        heading.setFont(Font.font("Arial", FontWeight.BOLD, 26));
        heading.setTextFill(Color.DARKBLUE);

        Label idLabel = new Label("Teacher ID:");
        idLabel.setFont(Font.font("Arial", 16));
        TextField idField = new TextField();
        idField.setPrefWidth(200);

        GridPane formGrid = new GridPane();
        formGrid.setHgap(20);
        formGrid.setVgap(15);
        formGrid.add(idLabel, 0, 0);
        formGrid.add(idField, 1, 0);
        formGrid.setAlignment(Pos.CENTER);

        Button submitBtn = new Button("Calculate");
        Button backBtn = new Button("Back");
        submitBtn.setStyle("-fx-background-color: royalblue; -fx-text-fill: white;");
        backBtn.setStyle("-fx-background-color: gray; -fx-text-fill: white;");
        submitBtn.setPrefWidth(120);
        backBtn.setPrefWidth(120);

        HBox buttonBox = new HBox(20, submitBtn, backBtn);
        buttonBox.setAlignment(Pos.CENTER);

        Label feedback = new Label();
        feedback.setWrapText(true);
        feedback.setFont(Font.font("Arial", 14));
        feedback.setTextFill(Color.DARKGREEN);
        feedback.setMaxWidth(400);
        feedback.setAlignment(Pos.CENTER);

        VBox layout = new VBox(30, heading, formGrid, buttonBox, feedback);
        layout.setPadding(new Insets(30));
        layout.setAlignment(Pos.TOP_CENTER);
        layout.setStyle("-fx-background-color: #f0f8ff;");

        submitBtn.setOnAction(e -> {
            try {
                int id = Integer.parseInt(idField.getText());
                String info = this.staffManagement.TeacherSalary(id);
                feedback.setTextFill(Color.DARKGREEN);
                feedback.setText(info);
            } catch (NumberFormatException ex) {
                feedback.setTextFill(Color.RED);
                feedback.setText("Please enter a valid numeric ID.");
            }
        });

        backbutton2(backBtn, stage);
        stage.setScene(new Scene(layout, 700, 700));
    }

    // 2. Calculate Salary of Junior Staff
    public void calculateSalaryOfJuniorStaff(Stage stage) {
        Label heading = new Label("Junior Staff Salary");
        heading.setFont(Font.font("Arial", FontWeight.BOLD, 26));
        heading.setTextFill(Color.DARKBLUE);

        Label idLabel = new Label("Junior Staff ID:");
        idLabel.setFont(Font.font("Arial", 16));
        TextField idField = new TextField();
        idField.setPrefWidth(200);

        GridPane formGrid = new GridPane();
        formGrid.setHgap(20);
        formGrid.setVgap(15);
        formGrid.add(idLabel, 0, 0);
        formGrid.add(idField, 1, 0);
        formGrid.setAlignment(Pos.CENTER);

        Button submitBtn = new Button("Calculate");
        Button backBtn = new Button("Back");
        submitBtn.setStyle("-fx-background-color: royalblue; -fx-text-fill: white;");
        backBtn.setStyle("-fx-background-color: gray; -fx-text-fill: white;");
        submitBtn.setPrefWidth(120);
        backBtn.setPrefWidth(120);

        HBox buttonBox = new HBox(20, submitBtn, backBtn);
        buttonBox.setAlignment(Pos.CENTER);

        Label feedback = new Label();
        feedback.setWrapText(true);
        feedback.setFont(Font.font("Arial", 14));
        feedback.setTextFill(Color.DARKGREEN);
        feedback.setMaxWidth(400);
        feedback.setAlignment(Pos.CENTER);

        VBox layout = new VBox(30, heading, formGrid, buttonBox, feedback);
        layout.setPadding(new Insets(30));
        layout.setAlignment(Pos.TOP_CENTER);
        layout.setStyle("-fx-background-color: #f0f8ff;");

        submitBtn.setOnAction(e -> {
            try {
                int id = Integer.parseInt(idField.getText());
                String info = this.staffManagement.JuniorStaffSalary(id);
                feedback.setTextFill(Color.DARKGREEN);
                feedback.setText(info);
            } catch (NumberFormatException ex) {
                feedback.setTextFill(Color.RED);
                feedback.setText("Please enter a valid numeric ID.");
            }
        });

        backbutton2(backBtn, stage);
        stage.setScene(new Scene(layout, 700, 700));
    }

    // 3. Display Fee of Student
    public void displayFee(Stage stage) {
        Label heading = new Label("Student Fee");
        heading.setFont(Font.font("Arial", FontWeight.BOLD, 26));
        heading.setTextFill(Color.DARKBLUE);

        Label idLabel = new Label("Student ID:");
        idLabel.setFont(Font.font("Arial", 16));
        TextField idField = new TextField();
        idField.setPrefWidth(200);

        GridPane formGrid = new GridPane();
        formGrid.setHgap(20);
        formGrid.setVgap(15);
        formGrid.add(idLabel, 0, 0);
        formGrid.add(idField, 1, 0);
        formGrid.setAlignment(Pos.CENTER);

        Button submitBtn = new Button("Check Fee");
        Button backBtn = new Button("Back");
        submitBtn.setStyle("-fx-background-color: royalblue; -fx-text-fill: white;");
        backBtn.setStyle("-fx-background-color: gray; -fx-text-fill: white;");
        submitBtn.setPrefWidth(120);
        backBtn.setPrefWidth(120);

        HBox buttonBox = new HBox(20, submitBtn, backBtn);
        buttonBox.setAlignment(Pos.CENTER);

        Label feedback = new Label();
        feedback.setWrapText(true);
        feedback.setFont(Font.font("Arial", 14));
        feedback.setTextFill(Color.DARKGREEN);
        feedback.setMaxWidth(400);
        feedback.setAlignment(Pos.CENTER);

        VBox layout = new VBox(30, heading, formGrid, buttonBox, feedback);
        layout.setPadding(new Insets(30));
        layout.setAlignment(Pos.TOP_CENTER);
        layout.setStyle("-fx-background-color: #f0f8ff;");

        submitBtn.setOnAction(e -> {
            try {
                int id = Integer.parseInt(idField.getText());
                String info = this.studentManagement.DisplayingFee(id);
                feedback.setTextFill(Color.DARKGREEN);
                feedback.setText(info);
            } catch (NumberFormatException ex) {
                feedback.setTextFill(Color.RED);
                feedback.setText("Please enter a valid numeric ID.");
            }
        });

        backbutton(backBtn, stage);
        stage.setScene(new Scene(layout, 700, 700));
    }

    // 4. See Scholarship of Student
    public void seeScholarship(Stage stage) {
        Label heading = new Label("Student Scholarship");
        heading.setFont(Font.font("Arial", FontWeight.BOLD, 26));
        heading.setTextFill(Color.DARKBLUE);

        Label idLabel = new Label("Student ID:");
        idLabel.setFont(Font.font("Arial", 16));
        TextField idField = new TextField();
        idField.setPrefWidth(200);

        GridPane formGrid = new GridPane();
        formGrid.setHgap(20);
        formGrid.setVgap(15);
        formGrid.add(idLabel, 0, 0);
        formGrid.add(idField, 1, 0);
        formGrid.setAlignment(Pos.CENTER);

        Button submitBtn = new Button("Check Scholarship");
        Button backBtn = new Button("Back");
        submitBtn.setStyle("-fx-background-color: royalblue; -fx-text-fill: white;");
        backBtn.setStyle("-fx-background-color: gray; -fx-text-fill: white;");
        submitBtn.setPrefWidth(140);
        backBtn.setPrefWidth(120);

        HBox buttonBox = new HBox(20, submitBtn, backBtn);
        buttonBox.setAlignment(Pos.CENTER);

        Label feedback = new Label();
        feedback.setWrapText(true);
        feedback.setFont(Font.font("Arial", 14));
        feedback.setTextFill(Color.DARKGREEN);
        feedback.setMaxWidth(400);
        feedback.setAlignment(Pos.CENTER);

        VBox layout = new VBox(30, heading, formGrid, buttonBox, feedback);
        layout.setPadding(new Insets(30));
        layout.setAlignment(Pos.TOP_CENTER);
        layout.setStyle("-fx-background-color: #f0f8ff;");

        submitBtn.setOnAction(e -> {
            try {
                int id = Integer.parseInt(idField.getText());
                String info = this.studentManagement.ScholarShip(id);
                feedback.setTextFill(Color.DARKGREEN);
                feedback.setText(info);
            } catch (NumberFormatException ex) {
                feedback.setTextFill(Color.RED);
                feedback.setText("Please enter a valid numeric ID.");
            }
        });

        backbutton(backBtn, stage);
        stage.setScene(new Scene(layout, 700, 700));
    }

    // 1. Update Student Information
    public void UpdateStudentInformation(Stage stage) {
        Label heading = new Label("Update Student Information");
        heading.setFont(Font.font("Arial", FontWeight.BOLD, 26));
        heading.setTextFill(Color.DARKBLUE);

        // Input fields
        Label idLabel = new Label("Student ID:");
        TextField idField = new TextField();
        idField.setPrefWidth(200);

        Label contactLabel = new Label("Contact Info:");
        TextField contactField = new TextField();

        Label emailLabel = new Label("Email:");
        TextField emailField = new TextField();

        Label fatherContactLabel = new Label("Father Contact:");
        TextField fatherContactField = new TextField();

        Label motherContactLabel = new Label("Mother Contact:");
        TextField motherContactField = new TextField();

        Label fatherEmailLabel = new Label("Father Email:");
        TextField fatherEmailField = new TextField();

        // Form Grid
        GridPane formGrid = new GridPane();
        formGrid.setVgap(15);
        formGrid.setHgap(20);
        formGrid.add(idLabel, 0, 0); formGrid.add(idField, 1, 0);
        formGrid.add(contactLabel, 0, 1); formGrid.add(contactField, 1, 1);
        formGrid.add(emailLabel, 0, 2); formGrid.add(emailField, 1, 2);
        formGrid.add(fatherContactLabel, 0, 3); formGrid.add(fatherContactField, 1, 3);
        formGrid.add(motherContactLabel, 0, 4); formGrid.add(motherContactField, 1, 4);
        formGrid.add(fatherEmailLabel, 0, 5); formGrid.add(fatherEmailField, 1, 5);
        formGrid.setAlignment(Pos.CENTER);

        // Buttons
        Button submitBtn = new Button("Update");
        Button backBtn = new Button("Back");
        submitBtn.setStyle("-fx-background-color: royalblue; -fx-text-fill: white;");
        backBtn.setStyle("-fx-background-color: gray; -fx-text-fill: white;");
        submitBtn.setPrefWidth(120);
        backBtn.setPrefWidth(120);

        HBox buttonBox = new HBox(20, submitBtn, backBtn);
        buttonBox.setAlignment(Pos.CENTER);

        // Feedback Label
        Label feedback = new Label();
        feedback.setWrapText(true);
        feedback.setFont(Font.font("Arial", 14));
        feedback.setTextFill(Color.DARKGREEN);
        feedback.setMaxWidth(400);
        feedback.setAlignment(Pos.CENTER);

        // Layout
        VBox layout = new VBox(30, heading, formGrid, buttonBox, feedback);
        layout.setPadding(new Insets(30));
        layout.setAlignment(Pos.TOP_CENTER);
        layout.setStyle("-fx-background-color: #f0f8ff;");

        submitBtn.setOnAction(e -> {
            try {
                int id = Integer.parseInt(idField.getText());
                long contact = Long.parseLong(contactField.getText());
                String email = emailField.getText();
                long fContact = Long.parseLong(fatherContactField.getText());
                long mContact = Long.parseLong(motherContactField.getText());
                String fEmail = fatherEmailField.getText();

                File file = new File("data/Student.txt");
                if (!file.exists()) {
                    feedback.setTextFill(Color.RED);
                    feedback.setText("No student records found.");
                    return;
                }

                // Read all lines
                ArrayList<String> lines = new ArrayList<>();
                boolean updated = false;
                try (Scanner sc = new Scanner(file)) {
                    while (sc.hasNextLine()) {
                        String line = sc.nextLine();
                        String[] parts = line.split(",");
                        if (parts.length < 15) {
                            lines.add(line);
                            continue;
                        }
                        int studentId = Integer.parseInt(parts[0]);
                        if (studentId == id) {
                            // Update CSV line
                            parts[5] = String.valueOf(contact);
                            parts[6] = email;
                            parts[10] = String.valueOf(fContact);
                            parts[11] = String.valueOf(mContact);
                            parts[14] = fEmail;
                            line = String.join(",", parts);
                            updated = true;
                        }
                        lines.add(line);
                    }
                }

                if (!updated) {
                    feedback.setTextFill(Color.RED);
                    feedback.setText("Student ID not found.");
                    return;
                }

                // Write back all lines
                try (PrintWriter pw = new PrintWriter(file)) {
                    for (String l : lines) {
                        pw.println(l);
                    }
                }

                feedback.setTextFill(Color.DARKGREEN);
                feedback.setText("Student information updated successfully ✔");
            } catch (NumberFormatException ex) {
                feedback.setTextFill(Color.RED);
                feedback.setText("Please enter valid numeric values where required.");
            } catch (Exception ex) {
                feedback.setTextFill(Color.RED);
                feedback.setText("Error: " + ex.getMessage());
            }
        });

        backbutton(backBtn, stage);
        stage.setScene(new Scene(layout, 700, 700));
    }

    // 2. Update Teacher Information
    public void UpdatingTeacherInfo(Stage stage) {
        Label heading = new Label("Update Teacher Information");
        heading.setFont(Font.font("Arial", FontWeight.BOLD, 26));
        heading.setTextFill(Color.DARKBLUE);

        Label idLabel = new Label("Teacher ID:");
        TextField idField = new TextField();
        idField.setPrefWidth(200);

        Label contactLabel = new Label("Contact Info:");
        TextField contactField = new TextField();

        Label emailLabel = new Label("Email:");
        TextField emailField = new TextField();

        GridPane formGrid = new GridPane();
        formGrid.setVgap(15);
        formGrid.setHgap(20);
        formGrid.add(idLabel, 0, 0); formGrid.add(idField, 1, 0);
        formGrid.add(contactLabel, 0, 1); formGrid.add(contactField, 1, 1);
        formGrid.add(emailLabel, 0, 2); formGrid.add(emailField, 1, 2);
        formGrid.setAlignment(Pos.CENTER);

        Button submitBtn = new Button("Update");
        Button backBtn = new Button("Back");
        submitBtn.setStyle("-fx-background-color: royalblue; -fx-text-fill: white;");
        backBtn.setStyle("-fx-background-color: gray; -fx-text-fill: white;");
        submitBtn.setPrefWidth(120);
        backBtn.setPrefWidth(120);

        HBox buttonBox = new HBox(20, submitBtn, backBtn);
        buttonBox.setAlignment(Pos.CENTER);

        Label feedback = new Label();
        feedback.setWrapText(true);
        feedback.setFont(Font.font("Arial", 14));
        feedback.setTextFill(Color.DARKGREEN);
        feedback.setMaxWidth(400);
        feedback.setAlignment(Pos.CENTER);

        VBox layout = new VBox(30, heading, formGrid, buttonBox, feedback);
        layout.setPadding(new Insets(30));
        layout.setAlignment(Pos.TOP_CENTER);
        layout.setStyle("-fx-background-color: #f0f8ff;");

        submitBtn.setOnAction(e -> {
            try {
                int id = Integer.parseInt(idField.getText());
                long contact = Long.parseLong(contactField.getText());
                String email = emailField.getText();

                File file = new File("data/Teacher.txt");
                ArrayList<String> lines = new ArrayList<>();
                boolean updated = false;

                if (!file.exists()) {
                    feedback.setTextFill(Color.RED);
                    feedback.setText("Teacher file does not exist!");
                    return;
                }

                try (Scanner sc = new Scanner(file)) {
                    while (sc.hasNextLine()) {
                        String line = sc.nextLine();
                        String[] parts = line.split(",");
                        if (parts.length < 12) {
                            lines.add(line);
                            continue;
                        }
                        int tid = Integer.parseInt(parts[0]);
                        if (tid == id) {
                            parts[5] = String.valueOf(contact);
                            parts[6] = email;
                            line = String.join(",", parts);
                            updated = true;
                        }
                        lines.add(line);
                    }
                }

                if (!updated) {
                    feedback.setTextFill(Color.RED);
                    feedback.setText("Teacher ID not found!");
                    return;
                }

                try (PrintWriter pw = new PrintWriter(file)) {
                    for (String l : lines) pw.println(l);
                }

                feedback.setTextFill(Color.DARKGREEN);
                feedback.setText("Teacher information updated successfully ✔");

            } catch (NumberFormatException ex) {
                feedback.setTextFill(Color.RED);
                feedback.setText("Please enter valid numeric values where required.");
            } catch (Exception ex) {
                feedback.setTextFill(Color.RED);
                feedback.setText("Error updating teacher info: " + ex.getMessage());
            }
        });

        backbutton2(backBtn, stage);
        stage.setScene(new Scene(layout, 700, 700));
    }


    // 1. Display Principal Info
    public void displayPrincipleInfo(Stage stage) {
        Principle principle = new Principle("Zaviyar", 28, 1, 12345678, true, 030042560650L, "Zavi@gmail.com", "Bahria Town");

        Label heading = new Label("Principal Information");
        heading.setFont(Font.font("Arial", FontWeight.BOLD, 26));
        heading.setTextFill(Color.DARKBLUE);

        TextArea infoArea = new TextArea(principle.toString());
        infoArea.setEditable(false);
        infoArea.setWrapText(true);
        infoArea.setFont(Font.font("Arial", 16));
        infoArea.setPrefHeight(500);
        infoArea.setPrefWidth(600);

        Button backBtn = new Button("Back to Menu");
        backBtn.setStyle("-fx-background-color: gray; -fx-text-fill: white;");
        backBtn.setPrefWidth(150);

        VBox layout = new VBox(20, heading, infoArea, backBtn);
        layout.setPadding(new Insets(30));
        layout.setAlignment(Pos.TOP_CENTER);
        layout.setStyle("-fx-background-color: #f0f8ff;");

        backbutton2(backBtn, stage);
        stage.setScene(new Scene(layout, 700, 700));
    }

    // 2. Display Student Info
    public void displayStudentInfo(Stage stage) {
        Label heading = new Label("Student Information");
        heading.setFont(Font.font("Arial", FontWeight.BOLD, 26));
        heading.setTextFill(Color.DARKBLUE);

        TextArea infoArea = new TextArea();
        infoArea.setEditable(false);
        infoArea.setWrapText(true);
        infoArea.setFont(Font.font("Arial", 16));
        infoArea.setPrefHeight(500);
        infoArea.setPrefWidth(650);

        File file = new File("data/Student.txt");
        StringBuilder studentInfo = new StringBuilder();
        if (!file.exists()) {
            studentInfo.append("No student records found.\n");
        } else {
            try (Scanner sc = new Scanner(file)) {
                boolean isHeader = true;
                while (sc.hasNextLine()) {
                    String line = sc.nextLine();
                    if (isHeader) { // skip CSV header
                        isHeader = false;
                        continue;
                    }
                    String[] parts = line.split(",");
                    if (parts.length < 15) continue; // ignore incomplete lines
                    studentInfo.append("Name: ").append(parts[1]).append("\n")
                            .append("Age: ").append(parts[2]).append("\n")
                            .append("ID: ").append(parts[0]).append("\n")
                            .append("CNIC: ").append(parts[3]).append("\n")
                            .append("Gender: ").append(parts[4]).append("\n")
                            .append("Contact Info: ").append(parts[5]).append("\n")
                            .append("Email: ").append(parts[6]).append("\n")
                            .append("Address: ").append(parts[7]).append("\n")
                            .append("Father Name: ").append(parts[8]).append("\n")
                            .append("Mother Name: ").append(parts[9]).append("\n")
                            .append("Father Contact: ").append(parts[10]).append("\n")
                            .append("Mother Contact: ").append(parts[11]).append("\n")
                            .append("Father Business: ").append(parts[12]).append("\n")
                            .append("Father Income: ").append(parts[13]).append("\n")
                            .append("Father Email: ").append(parts[14]).append("\n")
                            .append("------------------------------------------------\n");
                }
            } catch (IOException e) {
                studentInfo.append("Error reading student records.");
            }
        }

        infoArea.setText(studentInfo.toString());

        Button backBtn = new Button("Back to Menu");
        backBtn.setStyle("-fx-background-color: gray; -fx-text-fill: white;");
        backBtn.setPrefWidth(150);

        VBox layout = new VBox(20, heading, infoArea, backBtn);
        layout.setPadding(new Insets(30));
        layout.setAlignment(Pos.TOP_CENTER);
        layout.setStyle("-fx-background-color: #f0f8ff;");

        backbutton(backBtn, stage);
        stage.setScene(new Scene(layout, 700, 700));
    }


    // 3. Display Teacher Info
    public void displayTeacherInfo(Stage stage) {
        Label heading = new Label("Teacher Information");
        heading.setFont(Font.font("Arial", FontWeight.BOLD, 26));
        heading.setTextFill(Color.DARKBLUE);

        TextArea infoArea = new TextArea();
        infoArea.setEditable(false);
        infoArea.setWrapText(true);
        infoArea.setFont(Font.font("Arial", 16));
        infoArea.setPrefHeight(500);
        infoArea.setPrefWidth(600);

        File file = new File("data/Teacher.txt");
        StringBuilder teacherInfo = new StringBuilder();
        if (!file.exists()) {
            teacherInfo.append("No teacher records found.\n");
        } else {
            try (Scanner sc = new Scanner(file)) {
                while (sc.hasNextLine()) {
                    String line = sc.nextLine();
                    String[] parts = line.split(",");
                    if (parts.length < 12) continue; // Skip invalid lines

                    teacherInfo.append("ID: ").append(parts[0])
                            .append("\nName: ").append(parts[1])
                            .append("\nAge: ").append(parts[2])
                            .append("\nCNIC: ").append(parts[3])
                            .append("\nGender: ").append(parts[4])
                            .append("\nContact: ").append(parts[5])
                            .append("\nEmail: ").append(parts[6])
                            .append("\nAddress: ").append(parts[7])
                            .append("\nQualification: ").append(parts[8])
                            .append("\nExperience (Years): ").append(parts[9])
                            .append("\nCertificate: ").append(parts[10])
                            .append("\nMarital Status: ").append(parts[11])
                            .append("\n---------------------------\n");
                }
            } catch (IOException e) {
                teacherInfo.append("Error reading teacher records.");
            }
        }

        infoArea.setText(teacherInfo.toString());

        Button backBtn = new Button("Back to Menu");
        backBtn.setStyle("-fx-background-color: gray; -fx-text-fill: white;");
        backBtn.setPrefWidth(150);

        VBox layout = new VBox(20, heading, infoArea, backBtn);
        layout.setPadding(new Insets(30));
        layout.setAlignment(Pos.TOP_CENTER);
        layout.setStyle("-fx-background-color: #f0f8ff;");

        backbutton2(backBtn, stage);
        stage.setScene(new Scene(layout, 700, 700));
    }


    public void displayJuniorStaffInfo(Stage stage) {
        Label heading = new Label("Junior Staff Information");
        heading.setFont(Font.font("Arial", FontWeight.BOLD, 26));
        heading.setTextFill(Color.DARKBLUE);

        TextArea infoArea = new TextArea();
        infoArea.setEditable(false);
        infoArea.setWrapText(true);
        infoArea.setFont(Font.font("Arial", 16));
        infoArea.setPrefHeight(500);
        infoArea.setPrefWidth(600);

        File file = new File("data/Staff.txt");
        StringBuilder staffInfo = new StringBuilder();
        if (!file.exists()) {
            staffInfo.append("No junior staff records found.\n");
        } else {
            try (Scanner sc = new Scanner(file)) {
                while (sc.hasNextLine()) {
                    String line = sc.nextLine();
                    String[] parts = line.split(",");
                    if (parts.length < 9) continue; // Skip invalid lines

                    staffInfo.append("ID: ").append(parts[0])
                            .append("\nName: ").append(parts[1])
                            .append("\nAge: ").append(parts[2])
                            .append("\nCNIC: ").append(parts[3])
                            .append("\nGender: ").append(parts[4])
                            .append("\nContact: ").append(parts[5])
                            .append("\nEmail: ").append(parts[6])
                            .append("\nAddress: ").append(parts[7])
                            .append("\nExperience (Years): ").append(parts[8])
                            .append("\n---------------------------\n");
                }
            } catch (IOException e) {
                staffInfo.append("Error reading junior staff records.");
            }
        }

        infoArea.setText(staffInfo.toString());

        Button backBtn = new Button("Back to Menu");
        backBtn.setStyle("-fx-background-color: gray; -fx-text-fill: white;");
        backBtn.setPrefWidth(150);

        VBox layout = new VBox(20, heading, infoArea, backBtn);
        layout.setPadding(new Insets(30));
        layout.setAlignment(Pos.TOP_CENTER);
        layout.setStyle("-fx-background-color: #f0f8ff;");

        backbutton2(backBtn, stage);
        stage.setScene(new Scene(layout, 700, 700));
    }

    public void assignCourses(Stage stage) {
        // Title
        Label title = new Label("Assign Courses to Student");
        title.setFont(Font.font("Arial", FontWeight.BOLD, 26));
        title.setTextFill(Color.DARKBLUE);

        // Student ID
        Label idLabel = new Label("Student ID:");
        TextField idField = new TextField();
        idField.setPromptText("Enter Student ID");

        // Class Name
        Label classLabel = new Label("Class (1-8):");
        TextField classField = new TextField();
        classField.setPromptText("Enter class number");

        // Buttons
        Button submitBtn = new Button("Submit");
        submitBtn.setStyle("-fx-background-color: royalblue; -fx-text-fill: white;");
        submitBtn.setPrefWidth(120);

        Button backBtn = new Button("Back to Menu");
        backBtn.setStyle("-fx-background-color: gray; -fx-text-fill: white;");
        backBtn.setPrefWidth(120);

        // Feedback Label
        Label feedback = new Label();
        feedback.setTextFill(Color.FIREBRICK);
        feedback.setWrapText(true);
        feedback.setFont(Font.font("Arial", 14));

        // Layout
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.TOP_CENTER);
        grid.setPadding(new Insets(30));
        grid.setHgap(20);
        grid.setVgap(20);

        grid.add(title, 0, 0, 2, 1);
        grid.add(idLabel, 0, 1); grid.add(idField, 1, 1);
        grid.add(classLabel, 0, 2); grid.add(classField, 1, 2);
        grid.add(submitBtn, 0, 3); grid.add(backBtn, 1, 3);
        grid.add(feedback, 0, 4, 2, 1);

        Scene scene = new Scene(grid, 700, 700);
        setBackgroundColor(grid);
        stage.setScene(scene);

        submitBtn.setOnAction(e -> {
            try {
                int id = Integer.parseInt(idField.getText());
                int classNum = Integer.parseInt(classField.getText());
                if (classNum < 1 || classNum > 8) throw new InputMismatchException("Class must be between 1 and 8");

                Class studentClass = new Class(classNum);
                String info = this.studentManagement.assignCoursesToStudent(id, studentClass);
                feedback.setTextFill(Color.DARKGREEN);
                feedback.setText(info);

            } catch (NumberFormatException ex) {
                feedback.setText("Please enter valid numeric values for ID and Class.");
                feedback.setTextFill(Color.FIREBRICK);
            } catch (InputMismatchException ex) {
                feedback.setText(ex.getMessage());
                feedback.setTextFill(Color.FIREBRICK);
            }
        });

        backbutton(backBtn, stage);
    }

    public void viewAssignedCourses(Stage stage) {
        Label title = new Label("View Assigned Courses");
        title.setFont(Font.font("Arial", FontWeight.BOLD, 26));
        title.setTextFill(Color.DARKBLUE);

        Label idLabel = new Label("Student ID:");
        TextField idField = new TextField();
        idField.setPromptText("Enter Student ID");

        Button submitBtn = new Button("Submit");
        submitBtn.setStyle("-fx-background-color: royalblue; -fx-text-fill: white;");
        submitBtn.setPrefWidth(120);

        Button backBtn = new Button("Back to Menu");
        backBtn.setStyle("-fx-background-color: gray; -fx-text-fill: white;");
        backBtn.setPrefWidth(120);

        TextArea resultArea = new TextArea();
        resultArea.setEditable(false);
        resultArea.setWrapText(true);
        resultArea.setPrefHeight(200);
        resultArea.setFont(Font.font("Arial", 14));

        GridPane grid = new GridPane();
        grid.setAlignment(Pos.TOP_CENTER);
        grid.setPadding(new Insets(30));
        grid.setHgap(20);
        grid.setVgap(20);

        grid.add(title, 0, 0, 2, 1);
        grid.add(idLabel, 0, 1); grid.add(idField, 1, 1);
        grid.add(submitBtn, 0, 2); grid.add(backBtn, 1, 2);
        grid.add(resultArea, 0, 3, 2, 1);

        Scene scene = new Scene(grid, 700, 700);
        setBackgroundColor(grid);
        stage.setScene(scene);

        submitBtn.setOnAction(e -> {
            try {
                int id = Integer.parseInt(idField.getText());
                String info = this.studentManagement.viewAssignedCourses(id);
                resultArea.setText(info);
            } catch (NumberFormatException ex) {
                resultArea.setText("Please enter a valid numeric Student ID.");
            }
        });

        backbutton(backBtn, stage);
    }

    public void seeResult(Stage stage) {
        Label title = new Label("Students Result");
        title.setFont(Font.font("Arial", FontWeight.BOLD, 26));
        title.setTextFill(Color.DARKBLUE);

        Label idLabel = new Label("Enter Student ID:");
        TextField idField = new TextField();
        idField.setPromptText("Student ID");

        Label termLabel = new Label("Select Term:");
        RadioButton midTerm = new RadioButton("Mid-Term");
        RadioButton finalTerm = new RadioButton("Final-Term");
        ToggleGroup termGroup = new ToggleGroup();
        midTerm.setToggleGroup(termGroup);
        finalTerm.setToggleGroup(termGroup);
        HBox termBox = new HBox(15, midTerm, finalTerm);
        termBox.setAlignment(Pos.CENTER_LEFT);

        GridPane marksGrid = new GridPane();
        marksGrid.setHgap(15);
        marksGrid.setVgap(10);

        Button submitBtn = new Button("Submit");
        submitBtn.setStyle("-fx-background-color: royalblue; -fx-text-fill: white;");
        Button backBtn = new Button("Back to Menu");
        backBtn.setStyle("-fx-background-color: gray; -fx-text-fill: white;");
        backBtn.setPrefWidth(120);
        HBox buttonBox = new HBox(20, submitBtn, backBtn);
        buttonBox.setAlignment(Pos.CENTER);

        TextArea resultArea = new TextArea();
        resultArea.setEditable(false);
        resultArea.setWrapText(true);
        resultArea.setPrefHeight(150);
        resultArea.setFont(Font.font("Arial", 14));

        VBox mainLayout = new VBox(15, title, idLabel, idField, termLabel, termBox, marksGrid, buttonBox, resultArea);
        mainLayout.setPadding(new Insets(30));
        mainLayout.setAlignment(Pos.TOP_CENTER);
        mainLayout.setStyle("-fx-background-color: #f0f8ff;");

        Scene scene = new Scene(mainLayout, 700, 700);
        setBackgroundColor(mainLayout);
        stage.setScene(scene);

        submitBtn.setOnAction(e -> {
            try {
                int id = Integer.parseInt(idField.getText());
                Student student = this.studentManagement.getStudentById(id);

                if (student == null) {
                    resultArea.setText("Student not found.");
                    return;
                }

                // Get class number based on number of courses assigned
                int classNumber;
                int courseCount = student.getCourses().size();
                if (courseCount == 5) classNumber = 1;   // Classes 1-4
                else classNumber = 5;                     // Classes 5-8

                Class studentClass = new Class(classNumber);
                ArrayList<Course1> courses = Course1.initializeCourses(studentClass);
                Grade grade = new Grade(studentClass);

                // Generate input fields for each course
                marksGrid.getChildren().clear();
                TextField[] marksFields = new TextField[courses.size()];
                for (int i = 0; i < courses.size(); i++) {
                    marksGrid.add(new Label(courses.get(i).getTitle() + ":"), 0, i);
                    marksFields[i] = new TextField();
                    marksFields[i].setPromptText("Enter marks for " + courses.get(i).getTitle());
                    marksGrid.add(marksFields[i], 1, i);
                }

                submitBtn.setOnAction(ev -> {
                    try {
                        for (int i = 0; i < courses.size(); i++) {
                            int mark = Integer.parseInt(marksFields[i].getText());
                            grade.setMark(i, mark);
                        }

                        if (midTerm.isSelected()) {
                            if (grade.hasInvalidMidTerm()) {
                                resultArea.setText("Error: One or more Mid-Term marks exceed 50.");
                            } else {
                                grade.setMidtermMarks();
                                resultArea.setText("Percentage: " + grade.calculateMidtermPercentage() +
                                        "\nGrade: " + grade.getMidtermGrade());
                            }
                        } else if (finalTerm.isSelected()) {
                            if (grade.hasInvalidFinalTerm()) {
                                resultArea.setText("Error: One or more Final-Term marks exceed 100.");
                            } else {
                                grade.setFinalTermMarks();
                                resultArea.setText("Percentage: " + grade.calculateFinalTermPercentage() +
                                        "\nGrade: " + grade.getFinalTermGrade());
                            }
                        } else {
                            resultArea.setText("Please select a term.");
                        }

                    } catch (NumberFormatException ex) {
                        resultArea.setText("Please enter valid numeric marks.");
                    }
                });

            } catch (NumberFormatException ex) {
                resultArea.setText("Please enter a valid numeric Student ID.");
            }
        });

        backbutton(backBtn, stage);
    }



    // -------------------------- Add Book --------------------------
    private void addBook(Stage stage) {
        Label heading = new Label("Add a New Book");
        heading.setFont(Font.font("Arial", FontWeight.BOLD, 26));
        heading.setTextFill(Color.DARKBLUE);

        Label titleLabel = new Label("Title:"); titleLabel.setFont(Font.font(16));
        Label authorLabel = new Label("Author:"); authorLabel.setFont(Font.font(16));
        Label isbnLabel = new Label("ISBN:"); isbnLabel.setFont(Font.font(16));

        TextField titleField = new TextField(); titleField.setPrefWidth(250);
        TextField authorField = new TextField(); authorField.setPrefWidth(250);
        TextField isbnField = new TextField(); isbnField.setPrefWidth(250);

        GridPane formGrid = new GridPane();
        formGrid.setHgap(20); formGrid.setVgap(15);
        formGrid.add(titleLabel, 0, 0); formGrid.add(titleField, 1, 0);
        formGrid.add(authorLabel, 0, 1); formGrid.add(authorField, 1, 1);
        formGrid.add(isbnLabel, 0, 2); formGrid.add(isbnField, 1, 2);
        formGrid.setAlignment(Pos.CENTER);

        Button addBtn = new Button("Add Book");
        addBtn.setStyle("-fx-background-color: darkgreen; -fx-text-fill: white;");
        Button backBtn = new Button("Back");
        backBtn.setStyle("-fx-background-color: gray; -fx-text-fill: white;");
        addBtn.setPrefWidth(120); backBtn.setPrefWidth(120);

        HBox buttonBox = new HBox(20, addBtn, backBtn);
        buttonBox.setAlignment(Pos.CENTER);

        Label feedback = new Label(); feedback.setWrapText(true);
        feedback.setFont(Font.font(14)); feedback.setTextFill(Color.DARKGREEN);
        feedback.setMaxWidth(400); feedback.setAlignment(Pos.CENTER);

        VBox layout = new VBox(30, heading, formGrid, buttonBox, feedback);
        layout.setPadding(new Insets(30));
        layout.setAlignment(Pos.TOP_CENTER);
        layout.setStyle("-fx-background-color: #f0f8ff;");

        addBtn.setOnAction(e -> {
            try {
                String title = titleField.getText();
                String author = authorField.getText();
                int isbn = Integer.parseInt(isbnField.getText());

                Book book = new Book(title, author, isbn, true);
                feedback.setTextFill(Color.DARKGREEN);
                feedback.setText(libraryManagement.addBook(book));
            } catch (NumberFormatException ex) {
                feedback.setTextFill(Color.RED);
                feedback.setText("ISBN must be a numeric value!");
            }
        });

        backbutton3(backBtn, stage);
        stage.setScene(new Scene(layout, 700, 700));
    }

    // -------------------------- Assign Book --------------------------
    private void assignBook(Stage stage) {
        Label heading = new Label("Assign Book to Student");
        heading.setFont(Font.font("Arial", FontWeight.BOLD, 26));
        heading.setTextFill(Color.DARKBLUE);

        Label studentLabel = new Label("Student ID:"); studentLabel.setFont(Font.font(16));
        Label isbnLabel = new Label("Book ISBN:"); isbnLabel.setFont(Font.font(16));

        TextField studentField = new TextField(); studentField.setPrefWidth(250);
        TextField isbnField = new TextField(); isbnField.setPrefWidth(250);

        GridPane formGrid = new GridPane();
        formGrid.setHgap(20); formGrid.setVgap(15);
        formGrid.add(studentLabel, 0, 0); formGrid.add(studentField, 1, 0);
        formGrid.add(isbnLabel, 0, 1); formGrid.add(isbnField, 1, 1);
        formGrid.setAlignment(Pos.CENTER);

        Button assignBtn = new Button("Assign");
        assignBtn.setStyle("-fx-background-color: royalblue; -fx-text-fill: white;");
        Button backBtn = new Button("Back");
        backBtn.setStyle("-fx-background-color: gray; -fx-text-fill: white;");
        assignBtn.setPrefWidth(120); backBtn.setPrefWidth(120);

        HBox buttonBox = new HBox(20, assignBtn, backBtn);
        buttonBox.setAlignment(Pos.CENTER);

        Label feedback = new Label(); feedback.setWrapText(true);
        feedback.setFont(Font.font(14)); feedback.setTextFill(Color.DARKGREEN);
        feedback.setMaxWidth(400); feedback.setAlignment(Pos.CENTER);

        VBox layout = new VBox(30, heading, formGrid, buttonBox, feedback);
        layout.setPadding(new Insets(30));
        layout.setAlignment(Pos.TOP_CENTER);
        layout.setStyle("-fx-background-color: #f0f8ff;");

        assignBtn.setOnAction(e -> {
            try {
                int studentId = Integer.parseInt(studentField.getText());
                int isbn = Integer.parseInt(isbnField.getText());

                boolean success = libraryManagement.checkoutBook(isbn, studentId);
                feedback.setTextFill(success ? Color.DARKGREEN : Color.RED);
                feedback.setText(success ? "Book assigned successfully." : "Failed. Check Student ID or Book ISBN.");
            } catch (NumberFormatException ex) {
                feedback.setTextFill(Color.RED);
                feedback.setText("Student ID and ISBN must be numeric!");
            }
        });

        backbutton3(backBtn, stage);
        stage.setScene(new Scene(layout, 700, 700));
    }

    // -------------------------- Return Book --------------------------
    private void returnBook(Stage stage) {
        Label heading = new Label("Return a Book");
        heading.setFont(Font.font("Arial", FontWeight.BOLD, 26));
        heading.setTextFill(Color.DARKBLUE);

        Label isbnLabel = new Label("Book ISBN:"); isbnLabel.setFont(Font.font(16));
        TextField isbnField = new TextField(); isbnField.setPrefWidth(250);

        GridPane formGrid = new GridPane();
        formGrid.setHgap(20); formGrid.setVgap(15);
        formGrid.add(isbnLabel, 0, 0); formGrid.add(isbnField, 1, 0);
        formGrid.setAlignment(Pos.CENTER);

        Button returnBtn = new Button("Return");
        returnBtn.setStyle("-fx-background-color: darkred; -fx-text-fill: white;");
        Button backBtn = new Button("Back");
        backBtn.setStyle("-fx-background-color: gray; -fx-text-fill: white;");
        returnBtn.setPrefWidth(120); backBtn.setPrefWidth(120);

        HBox buttonBox = new HBox(20, returnBtn, backBtn);
        buttonBox.setAlignment(Pos.CENTER);

        Label feedback = new Label(); feedback.setWrapText(true);
        feedback.setFont(Font.font(14)); feedback.setTextFill(Color.DARKGREEN);
        feedback.setMaxWidth(400); feedback.setAlignment(Pos.CENTER);

        VBox layout = new VBox(30, heading, formGrid, buttonBox, feedback);
        layout.setPadding(new Insets(30));
        layout.setAlignment(Pos.TOP_CENTER);
        layout.setStyle("-fx-background-color: #f0f8ff;");

        returnBtn.setOnAction(e -> {
            try {
                int isbn = Integer.parseInt(isbnField.getText());
                boolean success = libraryManagement.returnBook(isbn);
                feedback.setTextFill(success ? Color.DARKGREEN : Color.RED);
                feedback.setText(success ? "Book returned successfully." : "Failed. Check the Book ISBN.");
            } catch (NumberFormatException ex) {
                feedback.setTextFill(Color.RED);
                feedback.setText("ISBN must be numeric!");
            }
        });

        backbutton3(backBtn, stage);
        stage.setScene(new Scene(layout, 700, 700));
    }

    // -------------------------- Display Available Books --------------------------
    private void displayAvailableBooks(Stage stage) {
        Label heading = new Label("Available Books");
        heading.setFont(Font.font("Arial", FontWeight.BOLD, 26));
        heading.setTextFill(Color.DARKBLUE);

        TextArea booksArea = new TextArea(libraryManagement.displayAvailableBooks());
        booksArea.setWrapText(true); booksArea.setEditable(false);

        Button backBtn = new Button("Back");
        backBtn.setStyle("-fx-background-color: gray; -fx-text-fill: white;"); backBtn.setPrefWidth(120);

        VBox layout = new VBox(30, heading, booksArea, backBtn);
        layout.setPadding(new Insets(30));
        layout.setAlignment(Pos.TOP_CENTER);
        layout.setStyle("-fx-background-color: #f0f8ff;");

        backbutton3(backBtn, stage);
        stage.setScene(new Scene(layout, 700, 700));
    }

    // -------------------------- Display Checked Out Books --------------------------
    private void displayCheckedOutBooks(Stage stage) {
        Label heading = new Label("Checked Out Books");
        heading.setFont(Font.font("Arial", FontWeight.BOLD, 26));
        heading.setTextFill(Color.DARKBLUE);

        TextArea booksArea = new TextArea(libraryManagement.displayCheckedOutBooks());
        booksArea.setWrapText(true); booksArea.setEditable(false);

        Button backBtn = new Button("Back");
        backBtn.setStyle("-fx-background-color: gray; -fx-text-fill: white;"); backBtn.setPrefWidth(120);

        VBox layout = new VBox(30, heading, booksArea, backBtn);
        layout.setPadding(new Insets(30));
        layout.setAlignment(Pos.TOP_CENTER);
        layout.setStyle("-fx-background-color: #f0f8ff;");

        backbutton3(backBtn, stage);
        stage.setScene(new Scene(layout, 700, 700));
    }


    public void setBackgroundColor(Pane pane) {
        // Light gray background with padding and subtle corners
        BackgroundFill backgroundFill = new BackgroundFill(Color.LIGHTGRAY, new CornerRadii(10), Insets.EMPTY);
        Background background = new Background(backgroundFill);
        pane.setBackground(background);
    }

    public void backbutton(Button tb1, Stage stage) {
        tb1.setOnAction(backEvent -> stage.setScene(studentscene));
    }
    public void backbutton2(Button tb1, Stage stage) {
        tb1.setOnAction(backEvent -> stage.setScene(staffscene));
    }
    public void backbutton3(Button tb1, Stage stage) {
        tb1.setOnAction(backEvent -> stage.setScene(libraryscene));
    }

}