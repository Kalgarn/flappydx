package com.kalgarn.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;

/**
 * Created by Jerome on 19/01/2016.
 */
public class HighScore {

    private static final String preferenceKey = "prefs";
    private static final String scoreKey ="highscore";

    private static HighScore instance = null;
    private static int highscore;

    protected HighScore(){

    }

    public static HighScore getInstance(){
        if (instance == null){
            instance = new HighScore();
        }
        return instance;
    }

    // initialise toutes les données a sauvegarde
    public void load(){
        Preferences prefs = Gdx.app.getPreferences(preferenceKey);
        highscore = prefs.getInteger(scoreKey,0);
    }
    // sauvegarde dans Preferences de ligdx
    public void save(){
        Preferences preferences = Gdx.app.getPreferences(preferenceKey);
        preferences.putInteger(scoreKey, highscore);

        preferences.flush();
    }

    public static int getHighscore() {
        return highscore;
    }

    public static int setHighscore(int score) {
        HighScore.highscore = score;
        return highscore;
    }
}
