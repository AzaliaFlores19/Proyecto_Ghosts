/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto1;

import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author Azalia
 */
public final class Player extends Usuarios1{
    private ArrayList<Usuarios1>ListaUsuarios;
    private ArrayList<Usuarios1>ListaUsuariosEliminados;
    private ArrayList<String> resultadosJuegos=new ArrayList();
    
    public String UsuarioLogeado;
    public String SegundoUsuario; 
    public Player(String username,String password){
        super(username,password);
        ListaUsuarios=new ArrayList<>(); 
        ListaUsuariosEliminados=new ArrayList<>();
    }
    //Buscar Usuarios si existentes usando recursividad
   private Usuarios1 buscarUsuarioRec(String username, int indice) {
        if (indice>= 0&& indice<ListaUsuarios.size()) {
            Usuarios1 usuario = ListaUsuarios.get(indice);
            if (usuario!= null) {
                if (usuario.getUsername().equals(username)) {
                    return usuario;
                } else {
                    return buscarUsuarioRec(username, indice + 1);
                }
            }
        }
        return null;
    }

 //Buscar usuarios para ser llamado
    public Usuarios1 buscarUsuario(String username) {
        return buscarUsuarioRec(username, 0);
    }

    //agregar Usuarios usando Recursividad
        public boolean agregarUsuarioRec(Usuarios1 usuario, int indice) {
        if (indice >= 0 && indice< ListaUsuarios.size()) {
            Usuarios1 aux = ListaUsuarios.get(indice);
            if (aux != null) {
                if (aux.getUsername().equals(usuario.getUsername())) {

                    return false;
                } else {

                    return agregarUsuarioRec(usuario, indice + 1);
                }
            }
        }
        ListaUsuarios.add(usuario);
        return true;
    }


    public boolean agregarUsuario(Usuarios1 usuario) {
        return agregarUsuarioRec(usuario, 0);
    }

    
   //eliminar usuario usando recursividad 
    public void eliminarUsuario(Usuarios1 usuario) {
    eliminarUsuarioRec(usuario, 0);
    }

    private void eliminarUsuarioRec(Usuarios1 usuario, int indice) {
        if (indice < ListaUsuarios.size()) {
            Usuarios1 usuarioLista = ListaUsuarios.get(indice);
            if (usuarioLista != null && usuarioLista.equals(usuario)) {
                ListaUsuarios.remove(indice);
                return;
            } else {
                eliminarUsuarioRec(usuario, indice + 1);
            }
        }
    }
    //Usar for each para obtener lista de usuarios
    public ArrayList<Usuarios1> getListaUsuarios() {
        ArrayList<Usuarios1> listaUsuarios = new ArrayList<>();
        for (Usuarios1 usuario : ListaUsuarios) {
            listaUsuarios.add(usuario);
        }
        return listaUsuarios;
    }

    //Agregar resultados de juegos 
   public void agregarRegistroJuego(String registro) {
    resultadosJuegos.add(registro);  // Agregar a lista
    //  10 registros
    if (resultadosJuegos.size() > 10) {
        resultadosJuegos.remove(0);
    }
}

    // Registros de Usuario Logueado
   public ArrayList<String> RegistrosJuegos(String usuarioLogeado) {
    ArrayList<String> descripcionJuegos= new ArrayList<>();
    for (int i= resultadosJuegos.size()- 1; i>= 0; i--) {
        String registro = resultadosJuegos.get(i);
        if (registro.contains(usuarioLogeado)) {
            descripcionJuegos.add(registro);
        }
    }
    return descripcionJuegos;
}
    //metodo para obtener puntos herencia
    @Override
    public void IncrementarPuntos(int cantidad) {
        int puntosActuales = getPuntos();
        super.puntos = puntosActuales + (cantidad - (puntosActuales % cantidad));
    }
    
}
