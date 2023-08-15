/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto1;

/**
 *
 * @author Azalia
 */
public abstract class Usuarios1 {
     public String username,password;
    public int puntos;
    
    public Usuarios1(String username,String password){
       this.username=username;
       this.password=password;
       this.puntos=0;
       
    }
    
    public void setUsername(String username){
        this.username=username;
    }
    
    public String getUsername(){
        return username;
    }
    
    public void setPassword(String password){
        this.password=password;
    }
    
    public String getPassword(){
        return password;
    }
    
    public String infoCreatePalayer(){
        return "\nUsername: "+username;  
    }
    
    public int getPuntos() {
        return puntos;
    }

   //Funcion abstract para puntos  
    public abstract void IncrementarPuntos(int cantidad);
    
}
