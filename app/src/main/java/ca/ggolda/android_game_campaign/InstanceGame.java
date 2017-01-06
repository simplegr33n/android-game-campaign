package ca.ggolda.android_game_campaign;

/**
 * Created by gcgol on 11/01/2016.
 */
public class InstanceGame {


    private String mMatch_id;
    private String mBoard;
    private String mGameset;
    private String mRed;
    private String mBlue;
    private String mTurn_color;
    private String mUsername_red;
    private String mUsername_blue;
    private Long mLast_play;



    // Required for firebase
    public InstanceGame() {
    }

    // Constructor for InstanceGame with all attributes
    public InstanceGame(String match_id, String board, String gameset, String red, String blue, String turn_color, String username_red, String username_blue, Long last_play) {
        mMatch_id = match_id;
        mBoard = board;
        mGameset = gameset;
        mRed = red;
        mBlue = blue;
        mTurn_color = turn_color;
        mUsername_red = username_red;
        mUsername_blue = username_blue;
        mLast_play = last_play;

    }


    public String getMatch_id() {
        return mMatch_id;
    }
    public void setMatch_id(String mMatch_id) {
        this.mMatch_id = mMatch_id;
    }

    public String getBoard() {
        return mBoard;
    }
    public void setBoard(String mBoard) {
        this.mBoard = mBoard;
    }

    public String getGameset() {
        return mGameset;
    }
    public void setGameset(String mGameset) {
        this.mGameset = mGameset;
    }

    public String getRed() {
        return mRed;
    }
    public void setRed(String mRed) {
        this.mRed = mRed;
    }

    public String getBlue() {
        return mBlue;
    }
    public void setBlue(String mBlue) {
        this.mBlue = mBlue;
    }

    public String getTurn_color() {
        return mTurn_color;
    }
    public void setTurn_color(String mTurn_color) {
        this.mTurn_color = mTurn_color;
    }

    public String getUsername_red() {
        return mUsername_red;
    }
    public void setUsername_red(String mUsername_red) {
        this.mUsername_red = mUsername_red;
    }

    public String getUsername_blue() {
        return mUsername_blue;
    }
    public void setUsername_blue(String mUsername_blue) {
        this.mUsername_blue = mUsername_blue;
    }

    public Long getLast_play() {
        return mLast_play;
    }
    public void setLast_play(Long mLast_play) {
        this.mLast_play = mLast_play;
    }


}