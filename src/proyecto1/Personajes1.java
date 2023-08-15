/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto1;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.util.ArrayList;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.Random;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
/**
 *
 * @author Azalia
 */
public class Personajes1 {
    private int currentRow;
    private int currentColumn;
    String nombrePersonaje;
    boolean PrimerTurno;
    boolean PersonajeBueno;
    boolean posicionado = false;
    ImageIcon icono;
    ImageIcon iconoEscondido;
    Login1 login;
    boolean aleatorio;
    public Personajes1(String nombrePersonaje,boolean PersonajeBueno,boolean PrimerTurno,String path){
        this.nombrePersonaje=nombrePersonaje;
        this.PersonajeBueno=PersonajeBueno;
        this.PrimerTurno=PrimerTurno;
        
        try {
           Image resizedImg = resizeImage(ImageIO.read(new File("src/imagenes/Oculto.png")), 131, 100);
           iconoEscondido = new ImageIcon(resizedImg);
        } catch (Exception e) {
            iconoEscondido = null;
        }
        
        try {
            Image newImg = resizeImage(ImageIO.read(new File(path)), 100, 40);
            icono = new ImageIcon(newImg);
        } catch (Exception e) {
            icono = null;
        }
        loadIcon();
    }
    
     private void loadIcon() {
        String filename;
        
        if (!PrimerTurno){
            filename = "src/imagenes/" + nombrePersonaje.replace(" ", "") + ".png";
        }
        else {
            filename = "src/imagenes/" + nombrePersonaje.replace(" ", "") + ".png";
        }
            
        try {
            
            Image newImg = resizeImage(ImageIO.read(new File(filename)), 131, 100);
            icono = new ImageIcon(newImg);
        } catch (Exception e) {
            icono = null;
        }
    }
    
    private Image resizeImage(Image img, int w, int h) {
        BufferedImage resizedImg = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2 = resizedImg.createGraphics();
        
        
        g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BICUBIC);
        g2.drawImage(img, 0, 0, w, h, null);
        g2.dispose();

