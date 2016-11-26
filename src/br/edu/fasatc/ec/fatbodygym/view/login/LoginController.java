package br.edu.fasatc.ec.fatbodygym.view.login;

import javax.swing.JOptionPane;

import br.edu.fasatc.ec.fatbodygym.exceptions.EntidadeNaoEncontradaException;
import br.edu.fasatc.ec.fatbodygym.exceptions.ReadFileException;
import br.edu.fasatc.ec.fatbodygym.model.Usuario;
import br.edu.fasatc.ec.fatbodygym.persistence.repository.UsuarioRepository;

/**
 *
 * @author BRUNO-PC
 */
public class LoginController {

	public Usuario getUsuarioPorEmail(String email) throws ReadFileException, EntidadeNaoEncontradaException {
		final UsuarioRepository uRepository = new UsuarioRepository();
		return uRepository.findByStringFields(email);
	}

	public boolean isNomeEmailUsuarioDisponivel(String usuarioEmail) throws ReadFileException {
		final UsuarioRepository uRepository = new UsuarioRepository();

		try {
			uRepository.findByStringFields(usuarioEmail);
		} catch (final EntidadeNaoEncontradaException e) {
			return true;
		}

		return false;
	}

	public boolean login(Usuario usuario) throws ReadFileException, EntidadeNaoEncontradaException {

		if (!isValido(usuario)) {
			return false;
		}

		Usuario usuarioLocalizado = null;

		final UsuarioRepository uRepo = new UsuarioRepository();

		try {
			usuarioLocalizado = uRepo.findByStringFields(usuario.getEmail());
			return true;
		} catch (final EntidadeNaoEncontradaException e) {
		}

		if (usuarioLocalizado == null || !usuarioLocalizado.getSenha().equals(usuario.getSenha())) {
			JOptionPane.showMessageDialog(null, "Usuário e/ou senha inválidos.", "Acesso não permitido", JOptionPane.ERROR_MESSAGE);
		}

		return false;
	}

	private boolean isValido(Usuario usuario) {

		if (usuario.getEmail() == null || usuario.getEmail().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Por favor, informe um e-mail.", "Campos não prenchidos", JOptionPane.ERROR_MESSAGE);
			return false;
		}

		if (usuario.getSenha() == null || usuario.getSenha().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Por favor, informe uma senha.", "Campos não prenchidos", JOptionPane.ERROR_MESSAGE);
			return false;
		}

		return true;
	}

}
