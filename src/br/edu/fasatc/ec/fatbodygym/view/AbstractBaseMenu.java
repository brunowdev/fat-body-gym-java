package br.edu.fasatc.ec.fatbodygym.view;

import java.util.Scanner;

/**
 * Menu base para os menus da aplica��o
 *
 * @author BRUNO-PC
 */
public abstract class AbstractBaseMenu {

    public abstract int getOpcaoMinima();

    public abstract int getOpcaoMaxima();
    protected Scanner scanner;

    public AbstractBaseMenu(Scanner scanner) {
      
        if (scanner == null) {
            throw new NullPointerException("Scanner inv�lido.");
        }
        
        this.scanner = scanner;
    }

    public abstract int selecionarOpcao();

}
