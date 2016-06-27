package exercicio;

/*	Cadastro de Clientes
 *  Felipe da Silva Lima
 *  Ciência da Computação - 3 Semestre
 *  Faculdade Alvorada - Maringá.*/

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

public class CadastroClientes {

	static JFrame telaPrincipal = new JFrame("Cadastro de Clientes");
	static JFrame telaCadastrar = new JFrame("Cadastrar");
	static JFrame telaAtualizar = new JFrame("Atualizar");
	static JFrame telaExcluir = new JFrame("Excluir");

	static JButton btCadastrarCliente = new JButton("Cadastrar Clientes");
	static JButton btAtualizarCliente = new JButton("Atualizar Clientes");
	static JButton btExcluir = new JButton("Excluir Cliente");
	static JButton btConsultar = new JButton("Consultar Clientes");
	static JButton btSair = new JButton("Sair");
	static JButton btSalvar = new JButton("Salvar");
	static JButton btAtualizarRegistro = new JButton("Atualizar");
	static JButton btFechar = new JButton("Fechar");
	static JButton btLimparTela = new JButton("Limpar Tela");
	static JButton btLiberarTela = new JButton("Novo");

	static JTextField tfCadastrarNome = new JTextField(null);
	static JTextField tfCadastrarCpf = new JTextField(null);
	static JTextField tfCadastrarEndereco = new JTextField(null);
	static JTextField tfCadastrarTelefoneCel = new JTextField(null);
	static JTextField tfCadastrarTelefoneRes = new JTextField(null);
	static JTextField tfCadastrarTelefoneCom = new JTextField(null);
	static JTextField tfCodigoCadastro = new JTextField(null);
	static JTextField tfPesquisarCliente = new JTextField("");

	static JLabel lbCodigoCadastro = new JLabel();
	static JLabel lbNome = new JLabel("Nome:");
	static JLabel lbCpf = new JLabel("CPF: ");
	static JLabel lbEndereco = new JLabel("Endereço:");
	static JLabel lbCodigo = new JLabel("Codigo de Cadastro:");
	static JLabel lbTelefoneCel = new JLabel("Fone Celular:");
	static JLabel lbTelefoneRes = new JLabel("Fone Residencial:");
	static JLabel lbTelefoneCom = new JLabel("Fone Comercial:");
	static JLabel lbMenu = new JLabel("Menu Principal");
	static JLabel lbStatusCliente = new JLabel("Cliente Inativo");
	static JLabel lbPesquisarCliente = new JLabel("Pesquisar Cliente");

	static JTable tableClientes = new JTable();
	static JScrollPane scrollClientes = new JScrollPane();
	static JPanel panelClientes = new JPanel();

	// Tamanho telas principais
	static int larguraPrincipal = 900;
	static int alturaPrincipal = 600;

	// Temanho telas secundárias
	static int larguraSecundaria = 600;
	static int alturaSecundaria = 400;

	static int quantRegistros = 100;
	static int quantColunas = 6;
	static int quantTelefones= 3;

	static String[][][] matrizRegistro = new String[quantRegistros][quantColunas][quantTelefones];

	// Status Cliente.
	static final String ATIVO = "ATIVO";
	static final String INATIVO = "INATIVO";

	static String nomeCadastro;
	static char emailCadastro;
	static int cpfCadastro;
	static int autoIncremento = 1;
	static int codigoCadastro = -1;
	static int codPesquisa;
	static int linha;
	static int valorLinha;

	private static final String ARIAL = "Arial";

	public static void main(String[] args) {

		janelaPrincipal();
		layoutMenu();
		componentesCodigo();
		componentesNome();
		componentesCpf();
		componentesEndereco();
		componentesTelefone();
		salvarCadastro();
		liberarTela();

		atualizarCadastro();
		limparTela();
		pesquisarCod();
		botaoFechar();

		JOptionPane.showMessageDialog(null,
				"      Bem vindo ao Cadastro de Clientes! \nPara que seus dados não sejam perdidos,\nnão feche a tela de Menus.");
	}

