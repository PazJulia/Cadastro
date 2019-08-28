package br.unitins.cadastro.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Named;

import br.unitins.cadastro.application.Util;
import br.unitins.cadastro.model.Perfil;
import br.unitins.cadastro.model.Telefone;
import br.unitins.cadastro.model.Usuario;

@Named
@ViewScoped
public class UsuarioController implements Serializable {

	private static final long serialVersionUID = -398646171892892491L;

	private Usuario usuario;
	
	private Telefone telefone;
	
	private List<Usuario> listaUsuario;
	
	
	public void verificarId() {
		for (Usuario u : listaUsuario) {
			if (u.getId().equals(getUsuario().getId())) {
				Util.addMessageError("Este id ja existe. Informe outro.");
				return;
			}
		}
	}

	public List<Usuario> getListaUsuario() {
		if (listaUsuario == null) {
			listaUsuario = new ArrayList<Usuario>();
			listaUsuario.add(new Usuario(1, "Joao", "joao", "123", Perfil.FUNCIONARIO));
			listaUsuario.add(new Usuario(2, "Maria", "maria", "321", Perfil.ADMINISTRADOR));
		}
		return listaUsuario;
	}
	
	public Perfil[] getListaPerfil() {
		return Perfil.values();
	}

	public Usuario getUsuario() {
		if (usuario == null)
			usuario = new Usuario();
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	
	public Telefone getTelefone() {
		if (telefone == null)
			telefone = new Telefone();
		return telefone;
	}

	public void setTelefone(Telefone telefone) {
		this.telefone = telefone;
	}

	public void editar(Usuario usuario) {
		setUsuario((Usuario)usuario.getClone());
	}
	
	public void adicionarTelefone() {
		if (getUsuario().getListaTelefone() == null) 
			getUsuario().setListaTelefone(new ArrayList<Telefone>());
		
		getUsuario().getListaTelefone().add(getTelefone());
		setTelefone(null);
	}
	
	public void removerTelefone(Telefone telefone) {
		getUsuario().getListaTelefone().remove(telefone);
	}
	
	public void incluir() {
		// validacao de senha no servidor
		if (getUsuario().getSenha() == null || 
			getUsuario().getSenha().trim().equals("") ) {
			Util.addMessageError("A senha deve ser informada.");
			return;
		}
		
		getListaUsuario().add(getUsuario());
		limpar();
	}
	
	public void alterar() {	
		// validacao de senha no servidor
		if (getUsuario().getSenha() == null || 
				getUsuario().getSenha().trim().equals("") ) {
				Util.addMessageError("A senha deve ser informada.");
				return;
		}
		
		// obtendo o indice (posicao da lista)
		int index = getListaUsuario().indexOf(getUsuario());
		System.out.println(getUsuario().getId());
		if (index != -1) {
			// alterando a posicao da lista com um novo usuario
			getListaUsuario().set(index, getUsuario());
			limpar();
		}
			
	}
	
	public void excluir() {
		int index = getListaUsuario().indexOf(getUsuario());
		getListaUsuario().remove(index);
		if (index != -1)
			limpar();
	}
	
	public void limpar() {	
		usuario = null;
		telefone = null;
	}

}
