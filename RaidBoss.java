/*
    NOMBRE: Francisco Javier Castillo Cerna 21562
    NOMBRE DE LA CLASE: RaidBoss.java
        La clase que compone al Raid Boss, extiende de Jefe
*/

import java.util.ArrayList;

public class RaidBoss extends Jefe{
    private ArrayList<Enemigo> clones;
    private Habilidades habilidadParaClonar;
    public RaidBoss(){
        super("INFERNO: El devora-almas");
        this.vida = 150;
        this.ataque = 15;
        this.habilidad = new RobarVida(this);
        this.segundaHabilidad = new GolpeLetal(this);
        this.habilidadParaClonar = habilidad;
        clones = new ArrayList<>();
    }
    
    /** 
     * @return ArrayList<Enemigo> los clones actuales
     */
    public ArrayList<Enemigo> getClones(){
        return this.clones;
    }
    
    /** 
     * @param newClones la actualizacion al arreglo de Clones
     */
    public void setClones(ArrayList<Enemigo> newClones){
        this.clones = newClones;
    }
    
    /** 
     * @return Habilidades la nueva habilidad que se coloco
     */
    public Habilidades variar(){
        String nombreHabilidad = this.habilidadParaClonar.getClass().getName();
        if(nombreHabilidad.equals("RobarVida")){
            this.habilidadParaClonar = segundaHabilidad;
        }else{
            this.habilidadParaClonar = habilidad;
        }
        return this.habilidadParaClonar;
    }
    
    /** 
     * @return ArrayList<Enemigo> La actualizacion del arreglo de Clones
     */
    public ArrayList<Enemigo> liberar(){
        this.clones.clear();
        return this.clones;
    }
    
    /** 
     * @param objetivo A quien se clonara
     * @return ArrayList<Enemigo> La actualizacion del arreglo de clones
     */
    public ArrayList<Enemigo> clonar(Combatientes objetivo){
        if(objetivo.getClass().getName().equals("Acompaniantes")){
            Acompaniantes verificar = (Acompaniantes)objetivo;
            if(!verificar.isDead()){
                Enemigo nuevo = new Enemigo("Clon de " + objetivo.nombre);
                nuevo.vida = objetivo.vida;
                nuevo.ataque = objetivo.ataque;
                nuevo.habilidad = this.habilidadParaClonar;
                clones.add(nuevo);
            }
        }else{
            Enemigo nuevo = new Enemigo("Clon de " + objetivo.nombre);
            nuevo.vida = objetivo.vida;
            nuevo.ataque = objetivo.ataque;
            nuevo.habilidad = this.habilidadParaClonar;
            clones.add(nuevo);
        }
        return clones;
    }
}
