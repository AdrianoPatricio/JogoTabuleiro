package ufpb.br.aps.jogo.teste;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Before;
import org.junit.Test;



import ufpb.br.aps.jogo.entidade.Questao;
import ufpb.br.aps.jogo.excecoes.ExcecaoJogoTabuleiro;
import ufpb.br.aps.jogo.fachada.Jogo;

//import ufpb.br.aps.jogo.excecoes.ExcecaoNomeJogador;

public class JogoTest {

	private Jogo jogo;


	@Before
	public void criarJogo() {
		jogo = new Jogo();
	}

	// teste 1
	@Test
	public void iniciarJogo() {
		assertFalse("O jogo iniciou acabado", jogo.acabou());
	}

	// teste 2
	@Test
	public void definirPersonagemX() {
		jogo.setEscolhaPersonagemX(true);
		assertTrue("Esperava que o primeiro personagem fosse X",
				jogo.isEscolhaPersonagemX());
	}

	// teste 3
	@Test
	public void definirPersonagemXDeNovo() {
		jogo.setEscolhaPersonagemX(true);
		jogo.setEscolhaPersonagemX(false);
		assertFalse("esperava que o primeiro personagem fosse Y",
				jogo.isEscolhaPersonagemX());
	}

	// teste 4
	@Test(expected = ExcecaoJogoTabuleiro.class)
	public void definirPersonagemXAposInicio() {
		jogo.setEscolhaPersonagemX(true);
		jogo.lancarDado();
		jogo.setEscolhaPersonagemX(false);
	}

	// teste 5
	@Test(expected = ExcecaoJogoTabuleiro.class)
	public void jogarDadoAntesDeDefinirPersonagem() {
		jogo.lancarDado();
	}

	// teste 6
	@Test(expected = ExcecaoJogoTabuleiro.class)
	public void moverPersonagemPosicaoIrregular() {
		jogo.setEscolhaPersonagemX(true);
		jogo.moverPersonagemX(-1);
	}

	// teste 7
	@Test
	public void scoreZero() {
		jogo.setEscolhaPersonagemX(true);
		assertEquals(jogo.getScore(), 0);
	}

	// teste 8
	@Test
	public void verificarPosicaoInicialPersonagemX() {
		jogo.setEscolhaPersonagemX(true);
		assertTrue(jogo.getPosicaoPersonagem() == 0);
	}

	// teste 9
	@Test
	public void jogarDado() {
		jogo.setEscolhaPersonagemX(true);
		assertTrue("espera-se um numero > 0 e < 7", jogo.lancarDado() > 0
				&& jogo.lancarDado() < 7);
	}

	// teste 10
	@Test
	public void verificarRespostaPersonagemX() {
		jogo.setEscolhaPersonagemX(true);
		jogo.lancarDado();
		jogo.questao("pergunta", new String[] { "a", "b", "c" }, "a");
		jogo.setRespostaPersonagemX("a");
		assertTrue("Espera-se que o personagem responda correto o desafio",
				jogo.isResultadoQuestao());
	}

	// teste 11
	@Test
	public void verificarPosicaoAposAcerto() {
		jogo.setEscolhaPersonagemX(true);
		jogo.lancarDado();
		jogo.questao("questao", new String[] { "a", "b", "c" }, "a");
		jogo.setRespostaPersonagemX("a");
		assertTrue("Espera-se que o personagem esteja na posicao 1",
				jogo.getPosicaoPersonagem() == 1);
	}

	// teste 12
	@Test
	public void verificarPosicaoAposErro() {
		jogo.setEscolhaPersonagemX(true);
		jogo.lancarDado();
		jogo.questao("questao", new String[] { "a", "b", "c" }, "a");
		jogo.setRespostaPersonagemX("b");
		assertTrue("Espera-se que o personagem esteja na posicao 0",
				jogo.getPosicaoPersonagem() == 0);
	}

	// teste 13
	@Test(expected = ExcecaoJogoTabuleiro.class)
	public void verificarValorIrregularScore() {
		jogo.setEscolhaPersonagemX(true);
		jogo.setScore(-1);
	}

