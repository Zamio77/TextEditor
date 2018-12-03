import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.text.*;

import javax.swing.*;


public class TextFont {

    Font timesRoman = new Font("Times New Roman", 12);
    Font serif = Font.font("Serif",FontWeight.BOLD, 12);
    Font arial = Font.font("Arial", FontWeight.SEMI_BOLD, FontPosture.REGULAR, 12);
    Font sansSerif = Font.font("Sans-Serif", FontWeight.BOLD, FontPosture.ITALIC, 12);

    public TextFont() {

    }

    public TextFont(Font timesRoman, Font serif, Font arial, Font sansSerif) {
        this.timesRoman = timesRoman;
        this.serif = serif;
        this.arial = arial;
        this.sansSerif = sansSerif;
    }

    public Font getTimesRoman() {
        return timesRoman;
    }

    public Font getSerif() {
        return serif;
    }

    public Font getArial() {
        return arial;
    }

    public Font getSansSerif() {
        return sansSerif;
    }

    public void setTimesRoman(TextArea area) {
        area.setFont(timesRoman);
    }

    public void setSerif(TextArea area) {
        area.setFont(serif);
    }

    public void setArial(TextArea area) {
        area.setFont(arial);
    }

    public void setSansSerif(TextArea area) {
        area.setFont(sansSerif);
    }
}
