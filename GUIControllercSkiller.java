package scripts.GUI;

import com.allatori.annotations.DoNotRename;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseDragEvent;
import org.tribot.api.General;
import org.tribot.api2007.Skills;
import org.tribot.script.sdk.util.ScriptSettings;
import scripts.Data.Const;
import scripts.Data.Enums.Methods;
import scripts.Data.SkillTasks;
import scripts.Data.Vars;
import scripts.Timer;
import scripts.Utils;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class GUIControllercSkiller extends AbstractGUIController {


    @FXML @DoNotRename
    private ImageView craftingGoalLevel;

    @FXML @DoNotRename
    private TextField agilityGoalLevelBox;

    @FXML @DoNotRename
    private TextField herbloreGoalLevelBox;

    @FXML @DoNotRename
    private TextField craftingGoalLevelBox;

    @FXML @DoNotRename
    private TextField fletchingGoalLevelBox;

    @FXML @DoNotRename
    private TextField miningGoalLevelBox;

    @FXML @DoNotRename
    private TextField firemakingGoalLevelBox;

    @FXML @DoNotRename
    private TextField woodcuttingGoalLevelBox;

    @FXML @DoNotRename
    private TextField cookingGoalLevelBox;

    @FXML @DoNotRename
    private TextField fishingGoalLevel;

    @FXML @DoNotRename
    private TextField prayerGoalLevelBox;

    @FXML @DoNotRename
    private TextField magicGoalLevelBox;

    @FXML @DoNotRename
    private TextField thievingGoalLevelBox;

    @FXML @DoNotRename
    private TextField smithingGoalLevelBox;

    @FXML @DoNotRename
    private TextField runecraftingGoalLevelBox;

    @FXML @DoNotRename
    private TextField constructionGoalLevelBox;

    @FXML @DoNotRename
    private TextField hunterGoalLevelBox;

    @FXML @DoNotRename
    private TextField slayerGoalLevelBox;


    @FXML @DoNotRename
    private TextField switchSkillMinTime;

    @FXML @DoNotRename
    private TextField switchSkillMaxTime;

    @FXML @DoNotRename
    private TextField afkEveryAverage;

    @FXML @DoNotRename
    private TextField afkEverySD;

    @FXML @DoNotRename
    private TextField afkForAverage;

    @FXML @DoNotRename
    private TextField afkForSD;

    @FXML @DoNotRename
    private TextField mouseSpeedBox;

    @FXML @DoNotRename
    private Slider abc2Modifier;

    @FXML @DoNotRename
    private ComboBox<String> startingSkillDropDown;


    @FXML @DoNotRename
    private ComboBox<String> logActionBox;

    @FXML @DoNotRename
    private CheckBox useMlmBox1;

    @FXML @DoNotRename
    private CheckBox useBlastFurnaceBox;


    @FXML
    @DoNotRename
    void startButtonPressed(ActionEvent event) {
        General.println("[GUIController]: Start button pressed");
        Vars settings = Vars.get();
        ScriptSettings handler = ScriptSettings.getDefault();
        handler.save("last", settings);
        updateSkillEndLevel(event);
        updateSkillSwitchTimer(event);
        if (useMlmBox1.isSelected())
            Vars.get().useMLM = true;
        else
            Vars.get().useMLM = false;
      //  setUseCBalls(event);
        this.getGUI().close();
    }




 /*   @FXML
    @DoNotRename
    void setUseCBalls(ActionEvent event) {
        Vars.get().smeltCannonballs = smithCannonballsBox.isSelected();
        if (smithCannonballsBox.isSelected())
            useBlastFurnaceBox.setSelected(false);
    }*/


    @FXML
    @DoNotRename
    void setUseMlmBox(ActionEvent event) {
        General.println("[GUIController]: Updated useMLM Boolean to: " + useMlmBox1.isSelected());
        if (useMlmBox1.isSelected())
            Vars.get().useMLM = true;
        else
            Vars.get().useMLM = false;
    }

    @FXML
    @DoNotRename
    void mouseSpeedAdjusted(ActionEvent event) {
        General.println("[GUIController]: Mouse Speed adjusted to " + mouseSpeedBox.getText());
        Vars.get().mouseSpeed = Integer.parseInt(mouseSpeedBox.getText());
    }

    @FXML
    @DoNotRename
    void updateAFKFrequency(ActionEvent event) {
        Vars.get().afkFrequencyAvg = Integer.parseInt(afkEveryAverage.getText()) * 1000;
        Vars.get().afkFrequencySD = Integer.parseInt(afkEverySD.getText()) * 1000;
        Vars.get().afkTimer = new Timer(General.randomSD(Vars.get().afkFrequencyAvg, Vars.get().afkFrequencySD));
        General.println("[GUIController]: Updated AFK Frequency");
    }

    @FXML @DoNotRename
    void updateAfkDuration(ActionEvent event) {
        General.println("[GUIController]: Updated AFK duration");
        Vars.get().afkDurationAvg = Integer.parseInt(afkForAverage.getText()) * 1000;
        Vars.get().afkDurationSD = Integer.parseInt(afkForSD.getText()) * 1000;
    }

    @FXML @DoNotRename
    void updateSkillSwitchTimer(ActionEvent event) {
        General.println("[GUIController]: Updated Skill Switch duration: " + switchSkillMinTime.getText());
        Vars.get().skillSwitchMin = Integer.parseInt(switchSkillMinTime.getText()) * 60000;
        Vars.get().skillSwitchMax = Integer.parseInt(switchSkillMaxTime.getText()) * 60000;
        Vars.get().skillSwitchTimer = new Timer(General.random(Vars.get().skillSwitchMin, Vars.get().skillSwitchMax));
        General.println("[GUIController]: Updated Skill Switch duration");
        General.println("[GUIController]: skillSwitchTimer.getRemaining()" + Vars.get().skillSwitchTimer.getRemaining());
    }

    @FXML @DoNotRename
    void updateABC2Modifier(MouseDragEvent event) {
        General.println("[GUIController]: Updated ABC2 Modifier to " + event.toString());
        Utils.FACTOR = (abc2Modifier.getValue() / 100);
    }

    @FXML @DoNotRename
    void updateSkillEndLevel(ActionEvent event) {
        General.println("[GUIController]: Updating end level");
        SkillTasks.AGILITY.setEndLevel(Integer.parseInt(String.valueOf(agilityGoalLevelBox.getText())));
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
        SkillTasks.SLAYER.setEndLevel(Integer.parseInt(String.valueOf(slayerGoalLevelBox.getText())));
        SkillTasks.CONSTRUCTION.setEndLevel(Integer.parseInt(String.valueOf(constructionGoalLevelBox.getText())));
        SkillTasks.THIEVING.setEndLevel(Integer.parseInt(String.valueOf(thievingGoalLevelBox.getText())));
        SkillTasks.RUNECRAFTING.setEndLevel(Integer.parseInt(String.valueOf(runecraftingGoalLevelBox.getText())));
        SkillTasks.HUNTER.setEndLevel(Integer.parseInt(String.valueOf(hunterGoalLevelBox.getText())));

        SkillTasks.SMITHING.setEndLevel(Integer.parseInt(String.valueOf(smithingGoalLevelBox.getText())));
        General.println("[GUIController]: Updating end level");

    }

    @FXML @DoNotRename
    void skillSelected(ActionEvent event) {

        switch (startingSkillDropDown.getValue()) {
            case ("AGILITY"):
                Vars.get().currentTask = SkillTasks.AGILITY;
                break;
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
            case ("CONSTRUCTION"):
                Vars.get().currentTask = SkillTasks.CONSTRUCTION;
                break;
            case ("SLAYER"):
                Vars.get().currentTask = SkillTasks.SLAYER;
                break;
            case ("SMITHING"):
                Vars.get().currentTask = SkillTasks.SMITHING;
            case ("THIEVING"):
                Vars.get().currentTask = SkillTasks.THIEVING;
                break;
        }
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {

        agilityGoalLevelBox.setText(String.valueOf(Skills.SKILLS.AGILITY.getActualLevel()));
        herbloreGoalLevelBox.setText(String.valueOf(Skills.SKILLS.HERBLORE.getActualLevel()));
        craftingGoalLevelBox.setText(String.valueOf(Skills.SKILLS.CRAFTING.getActualLevel()));
        fletchingGoalLevelBox.setText(String.valueOf(Skills.SKILLS.FLETCHING.getActualLevel()));
        miningGoalLevelBox.setText(String.valueOf(Skills.SKILLS.MINING.getActualLevel()));
        firemakingGoalLevelBox.setText(String.valueOf(Skills.SKILLS.FIREMAKING.getActualLevel()));
        woodcuttingGoalLevelBox.setText(String.valueOf(Skills.SKILLS.WOODCUTTING.getActualLevel()));
        cookingGoalLevelBox.setText(String.valueOf(Skills.SKILLS.COOKING.getActualLevel()));
        fishingGoalLevel.setText(String.valueOf(Skills.SKILLS.FISHING.getActualLevel()));
        prayerGoalLevelBox.setText(String.valueOf(Skills.SKILLS.PRAYER.getActualLevel()));
        magicGoalLevelBox.setText(String.valueOf(Skills.SKILLS.MAGIC.getActualLevel()));
        thievingGoalLevelBox.setText(String.valueOf(Skills.SKILLS.THIEVING.getActualLevel()));
        smithingGoalLevelBox.setText(String.valueOf(Skills.SKILLS.SMITHING.getActualLevel()));
        runecraftingGoalLevelBox.setText(String.valueOf(Skills.SKILLS.RUNECRAFTING.getActualLevel()));
        hunterGoalLevelBox.setText(String.valueOf(Skills.SKILLS.HUNTER.getActualLevel()));
        slayerGoalLevelBox.setText(String.valueOf(Skills.SKILLS.SLAYER.getActualLevel()));
      //  constructionGoalLevelBox.setText(String.valueOf(Skills.SKILLS.CONSTRUCTION.getActualLevel()));
        thievingGoalLevelBox.setText(String.valueOf(Skills.SKILLS.THIEVING.getActualLevel()));
        switchSkillMinTime.setText(String.valueOf(Vars.get().skillSwitchMin/60000));
        switchSkillMaxTime.setText(String.valueOf(Vars.get().skillSwitchMax/60000));

        for (SkillTasks s : SkillTasks.values()) {
            startingSkillDropDown.getItems().add(s.getSkillName());
        }
        for (Const.LOG_ACTIONS action : Const.LOG_ACTIONS.values()) {
            logActionBox.getItems().add(action.toString());
        }
        for (Methods.MAGIC meth : Methods.MAGIC.values()) {

        }
        for (Methods.HERBLORE meth : Methods.HERBLORE.values()) {

        }
        for (Methods.COOKING meth : Methods.COOKING.values()) {

        }
        for (Methods.CRAFTING meth : Methods.CRAFTING.values()) {

        }
        for (Methods.FISHING meth : Methods.FISHING.values()) {

        }
        for (Methods.FLETCHING meth : Methods.FLETCHING.values()) {

        }
        for (Methods.HUNTER meth : Methods.HUNTER.values()) {

        }
        for (Methods.MINING meth : Methods.MINING.values()) {

        }
    }
}


