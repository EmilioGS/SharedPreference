package com.example.tarea7;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.InputType;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements OnClickListener {

    EditText edTxt1, edTxt2;
    TextView txt1, txt2, txt3, txt4;

    SharedPreferences misDatos;
    String nombre, apellido;

    CheckBox cbox1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txt1 = (TextView) findViewById(R.id.txt1);
        txt2 = (TextView) findViewById(R.id.txt2);
        txt3 = (TextView) findViewById(R.id.txt3);
        txt4 = (TextView) findViewById(R.id.txt4);
        edTxt1 = (EditText) findViewById(R.id.edTxt1);
        edTxt2 = (EditText) findViewById(R.id.edTxt2);
        cbox1 = (CheckBox) findViewById(R.id.cbox1);
        Button button1 = (Button) findViewById(R.id.btn1);
        button1.setOnClickListener(this);
        //Clase Propia
        clasepropiaOnKey miListener = new clasepropiaOnKey();
        edTxt1.setOnKeyListener(miListener);
        edTxt2.setOnKeyListener(miListener);

        //edTxt1.setInputType(InputType.TYPE_NUMBER_VARIATION_PASSWORD | InputType.TYPE_CLASS_NUMBER);

        //getSharedPreferences("nombre archivo", modo: protegido(0))
        misDatos = getSharedPreferences("preferencias", 0);
        // El primer argumento es el nombre de la etiqueta, El segundo parametro es por si no encuentra nada en el archivo
        nombre = misDatos.getString("name", "No hay nombre");
        apellido = misDatos.getString("last_name", "No hay apellido");
        edTxt1.setText(nombre);
        edTxt2.setText(apellido);

    }

    ///// Ciclos de vida de una aplicación /////
    @Override
    protected void onStart(){
        super.onStart();
        Toast.makeText(getApplicationContext(),"onStart",Toast.LENGTH_LONG).show();
    }

    @Override
    protected void onResume(){
        super.onResume();
        Toast.makeText(getApplicationContext(),"onResume",Toast.LENGTH_LONG).show();
    }

    //Cuando la aplicacion esta en segundo plano
    @Override
    protected void onPause(){
        super.onPause();
        Toast.makeText(getApplicationContext(),"onPause",Toast.LENGTH_LONG).show();
        //Creamos un editor para poder guardar en el archivo los datos ingresados por el usuario
        SharedPreferences.Editor miEditor = misDatos.edit();
        nombre  = edTxt1.getText().toString();
        apellido = edTxt2.getText().toString();
        // Guardamos los datos ingresados en los EditText en el editor
        miEditor.putString("name", nombre);
        miEditor.putString("last_name", apellido);
        // Guardamos el archivo
        miEditor.commit();
    }

    @Override
    protected void onStop(){
        super.onStop();
        Toast.makeText(getApplicationContext(),"onStop",Toast.LENGTH_LONG).show();

    }

    @Override
    protected void onDestroy(){
        super.onDestroy();
        //Borramos los datos del archivo
        if(cbox1.isChecked()){
            misDatos.edit().clear().apply();
            misDatos.edit().clear().commit();
        }

        Toast.makeText(getApplicationContext(),"onDestroy",Toast.LENGTH_LONG).show();
    }

    @Override
    protected void onRestart(){
        super.onRestart();
        Toast.makeText(getApplicationContext(),"onRestart",Toast.LENGTH_LONG).show();
    }

    @Override
    public void onClick(View view) {
        txt3.setText("Tu nombre es: " + edTxt1.getText().toString());
        txt4.setText("Tu nombre es: " + edTxt2.getText().toString());
    }

    class clasepropiaOnKey implements View.OnKeyListener{

        @Override
        public boolean onKey(View view, int i, KeyEvent keyEvent) {
            if(keyEvent.getAction()==KeyEvent.ACTION_DOWN && i==KeyEvent.KEYCODE_ENTER){
                switch(view.getId()){
                    case R.id.edTxt1:
                        txt3.setText("Tu nombre es: " + edTxt1.getText().toString());
                        break;
                    case R.id.edTxt2:
                        txt4.setText("Tu apellido es: " + edTxt2.getText().toString());
                        break;
                }
                //Mensaje emergente
                Toast.makeText(getApplicationContext(),"tecla = " + i,Toast.LENGTH_LONG).show();
                return true;
            }
            return false;
        }
    }
}