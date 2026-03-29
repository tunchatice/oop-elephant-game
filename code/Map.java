//Hatice Tunc
//202340081
public class Map {
// Declares a private field: private Stage stage
    private Stage stage;
    // Declares a private field: private Player player
    private Player player;
    private static int[][] obstacles = {
            new int[]{0, 120, 120, 270}, new int[]{0, 270, 168, 330},
            new int[]{0, 330, 30, 480}, new int[]{0, 480, 180, 600},
            new int[]{180, 570, 680, 600}, new int[]{270, 540, 300, 570},
            new int[]{590, 540, 620, 570}, new int[]{680, 510, 800, 600},
            new int[]{710, 450, 800, 510}, new int[]{740, 420, 800, 450},
            new int[]{770, 300, 800, 420}, new int[]{680, 240, 800, 300},
            new int[]{680, 300, 710, 330}, new int[]{770, 180, 800, 240},
            new int[]{0, 120, 800, 150}, new int[]{560, 150, 800, 180},
            new int[]{530, 180, 590, 210}, new int[]{530, 210, 560, 240},
            new int[]{320, 150, 440, 210}, new int[]{350, 210, 440, 270},
            new int[]{220, 270, 310, 300}, new int[]{360, 360, 480, 390},
            new int[]{530, 310, 590, 340}, new int[]{560, 400, 620, 430}};
    // Declares a private field: private static int[] button = new int[]{400, 390, 470, 410}
    private static int[] button = new int[]{400, 390, 470, 410};
    // Declares a private field: private int[] buttonFloor = new int[]{400, 390, 470, 400}
    private int[] buttonFloor = new int[]{400, 390, 470, 400};
    private int[][] startPipe = {new int[]{115, 450, 145, 480},
            new int[]{110, 430, 150, 450}};
    private static int[][] exitPipe = {new int[]{720, 175, 740, 215},
            new int[]{740, 180, 770, 210}};
    private static int[][] spikes = {
            new int[]{30, 333, 50, 423}, new int[]{121, 150, 207, 170},
            new int[]{441, 150, 557, 170}, new int[]{591, 180, 621, 200},
            new int[]{750, 301, 769, 419}, new int[]{680, 490, 710, 510},
            new int[]{401, 550, 521, 570}};
    // Declares a private field: private int[] door = new int[]{685, 180, 700, 240}
    private int[] door = new int[]{685, 180, 700, 240};
    // Declares a private field: private int[] timerArea = new int[]{0, 0, 800, 120}
    // Timer Area (Blue Area at the Bottom)
    private int[] timerArea = new int[]{0, 0, 800, 120};
    // Declares a private field: private int buttonPressNum = 0
    private int buttonPressNum = 0;
    // Declares a private field: private boolean isDoorOpen = false
    private boolean isDoorOpen = false;
    // Declares a private field: private boolean buttonPreviouslyPressed = false
    private boolean buttonPreviouslyPressed = false;

    // Method: setIsDoorOpen — sets the door open if necessary
    public void setIsDoorOpen() {
        boolean isPressedNow = player.checkButtonPressed(player.getX(), player.getY());
        // Only increment if it's newly pressed
        // Conditional check
        if (stage.getStageNumber() == 3) {
            if (isPressedNow && !buttonPreviouslyPressed) {
                setButtonPressNum();
            }
        }

        // Logic for door opening
        // Conditional check
        if (stage.getStageNumber() != 3 && isPressedNow) {
                isDoorOpen = true;}
        // Conditional check
        if (stage.getStageNumber() == 3 && buttonPressNum >= 5) {
            isDoorOpen = true;}
        buttonPreviouslyPressed = isPressedNow;
    }
    // Method: setDoorBack —redraws the door
    public void setDoorBack(){
        doorY=getDoor()[3];
        isDoorOpen=false;
    }

    public void setButtonPressNum() {
        buttonPressNum++;
    }
    public void resetButtonPressNum() {
        buttonPressNum=0;
    }

    public int[] getDoor() {
        return door;
    }
    private double doorY=getDoor()[3];

    public void doorFalling() {
        if (isDoorOpen && (!(doorY == getDoor()[1]))) {
            doorY -= 2.5;}
        }


    public Map(Stage stage, Player player){
        this.stage=stage;
        this.player=player;
    }

    public static int[] getExitPipe(){
        return exitPipe[1];
    }
    public static int[][] getSpikes(){
        return spikes;}
    public static int[][] getObstacles() {
        return obstacles;
    }
    public static int[] getButton(){
        return button;}


