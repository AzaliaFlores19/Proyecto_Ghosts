/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto1;

/**
 *
 * @author Azalia
 */
public class MainJuego {
    
    public static Login1 login;
    public static Player datos;
    public static MenuInicio menuInicio;
    
    public static void main(String[] args){
        Player datos =new Player("","");
        Login1 login=new Login1(datos);
        MenuInicio menuInicio=new MenuInicio(login,datos);
        menuInicio.setVisible(true);
    }
    
}
