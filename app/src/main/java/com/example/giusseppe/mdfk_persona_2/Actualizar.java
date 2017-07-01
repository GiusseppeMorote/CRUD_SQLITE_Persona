package com.example.giusseppe.mdfk_persona_2;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Actualizar extends AppCompatActivity {

    EditText etNuevoNombre,etNuevoApellido;
    Button btnActualizar,btnEliminar;
    int verificar;
    String nombre,apellido;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actualizar);


        Bundle bundle = getIntent().getExtras();
        if(bundle!=null){
            verificar = bundle.getInt("Id");
            nombre = bundle.getString("Nombre");
            apellido = bundle.getString("Apellido");

        }

        etNuevoNombre=(EditText) findViewById(R.id.etNuevoNombre);
        etNuevoApellido=(EditText) findViewById(R.id.etNuevoApellido);

        etNuevoNombre.setText(nombre);
        etNuevoApellido.setText(apellido);


        btnActualizar = (Button)findViewById(R.id.btnActualizar);
        btnEliminar  = (Button) findViewById(R.id.btnEliminar);

        btnActualizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Persona persona = new Persona();
                persona.setNombre(etNuevoNombre.getText().toString().trim());
                persona.setApellido(etNuevoApellido.getText().toString().trim());
                persona.setId(getIntent().getIntExtra("Id",0));
                actualizar(persona);
                onBackPressed();
            }
        });

        btnEliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Persona persona =  new Persona();
                persona.setId(getIntent().getIntExtra("Id",0));
                eliminar(persona);
                onBackPressed();
            }
        });

    }

    private void actualizar(Persona persona){
        DatabaseHelper helper = new DatabaseHelper(Actualizar.this,"bd_persona",null,1);
        SQLiteDatabase db = helper.getWritableDatabase();

        try{
            ContentValues contentValues = new ContentValues();
            contentValues.put("NOMBRE",persona.getNombre());
            contentValues.put("APELLIDO",persona.getApellido());
            db.update("TB_PERSONAS",contentValues,"ID="+String.valueOf(persona.getId()),null);

            db.close();
            Toast.makeText(Actualizar.this,"Registro Actualizado",Toast.LENGTH_LONG).show();
        }catch (Exception e){
            Toast.makeText(Actualizar.this,"Error al Actualizar : " + e.getMessage(),Toast.LENGTH_LONG).show();
            e.printStackTrace();
        }







    }

    private void eliminar(Persona persona){
        DatabaseHelper helper = new DatabaseHelper(Actualizar.this,"bd_persona",null,1);
        SQLiteDatabase db = helper.getWritableDatabase();
        try {
            ContentValues contentValues = new ContentValues();
            contentValues.put("ID",persona.getId());

            db.delete("TB_PERSONAS", "ID="+String.valueOf(persona.getId()),null);


            Toast.makeText(Actualizar.this,"Registro Eliminado",Toast.LENGTH_LONG).show();
        }catch (Exception e){
            Toast.makeText(Actualizar.this,"Error al Eliminar : " + e.getMessage(),Toast.LENGTH_LONG).show();
            e.printStackTrace();
        }
    }

}
