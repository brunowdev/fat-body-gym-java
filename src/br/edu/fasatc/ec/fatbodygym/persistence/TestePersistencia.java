package br.edu.fasatc.ec.fatbodygym.persistence;

import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

import br.edu.fasatc.ec.fatbodygym.model.Usuario;

public class TestePersistencia {

	public static void save() throws IOException, ClassNotFoundException {
		final List<Usuario> usuarios = lerExistentes();

		final FileOutputStream fileOutputStream = new FileOutputStream("vw_usuarios.ser");
		final ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);

		final Usuario u = new Usuario();

		u.setSenha("123456");
		u.setEmail("myemail@gmail.com");

		usuarios.add(u);

		for (final Usuario us : usuarios) {
			System.out.println(us.getEmail());
			objectOutputStream.writeObject(us);
		}

		objectOutputStream.close();

	}

	public static void main(String[] args) throws IOException, ClassNotFoundException {

		save();

	}

	public static List<Usuario> lerExistentes() throws IOException, ClassNotFoundException {

		final FileInputStream fileInputStream = new FileInputStream("vw_usuarios.ser");
		final ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);

		final List<Usuario> usuarios = new ArrayList<>();

		int x = 0;

		try {

			while (true) {
				usuarios.add((Usuario) objectInputStream.readObject());
				x++;
			}

		} catch (final EOFException e) {
		}

		System.out.println(x);

		return usuarios;
	}

}
