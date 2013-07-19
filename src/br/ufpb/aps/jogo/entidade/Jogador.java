package br.ufpb.aps.jogo.entidade;

public class Jogador {

	private String nome;
	private String senha;
	private int score;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public void aumentarScore() {
		score += 3;

	}

	public void diminuirScore() {
		score -= 1;
	}

	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Jogador))
			return false;
		Jogador other = (Jogador) obj;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!senha.equals(other.senha))
			return false;
		return true;
	}

}
