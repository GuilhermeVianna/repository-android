package dialog.cursoandroid.com.dialog;

import android.app.Activity;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends Activity {

    private Button abrirDialog;
    private AlertDialog.Builder dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        abrirDialog = (Button) findViewById(R.id.botaoId);
        abrirDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //CRIAR ALERT DIALOG
                dialog =  new AlertDialog.Builder(MainActivity.this);

                //CONFIGURAR DIALOG
                dialog.setTitle("Titulo Dialog");
                dialog.setMessage("Deseja Continuar?");
                dialog.setCancelable(false);
                dialog.setIcon(android.R.drawable.ic_dialog_info);

                //BOTAO NEGATIVO
                dialog.setNegativeButton("NÃO", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(getApplicationContext(),"CLICOU NÃO",Toast.LENGTH_SHORT).show();
                    }
                });

                //BOTAO POSITIVO
                dialog.setPositiveButton("SIM", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(getApplicationContext(),"CLICOU SIM",Toast.LENGTH_SHORT).show();
                    }
                });
                dialog.create();
                dialog.show();

            }
        });
    }
}
