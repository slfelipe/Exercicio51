import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

/*	Crie um cadastro de clientes utilizando
	funções, parâmetros, tipo de retorno, escopo de variáveis, vetores, matrizes e recursividade. 
	O sistema deve permitir
	cadastrar 100 clientes, atualizar clientes, excluir clientes e consultar clientes.
	Utilizar Java SE com Swing para desenvolver a User Interface.
	-- http://javafree.uol.com.br/artigo/871500/Criando-uma-Janela-Swing.html 
	http://www.devmedia.com.br/java-swing-conheca-os-componentes-jtextfield-e-jformattedtextfield/30981*/

public class CadastroClientes {
	static JFrame TelaPrincipal = new JFrame("Cadastro de Clientes");
	static JButton Cadastrar = new JButton("Cadastrar Clientes");
	static JFrame telaCadastrar = new JFrame("Cadastrar");
	static JButton Atualizar = new JButton("Atualizar Clientes");
	static JFrame telaAtualizar = new JFrame("Atualizar");
	static JButton Excluir = new JButton("Excluir Clientes");
	static JFrame telaExcluir = new JFrame("Excluir");
	static JButton Consultar = new JButton("Consultar Clientes");
	static JFrame telaConsultar = new JFrame("Consultar");
	static JButton Sair = new JButton("Sair");
	static JLabel codigoAtual = new JLabel();
	static JTextField CadastrarNome = new JTextField();
	static JTextField CadastrarTelefone = new JTextField();
	static JTextField CadastrarEmail = new JTextField();
	static JButton Salvar = new JButton("Salvar");
	static JButton Fechar = new JButton("Fechar");
	static JLabel msgNome = new JLabel("NOME:");
	static JLabel msgTelefone = new JLabel("TELEFONE: ");
	static JLabel msgEmail = new JLabel("EMAIL:");
	static JLabel msgCodigo = new JLabel("Codigo de Cadastro:");

	static int Largura = 800;
	static int Altura = 600;

	// Temanho telas secundárias
	static int TamanhoTelasA = 500;
	static int TamanhoTelasB = 500;

	static String nomeCadastro;
	static char emailCadastro;
	static int telefoneCadastro;
	static int AutoIncremento = 0;
	static int codigoCadastro = 1;

	public static void main(String[] args) {
		TelaPrincipal();
		Menu();
		JOptionPane.showMessageDialog(null,
				"      Bem vindo ao Cadastro de Clientes! \nPara que seus dados não sejam perdidos, não feche a tela de Menus.");
	}

	static void TelaPrincipal() {
		TelaPrincipal.setVisible(true);
		TelaPrincipal.setSize(Largura, Altura);
		TelaPrincipal.setLayout(null);
		TelaPrincipal.setLocationRelativeTo(null);
		TelaPrincipal.setResizable(false);
		TelaPrincipal.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
	}

	static void Menu() {
		
		Codigo();
		Nome();
		Telefone();
		Email();
		SalvarCadastro();
		Fechar();
		// SetBounds Lado, Altura, Largura, Comprimento
		Cadastrar();
		Cadastrar.setVisible(true);
		TelaPrincipal.add(Cadastrar);
		Cadastrar.setBounds(150, 50, 200, 50);

		Atualizar();
		Atualizar.setVisible(true);
		TelaPrincipal.add(Atualizar);
		Atualizar.setBounds(150, 150, 200, 50);

		Excluir();
		Excluir.setVisible(true);
		TelaPrincipal.add(Excluir);
		Excluir.setBounds(150, 250, 200, 50);

		Consultar();
		Consultar.setVisible(true);
		TelaPrincipal.add(Consultar);
		Consultar.setBounds(150, 350, 200, 50);

		Sair();
		Sair.setVisible(true);
		TelaPrincipal.add(Sair);
		Sair.setBounds(650, 500, 80, 30);

	}

