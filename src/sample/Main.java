/*
 ============================================================================
 Name: Nikita Shymberg
 Date: 2016/06/20
 File name: Seating.jar
 Description: This program sits families randomly designed for the FAT
 Copyright: It's mine, please don't steal
 ============================================================================
 */

package sample;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

import java.io.*;

public class Main extends Application {
    private final int NUMFAM = countFam(); //total number of families
    private Family[] unseated = new Family[NUMFAM];
    private Family[] readToHere = new Family[NUMFAM];
    private Family[] seated = new Family[NUMFAM];
    private final int NUMROWS = 28;
    private final int NUMCOLS = 55;
    private Seat[][] fat = new Seat[NUMROWS][NUMCOLS];
    private GridPane gridPane = new GridPane();
    private int seatCount;
    private int tryCount = 0;
    private Stage stage;
    private TextField[] rowNames =  new TextField[NUMROWS];
    private String out;
    private HBox top = new HBox();

    /**
     * Counts the total number of families
     * @return the number of families in Seat.txt
     */
    private static int countFam(){
        String fileName = "Seat.txt";

        int out = 0;

        try {
            FileReader fileReader = new FileReader(fileName);

            BufferedReader bufferedReader = new BufferedReader(fileReader);

            while (bufferedReader.readLine() != null) {
                out++;
            }

            bufferedReader.close();
        } catch (FileNotFoundException ex) {
            System.out.println("Unable to open file '" + fileName + "'");
        } catch (IOException ex) {
            System.out.println("Error reading file '" + fileName + "'");
        }

        return out;
    }

