/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Grafico;


import Main.metodos;
import Main.Login;
import Main.Usuario;
import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import javax.swing.JFrame;

/**
 *
 * @author ADMIN
 */
public class Juego extends JFrame {
    Usuario Lista,Fin;
    Metodos met=new Metodos(); /*Todos los metods*/
    static final int width=1365,height=720; /*Tamano de la ventana*/
    /*Canvas y los grafics para dibujar*/
    Login log=new Login();
    metodos meto=new metodos();

    
    public Juego(Usuario Lista,Usuario Fin){
        this.Lista=Lista;
        this.Fin=Fin;
        setTitle("DOMINO");
        setSize(width,height); /*Tamano de la ventana*/
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); /*Permite cerrar la ventana con la X*/
        Partida canvas = new Partida(Lista,Fin); 
        add(canvas); /*Se agrega el canvas en este caso JUEGO*/
        setVisible(true); /*Hace Visible la Ventana*/
 
        
        
    }
    public void cerrartodo(){
        setVisible(false);
        Main.userUser F9= new Main.userUser(log,meto);
        F9.setVisible(true);
    }

    
   
    

    
}
