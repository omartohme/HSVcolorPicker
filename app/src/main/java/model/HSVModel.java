package model;

import java.util.Observable;

import android.graphics.Color;


public class HSVModel extends Observable {

    // CLASS VARIABLES
    public static final float MAX_HUE = 360.0f;
    public static final float MIN_HUE = 0.0f;
    public static final float MAX_VALUE = 100.0f;
    public static final float MIN_VALUE = 0.0f;
    public static final float MAX_SATURATION = 100.0f;
    public static final float MIN_SATURATION = 0.0f;


    // INSTANCE VARIABLES

    private float saturation;
    private float value;
    private float hue;


    public HSVModel() {
        this(MAX_HUE, MAX_SATURATION, MAX_VALUE);
    }


    public HSVModel(float hue, float value, float saturation) {
        super();

        this.saturation = saturation;
        this.value = value;
        this.hue = hue;
    }

    public void asBlack() {
        this.setHSV(0.0f, 0.0f, 0.0f);
    }

    public void asRed() {
        this.setHSV(0.0f, 1.0f, 1.0f);
    }

    public void asLime() {
        this.setHSV(120.0f, 1.0f, 1.0f);
    }

    public void asBlue() {
        this.setHSV(240.0f, 1.0f, 1.0f);
    }

    public void asYellow() {
        this.setHSV(60.0f, 1.0f, 1.0f);
    }

    public void asCyan() {
        this.setHSV(180.0f, 1.0f, 1.0f);
    }

    public void asMagenta() {
        this.setHSV(300.0f, 1.0f, 1.0f);
    }

    public void asSilver() {
        this.setHSV(0.0f, 0.0f, 0.75f);
    }

    public void asGray() {
        this.setHSV(0.0f, 0.0f, 0.5f);
    }

    public void asMaroon() {
        this.setHSV(0.0f, 1.0f, 0.5f);
    }

    public void asOlive() {
        this.setHSV(60.0f, 1.0f, 0.5f);
    }

    public void asGreen() {
        this.setHSV(120.0f, 1.0f, 0.5f);
    }

    public void asPurple() {
        this.setHSV(300.0f, 1.0f, 0.5f);
    }

    public void asTeal() {
        this.setHSV(180.0f, 1.0f, 0.5f);
    }

    public void asNavy() {
        this.setHSV(240.0f, 1.0f, 0.5f);
    }

    public void asWhite() {
        this.setHSV(0.0f, 0.0f, 1.0f);
    }

    // GETTERS
    public float getSaturation() {
        return saturation;
    }

    public float getValue() {
        return value;
    }

    public float getHue() {
        return hue;
    }

    // SETTERS


    public void setSaturation(float saturation) {
        this.saturation = saturation;

        this.updateObservers();
    }

    public void setValue(float value) {
        this.value = value;

        this.updateObservers();
    }

    public void setHue(float hue) {
        this.hue = hue;

        this.updateObservers();
    }

    public void setHSV(float hue, float saturation, float value) {
        this.hue = hue;
        this.value = value;
        this.saturation = saturation;

        this.updateObservers();
    }

    private void updateObservers() {
        this.setChanged();
        this.notifyObservers();
    }

}