    /**
     * Creates the window and sets up default FAT layout
     * @param primaryStage the stage
     */
    public void start(Stage primaryStage) {
        BorderPane root;
        Scene scene;

        VBox rows = new VBox();
        top.setStyle("-fx-background-color: #773333;");

        /* The default row names */
        for(int i = 0; i < NUMROWS; i++){
            rowNames[i] = new TextField("0");
            rowNames[i].setPrefWidth(39);
            rowNames[i].setFont(Font.font("Verdana", FontWeight.NORMAL, 12.16));
            if(i == 0){
                rowNames[i].setText("AA");
            }
            if(i == 1){
                rowNames[i].setText("A");
            }
            if(i == 2){
                rowNames[i].setText("B");
            }
            if(i == 3){
                rowNames[i].setText("C");
            }
            if(i == 4){
                rowNames[i].setText("D");
            }
            if(i == 5){
                rowNames[i].setText("E");
            }
            if(i == 6){
                rowNames[i].setText("F");
            }
            if(i == 7){
                rowNames[i].setText("G");
            }
            if(i == 8){
                rowNames[i].setText("H");
            }
            if(i == 9){
                rowNames[i].setText("J");
            }
            if(i == 10){
                rowNames[i].setText("K");
            }
            if(i == 11){
                rowNames[i].setText("L");
            }
            if(i == 12){
                rowNames[i].setText("M");
            }
            if(i == 13){
                rowNames[i].setText("N");
            }
            if(i == 15){
                rowNames[i].setText("PP");
            }
            if(i == 16){
                rowNames[i].setText("P");
            }
            if(i == 17){
                rowNames[i].setText("R");
            }
            if(i == 18){
                rowNames[i].setText("S");
            }
            if(i == 19){
                rowNames[i].setText("T");
            }
            if(i == 20){
                rowNames[i].setText("U");
            }
            if(i == 21){
                rowNames[i].setText("V");
            }
            if(i == 22){
                rowNames[i].setText("W");
            }
            if(i == 23){
                rowNames[i].setText("X");
            }
            if(i == 24){
                rowNames[i].setText("XX");
            }
            rows.getChildren().add(rowNames[i]);
        }

        /* The default existing red seats in FAT */
        for(int r = 0; r < NUMROWS; r++){
            for(int c = 0; c < NUMCOLS; c++){
                Rectangle rec = new Rectangle(24, 24, Color.rgb(204,0,0));
                fat[r][c] = new Seat();
                rec.setStroke(Color.BLACK);
                changeRecColor(rec, r, c);
                StackPane s = new StackPane();
                s.getChildren().add(rec);
                gridPane.add(s, c, r);
                if(
                        r == 14 ||
                                c == 0 ||
                                c == 1 && r != 23 ||
                                c == 2 && r != 23 ||
                                c == 3 && (r != 22 && r != 23) ||
                                c == 4 && (r != 20 && r != 21 && r != 22 && r != 23) ||
                                c == 5 && (r != 12 && r != 13 && r != 18 && r != 19 && r != 20 && r != 21 && r != 22 && r != 23) ||
                                c == 6 && (r != 10 && r != 11 && r != 12 && r != 13 && r != 16 && r != 17 && r != 18 && r != 19 && r != 20 && r != 21 && r != 22 && r != 23) ||
                                c == 7 && (r != 8 && r != 9 && r != 10 && r != 11 && r != 12 && r != 13 && r != 16 && r != 17 && r != 18 && r != 19 && r != 20 && r != 21 && r != 22 && r != 23) ||
                                c == 8 && (r == 0 || r == 1 || r == 2 || r == 3 || r == 4 || r == 5 || r == 24) ||
                                c == 9 && (r == 0 || r == 1 || r == 2 || r == 3 || r == 24) ||
                                c == 10 && (r == 1 || r == 24) ||
                                (c == 11 || c == 12 || c == 13) && r == 24 ||
                                (c == 14 || c == 15 || c == 16) && r == 24 ||
                                c == 17 ||
                                c == 18 ||
                                c == 19 && (r != 0) ||
                                c == 20 && (r == 15 || r == 23 || r == 24) ||
                                c == 21 && (r == 23 || r == 24) ||
                                (c == 22 || c == 23) && (r == 23 || r == 24) ||
                                (c == 24 || c == 25 || c == 26 || c == 27 || c == 28 || c == 29 || c == 30) && (r == 23 || r == 24) ||
                                (c == 31 || c == 32 || c == 33 || c == 34 || c == 35) && (r == 23 || r == 24) ||
                                c == 36 && (r != 0 && r != 16 && r != 17 && r != 18 && r != 19 && r != 20 && r != 21 && r != 22) ||
                                c == 37 && (r != 23) ||
                                c == 38 && (r != 23) ||
                                c == 39 && (r == 15 || r == 16 || r == 17 || r == 18 || r == 19 || r == 20 || r == 21 || r == 22 || r == 24) ||
                                c == 40 && r == 24 ||
                                (c == 41 || c == 42 || c == 43 || c == 44) && r == 24 ||
                                c == 45 && (r == 1 || r == 24) ||
                                c == 46 && (r < 14 && (r == 0 || r == 1 || r == 2 || r == 3) || r == 24) ||
                                c == 47 && (r < 14 && (r == 0 || r == 1 || r == 2 || r == 3 || r == 4 || r == 5) || r == 24) ||
                                c == 48 && (r != 8 && r != 9 && r != 10 && r != 11 && r != 12 && r != 13 && r != 15 && r != 16 && r != 17 && r != 18 && r != 19 && r != 20 && r != 21 && r != 22 && r != 23) ||
                                c == 49 && (r != 10 && r != 11 && r != 12 && r != 13 && r != 16 && r != 17 && r != 18 && r != 19 && r != 20 && r != 21 && r != 22 && r != 23) ||
                                c == 50 && (r != 12 && r != 13 && r != 16 && r != 17 && r != 18 && r != 19 && r != 20 && r != 21 && r != 22 && r != 23) ||
                                c == 51 && (r < 14 || r > 14 && (r == 15 || r == 16 || r == 17 || r == 24)) ||
                                c == 52 && (r != 20 && r != 21 && r != 22 && r != 23) ||
                                c == 53 && (r != 22 && r != 23) ||
                                c == 54 ||
                                r > 24
                        ){
                    rec.setFill(Color.WHITE);
                    fat[r][c] = null;
                }
            }
        }

        stage = primaryStage;
        root = new BorderPane();
        scene = new Scene(root, 1450, 770);
        root.setPadding(new Insets(15));
        stage.setScene(scene);
        stage.setTitle("Seating");
        root.setCenter(gridPane);
        root.setTop(top);
        root.setLeft(rows);

        Button sit = new Button("Sit");
        sit.setFont(Font.font("Verdana", FontWeight.BOLD, 22));
        sit.setOnMouseClicked(event -> {
            Seating();
        });
        top.getChildren().add(sit);
        top.setAlignment(Pos.CENTER);

        stage.setResizable(false);
        stage.show();
    }

