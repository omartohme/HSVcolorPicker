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

/**
 * The Controller for RGBAModel.
 * <p>
 * As the Controller:
 * a) event handler for the View
 * b) observer of the Model (RGBAModel)
 * <p>
 * Features the Update / React Strategy.
 *
 * @author tohm0011@AlgonquinCollege.com
 * @version 1.0
 */
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

        // Instantiate a new AboutDialogFragment()
        // but do not show it (yet)
        mAboutDialog = new AboutDialogFragment();

        // Instantiate a new RGBA model
        // Initialize the model red (max), green (min), blue (min), and alpha (max)
        mModel = new HSVModel();
        mModel.setHue(HSVModel.MAX_HUE);
        mModel.setSaturation(HSVModel.MIN_SATURATION);
        mModel.setValue(HSVModel.MIN_VALUE);
        // The Model is observing this Controller (class MainActivity implements Observer)
        mModel.addObserver(this);

        // reference each View
        mColorSwatch = (TextView) findViewById(R.id.colorSwatch);
        mHueSB = (SeekBar) findViewById(R.id.hueSB);
        mSaturationSB = (SeekBar) findViewById(R.id.saturationSB);
        mValueSB = (SeekBar) findViewById(R.id.valueSB);
        mHueText = (TextView) findViewById(R.id.hue);
        mSaturationText = (TextView) findViewById(R.id.saturation);
        mValueText = (TextView) findViewById(R.id.value);
        //TODO: reference the remaining <SeekBar>s: green, blue and alpha

        // set the domain (i.e. max) for each component
        mHueSB.setMax((int) HSVModel.MAX_HUE);
        mSaturationSB.setMax((int) HSVModel.MAX_SATURATION);
        mValueSB.setMax((int) HSVModel.MAX_VALUE);
        //TODO: setMax() for the remaining <SeekBar>s: green, blue and alpha

        // register the event handler for each <SeekBar>
        mHueSB.setOnSeekBarChangeListener(this);
        mSaturationSB.setOnSeekBarChangeListener(this);
        mValueSB.setOnSeekBarChangeListener(this);
        //TODO: register the remaining <SeekBar>s: green, blue and alpha

        // initialize the View to the values of the Model
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

    /**
     * Event handler for the <SeekBar>s: red, green, blue, and alpha.
     */
    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

        // Did the user cause this event?
        // YES > continue
        // NO  > leave this method
        if (fromUser == false) {
            return;
        }

        // Determine which <SeekBark> caused the event (switch + case)
        // GET the SeekBar's progress, and SET the model to it's new value
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
        //GET the model's red value, and SET the red <SeekBar>
        mHueSB.setProgress((int) mModel.getHue());
        mHueText.setText("Hue: " + mModel.getHue());
    }

    // synchronize each View component with the Model
    public void updateView() {
        this.updateColorSwatch();
        this.updateHueSB();
        this.updateSaturationSB();
        this.updateValueSB();
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        if (id == R.id.action_about) {
            DialogFragment newFragment = new AboutDialogFragment();
            newFragment.show(getFragmentManager(), ABOUT_DIALOG_TAG);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
