package com.example.mauriciocantu.prova4i;

import android.app.Dialog;
import android.content.DialogInterface;
import android.provider.MediaStore;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import dao.TelevisaoDAO;
import pojo.Televisao;

public class AcaoActivity extends AppCompatActivity {

    private Button btAcao, btDeletar;
    private AutoCompleteTextView acMarca;
    private EditText etModelo, etPeso;
    //closed caption, canal digital e sap
    private CheckBox cbCc, cbCd, cbSap;
    private RadioGroup rgRes;
    private RadioButton rbHd, rbFullHd, rb4k;
    private ArrayAdapter<String> marcasAdapter;
    private Televisao tv;
    private TelevisaoDAO tvDAO;
    private AlertDialog.Builder builder;
    private Dialog deletarDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.acao_layout);
        inicializaComps();

        switch (getIntent().getStringExtra("acao")){
            case "cadastrar":

                btAcao.setText("cadastrar");

                tv = new Televisao();

                btAcao.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        cadastro();

                        boolean salvou = tvDAO.salvar(tv);

                        if (salvou){
                            Toast.makeText(getApplicationContext(), "Salvo!", Toast.LENGTH_LONG).show();
                        }else {
                            Toast.makeText(getApplicationContext(), "Erro ao salvar", Toast.LENGTH_LONG).show();
                        }

                        finish();
                    }
                });

                break;
            case "editar":

                deletarDialog = createDeletarDialog();

                btDeletar.setVisibility(View.VISIBLE);

                btAcao.setText("Editar");

                tv = (Televisao) getIntent().getSerializableExtra("tv");

                acMarca.setText(tv.getMarca());

                etModelo.setText(tv.getModelo());

                etPeso.setText(tv.getPeso());

                if (tv.getTemCc().equals("Sim")){
                    cbCc.toggle();
                }

                if (tv.getTemCd().equals("Sim")){
                    cbCd.toggle();
                }

                if (tv.getTemSap().equals("Sim")){
                    cbSap.toggle();
                }

                switch (tv.getResolucao()){
                    case "4K":
                        rb4k.toggle();
                        break;
                    case "Full HD":
                        rbFullHd.toggle();
                        break;
                    case "HD":
                        rbHd.toggle();
                        break;
                }

                btAcao.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        cadastro();

                        boolean editou = tvDAO.editar(tv);

                        if (editou){
                            Toast.makeText(getApplicationContext(), "Editado!", Toast.LENGTH_LONG).show();
                        }else{
                            Toast.makeText(getApplicationContext(), "Erro ao editar", Toast.LENGTH_LONG).show();
                        }

                        finish();
                    }
                });

                btDeletar.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        deletarDialog.show();
                    }
                });

                break;
        }

    }

    private void inicializaComps(){
        this.btAcao = (Button) findViewById(R.id.bt_acao);
        this.btDeletar = (Button) findViewById(R.id.bt_deletar);
        this.acMarca = (AutoCompleteTextView) findViewById(R.id.ac_marca);
        this.etModelo = (EditText) findViewById(R.id.et_modelo);
        this.etPeso = (EditText) findViewById(R.id.et_peso);
        this.cbCc = (CheckBox) findViewById(R.id.cb_cc);
        this.cbCd = (CheckBox) findViewById(R.id.cb_cd);
        this.cbSap = (CheckBox) findViewById(R.id.cb_sap);
        this.rgRes = (RadioGroup) findViewById(R.id.rg_res);
        this.rb4k = (RadioButton) findViewById(R.id.rb_4k);
        this.rbFullHd = (RadioButton) findViewById(R.id.rb_full_hd);
        this.rbHd = (RadioButton) findViewById(R.id.rb_hd);
        this.marcasAdapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.marcas));
        this.acMarca.setAdapter(marcasAdapter);
        this.tvDAO = new TelevisaoDAO(getApplicationContext());
    }

    private void cadastro(){

        tv.setMarca(acMarca.getText().toString());

        tv.setModelo(etModelo.getText().toString());

        tv.setPeso(etPeso.getText().toString());

        if(cbCc.isChecked()){
            tv.setTemCc("Sim");
        }else{
            tv.setTemCc("Nao");
        }

        if(cbCd.isChecked()){
            tv.setTemCd("Sim");
        }else{
            tv.setTemCd("Nao");
        }

        if(cbSap.isChecked()){
            tv.setTemSap("Sim");
        }else{
            tv.setTemSap("Nao");
        }

        switch (rgRes.getCheckedRadioButtonId()){
            case R.id.rb_4k:
                tv.setResolucao("4K");
                break;
            case R.id.rb_full_hd:
                tv.setResolucao("Full HD");
                break;
            case R.id.rb_hd:
                tv.setResolucao("HD");
                break;
        }

    }

    private Dialog createDeletarDialog(){
        builder = new AlertDialog.Builder(AcaoActivity.this);

        builder.setMessage("Deletar?");

        builder.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                boolean deletou = tvDAO.deletar(tv);

                if (deletou){
                    Toast.makeText(getApplicationContext(), "Deletado!", Toast.LENGTH_LONG).show();
                }else{
                    Toast.makeText(getApplicationContext(), "Erro", Toast.LENGTH_LONG).show();
                }

                finish();

            }
        });

        builder.setNegativeButton("Não", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.cancel();
            }
        });

        return builder.create();
    }

}