    /**
     * Private helper method to change the rectangle colour from white to red
     * and vice versa
     * @param rec the rectangel that needs to change colour
     * @param r the row of the rectangle in FAT
     * @param c the column of the rectangle in FAT
     */
    private void changeRecColor(Rectangle rec, int r, int c){
        rec.setOnMouseClicked(event -> {
            if(rec.getFill().equals(Color.WHITE)) {
                rec.setFill(Color.rgb(125,0,0));
                fat[r][c] = new Seat();
                seatCount++;
            } else {
                rec.setFill(Color.WHITE);
                fat[r][c] = null;
                seatCount--;
            }
        });
    }

    /**
     * The method that sits all the families down in the FAT and updates the window
     */
    private void Seating() {

        /* Read and set the number of people un each family */
        String fileName = "Seat.txt";

        String line;
        String famName;
        int famNum;

        try {
            FileReader fileReader = new FileReader(fileName);

            BufferedReader bufferedReader = new BufferedReader(fileReader);

            for (int i = 0; i < NUMFAM; i++) {
                line = bufferedReader.readLine();
                if (line != null) {
                    String[] pieces = line.split(";");
                    famName = pieces[0];
                    famNum = Integer.parseInt(pieces[1]);
                    readToHere[i] = new Family(i + 1, famNum, famName);
                }
            }

            bufferedReader.close();
        } catch (FileNotFoundException ex) {
            System.out.println("Unable to open file '" + fileName + "'");
        } catch (IOException ex) {
            System.out.println("Error reading file '" + fileName + "'");
        }

        for (int i = 0; i < NUMFAM; i++) {
            for (int j = 0; j < NUMFAM - 1; j++) {
                if (readToHere[j].getNum() > readToHere[j + 1].getNum()) {
                    Family temp = readToHere[j];
                    readToHere[j] = readToHere[j + 1];
                    readToHere[j + 1] = temp;
                }
            }
        }

        do {
            tryCount++;
            /* Set appropriate seats to null */
            for (int r = 0; r < NUMROWS; r++) {
                for (int c = 0; c < NUMCOLS; c++) {
                    if (fat[r][c] != null) {
                        fat[r][c].setFamily(null);
                    }
                }
            }


			/* Name the rows and columns */
            for (int r = 0; r < NUMROWS; r++) {
                for (int c = 0; c < NUMCOLS; c++) {
                    if (fat[r][c] != null) {

                        /* Naming the colums, only to be implemented like this in the case of the FAT where
                         * the ground floor has extra chairs on the left */
                        if(r < 14 && r != 0){
                            fat[r][c].setCol(c);
                        } else {
                            fat[r][c].setCol(c+1);
                        }

                        fat[r][c].setRow(rowNames[r].getText());
                    }
                }
            }

			/* Read and set the number of people un each family */
            for (int i = 0; i < NUMFAM; i++) {
                unseated[i] = readToHere[i];
            }

			/* Seating V2.1 */

            int famCount = 0;
            int satCount = 0;

            System.out.println();
            System.out.println();

            for (int r = 0; r < NUMROWS; r++) {
                for (int c = 0; c < NUMCOLS; c++) {
                    int cur = 0;
                    boolean valid = false;
                    boolean possible = true;

                    if (fat[r][c] != null && unseated[0] != null) {
                        for (int i = 0; i < unseated[0].getNum(); i++) {
                            if (c + i >= NUMCOLS || fat[r][c + i] == null) {
                                possible = false;
                            }
                        }

                        if (possible) {
                            while (!valid) {
                                valid = true;
                                cur = (int) (Math.random() * (NUMFAM - famCount));

                                for (int i = 0; i < unseated[cur].getNum(); i++) {
                                    if (c + i >= NUMCOLS || fat[r][c + i] == null) {
                                        valid = false;
                                    }
                                }

                            }

                            satCount += unseated[cur].getNum();

							/* Sit the family down */
                            for (int j = 0; j < unseated[cur].getNum(); j++) {
                                unseated[cur].sit(fat[r][c + j]);
                            }
                            seated[famCount] = unseated[cur];

                            c += (unseated[cur].getNum() - 1);

                            unseated[cur] = null;

                            for (int j = cur; j < unseated.length - 1; j++) {
                                unseated[j] = unseated[j + 1];
                            }

                            unseated[unseated.length - 1] = null;

                            famCount++;
                        }
                    }
                }
            }

            System.out.println("All Seated!");

            int emCount = 0;
            int stdCount = 0;

			/* Print out the seating arrangement */
            for (int r = 0; r < NUMROWS; r++) {
                if (fat[r][16] != null)
                    System.out.printf("ROW: %2s||", fat[r][16].getRow());
                else
                    System.out.print("ROW: XX||");
                for (int c = 0; c < NUMCOLS; c++) {
                    if (fat[r][c] != null && fat[r][c].getFam() != null) {
                        System.out.printf("%3d", fat[r][c].getFam().getId());
                    } else if (fat[r][c] == null) {
                        System.out.print(" X ");
                    } else {
                        System.out.print(" E ");
                        emCount++;
                    }
                }
                System.out.println();
            }

            System.out.println();
            System.out.println();

            System.out.println("Standing fams: " + (NUMFAM - famCount));
            System.out.println("Sitting people: " + satCount);
            for (int i = 0; i < NUMFAM - famCount; i++) {
                stdCount += unseated[i].getNum();
            }
            System.out.println("Standing people: " + stdCount);
            System.out.println("Free Seats: " + emCount);
            System.out.println("Total seats " + seatCount);
            System.out.println("Number of tries " + tryCount);

        } while (unseated[0] != null);

        resetSeats();

        top.getChildren().clear();
        Button output = new Button("Output");
        output.setFont(Font.font("Verdana", FontWeight.BOLD, 22));
        output.setOnMouseClicked(event -> {
            outPut();
        });
        top.getChildren().add(output);

    }