	// teste 14
	@Test
	public void verificarScoreAposAcerto() {
		jogo.setEscolhaPersonagemX(true);
		jogo.lancarDado();
		jogo.questao("questao", new String[] { "a", "b", "c" }, "a");
		jogo.setRespostaPersonagemX("a");
		assertEquals(3, jogo.getScore());
	}

	// teste 15
	@Test
	public void verificarScoreAposErro() {
		jogo.setEscolhaPersonagemX(true);
		jogo.lancarDado();
		jogo.questao("questao", new String[] { "a", "b", "c" }, "a");
		jogo.setRespostaPersonagemX("b");
		assertEquals(0, jogo.getScore());
	}

	// teste 16
	@Test
	public void verificarScoreAposAcertoeAposErro() {
		jogo.setEscolhaPersonagemX(true);
		jogo.lancarDado();
		jogo.questao("questao", new String[] { "a", "b", "c" }, "a");
		jogo.setRespostaPersonagemX("a");
		jogo.lancarDado();
		jogo.questao("questao", new String[] { "a", "b", "c" }, "a");
		jogo.setRespostaPersonagemX("b");
		jogo.isResultadoQuestao();
		assertEquals(2, jogo.getScore());
	}

	// teste 17
	@Test
	public void jogoGanho() {
		jogo.setEscolhaPersonagemX(true);
		jogo.lancarDado();
		jogo.questao("questao", new String[] { "a", "b", "c" }, "a");
		jogo.setRespostaPersonagemX("a");
		jogo.lancarDado();
		jogo.questao("questao", new String[] { "a", "b", "c" }, "a");
		jogo.setRespostaPersonagemX("a");
		jogo.lancarDado();
		jogo.questao("questao", new String[] { "a", "b", "c" }, "a");
		jogo.setRespostaPersonagemX("a");
		assertTrue("Espera-se que o personagem X tenha ganhado o jogo",
				jogo.acabou());
	}

	// teste 18
	@Test(expected = ExcecaoJogoTabuleiro.class)
	public void lancarDadoAposJogoGanho() {
		jogo.setEscolhaPersonagemX(true);
		jogo.lancarDado();
		jogo.questao("questao", new String[] { "a", "b", "c" }, "a");
		jogo.setRespostaPersonagemX("a");
		jogo.lancarDado();
		jogo.questao("questao", new String[] { "a", "b", "c" }, "a");
		jogo.setRespostaPersonagemX("a");
		jogo.lancarDado();
		jogo.questao("questao", new String[] { "a", "b", "c" }, "a");
		jogo.setRespostaPersonagemX("a");
		jogo.lancarDado();
	}

	// teste 19
	@Test
	public void verificarCasaSurpresa() {
		jogo.setEscolhaPersonagemX(true);
		jogo.lancarDado();
		jogo.questao("questao", new String[] { "a", "b", "c" }, "a");
		jogo.setRespostaPersonagemX("a");
		jogo.setSurpresa(true);
		assertTrue("Espera-se que casa contenha uma surpresa",
				jogo.isSurpresa());
	}

	// teste 20
	@Test
	public void surpresaBoa() {
		jogo.setEscolhaPersonagemX(true);
		jogo.lancarDado();
		jogo.questao("questao", new String[] { "a", "b", "c" }, "a");
		jogo.setRespostaPersonagemX("a");
		jogo.setSurpresa(true);
		assertTrue("Espera-se que casa contenha uma surpresa boa",
				(jogo.getPosicaoPersonagem() == 2));
	}

	// teste 21
	@Test
	public void surpresaRuim() {
		jogo.setEscolhaPersonagemX(true);
		jogo.lancarDado();
		jogo.questao("questao", new String[] { "a", "b", "c" }, "a");
		jogo.setRespostaPersonagemX("a");
		jogo.setSurpresa(true);
		assertFalse("Espera-se que casa contenha uma surpresa ruim",
				(jogo.surpresa(0)));
	}

