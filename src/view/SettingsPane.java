package view;

import Util.BiMap;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import sun.security.provider.ConfigFile;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by jsybran on 10/22/2016.
 */
public class SettingsPane extends HBox {

    Map<String,ComboBox<String>> options;
    Map<String,BiMap<Integer,String>> comboBoxValues;
    Button addButton;
    Spinner<Integer> rowSpinner;
    Spinner<Integer> colSpinner;

    public SettingsPane(){
        addButton = new Button("Add");
        rowSpinner = new Spinner<>(1,100,10);
        colSpinner = new Spinner<>(1,100,10);
        VBox vb1 = new VBox();
        vb1.getChildren().add(new Label("# Rows"));
        vb1.getChildren().add(rowSpinner);
        VBox vb2 = new VBox();
        vb2.getChildren().add(new Label("# Cols"));
        vb2.getChildren().add(colSpinner);
        getChildren().add(addButton);
        getChildren().add(vb1);
        getChildren().add(vb2);
        options = new HashMap<>();
        comboBoxValues = new HashMap<>();
    }



    public ComboBox<String> addSelector(String title, BiMap<Integer,String> values){
        ComboBox<String> comboBox = new ComboBox<>();
        for(String s : values.values())
            comboBox.getItems().add(s);
        options.put(title,comboBox);
        comboBoxValues.put(title,values);
        VBox vb = new VBox();
        vb.getChildren().add(new Label(title));
        vb.getChildren().add(comboBox);
        getChildren().add(vb);
        return comboBox;
    }

    public Button getAddButton(){return addButton;}

    public int getComboBoxValue(String name){
        return comboBoxValues.get(name).getValue(options.get(name).getSelectionModel().getSelectedItem());
    }

    public int getRowValue(){
        return rowSpinner.getValue();
    }

    public int getColValue(){
        return colSpinner.getValue();
    }

}
