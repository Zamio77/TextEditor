import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.scene.control.ScrollPane;

import javax.swing.*;
import java.io.File;

import static javafx.application.Application.launch;

public class TextEditor extends Application {

    private TextArea area = new TextArea();
    private FileChooser dialog = new FileChooser();
    private String currentFile = "Untitled";
    private boolean changed = false;
    TextFont newFont = new TextFont();
    FileDropdown fileDropdown = new FileDropdown();

    public void start(Stage primaryStage) {
        newFont.setTimesRoman(area);
        ScrollPane scrollPane = new ScrollPane(area);

        area.setWrapText(true);
        area.setEditable(true);

        // Create the menu lists
        Menu fileMenu = new Menu("File");
        Menu editMenu = new Menu("Edit");
        Menu fontMenu = new Menu("Font");
        Menu spellCheckMenu = new Menu("Spell Check");

        // Create the File menu items
        MenuItem newFile = new MenuItem("New");
        MenuItem open = new MenuItem("Open");
        MenuItem save = new MenuItem("Save");
        MenuItem saveAs = new MenuItem("Save As");
        MenuItem quit = new MenuItem("Quit");

        // Add the File menu items to the File menu
        fileMenu.getItems().add(newFile);
        fileMenu.getItems().add(open);
        fileMenu.getItems().add(save);
        fileMenu.getItems().add(saveAs);
        fileMenu.getItems().add(quit);

        // Create the Edit menu items
        MenuItem cut = new MenuItem("Cut");
        MenuItem copy = new MenuItem("Copy");
        MenuItem paste = new MenuItem("Paste");

        // Add the Edit menu items to the Edit menu
        editMenu.getItems().add(cut);
        editMenu.getItems().add(copy);
        editMenu.getItems().add(paste);

        // Create the Font menu items
        MenuItem timesRoman = new MenuItem("Times New Roman");
        MenuItem serif = new MenuItem("Serif");
        MenuItem arial = new MenuItem("Arial");
        MenuItem sansSerif = new MenuItem("Sans-Serif");

        // Add the Font menu items to the Font menu
        fontMenu.getItems().add(timesRoman);
        fontMenu.getItems().add(serif);
        fontMenu.getItems().add(arial);
        fontMenu.getItems().add(sansSerif);

        // Create the Spell Check menu items
        MenuItem spellCheck = new MenuItem("Check Spelling");

        // Add the Spell Check menu items to the Spell Check menu
        spellCheckMenu.getItems().add(spellCheck);

        // Create the menu bars
        MenuBar fileMenuBar = new MenuBar();
        MenuBar editMenuBar = new MenuBar();
        MenuBar fontMenuBar = new MenuBar();
        MenuBar spellCheckMenuBar = new MenuBar();

        // Add the menus to the menu bars
        fileMenuBar.getMenus().add(fileMenu);
        editMenuBar.getMenus().add(editMenu);
        fontMenuBar.getMenus().add(fontMenu);
        spellCheckMenuBar.getMenus().add(spellCheckMenu);

        // Create the Vboxs
        VBox fileVbox = new VBox(fileMenuBar);
        VBox editVbox = new VBox(editMenuBar);
        VBox fontVbox = new VBox(fontMenuBar);
        VBox spellCheckVbox = new VBox(spellCheckMenuBar);

        HBox menuBarOptions = new HBox(fileVbox, editVbox, fontVbox, spellCheckVbox);

        newFile.setOnAction(e -> {
            FileChooser fileChooser = new FileChooser();

            //Set extension filter for text files
            FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("TXT files (*.txt)", "*.txt");
            fileChooser.getExtensionFilters().add(extFilter);

            //Show save file dialog
            File file = fileChooser.showSaveDialog(primaryStage);

            if (file != null) {
                fileDropdown.saveFile(getArea().getText(), file);
                getArea().setText(" ");
            }

            setCurrentFile(file.getName());

        });

        open.setOnAction(e -> {
            FileChooser fileChooser = new FileChooser();

            File file = fileChooser.showOpenDialog(primaryStage);
            if (file != null) {
                FileDropdown.openFile(file);
            }
        });

        save.setOnAction(e -> {
            FileChooser fileChooser = new FileChooser();

            //Set extension filter for text files
            FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("TXT files (*.txt)", "*.txt");
            fileChooser.getExtensionFilters().add(extFilter);

            File file = fileChooser.showSaveDialog(null);

            if (file != null) {
                fileDropdown.saveFile(getArea().getText(), file);
            }
        });

        saveAs.setOnAction(e -> {
            FileChooser fileChooser = new FileChooser();

            //Set extension filter for text files
            FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("TXT files (*.txt)", "*.txt");
            fileChooser.getExtensionFilters().add(extFilter);

            //Show save file dialog
            File file = fileChooser.showSaveDialog(primaryStage);

            if (file != null) {
                fileDropdown.saveFile(getArea().getText(), file);
            }

        });

        quit.setOnAction(e -> {
            primaryStage.close();
        });

        cut.setOnAction(e -> {
            getArea().cut();
        });

        copy.setOnAction(e -> {
            getArea().copy();
        });

        paste.setOnAction(e -> {
            getArea().paste();
        });

        timesRoman.setOnAction(e -> {
            newFont.setTimesRoman(area);
        });

        serif.setOnAction(e -> {
            newFont.setSerif(area);
        });

        arial.setOnAction(e -> {
            newFont.setArial(area);
        });

        sansSerif.setOnAction(e -> {
            newFont.setSansSerif(area);
        });

        Pane pane = new Pane();
        pane.getChildren().addAll(menuBarOptions);

        BorderPane borderPane = new BorderPane();
        borderPane.setTop(pane);
        borderPane.setCenter(area);

        Scene scene = new Scene(borderPane, 450, 200);
        primaryStage.setTitle(getCurrentFile());
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }


    public boolean isChanged() {
        return changed;
    }

    public TextArea getArea() {
        return area;
    }

    public void setArea(TextArea area) {
        this.area = area;
    }

    public String getCurrentFile() {
        return currentFile;
    }

    public FileChooser getDialog() {
        return dialog;
    }

    public void setDialog(FileChooser dialog) {
        this.dialog = dialog;
    }

    public void setCurrentFile(String currentFile) {
        this.currentFile = currentFile;
    }

    public void setChanged(boolean changed) {
        this.changed = changed;
    }

    public TextFont getNewFont() {
        return newFont;
    }

    public void setNewFont(TextFont newFont) {
        this.newFont = newFont;
    }



}