    /**
     * Refreshes the window to represent the actual FAT layout
     */
    private void resetSeats(){
        gridPane.getChildren().clear();

        boolean colorChoose = true;

        for (int r = 0; r < NUMROWS; r++) {
            for (int c = 0; c < NUMCOLS; c++) {
                Rectangle rec = new Rectangle(24, 24, Color.WHITE);

                rec.setStroke(Color.BLACK);

                StackPane s = new StackPane();
                if (fat[r][c] != null && fat[r][c].getFam() != null) {
                    if (colorChoose) {
                        rec.setFill(Color.rgb(204, 204, 204));
                    } else {
                        rec.setFill(Color.rgb(170, 170, 170));
                    }
                    if (fat[r][c + 1] == null || fat[r][c + 1].getFam() == null || fat[r][c].getFam().getId() != fat[r][c + 1].getFam().getId()) {
                        colorChoose = !colorChoose;
                    }
                    Family curFam = fat[r][c].getFam();
                    s.setOnMouseClicked(event -> {
                        switchColours(curFam);
                    });
                    s.getChildren().addAll(rec, new Label(fat[r][c].getFam().getId() + ""));
                } else if (fat[r][c] != null && fat[r][c].getFam() == null) {
                    rec.setFill(Color.rgb(255, 0, 0));
                    s.getChildren().addAll(rec, new Label("E"));
                    int i = 0;
                    while(fat[r][c-i] != null && fat[r][c-i].getFam() == null){
                        i++;
                    }
                    int numEmpt = i;
                    if(fat[r][c-i] != null && fat[r][c-i].getFam() != null) {
                        Family curFam = fat[r][c - i].getFam();
                        s.setOnMouseClicked(event -> {
                            switchColoursEmpt(curFam, numEmpt);
                        });
                    }
                } else {
                    s.getChildren().add(rec);
                }

                gridPane.add(s, c, r);
            }
        }
    }

