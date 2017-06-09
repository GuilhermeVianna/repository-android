package toast.cursoandroid.com.mensagemtoast;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Button botaoCliqueAqui;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        botaoCliqueAqui = (Button) findViewById(R.id.botaoCliqueId);
        botaoCliqueAqui.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(),"VOCÊ CLICOU!!",Toast.LENGTH_LONG).show();
            }
        });
    }
}
