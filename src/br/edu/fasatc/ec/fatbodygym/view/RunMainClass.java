package br.edu.fasatc.ec.fatbodygym.view;

import br.edu.fasatc.ec.fatbodygym.view.login.LoginGUI;
import java.awt.Frame;

/**
 * Abre a tela de login.
 * 
 * @author BRUNO-PC
 */
public class RunMainClass {

    public static void main(String[] args) {

        Frame jf = new Frame();
        LoginGUI login = new LoginGUI(jf, true);
        login.setVisible(true);

    }

}
