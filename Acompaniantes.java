/*
    NOMBRE: Francisco Javier Castillo Cerna 21562
    NOMBRE DE LA CLASE: Acompaniantes.java
        Clase que modela los acompaniantes de cazador y los clones del Raid Boss
*/

public class Acompaniantes extends Combatientes{
    private Combatientes asociadoA;
    private Habilidades habilidad;
    private int rondasMuerto;
    
    public Acompaniantes(String nombre, Combatientes socio){
        super(nombre);
        this.vida = 10; //75
        this.ataque = 5;
        this.asociadoA = socio;
        this.rondasMuerto = 0;
    }
    
    /** 
     * @param newhabilidad la nueva Habilidad que se colocara
     */
    public void setHabilidad(Habilidades newhabilidad){
        this.habilidad  = newhabilidad;
    }
    
    /** 
     * @return Combatientes el asociado a esta mascota
     */
    public Combatientes getAsociado(){
        return this.asociadoA;
    }
    
    /** 
     * @param objetivo a quien se le aplicara la habilidad
     */
    public void usarHabilidad(Combatientes objetivo){
        this.habilidad.usar(objetivo);
    }
    
    /** 
     * @return int las rondas que debe estar muerto aun
     */
    public int getRondasMuerto(){
        return this.rondasMuerto;
    }
    
    /** 
     * @param newRondas las rondas por las cuales estara en "cooldown" esta mascota
     */
    public void setRondasMuerto(int newRondas){
        this.rondasMuerto =  newRondas;
    }
    public void regenerar(){
        this.vida = 75;
        this.ataque = 5;
        this.rondasMuerto = 0;
    }
    
    /** 
     * @return boolean Si esta muerrto (True) o no (False)
     */
    public boolean isDead(){
        if(this.rondasMuerto > 0){
            return true;
        }else{
            return false;
        }
    }
}
