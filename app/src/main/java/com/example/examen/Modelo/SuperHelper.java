package com.example.examen.Modelo;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class SuperHelper extends SQLiteOpenHelper {
    private static final String DATABASE ="examen_final.db";
    Context micontext;

    public SuperHelper( Context context) {
        super(context, DATABASE, null, 1);
        micontext=context;
        //VERIFICAR ARCHIVO
        File pathArchivo=micontext.getDatabasePath(DATABASE);
        if(!verificarBase(pathArchivo.getAbsolutePath())){
            //COPIAR ARCHIVO
            try {
                copiarBase(pathArchivo);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
    //Obtener Proveedores
    public Cursor listaProveedores(){
        Cursor cursor;
        String SQL ="select ROWID as _id,* from proveedores";
        cursor=this.getReadableDatabase().rawQuery(SQL,null);
        return cursor;
    }

    //Eliminar Empleados
    public String eliminarProveedores(String ruc){
        String Sql = "Delete from proveedores where ruc = "+ruc;
        try{
            this.getWritableDatabase().execSQL(Sql);
        }catch (SQLException ex){
            System.out.println(ex.getMessage());
            return  ex.getMessage();
        }
        return null;
    }

    //Insertar Empleados

    public String insertarProveedores (Proveedor proveedor){
        String sql;
        sql="insert into proveedores " +
                "(ruc,nombre_comercial,representante_legal,direccion,telefono,producto,credito)" +
                "values('"+proveedor.getRuc()+"','"+proveedor.getNombre_Comercial()+
                "','"+proveedor.getRepresentante_Legal()+"','"+proveedor.getDireccion()+
                "','"+proveedor.getTelefono()+"','"+proveedor.getProducto()+"','"+proveedor.getCredito()+"')";
        try {
            this.getWritableDatabase().execSQL(sql);

        }catch (SQLException ex){
            System.out.println(ex.getMessage());
            return ex.getMessage();
        }
        return null;
    }
    //Copiar base
    private void copiarBase(File rutabase) throws IOException {
        InputStream miInput = micontext.getAssets().open(DATABASE);
        OutputStream miOutput = new FileOutputStream(rutabase);
        byte [] buffer = new byte[1024];
        int largo;
        while ((largo = miInput.read(buffer))>0){
            miOutput.write(buffer,0,largo);
        }
        miOutput.flush();
        miOutput.close();
        miInput.close();



    }

    private boolean verificarBase(String ruta){
        SQLiteDatabase miBase = null;
        try {
        }
        catch (Exception ex){
            miBase=SQLiteDatabase.openDatabase(ruta,null,SQLiteDatabase.OPEN_READONLY);
        }
        if (miBase!=null){
            miBase.close();
        }
        return miBase!=null;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