    /**
     * Triggered on the selection of a family and will highlight all the families
     * with the same number of people as family f
     * @param f the family that was clicked on to trigger this method
     */
    private void switchColours(Family f){
        resetSeats();
        int numPeople = f.getNum();
        boolean colorChoose = true;
        boolean rightSize;

        for(int r = 0; r < NUMROWS; r++){
            for (int c = 0; c < NUMCOLS; c++) {
                rightSize = false;

                if(fat[r][c] != null){
                    if(fat[r][c].getFam() != null && fat[r][c].getFam().getNum() == numPeople){
                        rightSize = true;
                    }
                }

                if(rightSize){
                    Rectangle rec = new Rectangle(24, 24, Color.rgb(200, 50, 0));
                    if (colorChoose) {
                        rec.setFill(Color.rgb(50, 200, 0));
                    } else {
                        rec.setFill(Color.rgb(120, 200, 0));
                    }
                    if(fat[r][c].getFam().getId() == f.getId()){
                        rec.setFill(Color.rgb(150, 0, 0));
                    }
                    if (fat[r][c + 1] == null || fat[r][c + 1].getFam() == null || fat[r][c].getFam().getId() != fat[r][c + 1].getFam().getId()) {
                        colorChoose = !colorChoose;
                    }
                    rec.setStroke(Color.BLACK);
                    StackPane s = new StackPane();
                    s.getChildren().addAll(rec, new Label(fat[r][c].getFam().getId() + ""));
                    Family o = fat[r][c].getFam();
                    s.setOnMouseClicked(event -> {
                        switchSpots(f, o);
                    });
                    gridPane.add(s, c, r);

                }
            }
        }
    }

    /**
     * switches the positions of family one and two in the FAT and calls resetSeats()
     * @param o family one
     * @param t family two
     */
    private void switchSpots(Family o, Family t) {
        int rowO = 0;
        int rowT = 0;
        int colO = 0;
        int colT = 0;
        int count = 0;

        /* Figure out the FAT row and column of the two families */
        while(rowO == 0 && count < NUMCOLS) {
            for (int i = 0; i < NUMROWS; i++) {
                if (fat[i][count] != null && fat[i][count].getRow().equals(o.getSeat().getRow())) {
                    rowO = i;
                }
            }
            count++;
        }

        count = 0;

        while(rowT == 0 && count < NUMCOLS) {
            for (int i = 0; i < NUMROWS; i++) {
                if (fat[i][count] != null && fat[i][count].getRow().equals(t.getSeat().getRow())) {
                    rowT = i;
                }
            }
            count++;
        }

        for (int i = 0; i < NUMCOLS; i++) {
            if(fat[rowT][i] != null && fat[rowT][i].getCol() == t.getSeat().getCol()) {
                colT = i;
            }
        }

        for (int i = 0; i < NUMCOLS; i++) {
            if(fat[rowO][i] != null && fat[rowO][i].getCol() == o.getSeat().getCol()) {
                colO = i;
            }
        }

        /* Sit the families down */
        for (int i = o.getNum() - 1; i >= 0; i--) {
            t.sit(fat[rowO][colO - i]);
        }

        for (int i = t.getNum() - 1; i >= 0; i--) {
            o.sit(fat[rowT][colT - i]);
        }

        resetSeats();
    }

    /**
     * Similar to switchColours() except is only triggered when an empty seat is clicked
     * highlights families of the same size as family f (the closest family on the left of the empty seat)
     * plus the number of empty seats on the left of the triggered seat
     * @param f the closest family on the left of the empty seat
     * @param numEmpt the extra number of empty seats
     */
    private void switchColoursEmpt(Family f, int numEmpt){
        resetSeats();
        int numPeople = f.getNum();
        boolean colorChoose = true;
        boolean rightSize;

        for(int r = 0; r < NUMROWS; r++){
            for (int c = 0; c < NUMCOLS; c++) {
                rightSize = false;

                if(fat[r][c] != null){
                    if(fat[r][c].getFam() != null && (fat[r][c].getFam().getNum() == numPeople + numEmpt || fat[r][c].getFam().getId() == f.getId())){
                        rightSize = true;
                    }
                }

                if(rightSize){
                    Rectangle rec = new Rectangle(24, 24, Color.rgb(200, 50, 0));
                    if (colorChoose) {
                        rec.setFill(Color.rgb(50, 200, 0));
                    } else {
                        rec.setFill(Color.rgb(120, 200, 0));
                    }
                    if(fat[r][c].getFam().getId() == f.getId()){
                        rec.setFill(Color.rgb(150, 0, 0));
                    }
                    if (fat[r][c + 1] == null || fat[r][c + 1].getFam() == null || fat[r][c].getFam().getId() != fat[r][c + 1].getFam().getId()) {
                        colorChoose = !colorChoose;
                    }
                    rec.setStroke(Color.BLACK);
                    StackPane s = new StackPane();
                    s.getChildren().addAll(rec, new Label(fat[r][c].getFam().getId() + ""));
                    Family o = fat[r][c].getFam();
                    s.setOnMouseClicked(event -> {
                        switchSpots(f, o, numEmpt);
                    });
                    gridPane.add(s, c, r);

                }
            }
        }

    }

