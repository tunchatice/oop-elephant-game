//202340081
import java.awt.*;
import java.util.Random;

public class Stage {
    private int stageNumber;
    private double gravity;
    private double velocityX;
    private double velocityY;
    private int rightCode;
    private int leftCode;
    private int upCode;
    private String clue;
    private String help;

    public Stage(double gravity, double velocityX, double velocityY, int stageNumber,
                 int rightCode, int leftCode, int upCode, String clue, String help) {
        this.gravity = gravity;
        this.velocityX = velocityX;
        this.velocityY = velocityY;
        this.stageNumber = stageNumber;
        this.rightCode = rightCode;
        this.leftCode = leftCode;
        this.upCode = upCode;
        this.clue = clue;
        this.help = help;
    }
    public int getStageNumber(){
        return stageNumber;
    };
    public static Color setcolor(){
        Random random = new Random();
        int red = random.nextInt(256); // Generates 0-255
        int green = random.nextInt(256); // Generates 0-255
        int blue = random.nextInt(256); // Generates 0-255
        Color randomcolor = new Color(red, green, blue);
        return randomcolor;}

    private Color color=setcolor();


    public double getGravity() {
        return gravity;
    };
    public double getVelocityX() {
        return velocityX;
    };
    public double getVelocityY() {
        return velocityY;
    }
    public int[] getKeyCodes() {
        return new int[]{rightCode,leftCode,upCode};
    }
    public String getClue(){
        return clue;
    }
    public String getHelp(){
        return help;
    }
    public Color getColor(){
        return color;
    }
}

