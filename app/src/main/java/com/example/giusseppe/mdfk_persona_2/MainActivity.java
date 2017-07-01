package com.example.giusseppe.mdfk_persona_2;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText etNombre,etApellido;
    Button btnGuardar,btnMostrar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etNombre = (EditText) findViewById(R.id.etNombre);
        etApellido = (EditText) findViewById(R.id.etApellido);
        btnGuardar = (Button) findViewById(R.id.btnGuardar);
        btnMostrar = (Button) findViewById(R.id.btnMostrar);

        btnGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Persona persona = new Persona();
                persona.setNombre(etNombre.getText().toString().trim());
                persona.setApellido(etApellido.getText().toString().trim());

                guardar(persona);
            }
        });

        btnMostrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this,Listado.class));
            }
        });
    }


    private void guardar(Persona persona){
        DatabaseHelper helper = new DatabaseHelper(MainActivity.this,"bd_persona",null,1);
        SQLiteDatabase db = helper.getWritableDatabase();
        try{
            ContentValues contentValues = new ContentValues();
            contentValues.put("NOMBRE",persona.getNombre());
            contentValues.put("APELLIDO",persona.getApellido());
            db.insert("TB_PERSONAS",null,contentValues);
            db.close();
            Toast.makeText(MainActivity.this,"Registro Insertado",Toast.LENGTH_LONG).show();
        }catch (Exception e){
            Toast.makeText(MainActivity.this,"Error al Registrar : " + e.getMessage(),Toast.LENGTH_LONG).show();
            e.printStackTrace();
        }
    }
}
