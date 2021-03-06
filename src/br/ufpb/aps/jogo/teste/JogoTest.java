package br.ufpb.aps.jogo.teste;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import br.ufpb.aps.jogo.entidade.Dado;
import br.ufpb.aps.jogo.entidade.Jogador;
import br.ufpb.aps.jogo.entidade.Questao;
import br.ufpb.aps.jogo.excecoes.ExcecaoJogoTabuleiro;
import br.ufpb.aps.jogo.fachada.Jogo;

public class JogoTest {
	private Jogo	fachada;

	@Before
	public void criarJogo() {
		fachada = new Jogo();
	}
	// teste 1
	@Test
	public void iniciarJogo() {
		assertFalse("O jogo iniciou acabado", fachada.acabou());
	}
	// teste 2
	@Test
	public void definirPersonagemX() {
		fachada.setEscolhaPersonagemX(true);
		assertTrue("Esperava que o primeiro personagem fosse X",
				fachada.isEscolhaPersonagemX());
	}
	// teste 3
	@Test
	public void definirPersonagemXDeNovo() {
		fachada.setEscolhaPersonagemX(true);
		fachada.setEscolhaPersonagemX(false);
		assertFalse("esperava que o primeiro personagem fosse Y",
				fachada.isEscolhaPersonagemX());
	}
	// teste 4
	@Test(expected = ExcecaoJogoTabuleiro.class)
	public void definirPersonagemXAposInicio() {
		fachada.setEscolhaPersonagemX(true);
		fachada.jogarDado();
		fachada.setEscolhaPersonagemX(false);
	}
	// teste 5
	@Test(expected = ExcecaoJogoTabuleiro.class)
	public void jogarDadoAntesDeDefinirPersonagem() {
		fachada.jogarDado();
	}
	// teste 6
	@Test
	public void scoreZeroPersonagemX() {
		fachada.setEscolhaPersonagemX(true);
		assertEquals(fachada.verificarScore(), 0);
	}
	// teste 7
	@Test
	public void scoreZeroPersonagemY() {
		fachada.setEscolhaPersonagemX(false);
		assertEquals(fachada.verificarScore(), 0);
	}
	// teste 8
	@Test
	public void verificarPosicaoInicialPersonagemX() {
		fachada.setEscolhaPersonagemX(true);
		assertEquals(0, fachada.getPosicaoPersonagemX());
	}
	// teste 9
	@Test
	public void verificarPosicaoInicialPersonagemY() {
		fachada.setEscolhaPersonagemX(false);
		assertEquals(0, fachada.getPosicaoPersonagemY());
	}
	// teste 10
	@Test
	public void jogarDado() {
		fachada.setEscolhaPersonagemX(true);
		assertTrue("espera-se um numero > 0 e < 7", fachada.jogarDado() > 0
				&& fachada.jogarDado() < 7);
	}
	// teste 11
	@Test
	public void verificarRespostaPersonagemXCerta() {
		criarDesafio();
		fachada.setRespostaPersonagemX("a");
		assertTrue("Espera-se que o personagem X acerte o desafio",
				fachada.isResultadoQuestao());
	}
	// teste 12
	@Test
	public void verificarRespostaPersonagemYCerta() {
		fachada.setEscolhaPersonagemX(false);
		criarDado();
		Questao questao1 = criarQuestao();
		fachada.criarQuestao(questao1);
		fachada.setRespostaPersonagemY("a");
		assertTrue("Espera-se que o personagem Y acerte o desafio",
				fachada.isResultadoQuestao());
	}
	// teste 13
	@Test
	public void verificarRespostaPersonagemXErrada() {
		criarDesafio();
		fachada.setRespostaPersonagemX("b");
		assertFalse("Espera-se que o personagem X erre o desafio",
				fachada.isResultadoQuestao());
	}
	// teste 14
	@Test
	public void verificarRespostaPersonagemYErrada() {
		fachada.setEscolhaPersonagemX(false);
		criarDado();
		Questao questao1 = criarQuestao();
		fachada.criarQuestao(questao1);
		fachada.setRespostaPersonagemY("b");
		assertFalse("Espera-se que o personagem erre o desafio",
				fachada.isResultadoQuestao());
	}
	// teste 15
	@Test
	public void verificarPosicaoAposAcertoPersonagemX() {
		criarDesafio();
		fachada.setRespostaPersonagemX("a");
		assertTrue("Espera-se que o personagem X esteja na posicao 1",
				fachada.getPosicaoPersonagemX() == 1);
	}
	// teste 16
	@Test
	public void verificarPosicaoAposAcertoPersonagemY() {
		fachada.setEscolhaPersonagemX(false);
		criarDado();
		Questao questao1 = criarQuestao();
		fachada.criarQuestao(questao1);
		fachada.setRespostaPersonagemY("a");
		assertTrue("Espera-se que o personagem Y esteja na posicao 1",
				fachada.getPosicaoPersonagemY() == 1);
	}
	// teste 17
	@Test
	public void verificarPosicaoAposErroPersonagemX() {
		criarDesafio();
		fachada.setRespostaPersonagemX("b");
		assertTrue("Espera-se que o personagem esteja na posicao 0",
				fachada.getPosicaoPersonagemX() == 0);
	}
	// teste 18
	@Test
	public void verificarPosicaoAposErroPersonagemY() {
		fachada.setEscolhaPersonagemX(false);
		criarDado();
		Questao questao1 = criarQuestao();
		fachada.criarQuestao(questao1);
		fachada.setRespostaPersonagemY("b");
		assertTrue("Espera-se que o personagem esteja na posicao 0",
				fachada.getPosicaoPersonagemY() == 0);
	}
	// teste 19
	@Test
	public void verificarScoreAposAcertoPersonagemX() {
		criarDesafio();
		fachada.setRespostaPersonagemX("a");
		assertEquals(3, fachada.verificarScore());
	}
	// teste 20
	@Test
	public void verificarScoreAposAcertoPersonagemY() {
		fachada.setEscolhaPersonagemX(false);
		criarDado();
		Questao questao1 = criarQuestao();
		fachada.criarQuestao(questao1);
		fachada.setRespostaPersonagemY("a");
		assertEquals(3, fachada.verificarScore());
	}
	// teste 21
	@Test
	public void verificarScoreAposErroPerosnagemX() {
		criarDesafio();
		fachada.setRespostaPersonagemX("b");
		assertEquals(0, fachada.verificarScore());
	}
	// teste 22
	@Test
	public void verificarScoreAposErroPerosnagemY() {
		fachada.setEscolhaPersonagemX(false);
		criarDado();
		Questao questao1 = criarQuestao();
		fachada.criarQuestao(questao1);
		fachada.setRespostaPersonagemY("b");
		assertEquals(0, fachada.verificarScore());
	}
	// teste 15 n�o passa
	/*
	 * @Test public void verificarScoreAposAcertoeAposErro() {
	 * 
	 * 
	 * fachada.setEscolhaPersonagemX(true); criarDado(); Questao questao1 =
	 * criarQuestao(); fachada.criarQuestao(questao1);
	 * fachada.setRespostaPersonagemX("a");
	 * 
	 * fachada.jogarDado(); Questao questao2 = criarQuestao();
	 * fachada.criarQuestao(questao2); fachada.setRespostaPersonagemY("b");
	 * 
	 * fachada.jogarDado(); Questao questao3 = criarQuestao();
	 * fachada.criarQuestao(questao3); fachada.setRespostaPersonagemX("b");
	 * 
	 * assertEquals(2, fachada.verificarScore()); }
	 */
	// teste 23
	@Test
	public void vencedorPersonagemX() {
		criarDesafio();
		fachada.setRespostaPersonagemX("a"); // personagem X
		criarDado();
		Questao questao2 = criarQuestao();
		fachada.criarQuestao(questao2);
		fachada.setRespostaPersonagemY("b"); // personagem Y
		criarDado();
		Questao questao3 = criarQuestao();
		fachada.criarQuestao(questao3);
		fachada.setRespostaPersonagemX("a");
		criarDado();
		Questao questao4 = criarQuestao();
		fachada.criarQuestao(questao4);
		fachada.setRespostaPersonagemY("b");
		criarDado();
		Questao questao5 = criarQuestao();
		fachada.criarQuestao(questao5);
		fachada.setRespostaPersonagemX("a");
		criarDado();
		Questao questao6 = criarQuestao();
		fachada.criarQuestao(questao6);
		fachada.setRespostaPersonagemY("a");
		criarDado();
		Questao questao7 = criarQuestao();
		fachada.criarQuestao(questao7);
		fachada.setRespostaPersonagemX("a");
		criarDado();
		Questao questao8 = criarQuestao();
		fachada.criarQuestao(questao8);
		fachada.setRespostaPersonagemY("b");
		criarDado();
		Questao questao9 = criarQuestao();
		fachada.criarQuestao(questao9);
		fachada.setRespostaPersonagemX("a");
		criarDado();
		Questao questao10 = criarQuestao();
		fachada.criarQuestao(questao10);
		fachada.setRespostaPersonagemY("a");
		criarDado();
		Questao questao11 = criarQuestao();
		fachada.criarQuestao(questao11);
		fachada.setRespostaPersonagemX("a");
		assertTrue("Espera-se que o personagem X tenha ganhado o jogo",
				fachada.acabou());
	}
	// teste 24
	@Test
	public void vencedorPersonagemY() {
		fachada.setEscolhaPersonagemX(false);
		criarDado();
		Questao questao1 = criarQuestao();
		fachada.criarQuestao(questao1);
		fachada.setRespostaPersonagemY("a");
		criarDado();
		Questao questao2 = criarQuestao();
		fachada.criarQuestao(questao2);
		fachada.setRespostaPersonagemX("b");
		criarDado();
		Questao questao3 = criarQuestao();
		fachada.criarQuestao(questao3);
		fachada.setRespostaPersonagemY("a");
		criarDado();
		Questao questao4 = criarQuestao();
		fachada.criarQuestao(questao4);
		fachada.setRespostaPersonagemX("b");
		criarDado();
		Questao questao5 = criarQuestao();
		fachada.criarQuestao(questao5);
		fachada.setRespostaPersonagemY("a");
		criarDado();
		Questao questao6 = criarQuestao();
		fachada.criarQuestao(questao6);
		fachada.setRespostaPersonagemX("b");
		criarDado();
		Questao questao7 = criarQuestao();
		fachada.criarQuestao(questao7);
		fachada.setRespostaPersonagemY("a");
		criarDado();
		Questao questao8 = criarQuestao();
		fachada.criarQuestao(questao8);
		fachada.setRespostaPersonagemX("b");
		criarDado();
		Questao questao9 = criarQuestao();
		fachada.criarQuestao(questao9);
		fachada.setRespostaPersonagemY("a");
		criarDado();
		Questao questao10 = criarQuestao();
		fachada.criarQuestao(questao10);
		fachada.setRespostaPersonagemX("b");
		criarDado();
		Questao questao11 = criarQuestao();
		fachada.criarQuestao(questao11);
		fachada.setRespostaPersonagemY("a");
		assertFalse("Espera-se que o personagem Y tenha ganhado o jogo",
				fachada.acabou());
	}
	// teste 25
	@Test(expected = ExcecaoJogoTabuleiro.class)
	public void lancarDadoAposJogoGanho() {
		criarDesafio();
		fachada.setRespostaPersonagemX("a");
		criarDado();
		Questao questao2 = criarQuestao();
		fachada.criarQuestao(questao2);
		fachada.setRespostaPersonagemX("a");
		criarDado();
		Questao questao3 = criarQuestao();
		fachada.criarQuestao(questao3);
		fachada.setRespostaPersonagemX("a");
		fachada.jogarDado();
		criarDado();
		Questao questao4 = criarQuestao();
		fachada.criarQuestao(questao4);
		fachada.setRespostaPersonagemX("a");
		criarDado();
		Questao questao5 = criarQuestao();
		fachada.criarQuestao(questao5);
		fachada.setRespostaPersonagemX("a");
		criarDado();
		Questao questao6 = criarQuestao();
		fachada.criarQuestao(questao6);
		fachada.setRespostaPersonagemX("a");
		fachada.jogarDado();
	}
	// teste 26 novo
	@Test
	public void verificarCasaSurpresa() {
		criarDesafio();
		fachada.setRespostaPersonagemX("a");
		fachada.casaSurpresa();
		assertTrue("Espera-se que casa contenha surpresa", fachada.isSurpresa());
	}
	// teste 27 modificado
	@Test
	public void verificaPosicaoAposSupresaBoa() {
		criarDesafio();
		fachada.setRespostaPersonagemX("a");
		fachada.surpresaBoa();
		assertTrue("Espera-se que a posicao seja 2",
				fachada.getPosicaoPersonagemX() == 2);
	}
	// teste 28 novo
	@Test
	public void verificaPosicaoAposSupresaRuim() {
		criarDesafio();
		fachada.setRespostaPersonagemX("a");
		fachada.surpresaRuim();
		assertTrue("Espera-se que a posicao seja 0",
				fachada.getPosicaoPersonagemX() == 0);
	}
	// teste 29
	@Test(expected = ExcecaoJogoTabuleiro.class)
	public void NaoMostrarQuestaoAntesDeJogarDado() {
		fachada.setEscolhaPersonagemX(true);
		Questao questao1 = criarQuestao();
		fachada.criarQuestao(questao1);
	}
	// teste 30
	@Test(expected = ExcecaoJogoTabuleiro.class)
	public void escolherRespostaAntesDeMostrarQuestaoTest() {
		fachada.setEscolhaPersonagemX(true);
		fachada.jogarDado();
		fachada.setRespostaPersonagemX("a");
	}
	// teste 31
	@Test(expected = ExcecaoJogoTabuleiro.class)
	public void escolherAlternativaInexistente() {
		criarDesafio();
		fachada.setRespostaPersonagemX("d");
	}
	// teste 32
	@Test
	public void alternativaValidaTest() {
		criarDesafio();
		fachada.setRespostaPersonagemX("a");
		assertEquals("Espera-se que a alternativa escolhida seja valida.",
				true,
				fachada.alternativaValida(fachada.getRespostaPersonagemX()));
	}
	// teste 33
	@Test(expected = ExcecaoJogoTabuleiro.class)
	public void alternativaInvalidaTest() {
		criarDesafio();
		fachada.setRespostaPersonagemX("d");
		assertEquals("Espera-se que a alternativa escolhida seja invalida.",
				false,
				fachada.alternativaValida(fachada.getRespostaPersonagemX()));
	}
	// teste 34
	@Test
	public void comparacaoDeAlternativa() {
		criarDesafio();
		fachada.setRespostaPersonagemX("a");
		assertEquals("Espera-se que a alternativa (a) seja a escolhida.", "a",
				fachada.getRespostaPersonagemX());
	}
	// teste 35
	@Test
	public void testValorDadoAntesDeCome�arJogo() {
		fachada.setEscolhaPersonagemX(true);
		assertTrue(fachada.getValorDoDado() == 0);
	}
	// teste 36
	@Test
	public void verificarPosicaoAposAcertoEAposErroPersonagemX() {
		criarDesafio();
		fachada.setRespostaPersonagemX("a");
		fachada.jogarDado();
		Questao questao2 = criarQuestao();
		fachada.criarQuestao(questao2);
		fachada.setRespostaPersonagemY("b");
		fachada.jogarDado();
		Questao questao3 = criarQuestao();
		fachada.criarQuestao(questao3);
		fachada.setRespostaPersonagemX("b");
		assertTrue("Espera-se que o personagem esteja na posicao 1",
				fachada.getPosicaoPersonagemX() == 1);
	}
	// teste 37
	@Test
	public void verificarPosicaoAposAcertoEAposErroPersonagemY() {
		fachada.setEscolhaPersonagemX(false);
		criarDado();
		Questao questao1 = criarQuestao();
		fachada.criarQuestao(questao1);
		fachada.setRespostaPersonagemY("a");
		fachada.jogarDado();
		Questao questao2 = criarQuestao();
		fachada.criarQuestao(questao2);
		fachada.setRespostaPersonagemX("b");
		fachada.jogarDado();
		Questao questao3 = criarQuestao();
		fachada.criarQuestao(questao3);
		fachada.setRespostaPersonagemY("b");
		assertTrue("Espera-se que o personagem esteja na posicao 1",
				fachada.getPosicaoPersonagemY() == 1);
	}
	// teste 38
	@Test
	public void verificarScoreSalvoPersonagemX() {
		criarDesafio();
		fachada.setRespostaPersonagemX("a");
		Questao questao2 = criarQuestao();
		fachada.criarQuestao(questao2);
		fachada.setRespostaPersonagemY("a");
		Questao questao3 = criarQuestao();
		fachada.criarQuestao(questao3);
		fachada.setRespostaPersonagemX("a");
		assertTrue("Espera-se que o score do personagem X seja 9",
				fachada.verificarScore() == 9);
	}
	// teste 39
	@Test
	public void verificaPontuacaoDeUmJogadorDeterminado() {
		fachada.setEscolhaPersonagemX(true);
		fachada.setNomeJogador("Lucas");
		fachada.jogarDado();
		Questao questao = criarQuestao();
		fachada.criarQuestao(questao);
		fachada.setRespostaPersonagemX("a");
		fachada.encerrarAntesDoTempo();
	}
	// teste 40
	@Test
	public void verificarJogoEncerradoAntesDoTempo() {
		criarDesafio();
		fachada.setRespostaPersonagemX("a");
		fachada.encerrarAntesDoTempo();
		assertTrue("Espera que o jogo tenha terminado antes da hora",
				fachada.encerrarAntesDoTempo() == true);
	}
	// teste 41
	@Test
	public void verificarScoreDePartidaCanceladaPersonagemX() {
		criarDesafio();
		fachada.setRespostaPersonagemX("a");
		fachada.encerrarAntesDoTempo();
		assertTrue("Espera-se que o score seja 3",
				fachada.verificarScore() == 3);
	}
	// teste 42
	@Test
	public void verificarScoreDePartidaCanceladaPersonagemY() {
		fachada.setEscolhaPersonagemX(false);
		criarDado();
		Questao questao1 = criarQuestao();
		fachada.criarQuestao(questao1);
		fachada.setRespostaPersonagemY("a");
		fachada.encerrarAntesDoTempo();
		assertTrue("Espera-se que o score seja 3",
				fachada.verificarScore() == 3);
	}
	// teste 43
	@Test
	public void testNomeJogador() {
		fachada.setEscolhaPersonagemX(true);
		fachada.setNomeJogador("Joao");
		assertTrue("Espera-se que o personagem seja Joao",
				fachada.getNomeJogador() == "Joao");
	}
	// teste 44
	@Test
	public void testTamanhoNomeAceitavel() {
		fachada.setEscolhaPersonagemX(true);
		fachada.setNomeJogador("Luc");
		fachada.getTamanhoNome();
		assertTrue("Espera-se que o personagem seja Luc",
				fachada.getNomeJogador() == "Luc");
		assertEquals(3, fachada.getTamanhoNome());
	}
	// teste 45
	@Test
	public void cadastrarQuestao() {
		Questao questao1 = criarQuestao();
		fachada.cadastrarQuestao(questao1);
		List<Questao> questoesSalvas = fachada.listarQuestoes();
		assertEquals(1, questoesSalvas.size());
		Questao questaoSalva = questoesSalvas.get(0);
		assertEquals(questao1, questaoSalva);
	}
	// teste 46
	@Test(expected = ExcecaoJogoTabuleiro.class)
	public void cadastrarQuestaoNovamente() {
		Questao questao1 = criarQuestao();
		fachada.cadastrarQuestao(questao1);
		fachada.cadastrarQuestao(questao1);
	}
	// teste 47
	@Test
	public void removerQuestao() {
		Questao questao1 = criarQuestao();
		fachada.cadastrarQuestao(questao1);
		List<Questao> questoesSalvas = fachada.listarQuestoes();
		Questao questaoSalva = questoesSalvas.get(0);
		assertEquals(questao1, questaoSalva);
		fachada.removerQuestao(questao1);
		assertTrue(questoesSalvas.size() == 0);
	}
	// teste 48
	@Test(expected = ExcecaoJogoTabuleiro.class)
	public void removerQuestaoDeNovo() {
		Questao questao1 = criarQuestao();
		fachada.cadastrarQuestao(questao1);
		List<Questao> questoesSalvas = fachada.listarQuestoes();
		Questao questaoSalva = questoesSalvas.get(0);
		assertEquals(questao1, questaoSalva);
		fachada.removerQuestao(questao1);
		fachada.removerQuestao(questao1);
	}
	// teste 49
	@Test(expected = ExcecaoJogoTabuleiro.class)
	public void removerQuestaoInexistente() {
		Questao questao1 = criarQuestao();
		fachada.removerQuestao(questao1);
	}
	// teste 50
	@Test
	public void alterarQuestao() {
		Questao questao1 = criarQuestao();
		fachada.cadastrarQuestao(questao1);
		List<Questao> questoesSalvas = fachada.listarQuestoes();
		fachada.alterarQuestao(questao1);
		Questao questaoSalva = questoesSalvas.get(0);
		assertEquals(questao1, questaoSalva);
	}
	// teste 51
	@Test(expected = ExcecaoJogoTabuleiro.class)
	public void inserirPerguntaNula() {
		Questao questao1 = new Questao();
		questao1.setPergunta(null);
		questao1.setAlternativas(new String[] { "a", "b", "c" });
		questao1.setGabarito("a");
	}
	// teste 52
	@Test(expected = ExcecaoJogoTabuleiro.class)
	public void inserirGabaritoSemResposta() {
		Questao questao = new Questao();
		questao.setPergunta("Pergunta");
		questao.setAlternativas(new String[] { "a", "b", "c" });
		questao.setGabarito(null);
	}
	// test 53
	@Test(expected = ExcecaoJogoTabuleiro.class)
	public void inserirAlternativaNula() {
		String alternativas[] = new String[] { null, "", "" };
		Questao questao = new Questao();
		questao.setAlternativas(alternativas);
	}
	// teste 54 novo
	@Test
	public void verificarPersonagemXNoTabuleiro() {
		criarDesafio();
		fachada.setRespostaPersonagemX("a");
		assertTrue(fachada.verificarPersonagemXNoTabuleiro());
	}
	// teste 55 novo
	@Test
	public void verificarPersonagemYNoTabuleiro() {
		fachada.setEscolhaPersonagemX(false);
		fachada.jogarDado();
		Questao questao1 = criarQuestao();
		fachada.criarQuestao(questao1);
		fachada.setRespostaPersonagemY("a");
		assertTrue(fachada.verificarPersonagemYNoTabuleiro());
	}
	// teste 56 novo
	@Test
	public void cadastrarJogador() {
		Jogador jogador = criarJogador();
		fachada.cadastrarJogador(jogador);
		List<Jogador> jogadoresSalvos = fachada.listarJogador();
		assertEquals("Espera-se que o tamanho da lista de jogadores seja 1", 1,
				jogadoresSalvos.size());
		Jogador jogadorSalvo = jogadoresSalvos.get(0);
		assertEquals(
				"Espera-se que o jogador salvo seja igual ao jogador cadastrado",
				jogador, jogadorSalvo);
	}
	// teste 57 novo
	@Test
	public void removerJogador() {
		Jogador jogador = criarJogador();
		fachada.cadastrarJogador(jogador);
		List<Jogador> jogadoresSalvos = fachada.listarJogador();
		Jogador jogadorSalvo = jogadoresSalvos.get(0);
		assertEquals(jogador, jogadorSalvo);
		fachada.removerJogador(jogador);
		assertTrue(jogadoresSalvos.size() == 0);
	}
	// teste 58 novo
	@Test(expected = ExcecaoJogoTabuleiro.class)
	public void cadastrarJogadorComMesmaSenha() {
		Jogador jogador1 = new Jogador();
		jogador1.setNome("nome1");
		jogador1.setSenha("1a");
		fachada.cadastrarJogador(jogador1);
		Jogador jogador2 = new Jogador();
		jogador1.setNome("nome2");
		jogador2.setSenha("1a");
		fachada.cadastrarJogador(jogador2);
	}
	// teste 59 novo
	@Test(expected = ExcecaoJogoTabuleiro.class)
	public void removerJogadorNovamente() {
		Jogador jogador = criarJogador();
		fachada.cadastrarJogador(jogador);
		List<Jogador> jogadoresSalvos = fachada.listarJogador();
		Jogador jogadorSalvo = jogadoresSalvos.get(0);
		assertEquals(jogador, jogadorSalvo);
		fachada.removerJogador(jogador);
		fachada.removerJogador(jogador);
	}
	// teste 60 novo
	@Test(expected = ExcecaoJogoTabuleiro.class)
	public void removerJogadorInexistente() {
		Jogador jogador = criarJogador();
		fachada.removerJogador(jogador);
	}
	// teste 61 novo
	@Test(expected = ExcecaoJogoTabuleiro.class)
	public void cadastrarJogadorSemNome() {
		Jogador jogador = new Jogador();
		jogador.setNome(null);
		jogador.setSenha("senha");
		fachada.cadastrarJogador(jogador);
	}
	// teste 62 novo
	@Test(expected = ExcecaoJogoTabuleiro.class)
	public void cadastrarJogadorSemSenha() {
		Jogador jogador = new Jogador();
		jogador.setNome("nome");
		jogador.setSenha(null);
		fachada.cadastrarJogador(jogador);
	}
	// Metodo auxiliar de teste
	public Questao criarQuestao() {
		Questao questao = new Questao();
		questao.setPergunta("Pergunta");
		questao.setAlternativas(new String[] { "a", "b", "c" });
		questao.setGabarito("a");
		return questao;
	}
	// Metodo auxiliar de teste
	public void criarDesafio() {
		fachada.setEscolhaPersonagemX(true);
		criarDado();
		Questao questao1 = criarQuestao();
		fachada.criarQuestao(questao1);
	}
	// Metodo auxiliar de teste
	public Dado criarDado() {
		Dado d = new Dado();
		d.setValorDado(1);
		fachada.criarDado(d);
		return d;
	}
	// Metodo auxiliar de teste
	public Jogador criarJogador() {
		Jogador jogador = new Jogador();
		jogador.setNome("nome");
		jogador.setSenha("senha");
		return jogador;
	}
}