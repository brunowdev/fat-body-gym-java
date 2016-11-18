package br.edu.fasatc.ec.fatbodygym.view.login;

import br.edu.fasatc.ec.fatbodygym.exceptions.ReadFileException;
import br.edu.fasatc.ec.fatbodygym.model.Usuario;
import br.edu.fasatc.ec.fatbodygym.persistence.repository.UsuarioRepository;
import javax.swing.JOptionPane;

/**
 *
 * @author BRUNO-PC
 */
public class LoginController {

    public boolean login(Usuario usuario) throws ReadFileException {

        if (!isValido(usuario)) {
            return false;
        }

        UsuarioRepository uRepo = new UsuarioRepository();
        Usuario usuarioLocalizado = uRepo.findByStringFields(usuario.getEmail());

        if (usuarioLocalizado == null || !usuarioLocalizado.getSenha().equals(usuario.getSenha())) {
            JOptionPane.showMessageDialog(null, "Usuário e/ou senha inválidos.", "Acesso não permitido", JOptionPane.ERROR_MESSAGE);
        }

        return true;
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