	static void janelaPrincipal() {
		telaPrincipal.setVisible(true);
		telaPrincipal.setSize(larguraPrincipal, alturaPrincipal);
		telaPrincipal.setLayout(null);
		telaPrincipal.setLocationRelativeTo(null);
		telaPrincipal.setResizable(false);
		telaPrincipal.getContentPane().setBackground(Color.WHITE);

		telaPrincipal.add(panelClientes);
		telaPrincipal.add(scrollClientes);
		telaPrincipal.add(tableClientes);
		tabelaClientes();

		telaPrincipal.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
	}

	static void tabelaClientes() {

		telaPrincipal.add(panelClientes);
		panelClientes.add(scrollClientes);
		scrollClientes.add(tableClientes);

		panelClientes.setVisible(true);
		panelClientes.setBounds(20, 180, 860, 380);
		panelClientes.setLayout(null);

		scrollClientes.setVisible(true);
		scrollClientes.setBounds(0, 0, 860, 380);
		scrollClientes.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		scrollClientes.setViewportView(tableClientes);

		tableClientes.setVisible(true);
		tableClientes.setLayout(null);
		tableClientes.setBounds(0, 0, 855, 380);
		tableClientes.setModel(new DefaultTableModel(new Object[] { "Código", "Nome", "CPF", "Endereço",
				"Telefone Celular", "Telefone Residencial", "Telefone Comercial", "Status" }, 0));

		filtrarPesquisa(tableClientes);
		tfPesquisarCliente.setVisible(true);
		telaPrincipal.add(tfPesquisarCliente);
		tfPesquisarCliente.setBounds(20, 140, 400, 25);

		limparPesquisa();
		for (int i = 0; i <= codigoCadastro; i++) {
			DefaultTableModel valores2 = (DefaultTableModel) tableClientes.getModel();
			valores2.addRow(new Object[] { i + 1, matrizRegistro[i][1][0], matrizRegistro[i][2][0], matrizRegistro[i][3][0],
					matrizRegistro[i][4][0], matrizRegistro[i][4][1], matrizRegistro[i][4][2], matrizRegistro[i][5][0] });
		}

	}

	static int verificarRegistro() {

		linha = tableClientes.getSelectedRow();
		valorLinha = (int) tableClientes.getValueAt(linha, 0);

		return valorLinha;
	}

	static void layoutMenu() {

		lbMenu.setFont(new Font(ARIAL, Font.ITALIC, 25));
		lbMenu.setBounds(100, 20, 250, 25);
		lbMenu.setVisible(true);
		telaPrincipal.add(lbMenu);

		janelaCadastrar();
		btCadastrarCliente.setVisible(true);
		telaPrincipal.add(btCadastrarCliente);
		btCadastrarCliente.setBounds(20, 65, 150, 40);

		janelaAtualizar();
		btAtualizarCliente.setVisible(true);
		telaPrincipal.add(btAtualizarCliente);
		btAtualizarCliente.setBounds(190, 65, 150, 40);

		botaoExcluir();
		btExcluir.setVisible(true);
		telaPrincipal.add(btExcluir);
		btExcluir.setBounds(450, 140, 150, 25);
		btExcluir.setBackground(null);

		botaoSair();
		btSair.setVisible(true);
		telaPrincipal.add(btSair);
		btSair.setBounds(810, 10, 80, 30);

		lbPesquisarCliente.setVisible(true);
		telaPrincipal.add(lbPesquisarCliente);
		lbPesquisarCliente.setBounds(20, 115, 400, 25);

		tfPesquisarCliente.setDocument(new UpperCaseDocument());
		telaPrincipal.setVisible(false);
		telaPrincipal.setVisible(true);
	}

