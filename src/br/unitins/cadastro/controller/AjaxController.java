package br.unitins.cadastro.controller;

import java.io.Serializable;

import javax.faces.view.ViewScoped;
import javax.inject.Named;

@Named
@ViewScoped
public class AjaxController implements Serializable{

	private static final long serialVersionUID = -4276358492746565902L;
	private	String nome;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

}