        return resizedImg;
    }
    ////Personajes por dificultad y modo
     public static ArrayList<Personajes1> getPersonajesPrimerTurno(String difficulty,boolean aleatorio) {
        ArrayList<Personajes1> personajes = new ArrayList<Personajes1>();

        if (difficulty.equalsIgnoreCase("normal")) {
            personajes.addAll(getFantasmasNormalesPrimerTurno(aleatorio));
        } else if (difficulty.equalsIgnoreCase("expert")) {
            personajes.addAll(getFantasmasExpertPrimerTurno(aleatorio));
        } else if (difficulty.equalsIgnoreCase("genius")) {
            personajes.addAll(getFantasmasGeniusPrimerTurno(aleatorio));
        } else {
            // Al inicio es aleatorio
            personajes.addAll(getFantasmasNormalesPrimerTurno(aleatorio));
        }

        return personajes;
    }

    public static ArrayList<Personajes1> getPersonajesSegundoTurno(String difficulty,boolean aleatorio) {
        ArrayList<Personajes1> personajes = new ArrayList<Personajes1>();

        if (difficulty.equalsIgnoreCase("normal")) {
            personajes.addAll(getFantasmasNormalesSegundoTurno(aleatorio));
        } else if (difficulty.equalsIgnoreCase("expert")) {
            personajes.addAll(getFantasmasExpertSegundoTurno(aleatorio));
        } else if (difficulty.equalsIgnoreCase("genius")) {
            personajes.addAll(getFantasmasGeniusSegundoTurno(aleatorio));
        } else {
            // Default to normal difficulty if no valid mode is specified
            personajes.addAll(getFantasmasNormalesSegundoTurno(aleatorio));
        }

        return personajes;
    }
    //Personajes dificultad normal por modo Primer turno
    public static ArrayList<Personajes1> getFantasmasNormalesPrimerTurno(boolean modoAleatorio) {
        ArrayList<Personajes1> personajes = new ArrayList<Personajes1>();

        if (modoAleatorio) {
            personajes.addAll(getFantasmasNormalesPrimerTurnoAleatorio());
        } else {
            personajes.addAll(getFantasmasNormalesPrimerTurnoManual());
        }
        return personajes;
    }
    
     //Personajes dificultad expert por modo primer turno
    public static ArrayList<Personajes1> getFantasmasExpertPrimerTurno(boolean modoAleatorio) {
        ArrayList<Personajes1> personajes = new ArrayList<Personajes1>();

        if (modoAleatorio) {
            personajes.addAll(getFantasmasExpertosPrimerTurnoAleatorio());
        } else {
            personajes.addAll(getFantasmasExpertosPrimerTurnoManual());
        }
        return personajes;
    }
    
      //Personajes dificultad genius por modo primer turno
    public static ArrayList<Personajes1> getFantasmasGeniusPrimerTurno(boolean modoAleatorio) {
        ArrayList<Personajes1> personajes = new ArrayList<Personajes1>();

        if (modoAleatorio) {
            personajes.addAll(getFantasmasGeniusPrimerTurnoAleatorio());
        } else {
            personajes.addAll(getFantasmasGeniusPrimerTurnoManual());
        }
        return personajes;
    }
    
     //Personajes dificultad normal por modo segundo turno
    public static ArrayList<Personajes1> getFantasmasNormalesSegundoTurno(boolean modoAleatorio) {
        ArrayList<Personajes1> personajes = new ArrayList<Personajes1>();

        if (modoAleatorio) {
            personajes.addAll(getFantasmasNormalesSegundoTurnoAleatorio());
        } else {
            personajes.addAll(getFantasmasNormalesSegundoTurnoManual());
        }
        return personajes;
    }
     //Personajes dificultad expert por modo segundo turno
    public static ArrayList<Personajes1> getFantasmasExpertSegundoTurno(boolean modoAleatorio) {
        ArrayList<Personajes1> personajes = new ArrayList<Personajes1>();

        if (modoAleatorio) {
            personajes.addAll(getFantasmasExpertosSegundoTurnoAleatorio());
        } else {
            personajes.addAll(getFantasmasExpertosSegundoTurnoManual());
        }
        return personajes;
    }
    
    //Personajes dificultad genius por modo segundo turno
    public static ArrayList<Personajes1> getFantasmasGeniusSegundoTurno(boolean modoAleatorio) {
        ArrayList<Personajes1> personajes = new ArrayList<Personajes1>();

        if (modoAleatorio) {
            personajes.addAll(getFantasmasGeniusSegundoTurnoAleatorio());
        } else {
            personajes.addAll(getFantasmasGeniusSegundoTurnoManual());
        }
        return personajes;
    }
    //Dificultades
    //Primer turno dificultad normal modo manual
    private static ArrayList<Personajes1> getFantasmasNormalesPrimerTurnoManual() {
        ArrayList<Personajes1> personajes = new ArrayList<Personajes1>();
        //8 fantasmas
        personajes.add(new Personajes1("Bueno", true, true, null));
        personajes.add(new Personajes1("Malo", false, true, null));
        personajes.add(new Personajes1("Bueno", true, true, null));
        personajes.add(new Personajes1("Malo", false, true, null));
        personajes.add(new Personajes1("Bueno", true, true, null));
        personajes.add(new Personajes1("Malo", false, true, null));
        personajes.add(new Personajes1("Bueno", true, true, null));
        personajes.add(new Personajes1("Malo", false, true, null));

        return personajes;
    }
    //Primer turno dificultad normal modo aleatorio
    private static ArrayList<Personajes1> getFantasmasNormalesPrimerTurnoAleatorio() {
        ArrayList<Personajes1> personajes = new ArrayList<Personajes1>();
        // 8 fantasmas
        personajes.add(new Personajes1("Bueno1", true, true, null));
        personajes.add(new Personajes1("Malo1", false, true, null));
        personajes.add(new Personajes1("Bueno1", true, true, null));
        personajes.add(new Personajes1("Malo1", false, true, null));
        personajes.add(new Personajes1("Bueno1", true, true, null));
        personajes.add(new Personajes1("Malo1", false, true, null));
        personajes.add(new Personajes1("Bueno1", true, true, null));
        personajes.add(new Personajes1("Malo1", false, true, null));

        return personajes;
    }
    //Primer turno dificultad expert modo manual
    private static ArrayList<Personajes1> getFantasmasExpertosPrimerTurnoManual() {
        ArrayList<Personajes1> personajes = new ArrayList<Personajes1>();
       //4 fantasmas
        personajes.add(new Personajes1("Bueno", true, true, null));
        personajes.add(new Personajes1("Malo", false, true, null));
        personajes.add(new Personajes1("Bueno", true, true, null));
        personajes.add(new Personajes1("Malo", false, true, null));

        return personajes;
    }
    //Primer turno dificultad expert modo aleatorio
     private static ArrayList<Personajes1> getFantasmasExpertosPrimerTurnoAleatorio() {
        ArrayList<Personajes1> personajes = new ArrayList<Personajes1>();
        // 4 fantasmas
        personajes.add(new Personajes1("Bueno1", true, true, null));
        personajes.add(new Personajes1("Malo1", false, true, null));
        personajes.add(new Personajes1("Bueno1", true, true, null));
        personajes.add(new Personajes1("Malo1", false, true, null));

        return personajes;
    }
     //Primer turno dificultad genius modo manual
    private static ArrayList<Personajes1> getFantasmasGeniusPrimerTurnoManual() {
        ArrayList<Personajes1> personajes = new ArrayList<Personajes1>();
         String[] nombresFantasmasTrampa= {"Trampa", "Trampa", "Trampa", "Trampa"};
        // 2 fantasmas fijos
        personajes.add(new Personajes1("Bueno", true, true, null));
        personajes.add(new Personajes1("Malo", false, true, null));

        // Generar 4 fantasmas aleatorios trampa
        Random random = new Random();
        for (int i = 0; i < 4; i++) {
            String nombreFantasmaMalo = nombresFantasmasTrampa[random.nextInt(nombresFantasmasTrampa.length)];
            // 
            while (nombreFantasmaMalo.equals("FantasmaBueno1") || nombreFantasmaMalo.equals("FantasmaMalo1")) {
                nombreFantasmaMalo = nombresFantasmasTrampa[random.nextInt(nombresFantasmasTrampa.length)];
            }
            personajes.add(new Personajes1(nombreFantasmaMalo, true, true, null));
        }
        
        return personajes;
    }
    //Primer turno dificultad geius modo aleatorio
     private static ArrayList<Personajes1> getFantasmasGeniusPrimerTurnoAleatorio() {
        ArrayList<Personajes1> personajes = new ArrayList<Personajes1>();
         String[] nombresFantasmasTrampa= {"Trampa", "Trampa", "Trampa", "Trampa"};
        // 2 fantasmas fijos
        personajes.add(new Personajes1("Bueno1", true, true, null));
        personajes.add(new Personajes1("Malo1", false, true, null));

        // Generar 4 fantasmas aleatorios trampa
        Random random = new Random();
        for (int i = 0; i < 4; i++) {
            String nombreFantasmaMalo = nombresFantasmasTrampa[random.nextInt(nombresFantasmasTrampa.length)];
            // Asegurarse de que el nombre generado sea diferente al fantasma bueno
            while (nombreFantasmaMalo.equals("FantasmaBueno1") || nombreFantasmaMalo.equals("FantasmaMalo1")) {
                nombreFantasmaMalo = nombresFantasmasTrampa[random.nextInt(nombresFantasmasTrampa.length)];
            }
            personajes.add(new Personajes1(nombreFantasmaMalo, true, true, null));
        }
        
        return personajes;
    }
     //Segundo Turno dificultad normal modo manual
    private static ArrayList<Personajes1> getFantasmasNormalesSegundoTurnoManual() {
        ArrayList<Personajes1> personajes = new ArrayList<Personajes1>();
        //8 fantasmas
        personajes.add(new Personajes1("Bueno", true, false, null));
        personajes.add(new Personajes1("Malo", false, false, null));
        personajes.add(new Personajes1("Bueno", true, false, null));
        personajes.add(new Personajes1("Malo", false, false, null));
        personajes.add(new Personajes1("Bueno", true, false, null));
        personajes.add(new Personajes1("Malo", false, false, null));
        personajes.add(new Personajes1("Bueno", true, false, null));
        personajes.add(new Personajes1("Malo", false, false, null));

        return personajes;
    }
    //Segundo Turno dificultad normal modo aleatorio
    private static ArrayList<Personajes1> getFantasmasNormalesSegundoTurnoAleatorio() {
        ArrayList<Personajes1> personajes = new ArrayList<Personajes1>();
        // 8 fantasmas
        personajes.add(new Personajes1("Bueno1", true, false, null));
        personajes.add(new Personajes1("Malo1", false, false, null));
        personajes.add(new Personajes1("Bueno1", true, false, null));
        personajes.add(new Personajes1("Malo1", false, false, null));
        personajes.add(new Personajes1("Bueno1", true, false, null));
        personajes.add(new Personajes1("Malo1", false, false, null));
        personajes.add(new Personajes1("Bueno1", true, false, null));
        personajes.add(new Personajes1("Malo1", false, false, null));

        return personajes;
    }
    //Segundo turno dificultad expert modo manual 
    private static ArrayList<Personajes1> getFantasmasExpertosSegundoTurnoManual() {
        ArrayList<Personajes1> personajes = new ArrayList<Personajes1>();
        // 4 fantasmas
        personajes.add(new Personajes1("Bueno", true, false, null));
        personajes.add(new Personajes1("Malo", false, false, null));
        personajes.add(new Personajes1("Bueno", true, false, null));
        personajes.add(new Personajes1("Malo", false, false, null));

        return personajes;
    }
    //Segundo turno dificultad expert modo aleatorio
    private static ArrayList<Personajes1> getFantasmasExpertosSegundoTurnoAleatorio() {
        ArrayList<Personajes1> personajes = new ArrayList<Personajes1>();
        // 4 fantasmas
        personajes.add(new Personajes1("Bueno1", true, false, null));
        personajes.add(new Personajes1("Malo1", false, false, null));
        personajes.add(new Personajes1("Bueno1", true, false, null));
        personajes.add(new Personajes1("Malo1", false, false, null));

        return personajes;
    }
    //Segundo turno dificultad genius modo manual
    private static ArrayList<Personajes1> getFantasmasGeniusSegundoTurnoManual() {
        ArrayList<Personajes1> personajes = new ArrayList<Personajes1>();
        String[] nombresFantasmasTrampa= {"Trampa", "Trampa", "Trampa", "Trampa"};
        // 2 fantasmas fijos
        personajes.add(new Personajes1("Bueno", true, false, null));
        personajes.add(new Personajes1("Malo", false, false, null));
        // Generar 4 fantasmas aleatorios trampa
        Random random = new Random();
        for (int i = 0; i < 4; i++) {
            String nombreFantasmaMalo = nombresFantasmasTrampa[random.nextInt(nombresFantasmasTrampa.length)];
            //
            while (nombreFantasmaMalo.equals("FantasmaBueno1") || nombreFantasmaMalo.equals("FantasmaMalo1")) {
                nombreFantasmaMalo = nombresFantasmasTrampa[random.nextInt(nombresFantasmasTrampa.length)];
            }
            personajes.add(new Personajes1(nombreFantasmaMalo, true, false, null));
        }
        return personajes;
    }
    //Segundo turno dificultad genius modo aleatorio
    private static ArrayList<Personajes1> getFantasmasGeniusSegundoTurnoAleatorio() {
        ArrayList<Personajes1> personajes = new ArrayList<Personajes1>();
        String[] nombresFantasmasTrampa= {"Trampa", "Trampa", "Trampa", "Trampa"};
        // 2 fantasmas fijos
        personajes.add(new Personajes1("Bueno1", true, false, null));
        personajes.add(new Personajes1("Malo1", false, false, null));
        // Generar 4 fantasmas aleatorios trampa
        Random random = new Random();
        for (int i = 0; i < 4; i++) {
            String nombreFantasmaMalo = nombresFantasmasTrampa[random.nextInt(nombresFantasmasTrampa.length)];
            //
            while (nombreFantasmaMalo.equals("FantasmaBueno1") || nombreFantasmaMalo.equals("FantasmaMalo1")) {
                nombreFantasmaMalo = nombresFantasmasTrampa[random.nextInt(nombresFantasmasTrampa.length)];
            }
            personajes.add(new Personajes1(nombreFantasmaMalo, true, false, null));
        }
        return personajes;
    }
       public String toString() {
        return nombrePersonaje;
    }
}
