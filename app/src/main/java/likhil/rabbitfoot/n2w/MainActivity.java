package likhil.rabbitfoot.n2w;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    static String ones[] = {"","one","two","three","four","five","six","seven","eight","nine"},
                  tens[] = {"","ten","twenty","thirty","fourty","fifty","sixty","seventy","eighty","ninety"},
                  spl[] = {"","Thousand","Million","Billion","Trillion","Quadrillion","Quintillion","Sextillion","Septillion","Octillion","Nonillion","Decillion","Undecillion","Duodecillion","Tredecillion","Quattuordecillion","Quindecillion","Sexdecillion","Septendecillion","Octodecillion","Novemdecillion","Vigintillion"},
                  dif[] = {"ten","eleven","twelve","thirteen","fourteen","fifteen","sixteen","seventeen","eighteen","nineteen"};

    private void splitNum(String num){
        int length=num.length();
        String threes[] = new String[(int)Math.ceil(length/3.0d)];
        if(length%3==0){
            for(int j=0;j<length/3;j++){
                threes[j]=num.substring(j*3,j*3+3);
            }
        }
        else{
            int beg=length%3;
            threes[0]=num.substring(0,beg);
            for(int j=1;j<=length/3;j++){
                threes[j]=num.substring(beg,beg+3);
                beg+=3;
            }
        }

        String word[] = new String[threes.length];
        for(int i=0;i<threes.length;i++){
            word[i] = getWord(threes[i]);
        }
        int count = 0;
        StringBuilder sb=new StringBuilder();
        for(int i=word.length-1;i>=0;i--){
            if(word[i].equalsIgnoreCase("xxx") && word[i-1].equalsIgnoreCase("xxx")){
                count++;
                continue;
            }
            else if(word[i].equalsIgnoreCase("xxx")){//no need of this if condition
                count++;
                continue;
            }
            sb.insert(0,spl[count]+" ");
            sb.insert(0,word[i]+" ");
            count++;
        }

        String ans = sb.toString();
        output.setText(ans);
    }

    private String getWord(String num){
        if(num.equals("000")||num.equals("00")||num.equals("0"))
            return "xxx";
        int n=Integer.parseInt(num);
        String string = Integer.toString(n);
        StringBuilder ans = new StringBuilder();
        if(string.length()>0){
            ans.insert(0,ones[Integer.parseInt(""+string.charAt(string.length()-1))]);
        }
        if(string.length()>1){
            if(string.charAt(string.length()-2)=='1'){
                ans=new StringBuilder();
                ans.insert(0,dif[Integer.parseInt(""+string.charAt(string.length()-1))]);
            }
            else if(string.charAt(string.length()-2)!='0'){
                ans.insert(0,tens[Integer.parseInt(""+string.charAt(string.length()-2))]+ " ");
            }
        }
        if(string.length()>2){
            ans.insert(0,ones[Integer.parseInt(""+string.charAt(string.length()-3))]+ " hundred ");
        }

        return  ans.toString();
    }



    EditText input;
    TextView output;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        input = (EditText)findViewById(R.id.editText);
        output = (TextView)findViewById(R.id.textView);

        input.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(charSequence.toString().equalsIgnoreCase("0"))
                    input.setText("");
                else
                    splitNum(charSequence.toString());
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

    }
}
