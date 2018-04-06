/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Grafico;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

/**
 *
 * @author marco
 */
public class Assets {
    public static BufferedImage imp;
    public static ArrayList<BufferedImage>List=new ArrayList();
    public static void init(){
        for(int i=0; i<28;i++){
        imp=Loader.ImageLoader("/Imagenes/"+(i+1)+".png");
        List.add(imp);
        }
    }
}
