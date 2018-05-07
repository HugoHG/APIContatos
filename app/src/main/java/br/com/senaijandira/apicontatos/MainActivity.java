package br.com.senaijandira.apicontatos;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ListView list_view;
    ContatoAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        list_view = (ListView) findViewById(R.id.list_view);

        //instanciar o adapter
        adapter = new ContatoAdapter(this);

        //ligando a lista com seu adaptador
        list_view.setAdapter(adapter);

    }

    @Override
    protected void onResume() {
        super.onResume();

        adapter.clear();

        new AsyncTask<Void, Void, String>(){
            @Override
            protected String doInBackground(Void... voids) {
                String json = "";
                //10.0.2.2 é o localhost do hospedeiro
                String url = "http://10.0.2.2/inf3t20181/TurmaB/Hugo/APIContatos/selecionar.php";
                json = HttpConnection.get(url);

                return json;
            }

            @Override
            protected void onPostExecute(String json) {
                super.onPostExecute(json);

                if(json==null) json = "sem dados";

                ArrayList<Contato> lstContatos = new ArrayList<>();
                if (json != null){
                    try{
                        JSONArray arrayContatos = new JSONArray(json);
                        for (int i = 0; i < arrayContatos.length(); i++){
                            JSONObject contatoJson = arrayContatos.getJSONObject(i);
                            //criando o contato a partir do JSON
                            Contato c = new Contato();
                            c.setId(contatoJson.getInt("id"));
                            c.setNome(contatoJson.getString("nome"));
                            c.setEmail(contatoJson.getString("email"));
                            c.setTelefone(contatoJson.getString("telefone"));

                            lstContatos.add(c);
                        }
                    } catch (Exception e){
                        e.printStackTrace();
                    }
                    adapter.addAll(lstContatos);
                }

                Log.d("json", json);
            }
        }.execute();
    }

    public void adicionar(View v){
        Intent intent = new Intent(this, AdicionarActivity.class);
        startActivity(intent);
    }
}
