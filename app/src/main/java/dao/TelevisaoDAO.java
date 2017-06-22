package dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;

import pojo.Televisao;

/**
 * Created by mauriciocantu on 22/06/17.
 */

public class TelevisaoDAO {

    private SQLiteDatabase db;

    public static final String NOME_TABELA = "televisao";
    public static final String COLUNA_ID = "id";
    public static final String COLUNA_MARCA = "marca";
    public static final String COLUNA_MODELO = "modelo";
    public static final String COLUNA_PESO = "peso";
    public static final String COLUNA_CC = "temcc";
    public static final String COLUNA_CD = "temcd";
    public static final String COLUNA_SAP = "temsap";
    public static final String COLUNA_RESOLUCAO = "resolucao";
    public static final String CRIAR_TABELA = "CREATE TABLE "+NOME_TABELA+"("+COLUNA_ID+" INTEGER PRIMARY KEY AUTOINCREMENT NOT" +
            " NULL UNIQUE, "+COLUNA_MARCA+" TEXT NOT NULL, "+COLUNA_MODELO+" TEXT NOT NULL, "+COLUNA_PESO+" TEXT NOT NULL, " +
            COLUNA_CC+" TEXT NOT NULL, " + COLUNA_CD + " TEXT NOT NULL, " + COLUNA_SAP + " TEXT NOT NULL, " + COLUNA_RESOLUCAO + " TEXT NOT NULL)";

    public TelevisaoDAO(Context ctx){
        db = SQLHelper.getInstance(ctx).getWritableDatabase();
    }

    public boolean salvar(Televisao televisao){
        long i = db.insert(NOME_TABELA, null, televisaoContentValues(televisao));

        Log.e("insert: ", String.valueOf(i));

        return i != -1;

    }

    public boolean deletar(Televisao televisao){
        String where = COLUNA_ID+ "=?";

        String[] whereArgs = {
                televisao.getId()
        };

        long i = db.delete(NOME_TABELA, where, whereArgs);

        Log.e("delete: ", String.valueOf(i));

        return i > 0;
    }

    public boolean editar(Televisao televisao){
        String where = COLUNA_ID + " =?";

        String[] whereArgs = {
                televisao.getId()
        };

        long i = db.update(NOME_TABELA, televisaoContentValues(televisao), where, whereArgs);

        Log.e("update: ", String.valueOf(i));

        return i > 0;

    }

    public ArrayList<Televisao> listar(){
        ArrayList<Televisao> listaTelevisaos = new ArrayList<Televisao>();
        String sqlBusca = "select * from " + NOME_TABELA;
        Cursor cursor = db.rawQuery(sqlBusca, null);

        Televisao televisao;

        int indiceId = cursor.getColumnIndex(COLUNA_ID);
        int indiceMarca = cursor.getColumnIndex(COLUNA_MARCA);
        int indiceModelo = cursor.getColumnIndex(COLUNA_MODELO);
        int indicePeso = cursor.getColumnIndex(COLUNA_PESO);
        int indiceCc = cursor.getColumnIndex(COLUNA_CC);
        int indiceCd = cursor.getColumnIndex(COLUNA_CD);
        int indiceSap = cursor.getColumnIndex(COLUNA_SAP);
        int indiceResolucao = cursor.getColumnIndex(COLUNA_RESOLUCAO);

        while(cursor.moveToNext()){

            televisao = new Televisao();

            televisao.setId(cursor.getString(indiceId));
            televisao.setMarca(cursor.getString(indiceMarca));
            televisao.setModelo(cursor.getString(indiceModelo));
            televisao.setPeso(cursor.getString(indicePeso));
            televisao.setTemCc(cursor.getString(indiceCc));
            televisao.setTemCd(cursor.getString(indiceCd));
            televisao.setTemSap(cursor.getString(indiceSap));
            televisao.setResolucao(cursor.getString(indiceResolucao));

            listaTelevisaos.add(televisao);

        }

        return listaTelevisaos;
    }

    public ContentValues televisaoContentValues(Televisao televisao){

        ContentValues cv = new ContentValues();

        cv.put(COLUNA_MARCA, televisao.getMarca());
        cv.put(COLUNA_MODELO, televisao.getModelo());
        cv.put(COLUNA_PESO, televisao.getPeso());
        cv.put(COLUNA_CC, televisao.getTemCc());
        cv.put(COLUNA_CD, televisao.getTemCd());
        cv.put(COLUNA_SAP, televisao.getTemSap());
        cv.put(COLUNA_RESOLUCAO, televisao.getResolucao());

        return cv;

    }
    
}
