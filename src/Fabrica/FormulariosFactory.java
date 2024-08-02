package Fabrica; 

import Formularios.ListaUsuario;
import Formularios.Login;
import Formularios.Registro;
import ManejoUsuario.UsuarioDAO;

public class FormulariosFactory {

    public static Object crearVista(String tipoVista) {
        switch (tipoVista) {
            case "Login":
                return new Login();
            case "Registro":
                return new Registro();
            case "UsuarioLista":
                return new ListaUsuario(new UsuarioDAO());
            default:
                throw new IllegalArgumentException("Tipo de vista desconocido: " + tipoVista);
        }
    }
}
