/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto1;

import java.awt.Color;
import javax.swing.BorderFactory;
import javax.swing.JLabel;

/**
 *
 * @author Azalia
 */
public class ControladorCasillas implements EsconderCasilla {
    JLabel label;
    Personajes1 personajeActual;
    int row;
    int column;
    boolean selected = false;
    
    public ControladorCasillas(int row, int column, Personajes1 personajeActual) {        
        this.label = new JLabel();
        this.row = row;
        this.column = column;
        this.personajeActual = personajeActual;
        
        label.setBorder(BorderFactory.createLineBorder(Color.BLACK));
    }
       
    public int getRow() {
        return row;
    }

    public int getColumn() {
        return column;
    }
    
    public void setPersonaje(Personajes1 personaje) {
        personajeActual = personaje;
        
        if (personaje == null) {
            label.setText("");
            label.setIcon(null);
        } else {            
             if (personajeActual.icono != null) {
                label.setIcon(personajeActual.icono);
            }
             else {
                label.setText(personajeActual.nombrePersonaje);
            }
        }        
    }
    @Override
    public void esconderCasilla(boolean esconder) {
        if (esconder) {
            if (personajeActual.iconoEscondido != null) {
                label.setIcon(personajeActual.iconoEscondido);
                label.repaint();
            } else {
                label.setIcon(null);
                label.setText("???");
            }  
        } else {
            if (personajeActual.icono != null) {
                label.setIcon(personajeActual.icono);
            }
            else
                label.setText(personajeActual.nombrePersonaje);
        }
    } 
    
}
