/*
    NOMBRE: Francisco Javier Castillo Cerna 21562
    NOMBRE DE LA CLASE: Combatientes.java
        Clase que modela todos los objetos que interactuan en la batalla
*/

public class Combatientes {
    protected String nombre;
    protected int vida;
    protected int ataque;
    protected String textoSaludo;
    protected String textoMuerte;

    public Combatientes(String nombre){
        this.nombre = nombre;
        this.textoSaludo = "Narrador: "+ nombre + " ha entrado a la batalla!";
        this.textoMuerte = "Narrador: ESO DOLIO! " + nombre + " ha muerto!";
    }

    
    /** 
     * @return String el texto de ingreso a la batalla
     */
    public String getSaludo(){
        return this.textoSaludo;
    }
    
    /** 
     * @return String el texto de salida de la batalla
     */
    public String getMuerte(){
        return this.textoMuerte;
    }
    
    /** 
     * @return int la vida de este combatiente
     */
    public int getVida(){
        return this.vida;
    }
    
    /** 
     * @return int el ataque de este combatiente
     */
    public int getAtaque(){
        return this.ataque;
    }
    
    /** 
     * @param cantidad la nueva cantidad de ataque a colocar
     */
    public void modificarAtaque(int cantidad){
        this.ataque += cantidad;
    }
    
    /** 
     * @param cantidad la cantidad de vida que se le bajara
     */
    public void bajarVida(int cantidad){
        this.vida -= cantidad;
    }
    
    /** 
     * @param cantidad la cantidad de vida que se le subira
     */
    public void subirVida(int cantidad){
        this.vida += cantidad;
    }

    
    /** 
     * @param objetivo a quien atacara
     */
    public void atacar(Combatientes objetivo){
        objetivo.bajarVida(this.ataque);
    }
}
