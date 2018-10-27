package aplicacion.android.serapina.onlineconversor_m08;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class SecondActivity extends AppCompatActivity {
    ListView listView;
    TextView txt_change;
    EditText ed_change;
    ImageButton im_accept;
    int pos;

    //array to take values
    String[] p;
    double[] v;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        txt_change = findViewById(R.id.txt_pais);
        ed_change = findViewById(R.id.edt_change);
        im_accept = findViewById(R.id.img_accept);
        //make a Bundle to take values from Main activity
        //it takes values from intent
        Bundle myBundle = getIntent().getExtras();

        //fill array
        p = myBundle.getStringArray("greeter_paises");
        v = myBundle.getDoubleArray("greeter_val");
        //double t[][] = myBundle.getDoubleArray()

        //make an ArrayList to catch the data
        ArrayList<String> arrayList = new ArrayList<>();

        //fill the array list with arrays p and v
        for (int i = 0; i < p.length; i++) {

            arrayList.add(p[i] + " son: " + v[i]);

        }

        //create an Adapter
        ArrayAdapter<String> myAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, arrayList);

        //put values in the list view
        //list view
        listView = findViewById(R.id.lst_divisas);
        listView.setAdapter(myAdapter);

        //method change value
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                txt_change.setText(listView.getItemAtPosition(position).toString());//catch the list view item clicked
                pos = position;
                // Toast.makeText(getBaseContext(),s,Toast.LENGTH_LONG).show();
            }
        });


    }

    //accept changes
    public void accept_changes(View view) {
        //Toast.makeText(this, "hola", Toast.LENGTH_LONG).show();
        v[pos] = Double.parseDouble(ed_change.getText().toString());

        //Toast.makeText(this , "" +pos  , Toast.LENGTH_LONG).show();
        //
        //fill the array list with arrays p and v
        ArrayList<String> arrayList2 = new ArrayList<>();
        for (int i = 0; i < p.length; i++) {


            arrayList2.add(p[i] + " son: " + v[i]);

        }

        //create an Adapter
        ArrayAdapter<String> myAdapter2 = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, arrayList2);

        //put values in the list view
        //list view
        //listView = findViewById(R.id.lst_divisas);
        listView.setAdapter(myAdapter2);
    }

    //method to go back
}
