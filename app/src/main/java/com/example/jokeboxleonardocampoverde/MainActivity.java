package com.example.jokeboxleonardocampoverde;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationManagerCompat;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    TextView numero;
    EditText input;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        numero=findViewById(R.id.numero);
        input=findViewById(R.id.inputnumero);
        input.setText("1");
        numero.setText("1");

    }
    @Override
    protected void onResume(){
        super.onResume();
        NotificationManagerCompat.from(this).deleteNotificationChannel("player");
    }


    public void scopri(View v){
        String num = (String) numero.getText();
        //creo un oggetto che Intent che mi passerà dall'activity main alla generazione del numero passandogli il numero in formato stringa e in formato numerico
        Intent intent = new Intent(MainActivity.this, numGenerato.class);
        intent.putExtra("num",num);
        intent.putExtra("intNum",Integer.valueOf(num) );

        //avvio l'activity nuova
        startActivity(intent);
    }

    public void genera(View view){

        int max=20;
        int min=0;
        int random_int = (int)Math.floor(Math.random() * (max - min) + min);//creo numero random
        numero.setText(Integer.toString(random_int));

    }

    public void toast(View view){
        Toast miao = Toast.makeText(this,"IL TOAST PIU FIGO", Toast.LENGTH_LONG);
        miao.show();
    }
    public void incrementa(View v){
        int num= Integer.parseInt((String) numero.getText());
        int inputNum= Integer.parseInt(input.getText().toString());
        int sum= num+inputNum;
        if(sum>20){
            sum=20;
            Toast t = new Toast(getApplicationContext());
            t.setText("IL MASSIMO NUMERO CONSENTITO È 20");
            t.show();

        }


        numero.setText(Integer.toString(sum));

    }

    public void decrementa(View v) {
        int num = Integer.parseInt((String) numero.getText());
        int inputNum = Integer.parseInt(input.getText().toString());
        int sum = num - inputNum;
        if (sum < 1) {
            sum = 1;
            Toast t = new Toast(getApplicationContext());
            t.setText("IL VALORE MINIMO CONSENTITO È 1");
            t.show();

        }
        numero.setText(Integer.toString(sum));

    }


}