	// teste 22
	@Test
	public void verificaPosicaoAposSupresa() {
		jogo.setEscolhaPersonagemX(true);
		jogo.lancarDado();
		jogo.questao("questao", new String[] { "a", "b", "c" }, "a");
		jogo.setRespostaPersonagemX("a");
		jogo.setSurpresa(true);
		assertTrue("Espera-se que a posicao seja 2",
				jogo.getPosicaoPersonagem() == 2);
	}

	// teste 23
	@Test(expected = ExcecaoJogoTabuleiro.class)
	public void NaoMostrarQuestaoAntesDeJogarDado() {
		jogo.setEscolhaPersonagemX(true);
		jogo.questao("pergunta", new String[] { "a", "b", "c" }, "a");
	}

	// teste 24
	@Test(expected = ExcecaoJogoTabuleiro.class)
	public void escolherRespostaAntesDeMostrarQuestaoTest() {
		jogo.setEscolhaPersonagemX(true);
		jogo.lancarDado();
		jogo.setRespostaPersonagemX("a");
	}

	// teste 25
	@Test(expected = ExcecaoJogoTabuleiro.class)
	public void escolherQuestaoInexistente() {
		jogo.setEscolhaPersonagemX(true);
		jogo.lancarDado();
		jogo.questao("pergunta", new String[] { "a", "b", "c" }, "a");
		jogo.setRespostaPersonagemX("d");
		assertFalse("Espera-se que a resposta n�o seja valida",
				jogo.respostaValida(jogo.getRespostaPersonagemX()));
	}

	// teste 26
	@Test
	public void respostaValidaTest() {
		jogo.setEscolhaPersonagemX(true);
		jogo.lancarDado();
		jogo.questao("pergunta", new String[] { "a", "b", "c" }, "a");
		jogo.setRespostaPersonagemX("a");
		assertEquals(true, jogo.respostaValida(jogo.getRespostaPersonagemX()));
	}

	// teste 27
	public void respostaInvalidaTest() {
		jogo.setEscolhaPersonagemX(true);
		jogo.lancarDado();
		jogo.questao("pergunta", new String[] { "a", "b", "c" }, "a");
		jogo.setRespostaPersonagemX("d");
		assertEquals(false, jogo.respostaValida(jogo.getRespostaPersonagemX()));
	}

	// teste 28
	@Test
	public void verificaRespostaDoPersonagemX() {
		jogo.setEscolhaPersonagemX(true);
		jogo.lancarDado();
		jogo.questao("pergunta", new String[] { "a", "b", "c" }, "a");
		jogo.setRespostaPersonagemX("a");
		assertEquals("a", jogo.getRespostaPersonagemX());
	}

	// teste 29
	@Test
	public void testValorDadoAntesDeCome�arJogo() {
		jogo.setEscolhaPersonagemX(true);
		assertTrue(jogo.getValorDoDado() == 0);
	}

	// teste 30
	@Test
	public void verificarPosicaoAposAcertoEAposErro() {
		jogo.setEscolhaPersonagemX(true);
		jogo.lancarDado();
		jogo.questao("questao", new String[] { "a", "b", "c" }, "a");
		jogo.setRespostaPersonagemX("a");
		jogo.lancarDado();
		jogo.questao("questao", new String[] { "a", "b", "c" }, "b");
		jogo.setRespostaPersonagemX("a");
		assertTrue("Espera-se que o personagem esteja na posicao 1",
				jogo.getPosicaoPersonagem() == 1);
	}

	// teste 31
	@Test
	public void verificarScoreSalvo() {
		jogo.setEscolhaPersonagemX(true);
		jogo.lancarDado();
		jogo.questao("pergunta", new String[] { "a", "b", "c" }, "a");
		jogo.setRespostaPersonagemX("a");
		jogo.questao("pergunta", new String[] { "a", "b", "c" }, "b");
		jogo.setRespostaPersonagemX("b");
		jogo.questao("pergunta", new String[] { "a", "b", "c" }, "c");
		jogo.setRespostaPersonagemX("c");
		assertTrue("Espera-se que o score seja 9", jogo.getScore() == 9);
	}

