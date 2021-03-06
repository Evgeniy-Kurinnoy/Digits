package evgeniy.kurinnoy.digits;

import android.content.DialogInterface;
import android.graphics.Color;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ScrollView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    private ArrayList<TextView> number = new ArrayList<>(9);
    private ArrayList<TextView> resNumber = new ArrayList<>(9);
    private CheckBox checkBox;
    private TextView steps;
    private TextView stepsDetail;
    private ScrollView scroll;
    private int difficulty = 0;
    private int stepCount = 0;
    private boolean detailVisible = false;
    private boolean showDialog = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        number.add((TextView)findViewById(R.id.number1));
        number.add((TextView)findViewById(R.id.number2));
        number.add((TextView)findViewById(R.id.number3));
        number.add((TextView)findViewById(R.id.number4));
        number.add((TextView)findViewById(R.id.number5));
        number.add((TextView)findViewById(R.id.number6));
        number.add((TextView)findViewById(R.id.number7));
        number.add((TextView)findViewById(R.id.number8));
        number.add((TextView)findViewById(R.id.number9));
        resNumber.add((TextView)findViewById(R.id.resNumber1));
        resNumber.add((TextView)findViewById(R.id.resNumber2));
        resNumber.add((TextView)findViewById(R.id.resNumber3));
        resNumber.add((TextView)findViewById(R.id.resNumber4));
        resNumber.add((TextView)findViewById(R.id.resNumber5));
        resNumber.add((TextView)findViewById(R.id.resNumber6));
        resNumber.add((TextView)findViewById(R.id.resNumber7));
        resNumber.add((TextView)findViewById(R.id.resNumber8));
        resNumber.add((TextView)findViewById(R.id.resNumber9));
        steps = (TextView)findViewById(R.id.steps);
        checkBox = (CheckBox) findViewById(R.id.difficulty);
        stepsDetail = (TextView)findViewById(R.id.stepsDetail);
        scroll = (ScrollView) findViewById(R.id.scrollView2);
        scroll.setVisibility(View.INVISIBLE);
        steps.setClickable(true);
        steps.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                detailVisible = !detailVisible;
                if (detailVisible)
                    scroll.setVisibility(View.VISIBLE);
                else
                    scroll.setVisibility(View.INVISIBLE);
            }
        });
        if (savedInstanceState == null) {
            if (checkBox.isChecked())
                difficulty = 1;
            else
                difficulty = 0;
            generateNum(number);
            generateNum(resNumber);
            checkProgress(showDialog);
            steps.setText(R.string.stepsNum);
            steps.setText(steps.getText() + " 0");
            for (int i = 0; i < number.size(); i++){
                stepsDetail.setText(stepsDetail.getText() + number.get(i).getText().toString());
            }
        }
    }

    public void buttonUpListener(View v){
        switch (v.getId()){
            case R.id.buttonUP1:
                numberUP(0);
                break;
            case R.id.buttonUP2:
                numberUP(1);
                break;
            case R.id.buttonUP3:
                numberUP(2);
                break;
            case R.id.buttonUP4:
                numberUP(3);
                break;
            case R.id.buttonUP5:
                numberUP(4);
                break;
            case R.id.buttonUP6:
                numberUP(5);
                break;
            case R.id.buttonUP7:
                numberUP(6);
                break;
            case R.id.buttonUP8:
                numberUP(7);
                break;
            case R.id.buttonUP9:
                numberUP(8);
                break;
        }
    }
    public void buttonDownListener(View v){
        switch (v.getId()){
            case R.id.buttonDOWN1:
                numberDOWN(0);
                break;
            case R.id.buttonDOWN2:
                numberDOWN(1);
                break;
            case R.id.buttonDOWN3:
                numberDOWN(2);
                break;
            case R.id.buttonDOWN4:
                numberDOWN(3);
                break;
            case R.id.buttonDOWN5:
                numberDOWN(4);
                break;
            case R.id.buttonDOWN6:
                numberDOWN(5);
                break;
            case R.id.buttonDOWN7:
                numberDOWN(6);
                break;
            case R.id.buttonDOWN8:
                numberDOWN(7);
                break;
            case R.id.buttonDOWN9:
                numberDOWN(8);
                break;
        }
    }
    public void newGame(View v){
        showDialog = true;
        if (checkBox.isChecked())
            difficulty =1;
        else
            difficulty = 0;
        generateNum(number);
        generateNum(resNumber);
        checkProgress(showDialog);
        stepCount = 0;
        steps.setText("");
        stepsDetail.setText("");
        for (int i = 0; i < number.size(); i++){
            stepsDetail.setText(stepsDetail.getText() + number.get(i).getText().toString());
        }
        steps.setText(R.string.stepsNum);
        steps.setText(steps.getText() + " 0");
    }
    private void generateNum(ArrayList<TextView> tv){
        final Random random = new Random();
        int minNum, maxNum;
        if (difficulty == 0 ){
            minNum = 0;
            maxNum = 10;
        } else{
            minNum = 1;
            maxNum = 9;
        }
        for(int i = 0; i < tv.size(); i++){
            tv.get(i).setText("" + (random.nextInt(maxNum) + minNum));
        }
    }
    private void checkProgress(boolean showDialog1){
        int count = 0;
        for (int i=0; i < number.size(); i++){
            if (number.get(i).getText().equals(resNumber.get(i).getText())) {
                resNumber.get(i).setTextColor(Color.parseColor("#1c8c27"));
                count++;
            }
             else
                resNumber.get(i).setTextColor(Color.parseColor("#871c1c"));
        }
        if (showDialog1)
        if (count == 9){
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle(R.string.congratulationTitle)
                    .setMessage(R.string.congratulation)
                    .setCancelable(false)
                    .setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            showDialog = false;
                            dialogInterface.cancel();
                        }
                    })
                    .setPositiveButton(R.string.tryAgain, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                           newGame(scroll);
                        }
                    });
            AlertDialog alert = builder.create();
            alert.show();
        }
        else {
            count = 0;
            for (int i = 0; i < number.size(); i++) {
                if (number.get(i).getText().equals(""+(i+1)))
                    count++;
            }
            if (count == 9){
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setTitle(R.string.loseTitle)
                        .setCancelable(false)
                        .setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                showDialog = false;
                                dialogInterface.cancel();
                            }
                        })
                        .setPositiveButton(R.string.tryAgain, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                newGame(scroll);
                            }
                        });
                AlertDialog alert = builder.create();
                alert.show();
            }
        }

    }
    private void numberUP(int index){
        showDialog = true;
        int currentDigit = Integer.parseInt(number.get(index).getText().toString());
        if (currentDigit == 9)
            number.get(index).setText("" + difficulty);
        else
            number.get(index).setText("" + ((currentDigit + 1))%10);
        try {
            TextView tv = number.get(currentDigit - 1);
            if (tv.getText().equals(""+difficulty))
                tv.setText("9");
            else
                tv.setText("" + (Integer.parseInt(tv.getText().toString()) - 1));
        } catch (IndexOutOfBoundsException e){

        }
        if (currentDigit != (index + 1))
            showStep();
        checkProgress(showDialog);
    }
    private void numberDOWN(int index){
        showDialog = true;
        int currentDigit = Integer.parseInt(number.get(index).getText().toString());

        if (currentDigit == difficulty)
            number.get(index).setText("" + 9);
        else
            number.get(index).setText("" + ((currentDigit - 1)));
        try {
            TextView tv = number.get(currentDigit - 1);
            if (tv.getText().equals(""+9))
                tv.setText(""+difficulty);
            else
                tv.setText("" + ((Integer.parseInt(tv.getText().toString()) + 1))%10);
        } catch (IndexOutOfBoundsException e){

        }
        if (currentDigit != (index + 1))
            showStep();
        checkProgress(showDialog);
    }
    private void showStep(){
        stepCount++;
        steps.setText(R.string.stepsNum);
        steps.setText(steps.getText().toString() +" " + stepCount);
        stepsDetail.setText(stepsDetail.getText() + " -> ");
        for (int i = 0; i < number.size(); i++){
            stepsDetail.setText(stepsDetail.getText() + number.get(i).getText().toString());
        }
        scroll.scrollTo(0, scroll.getBottom());
    }
    protected void onSaveInstanceState(Bundle outState){
        super.onSaveInstanceState(outState);
        for (int i = 0; i < number.size(); i++){
            outState.putString("number" + i, number.get(i).getText().toString());
            outState.putString("resNumber" + i, resNumber.get(i).getText().toString());
        }
        outState.putInt("stepCount", stepCount);
        outState.putInt("difficulty", difficulty);
        outState.putString("stepsDetail", stepsDetail.getText().toString());
        outState.putBoolean("detailVisible", detailVisible);
        outState.putBoolean("showDialog", showDialog);
    }

    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        for (int i = 0; i < number.size(); i++){
            number.get(i).setText(savedInstanceState.getString("number" + i));
            resNumber.get(i).setText(savedInstanceState.getString("resNumber" + i));
        }
        stepCount = savedInstanceState.getInt("stepCount");
        difficulty = savedInstanceState.getInt("difficulty");
        showDialog = savedInstanceState.getBoolean("showDialog");
        checkProgress(showDialog);
        steps.setText(R.string.stepsNum);
        steps.setText(steps.getText().toString() +" " + stepCount);
        stepsDetail.setText(savedInstanceState.getString("stepsDetail"));
        detailVisible = savedInstanceState.getBoolean("detailVisible");
        if (detailVisible)
            scroll.setVisibility(View.VISIBLE);
        else
            scroll.setVisibility(View.INVISIBLE);
    }

}
