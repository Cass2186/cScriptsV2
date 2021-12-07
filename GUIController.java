package scripts.GUI;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseDragEvent;
import org.tribot.api.General;
import scripts.Data.Const;
import scripts.Data.Enums.Methods;
import scripts.Data.SkillTasks;
import scripts.Data.Vars;
import scripts.Timer;
import scripts.Utils;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class GUIController extends AbstractGUIController {


    @FXML
    private ImageView craftingGoalLevel;

    @FXML
    private TextField agilityGoalLevelBox;

    @FXML
    private TextField herbloreGoalLevelBox;

    @FXML
    private TextField craftingGoalLevelBox;

    @FXML
    private TextField fletchingGoalLevelBox;

    @FXML
    private TextField miningGoalLevelBox;

    @FXML
    private TextField firemakingGoalLevelBox;

    @FXML
    private TextField woodcuttingGoalLevelBox;

    @FXML
    private TextField cookingGoalLevelBox;

    @FXML
    private TextField fishingGoalLevel;

    @FXML
    private TextField prayerGoalLevelBox;

    @FXML
    private TextField magicGoalLevelBox;

    @FXML
    private TextField thievingGoalLevelBox;

    @FXML
    private TextField smithingGoalLevelBox;

    @FXML
    private TextField runecraftingGoalLevelBox;

    @FXML
    private TextField switchSkillMinTime;

    @FXML
    private TextField switchSkillMaxTime;

    @FXML
    private TextField afkEveryAverage;

    @FXML
    private TextField afkEverySD;

    @FXML
    private TextField afkForAverage;

    @FXML
    private TextField afkForSD;

    @FXML
    private TextField mouseSpeedBox;

    @FXML
    private Slider abc2Modifier;

    @FXML
    private ComboBox<String> startingSkillDropDown;


    @FXML
    private ComboBox<String> logActionBox;


    @FXML
    void startButtonPressed(ActionEvent event) {
        General.println("[GUIController]: Start button pressed");
        this.getGUI().close();
    }


    @FXML
    void updateAFKFrequency(ActionEvent event) {
        Vars.get().afkFrequencyAvg = Integer.parseInt(afkEveryAverage.getText()) * 1000;
        Vars.get().afkFrequencySD = Integer.parseInt(afkEverySD.getText()) * 1000;
        Vars.get().afkTimer = new Timer(General.randomSD(Vars.get().afkFrequencyAvg, Vars.get().afkFrequencySD));
        General.println("[GUIController]: Updated AFK Frequency");
    }

    @FXML
    void updateAfkDuration(ActionEvent event) {
        General.println("[GUIController]: Updated AFK duration");
        Vars.get().afkDurationAvg = Integer.parseInt(afkForAverage.getText()) * 1000;
        Vars.get().afkDurationSD = Integer.parseInt(afkForSD.getText()) * 1000;
    }

    @FXML
    void updateSkillSwitchTimer(ActionEvent event) {
        General.println("[GUIController]: Updated Skill Switch duration: " + switchSkillMinTime.getText());
        Vars.get().skillSwitchMin = Integer.parseInt(switchSkillMinTime.getText()) * 60000;
        Vars.get().skillSwitchMax = Integer.parseInt(switchSkillMaxTime.getText()) * 60000;
        Vars.get().skillSwitchTimer = new Timer(General.random(Vars.get().skillSwitchMin, Vars.get().skillSwitchMax));
        General.println("[GUIController]: Updated Skill Switch duration");
        General.println("[GUIController]: skillSwitchTimer.getRemaining()" + Vars.get().skillSwitchTimer.getRemaining());
    }

    @FXML
    void updateABC2Modifier(MouseDragEvent event) {
        General.println("[GUIController]: Updated ABC2 Modifier to " + (abc2Modifier.getValue() / 100));
        Utils.FACTOR = (abc2Modifier.getValue() / 100);
    }

    @FXML
    void updateSkillEndLevel(ActionEvent event) {
        General.println("[GUIController]: Updating end level");
        // SkillTasks.AGILITY.setEndLevel(Integer.parseInt(String.valueOf(agilityGoalLevelBox)));
        SkillTasks.HERBLORE.setEndLevel(Integer.parseInt(String.valueOf(herbloreGoalLevelBox.getText())));
        SkillTasks.CRAFTING.setEndLevel(Integer.parseInt(String.valueOf(craftingGoalLevelBox.getText())));
        SkillTasks.CRAFTING.setEndLevel(Integer.parseInt(String.valueOf(craftingGoalLevelBox.getText())));
        SkillTasks.FLETCHING.setEndLevel(Integer.parseInt(String.valueOf(fletchingGoalLevelBox.getText())));
        SkillTasks.MINING.setEndLevel(Integer.parseInt(String.valueOf(miningGoalLevelBox.getText())));
        SkillTasks.FIREMAKING.setEndLevel(Integer.parseInt(String.valueOf(firemakingGoalLevelBox.getText())));
        SkillTasks.WOODCUTTING.setEndLevel(Integer.parseInt(String.valueOf(woodcuttingGoalLevelBox.getText())));
        SkillTasks.COOKING.setEndLevel(Integer.parseInt(String.valueOf(cookingGoalLevelBox.getText())));
        SkillTasks.FISHING.setEndLevel(Integer.parseInt(String.valueOf(fishingGoalLevel.getText())));
        SkillTasks.PRAYER.setEndLevel(Integer.parseInt(String.valueOf(prayerGoalLevelBox.getText())));
        SkillTasks.MAGIC.setEndLevel(Integer.parseInt(String.valueOf(magicGoalLevelBox.getText())));
        //SkillTasks.THIEVING.setEndLevel(Integer.parseInt(String.valueOf(thievingGoalLevelBox)));

        //SkillTasks.SMITHING.setEndLevel(Integer.parseInt(String.valueOf(smithingGoalLevelBox)));

        //SkillTasks.RUNECRAFTING.setEndLevel(Integer.parseInt(String.valueOf(runecraftingGoalLevelBox)));

    }

    @FXML
    void skillSelected(ActionEvent event) {

        switch (startingSkillDropDown.getValue()) {
            case ("FISHING"):
                Vars.get().currentTask = SkillTasks.FISHING;
                break;
            case ("FIREMAKING"):
                Vars.get().currentTask = SkillTasks.FIREMAKING;
                break;
            case ("CRAFTING"):
                Vars.get().currentTask = SkillTasks.CRAFTING;
                break;
            case ("COOKING"):
                Vars.get().currentTask = SkillTasks.COOKING;
                break;
            case ("FLETCHING"):
                Vars.get().currentTask = SkillTasks.FLETCHING;
                break;
            case ("HERBLORE"):
                Vars.get().currentTask = SkillTasks.HERBLORE;
                break;
            case ("HUNTER"):
                Vars.get().currentTask = SkillTasks.HUNTER;
                break;
            case ("MAGIC"):
                Vars.get().currentTask = SkillTasks.MAGIC;
                break;
            case ("MINING"):
                Vars.get().currentTask = SkillTasks.MINING;
                break;
            case ("PRAYER"):
                Vars.get().currentTask = SkillTasks.PRAYER;
                break;
            case ("WOODCUTTING"):
                Vars.get().currentTask = SkillTasks.WOODCUTTING;
                break;
            case ("PEST_CONTROL"):
                Vars.get().currentTask = SkillTasks.PEST_CONTROL;
                break;
        }
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        for (SkillTasks s : SkillTasks.values()) {
            startingSkillDropDown.getItems().add(s.getSkillName());
        }
        for (Const.LOG_ACTIONS action : Const.LOG_ACTIONS.values()){
            logActionBox.getItems().add(action.toString());
        }
        for (Methods.MAGIC meth : Methods.MAGIC.values()){

        }
        for (Methods.HERBLORE meth : Methods.HERBLORE.values()){

        }
        for (Methods.COOKING meth : Methods.COOKING.values()){

        }
        for (Methods.CRAFTING meth : Methods.CRAFTING.values()){

        }
        for (Methods.FISHING meth : Methods.FISHING.values()){

        }
        for (Methods.FLETCHING meth : Methods.FLETCHING.values()){

        }
        for (Methods.HUNTER meth : Methods.HUNTER.values()){

        }
        for (Methods.MINING meth : Methods.MINING.values()){

        }
    }
}


