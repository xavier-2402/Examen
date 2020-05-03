package com.example.examen.ui.gallery;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.examen.Modelo.SuperHelper;
import com.example.examen.R;

public class GalleryFragment extends Fragment {

    private GalleryViewModel galleryViewModel;
    private SimpleCursorAdapter cursorAdapter;
    private SuperHelper superHelper;
    private ListView listaEmpleados;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        galleryViewModel =
                ViewModelProviders.of(this).get(GalleryViewModel.class);
        View root = inflater.inflate(R.layout.fragment_gallery, container, false);
        final TextView textView = root.findViewById(R.id.text_gallery);
        galleryViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        superHelper = new SuperHelper(getContext());
        MostrarProveedores(root);
        return root;
    }
    private void ListarEmpleados(View root) {
        SuperHelper superHelper = new SuperHelper(getContext());
        Cursor cursor = superHelper.listaProveedores();
        String[] desde = new String[]{"nombres","apellidos"};
        int[] hasta = new int[]{android.R.id.text1,android.R.id.text2};
        SimpleCursorAdapter cursorAdapter = new SimpleCursorAdapter(getContext(), android.R.layout.simple_list_item_2, cursor, desde, hasta, 0);

        ListView listaProveedores = (ListView) root.findViewById(R.id.listaempleados);
        listaProveedores.setAdapter(cursorAdapter);

    }
    @Override
    public void onResume() {
        cursorAdapter.swapCursor(superHelper.listaProveedores());
        listaEmpleados.setAdapter(cursorAdapter);
        super.onResume();
    }
    private void MostrarProveedores(final View root){

        final Cursor cursor = superHelper.listaProveedores();
        String[] desde = new String[]{"nombre_comercial","direccion","telefono","producto"};
        int[] hasta = new int[]{R.id.txtNombre_Comercial, R.id.txtRepresentante,R.id.txtDireccion,R.id.txtProducto};
       cursorAdapter = new SimpleCursorAdapter(getContext(),R.layout.proveedoreslista,cursor,desde,hasta,0);
        listaEmpleados = (ListView)root.findViewById(R.id.listaempleados);
        listaEmpleados.setAdapter(cursorAdapter);

        listaEmpleados.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, final View view, final int position, long id) {
                AlertDialog.Builder alerta = new AlertDialog.Builder(getContext());
                alerta
                        .setIcon(R.drawable.ic_menu_share)
                        .setTitle("Acciones sobre Empleados")
                        .setMessage("Atención: \nEstás a punto de elimnar a un Empleado, ¿estás seguro?.")
                        .setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Cursor itemp=(Cursor)listaEmpleados.getItemAtPosition(position);
                                String ruc =itemp.getString(1);
                                String transa=superHelper.eliminarProveedores(ruc);
                                if(transa==null){
                                    Toast.makeText(getContext(),"OK",Toast.LENGTH_LONG).show();

                                    cursorAdapter.swapCursor(superHelper.listaProveedores());
                                    listaEmpleados.setAdapter(cursorAdapter);
                                }else{
                                    Toast.makeText(getContext(),"ERROR " +transa,Toast.LENGTH_LONG).show();
                                }
                            }
                        })
                        .setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        });
                AlertDialog alertDialog=alerta.show();
                return false;
            }
        });




    }
}