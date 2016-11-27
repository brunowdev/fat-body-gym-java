package br.edu.fasatc.ec.fatbodygym.model.comparators;

import java.util.Comparator;

import br.edu.fasatc.ec.fatbodygym.model.Aluno;

public class AlunoComparator implements Comparator<Aluno> {

	@Override
	public int compare(Aluno o1, Aluno o2) {
		return o1.getNome().compareTo(o2.getNome());
	}

}
