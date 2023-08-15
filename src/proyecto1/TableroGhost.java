/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto1;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Random;
/**
 *
 * @author Azalia
 */
public class TableroGhost extends JPanel{
    //Atributos
    Usuarios1 usuario;
    Player datos;
    Login1 login;
    Menu1 menu;
    Juego juego;
    ArrayList<Personajes1> ArregloPersonajesPrimerTurno = Personajes1.getPersonajesPrimerTurno("",true);
    ArrayList<Personajes1> ArregloPersonajesSegundoTurno= Personajes1.getPersonajesSegundoTurno("",true);
    private ArrayList<Personajes1> ArregloPrimerTurnoEliminados = new ArrayList<Personajes1>();
    private ArrayList<Personajes1> ArregloSegundoTurnoEliminados = new ArrayList<Personajes1>(); 
    private JTextArea txtAreaEliminados;
    private JLabel Turnos;
    private boolean SeguirJugando=true;
    private boolean SeSeleccionoCasilla=false;
    private ControladorCasillas casillaSeleccionada;
    //Arreglo bidimensional uso para controlar casillas
    private ControladorCasillas [][] fichas;
    private boolean turnoPrimerJugador = true;
    private boolean HayGanador=false;
    //Coordenadas para colocacion
    private ArrayList<int[]> coordenadasJugador1=new ArrayList<>();
    private ArrayList<int[]> coordenadasJugador2=new ArrayList<>();
    public Image tablero;
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        // Dibujar la imagen de fondo
        g.drawImage(tablero, 0, 0, getWidth(), getHeight(), this);
    }
    
    public TableroGhost(Player datos, Login1 login, JTextArea txtAreaEliminados,JLabel Turnos, Juego juego) {
        String dificultad=login.dificultad;
         boolean aleatorio=login.aleatorio;
         //Imagen fondo tablero  
        ImageIcon fondo = new ImageIcon("src/imagenes/Tablero.png");
        tablero = fondo.getImage();
        this.datos = datos;
        this.login = login;
        this.juego = juego;
        this.txtAreaEliminados = txtAreaEliminados;
        this.Turnos = Turnos;
        //Definir un grid de 6x6 para todas las fichas
        setLayout(new GridLayout(6, 6));
        //Dificultad utilizando switch
        switch (dificultad) {
        case "normal":
            ArregloPersonajesPrimerTurno= Personajes1.getPersonajesPrimerTurno(dificultad,aleatorio);
            ArregloPersonajesSegundoTurno= Personajes1.getPersonajesSegundoTurno(dificultad,aleatorio);
            break;
        case "expert":
            ArregloPersonajesPrimerTurno= Personajes1.getPersonajesPrimerTurno(dificultad,aleatorio);
            ArregloPersonajesSegundoTurno= Personajes1.getPersonajesSegundoTurno(dificultad,aleatorio);
            break;
        case "genius":
            ArregloPersonajesPrimerTurno= Personajes1.getPersonajesPrimerTurno(dificultad,aleatorio);
            ArregloPersonajesSegundoTurno= Personajes1.getPersonajesSegundoTurno(dificultad,aleatorio);
            break;
           }
       // se generan los Jlabels para las fichas en el Grid de 6x6
        fichas = new ControladorCasillas[6][6];
        for (int filas = 0; filas < 6; filas++) {
            for (int columnas = 0; columnas < 6; columnas++) {
                ControladorCasillas ficha = new ControladorCasillas(filas, columnas, null);
                fichas[filas][columnas] = ficha;
                add(fichas[filas][columnas].label);  
            }
        }
        //Evento para aceptar losel uso de mouse en el tablero
        MouseAdapter mouseAdapter = new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                JLabel label = (JLabel) e.getSource();
                int row = -1, column = -1;  //si no seleccione la casilla correcta
                // casilla ocupada por  personaje
                if (SeSeleccionoCasilla == false) { 
                    //buscan coordenadas de la casilla
                    for (int i = 0; i < 6; i++) {
                        for (int j = 0; j < 6; j++) {
                            // La casilla seleccionada es de la casilla actual
                            if (fichas[i][j].label == label) {                         
                                casillaSeleccionada = fichas[i][j];
                                //casilla tiene un fantasma y es su turno 
                                if (casillaSeleccionada.personajeActual != null && casillaSeleccionada.personajeActual.PrimerTurno== turnoPrimerJugador) {
                                    SeSeleccionoCasilla = true; 
                                    resaltarZonasProhibidas();
                                    break;
                                } else {
                                    casillaSeleccionada= null;
                                    SeSeleccionoCasilla= false;
                                    break;
                                }
                            }
                        }
                    }
                //si se selecciono una casilla y desea mover 
                } else { 
                    for (int filas = 0; filas < 6; filas++)  {
                        for (int columnas = 0; columnas < 6; columnas++) {
                            if (fichas[filas][columnas].label == label) {
                                // Si se selecciona una  ficha del mismo jugador se cambia a esa ficha 
                                if (fichas[filas][columnas].personajeActual != null) {
                                    if (fichas[filas][columnas].personajeActual.PrimerTurno== turnoPrimerJugador) {
                                        // Actualizar casillas  
                                        casillaSeleccionada = fichas[filas][columnas];
                                        resaltarZonasProhibidas();
                                        break;
                                    }
                                }
                                //si movimiento es valido se mueve a esa casilla seleccionada
                                if (ComprobarMovimientoValido(filas, columnas) == true) {
                                    moverPersonaje(filas, columnas);
                                } else{
                                    JOptionPane.showMessageDialog(null, "NO PUEDES MOVERTE A ESA CASILLA");
                                }
                            }
                            
                        }
                    } 
                }
            }
        };
        // Agregar el MouseListener a las casillas
        for (int filas = 0; filas < 6; filas++) {
            for (int columnas = 0; columnas < 6; columnas++) {
                fichas[filas][columnas].label.addMouseListener(mouseAdapter);
            }
        }
        //Posicionar las fichas, marcar las zonas de salidas del castillo y esconder los personajes del bando contrario
        Turnos.setText("Turno de: " + datos.UsuarioLogeado);
        resaltarZonasProhibidas();
        mostrarPersonajesDatos();
        posicionarTodo();
        esconderPersonajesBandoOpuesto();
        setVisible(true);
    }
    //Comprobar movimientos
    private boolean ComprobarMovimientoValido(int fila, int columna) {
        int FilaActual= casillaSeleccionada.row;
    int ColumnaActual= casillaSeleccionada.column;
    // Verificar si el movimiento es ortogonal
    boolean EsOrtogonal=(fila == FilaActual && Math.abs(columna - ColumnaActual)== 1) ||
            (columna== ColumnaActual&& Math.abs(fila- FilaActual)== 1);
    // Verificar si la casilla está ocupada por un personaje del mismo jugador
    boolean FichaEstaOcupadaMismoBando = false;
    if (fichas[fila][columna].personajeActual!= null) {
        FichaEstaOcupadaMismoBando= (fichas[fila][columna].personajeActual.PrimerTurno == casillaSeleccionada.personajeActual.PrimerTurno);
    }
   // Verificar si la casilla está en una zona prohibida (color magenta o amarillo) según el turno del jugador
    boolean EsZonaProhibida = false;
    if (turnoPrimerJugador) {
        EsZonaProhibida = fichas[fila][columna].label.getBackground().equals(Color.MAGENTA);
    } else {
        EsZonaProhibida = fichas[fila][columna].label.getBackground().equals(Color.YELLOW);
    }
    // El movimiento es válido solo si es ortogonal y no esta en una zona prohibida de salida del castillo y no está ocupado por un personaje del mismo jugador
    boolean esMovimientoValido = EsOrtogonal && !EsZonaProhibida && !FichaEstaOcupadaMismoBando;

    if (!esMovimientoValido) {
        if (casillaSeleccionada.personajeActual.PrimerTurno) {
           // JOptionPane.showMessageDialog(null, "No puedes moverte a esa casilla");
        } else {
            //JOptionPane.showMessageDialog(null, "No puedes moverte a esa casilla");
        }
    }
    return esMovimientoValido;
    }
    //se itera por todas las casillas y se revisa si es un personaje es del segundo jugador  se esconde su personaje
    private void esconderPersonajesBandoOpuesto() {
        for (int filas = 0; filas < 6; filas++) {
            for (int columnas = 0; columnas < 6; columnas++) {
                if (fichas[filas][columnas].personajeActual != null) {
                    fichas[filas][columnas].esconderCasilla(turnoPrimerJugador != fichas[filas][columnas].personajeActual.PrimerTurno);
                } 
            }
        }
    }
    //se resalta con un color diferente las zonas d salidas dl castillo
    public void resaltarZonasProhibidas() {
       // se cambia el fondo de las celdas
    for (int fila = 0; fila < 6; fila++) {
        for (int columna = 0; columna < 6; columna++) {
            fichas[fila][columna].label.setOpaque(false);
            fichas[fila][columna].label.setBackground(null);
        }
    }
    // Resaltar Salidads dell castillo 
    fichas[0][0].label.setBackground(Color.YELLOW);
    fichas[0][5].label.setBackground(Color.YELLOW);
    fichas[5][0].label.setBackground(Color.MAGENTA);
    fichas[5][5].label.setBackground(Color.MAGENTA);
    }
 //se mueven los personajes
  private void moverPersonaje(int filanueva, int columananueva) {
      //Verificar si llegaron al castillo
      verificarVictoriaDerrota(filanueva,columananueva);
    //Verificar Batallas  
    if (fichas[filanueva][columananueva].personajeActual != null) {
        // Si la casilla esta ocupada por un personaje del otro jugador, inicia la batalla
        Personajes1 atacante= casillaSeleccionada.personajeActual;
        Personajes1 defensor= fichas[filanueva][columananueva].personajeActual;
        //Personajes Trampa
        String mensajeBatalla1= atacante.toString() + " vs " + defensor.toString();
       if (atacante.nombrePersonaje.equals("Trampa")) {
            if (defensor != null && defensor.nombrePersonaje.equals("Trampa")) {
                mensajeBatalla1 += "\nEmpate ";
                // Ambos son "Trampa" se eliminan ambos
                ArregloPrimerTurnoEliminados.add(atacante);
                ArregloSegundoTurnoEliminados.add(defensor);
                fichas[filanueva][columananueva].setPersonaje(null);
                casillaSeleccionada.setPersonaje(null);
            } else {
                mensajeBatalla1 += "\nGanador: " + defensor.toString();
                ArregloPrimerTurnoEliminados.add(atacante);
                fichas[filanueva][columananueva].setPersonaje(defensor);
                casillaSeleccionada.setPersonaje(null);
            }
             JOptionPane.showMessageDialog(null, mensajeBatalla1);
        } else if (defensor != null && defensor.nombrePersonaje.equals("Trampa")) {
            mensajeBatalla1 += "\nGanador: " + atacante.toString();
            ArregloPrimerTurnoEliminados.add(defensor);
            fichas[filanueva][columananueva].setPersonaje(atacante);
            casillaSeleccionada.setPersonaje(null);
             JOptionPane.showMessageDialog(null, mensajeBatalla1);
        }else{
        if (atacante.PrimerTurno != defensor.PrimerTurno) {
            Personajes1 ganador= EmpezarBatalla(atacante, defensor);
            // Mostrar el resultado de quien elimino al fantasma
            String mensajeBatalla= atacante.toString() + " vs " + defensor.toString();
            if (ganador != null) {
                mensajeBatalla += "\nGanador: " + ganador.toString();
                //Se elimina el personaje que perdio
                if (ganador.PrimerTurno) {
                    ArregloSegundoTurnoEliminados.add(defensor);
                } else {
                    ArregloPrimerTurnoEliminados.add(defensor);
                }
                fichas[filanueva][columananueva].setPersonaje(atacante);
                casillaSeleccionada.setPersonaje(null);
            } else {
                mensajeBatalla += "\nEmpate";
            }
            JOptionPane.showMessageDialog(null, mensajeBatalla);
        } else {
            // Si la casilla esta ocupada por un personaje del mismo jugador, simplemente actualiza la casilla seleccionada
            fichas[filanueva][columananueva].setPersonaje(casillaSeleccionada.personajeActual);
            casillaSeleccionada.setPersonaje(null);
        }
        }
    } else {
        // No habi­a un personaje en esa casilla, asi­ que solo se actualiza la posicion
        Personajes1 personaje = casillaSeleccionada.personajeActual;
        casillaSeleccionada.setPersonaje(null);
        fichas[filanueva][columananueva].setPersonaje(personaje);
    }
    // Restablecer la seleccion de casilla y cambiar el turno
    casillaSeleccionada = null;
    SeSeleccionoCasilla = false;
    turnoPrimerJugador = !turnoPrimerJugador;
    esconderPersonajesBandoOpuesto();
    // Verificar si se cumplen las condiciones de gane
    verificarVictoria();
    CambiarTurno();   
}   
        // Metodo para iniciar una batalla entre dos personajes
    public Personajes1 EmpezarBatalla(Personajes1 atacante, Personajes1 defensor) {
        // Todos los personajes pueden atacar a cualquiera del otro jugador
        // Agregar al personaje defensor a la lista de eliminados segun su turno
        if (atacante != null && defensor != null && atacante.PrimerTurno != defensor.PrimerTurno) {
            if (atacante.nombrePersonaje.equals("Trampa") && defensor.nombrePersonaje.equals("Trampa")) {
                // Ambos son "trampa", se eliminan mutuamente
                ArregloPrimerTurnoEliminados.add(atacante);
                ArregloSegundoTurnoEliminados.add(defensor);
                return null;
            } else if (atacante.nombrePersonaje.equals("Trampa")) {
                ArregloSegundoTurnoEliminados.add(defensor);
                return atacante;
            } else if (defensor.nombrePersonaje.equals("Trampa")) {
                ArregloPrimerTurnoEliminados.add(atacante);
                return defensor;
            } else {
                if (defensor.PrimerTurno) {
                    ArregloPrimerTurnoEliminados.add(defensor);
                } else {
                    ArregloSegundoTurnoEliminados.add(defensor);
                }
                return atacante;
            }
        }
        return null;
    }
    // Método para cambiar de turno
        public void CambiarTurno() {
            // Verificar si se cumple el gane
        if (HayGanador==false) {
           //Turnos
            if (turnoPrimerJugador) {
            Turnos.setText("Turno de: " + datos.UsuarioLogeado);
            JOptionPane.showMessageDialog(null, "Turno de: " + datos.UsuarioLogeado);
        } else {
            Turnos.setText("Turno de: " + datos.SegundoUsuario);
            JOptionPane.showMessageDialog(null, "Turno de: " + datos.SegundoUsuario);
        }
        }
        // Esconder personajes del otro jugador
        mostrarPersonajesDatos();
        resaltarZonasProhibidas();
        setVisible(true);
    }
   //Mostrar info personajes 
    public void mostrarPersonajesDatos() {
        String mensaje = "";
        // Contar personajes buenos y malos iniciales
    long buenosPrimerTurnoIniciales = ArregloPersonajesPrimerTurno.stream()
            .filter(personaje -> personaje.PersonajeBueno && !personaje.nombrePersonaje.equals("Trampa"))
            .count();
    long malosPrimerTurnoIniciales = ArregloPersonajesPrimerTurno.stream()
            .filter(personaje -> !personaje.PersonajeBueno && !personaje.nombrePersonaje.equals("Trampa"))
            .count();
    long buenosSegundoTurnoIniciales = ArregloPersonajesSegundoTurno.stream()
            .filter(personaje -> personaje.PersonajeBueno && !personaje.nombrePersonaje.equals("Trampa"))
            .count();
    long malosSegundoTurnoIniciales = ArregloPersonajesSegundoTurno.stream()
            .filter(personaje -> !personaje.PersonajeBueno && !personaje.nombrePersonaje.equals("Trampa"))
            .count();
    // personajes buenos y malos eliminados
    long buenosPrimerTurnoEliminados = ArregloPrimerTurnoEliminados.stream()
            .filter(personaje -> personaje.PersonajeBueno && !personaje.nombrePersonaje.equals("Trampa"))
            .count();
    long malosPrimerTurnoEliminados = ArregloPrimerTurnoEliminados.stream()
            .filter(personaje -> !personaje.PersonajeBueno && !personaje.nombrePersonaje.equals("Trampa"))
            .count();

    long buenosSegundoTurnoEliminados = ArregloSegundoTurnoEliminados.stream()
            .filter(personaje -> personaje.PersonajeBueno && !personaje.nombrePersonaje.equals("Trampa"))
            .count();
    long malosSegundoTurnoEliminados = ArregloSegundoTurnoEliminados.stream()
            .filter(personaje -> !personaje.PersonajeBueno && !personaje.nombrePersonaje.equals("Trampa"))
            .count();
    //  fantasmas disponibles
    long buenosPrimerTurnoDisponibles = buenosPrimerTurnoIniciales - buenosPrimerTurnoEliminados / 2;
    long malosPrimerTurnoDisponibles = malosPrimerTurnoIniciales - malosPrimerTurnoEliminados / 2;

    long buenosSegundoTurnoDisponibles = buenosSegundoTurnoIniciales - buenosSegundoTurnoEliminados / 2;
    long malosSegundoTurnoDisponibles = malosSegundoTurnoIniciales - malosSegundoTurnoEliminados / 2;

    // Mensaje de salida
    mensaje += " Jugador " + datos.UsuarioLogeado + ":"
            + " Buenos: " + buenosPrimerTurnoDisponibles
            + ", Malos: " + malosPrimerTurnoDisponibles + "\n"
            + " Jugador " + datos.SegundoUsuario + ":"
            + " Buenos: " + buenosSegundoTurnoDisponibles
            + ", Malos: " + malosSegundoTurnoDisponibles;
    txtAreaEliminados.setText(mensaje);
    }
    //verificr si llegaron al castillo
        private void verificarVictoriaDerrota(int filanueva,int columnanueva) {
            Personajes1 personajeActual = casillaSeleccionada.personajeActual;
       boolean esFantasmaPrimerTurno = casillaSeleccionada.personajeActual.PrimerTurno;
        boolean esFantasmaBueno = casillaSeleccionada.personajeActual.PersonajeBueno;
        boolean esFantasmaMalo = !casillaSeleccionada.personajeActual.PersonajeBueno;

        boolean esCasillaVictoriaJugador1 = esFantasmaBueno && fichas[filanueva][columnanueva].label.getBackground().equals(Color.YELLOW);
        boolean esCasillaVictoriaJugador2 = esFantasmaBueno && fichas[filanueva][columnanueva].label.getBackground().equals(Color.MAGENTA);
        //verificar gane o fallo
        if (esFantasmaPrimerTurno) {
            if (esCasillaVictoriaJugador1&&!personajeActual.nombrePersonaje.equalsIgnoreCase("Trampa")) {
                // Si un fantasma bueno del jugador 1 llega a una casilla amarilla, gana el jugador 1
                HayGanador = true;
                JOptionPane.showMessageDialog(null, "¡El jugador "+datos.UsuarioLogeado+" triunfo al sacar un fantasma bueno !!");
                Usuarios1 usuarioGanador = datos.buscarUsuario(datos.UsuarioLogeado);
                   //Sumar Puntos con Polimorfismo
                    usuarioGanador.IncrementarPuntos(3);
                    //resultados Juegos 
                     String resultado="El jugador "+datos.UsuarioLogeado+" triunfo al sacar un fantasma bueno/ perdedor jugador "+datos.SegundoUsuario;
                    datos.agregarRegistroJuego(resultado);
                    //menu
                    menu = new Menu1(login, datos);
                        menu.setVisible(true);
                        juego.dispose();
            } else if (esFantasmaMalo && fichas[filanueva][columnanueva].label.getBackground().equals(Color.YELLOW)) {
                // Si un fantasma malo del jugador 1 llega a una casilla amarilla, gana el jugador 2
                HayGanador = true;
                JOptionPane.showMessageDialog(null, "¡El jugador "+datos.SegundoUsuario+" triunfo ya que un fantasma malo del jugador contrario salio del castillo ");

                Usuarios1 usuarioGanador = datos.buscarUsuario(datos.SegundoUsuario);
                 //Sumar Puntos con Polimorfismo
                    usuarioGanador.IncrementarPuntos(3);
                    //resultados Juegos 
                     String resultado="El jugador "+datos.SegundoUsuario+" triunfo ya que un fantasma malo del jugador contrario salio del castillo/ perdedor jugador "+datos.UsuarioLogeado;
                    datos.agregarRegistroJuego(resultado);
                   //menu
                  menu = new Menu1(login, datos);
                       menu.setVisible(true);
                       juego.dispose();
                   }
        } else {
            if (esCasillaVictoriaJugador2&&!personajeActual.nombrePersonaje.equalsIgnoreCase("Trampa")) {
                // Si un fantasma bueno del jugador 2 llega a una casilla rosa, gana el jugador 2
                HayGanador = true;
                JOptionPane.showMessageDialog(null, "¡El jugador "+datos.SegundoUsuario+" triunfo al sacar un fantasma bueno !!");

                Usuarios1 usuarioGanador = datos.buscarUsuario(datos.SegundoUsuario);
                     //Sumar Puntos con Polimorfismo
                       usuarioGanador.IncrementarPuntos(3);
                     //resultados Juegos 
                      String resultado="El jugador "+datos.SegundoUsuario+" triunfo al sacar un fantasma bueno/ perdedor jugador "+datos.UsuarioLogeado;
                     datos.agregarRegistroJuego(resultado);
                    //menu
                   menu = new Menu1(login, datos);
                        menu.setVisible(true);
                        juego.dispose();
            } else if (esFantasmaMalo && fichas[filanueva][columnanueva].label.getBackground().equals(Color.MAGENTA)) {
                // Si un fantasma malo del jugador 2 llega a una casilla rosa gana el jugador 1
                HayGanador = true;
                JOptionPane.showMessageDialog(null, "¡El jugador "+datos.UsuarioLogeado+" triunfo ya que un fantasma malo del jugador contrario salio del castillo");

                        Usuarios1 usuarioGanador = datos.buscarUsuario(datos.UsuarioLogeado);
                       //Sumar Puntos con Polimorfismo
                       usuarioGanador.IncrementarPuntos(3);
                    //resultados Juegos 
                      String resultado="El jugador "+datos.UsuarioLogeado+" triunfo ya que un fantasma malo del jugador contrario salio del castillo/ perdedor jugador "+datos.SegundoUsuario;
                     datos.agregarRegistroJuego(resultado);
                    //menu
                   menu = new Menu1(login, datos);
                        menu.setVisible(true);
                        juego.dispose();
                    }
        }
    }
    // Método para verificar si hay un ganador
    private void verificarVictoria() {
       boolean todosLosFantasmasBuenosPrimerTurnoEliminados = ArregloPersonajesPrimerTurno.stream()
               .filter(personaje -> personaje.PersonajeBueno && !personaje.nombrePersonaje.equalsIgnoreCase("Trampa"))
               .allMatch(personaje -> ArregloPrimerTurnoEliminados.contains(personaje));

       boolean todosLosFantasmasMalosPrimerTurnoEliminados = ArregloPersonajesPrimerTurno.stream()
               .filter(personaje -> !personaje.PersonajeBueno && !personaje.nombrePersonaje.equalsIgnoreCase("Trampa"))
               .allMatch(personaje -> ArregloPrimerTurnoEliminados.contains(personaje));

       boolean todosLosFantasmasBuenosSegundoTurnoEliminados = ArregloPersonajesSegundoTurno.stream()
               .filter(personaje -> personaje.PersonajeBueno && !personaje.nombrePersonaje.equalsIgnoreCase("Trampa"))
               .allMatch(personaje -> ArregloSegundoTurnoEliminados.contains(personaje));

       boolean todosLosFantasmasMalosSegundoTurnoEliminados = ArregloPersonajesSegundoTurno.stream()
               .filter(personaje -> !personaje.PersonajeBueno && !personaje.nombrePersonaje.equalsIgnoreCase("Trampa"))
               .allMatch(personaje -> ArregloSegundoTurnoEliminados.contains(personaje));

       if (todosLosFantasmasBuenosPrimerTurnoEliminados || todosLosFantasmasMalosPrimerTurnoEliminados
               || todosLosFantasmasBuenosSegundoTurnoEliminados || todosLosFantasmasMalosSegundoTurnoEliminados) {
           SeguirJugando = false;
           HayGanador = true;
           // Determinara ganador si capturo todos fantasmas malos o buenos
           if (todosLosFantasmasBuenosPrimerTurnoEliminados ) {
               JOptionPane.showMessageDialog(null, "¡El jugador "+datos.SegundoUsuario+" triunfo sobre "+datos.UsuarioLogeado+" por que capturo todos sus fantasmas sus fantasmas Buenos");

               Usuarios1 usuarioGanador = datos.buscarUsuario(datos.SegundoUsuario);
                    //Sumar Puntos con Polimorfismo
                   usuarioGanador.IncrementarPuntos(3);
                   //resultados Juegos
                   String resultado="El jugador "+datos.SegundoUsuario+" triunfo sobre "+datos.UsuarioLogeado+" por que capturo todos sus fantasmas Buenos";
                   datos.agregarRegistroJuego(resultado);
                  //Menu 
                      menu = new Menu1(login, datos);
                      menu.setVisible(true);
                      juego.dispose();
           }else if(todosLosFantasmasMalosPrimerTurnoEliminados){
               JOptionPane.showMessageDialog(null, "¡El jugador "+datos.SegundoUsuario+" triunfo sobre "+datos.UsuarioLogeado+" por que capturo todos sus fantasmas sus fantasmas Malos");

               Usuarios1 usuarioGanador = datos.buscarUsuario(datos.SegundoUsuario);
                   //Sumar Puntos con Polimorfismo
                   usuarioGanador.IncrementarPuntos(3);
                   //resultados Juegos 
                   String resultado="El jugador "+datos.SegundoUsuario+" triunfo sobre "+datos.UsuarioLogeado+" por que capturo todos sus fantasmas Malos";
                    datos.agregarRegistroJuego(resultado);
                   //Menu 
                       menu = new Menu1(login, datos);
                       menu.setVisible(true);
                       juego.dispose(); 
           }
           else if(todosLosFantasmasMalosSegundoTurnoEliminados){
               JOptionPane.showMessageDialog(null, "¡¡El jugador "+datos.UsuarioLogeado+" triunfo sobre "+datos.SegundoUsuario+" por que capturo todos sus fantasmas sus fantasmas Malos");

               Usuarios1 usuarioGanador = datos.buscarUsuario(datos.UsuarioLogeado);
                  //Sumar Puntos con Polimorfismo
                   usuarioGanador.IncrementarPuntos(3);
                   //resultados Juegos
                    String resultado="El jugador "+datos.UsuarioLogeado+" triunfo sobre "+datos.SegundoUsuario+" por que capturo todos sus fantasmas Malos";
                   datos.agregarRegistroJuego(resultado);
                  //menu 
                      menu = new Menu1(login, datos);
                      menu.setVisible(true);
                      juego.dispose();
           }else if(todosLosFantasmasBuenosSegundoTurnoEliminados){
               JOptionPane.showMessageDialog(null, "¡¡El jugador "+datos.UsuarioLogeado+" triunfo sobre "+datos.SegundoUsuario+" por que capturo todos sus fantasmas sus fantasmas Buenos");

               Usuarios1 usuarioGanador = datos.buscarUsuario(datos.UsuarioLogeado);
                 //Sumar Puntos con Polimorfismo
                   usuarioGanador.IncrementarPuntos(3);
                   //resultados Juegos
                    String resultado="El jugador "+datos.UsuarioLogeado+" triunfo sobre "+datos.SegundoUsuario+" por que capturo todos sus fantasmas Buenos";
                   datos.agregarRegistroJuego(resultado);
                  //menu 
                      menu = new Menu1(login, datos);
                      menu.setVisible(true);
                      juego.dispose();
           }
       }   
   }
    // Método para determinar si un personaje se mueve a una casilla de victoria o derrota
    private boolean esCasillaDeVictoriaDerrota(Personajes1 personaje, int fila, int columna) {
        boolean esCasillaAmarilla = fichas[fila][columna].label.getBackground().equals(Color.YELLOW);
        boolean esCasillaMagenta = fichas[fila][columna].label.getBackground().equals(Color.MAGENTA);
        return (personaje.PersonajeBueno && esCasillaAmarilla) || (!personaje.PersonajeBueno && esCasillaMagenta);
    }
    //Posicionar Personajes Aleatorio
    public void posicionarPersonajes() {
        // Generar una ficha aleatoria y una columna aleatoria
    repaint();
    boolean placed= false;
    Random random= new Random();
    // Primer Turno 
    for (int i = 0; i < ArregloPersonajesPrimerTurno.size(); i++) {
        Personajes1 personajeActual = ArregloPersonajesPrimerTurno.get(i);
        int filaAleatoria;
        int columnaAleatoria;
        do {
            filaAleatoria = random.nextInt(2) + 4; //  fila 4 y 5
            columnaAleatoria = random.nextInt(4) + 1; //columna  1 y 4
        } while (fichas[filaAleatoria][columnaAleatoria].personajeActual != null);
        
        fichas[filaAleatoria][columnaAleatoria].setPersonaje(personajeActual);
        personajeActual.posicionado = true;
    } 
    // Segundo Turno
    for (int i = 0; i < ArregloPersonajesSegundoTurno.size(); i++) {
        Personajes1 personajeActual = ArregloPersonajesSegundoTurno.get(i);
        
        int filaAleatoria;
        int columnaAleatoria;
        
        do {
            filaAleatoria = random.nextInt(2); //  fila 0 y 1
            columnaAleatoria = random.nextInt(4) + 1; //  columna  1 y 4
        } while (fichas[filaAleatoria][columnaAleatoria].personajeActual != null);
        
        fichas[filaAleatoria][columnaAleatoria].setPersonaje(personajeActual);
        personajeActual.posicionado = true;
    } 
    }
    ////Modo Manual
