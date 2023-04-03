
package com.example.p_4;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.p_4.LogicaNegocio;
import com.example.p_4.PeticionarioREST;

public class MainActivity extends AppCompatActivity {
    private EditText userName;
    private TextView textoEmail;

    // ---------------------------------------------------------------------------------------------
    // ---------------------------------------------------------------------------------------------
    @Override
    protected void onCreate(Bundle savedInstanceState) { // <-- + o - sería MAIN
        Log.d( "primeraApp", "MainActivity.onCreate(): empieza"); // <-- System.out.println

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        prueba();

        Log.d( "primeraApp", "MainActivity.onCreate(): acaba");
    } // onCreate()

    // ---------------------------------------------------------------------------------------------
    // ---------------------------------------------------------------------------------------------
    public void prueba() {
        Log.d( "primeraApp", "MainActivity.prueba(): empieza");

        Log.d( "primeraApp", "MainActivity.prueba(): acaba");
    }

    // ---------------------------------------------------------------------------------------------
    // ---------------------------------------------------------------------------------------------
    @RequiresApi(api = Build.VERSION_CODES.N)
    public void boton_pedir_pulsado(View quien ) { // <--- callback del boton
        Log.d( "primeraApp", "MainActivity.boton_pedir_pulsado(): empieza");
        userName = (EditText)findViewById(R.id.userName);
        textoEmail = (TextView)findViewById(R.id.textoEmail);
        //coger el texto del username y pasarlo a string
        String userNameString = String.valueOf(userName.getText());
        Log.d("username a string hecho", userName.toString());

        LogicaNegocio.pedirAlgoAlServidorRest( userNameString, ( resultados ) -> {

                    Log.d( "primeraApp", "MainActivity.boton_ok_pulsado(): resultados: " + resultados.getString("resultadoSinParsear" ) );
                    //coge el bundle al que se le ha metido la resouesta desde la lógica Fake
                     textoEmail.setText( resultados.getString("correo" ));
                });


        // dimePrecio()
        Log.d( "primeraApp", "MainActivity.boton_pedir_pulsado(): acaba");
    }
}
