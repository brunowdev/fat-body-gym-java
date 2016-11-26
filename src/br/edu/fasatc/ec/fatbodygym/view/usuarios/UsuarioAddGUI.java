package br.edu.fasatc.ec.fatbodygym.view.usuarios;

import javax.swing.JOptionPane;

import br.edu.fasatc.ec.fatbodygym.exceptions.ReadFileException;
import br.edu.fasatc.ec.fatbodygym.exceptions.WriteFileException;
import br.edu.fasatc.ec.fatbodygym.model.Usuario;
import br.edu.fasatc.ec.fatbodygym.persistence.repository.UsuarioRepository;
import br.edu.fasatc.ec.fatbodygym.view.login.LoginController;

public class UsuarioAddGUI extends javax.swing.JDialog {

	private static final long serialVersionUID = 5010996200538866892L;

	public UsuarioAddGUI(java.awt.Frame parent, boolean modal) {
		super(parent, modal);
		initComponents();
	}

	private boolean isValido(Usuario usuario) {

		if (usuario.getEmail() == null || usuario.getEmail().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Por favor, informe um e-mail.", "Campos não prenchidos", JOptionPane.ERROR_MESSAGE);
			return false;
		}

		if (usuario.getSenha() == null || usuario.getSenha().isEmpty()) {
			JOptionPane.showMessageDialog(null, "Por favor, informe uma senha.", "Campos não prenchidos", JOptionPane.ERROR_MESSAGE);
			return false;
		}

		return true;
	}

	private void limparCampos() {
		TF_USERNAME.setText("");
		TF_PASSWORD.setText("");
	}

	private void initComponents() {

		jPanel1 = new javax.swing.JPanel();
		TF_PASSWORD = new javax.swing.JPasswordField();
		TF_USERNAME = new javax.swing.JTextField();
		BT_SALVAR = new javax.swing.JButton();
		jLabel1 = new javax.swing.JLabel();
		jLabel2 = new javax.swing.JLabel();

		setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
		setTitle("Crie sua conta");
		setModal(true);
		setResizable(false);

		jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Criar conta", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION,
				javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 12))); // NOI18N

		TF_PASSWORD.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

		TF_USERNAME.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

		BT_SALVAR.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
		BT_SALVAR.setText("Salvar");
		BT_SALVAR.setToolTipText("Crie uma conta.");
		BT_SALVAR.addActionListener(new java.awt.event.ActionListener() {
			@Override
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				try {
					BT_SALVARActionPerformed(evt);
				} catch (final ReadFileException e) {
					e.printStackTrace();
				}
			}
		});

		jLabel1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
		jLabel1.setText("E-mail:");

		jLabel2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
		jLabel2.setText("Senha:");

		final javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
		jPanel1.setLayout(jPanel1Layout);
		jPanel1Layout
				.setHorizontalGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(jPanel1Layout.createSequentialGroup().addContainerGap().addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
								.addComponent(TF_USERNAME, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 348, Short.MAX_VALUE)
								.addComponent(TF_PASSWORD, javax.swing.GroupLayout.Alignment.TRAILING)
								.addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup().addGap(0, 0, Short.MAX_VALUE).addComponent(BT_SALVAR))
								.addGroup(jPanel1Layout.createSequentialGroup()
										.addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addComponent(jLabel1).addComponent(jLabel2))
										.addGap(0, 0, Short.MAX_VALUE)))
								.addContainerGap()));
		jPanel1Layout.setVerticalGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(javax.swing.GroupLayout.Alignment.TRAILING,
				jPanel1Layout.createSequentialGroup().addContainerGap().addComponent(jLabel1).addGap(1, 1, 1)
						.addComponent(TF_USERNAME, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED).addComponent(jLabel2).addGap(4, 4, 4)
						.addComponent(TF_PASSWORD, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
						.addComponent(BT_SALVAR, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
						.addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));

		final javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
		getContentPane().setLayout(layout);
		layout.setHorizontalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(layout.createSequentialGroup().addContainerGap()
				.addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE).addContainerGap()));
		layout.setVerticalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(layout.createSequentialGroup().addContainerGap()
				.addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE).addContainerGap()));

		pack();
		setLocationRelativeTo(null);
	}// </editor-fold>//GEN-END:initComponents

	private void BT_SALVARActionPerformed(java.awt.event.ActionEvent evt) throws ReadFileException {// GEN-FIRST:event_BT_SALVARActionPerformed

		final LoginController lc = new LoginController();

		final String email = TF_USERNAME.getText();
		final String senha = new String(TF_PASSWORD.getPassword());

		if (!lc.isNomeEmailUsuarioDisponivel(email)) {
			JOptionPane.showMessageDialog(null, "Este nome de usuário não está disponível.\nEscolha outro e tente novamente.", "Usuário já existe", JOptionPane.WARNING_MESSAGE);
			return;
		}

		limparCampos();

		Usuario usuario = new Usuario(email, senha);

		if (!isValido(usuario)) {
			return;
		}

		final UsuarioRepository uRepo = new UsuarioRepository();
		try {
			usuario = uRepo.merge(usuario);
			JOptionPane.showMessageDialog(null, "Usuário \"" + usuario.getEmail() + "\" cadastrado com sucesso.", "Sucesso ao salvar", JOptionPane.INFORMATION_MESSAGE);
			// usuarioTableModel.addRow(usuario);
			dispose();
		} catch (WriteFileException | ReadFileException ex) {
			JOptionPane.showMessageDialog(null, "Ocorreu um erro ao salvar usuário.\n\nErro: " + ex.getMessage(), "Erro ao cadastrar usuário", JOptionPane.ERROR_MESSAGE);
		}

	}// GEN-LAST:event_BT_SALVARActionPerformed

	public static void main(String args[]) {
		try {
			for (final javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
				if ("Nimbus".equals(info.getName())) {
					javax.swing.UIManager.setLookAndFeel(info.getClassName());
					break;
				}
			}
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
			java.util.logging.Logger.getLogger(UsuarioAddGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
		}

		java.awt.EventQueue.invokeLater(() -> {
			final UsuarioAddGUI dialog = new UsuarioAddGUI(new javax.swing.JFrame(), true);
			dialog.addWindowListener(new java.awt.event.WindowAdapter() {
				@Override
				public void windowClosing(java.awt.event.WindowEvent e) {
					System.exit(0);
				}
			});
			dialog.setVisible(true);
		});
	}

	private javax.swing.JButton BT_SALVAR;
	private javax.swing.JPasswordField TF_PASSWORD;
	private javax.swing.JTextField TF_USERNAME;
	private javax.swing.JLabel jLabel1;
	private javax.swing.JLabel jLabel2;
	private javax.swing.JPanel jPanel1;

}
