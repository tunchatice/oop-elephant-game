//Hatice Tunc
//202340081
public class Player {
    // Declaring a private field: private double x
    private double x;
    // Declaring a private field: private double y
    private double y;
    // Declaring a private field: private double width=20
    private double width=20;
    // Declaring a private field: private double height=20
    private double height=20;
    // Declaring a private field: private double velocityY
    private double velocityY;
    public enum Direction {RIGHT,LEFT,UP}
    // Declaring a private field: private Direction facing
    private Direction facing;
    // Declaring a private field: private Stage stage
    private Stage stage;

// Method: Player — defines game behavior
    public Player(double x,double y, Stage stage){
        this.x=x;
        this.y=y;
        this.facing = Direction.RIGHT; // default facing direction
        this.stage=stage;}
    // Method: setX — sets the x value
    public void  setX(double x) {
        this.x=x;}
    // Method: setFacing — sets the direction
    public void setFacing(Direction way){
        facing=way;}
    // Method: setY — sets the y value
    public void setY(double y) {
        this.y=y;}
    // Method: getX — gets the x value
    public double getX() {
        return x;}
    // Method: getY — gets the y value
    public double getY(){
        return y;}
    // Method: checkCollision — checks if there's a collision
    public boolean checkCollision(double x, double y){
        //setting the right edge
        double rightEdge=x+width/2;
        //setting the left edge
        double leftEdge=x-width/2;
        //setting the top edge
        double topEdge=y+height/2;
        //setting the bottom edge
        double bottomEdge=y-height/2;
        //initially set to false to check if any collision happened
        boolean isCollision=false;
        // Loop structure
        for (int[] obstacle:(Map.getObstacles())){
            double obstacleRight = obstacle[2];
            double obstacleLeft = obstacle[0];
            double obstacleTop = obstacle[3];
            double obstacleBottom = obstacle[1];
            // Conditional check, if true there will be a collision
            if (rightEdge>obstacleLeft && leftEdge<obstacleRight&& topEdge>obstacleBottom&&bottomEdge<obstacleTop) {
                isCollision=true;
            }
        }

        return isCollision;
    }
// method checkExit, checks if the player succesfully entered the exit pipe
    public boolean checkExit(double x, double y){
        //setting the right edge
        double rightEdge = x + width / 2;
        //setting the left edge
        double leftEdge = x - width / 2;
        //setting the top edge
        double topEdge = y + height / 2;
        //setting the bottom edge
        double bottomEdge = y - height / 2;
        //initially set to false to check if exited
        boolean exit=false;
        //getting the exit pipes coordinates
        int[] exitPipe= Map.getExitPipe();
        //setting the x y values
        //setting the x2 value
        double pipeRight = exitPipe[2];
        //setting the x1 value
        double pipeLeft = exitPipe[0];
        //setting the y2 value
        double pipeTop = exitPipe[3];
        //setting the y1 value
        double pipeBottom = exitPipe[1];
        //check the condition if true exit
        if (rightEdge-5 > pipeLeft && leftEdge < pipeRight && topEdge > pipeBottom && bottomEdge < pipeTop) {
            exit = true;
        }
        return exit;
    }

    public boolean isOnGround() {
        return checkCollision(getX(), getY()-1); // check 1px below the player
    }
    public boolean checkButtonPressed(double x, double y) {
        double rightEdge = x + width / 2;
        double leftEdge = x - width / 2;
        double topEdge = y + height / 2;
        double bottomEdge = y - height / 2;
        boolean isButtonPressed = false;
        int[] button = Map.getButton();
        double buttonRight = button[2];
        double buttonLeft = button[0];
        double buttonTop = button[3];
        double buttonBottom = button[1];
        if (rightEdge > buttonLeft && leftEdge < buttonRight && topEdge > buttonBottom && bottomEdge < buttonTop) {
            isButtonPressed = true;
        }
        return isButtonPressed;
    }

    public boolean checkingSpike(double x, double y){
        //setting the right edge
        double rightEdge = x + width / 2;
        //setting the left edge
        double leftEdge = x - width / 2;
        //setting the top edge
        double topEdge = y + height / 2;
        //setting the bottom edge
        double bottomEdge = y - height / 2;
        //initially set to 0
        boolean isCollision=false;
        //loop structure
        for (int[] spike:(Map.getSpikes())){
            //setting the right edge
            double spikeRight = spike[2];
            //setting the left edge
            double spikeLeft = spike[0];
            //setting the top edge
            double spikeTop = spike[3];
            //setting the bottom edge
            double spikeBottom = spike[1];
            //checking the condition
            if (rightEdge>spikeLeft && leftEdge<spikeRight&& topEdge>spikeBottom&&bottomEdge<spikeTop) {
                isCollision=true;
            }
        }
        return isCollision;
    }


    public void move(){
        //special condition for the 3d stage
        if (stage.getStageNumber()==2) {
            velocityY += stage.getGravity();
            double nextY = getY() + velocityY;

            if (!checkCollision(getX(), nextY)) {
                // No collision, continue movement
                setY(nextY);
            } else {
                // Collision detected, bounce properly
                // When we hit something, invert the velocity
                velocityY = -velocityY ; //rebounce

                // Apply a small displacement to prevent getting stuck in collision
                if (velocityY > 0) {
                    setY(getY() + 1); // Move slightly up
                } else {
                    setY(getY() - 1); // Move slightly down
                }
            }
        }
        else {
            //other cases
            //getting slower in each frame
            velocityY += stage.getGravity();
            //if conditions hold move accordingly
            if (!(checkCollision(getX(), getY() + velocityY))) {
                setY(getY() + velocityY);
            } else {
                //else stop since it hit
                velocityY = 0;
            }
        }
    }
    public void move(Direction way ){
        if (way==Direction.RIGHT&&(!checkCollision((getX()+ stage.getVelocityX()),getY()))){
            setX((getX()+ stage.getVelocityX()));
        }
        if (way==Direction.LEFT &&(!checkCollision((getX()- stage.getVelocityX()),getY()))){
            setX(getX()- stage.getVelocityX());
        }
        if (way==Direction.UP&& isOnGround()){
            velocityY= stage.getVelocityY();
        }
    }
    //method respawn
    //if reset or died, respawn
    public void respawn(int[] spawnPoint) {
        //set the velocityy to 0
        velocityY=0;
        //set the facing to initial value
        setFacing(Direction.RIGHT);
        //spawn according to default x y values
        this.x=spawnPoint[0];
        this.y=spawnPoint[1];

    }//Respawns the player.
    //method draw
    //drawing the elephant
    public void draw(){
        //check which direction the elephant is moving
        if(facing==Direction.RIGHT){
            StdDraw.picture(x, y, "misc/ElephantRight.png", width, height);}
        if(facing==Direction.LEFT){
            StdDraw.picture(x, y, "misc/ElephantLeft.png", width, height);}
    }
}
