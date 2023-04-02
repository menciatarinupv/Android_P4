
package com.example.p_4;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.p_4.LogicaNegocio;
import com.example.p_4.PeticionarioREST;

public class MainActivity extends AppCompatActivity {

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

        LogicaNegocio.pedirAlgoAlServidorRest( "hola",
                ( resultados ) -> {

                    Log.d( "primeraApp", "MainActivity.boton_ok_pulsado(): resultados: "
                            + resultados.getString("resultadoSinParsear" ) );

                });


        // dimePrecio()
        Log.d( "primeraApp", "MainActivity.boton_pedir_pulsado(): acaba");
    }
}