	// teste 32
	@Test
	public void verificaPontuacaoDeUmJogadorDeterminado() {
		jogo.setEscolhaPersonagemX(true);
		jogo.setNomeJogador("Lucas");
		jogo.lancarDado();
		jogo.questao("pergunta", new String[] { "a", "b", "c" }, "a");
		jogo.setRespostaPersonagemX("a");
		jogo.encerrarAntesDoTempo();
	//	assertEquals("Espera o valor 3", 3, jogo.verificaPontuacao("Lucas"));
	}

	// teste 33
	@Test
	public void verificarJogoEncerradoAntesDoTempo() {
		jogo.setEscolhaPersonagemX(true);
		jogo.lancarDado();
		jogo.questao("pergunta", new String[] { "a", "b", "c" }, "a");
		jogo.setRespostaPersonagemX("a");
		jogo.encerrarAntesDoTempo();
		assertTrue("Espera que o jogo tenha terminado antes da hora",
				jogo.encerrarAntesDoTempo() == true);

	}

	// teste 34
	@Test
	public void verificarScoreDePartidaCancelada() {
		jogo.setEscolhaPersonagemX(true);
		jogo.lancarDado();
		jogo.questao("pergunta", new String[] { "a", "b", "c" }, "a");
		jogo.setRespostaPersonagemX("a");
		jogo.questao("pergunta", new String[] { "a", "b", "c" }, "b");
		jogo.setRespostaPersonagemX("b");
		jogo.encerrarAntesDoTempo();
		assertTrue("Espera-se que o score seja 6", jogo.getScore() == 6);
	}

	// teste 35
	@Test
	public void testNomeJogador() {
		jogo.setEscolhaPersonagemX(true);
		jogo.setNomeJogador("Joao");
		assertTrue("Espera-se que o personagem seja Joao",
				jogo.getNomeJogador() == "Joao");
	}

	// teste 36
	@Test
	public void testTamanhoNomeAceitavel() {
		jogo.setEscolhaPersonagemX(true);
		jogo.setNomeJogador("Luc");
		jogo.getTamanhoNome();
		assertTrue("Espera-se que o personagem seja Luc",
				jogo.getNomeJogador() == "Luc");
		assertEquals(3, jogo.getTamanhoNome());
	}

	@Test
	public void cadastrarQuestao() {
		//criarQuestao()
		Questao questao1 = new Questao();
		questao1.setPergunta("Pergunta");
		questao1.setAlternativas(new String[] { "a", "b", "c" });
		questao1.setGabarito("a");

		jogo.cadastrarQuestao(questao1);

		List<Questao> questoesSalvas = jogo.listarQuestoes();
		assertEquals(1, questoesSalvas.size());

		Questao questao1Salva = questoesSalvas.get(0);
		assertEquals(questao1, questao1Salva);
	}
	@Test(expected = ExcecaoJogoTabuleiro.class)
	public void cadastrarQuestaoNovamente(){
		
		Questao questao = criarQuestao();
		jogo.cadastrarQuestao(questao);
		jogo.cadastrarQuestao(questao);
		
	}
	@Test
	public void removerQuestao(){
		Questao questao = criarQuestao();

		jogo.cadastrarQuestao(questao);
		
		List<Questao> questoesSalvas = jogo.listarQuestoes();
		Questao questaoSalva = questoesSalvas.get(0);
		assertEquals(questao, questaoSalva);
		jogo.removerQuestao(questao);
		assertTrue(questoesSalvas.size()==0);
		
	}
	@Test(expected = ExcecaoJogoTabuleiro.class)
	public void removerQuestaoDeNovo(){
		Questao questao = criarQuestao();

		jogo.cadastrarQuestao(questao); // Devia estar no gerente de quest�o
		
		List<Questao> questoesSalvas = jogo.listarQuestoes();
		Questao questaoSalva = questoesSalvas.get(0);
		assertEquals(questao, questaoSalva);
		jogo.removerQuestao(questao);
		jogo.removerQuestao(questao);
	}

	private Questao criarQuestao() {
		return new Questao();  // Devia ficar no gerente de quest�o = questionario
	}
	
	
	
	
	
	

}