package pk.asghar.talha.calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;

import static pk.asghar.talha.calculator.Expression.*;

import pk.asghar.talha.calculator.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

   private ActivityMainBinding binding;
   private TextView tvInput;
   private TextView tvResult;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        tvInput = binding.tvInput;
        tvResult = binding.tvResult;

        binding.btn0.setOnClickListener(this);
        binding.btn1.setOnClickListener(this);
        binding.btn2.setOnClickListener(this);
        binding.btn3.setOnClickListener(this);
        binding.btn4.setOnClickListener(this);
        binding.btn5.setOnClickListener(this);
        binding.btn6.setOnClickListener(this);
        binding.btn7.setOnClickListener(this);
        binding.btn8.setOnClickListener(this);
        binding.btn9.setOnClickListener(this);

        binding.btnDiv.setOnClickListener(this);
        binding.btnMul.setOnClickListener(this);
        binding.btnAdd.setOnClickListener(this);
        binding.btnSub.setOnClickListener(this);
        binding.btnPoint.setOnClickListener(this);
        binding.btnEqual.setOnClickListener(this);
        binding.btnClear.setOnClickListener(this);
        binding.btnClearAll.setOnClickListener(this);

    }
    @Override
    public void onClick(View v) {
        Button btnTarget = (Button)v;
        switch (btnTarget.getId()){
            case R.id.btnClearAll:
                tvInput.setText("0");
                tvResult.setText("0");
                break;
            case R.id.btnClear:
                String tempText = tvInput.getText().toString();
                if(tempText.length() <= 1){
                    tvInput.setText("");
                }
                else{
                    tvInput.setText(tempText.substring(0, tempText.length() - 1));
                }

                break;
            case R.id.btnEqual:
                solveExpression(tvInput.getText().toString());
                break;
            default:
                tvInput.append(btnTarget.getText());

        }

    }

    private void solveExpression(String temporary) {

        temporary = temporary.trim();

        char [] inputData = temporary.toCharArray();

        if(inputData.length==0){
            Toast.makeText(getApplicationContext(), "Warning : No data was entered !!", Toast.LENGTH_SHORT).show();
        }
        else if(hasInvalidData(inputData) || hasInvalidOperator(inputData) ){
            Toast.makeText(getApplicationContext(), "Please check and correct your input!!", Toast.LENGTH_SHORT).show();
        }
        else{
            double calcAns = DMAS(inputData);
            boolean displayAns = true;
            try{

            }
            catch(NumberFormatException ex){

                Toast.makeText(getApplicationContext(), "Exception when calling DMAS , "+ex, Toast.LENGTH_SHORT).show();
                displayAns = false;

            }
            if(displayAns){
                DecimalFormat myFormat = new DecimalFormat("#.####");
                String formatedAns = myFormat.format(calcAns);
                tvInput.setText("0");
                tvResult.append(temporary+" = "+formatedAns);
            }
        }
    }

}