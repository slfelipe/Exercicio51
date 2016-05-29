
/*	Cadastro de Clientes
 *  Felipe da Silva Lima
 *  Ciência da Computação - 3 Semestre
 *  Faculdade Alvorada - Maringá.*/

import java.awt.Color;
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
	static JTextField CadastrarNome = new JTextField(null);
	static JTextField CadastrarCpf = new JTextField(null);
	static JTextField CadastrarEmail = new JTextField(null);
	static JTextField CadastrarTelefone1 = new JTextField(null);
	static JTextField CadastrarTelefone2 = new JTextField(null);
	static JTextField CadastrarTelefone3 = new JTextField(null);
	static JTextField CodigoCadastro = new JTextField(null);
	static JButton Salvar = new JButton("Salvar");
	static JButton AtualizarRegistro = new JButton("Atualizar");
	static JButton Fechar = new JButton("Fechar");
	static JLabel msgNome = new JLabel("Nome:");
	static JLabel msgCpf = new JLabel("CPF: ");
	static JLabel msgEmail = new JLabel("Email:");
	static JLabel msgCodigo = new JLabel("Codigo de Cadastro:");
	static JLabel msgTelefone = new JLabel("Fone(s):");
	static JButton PesquisarCod = new JButton("Pesquisar");
	static JButton LimparTela = new JButton("Limpar Tela");
	static JButton LiberarTela = new JButton("Novo");
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
		// TelaPrincipal.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
	}

	static void TabelaClientes() {
		TelaPrincipal.add(PanelClientes);
		PanelClientes.add(ScrollClientes);
		ScrollClientes.add(TableClientes);

		PanelClientes.setVisible(true);
		PanelClientes.setBounds(300, 50, 590, 400);
		PanelClientes.setLayout(null);

		ScrollClientes.setVisible(true);
		ScrollClientes.setBounds(0, 0, 590, 400);
		ScrollClientes.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		ScrollClientes.setViewportView(TableClientes);

		TableClientes.setVisible(true);
		TableClientes.setLayout(null);
		TableClientes.setBounds(0, 0, 590, 400);
		TableClientes.setModel(new DefaultTableModel(new Object[] { "Código", "Nome", "Endereço", "CPF" }, 0));

		for (int i = 0; i < AutoIncremento - 1; i++) {
			DefaultTableModel valores = (DefaultTableModel) TableClientes.getModel();
			valores.addRow(new Object[] { i + 1, Registro[i][0], Registro[i][1], Registro[i][2] });
		}

	}

	static void Menu() {

		Fechar();
		Telefone();

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
		// TelaPrincipal.add(Consultar);
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

				telaCadastrar.add(msgTelefone);
				telaCadastrar.add(CadastrarTelefone1);
				CadastrarTelefone1.setEnabled(false);

				telaCadastrar.add(CadastrarTelefone2);
				CadastrarTelefone2.setEnabled(false);

				telaCadastrar.add(CadastrarTelefone3);
				CadastrarTelefone3.setEnabled(false);

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

				telaAtualizar.add(msgTelefone);
				telaAtualizar.add(CadastrarTelefone1);
				CadastrarTelefone1.setEnabled(false);
				telaAtualizar.add(CadastrarTelefone2);
				CadastrarTelefone2.setEnabled(false);
				telaAtualizar.add(CadastrarTelefone3);
				CadastrarTelefone3.setEnabled(false);

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
		CadastrarTelefone1.setVisible(true);
		CadastrarTelefone1.setBounds(400, 135, 180, 25);
		
		CadastrarTelefone1.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub

				if ("".equals(CadastrarTelefone1.getText())) {
					JOptionPane.showMessageDialog(null, "Insira ao menos 1 telefone para contato");

				} else {
					CadastrarTelefone2.setEnabled(true);
				}
			}
		});

		
		msgTelefone.setVisible(true);
		msgTelefone.setBounds(400, 110, 45, 30);

		CadastrarTelefone2.setVisible(true);
		CadastrarTelefone2.setBounds(400, 170, 180, 25);

		CadastrarTelefone2.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub

				if ("".equals(CadastrarTelefone2.getText())) {
					JOptionPane.showMessageDialog(null, "Segundo telefone não inserido. Campo não obrigatório!");

				} else {
					CadastrarTelefone3.setEnabled(true);
				}
			}
		});

		CadastrarTelefone3.setVisible(true);
		CadastrarTelefone3.setBounds(400, 205, 180, 25);

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
				CadastrarTelefone1.setText(null);
				CadastrarTelefone2.setText(null);
				CadastrarTelefone3.setText(null);

				CodigoCadastro.setEnabled(true);
				CadastrarNome.setEnabled(false);
				CadastrarCpf.setEnabled(false);
				CadastrarEmail.setEnabled(false);
				CadastrarTelefone1.setEnabled(false);
				CadastrarTelefone2.setEnabled(false);
				CadastrarTelefone3.setEnabled(false);

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
				CadastrarTelefone1.setEnabled(true);

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
				CadastrarTelefone1.setText(Registro[codPesquisa][4]);
				CadastrarTelefone2.setText(Registro[codPesquisa][5]);
				CadastrarTelefone3.setText(Registro[codPesquisa][6]);

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
					CadastrarTelefone1.setEnabled(true);
					if ("".equals(CadastrarTelefone2.getText())) {

					} else {
						CadastrarTelefone2.setEnabled(true);
					}
					if ("".equals(CadastrarTelefone3.getText())) {

					} else {
						CadastrarTelefone3.setEnabled(true);
					}

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
				CadastrarTelefone1.setText(Registro[codPesquisa][4]);
				CadastrarTelefone2.setText(Registro[codPesquisa][5]);
				CadastrarTelefone3.setText(Registro[codPesquisa][6]);

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
					CadastrarTelefone1.setEnabled(true);
					if ("".equals(CadastrarTelefone2.getText())) {

					} else {
						CadastrarTelefone2.setEnabled(true);
					}
					if ("".equals(CadastrarTelefone3.getText())) {

					} else {
						CadastrarTelefone3.setEnabled(true);
					}
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
						|| "".equals(CadastrarEmail.getText()) || "".equals(CadastrarTelefone1.getText())) {
					JOptionPane.showMessageDialog(null, "Há campo(s) em branco que aguarda(m) ser(em) preenchidos!");

				} else {

					codigoCadastro = AutoIncremento - 1;
					nomeCadastro = CadastrarNome.getText();
					cpfCadastro = CadastrarCpf.getHeight();
					emailCadastro = CadastrarEmail.getFocusAccelerator();
					Registro[codigoCadastro][0] = codigoAtual.getText();
					Registro[codigoCadastro][1] = CadastrarNome.getText();
					Registro[codigoCadastro][2] = CadastrarCpf.getText();
					Registro[codigoCadastro][3] = CadastrarEmail.getText();
					Registro[codigoCadastro][4] = CadastrarTelefone1.getText();
					Registro[codigoCadastro][5] = CadastrarTelefone2.getText();
					Registro[codigoCadastro][6] = CadastrarTelefone3.getText();

					JOptionPane.showMessageDialog(null, "Registro de Código " + AutoIncremento + " Salvo com Sucesso!");

					AutoIncremento++;

					int continuarCadastro = JOptionPane.showConfirmDialog(null, "Prosseguir com novos Cadastros?");
					if (continuarCadastro == JOptionPane.YES_OPTION) {

						// Reabrir Janela
						CadastrarNome.setText(null);
						CadastrarCpf.setText(null);
						CadastrarEmail.setText(null);
						CadastrarTelefone1.setText(null);
						CadastrarTelefone2.setText(null);
						CadastrarTelefone2.setEnabled(false);
						CadastrarTelefone3.setText(null);
						CadastrarTelefone3.setEnabled(false);

						telaCadastrar.add(codigoAtual);

						Codigo();
						telaCadastrar.add(msgCodigo);
						codigoAtual.setText(String.valueOf(AutoIncremento));

					} else {

						CadastrarNome.setText(null);
						CadastrarCpf.setText(null);
						CadastrarEmail.setText(null);
						CadastrarTelefone1.setText(null);
						CadastrarTelefone2.setText(null);
						CadastrarTelefone3.setText(null);
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
						|| "".equals(CadastrarEmail.getText()) || "".equals(CodigoCadastro.getText()) || "".equals(CadastrarTelefone1.getText()) ) {
					JOptionPane.showMessageDialog(null, "Há campo(s) em branco que aguarda(m) ser(em) preenchidos!");

				} else {

					Registro[codPesquisa][1] = CadastrarNome.getText();
					Registro[codPesquisa][2] = CadastrarCpf.getText();
					Registro[codPesquisa][3] = CadastrarEmail.getText();
					Registro[codigoCadastro][4] = CadastrarTelefone1.getText();
					Registro[codigoCadastro][5] = CadastrarTelefone2.getText();
					Registro[codigoCadastro][6] = CadastrarTelefone3.getText();

					JOptionPane.showMessageDialog(null, "Cliente " + (codPesquisa + 1) + " Atualizado com Sucesso!");

					CodigoCadastro.setText(null);
					CadastrarNome.setText(null);
					CadastrarCpf.setText(null);
					CadastrarEmail.setText(null);
					CadastrarTelefone1.setText(null);
					CadastrarTelefone2.setText(null);
					CadastrarTelefone3.setText(null);

					CodigoCadastro.setEnabled(true);
					CadastrarNome.setEnabled(false);
					CadastrarCpf.setEnabled(false);
					CadastrarEmail.setEnabled(false);
					CadastrarTelefone1.setEnabled(false);
					CadastrarTelefone2.setEnabled(false);
					CadastrarTelefone3.setEnabled(false);
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
						CadastrarTelefone1.setText(null);
						CadastrarTelefone2.setText(null);
						CadastrarTelefone3.setText(null);
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