	static void janelaCadastrar() {

		btCadastrarCliente.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				telaPrincipal.setVisible(false);
				telaCadastrar.setVisible(true);
				telaCadastrar.setSize(larguraSecundaria, alturaSecundaria);
				telaCadastrar.setLayout(null);
				telaCadastrar.setLocationRelativeTo(null);
				telaCadastrar.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
				telaCadastrar.setResizable(false);
				telaCadastrar.getContentPane().setBackground(Color.WHITE);

				telaCadastrar.add(btLimparTela);

				telaCadastrar.add(btLiberarTela);

				telaCadastrar.add(lbCodigoCadastro);

				telaCadastrar.add(lbCodigo);
				lbCodigoCadastro.setText(String.valueOf(autoIncremento));

				telaCadastrar.add(lbNome);
				telaCadastrar.add(tfCadastrarNome);
				tfCadastrarNome.setEnabled(false);

				telaCadastrar.add(lbCpf);
				telaCadastrar.add(tfCadastrarCpf);
				tfCadastrarCpf.setEnabled(false);

				telaCadastrar.add(lbEndereco);
				telaCadastrar.add(tfCadastrarEndereco);
				tfCadastrarEndereco.setEnabled(false);

				telaCadastrar.add(lbTelefoneCel);
				telaCadastrar.add(tfCadastrarTelefoneCel);
				tfCadastrarTelefoneCel.setEnabled(false);

				telaCadastrar.add(lbTelefoneRes);
				telaCadastrar.add(tfCadastrarTelefoneRes);
				tfCadastrarTelefoneRes.setEnabled(false);

				telaCadastrar.add(lbTelefoneCom);
				telaCadastrar.add(tfCadastrarTelefoneCom);
				tfCadastrarTelefoneCom.setEnabled(false);

				telaCadastrar.add(btSalvar);
				

				telaCadastrar.add(btFechar);

			}

		});
	}

	static void janelaAtualizar() {

		btAtualizarCliente.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				telaPrincipal.dispose();
				telaAtualizar.setVisible(true);
				telaAtualizar.setSize(larguraSecundaria, alturaSecundaria);
				telaAtualizar.setLayout(null);
				telaAtualizar.setLocationRelativeTo(null);
				telaAtualizar.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
				telaAtualizar.setResizable(false);
				telaAtualizar.getContentPane().setBackground(Color.WHITE);
				telaAtualizar.add(lbCodigo);
				telaAtualizar.add(btLimparTela);
				telaAtualizar.add(tfCodigoCadastro);
				telaAtualizar.add(btFechar);
				telaAtualizar.add(btAtualizarRegistro);

				if (tableClientes.getSelectedRow() != -1) {

					obterDados();

				} else {

					telaAtualizar.add(lbCodigo);
					telaAtualizar.add(tfCodigoCadastro);
					tfCodigoCadastro.setEnabled(true);

					telaAtualizar.add(lbNome);
					telaAtualizar.add(tfCadastrarNome);
					tfCadastrarNome.setEnabled(false);

					telaAtualizar.add(lbCpf);
					telaAtualizar.add(tfCadastrarCpf);
					tfCadastrarCpf.setEnabled(false);

					telaAtualizar.add(lbEndereco);
					telaAtualizar.add(tfCadastrarEndereco);
					tfCadastrarEndereco.setEnabled(false);

					telaAtualizar.add(lbTelefoneCel);
					telaAtualizar.add(tfCadastrarTelefoneCel);
					tfCadastrarTelefoneCel.setEnabled(false);

					telaAtualizar.add(lbTelefoneRes);
					telaAtualizar.add(tfCadastrarTelefoneRes);
					tfCadastrarTelefoneRes.setEnabled(false);

					telaAtualizar.add(lbTelefoneCom);
					telaAtualizar.add(tfCadastrarTelefoneCom);
					tfCadastrarTelefoneCom.setEnabled(false);

					btAtualizarRegistro.setEnabled(true);

				}
				limparPesquisa();
			}

		});
	}

	static void obterDados() {
		int valorLinha = verificarRegistro();

		tfCodigoCadastro.setText(String.valueOf(valorLinha));
		tfCodigoCadastro.setEnabled(false);
		telaAtualizar.add(lbNome);
		telaAtualizar.add(tfCadastrarNome);
		tfCadastrarNome.setEnabled(true);
		String nomeRegistro = (String) tableClientes.getModel().getValueAt(valorLinha - 1, 1);
		tfCadastrarNome.setText(nomeRegistro);

		telaAtualizar.add(lbCpf);
		telaAtualizar.add(tfCadastrarCpf);
		tfCadastrarCpf.setEnabled(true);
		String cpfRegistro = (String) tableClientes.getModel().getValueAt(valorLinha - 1, 2);
		tfCadastrarCpf.setText(cpfRegistro);

		telaAtualizar.add(lbEndereco);
		telaAtualizar.add(tfCadastrarEndereco);
		tfCadastrarEndereco.setEnabled(true);
		String emailRegistro = (String) tableClientes.getModel().getValueAt(valorLinha - 1, 3);
		tfCadastrarEndereco.setText(emailRegistro);

		telaAtualizar.add(lbTelefoneCel);
		telaAtualizar.add(tfCadastrarTelefoneCel);
		tfCadastrarTelefoneCel.setEnabled(true);
		String celularRegistro = (String) tableClientes.getModel().getValueAt(valorLinha - 1, 4);
		tfCadastrarTelefoneCel.setText(celularRegistro);

		telaAtualizar.add(lbTelefoneRes);
		telaAtualizar.add(tfCadastrarTelefoneRes);
		tfCadastrarTelefoneRes.setEnabled(true);
		String residencialRegistro = (String) tableClientes.getModel().getValueAt(valorLinha - 1, 5);
		tfCadastrarTelefoneRes.setText(residencialRegistro);

		telaAtualizar.add(lbTelefoneCom);
		telaAtualizar.add(tfCadastrarTelefoneCom);
		tfCadastrarTelefoneCom.setEnabled(true);
		String comercialRegistro = (String) tableClientes.getModel().getValueAt(valorLinha - 1, 6);
		tfCadastrarTelefoneCom.setText(comercialRegistro);

		if (matrizRegistro[valorLinha - 1][5][0] == INATIVO) {

			telaAtualizar.add(lbStatusCliente);
			tfCodigoCadastro.setEnabled(false);
			tfCadastrarNome.setEnabled(false);
			tfCadastrarCpf.setEnabled(false);
			tfCadastrarEndereco.setEnabled(false);
			tfCadastrarTelefoneCel.setEnabled(false);
			tfCadastrarTelefoneRes.setEnabled(false);
			tfCadastrarTelefoneCom.setEnabled(false);
			btAtualizarRegistro.setEnabled(false);

			lbStatusCliente.setBounds(50, 250, 300, 50);
			lbStatusCliente.setFont(new Font(ARIAL, Font.ITALIC, 25));
			lbStatusCliente.setVisible(true);
			JOptionPane.showMessageDialog(null,
					"CLIENTE INATIVO.\n Para manipular dados deste cliente\nrealize um novo cadastro.");

		} else {
			btAtualizarRegistro.setEnabled(true);
		}
	}

	static void filtrarPesquisa(JTable pesquisar) {

		tfPesquisarCliente.addKeyListener(new java.awt.event.KeyListener() {

			DefaultTableModel padrao = (DefaultTableModel) tableClientes.getModel();

			@SuppressWarnings("unchecked")
			@Override
			public void keyPressed(KeyEvent e) {

				@SuppressWarnings("rawtypes")
				TableRowSorter sorter = new TableRowSorter(padrao);
				pesquisar.setRowSorter(sorter);
				String text = tfPesquisarCliente.getText();

				if (text.length() == 0) {
					sorter.setRowFilter(null);
				} else {
					sorter.setRowFilter(RowFilter.regexFilter(text));
				}

			}

			@SuppressWarnings("unchecked")
			@Override
			public void keyReleased(KeyEvent e) {

				@SuppressWarnings("rawtypes")
				TableRowSorter sorter = new TableRowSorter(padrao);
				pesquisar.setRowSorter(sorter);
				String text = tfPesquisarCliente.getText();

				if (text.length() > 0) {
					sorter.setRowFilter(RowFilter.regexFilter(text));
				} else {
					sorter.setRowFilter(null);
				}

			}

			@SuppressWarnings("unchecked")
			@Override
			public void keyTyped(KeyEvent e) {

				@SuppressWarnings("rawtypes")
				TableRowSorter sorter = new TableRowSorter(padrao);
				pesquisar.setRowSorter(sorter);
				String text = tfPesquisarCliente.getText();

				if (text.length() == 0) {
					sorter.setRowFilter(null);
				} else {
					sorter.setRowFilter(RowFilter.regexFilter(text));
				}
			}
		});
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	static void limparPesquisa() {
		if (!"".equals(tfPesquisarCliente.getText())) {
			DefaultTableModel model = (DefaultTableModel) tableClientes.getModel();
			TableRowSorter sorter = new TableRowSorter(model);
			tableClientes.setRowSorter(sorter);
			tfPesquisarCliente.setText(null);
		}
	}

	static void botaoExcluir() {

		btExcluir.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (tableClientes.getSelectedRow() != -1) {
					int linhaRegistro = verificarRegistro();
					if (matrizRegistro[linhaRegistro - 1][5][0] == INATIVO) {
						JOptionPane.showMessageDialog(null, "Cliente já Inativado!");
					} else {

						if (JOptionPane.showConfirmDialog(null,
								"Clientes excluidos não mais serão restaurados.\n - Todos os dados inseridos serão bloqueados\n"
										+ " sem que seja possivel altera-los.\n "
										+ "Tem realmente certeza que deseja continuar?") == JOptionPane.OK_OPTION) {

							matrizRegistro[linhaRegistro - 1][5][0] = INATIVO;

							telaPrincipal.dispose();
							janelaPrincipal();

							JOptionPane.showMessageDialog(null,
									"Cliente Inativo.\n Para acesso aos dados, realize novo Cadastro!");
						}
					}
				} else {
					JOptionPane.showMessageDialog(null, "Selecione o cliente que deseja excluir!");
				}
			}
		});

	}

	static void botaoSair() {
		btSair.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				if (JOptionPane.showConfirmDialog(null,
						"Os dados inseridos serão peridos!\nDeseja realmente continuar?") == JOptionPane.OK_OPTION) {

					JOptionPane.showMessageDialog(null, "Registro Limpo.\nSistema será fechado!");
					System.exit(0);
				}
			}
		});

	}

	static void componentesCodigo() {

		tfCodigoCadastro.setVisible(true);
		tfCodigoCadastro.setBounds(480, 20, 40, 25);

		lbCodigo.setVisible(true);
		lbCodigo.setBounds(350, 20, 130, 30);

		lbCodigoCadastro.setVisible(true);
		lbCodigoCadastro.setBounds(500, 20, 155, 30);
	}

	static void componentesNome() {

		tfCadastrarNome.setVisible(true);
		tfCadastrarNome.setBounds(50, 75, 500, 25);
		lbNome.setVisible(true);
		lbNome.setBounds(50, 50, 45, 30);
		tfCadastrarNome.setDocument(new UpperCaseDocument());

	}

	static void componentesCpf() {
		tfCadastrarCpf.setVisible(true);
		tfCadastrarCpf.setBounds(50, 135, 200, 25);
		lbCpf.setVisible(true);
		lbCpf.setBounds(50, 110, 100, 30);

	}

	static void componentesEndereco() {
		tfCadastrarEndereco.setVisible(true);
		tfCadastrarEndereco.setBounds(50, 205, 300, 25);
		lbEndereco.setVisible(true);
		lbEndereco.setBounds(50, 180, 80, 30);
		tfCadastrarEndereco.setDocument(new UpperCaseDocument());

	}

	static void componentesTelefone() {

		lbTelefoneCel.setVisible(true);
		lbTelefoneCel.setBounds(400, 110, 150, 30);
		tfCadastrarTelefoneCel.setVisible(true);
		tfCadastrarTelefoneCel.setBounds(400, 135, 180, 25);

		lbTelefoneRes.setVisible(true);
		lbTelefoneRes.setBounds(400, 165, 150, 30);
		tfCadastrarTelefoneRes.setVisible(true);
		tfCadastrarTelefoneRes.setBounds(400, 190, 180, 25);

		lbTelefoneCom.setVisible(true);
		lbTelefoneCom.setBounds(400, 220, 150, 30);
		tfCadastrarTelefoneCom.setVisible(true);
		tfCadastrarTelefoneCom.setBounds(400, 245, 180, 25);

	}

	static void limparTela() {
		btLimparTela.setVisible(true);
		btLimparTela.setBounds(125, 20, 110, 20);

		btLimparTela.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				btAtualizarRegistro.setEnabled(true);
				btSalvar.setEnabled(false);
				tfCodigoCadastro.setText(null);
				tfCadastrarNome.setText(null);
				tfCadastrarCpf.setText(null);
				tfCadastrarEndereco.setText(null);
				tfCadastrarTelefoneCel.setText(null);
				tfCadastrarTelefoneRes.setText(null);
				tfCadastrarTelefoneCom.setText(null);

				tfCodigoCadastro.setEnabled(true);
				tfCadastrarNome.setEnabled(false);
				tfCadastrarCpf.setEnabled(false);
				tfCadastrarEndereco.setEnabled(false);
				tfCadastrarTelefoneCel.setEnabled(false);
				tfCadastrarTelefoneRes.setEnabled(false);
				tfCadastrarTelefoneCom.setEnabled(false);

				lbStatusCliente.setVisible(false);

			}
		});
	}

	static void liberarTela() {
		btLiberarTela.setVisible(true);
		btLiberarTela.setBounds(50, 20, 65, 20);

		btLiberarTela.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				tfCadastrarNome.setEnabled(true);
				tfCadastrarCpf.setEnabled(true);
				tfCadastrarEndereco.setEnabled(true);
				tfCadastrarTelefoneCel.setEnabled(true);
				tfCadastrarTelefoneRes.setEnabled(true);
				tfCadastrarTelefoneCom.setEnabled(true);
				btSalvar.setEnabled(true);

			}
		});

	}

	static void pesquisarCod() {

		tfCodigoCadastro.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				String cod = tfCodigoCadastro.getText();
				codPesquisa = Integer.parseInt(cod) - 1;

				tfCadastrarNome.setText(matrizRegistro[codPesquisa][1][0]);
				tfCadastrarCpf.setText(matrizRegistro[codPesquisa][2][0]);
				tfCadastrarEndereco.setText(matrizRegistro[codPesquisa][3][0]);
				tfCadastrarTelefoneCel.setText(matrizRegistro[codPesquisa][4][0]);
				tfCadastrarTelefoneRes.setText(matrizRegistro[codPesquisa][4][1]);
				tfCadastrarTelefoneCom.setText(matrizRegistro[codPesquisa][4][2]);

				if ("".equals(tfCadastrarNome.getText()) && "".equals(tfCadastrarCpf.getText())
						&& "".equals(tfCadastrarEndereco.getText())) {
					JOptionPane.showMessageDialog(null, "  Cadastro não localizado.");
					tfCodigoCadastro.setText(null);
					tfCodigoCadastro.setEnabled(true);
					tfCadastrarNome.setEnabled(false);
					tfCadastrarCpf.setEnabled(false);
					tfCadastrarEndereco.setEnabled(false);

				} else {
					tfCodigoCadastro.setEnabled(false);
					tfCadastrarNome.setEnabled(true);
					tfCadastrarCpf.setEnabled(true);
					tfCadastrarEndereco.setEnabled(true);
					tfCadastrarTelefoneCel.setEnabled(true);
					tfCadastrarTelefoneRes.setEnabled(true);
					tfCadastrarTelefoneCom.setEnabled(true);

					if (matrizRegistro[codPesquisa][5][0] == INATIVO) {
						btAtualizarRegistro.setEnabled(false);
						tfCodigoCadastro.setEnabled(false);
						tfCadastrarNome.setEnabled(false);
						tfCadastrarCpf.setEnabled(false);
						tfCadastrarEndereco.setEnabled(false);
						tfCadastrarTelefoneCel.setEnabled(false);
						tfCadastrarTelefoneRes.setEnabled(false);
						tfCadastrarTelefoneCom.setEnabled(false);

						lbStatusCliente.setBounds(50, 250, 300, 50);
						lbStatusCliente.setFont(new Font(ARIAL, Font.ITALIC, 25));
						lbStatusCliente.setVisible(true);
						telaAtualizar.add(lbStatusCliente);
						JOptionPane.showMessageDialog(null,
								"CLIENTE INATIVO.\n Para manipular dados deste cliente\nrealize um novo cadastro.");

						telaAtualizar.setVisible(false);
						telaAtualizar.setVisible(true);
					}

				}

			}
		});
	}

	static void salvarCadastro() {
		btSalvar.setVisible(true);
		btSalvar.setEnabled(false);
		btSalvar.setBounds(480, 320, 80, 30);

		btSalvar.addActionListener(new ActionListener() {

			@Override
			@SuppressWarnings({ "rawtypes", "unchecked" })
			public void actionPerformed(ActionEvent e) {
				if ("".equals(tfCadastrarNome.getText()) || "".equals(tfCadastrarCpf.getText())
						|| "".equals(tfCadastrarEndereco.getText())) {
					JOptionPane.showMessageDialog(null, "Há campo(s) em branco que aguarda(m) ser(em) preenchidos!");

				} else if ("".equals(tfCadastrarTelefoneCel.getText()) && "".equals(tfCadastrarTelefoneRes.getText())
						&& "".equals(tfCadastrarTelefoneCom.getText())) {
					JOptionPane.showMessageDialog(null, "Cadastre um telefone para contato!");
				} else {

					codigoCadastro = autoIncremento - 1;
					matrizRegistro[codigoCadastro][0][0] = lbCodigoCadastro.getText();
					matrizRegistro[codigoCadastro][1][0] = tfCadastrarNome.getText();
					matrizRegistro[codigoCadastro][2][0] = tfCadastrarCpf.getText();
					matrizRegistro[codigoCadastro][3][0] = tfCadastrarEndereco.getText();
					matrizRegistro[codigoCadastro][4][0] = tfCadastrarTelefoneCel.getText();
					matrizRegistro[codigoCadastro][4][1] = tfCadastrarTelefoneRes.getText();
					matrizRegistro[codigoCadastro][4][2] = tfCadastrarTelefoneCom.getText();
					matrizRegistro[codigoCadastro][5][0] = ATIVO;

					JOptionPane.showMessageDialog(null, "Registro de Código " + autoIncremento + " Salvo com Sucesso!");

					autoIncremento++;

					int continuarCadastro = JOptionPane.showConfirmDialog(null, "Prosseguir com novos Cadastros?");
					if (continuarCadastro == JOptionPane.YES_OPTION) {

						tfCadastrarNome.setText(null);
						tfCadastrarCpf.setText(null);
						tfCadastrarEndereco.setText(null);
						tfCadastrarTelefoneCel.setText(null);
						tfCadastrarTelefoneRes.setText(null);
						tfCadastrarTelefoneCom.setText(null);

						telaCadastrar.add(lbCodigoCadastro);

						componentesCodigo();
						telaCadastrar.add(lbCodigo);
						lbCodigoCadastro.setText(String.valueOf(autoIncremento));

						telaPrincipal.dispose();

					} else {
						DefaultTableModel model = (DefaultTableModel) tableClientes.getModel();
						TableRowSorter sorter = new TableRowSorter(model);
						tableClientes.setRowSorter(sorter);
						tfPesquisarCliente.setText("0");
						
						tfCadastrarNome.setText(null);
						tfCadastrarCpf.setText(null);
						tfCadastrarEndereco.setText(null);
						tfCadastrarTelefoneCel.setText(null);
						tfCadastrarTelefoneRes.setText(null);
						tfCadastrarTelefoneCom.setText(null);
						btSalvar.setEnabled(false);
						telaCadastrar.dispose();
						telaPrincipal.dispose();
						janelaPrincipal();
					}
				}
			}
		});

	}

	static void atualizarCadastro() {
		btAtualizarRegistro.setVisible(true);
		btAtualizarRegistro.setBounds(450, 320, 110, 30);

		btAtualizarRegistro.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				if ("".equals(tfCadastrarNome.getText()) || "".equals(tfCadastrarCpf.getText())
						|| "".equals(tfCadastrarEndereco.getText()) || "".equals(tfCodigoCadastro.getText())) {
					JOptionPane.showMessageDialog(null, "Há campo(s) em branco que aguarda(m) ser(em) preenchidos!");

				} else {
					String cod = tfCodigoCadastro.getText();
					int codAtualizar = Integer.parseInt(cod) - 1;

					matrizRegistro[codAtualizar][1][0] = tfCadastrarNome.getText();
					matrizRegistro[codAtualizar][2][0] = tfCadastrarCpf.getText();
					matrizRegistro[codAtualizar][3][0] = tfCadastrarEndereco.getText();
					matrizRegistro[codAtualizar][4][0] = tfCadastrarTelefoneCel.getText();
					matrizRegistro[codAtualizar][4][1] = tfCadastrarTelefoneRes.getText();
					matrizRegistro[codAtualizar][4][2] = tfCadastrarTelefoneCom.getText();

					JOptionPane.showMessageDialog(null, "Cliente " + cod + " Atualizado com Sucesso!");

					tfCodigoCadastro.setText(null);
					tfCadastrarNome.setText(null);
					tfCadastrarCpf.setText(null);
					tfCadastrarEndereco.setText(null);
					tfCadastrarTelefoneCel.setText(null);
					tfCadastrarTelefoneRes.setText(null);
					tfCadastrarTelefoneCom.setText(null);

					tfCodigoCadastro.setEnabled(true);
					tfCadastrarNome.setEnabled(false);
					tfCadastrarCpf.setEnabled(false);
					tfCadastrarEndereco.setEnabled(false);
					tfCadastrarTelefoneCel.setEnabled(false);
					tfCadastrarTelefoneRes.setEnabled(false);
					tfCadastrarTelefoneCom.setEnabled(false);
				}

			}
		});
	}

	static void botaoFechar() {
		btFechar.setVisible(true);
		btFechar.setBounds(20, 320, 80, 30);
		btFechar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				if (!"".equals(tfCadastrarNome.getText()) || !"".equals(tfCadastrarCpf.getText())
						|| !"".equals(tfCadastrarEndereco.getText())) {

					if (JOptionPane.showConfirmDialog(null,
							"Sair sem salvar descartará informações inseridas.\n Deseja Continuar?") == JOptionPane.OK_OPTION) {
						btSalvar.setEnabled(false);
						tfCodigoCadastro.setText(null);
						tfCadastrarNome.setText(null);
						tfCadastrarCpf.setText(null);
						tfCadastrarEndereco.setText(null);
						tfCadastrarTelefoneCel.setText(null);
						tfCadastrarTelefoneRes.setText(null);
						tfCadastrarTelefoneCom.setText(null);
						lbStatusCliente.setVisible(false);
						telaCadastrar.dispose();
						telaAtualizar.dispose();
						telaPrincipal.dispose();
						janelaPrincipal();

					}
				} else {
					btSalvar.setEnabled(false);
					tfCodigoCadastro.setEnabled(true);
					tfCodigoCadastro.setText(null);
					tfCadastrarNome.setText(null);
					tfCadastrarCpf.setText(null);
					tfCadastrarEndereco.setText(null);
					tfCadastrarTelefoneCel.setText(null);
					tfCadastrarTelefoneRes.setText(null);
					tfCadastrarTelefoneCom.setText(null);
					lbStatusCliente.setVisible(false);
					telaCadastrar.dispose();
					telaAtualizar.dispose();
					telaPrincipal.dispose();
					janelaPrincipal();

				}
			}

		});
	}
}