    /**
     * Overloaded switchSpots() method except this one also takes into account the possiblity of
     * empty seats
     * @param o family one
     * @param t family two
     * @param numEmpt the number of empty seats to take into account
     */
    private void switchSpots(Family o, Family t, int numEmpt){
        if(o.getId() != t.getId()) {
            int rowO = 0;
            int rowT = 0;
            int colO = 0;
            int colT = 0;
            int count = 0;

            while (rowO == 0 && count < NUMCOLS) {
                for (int i = 0; i < NUMROWS; i++) {
                    if (fat[i][count] != null && fat[i][count].getRow().equals(o.getSeat().getRow())) {
                        rowO = i;
                    }
                }
                count++;
            }

            count = 0;

            while (rowT == 0 && count < NUMCOLS) {
                for (int i = 0; i < NUMROWS; i++) {
                    if (fat[i][count] != null && fat[i][count].getRow().equals(t.getSeat().getRow())) {
                        rowT = i;
                    }
                }
                count++;
            }

            for (int i = 0; i < NUMCOLS; i++) {
                if (fat[rowT][i] != null && fat[rowT][i].getCol() == t.getSeat().getCol()) {
                    colT = i;
                }
            }

            for (int i = 0; i < NUMCOLS; i++) {
                if (fat[rowO][i] != null && fat[rowO][i].getCol() == o.getSeat().getCol()) {
                    colO = i;
                }
            }


            for (int i = t.getNum() - 1 - numEmpt; i >= 0 - numEmpt; i--) {
                t.sit(fat[rowO][colO - i]);
            }

            for (int i = o.getNum() - 1 + numEmpt; i >= numEmpt; i--) {
                o.sit(fat[rowT][colT - i]);
            }

            for (int i = 0; i < numEmpt; i++) {
                fat[rowT][colT - i].setFamily(null);
            }

        }
        resetSeats();

    }

    /**
     * Outputs the current seats per each family onto out_seat.txt
     */
    private void outPut(){

        out = "out_seat.txt";


        try {
            FileWriter fileWriter = new FileWriter(out);

            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

            /* Bubblesort the families in ID order */
            for (int i = 0; i < seated.length - 1; i++) {
                for (int j = 0; j < seated.length - 1; j++) {
                    if (seated[j + 1] != null) {
                        if (seated[j].getId() > seated[j + 1].getId()) {
                            Family temp = seated[j];
                            seated[j] = seated[j + 1];
                            seated[j + 1] = temp;
                        }
                    }
                }
            }

            /* Print out the ordered families and their seats onto a file */
            for (int i = 0; i < unseated.length; i++) {
                if (seated[i] != null && seated[i].getSeat() != null) {
                    bufferedWriter.write(seated[i].getName() + ";");
                    for (int j = 0; j < seated[i].getNum(); j++) {
                        bufferedWriter.write(seated[i].getSeat().getRow() + "" + (seated[i].getSeat().getCol() - j) + " ");
                    }

                    bufferedWriter.newLine();
                } else {
                    bufferedWriter.write("SEATLESS");
                    bufferedWriter.newLine();
                }
            }

            bufferedWriter.close();
        } catch (IOException ex) {
            System.out.println("Error writing to file '" + out + "'");
        }

        //TODO: Add a search bar for a particular family id <-- extra feature
        //TODO: Notice groups of empty seats <-- more important
    }

    public static void main(String[] args) {
        launch(args);
    }

}