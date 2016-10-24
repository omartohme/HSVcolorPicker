/**
 *  Purpose/Description of your app
 *  @author Omar Tohme(tohm0011@algonquinlive.com)
 */

package com.example.omar.hsvcolorpicker;

import android.app.DialogFragment;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;
import android.view.View;

import java.util.Observable;
import java.util.Observer;

import model.HSVModel;


public class MainActivity extends AppCompatActivity implements Observer
        , SeekBar.OnSeekBarChangeListener {
    // CLASS VARIABLES
    private static final String ABOUT_DIALOG_TAG = "About";
    private static final String LOG_TAG = "HSV";

    // INSTANCE VARIABLES
    // Pro-tip: different naming style; the 'm' means 'member'
    private AboutDialogFragment mAboutDialog;
    private TextView mColorSwatch;
    private HSVModel mModel;
    private SeekBar mHueSB;
    private SeekBar mSaturationSB;
    private SeekBar mValueSB;

    private TextView mHueText;
    private TextView mSaturationText;
    private TextView mValueText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        mAboutDialog = new AboutDialogFragment();


        mModel = new HSVModel();
        mModel.setHue(HSVModel.MAX_HUE);
        mModel.setSaturation(HSVModel.MIN_SATURATION);
        mModel.setValue(HSVModel.MIN_VALUE);

        mModel.addObserver(this);


        mColorSwatch = (TextView) findViewById(R.id.colorSwatch);
        mHueSB = (SeekBar) findViewById(R.id.hueSB);
        mSaturationSB = (SeekBar) findViewById(R.id.saturationSB);
        mValueSB = (SeekBar) findViewById(R.id.valueSB);
        mHueText = (TextView) findViewById(R.id.hue);
        mSaturationText = (TextView) findViewById(R.id.saturation);
        mValueText = (TextView) findViewById(R.id.value);



        mHueSB.setMax((int) HSVModel.MAX_HUE);
        mSaturationSB.setMax((int) HSVModel.MAX_SATURATION);
        mValueSB.setMax((int) HSVModel.MAX_VALUE);



        mHueSB.setOnSeekBarChangeListener(this);
        mSaturationSB.setOnSeekBarChangeListener(this);
        mValueSB.setOnSeekBarChangeListener(this);



        this.updateView();
    }


    public boolean buttonPress(View button) {
        // Handle presses on the action bar items
        switch (button.getId()) {
            case R.id.redPress:
                mModel.asRed();
                showToast();
                return true;
            case R.id.greenPress:
                mModel.asGreen();
                showToast();
                return true;
            case R.id.bluePress:
                mModel.asBlue();
                showToast();
                return true;
            case R.id.yellowPress:
                mModel.asYellow();
                return true;
            case R.id.magentaPress:
                mModel.asMagenta();
                return true;
            case R.id.whitePress:
                mModel.asWhite();
                return true;
            case R.id.cyanPress:
                mModel.asCyan();
                return true;
            case R.id.blackPress:
                mModel.asBlack();
                return true;
            case R.id.silverPress:
                mModel.asSilver();
                showToast();
                return true;
            case R.id.olivePress:
                mModel.asOlive();
                return true;
            case R.id.grayPress:
                mModel.asGray();
                showToast();
                return true;
            case R.id.navyPress:
                mModel.asNavy();
                return true;
            case R.id.maroonPress:
                mModel.asMaroon();
                return true;
            case R.id.tealPress:
                mModel.asTeal();
                return true;
            case R.id.purplePress:
                mModel.asPurple();
                return true;
            case R.id.limePress:
                mModel.asLime();
                return true;
        }

        return false;

    }

    private void showToast() {
        Log.e("TAG", mModel.getHue() + " S " + mModel.getSaturation() + " v " + mModel.getValue());
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {


        if (fromUser == false) {
            return;
        }


        switch (seekBar.getId()) {
            case R.id.hueSB:
                mModel.setHue(mHueSB.getProgress());
                break;
            case R.id.saturationSB:
                mModel.setSaturation((float) mSaturationSB.getProgress() / 100);
                break;
            case R.id.valueSB:
                mModel.setValue((float) mValueSB.getProgress() / 100);
                break;
        }
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void update(Observable observable, Object data) {
        this.updateView();
    }

    private void updateSaturationSB() {
        int tSat=(int)(mModel.getSaturation() * 100);
        mSaturationSB.setProgress(tSat);
        mSaturationText.setText("Saturation: " +tSat );
    }

    private void updateColorSwatch() {
        mColorSwatch.setBackgroundColor(Color.HSVToColor(new float[]{mModel.getHue()
                , mModel.getSaturation()
                , mModel.getValue()}));
    }

    private void updateValueSB() {
        int tValue=(int)(mModel.getValue() * 100);
        mValueText.setText("Value: " + tValue);
    }

    private void updateHueSB() {
        mHueSB.setProgress((int) mModel.getHue());
        mHueText.setText("Hue: " + mModel.getHue());
    }


    public void updateView() {
        this.updateColorSwatch();
        this.updateHueSB();
        this.updateSaturationSB();
        this.updateValueSB();
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        if (id == R.id.action_about) {
            DialogFragment newFragment = new AboutDialogFragment();
            newFragment.show(getFragmentManager(), ABOUT_DIALOG_TAG);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
