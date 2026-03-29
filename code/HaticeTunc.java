//202340081

import java.util.ArrayList;
public class HaticeTunc {
    public static void main(String[] args) {

        ArrayList<Stage> stages = new ArrayList<>();

        Stage stage0= new Stage(-0.45, 3.65, 10, 0, 39, 37, 38,
                "Arrow keys are required ", "Arrow keys move player, press button and enter the second pipe");
        Stage stage1= new Stage(-0.45, 3.65, 10, 1, 37, 39, 38,
                "Not always straight forward", "Right and left buttons reversed");
        Stage stage2= new Stage(-2, 3.65, 24, 2, 39, 37, 38,
                "A bit bouncy here", "You jump constantly");
        Stage stage3= new Stage(-0.45, 3.65, 10, 3, 39, 37, 38,
                "Never gonna give you up", "Press button 5 times");
        Stage stage4= new Stage(-0.45, 3.65, 10, 4, 68, 65, 87,
                "Controls feel different this time.", "Use W to jump, A to go left, and D to go right.");
        stages.add(stage0);
        stages.add(stage1);
        stages.add(stage2);
        stages.add(stage3);
        stages.add(stage4);
        Game game = new Game(stages);
        game.play();
    }




    }

