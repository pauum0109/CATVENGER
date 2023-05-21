package GameState;

public class GameStateManager{
    private GameState[] gameStates;
    private int currentState;
	public static final int NUMGAMESTATES = 4;
    public static final int MENUSTATE = 0;
    public static final int LEVELONESTATE = 1;
    public static final int WINSTATE = 2;
    public static final int DIESTATE = 3;

    public GameStateManager(){
        gameStates = new GameState[NUMGAMESTATES];
        currentState = MENUSTATE;
        loadState(currentState);
    }
    private void loadState(int state){
		if(state == MENUSTATE) gameStates[state] = new MenuState(this);
		else if(state == LEVELONESTATE) gameStates[state] = new LevelOneState(this);
		else if(state == WINSTATE) gameStates[state] = new WinState(this);
		else if(state == DIESTATE) gameStates[state] = new DieState(this);

	}
    private void unloadState(int state){
		gameStates[state] = null;
	}
    public void setState(int state){
		unloadState(currentState);
		currentState = state;
		loadState(currentState);
	}
    public void update(){
		try {
			gameStates[currentState].update();
		} 
        catch (Exception e){

        }
	}
    public void draw(java.awt.Graphics2D g){
		try {
			gameStates[currentState].draw(g);
		} 
        catch(Exception e){
			
        }
	}
	public void keyPressed(int k){
		gameStates[currentState].keyPressed(k);
	}
	public void keyReleased(int k){
		gameStates[currentState].keyReleased(k);
	}
}
