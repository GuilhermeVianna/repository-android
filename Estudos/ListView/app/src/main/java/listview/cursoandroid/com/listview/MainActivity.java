package listview.cursoandroid.com.listview;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends Activity {

    private ListView listaItens;
    private String[] itens = {"Leopoldina", "Juiz de Fora","Muriaé",
            "Belo Horizonte","Gov.Valadares","Ipatinga","Cataguases","Ubá","Piacatuba",
            "Alem Paraiba","Rodero","Recreio","Rio de Janeiro","SJ Neoponuceno","Rio Pomba",
            "Jequitinhonha","Almenara","Itaobim","Teresopolis","Petropolis","Varginha",
            "Contagem","Viçosa","Tres Rios","Pirauba","Teofilo Otoni","Minas"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listaItens = (ListView) findViewById(R.id.listViewId);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                getApplicationContext(),
                android.R.layout.simple_list_item_1,
                android.R.id.text1,
                itens
        );

        listaItens.setAdapter(adapter);
        listaItens.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String valorClicado = listaItens.getItemAtPosition(position).toString();
                Toast.makeText(getApplicationContext(), valorClicado, Toast.LENGTH_SHORT).show();
            }
        });
    }
}