    public boolean checkDoorCollision(double x, double y){
        int width=20;
        int height=20;
        double rightEdge=x+width/2;
        double leftEdge=x-width/2;
        double topEdge=y+height/2;
        double bottomEdge=y-height/2;
        boolean isCollision=false;
        double doorRight = door[2];
        double doorLeft = door[0];
        double doorTop = doorY;
        double doorBottom = door[1];
        if (rightEdge>doorLeft && leftEdge<doorRight&& topEdge>doorBottom&&bottomEdge<doorTop) {
            isCollision=true;
        }
        return isCollision;
    }

    public void draw(){
        StdDraw.setPenColor(stage.getColor());
        // Loop structure
        for (int[] obstacle : obstacles) {
            int x1 = obstacle[0];
            int y1 = obstacle[1];
            int x2 = obstacle[2];
            int y2 = obstacle[3];
            double centerX = (x1 + x2) / 2.0;
            double centerY = (y1 + y2) / 2.0;
            double halfWidth = Math.abs(x2 - x1) / 2.0;
            double halfHeight = Math.abs(y2 - y1) / 2.0;
            StdDraw.filledRectangle(centerX, centerY, halfWidth, halfHeight);
        }
        StdDraw.setPenColor(StdDraw.GREEN);
        StdDraw.filledRectangle((getDoor()[0]+getDoor()[2])/2, (getDoor()[1]+doorY)/2, Math.abs((getDoor()[2]-getDoor()[0])/2), Math.abs((doorY-getDoor()[1])/2));

        StdDraw.setPenColor(253, 197, 53);
        StdDraw.filledRectangle((double) (startPipe[0][0] + startPipe[0][2])/2, (double) (startPipe[0][1] + startPipe[0][3])/2,
                Math.abs((double) (startPipe[0][2] - startPipe[0][0])/2),  Math.abs((double) (startPipe[0][3] - startPipe[0][1])/2));

        StdDraw.filledRectangle((double) (startPipe[1][0] + startPipe[1][2])/2, (double) (startPipe[1][1] + startPipe[1][3])/2,
                Math.abs((double) (startPipe[1][2] - startPipe[1][0])/2),  Math.abs((double) (startPipe[1][3] - startPipe[1][1])/2));


        StdDraw.filledRectangle((double) (exitPipe[0][0] + exitPipe[0][2])/2, (double) (exitPipe[0][1] + exitPipe[0][3])/2,
                Math.abs((double) (exitPipe[0][2] - exitPipe[0][0])/2),  Math.abs((double) (exitPipe[0][3] - exitPipe[0][1])/2));

        StdDraw.filledRectangle((double) (exitPipe[1][0] + exitPipe[1][2])/2, (double) (exitPipe[1][1] + exitPipe[1][3])/2,
                Math.abs((double) (exitPipe[1][2] - exitPipe[1][0])/2),  Math.abs((double) (exitPipe[1][3] - exitPipe[1][1])/2));


        if (!(player.checkButtonPressed(player.getX(),player.getY()))){
        StdDraw.setPenColor(214, 45, 32);
        StdDraw.filledRectangle((double) (button[0] + button[2])/2, (double) (button[1] + button[3])/2,
                Math.abs(button[2] - button[0]) / 2.0,  Math.abs(button[3] - button[1]) / 2.0);}

        StdDraw.setPenColor(60, 60, 60);
        StdDraw.filledRectangle((double) (buttonFloor[0] + buttonFloor[2])/2, (double) (buttonFloor[1] + buttonFloor[3])/2,
                Math.abs(buttonFloor[2] - buttonFloor[0]) / 2.0,  Math.abs(buttonFloor[3] - buttonFloor[1]) / 2.0);






        for (int i=0; i<7; i++) {
            int x1 = spikes[i][0];
            int y1 = spikes[i][1];
            int x2 = spikes[i][2];
            int y2 = spikes[i][3];
            double centerX = (x1 + x2) / 2.0;
            double centerY = (y1 + y2) / 2.0;
            double width = Math.abs(x2 - x1);
            double height = Math.abs(y2 - y1);
            if (i==1||i==2||i==3){
            StdDraw.picture(centerX, centerY, "misc/Spikes.png",
                    width, height);}
            if (i==5||i==6){
                StdDraw.picture(centerX, centerY, "misc/Spikesfacingdownward.png" ,
                        width, height);}
            if (i==0){
                StdDraw.picture(centerX, centerY, "misc/Spikesfacingright.png" ,
                        width, height);}
            if (i==4){
                StdDraw.picture(centerX, centerY,"misc/Spikesfacingleft.png",
                        width, height);}
        }

    }
}
