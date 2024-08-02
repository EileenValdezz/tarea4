package Formularios; 

import Clases.Usuario;
import ManejoUsuario.UsuarioDAO;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.net.URL;

public class ListaUsuario extends JFrame {
    private static final long serialVersionUID = 1L;
    private JTable tablaUsuarios;
    private JButton btnActualizar, btnEliminar, btnNuevo, btnCerrarSesion;
    private UsuarioDAO usuarioDAO;
    private JPanel panelBotones;
    private JScrollPane scrollPane;

    public ListaUsuario(UsuarioDAO usuarioDAO) {
        this.usuarioDAO = usuarioDAO;

        setTitle("Lista de Usuarios");
        setSize(600, 600); 
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        getContentPane().setBackground(new Color(255, 182, 193)); 
        setResizable(false); // Evitar maximizar la ventana
        
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

        cargarDatos();

        btnCerrarSesion = new JButton("Cerrar Sesión");
        btnCerrarSesion.setForeground(Color.BLACK);
        btnCerrarSesion.setBackground(Color.GRAY);
        btnCerrarSesion.setFont(new Font("Arial", Font.BOLD, 14));
        btnCerrarSesion.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cerrarSesion();
            }
        });

        btnActualizar = new JButton("Actualizar");
        btnActualizar.setForeground(Color.BLACK);
        btnActualizar.setBackground(Color.GRAY);
        btnActualizar.setFont(new Font("Arial", Font.BOLD, 14));
        btnActualizar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                actualizarUsuario();
            }
        });

        btnEliminar = new JButton("Eliminar");
        btnEliminar.setForeground(Color.BLACK);
        btnEliminar.setBackground(Color.GRAY);
        btnEliminar.setFont(new Font("Arial", Font.BOLD, 14));
        btnEliminar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                eliminarUsuario();
            }
        });

        btnNuevo = new JButton("Nuevo");
        btnNuevo.setForeground(Color.BLACK);
        btnNuevo.setBackground(Color.GRAY);
        btnNuevo.setFont(new Font("Arial", Font.BOLD, 14));
        btnNuevo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                nuevoUsuario();
            }
        });

        panelBotones = new JPanel();
        panelBotones.setBackground(new Color(255, 182, 193)); 
        panelBotones.add(btnNuevo);
        panelBotones.add(btnActualizar);
        panelBotones.add(btnEliminar);
        panelBotones.add(btnCerrarSesion);

        add(panelBotones, BorderLayout.SOUTH);

        JLabel titleLabel = new JLabel("LISTA DE USUARIOS", JLabel.CENTER);
        titleLabel.setFont(new Font("Comic Sans MS", Font.ITALIC, 48));
        titleLabel.setForeground(Color.BLACK);
        add(titleLabel, BorderLayout.NORTH);
    }

    void cargarDatos() {
        List<Usuario> usuarios = usuarioDAO.obtenerTodosLosUsuarios();
        String[] columnas = {"ID", "Nombre de Usuario", "Nombre", "Apellido", "Teléfono", "Email"};
        String[][] datos = new String[usuarios.size()][6];

        for (int i = 0; i < usuarios.size(); i++) {
            Usuario usuario = usuarios.get(i);
            datos[i][0] = String.valueOf(usuario.getId());
            datos[i][1] = usuario.getNombreUsuario();
            datos[i][2] = usuario.getNombre();
            datos[i][3] = usuario.getApellido();
            datos[i][4] = usuario.getTelefono();
            datos[i][5] = usuario.getEmail();
        }

        if (scrollPane != null) {
            remove(scrollPane);
        }
        tablaUsuarios = new JTable(datos, columnas);
        scrollPane = new JScrollPane(tablaUsuarios);
        add(scrollPane, BorderLayout.CENTER);
        revalidate();
        repaint();
    }

    private void cerrarSesion() {
        new Login().setVisible(true);
        this.dispose();
    }

    private void actualizarUsuario() {
        int filaSeleccionada = tablaUsuarios.getSelectedRow();
        if (filaSeleccionada == -1) {
            JOptionPane.showMessageDialog(this, "Seleccione un usuario para actualizar");
            return;
        }

        int id = Integer.parseInt(tablaUsuarios.getValueAt(filaSeleccionada, 0).toString());
        String nombreUsuario = tablaUsuarios.getValueAt(filaSeleccionada, 1).toString();
        String nombre = tablaUsuarios.getValueAt(filaSeleccionada, 2).toString();
        String apellido = tablaUsuarios.getValueAt(filaSeleccionada, 3).toString();
        String telefono = tablaUsuarios.getValueAt(filaSeleccionada, 4).toString();
        String email = tablaUsuarios.getValueAt(filaSeleccionada, 5).toString();

        JPanel panel = new JPanel(new GridLayout(7, 2));
        JTextField txtNombreUsuario = new JTextField(nombreUsuario);
        JTextField txtNombre = new JTextField(nombre);
        JTextField txtApellido = new JTextField(apellido);
        JTextField txtTelefono = new JTextField(telefono);
        JTextField txtEmail = new JTextField(email);
        JPasswordField txtContraseña = new JPasswordField();
        JPasswordField txtConfirmarContraseña = new JPasswordField();

        panel.add(new JLabel("Nombre de Usuario:"));
        panel.add(txtNombreUsuario);
        panel.add(new JLabel("Nombre:"));
        panel.add(txtNombre);
        panel.add(new JLabel("Apellido:"));
        panel.add(txtApellido);
        panel.add(new JLabel("Teléfono:"));
        panel.add(txtTelefono);
        panel.add(new JLabel("Email:"));
        panel.add(txtEmail);
        panel.add(new JLabel("Nueva Contraseña:"));
        panel.add(txtContraseña);
        panel.add(new JLabel("Confirmar Contraseña:"));
        panel.add(txtConfirmarContraseña);

        int option = JOptionPane.showConfirmDialog(this, panel, "Actualizar Usuario", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

        if (option == JOptionPane.OK_OPTION) {
            String nuevaContraseña = new String(txtContraseña.getPassword());
            String confirmarContraseña = new String(txtConfirmarContraseña.getPassword());

            if (!nuevaContraseña.equals(confirmarContraseña)) {
                JOptionPane.showMessageDialog(this, "Las contraseñas no coinciden");
                return;
            }

            Usuario usuario = new Usuario(id, txtNombreUsuario.getText(), txtNombre.getText(), txtApellido.getText(), txtTelefono.getText(), txtEmail.getText(), nuevaContraseña);
            if (usuarioDAO.actualizarUsuario(usuario)) {
                JOptionPane.showMessageDialog(this, "Usuario actualizado con éxito");
                cargarDatos();
            } else {
                JOptionPane.showMessageDialog(this, "Error al actualizar el usuario");
            }
        }
    }

    private void eliminarUsuario() {
        int filaSeleccionada = tablaUsuarios.getSelectedRow();
        if (filaSeleccionada == -1) {
            JOptionPane.showMessageDialog(this, "Seleccione un usuario para eliminar");
            return;
        }

        int id = Integer.parseInt(tablaUsuarios.getValueAt(filaSeleccionada, 0).toString());

        int confirmacion = JOptionPane.showConfirmDialog(this, "¿Está seguro de que desea eliminar este usuario?", "Confirmación", JOptionPane.YES_NO_OPTION);
        if (confirmacion == JOptionPane.YES_OPTION) {
            if (usuarioDAO.eliminarUsuario(id)) {
                JOptionPane.showMessageDialog(this, "Usuario eliminado con éxito");
                cargarDatos();
            } else {
                JOptionPane.showMessageDialog(this, "Error al eliminar el usuario");
            }
        }
    }

    private void nuevoUsuario() {
        Registro registroVista = new Registro();
        registroVista.setVisible(true);
        registroVista.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosed(java.awt.event.WindowEvent windowEvent) {
                cargarDatos(); // Actualizar datos cuando se cierra la ventana de registro
            }
        });
    }

    public static void main(String[] args) {
        new ListaUsuario(new UsuarioDAO()).setVisible(true);
    }
}
