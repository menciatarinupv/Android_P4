
package com.example.p_4;

import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import androidx.annotation.RequiresApi;

import java.util.Optional;

// -------------------------------------------------------------------------------------------------
// -------------------------------------------------------------------------------------------------
@RequiresApi(api = Build.VERSION_CODES.N)
public class LogicaNegocio {


    // ---------------------------------------------------------------------------------------------
    // ---------------------------------------------------------------------------------------------
    public interface Respuesta {
        void callback ( Bundle resultados );
    }

    // ---------------------------------------------------------------------------------------------
    // ---------------------------------------------------------------------------------------------
    private static Optional<String> url_servidor = Optional.empty();
    private static String servidor_por_defecto = "https://jsonplaceholder.typicode.com";

    /*
        Servidores REST de prueba

        + https://jsonplaceholder.typicode.com
        + https://httpbin.org

https://jsonplaceholder.typicode.com

/posts	100 posts
/comments	500 comments
/albums	100 albums
/photos	5000 photos
/todos	200 todos
/users	10 users

Routes:

All HTTP methods are supported. You can use http or https for your requests.

GET	/posts
GET	/posts/1
GET	/posts/1/comments
GET	/comments?postId=1
POST	/posts
DELETE	/posts/1


https://httpbin.org

allows you to echo back your request, to see that it does what you expect.

List of services (It echoes the data used in your request for any of these types)

https://httpbin.org/anything Returns most of the below.
https://httpbin.org/ip Returns Origin IP.
https://httpbin.org/headers Returns header dict.
https://httpbin.org/get Returns GET data.
https://httpbin.org/post Returns POST data.
https://httpbin.org/delete Returns DELETE data
     */

    // ---------------------------------------------------------------------------------------------
    // ---------------------------------------------------------------------------------------------
    public static void ponerUrlServidor( String url ) {
        Log.d( "primeraApp", "LogicaNegocio.ponerUrlServidor(): empieza");

        //El concepto de Java Optional hace referencia a una variable
        // que puede tener un valor asignado o que puede contener un valor null.
        url_servidor = Optional.of( url );
        Log.d( "primeraApp", "LogicaNegocio.ponerUrlServidor(): termina");
    } // ()

    // ---------------------------------------------------------------------------------------------
    // ---------------------------------------------------------------------------------------------
    public static void pedirAlgoAlServidorRest( String parametroQueNoUso, /*porque es una prueba*/
                                               Respuesta responder
    ) {
        Log.d( "primeraApp", "LogicaNegocio.pedirAlgoAlServidorRest(): empieza");

        PeticionarioREST elPeticionario = new PeticionarioREST();

        elPeticionario.hacerPeticionREST(
                "GET",
                url_servidor.orElse(servidor_por_defecto) + "/users",
                null,
                new PeticionarioREST.RespuestaREST() { /* se puede abreviar: (codigo, cuerpo) -> { .... } */
                    @Override
                    public void callback(int codigo, String cuerpo) {
                      Log.d( "primeraApp", "LogicaNegocio.pedirAlgoAlServidorRest().callback: recibo: " + codigo );

                      /* aquí deberíamos analizar la respuesta REST+HTTP. Si es correcta, parsear el resultado
                         que estará en JSON (pero como un único texto) para sacar las partes que nos interesen.
                         
                         Luego, ponemos las respuestas ya pasadas al tipo correcto en bundle (almacén clave-valor)
                         y llamamos al callback que está esperando en la parte de UX
                         */
                      //bundle para pasar entre actividades
                        Bundle res = new Bundle();
                        //le puedes añadir más de una cosa al bundle
                        res.putInt( "codigo", codigo );
                        //"Parsing" significa analizar y convertir un programa en un formato interno que un entorno de ejecución pueda realmente ejecutar
                        res.putString( "resultadoSinParsear", cuerpo );

                        JsonArray jsonArray = JsonParser.parseString(cuerpo).getAsJsonArray();
                        //uso null para definirlo, es como un 0.0 en un double
                        JsonObject jObject = null;
                        for (JsonElement jElement : jsonArray) {
                            jObject = jElement.getAsJsonObject();
                            Log.d("JSONArray", jObject.toString());
                            if (jObject.get("username").getAsString().equals(userNameString)) {
                                res.putString("correo",jObject.get("email").getAsString() );
                                break;
                            }
                        }

                      responder.callback( res );

                      Log.d( "primeraApp", "LogicaNegocio.pedirAlgoAlServidorRest().callback: recibo: " + cuerpo );

                    }
                } );


        Log.d( "primeraApp", "LogicaNegocio.pedirAlgoAlServidorRest(): termina");
    } // ()

} // class
// -------------------------------------------------------------------------------------------------
// -------------------------------------------------------------------------------------------------
// -------------------------------------------------------------------------------------------------
// -------------------------------------------------------------------------------------------------

