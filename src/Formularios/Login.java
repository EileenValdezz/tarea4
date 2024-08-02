package Formularios; 

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;
import javax.swing.*;

import Clases.Usuario;
import ManejoUsuario.UsuarioDAO;

public class Login extends JFrame {
    private static final long serialVersionUID = 1L;
    private JTextField txtNombreUsuario;
    private JPasswordField txtContraseña;
    private JButton btnIniciarSesion, btnRegistrarse;

    public Login() {
        setTitle("LOGIN");
        setSize(400, 600); // Ajustar el tamaño
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false); // No permitir maximizar
        getContentPane().setBackground(new Color(255, 182, 193)); 
        
        // Cargar el icono
        URL iconURL = getClass().getResource("/icono.png");
        if (iconURL != null) {
            ImageIcon icon = new ImageIcon(iconURL);
            setIconImage(icon.getImage());
        }

        // Cargar el logo
        URL logoURL = getClass().getResource("/logo.png");
        if (logoURL != null) {
            ImageIcon logo = new ImageIcon(logoURL);
            Image img = logo.getImage();
            Image scaledImg = img.getScaledInstance(100, 100, Image.SCALE_SMOOTH); // Ajustar el tamaño del logo 
            ImageIcon scaledLogo = new ImageIcon(scaledImg);
            JLabel logoLabel = new JLabel(scaledLogo);
            add(logoLabel, BorderLayout.NORTH);
        }

        txtNombreUsuario = new JTextField(20);
        txtContraseña = new JPasswordField(20);

        btnIniciarSesion = new JButton("Iniciar Sesión");
        btnIniciarSesion.setForeground(Color.BLACK);
        btnIniciarSesion.setBackground(Color.GRAY);
        btnIniciarSesion.setFont(new Font("Arial", Font.BOLD, 14));
        btnIniciarSesion.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                iniciarSesion();
            }
        });

        btnRegistrarse = new JButton("Registrarse");
        btnRegistrarse.setForeground(Color.BLACK);
        btnRegistrarse.setBackground(Color.GRAY);
        btnRegistrarse.setFont(new Font("Arial", Font.BOLD, 14));
        btnRegistrarse.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                abrirRegistro();
            }
        });

        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(15, 15, 15, 15);
        gbc.anchor = GridBagConstraints.CENTER;

        
        JLabel titleLabel = new JLabel("LOGIN");
        titleLabel.setFont(new Font("Comic Sans MS", Font.ITALIC, 36));
        titleLabel.setForeground(Color.BLACK);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        add(titleLabel, gbc);

        gbc.gridwidth = 1;
        gbc.gridy = 1;
        add(new JLabel("Usuario:"), gbc);
        gbc.gridx = 1;
        add(txtNombreUsuario, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        add(new JLabel("Contraseña:"), gbc);
        gbc.gridx = 1;
        add(txtContraseña, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        add(btnIniciarSesion, gbc);

        gbc.gridy = 4;
        add(btnRegistrarse, gbc);

        
        ((JPanel) getContentPane()).setBorder(BorderFactory.createLineBorder(Color.BLACK, 1, true));
    }

    private void iniciarSesion() {
        String nombreUsuario = txtNombreUsuario.getText();
        String contraseña = new String(txtContraseña.getPassword());
        
        if (nombreUsuario.isEmpty() || contraseña.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Debe ingresar su usuario y contraseña, si no está registrado debe registrarse");
            return;
        }
        
        UsuarioDAO usuarioDAO = new UsuarioDAO();
        Usuario usuario = usuarioDAO.obtenerUsuario(nombreUsuario, contraseña);

        if (usuario == null) {
            JOptionPane.showMessageDialog(this, "El usuario que ingresó no está registrado");
        } else {
            new ListaUsuario(usuarioDAO).setVisible(true);
            this.dispose();
        }
    }

    private void abrirRegistro() {
        new Registro().setVisible(true);
        this.dispose();
    }

    public static void main(String[] args) {
    	System.out.println("Este es un mensaje nuevo");
        new Login().setVisible(true);
    }
}
