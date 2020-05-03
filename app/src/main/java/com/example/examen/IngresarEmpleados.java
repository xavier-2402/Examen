package com.example.examen;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.examen.Modelo.Proveedor;
import com.example.examen.Modelo.SuperHelper;

public class IngresarEmpleados extends AppCompatActivity {
    private EditText txtRuc;
    private EditText txtNombre_Comercial;
    private EditText txtRepresentante_Legal;
    private EditText txtDireccion;
    private EditText txtTelefono;
    private EditText txtProducto;
    private EditText txtCredito;
    private Button btnGuardar;
    private Button btnCargar;
    private ImageView img;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ingresar_empleados);

        //Casting
        txtRuc = (EditText)findViewById(R.id.txtRuc);
        txtNombre_Comercial = (EditText)findViewById(R.id.txtNombre_Comercial);
        txtRepresentante_Legal = (EditText)findViewById(R.id.txtRepresentante);
        txtDireccion = (EditText)findViewById(R.id.txtDireccion);
        txtTelefono = (EditText)findViewById(R.id.txtTelefono);
        txtProducto = (EditText)findViewById(R.id.txtProducto);
        txtCredito = (EditText)findViewById(R.id.txtCredito);
        img = (ImageView)findViewById(R.id.imagen);
        btnCargar=(Button)findViewById(R.id.btnCargar);
        btnCargar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cargarImagen();
            }
        });
        btnGuardar=(Button)findViewById(R.id.btnGuardar);
        btnGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SuperHelper superHelper = new SuperHelper(getApplicationContext());
                Proveedor proveedor = new Proveedor(txtRuc.getText().toString(),
                        txtNombre_Comercial.getText().toString(),txtRepresentante_Legal.getText().toString(),
                        txtDireccion.getText().toString(),txtTelefono.getText().toString(),
                        txtProducto.getText().toString(),txtCredito.getText().toString());
                String sentencia=superHelper.insertarProveedores(proveedor);
                if(sentencia==null){
                    Toast.makeText(getApplicationContext(),"OK",Toast.LENGTH_LONG).show();
                    finish();
                }else{
                    Toast.makeText(getApplicationContext(),"ERROR " +sentencia,Toast.LENGTH_LONG).show();
                }

            }
        });
    }
    private void cargarImagen() {
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        intent.setType("image/");
        startActivityForResult(intent.createChooser(intent,"Seleccione la aplicacion"),10);

    }
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode==RESULT_OK) {
            Uri pathlocal = data.getData();
            img.setImageURI(pathlocal);
        }
    }
}
