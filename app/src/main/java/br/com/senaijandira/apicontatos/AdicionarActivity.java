package br.com.senaijandira.apicontatos;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

public class AdicionarActivity extends AppCompatActivity {

    EditText txt_add_nome, txt_add_telefone, txt_add_email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adicionar);

        txt_add_email = (EditText) findViewById(R.id.txt_add_email);
        txt_add_nome = (EditText) findViewById(R.id.txt_add_nome);
        txt_add_telefone = (EditText) findViewById(R.id.txt_add_telefone);
    }

    public void adicionarContato(View v){
        final String nome = txt_add_nome.getText().toString();
        final String telefone = txt_add_telefone.getText().toString();
        final String email = txt_add_email.getText().toString();

        new AsyncTask<Void, Void, Void>(){
            @Override
            protected Void doInBackground(Void... voids) {
                String url = "http://10.0.2.2/inf3t20181/TurmaB/Hugo/APIContatos/inserir.php?nome="+nome+"&telefone="+telefone+"&email="+email;
                HttpConnection.get(url);
                return null;
            }
        }.execute();
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
