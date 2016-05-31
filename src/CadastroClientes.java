
/*	Cadastro de Clientes
 *  Felipe da Silva Lima
 *  Ciência da Computação - 3 Semestre
 *  Faculdade Alvorada - Maringá.*/

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class CadastroClientes {
	
	static JFrame TelaPrincipal = new JFrame("Cadastro de Clientes");
	static JFrame telaCadastrar = new JFrame("Cadastrar");
	static JFrame telaAtualizar = new JFrame("Atualizar");
	static JFrame telaExcluir = new JFrame("Excluir");
	static JFrame telaConsultar = new JFrame("Consultar");

	static JButton Cadastrar = new JButton("Cadastrar Clientes");
	static JButton Atualizar = new JButton("Atualizar Clientes");
	static JButton Excluir = new JButton("Excluir Clientes");
	static JButton Consultar = new JButton("Consultar Clientes");
	static JButton Sair = new JButton("Sair");
	static JButton Salvar = new JButton("Salvar");
	static JButton AtualizarRegistro = new JButton("Atualizar");
	static JButton Fechar = new JButton("Fechar");
	static JButton PesquisarCod = new JButton("Pesquisar");
	static JButton LimparTela = new JButton("Limpar Tela");
	static JButton LiberarTela = new JButton("Novo");

	static JTextField CadastrarNome = new JTextField(null);
	static JTextField CadastrarCpf = new JTextField(null);
	static JTextField CadastrarEmail = new JTextField(null);
	static JTextField CadastrarTelefoneCel = new JTextField(null);
	static JTextField CadastrarTelefoneRes = new JTextField(null);
	static JTextField CadastrarTelefoneCom = new JTextField(null);
	static JTextField CodigoCadastro = new JTextField(null);

	static JLabel codigoAtual = new JLabel();
	static JLabel msgNome = new JLabel("Nome:");
	static JLabel msgCpf = new JLabel("CPF: ");
	static JLabel msgEmail = new JLabel("Email:");
	static JLabel msgCodigo = new JLabel("Codigo de Cadastro:");
	static JLabel msgTelefoneCel = new JLabel("Fone Celular:");
	static JLabel msgTelefoneRes = new JLabel("Fone Residencial:");
	static JLabel msgTelefoneCom = new JLabel("Fone Comercial:");
	static JLabel Menu = new JLabel("Menu Principal");

	static JTable TableClientes = new JTable();
	static JScrollPane ScrollClientes = new JScrollPane();
	static JPanel PanelClientes = new JPanel();

	static int Largura = 900;
	static int Altura = 600;

	// Temanho telas secundárias
	static int TamanhoTelasA = 600;
	static int TamanhoTelasB = 400;

	static int quantRegistros = 100;
	static int quantColunas = 7;

	static String Registro[][] = new String[quantRegistros][quantColunas];

	static String nomeCadastro;
	static char emailCadastro;
	static int cpfCadastro;
	static int AutoIncremento = 1;
	static int codigoCadastro;
	static int codPesquisa;

	private static final String ARIAL = "Arial";

	public static void main(String[] args) {

		TelaPrincipal();
		Menu();
		TabelaClientes();
		JOptionPane.showMessageDialog(null,
				"      Bem vindo ao Cadastro de Clientes! \nPara que seus dados não sejam perdidos,\nnão feche a tela de Menus.");
	}

	static void TelaPrincipal() {
		TelaPrincipal.setVisible(true);
		TelaPrincipal.setSize(Largura, Altura);
		TelaPrincipal.setLayout(null);
		TelaPrincipal.setLocationRelativeTo(null);
		TelaPrincipal.setResizable(false);
		TelaPrincipal.getContentPane().setBackground(Color.WHITE);
		TelaPrincipal.add(Menu);
		TelaPrincipal.add(PanelClientes);
		TelaPrincipal.add(ScrollClientes);
		TelaPrincipal.add(TableClientes);

		// TelaPrincipal.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
	}

	static void TabelaClientes() {
		TelaPrincipal.add(PanelClientes);
		PanelClientes.add(ScrollClientes);
		ScrollClientes.add(TableClientes);

		PanelClientes.setVisible(true);
		PanelClientes.setBounds(20, 180, 860, 380);
		PanelClientes.setLayout(null);

		ScrollClientes.setVisible(true);
		ScrollClientes.setBounds(0, 0, 860, 380);
		ScrollClientes.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		ScrollClientes.setViewportView(TableClientes);

		TableClientes.setVisible(true);
		TableClientes.setLayout(null);
		TableClientes.setBounds(0, 0, 855, 380);
		TableClientes.setModel(new DefaultTableModel(new Object[] { "Código", "Nome", "Endereço", "CPF",
				"Telefone Celular", "Telefone Residencial", "Telefone Comercial" }, 0));

		for (int i = 0; i < codigoCadastro; i++) {
			DefaultTableModel valores = (DefaultTableModel) TableClientes.getModel();
			valores.addRow(new Object[] { i, Registro[i][0], Registro[i][1], Registro[i][2], Registro[i][3],
					Registro[i][4], Registro[i][5], Registro[i][6] });
		}

	}

	static void Menu() {

		Menu.setFont(new Font(ARIAL, Font.ITALIC, 25));
		Menu.setBounds(100, 20, 250, 25);
		Menu.setVisible(true);

		Fechar();
		Telefone();

		// SetBounds Lado, Altura, Largura, Comprimento
		Cadastrar();
		Cadastrar.setVisible(true);
		TelaPrincipal.add(Cadastrar);
		Cadastrar.setBounds(20, 65, 150, 40);

		Atualizar();
		Atualizar.setVisible(true);
		TelaPrincipal.add(Atualizar);
		Atualizar.setBounds(190, 65, 150, 40);

		Excluir();
		Excluir.setVisible(true);
		telaConsultar.add(Excluir);
		Excluir.setBounds(150, 280, 200, 40);

		Consultar();
		Consultar.setVisible(true);
		// TelaPrincipal.add(Consultar);
		Consultar.setBounds(50, 150, 200, 40);

		Sair();
		Sair.setVisible(true);
		TelaPrincipal.add(Sair);
		Sair.setBounds(810, 10, 80, 30);

	}

	static void Cadastrar() {

		Codigo();
		Nome();
		Cpf();
		Email();
		SalvarCadastro();
		LimparTela();
		LiberarTela();

		Cadastrar.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				TelaPrincipal.setVisible(false);
				telaCadastrar.setVisible(true);
				telaCadastrar.setSize(TamanhoTelasA, TamanhoTelasB);
				telaCadastrar.setLayout(null);
				telaCadastrar.setLocationRelativeTo(null);
				// telaCadastrar.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
				telaCadastrar.setResizable(false);
				telaCadastrar.getContentPane().setBackground(Color.WHITE);

				telaCadastrar.add(LimparTela);

				telaCadastrar.add(LiberarTela);

				telaCadastrar.add(codigoAtual);

				telaCadastrar.add(msgCodigo);
				codigoAtual.setText(String.valueOf(AutoIncremento));

				telaCadastrar.add(msgNome);
				telaCadastrar.add(CadastrarNome);
				CadastrarNome.setEnabled(false);

				telaCadastrar.add(msgCpf);
				telaCadastrar.add(CadastrarCpf);
				CadastrarCpf.setEnabled(false);

				telaCadastrar.add(msgEmail);
				telaCadastrar.add(CadastrarEmail);
				CadastrarEmail.setEnabled(false);

				telaCadastrar.add(msgTelefoneCel);
				telaCadastrar.add(CadastrarTelefoneCel);
				CadastrarTelefoneCel.setEnabled(false);

				telaCadastrar.add(msgTelefoneRes);
				telaCadastrar.add(CadastrarTelefoneRes);
				CadastrarTelefoneRes.setEnabled(false);

				telaCadastrar.add(msgTelefoneCom);
				telaCadastrar.add(CadastrarTelefoneCom);
				CadastrarTelefoneCom.setEnabled(false);

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
		AtualizarCadastro();
		LimparTela();
		PesquisarCod();

		Atualizar.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				TelaPrincipal.setVisible(false);
				telaAtualizar.setVisible(true);
				telaAtualizar.setSize(TamanhoTelasA, TamanhoTelasB);
				telaAtualizar.setLayout(null);
				telaAtualizar.setLocationRelativeTo(null);
				// telaAtualizar.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
				telaAtualizar.setResizable(false);
				telaAtualizar.add(LimparTela);
				telaAtualizar.getContentPane().setBackground(Color.WHITE);

				telaAtualizar.add(msgCodigo);
				telaAtualizar.add(CodigoCadastro);
				telaAtualizar.add(PesquisarCod);

				telaAtualizar.add(msgNome);
				telaAtualizar.add(CadastrarNome);
				CadastrarNome.setEnabled(false);

				telaAtualizar.add(msgCpf);
				telaAtualizar.add(CadastrarCpf);
				CadastrarCpf.setEnabled(false);

				telaAtualizar.add(msgEmail);
				telaAtualizar.add(CadastrarEmail);
				CadastrarEmail.setEnabled(false);

				telaAtualizar.add(msgTelefoneCel);
				telaAtualizar.add(CadastrarTelefoneCel);
				CadastrarTelefoneCel.setEnabled(false);

				telaAtualizar.add(msgTelefoneRes);
				telaAtualizar.add(CadastrarTelefoneRes);
				CadastrarTelefoneRes.setEnabled(false);

				telaAtualizar.add(msgTelefoneCom);
				telaAtualizar.add(CadastrarTelefoneCom);
				CadastrarTelefoneCom.setEnabled(false);

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
				if (JOptionPane.showConfirmDialog(null,
						"Os dados inseridos serão peridos!\nDeseja realmente continuar?") == JOptionPane.OK_OPTION) {

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

	static void Telefone() {

		msgTelefoneCel.setVisible(true);
		msgTelefoneCel.setBounds(400, 110, 150, 30);
		CadastrarTelefoneCel.setVisible(true);
		CadastrarTelefoneCel.setBounds(400, 135, 180, 25);

		msgTelefoneRes.setVisible(true);
		msgTelefoneRes.setBounds(400, 165, 150, 30);
		CadastrarTelefoneRes.setVisible(true);
		CadastrarTelefoneRes.setBounds(400, 190, 180, 25);

		msgTelefoneCom.setVisible(true);
		msgTelefoneCom.setBounds(400, 220, 150, 30);
		CadastrarTelefoneCom.setVisible(true);
		CadastrarTelefoneCom.setBounds(400, 245, 180, 25);

	}

	static void LimparTela() {
		LimparTela.setVisible(true);
		LimparTela.setBounds(125, 20, 110, 20);

		LimparTela.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub

				CodigoCadastro.setText(null);
				CadastrarNome.setText(null);
				CadastrarCpf.setText(null);
				CadastrarEmail.setText(null);
				CadastrarTelefoneCel.setText(null);
				CadastrarTelefoneRes.setText(null);
				CadastrarTelefoneCom.setText(null);

				CodigoCadastro.setEnabled(true);
				CadastrarNome.setEnabled(false);
				CadastrarCpf.setEnabled(false);
				CadastrarEmail.setEnabled(false);
				CadastrarTelefoneCel.setEnabled(false);
				CadastrarTelefoneRes.setEnabled(false);
				CadastrarTelefoneCom.setEnabled(false);

			}
		});
	}

	static void LiberarTela() {
		LiberarTela.setVisible(true);
		LiberarTela.setBounds(50, 20, 65, 20);

		LiberarTela.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub

				CadastrarNome.setEnabled(true);
				CadastrarCpf.setEnabled(true);
				CadastrarEmail.setEnabled(true);
				CadastrarTelefoneCel.setEnabled(true);
				CadastrarTelefoneRes.setEnabled(true);
				CadastrarTelefoneCom.setEnabled(true);

			}
		});

	}

	static void PesquisarCod() {

		CodigoCadastro.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
				String cod = CodigoCadastro.getText();
				codPesquisa = Integer.parseInt(cod) - 1;

				CadastrarNome.setText(Registro[codPesquisa][1]);
				CadastrarCpf.setText(Registro[codPesquisa][2]);
				CadastrarEmail.setText(Registro[codPesquisa][3]);
				CadastrarTelefoneCel.setText(Registro[codPesquisa][4]);
				CadastrarTelefoneRes.setText(Registro[codPesquisa][5]);
				CadastrarTelefoneCom.setText(Registro[codPesquisa][6]);

				if ("".equals(CadastrarNome.getText()) && "".equals(CadastrarCpf.getText())
						&& "".equals(CadastrarEmail.getText())) {
					JOptionPane.showMessageDialog(null, "  Cadastro não localizado.");
					CodigoCadastro.setText(null);
					CodigoCadastro.setEnabled(true);
					CadastrarNome.setEnabled(false);
					CadastrarCpf.setEnabled(false);
					CadastrarEmail.setEnabled(false);

				} else {
					CodigoCadastro.setEnabled(false);
					CadastrarNome.setEnabled(true);
					CadastrarCpf.setEnabled(true);
					CadastrarEmail.setEnabled(true);
					CadastrarTelefoneCel.setEnabled(true);
					CadastrarTelefoneRes.setEnabled(true);
					CadastrarTelefoneCom.setEnabled(true);

				}

			}
		});
		PesquisarCod.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				String cod = CodigoCadastro.getText();
				codPesquisa = Integer.parseInt(cod) - 1;

				CadastrarNome.setText(Registro[codPesquisa][1]);
				CadastrarCpf.setText(Registro[codPesquisa][2]);
				CadastrarEmail.setText(Registro[codPesquisa][3]);
				CadastrarTelefoneCel.setText(Registro[codPesquisa][4]);
				CadastrarTelefoneRes.setText(Registro[codPesquisa][5]);
				CadastrarTelefoneCom.setText(Registro[codPesquisa][6]);

				if ("".equals(CadastrarNome.getText()) && "".equals(CadastrarCpf.getText())
						&& "".equals(CadastrarEmail.getText())) {
					JOptionPane.showMessageDialog(null, "  Cadastro não localizado.");
					CodigoCadastro.setText(null);
					CodigoCadastro.setEnabled(true);
					CadastrarNome.setEnabled(false);
					CadastrarCpf.setEnabled(false);
					CadastrarEmail.setEnabled(false);

				} else {
					CodigoCadastro.setEnabled(false);
					CadastrarNome.setEnabled(true);
					CadastrarCpf.setEnabled(true);
					CadastrarEmail.setEnabled(true);
					CadastrarTelefoneCel.setEnabled(true);
					CadastrarTelefoneRes.setEnabled(true);
					CadastrarTelefoneCom.setEnabled(true);

				}

			}
		});
	}

	static void SalvarCadastro() {
		Salvar.setVisible(true);
		Salvar.setBounds(480, 320, 80, 30);

		Salvar.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				if ("".equals(CadastrarNome.getText()) || "".equals(CadastrarCpf.getText())
						|| "".equals(CadastrarEmail.getText())) {
					JOptionPane.showMessageDialog(null, "Há campo(s) em branco que aguarda(m) ser(em) preenchidos!");

				} else if ("".equals(CadastrarTelefoneCel.getText()) && "".equals(CadastrarTelefoneRes.getText())
						&& "".equals(CadastrarTelefoneCom.getText())) {
					JOptionPane.showMessageDialog(null, "Cadastre um telefone para contato!");
				} else {

					codigoCadastro = AutoIncremento - 1;
					nomeCadastro = CadastrarNome.getText();
					cpfCadastro = CadastrarCpf.getHeight();
					emailCadastro = CadastrarEmail.getFocusAccelerator();
					Registro[codigoCadastro][0] = codigoAtual.getText();
					Registro[codigoCadastro][1] = CadastrarNome.getText();
					Registro[codigoCadastro][2] = CadastrarCpf.getText();
					Registro[codigoCadastro][3] = CadastrarEmail.getText();
					Registro[codigoCadastro][4] = CadastrarTelefoneCel.getText();
					Registro[codigoCadastro][5] = CadastrarTelefoneRes.getText();
					Registro[codigoCadastro][6] = CadastrarTelefoneCom.getText();

					JOptionPane.showMessageDialog(null, "Registro de Código " + AutoIncremento + " Salvo com Sucesso!");

					AutoIncremento++;

					int continuarCadastro = JOptionPane.showConfirmDialog(null, "Prosseguir com novos Cadastros?");
					if (continuarCadastro == JOptionPane.YES_OPTION) {

						// Reabrir Janela
						CadastrarNome.setText(null);
						CadastrarCpf.setText(null);
						CadastrarEmail.setText(null);
						CadastrarTelefoneCel.setText(null);
						CadastrarTelefoneRes.setText(null);
						CadastrarTelefoneCom.setText(null);

						telaCadastrar.add(codigoAtual);

						Codigo();
						telaCadastrar.add(msgCodigo);
						codigoAtual.setText(String.valueOf(AutoIncremento));

					} else {

						CadastrarNome.setText(null);
						CadastrarCpf.setText(null);
						CadastrarEmail.setText(null);
						CadastrarTelefoneCel.setText(null);
						CadastrarTelefoneRes.setText(null);
						CadastrarTelefoneCom.setText(null);
						telaCadastrar.dispose();

						TelaPrincipal.setVisible(true);
					}
				}
			}
		});

	}

	static void AtualizarCadastro() {
		AtualizarRegistro.setVisible(true);
		AtualizarRegistro.setBounds(450, 320, 110, 30);

		AtualizarRegistro.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				if ("".equals(CadastrarNome.getText()) || "".equals(CadastrarCpf.getText())
						|| "".equals(CadastrarEmail.getText()) || "".equals(CodigoCadastro.getText())) {
					JOptionPane.showMessageDialog(null, "Há campo(s) em branco que aguarda(m) ser(em) preenchidos!");

				} else {

					Registro[codPesquisa][1] = CadastrarNome.getText();
					Registro[codPesquisa][2] = CadastrarCpf.getText();
					Registro[codPesquisa][3] = CadastrarEmail.getText();
					Registro[codigoCadastro][4] = CadastrarTelefoneCel.getText();
					Registro[codigoCadastro][5] = CadastrarTelefoneRes.getText();
					Registro[codigoCadastro][6] = CadastrarTelefoneCom.getText();

					JOptionPane.showMessageDialog(null, "Cliente " + (codPesquisa + 1) + " Atualizado com Sucesso!");

					CodigoCadastro.setText(null);
					CadastrarNome.setText(null);
					CadastrarCpf.setText(null);
					CadastrarEmail.setText(null);
					CadastrarTelefoneCel.setText(null);
					CadastrarTelefoneRes.setText(null);
					CadastrarTelefoneCom.setText(null);

					CodigoCadastro.setEnabled(true);
					CadastrarNome.setEnabled(false);
					CadastrarCpf.setEnabled(false);
					CadastrarEmail.setEnabled(false);
					CadastrarTelefoneCel.setEnabled(false);
					CadastrarTelefoneRes.setEnabled(false);
					CadastrarTelefoneCom.setEnabled(false);
				}

			}
		});
	}

	static void Fechar() {
		Fechar.setVisible(true);
		Fechar.setBounds(20, 320, 80, 30);
		Fechar.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				if (!"".equals(CadastrarNome.getText()) || !"".equals(CadastrarCpf.getText())
						|| !"".equals(CadastrarEmail.getText())) {

					if (JOptionPane.showConfirmDialog(null,
							"Sair sem salvar descartará informações inseridas. Deseja Continuar?") == JOptionPane.OK_OPTION) {

						telaCadastrar.dispose();
						telaAtualizar.dispose();
						telaExcluir.dispose();
						telaConsultar.dispose();
						TelaPrincipal.setVisible(true);
						CodigoCadastro.setText(null);
						CadastrarNome.setText(null);
						CadastrarCpf.setText(null);
						CadastrarEmail.setText(null);
						CadastrarTelefoneCel.setText(null);
						CadastrarTelefoneRes.setText(null);
						CadastrarTelefoneCom.setText(null);
						CodigoCadastro.setEnabled(true);
					} else {

					}
				} else {

					telaCadastrar.dispose();
					telaAtualizar.dispose();
					telaExcluir.dispose();
					telaConsultar.dispose();
					TelaPrincipal.setVisible(true);
					CodigoCadastro.setEnabled(true);

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