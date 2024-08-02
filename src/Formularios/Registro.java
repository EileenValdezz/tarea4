package Formularios;

import Clases.Usuario;
import ManejoUsuario.UsuarioDAO;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;
import java.awt.Image;

public class Registro extends JFrame {
    private static final long serialVersionUID = 1L;
    private JTextField txtNombreUsuario, txtNombre, txtApellido, txtTelefono, txtEmail;
    private JPasswordField txtContraseña, txtConfirmarContraseña;
    private JButton btnRegistrar, btnCancelar; 

    public Registro() {
        setTitle("REGISTRO");
        setSize(500, 700); // Ajustar el tamaño
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false); // No permitir maximizar
        getContentPane().setBackground(new Color(255, 182, 193));
        URL iconURL = getClass().getResource("/icono.png");
        if (iconURL != null) {
            ImageIcon icon = new ImageIcon(iconURL);
            setIconImage(icon.getImage());
        }
        
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(15, 15, 15, 15);
        gbc.anchor = GridBagConstraints.CENTER;
        
        // Cargar el logo
        URL logoURL = getClass().getResource("/logo.png");
        if (logoURL != null) {
            ImageIcon logo = new ImageIcon(logoURL);
            Image img = logo.getImage();
            Image scaledImg = img.getScaledInstance(70, 70, Image.SCALE_SMOOTH); // Ajustar el tamaño del logo 
            ImageIcon scaledLogo = new ImageIcon(scaledImg);
            JLabel logoLabel = new JLabel(scaledLogo);
            
            gbc.gridx = 0;
            gbc.gridy = 0;
            gbc.gridwidth = 1;
            gbc.anchor = GridBagConstraints.WEST;
            gbc.insets = new Insets(0, 0, 0, 10); // Ajustar los márgenes según sea necesario
            add(logoLabel, gbc);
        }

        txtNombreUsuario = new JTextField(20);
        txtNombre = new JTextField(20);
        txtApellido = new JTextField(20);
        txtTelefono = new JTextField(20);
        txtEmail = new JTextField(20);
        txtContraseña = new JPasswordField(20);
        txtConfirmarContraseña = new JPasswordField(20);

        btnRegistrar = new JButton("Registrar");
        btnRegistrar.setForeground(Color.BLACK);
        btnRegistrar.setBackground(Color.GRAY);
        btnRegistrar.setFont(new Font("Arial", Font.BOLD, 14));
        btnRegistrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                registrarUsuario();
            }
        });
        
        btnCancelar = new JButton("Cancelar");
        btnCancelar.setForeground(Color.BLACK);
        btnCancelar.setBackground(Color.GRAY);
        btnCancelar.setFont(new Font("Arial", Font.BOLD, 14));
        btnCancelar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cancelarRegistro();
            }
        });

        setLayout(new GridBagLayout());
        GridBagConstraints gbc1 = new GridBagConstraints();
        gbc1.insets = new Insets(15, 15, 15, 15);
        gbc1.anchor = GridBagConstraints.CENTER;

       
        JLabel titleLabel = new JLabel("REGISTRO");
        titleLabel.setFont(new Font("Comic Sans MS", Font.ITALIC, 36));
        titleLabel.setForeground(Color.BLACK);
        gbc1.gridx = 0;
        gbc1.gridy = 0;
        gbc1.gridwidth = 2;
        add(titleLabel, gbc1);

        gbc1.gridwidth = 1;
        gbc1.gridy = 1;
        add(new JLabel("Nombre de Usuario:"), gbc1);
        gbc1.gridx = 1;
        add(txtNombreUsuario, gbc1);

        gbc1.gridx = 0;
        gbc1.gridy = 2;
        add(new JLabel("Nombre:"), gbc1);
        gbc1.gridx = 1;
        add(txtNombre, gbc1);

        gbc1.gridx = 0;
        gbc1.gridy = 3;
        add(new JLabel("Apellido:"), gbc1);
        gbc1.gridx = 1;
        add(txtApellido, gbc1);

        gbc1.gridx = 0;
        gbc1.gridy = 4;
        add(new JLabel("Teléfono:"), gbc1);
        gbc1.gridx = 1;
        add(txtTelefono, gbc1);

        gbc1.gridx = 0;
        gbc1.gridy = 5;
        add(new JLabel("Email:"), gbc1);
        gbc1.gridx = 1;
        add(txtEmail, gbc1);

        gbc1.gridx = 0;
        gbc1.gridy = 6;
        add(new JLabel("Contraseña:"), gbc1);
        gbc1.gridx = 1;
        add(txtContraseña, gbc1);

        gbc1.gridx = 0;
        gbc1.gridy = 7;
        add(new JLabel("Confirmar Contraseña:"), gbc1);
        gbc1.gridx = 1;
        add(txtConfirmarContraseña, gbc1);

        gbc1.gridx = 0;
        gbc1.gridy = 8;
        gbc1.gridwidth = 2;
        add(btnRegistrar, gbc1);

        gbc1.gridy = 9;
        gbc1.gridwidth = 2;
        add(btnCancelar, gbc1);

        // Redondear los bordes
        ((JPanel) getContentPane()).setBorder(BorderFactory.createLineBorder(Color.BLACK, 1, true));
    }

    public Registro(ListaUsuario usuarioListaVista) {
        // TODO Auto-generated constructor stub
    }

    private void registrarUsuario() {
        String nombreUsuario = txtNombreUsuario.getText();
        String nombre = txtNombre.getText();
        String apellido = txtApellido.getText();
        String telefono = txtTelefono.getText();
        String email = txtEmail.getText();
        String contraseña = new String(txtContraseña.getPassword());
        String confirmarContraseña = new String(txtConfirmarContraseña.getPassword());

        if (nombreUsuario.isEmpty() || nombre.isEmpty() || apellido.isEmpty() || telefono.isEmpty() || email.isEmpty() || contraseña.isEmpty() || confirmarContraseña.isEmpty()) {
            String campoFaltante = "";
            if (nombreUsuario.isEmpty()) campoFaltante = "Nombre de Usuario";
            else if (nombre.isEmpty()) campoFaltante = "Nombre";
            else if (apellido.isEmpty()) campoFaltante = "Apellido";
            else if (telefono.isEmpty()) campoFaltante = "Teléfono";
            else if (email.isEmpty()) campoFaltante = "Email";
            else if (contraseña.isEmpty()) campoFaltante = "Contraseña";
            else if (confirmarContraseña.isEmpty()) campoFaltante = "Confirmar Contraseña";

            JOptionPane.showMessageDialog(this, "El campo " + campoFaltante + " es obligatorio");
            return;
        }

        if (!contraseña.equals(confirmarContraseña)) {
            JOptionPane.showMessageDialog(this, "Las contraseñas no coinciden");
            return;
        }

        Usuario usuario = new Usuario(0, nombreUsuario, nombre, apellido, telefono, email, contraseña);
        UsuarioDAO usuarioDAO = new UsuarioDAO();
        if (usuarioDAO.registrarUsuario(usuario)) {
            JOptionPane.showMessageDialog(this, "Usuario registrado con éxito");
            new Login().setVisible(true);
            this.dispose();
        } else {
            JOptionPane.showMessageDialog(this, "Error al registrar el usuario");
        }
    }

    private void cancelarRegistro() {
        new Login().setVisible(true);
        this.dispose();
    }
}