	static void Cadastrar() {

		
		Cadastrar.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				telaCadastrar.setVisible(true);
				telaCadastrar.setSize(TamanhoTelasA, TamanhoTelasB);
				telaCadastrar.setLayout(null);
				telaCadastrar.setLocationRelativeTo(null);

				telaCadastrar.add(codigoAtual);
				codigoAtual.setVisible(true);
				codigoAtual.setBounds(180, 20, 155, 30);

				
				telaCadastrar.add(msgCodigo);
				codigoAtual.setText(String.valueOf(codigoCadastro));
				
				telaCadastrar.add(msgNome);
				telaCadastrar.add(CadastrarNome);
				
				telaCadastrar.add(msgTelefone);
				telaCadastrar.add(CadastrarTelefone);
				
				telaCadastrar.add(msgEmail);
				telaCadastrar.add(CadastrarEmail);
				
				telaCadastrar.add(Salvar);				
				
				telaCadastrar.add(Fechar);

			}

		});
	}

	static void Atualizar() {

		Atualizar.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				telaAtualizar.setVisible(true);
				telaAtualizar.setSize(TamanhoTelasA, TamanhoTelasB);
				telaAtualizar.setLayout(null);
				telaAtualizar.setLocationRelativeTo(null);

				
				telaAtualizar.add(msgCodigo);
				
				telaAtualizar.add(msgNome);
				telaAtualizar.add(CadastrarNome);
				
				telaAtualizar.add(msgTelefone);
				telaAtualizar.add(CadastrarTelefone);
				
				telaAtualizar.add(msgEmail);
				telaAtualizar.add(CadastrarEmail);
				
				telaAtualizar.add(Salvar);
				
				telaAtualizar.add(Fechar);
			}
		});
	}

	static void Excluir() {
		Excluir.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				telaExcluir.setVisible(true);
				telaExcluir.setSize(TamanhoTelasA, TamanhoTelasB);
				telaExcluir.setLayout(null);
				telaExcluir.setLocationRelativeTo(null);

				Codigo();
				telaExcluir.add(msgCodigo);
			}
		});
	}

	static void Consultar() {
		Consultar.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				telaConsultar.setVisible(true);
				telaConsultar.setSize(TamanhoTelasA, TamanhoTelasB);
				telaConsultar.setLayout(null);
				telaConsultar.setLocationRelativeTo(null);

				Codigo();
				telaConsultar.add(msgCodigo);
				ConsultarNome();
			}
		});
	}

	static void Sair() {
		Sair.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				if (JOptionPane.showConfirmDialog(null, "Os dados inseridos serão peridos") == JOptionPane.OK_OPTION) {

					JOptionPane.showMessageDialog(null, "Registro Limpo.\nSistema será fechado!");
					System.exit(0);
				}
			}
		});

	}

	static void Codigo() {

		msgCodigo.setVisible(true);
		msgCodigo.setBounds(20, 20, 150, 30);

	}

	static void Nome() {

		CadastrarNome.setVisible(true);
		CadastrarNome.setBounds(80, 50, 350, 30);
		msgNome.setVisible(true);
		msgNome.setBounds(30, 50, 45, 30);

	}

	static void Telefone() {
		CadastrarTelefone.setVisible(true);
		CadastrarTelefone.setBounds(100, 120, 200, 30);

		msgTelefone.setVisible(true);
		msgTelefone.setBounds(30, 120, 100, 30);

	}

	static void Email() {
		CadastrarEmail.setVisible(true);
		CadastrarEmail.setBounds(80, 190, 300, 30);

		msgEmail.setVisible(true);
		msgEmail.setBounds(30, 190, 45, 30);

	}

	static void SalvarCadastro() {
		Salvar.setVisible(true);
		Salvar.setBounds(380, 420, 80, 30);

		Salvar.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				nomeCadastro = CadastrarNome.getText();
				telefoneCadastro = CadastrarTelefone.getHeight();
				emailCadastro = CadastrarEmail.getFocusAccelerator();
				JOptionPane.showMessageDialog(null, "Registro de Código " + codigoCadastro + " Salvo com Sucesso!");

				telaCadastrar.dispose();

				codigoCadastro++;
				
				

				// Reabrir Janela
				telaCadastrar.setVisible(true);

				telaCadastrar.add(codigoAtual);
				codigoAtual.setVisible(true);
				codigoAtual.setBounds(180, 20, 155, 30);

				Codigo();
				telaCadastrar.add(msgCodigo);
				codigoAtual.setText(String.valueOf(codigoCadastro));

			}
		});
	}

	static void SalvarAtualizar() {
		Salvar.setVisible(true);
		Salvar.setBounds(380, 420, 80, 30);

		Salvar.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				nomeCadastro = CadastrarNome.getText();
				telefoneCadastro = CadastrarTelefone.getHeight();
				emailCadastro = CadastrarEmail.getFocusAccelerator();
				JOptionPane.showMessageDialog(null,
						"Registro de Código " + codigoCadastro + " Atualizado com Sucesso!");

				telaAtualizar.dispose();

				telaAtualizar.setVisible(true);
			}
		});
	}

	static void Fechar() {
		Fechar.setVisible(true);
		Fechar.setBounds(20, 420, 80, 30);
		Fechar.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				if (JOptionPane.showConfirmDialog(null, "Sair sem salvar descartará informações inseridas. Deseja Continuar?") == JOptionPane.OK_OPTION) {
				
					telaCadastrar.dispose();
					telaAtualizar.dispose();
					telaExcluir.dispose();
					telaConsultar.dispose();
					
				}			
			}
		});
	}

	static void ConsultarNome() {
		System.out.println(nomeCadastro);
		System.out.println(telefoneCadastro);
		System.out.println(emailCadastro);
	}
}
