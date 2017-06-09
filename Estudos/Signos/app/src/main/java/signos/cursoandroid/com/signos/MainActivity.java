package signos.cursoandroid.com.signos;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private ListView listaSignos;
    private String[] signos = {"Leão","Capricornio","Cancer",
            "Gemeos","Aries","Touro",
            "Virgem","Peixes","Aquario","Libra"};

    private String[] mensagem = {"SEU SIGNO É LEÃO","SEU SIGNO É CAPRICORNIO","SEU SIGNO É CANCER",
            "SEU SIGNO É GEMEOS","SEU SIGNO É ARIES","SEU SIGNO É TOURO","SEU SIGNO É VIRGEM",
            "SEU SIGNO É PEIXES","SEU SIGNO É AQUARIO","SEU SIGNO É LIBRA"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listaSignos = (ListView) findViewById(R.id.listViewId);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                getApplicationContext(),
                android.R.layout.simple_list_item_2,
                android.R.id.text2,
                signos
        );

        listaSignos.setAdapter(adapter);
        listaSignos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                 Toast.makeText(getApplicationContext(),mensagem[position],Toast.LENGTH_SHORT).show();
            }
        });
    }
}
