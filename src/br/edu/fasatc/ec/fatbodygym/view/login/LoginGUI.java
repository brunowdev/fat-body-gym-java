package br.edu.fasatc.ec.fatbodygym.view.login;

import javax.swing.JOptionPane;

import br.edu.fasatc.ec.fatbodygym.exceptions.EntidadeNaoEncontradaException;
import br.edu.fasatc.ec.fatbodygym.exceptions.ReadFileException;
import br.edu.fasatc.ec.fatbodygym.model.Usuario;
import br.edu.fasatc.ec.fatbodygym.view.MenuApp;

public class LoginGUI extends javax.swing.JDialog {

	private static final long serialVersionUID = -4220986390055662119L;

	public LoginGUI(java.awt.Frame parent, boolean modal) {
		super(parent, modal);
		initComponents();
	}

	private void initComponents() {

		jPanel1 = new javax.swing.JPanel();
		TF_PASSWORD = new javax.swing.JPasswordField();
		TF_USERNAME = new javax.swing.JTextField();
		jButton1 = new javax.swing.JButton();
		jButton2 = new javax.swing.JButton();

		setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
		setTitle("Fat Body GYM - Login");
		setResizable(false);

		jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Acesso ao sistema", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION,
				javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 12)));

		TF_PASSWORD.setFont(new java.awt.Font("Tahoma", 0, 14));

		TF_USERNAME.setFont(new java.awt.Font("Tahoma", 0, 14));

		jButton1.setFont(new java.awt.Font("Tahoma", 0, 14));
		jButton1.setText("Entrar");
		jButton1.setToolTipText("Acessa o sistema");
		jButton1.addActionListener(new java.awt.event.ActionListener() {
			@Override
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jButton1ActionPerformed(evt);
			}
		});

		jButton2.setFont(new java.awt.Font("Tahoma", 0, 14));
		jButton2.setText("Criar conta");
		jButton2.setToolTipText("Crie uma conta.");
		jButton2.addActionListener(new java.awt.event.ActionListener() {
			@Override
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jButton2ActionPerformed(evt);
			}
		});

		final javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
		jPanel1.setLayout(jPanel1Layout);
		jPanel1Layout.setHorizontalGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(javax.swing.GroupLayout.Alignment.TRAILING,
				jPanel1Layout.createSequentialGroup().addContainerGap()
						.addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
								.addGroup(jPanel1Layout.createSequentialGroup().addGap(0, 113, Short.MAX_VALUE).addComponent(jButton2)
										.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED).addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 88,
												javax.swing.GroupLayout.PREFERRED_SIZE))
								.addComponent(TF_USERNAME).addComponent(TF_PASSWORD))
						.addContainerGap()));
		jPanel1Layout.setVerticalGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(javax.swing.GroupLayout.Alignment.TRAILING,
				jPanel1Layout.createSequentialGroup().addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(TF_USERNAME, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE).addGap(18, 18, 18)
						.addComponent(TF_PASSWORD, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
						.addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
								.addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
								.addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
						.addGap(7, 7, 7)));

		final javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
		getContentPane().setLayout(layout);
		layout.setHorizontalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(layout.createSequentialGroup().addContainerGap()
				.addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE).addContainerGap()));
		layout.setVerticalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(layout.createSequentialGroup().addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
						.addContainerGap()));

		pack();
		setLocationRelativeTo(null);
	}

	private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {

		final LoginController lc = new LoginController();

		final String email = TF_USERNAME.getText();
		final String senha = new String(TF_PASSWORD.getPassword());

		limparCampos();

		try {
			final boolean logado = lc.login(new Usuario(email, senha));
			if (logado) {
				final Usuario usuarioLogado = lc.getUsuarioPorEmail(email);
				MenuApp.definirUsuarioLogado(usuarioLogado);
				JOptionPane.showMessageDialog(null, "Bem-Vindo(a) " + usuarioLogado.getEmail() + "!", "Login efetuado com sucesso", JOptionPane.INFORMATION_MESSAGE);
				this.dispose();
			}
		} catch (ReadFileException | EntidadeNaoEncontradaException ex) {
			JOptionPane.showMessageDialog(null, "Ocorreu um erro ao efetuar login." + ex.getMessage(), "Erro ao efetuar login", JOptionPane.ERROR_MESSAGE);
		}

	}

	private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {

		final UsuarioAddGUI uGUI = new UsuarioAddGUI(null, true);
		uGUI.setVisible(true);

	}

	private void limparCampos() {
		TF_USERNAME.setText("");
		TF_PASSWORD.setText("");
	}

	private javax.swing.JPasswordField TF_PASSWORD;
	private javax.swing.JTextField TF_USERNAME;
	private javax.swing.JButton jButton1;
	private javax.swing.JButton jButton2;
	private javax.swing.JPanel jPanel1;
}
