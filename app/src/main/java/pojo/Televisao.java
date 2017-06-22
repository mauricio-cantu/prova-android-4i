package pojo;

import java.io.Serializable;

/**
 * Created by mauriciocantu on 22/06/17.
 */

public class Televisao implements Serializable {

    private String id, marca, modelo, peso, temCc, temCd, temSap, resolucao;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getPeso() {
        return peso;
    }

    public void setPeso(String peso) {
        this.peso = peso;
    }

    public String getTemCc() {
        return temCc;
    }

    public void setTemCc(String temCc) {
        this.temCc = temCc;
    }

    public String getTemCd() {
        return temCd;
    }

    public void setTemCd(String temCd) {
        this.temCd = temCd;
    }

    public String getTemSap() {
        return temSap;
    }

    public void setTemSap(String temSap) {
        this.temSap = temSap;
    }

    public String getResolucao() {
        return resolucao;
    }

    public void setResolucao(String resolucao) {
        this.resolucao = resolucao;
    }

    private String exibirFuncionalidades(){
        String retorno = "";

        if (this.temCc.equals("Sim")){
            retorno += "Closed Caption ";
        }

        if(this.temCd.equals("Sim")){
            retorno += "Canal Digital ";
        }

        if (this.getTemSap().equals("Sim")){
            retorno += "Função SAP";
        }

        if(retorno.equals("")){
            retorno="Indefinido";
        }

        return retorno;
    }

    @Override
    public String toString() {
        return this.marca + " : " + this.modelo + " : " + this.peso + " : " + exibirFuncionalidades() + " : " + this.resolucao;
    }
}
