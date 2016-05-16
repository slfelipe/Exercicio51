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
	static JTextField CadastrarCpf = new JTextField();
	static JTextField CadastrarEmail = new JTextField();
	static JTextField CodigoCadastro = new JTextField();
	static JButton Salvar = new JButton("Salvar");
	static JButton AtualizarRegistro = new JButton("Atualizar");
	static JButton Fechar = new JButton("Fechar");
	static JLabel msgNome = new JLabel("Nome:");
	static JLabel msgCpf = new JLabel("CPF: ");
	static JLabel msgEmail = new JLabel("Email:");
	static JLabel msgCodigo = new JLabel("Codigo de Cadastro:");
	static JButton PesquisarCod = new JButton("Pesquisar");

	static int Largura = 300;
	static int Altura = 400;

	// Temanho telas secundárias
	static int TamanhoTelasA = 600;
	static int TamanhoTelasB = 400;

	static String Registro[][] = new String[100][4];

	static String nomeCadastro;
	static char emailCadastro;
	static int cpfCadastro;
	static int AutoIncremento = 1;
	static int codigoCadastro;

	public static void main(String[] args) {
		TelaPrincipal();
		Menu();
		JOptionPane.showMessageDialog(null,
				"      Bem vindo ao Cadastro de Clientes! \nPara que seus dados não sejam perdidos,\nnão feche a tela de Menus.");
	}

	static void TelaPrincipal() {
		TelaPrincipal.setVisible(true);
		TelaPrincipal.setSize(Largura, Altura);
		TelaPrincipal.setLayout(null);
		TelaPrincipal.setLocationRelativeTo(null);
		TelaPrincipal.setResizable(false);
		// TelaPrincipal.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
	}

	static void Menu() {

		Fechar();

		// SetBounds Lado, Altura, Largura, Comprimento
		Cadastrar();
		Cadastrar.setVisible(true);
		TelaPrincipal.add(Cadastrar);
		Cadastrar.setBounds(50, 50, 200, 40);

		Atualizar();
		Atualizar.setVisible(true);
		TelaPrincipal.add(Atualizar);
		Atualizar.setBounds(50, 100, 200, 40);

		Excluir();
		Excluir.setVisible(true);
		telaConsultar.add(Excluir);
		Excluir.setBounds(150, 250, 200, 40);

		Consultar();
		Consultar.setVisible(true);
		TelaPrincipal.add(Consultar);
		Consultar.setBounds(50, 150, 200, 40);

		Sair();
		Sair.setVisible(true);
		TelaPrincipal.add(Sair);
		Sair.setBounds(110, 300, 80, 30);

	}

	static void Cadastrar() {

		Codigo();
		Nome();
		Cpf();
		Email();
		SalvarCadastro();

		Cadastrar.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				TelaPrincipal.setVisible(false);
				telaCadastrar.setVisible(true);
				telaCadastrar.setSize(TamanhoTelasA, TamanhoTelasB);
				telaCadastrar.setLayout(null);
				telaCadastrar.setLocationRelativeTo(null);
				// telaCadastrar.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
				telaCadastrar.setResizable(false);

				telaCadastrar.add(codigoAtual);

				telaCadastrar.add(msgCodigo);
				codigoAtual.setText(String.valueOf(AutoIncremento));

				telaCadastrar.add(msgNome);
				telaCadastrar.add(CadastrarNome);

				telaCadastrar.add(msgCpf);
				telaCadastrar.add(CadastrarCpf);

				telaCadastrar.add(msgEmail);
				telaCadastrar.add(CadastrarEmail);

				telaCadastrar.add(Salvar);

				telaCadastrar.add(Fechar);

			}

		});
	}

	static void Atualizar() {

		Codigo();
		Nome();
		Cpf();
		Email();
		SalvarAtualizar();

		Atualizar.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				TelaPrincipal.setVisible(false);
				telaAtualizar.setVisible(true);
				telaAtualizar.setSize(TamanhoTelasA, TamanhoTelasB);
				telaAtualizar.setLayout(null);
				telaAtualizar.setLocationRelativeTo(null);
				// telaAtualizar.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
				telaAtualizar.setResizable(false);

				telaAtualizar.add(msgCodigo);
				telaAtualizar.add(CodigoCadastro);
				telaAtualizar.add(PesquisarCod);
				PesquisarCod.addActionListener(new ActionListener() {

					public void actionPerformed(ActionEvent e) {

						String cod = CodigoCadastro.getText();
						// Transformar "cod" em inteiro~~~~~~~
						//CadastrarNome = Registro[cod][1];
						//CadastrarCpf = Registro[cod][2];
						//CadastrarEmail = Registro[cod][3];

					}
				});

				telaAtualizar.add(msgNome);
				telaAtualizar.add(CadastrarNome);

				telaAtualizar.add(msgCpf);
				telaAtualizar.add(CadastrarCpf);

				telaAtualizar.add(msgEmail);
				telaAtualizar.add(CadastrarEmail);

				telaAtualizar.add(AtualizarRegistro);

				telaAtualizar.add(Fechar);
			}
		});
	}

	static void Excluir() {
		Excluir.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				TelaPrincipal.setVisible(false);
				telaExcluir.setVisible(true);
				telaExcluir.setSize(TamanhoTelasA, TamanhoTelasB);
				telaExcluir.setLayout(null);
				telaExcluir.setLocationRelativeTo(null);
				telaExcluir.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
				telaExcluir.setResizable(false);

				Codigo();
				telaExcluir.add(msgCodigo);
			}
		});
	}

	static void Consultar() {
		Consultar.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				TelaPrincipal.setVisible(false);
				telaConsultar.setVisible(true);
				telaConsultar.setSize(TamanhoTelasA, TamanhoTelasB);
				telaConsultar.setLayout(null);
				telaConsultar.setLocationRelativeTo(null);
				telaConsultar.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
				telaConsultar.setResizable(false);

				Codigo();
				telaConsultar.add(msgCodigo);
				ConsultarNome();
			}
		});
	}

	static void Sair() {
		Sair.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				if (JOptionPane.showConfirmDialog(null, "Os dados inseridos serão peridos!\nDeseja realmente continuar?") == JOptionPane.OK_OPTION) {

					JOptionPane.showMessageDialog(null, "Registro Limpo.\nSistema será fechado!");
					System.exit(0);
				}
			}
		});

	}

	static void Codigo() {

		PesquisarCod.setVisible(true);
		PesquisarCod.setBounds(450, 50, 100, 20);

		CodigoCadastro.setVisible(true);
		CodigoCadastro.setBounds(480, 20, 40, 25);

		msgCodigo.setVisible(true);
		msgCodigo.setBounds(350, 20, 130, 30);

		codigoAtual.setVisible(true);
		codigoAtual.setBounds(500, 20, 155, 30);
	}

	static void Nome() {

		CadastrarNome.setVisible(true);
		CadastrarNome.setBounds(50, 75, 500, 25);
		msgNome.setVisible(true);
		msgNome.setBounds(50, 50, 45, 30);

	}

	static void Cpf() {
		CadastrarCpf.setVisible(true);
		CadastrarCpf.setBounds(50, 135, 200, 25);

		msgCpf.setVisible(true);
		msgCpf.setBounds(50, 110, 100, 30);

	}

	static void Email() {
		CadastrarEmail.setVisible(true);
		CadastrarEmail.setBounds(50, 205, 300, 25);

		msgEmail.setVisible(true);
		msgEmail.setBounds(50, 180, 45, 30);

	}

	static void SalvarCadastro() {
		Salvar.setVisible(true);
		Salvar.setBounds(480, 320, 80, 30);

		Salvar.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				codigoCadastro = AutoIncremento - 1;
				nomeCadastro = CadastrarNome.getText();
				cpfCadastro = CadastrarCpf.getHeight();
				emailCadastro = CadastrarEmail.getFocusAccelerator();
				Registro[codigoCadastro][0] = codigoAtual.getText();
				Registro[codigoCadastro][1] = CadastrarNome.getText();
				Registro[codigoCadastro][2] = CadastrarCpf.getText();
				Registro[codigoCadastro][3] = CadastrarEmail.getText();

				JOptionPane.showMessageDialog(null, "Registro de Código " + AutoIncremento + " Salvo com Sucesso!");

				telaCadastrar.dispose();

				AutoIncremento++;

				// Reabrir Janela
				telaCadastrar.setVisible(true);

				telaCadastrar.add(codigoAtual);

				Codigo();
				telaCadastrar.add(msgCodigo);
				codigoAtual.setText(String.valueOf(AutoIncremento));

			}
		});
	}

	static void SalvarAtualizar() {
		AtualizarRegistro.setVisible(true);
		AtualizarRegistro.setBounds(450, 320, 110, 30);

		AtualizarRegistro.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				nomeCadastro = CadastrarNome.getText();
				cpfCadastro = CadastrarCpf.getHeight();
				emailCadastro = CadastrarEmail.getFocusAccelerator();
				JOptionPane.showMessageDialog(null, "Registro Atualizado com Sucesso!");

				telaAtualizar.dispose();

				telaAtualizar.setVisible(true);

			}
		});
	}

	static void Fechar() {
		Fechar.setVisible(true);
		Fechar.setBounds(20, 320, 80, 30);
		Fechar.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				if (JOptionPane.showConfirmDialog(null,
						"Sair sem salvar descartará informações inseridas. Deseja Continuar?") == JOptionPane.OK_OPTION) {

					telaCadastrar.dispose();
					telaAtualizar.dispose();
					telaExcluir.dispose();
					telaConsultar.dispose();
					TelaPrincipal.setVisible(true);
				}
			}
		});
	}

	static void ConsultarNome() {
		System.out.println(Registro[0][0]);
		System.out.println(Registro[0][1]);
		System.out.println(Registro[0][2]);
	}
}