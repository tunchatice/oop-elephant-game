//202340081

import java.awt.*;
import java.util.ArrayList; // Make sure to import ArrayList
public class Game {
    private int stageIndex=0;
    private ArrayList<Stage> stages;
    private int deathNumber=0;
    private double gameTime;
    private double resetTime;
    private boolean resetGame;
    private Map map;
    private Player player;
    private Stage currentStage;
    boolean wasMousePressed = false;
    long stageStartTime = System.currentTimeMillis();
    private long totalPauseTime = 0;
    private long pauseStartTime;



    public Game(ArrayList<Stage> stages) {
        if (stages.size()!=0){
            this.stages = stages;
            this.stageIndex = 0;
            this.currentStage = stages.get(stageIndex);
            player = new Player(130, 460, stages.get(getStageIndex()));
            map= new Map(getStage(), player);}
        else{
            this.currentStage = new Stage(-0.45, 5.65, 10, 0, 39, 37, 38,
                    "first one", "be careful");
        }
    }

    private void handleInput(Stage stage) {
        if (getStageIndex() != 1) {
            if (StdDraw.isKeyPressed(stage.getKeyCodes()[0])) {
                if (!(map.checkDoorCollision(player.getX(), player.getY()))) {
                    player.setFacing(Player.Direction.RIGHT);
                    player.move(Player.Direction.RIGHT);
                }
            }
            if (StdDraw.isKeyPressed(stage.getKeyCodes()[1])) {
                player.setFacing(Player.Direction.LEFT);
                player.move(Player.Direction.LEFT);
            }
            if (stageIndex != 2) {
                if (StdDraw.isKeyPressed(stage.getKeyCodes()[2])) {
                    player.move(Player.Direction.UP);
                }
            }
        }
        else {
            if (StdDraw.isKeyPressed(stage.getKeyCodes()[0])) {
                if (!(map.checkDoorCollision(player.getX(), player.getY()))) {
                    player.setFacing(Player.Direction.LEFT);
                    player.move(Player.Direction.RIGHT);
                }
            }
            if (StdDraw.isKeyPressed(stage.getKeyCodes()[1])) {
                player.setFacing(Player.Direction.RIGHT);
                player.move(Player.Direction.LEFT);
            }
            if (StdDraw.isKeyPressed(stage.getKeyCodes()[2])) {
                player.move(Player.Direction.UP);
            }
        }
    }


    private void handleMouseClick() {
        if (StdDraw.isMousePressed()) {
            if (!wasMousePressed) {
                double mx = StdDraw.mouseX();
                double my = StdDraw.mouseY();
                // Restart
                if (mx >= 510 && mx <= 590 && my >= 70 && my <= 100) {
                    deathNumber++;
                    player.respawn(new int[]{130, 460});
                    map.resetButtonPressNum();
                    map.setDoorBack();
                }

                // Reset Game
                if (mx >= 320 && mx <= 480 && my >= 5 && my <= 35) {
                    stageIndex = 0;
                    map.resetButtonPressNum();
                    setCurrentStage();
                    player = new Player(130, 460, currentStage);
                    map = new Map(currentStage, player);
                    deathNumber = 0;
                    totalPauseTime = 0;
                    stageStartTime = System.currentTimeMillis(); // 🔄 reset the timer
                    draw();
                    resetStage();          // 🎬 draw reset message
                    StdDraw.show();// ✅ render it
                    StdDraw.pause(1000);   // ⏸️ keep it visible for 1 second
                    StdDraw.clear();       // 🧹 clean the screen
                }

                wasMousePressed = true;
            }
        } else {
            wasMousePressed = false;
        }
    }

    private boolean isMouseOverHelpButton() {
        double mx = StdDraw.mouseX();
        double my = StdDraw.mouseY();
        return StdDraw.isMousePressed() &&
                mx >= 210 && mx <= 290 && my >= 70 && my <= 100;
    }



    public int getStageIndex() {
        return stageIndex;
    }

    public void setDeathNumber(){
        deathNumber++;
    }

    public void setCurrentStage() {
        if (stageIndex >= 0 && stageIndex < stages.size()) {
            currentStage=stages.get(stageIndex);}// Handle invalid cases
    }

    public Stage getStage() {
        return currentStage;}

    private String getFormattedElapsedTime(long x2, long x1,long x3) {
        long elapsed = x2 - x1- x3;

        long minutes = (elapsed / 60000);
        long seconds = (elapsed % 60000) / 1000;
        long millis = (elapsed % 1000) / 10; // two-digit ms for aesthetics

        return String.format("%02d : %02d : %02d", minutes, seconds, millis);
    }


