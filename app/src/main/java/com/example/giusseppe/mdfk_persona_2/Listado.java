package com.example.giusseppe.mdfk_persona_2;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class Listado extends AppCompatActivity {

    ListView lvLista;
    ArrayList<String>listado;

    @Override
    protected void onPostResume() {
        super.onPostResume();
        CargarListado();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listado);

        lvLista = (ListView) findViewById(R.id.lvLista);
        CargarListado();

        lvLista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                Toast.makeText(Listado.this,listado.get(position),Toast.LENGTH_LONG).show();
                int clave = Integer.parseInt(listado.get(position).split(" ")[0]);
                String nombre = listado.get(position).split(" ")[1];
                String apellido = listado.get(position).split(" ")[2];
                Intent intent = new Intent(Listado.this,Actualizar.class);
                intent.putExtra("Id",clave);
                intent.putExtra("Nombre",nombre);
                intent.putExtra("Apellido",apellido);
                startActivity(intent);
            }
        });

        if(getSupportActionBar()!=null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId()==android.R.id.home){
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    private void CargarListado(){
        listado = ListaPersonas();
        ArrayAdapter<String>adapter = new ArrayAdapter<String>(Listado.this,android.R.layout.simple_list_item_1,listado);
        lvLista.setAdapter(adapter);
    }


    private ArrayList<String>ListaPersonas(){
        ArrayList<String>datos = new ArrayList<String>();
        DatabaseHelper helper = new DatabaseHelper(Listado.this,"bd_persona",null,1);
        SQLiteDatabase db = helper.getWritableDatabase();
        String sql = "SELECT * FROM TB_PERSONAS";
        Cursor cursor = db.rawQuery(sql,null);
        if(cursor.moveToFirst()){
            do{
                String linea = cursor.getInt(0) + " " + cursor.getString(1) + " " + cursor.getString(2);
                datos.add(linea);
            }while (cursor.moveToNext());
        }
        db.close();
        return datos;
    }

}
