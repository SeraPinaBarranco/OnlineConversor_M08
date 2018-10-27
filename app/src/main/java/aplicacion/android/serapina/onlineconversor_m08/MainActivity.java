package aplicacion.android.serapina.onlineconversor_m08;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    //Crear las variables de los tipos de componentes
    EditText t_cantidad1, t_cantidad2;
    Spinner spn_div1, spn_div2;
    int index1 = 0, index2 = 0;
    double n1=0, n2=0, res=0;
    Button btn;
    ImageButton ibtn;

    //Crear un array con los paises
    String[] paises = {"Euro EUR","Dolar USD","Libra GBP","Yen JPY", "Franco Suizo CHF","Yuan Chino CNY","Dolar Canadiense CAD","Rupia India INR","Peso Argentino ARS","Corona Checa CZK"};

    //Array bidimensional de 10x10 para almacenar la tabla de conversiones
    double[][] t ={
            /*EUR*/{1, 1.15, 0.86, 129.668, 1.14, 129.67, 1.5, 87.47, 42.63, 25.84},
            /*USD*/     {0.86, 1, 0.75, 112.22, 0.99, 6.88, 1.3, 73.91, 36.66, 22.29},
            /*GPB*/     {1.14, 1.32, 1, 148.28, 1.3, 9.09, 1.72, 97.69, 48.39, 29.44},
            /*JPY*/     {0.007, 0.009, 0.006, 1, 0.009, 0.061, 0.011, 0.65, 0.32, 0.19},
            /*CHF*/     {0.87, 1.009, 0.76, 113.3, 1, 6.95, 1.31, 74.53, 36.91, 22.49},
            /*CNY*/     {0.12, 0.14, 0.10, 16.3, 0.14, 1, 0.18, 10.72, 5.31, 3.23},
            /*CAD*/     {0.66, 0.76, 0.58, 86.2, 0.76, 5.28, 1, 56.73, 28.11, 17.11},
            /*IND*/     {0.011, 0.013, 0.010, 1.51, 0.01, 0.09, 0.017, 1, 0.49, 0.30},
            /*ARS*/     {0.023, 0.027, 0.020, 3.064, 0.027, 0.188, 0.035, 2.019, 1, 0.609},
            /*CZK*/     {0.038, 0.04, 0.033, 5.034, 0.044, 0.30, 0.058, 3.31, 1.643, 1},
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        t_cantidad1 = findViewById(R.id.txtCantidad1);
        spn_div1 = findViewById(R.id.spn_divisa1);
        t_cantidad2 = findViewById(R.id.txtCantidad2);
        spn_div2 = findViewById(R.id.spn_divisa2);
        btn = findViewById(R.id.btn_convert);
        ibtn = findViewById(R.id.img_invert);



        //Crear arrayAdapter para agregarle el array paises
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,R.layout.support_simple_spinner_dropdown_item, paises);


        //Colocar el ArrayAdapter en los spinner
        spn_div1.setAdapter(adapter);
        spn_div2.setAdapter(adapter);

        //forzamos los Spinner a que al iniciar esté una posicion marcada
        spn_div1.setSelection(0);
        spn_div2.setSelection(0);

        //Asignar valor a los editText al inicio
        t_cantidad1.setText("1");
        t_cantidad2.setText("1");

        //almacenar el indice del item seleccionado
        spn_div1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                index1 = position;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }

        });

        spn_div2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                index2 = position;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }

        });
        //Evento al pulsar en boton convertir
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                try{
                    n1 = Double.parseDouble(t_cantidad1.getText().toString());
                    n2 = Double.parseDouble(t_cantidad2.getText().toString());

                    res = n1 * t[index1][index2];

                    t_cantidad2.setText(""+res);
                }catch (Exception e){
                    Toast.makeText(v.getContext(), e.getMessage(), Toast.LENGTH_LONG).show();
                }

            }
        });

        //Evento para boton de invertir divisas
        ibtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Integer p_aux1, p_aux2;

                spn_div1.setSelection(index2);
                spn_div2.setSelection(index1);

                try{
                    n1 = Double.parseDouble(t_cantidad1.getText().toString());
                    n2 = Double.parseDouble(t_cantidad2.getText().toString());

                    res = n1 * t[index2][index1];

                    t_cantidad2.setText(""+res);
                }catch (Exception e){
                    Toast.makeText(v.getContext(), e.getMessage(), Toast.LENGTH_LONG).show();
                }

            }
        });


    }

    //metodo para pasar a la second_activity
    public void go_Second_Activity(View view){
        //crear un array que contendra los valores de cambio con respecto al Euro
        double  val[]={1, 1.15, 0.86, 129.668, 1.14, 129.67, 1.5, 87.47, 42.63, 25.84};
        //intent params: form act1.class, to act2.class
        Intent intent = new Intent(this, SecondActivity.class);

        intent.putExtra("greeter_paises",paises);
        intent.putExtra("greeter_val",val);
        startActivity(intent);

    }


}