    public void draw(){
        // For drawing the game components
        // The elephant's width and height are 20 for drawing
        StdDraw.setFont(new Font("Arial", Font.PLAIN, 16)); //
        StdDraw.setPenColor(StdDraw.WHITE);
        StdDraw.setPenColor(new Color(56, 93, 172)); // Color of the area
        StdDraw.filledRectangle(400, 60, 400, 60); // Drawing timer area
        StdDraw.setPenColor(StdDraw.WHITE);
        StdDraw.text(250,85,"Help");
        StdDraw.rectangle(250,85,40,15); // Help button
        StdDraw.text(550,85,"Restart");
        StdDraw.rectangle(550,85,40,15); // Restart button
        StdDraw.text(400,20,"RESET THE GAME");
        StdDraw.rectangle(400,20,80,15); // Reset button
        StdDraw.text(700, 75, "Deaths: " + deathNumber);
        StdDraw.text(700, 50, "Stage: " + (getStageIndex()+1));
        StdDraw.text(100, 50,getFormattedElapsedTime(System.currentTimeMillis(),stageStartTime,totalPauseTime));
        StdDraw.text(100,75, "Level: 1");
        if (isMouseOverHelpButton()){
            StdDraw.text(400, 85, "Help:");
            StdDraw.text(400, 55, currentStage.getHelp());
        }
        else{
        StdDraw.text(400, 85, "Clue:");
        StdDraw.text(400, 55, currentStage.getClue());}
        player.draw();
        map.draw();

    }
    public void resetStage(){
        StdDraw.setPenColor(new Color(0, 102, 0)); // dark green
        StdDraw.filledRectangle(400, 300, 400, 60);
        StdDraw.setPenColor(Color.WHITE);
        StdDraw.setFont(new Font("Arial", Font.PLAIN, 30));
        StdDraw.text(400, 300, "RESETTING THE GAME");

    }



    public void passingStage(){
        StdDraw.setPenColor(new Color(0, 102, 0)); // dark green
        StdDraw.filledRectangle(400, 300, 400, 60);
        StdDraw.setPenColor(Color.WHITE);
        StdDraw.setFont(new Font("Arial", Font.PLAIN, 24));
        StdDraw.text(400, 320, "You passed the stage");
        StdDraw.text(400, 285, "But is the level over?!");
    }
    public void play() {
        //implement the input and interactions
        StdDraw.setCanvasSize(800, 600);
        StdDraw.setXscale(0, 800);
        StdDraw.setYscale(0, 600);
        StdDraw.enableDoubleBuffering();
        while(true){
            String currentTime= getFormattedElapsedTime(System.currentTimeMillis(),stageStartTime, totalPauseTime);
            map.setIsDoorOpen();
            map.doorFalling();
            handleInput(currentStage); //
            handleMouseClick();
            if (player.checkingSpike(player.getX(), player.getY())){
                player.respawn( new int[] {130,460});
                map.resetButtonPressNum();
                map.setDoorBack();
                setDeathNumber();
                StdDraw.pause(50); // 500 milliseconds = 0.5 seconds
            }
            player.move();
            draw();
            boolean quitGame=false;
            if (player.checkExit(player.getX(), player.getY())){
                draw();
                if (stageIndex<stages.size()-1){
                passingStage();}
                StdDraw.show();
                pauseStartTime = System.currentTimeMillis();
                StdDraw.pause(2000);
                totalPauseTime += System.currentTimeMillis() - pauseStartTime;
                stageIndex++;
                if (stageIndex<stages.size()) {
                    setCurrentStage();
                    player = new Player(130, 460, currentStage);
                    map = new Map(currentStage, player);
                }
                else {
                    while (true) {
                        StdDraw.clear();
                        StdDraw.setPenColor(new Color(0, 102, 0)); // dark green
                        StdDraw.filledRectangle(400, 300, 400, 60);
                        StdDraw.setPenColor(Color.WHITE);
                        StdDraw.setFont(new Font("Arial", Font.PLAIN, 24));
                        StdDraw.text(400, 320, "CONGRATULATIONS YOU FINISHED THE LEVEL");
                        StdDraw.text(400, 285, "PRESS A TO PLAY AGAIN");
                        StdDraw.setFont(new Font("Arial", Font.PLAIN, 12));
                        StdDraw.text(400, 255, "You finished with " +deathNumber +" deaths in "+currentTime);
                        StdDraw.show();
                        StdDraw.pause(16);
                        if (StdDraw.isKeyPressed(81)) {
                            quitGame=true;
                            break;
                        }
                            if (StdDraw.isKeyPressed(65)){
                                stageIndex = 0;
                                map.resetButtonPressNum();
                                setCurrentStage();
                                totalPauseTime=0;
                                player = new Player(130, 460, currentStage);
                                map = new Map(currentStage, player);
                                deathNumber = 0;
                                stageStartTime = System.currentTimeMillis(); // 🔄 reset the timer
                                break;
                        }
                    }
                }

            }
            if (quitGame){
                System.exit(0); // ✅ clean, immediate exit
            }
            StdDraw.show();
            StdDraw.pause(16);
            StdDraw.clear();
        }
    }
}