package easy.tuto.easycalculator;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView resultText,solutionText;
    Button cButton, openBracketButton, closeBracketButton, divideButton, sevenButton, eightButton, nineButton, intoButton, fourButton, fiveButton, sixButton,
            plusButton, oneButton, twoButton, threeButton, minusButton, acButton, zeroButton, dotButton, equalButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        cButton = findViewById(R.id.cButton);
        openBracketButton = findViewById(R.id.openBracketButton);
        closeBracketButton = findViewById(R.id.closeBracketButton);
        divideButton = findViewById(R.id.divideButton);
        sevenButton = findViewById(R.id.sevenButton);
        eightButton = findViewById(R.id.eightButton);
        nineButton = findViewById(R.id.nineButton);
        intoButton = findViewById(R.id.intoButton);
        fourButton = findViewById(R.id.fourButton);
        fiveButton = findViewById(R.id.fiveButton);
        sixButton = findViewById(R.id.sixButton);
        plusButton = findViewById(R.id.plusButton);
        oneButton = findViewById(R.id.oneButton);
        twoButton = findViewById(R.id.twoButton);
        threeButton = findViewById(R.id.threeButton);
        minusButton = findViewById(R.id.minusButton);
        acButton = findViewById(R.id.acButton);
        zeroButton = findViewById(R.id.zeroButton);
        dotButton = findViewById(R.id.dotButton);
        equalButton = findViewById(R.id.equalButton);
        resultText = findViewById(R.id.resultText);
        solutionText = findViewById(R.id.solutionText);


        acButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                solutionText.setText("");
                resultText.setText("");
            }
        });

        equalButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                solutionText.setText(resultText.getText());
            }
        });

        cButton.setOnClickListener(onClickListener);
        openBracketButton.setOnClickListener(onClickListener);
        closeBracketButton.setOnClickListener(onClickListener);
        divideButton.setOnClickListener(onClickListener);
        sevenButton.setOnClickListener(onClickListener);
        eightButton.setOnClickListener(onClickListener);
        nineButton.setOnClickListener(onClickListener);
        intoButton.setOnClickListener(onClickListener);
        fourButton.setOnClickListener(onClickListener);
        fiveButton.setOnClickListener(onClickListener);
        sixButton.setOnClickListener(onClickListener);
        plusButton.setOnClickListener(onClickListener);
        oneButton.setOnClickListener(onClickListener);
        twoButton.setOnClickListener(onClickListener);
        threeButton.setOnClickListener(onClickListener);
        minusButton.setOnClickListener(onClickListener);
        zeroButton.setOnClickListener(onClickListener);
        dotButton.setOnClickListener(onClickListener);




    }

    private View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            onButtonClick((Button) view);
        }
    };

    private void onButtonClick(Button button) {

        String buttonText = button.getText().toString();
        String dataToCalculate = solutionText.getText().toString();

        if(buttonText.equals("C")){
            dataToCalculate = dataToCalculate.substring(0,dataToCalculate.length()-1);
        }else{
            dataToCalculate = dataToCalculate+buttonText;
        }
        solutionText.setText(dataToCalculate);

        String finalResult = getFinalResult(dataToCalculate);

        if(!finalResult.equals("Err")){
            resultText.setText(finalResult);
        }

    }

    String getFinalResult(String data){
        try{
            org.mozilla.javascript.Context context  = org.mozilla.javascript.Context.enter();
            context.setOptimizationLevel(-1);
            org.mozilla.javascript.Scriptable scriptable = context.initStandardObjects();
            String finalResult =  context.evaluateString(scriptable,data,"Javascript",1,null).toString();
            if(finalResult.endsWith(".0")){
                finalResult = finalResult.replace(".0","");
            }
            return finalResult;
        }catch (Exception e){
            return "Err";
        }
    }

}