private void colocarPersonajeManual(Personajes1 personaje, boolean primerTurno) {
        int minFila, maxFila;
        // Dependiendo del turno del jugador
        if (primerTurno) {
            minFila = 4;
            maxFila = 5;
        } else {
            minFila = 0;
            maxFila = 1;
        }
        // Mostrar el mensaje del turno usuando operador Ternario
    String mensajeTurno = "Turno de " + (primerTurno ? datos.UsuarioLogeado+" coloque entre filas(4-5) y columnas (1-4)" : datos.SegundoUsuario+" coloque entre filas(0-1) y columnas (1-4)");
    JOptionPane.showMessageDialog(null, mensajeTurno);

        while (true) {
            String mensajeColocacion;
        try {
            mensajeColocacion= JOptionPane.showInputDialog(null, "Colocar " + personaje.nombrePersonaje +
                    " (Bando: " + (personaje.PersonajeBueno ? "Bueno" : "Malo") + ") en el tablero ghost " +
                    "Ingrese fila y columna separadas por un espacio (De esta forma: 1 2): ");
        } catch (HeadlessException e) {
            // El usuario cerro la ventana
            menu = new Menu1(login, datos);
            menu.setVisible(true);
            juego.dispose();
            break;
        }
        //si boton cancel
        if (mensajeColocacion == null) {
            // El usuario cancelo el ingreso, volver al menu 
            menu = new Menu1(login, datos);
            menu.setVisible(true);
            juego.dispose();
            break;
        }

            String[] coordenadas = mensajeColocacion.trim().split(" ");
            if (coordenadas.length != 2) {
                JOptionPane.showMessageDialog(null, "Dato ingresado Incorrecto. Ingrese dos números separados por espacio");
                continue;
            }

            try {
                int fila = Integer.parseInt(coordenadas[0]);
                int columna = Integer.parseInt(coordenadas[1]);

                if (fila < minFila || fila > maxFila || columna < 1 || columna > 4) {
                    JOptionPane.showMessageDialog(null, "Coordenadas incorrectas/Debe ser una fila entre " +
                            minFila + " y " + maxFila + " y una columna entre 1 y 4.");
                    continue;
                }

                if (fichas[fila][columna].personajeActual != null) {
                    JOptionPane.showMessageDialog(null, "La casilla esta ocupada por otro fantasma. Intente de nuevo");
                    continue;
                }

                fichas[fila][columna].setPersonaje(personaje);
                personaje.posicionado = true;
                personaje.PrimerTurno = primerTurno;
                // Guardamos las coordenadas ingresadas por el usuario
                if (primerTurno) {
                    coordenadasJugador1.add(new int[]{fila, columna});
                } else {
                    coordenadasJugador2.add(new int[]{fila, columna});
                }
                break;
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Entrada inválida. Ingrese números válidos.");
            }
        }
    }
///Posicionar todo en el tablero
    public void posicionarTodo() {
        if (login.aleatorio) {
            posicionarPersonajes();
        } else {
            colocarPersonajesManualmente();
        }
    }
//colocar personajes de forma manual
    private void colocarPersonajesManualmente() {
        JOptionPane.showMessageDialog(null, "Modo de juego manual: Coloque los fantasmas en el tablero.");
        //Primer turno
        for (int i = 0; i < ArregloPersonajesPrimerTurno.size(); i++) {
            Personajes1 personaje = ArregloPersonajesPrimerTurno.get(i);
            colocarPersonajeManual(personaje, true);
        }
        //Segundo turno
        for (int i = 0; i < ArregloPersonajesSegundoTurno.size(); i++) {
            Personajes1 personaje = ArregloPersonajesSegundoTurno.get(i);
            colocarPersonajeManual(personaje, false);
        }
        //Se completo colocacion de fantasmas
        JOptionPane.showMessageDialog(null, "Colocación de fantasmas completada.");
    }
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new TableroGhost(null, null,null, null, null);
                
            }
        });
    }
}
