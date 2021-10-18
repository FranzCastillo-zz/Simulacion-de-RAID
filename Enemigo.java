/*
    NOMBRE: Francisco Javier Castillo Cerna 21562
    NOMBRE DE LA CLASE: Enemigo.java
        Clase que modela al enemigo y los probee de utilizar el metodo abstracto de Habilidades
*/
public class Enemigo extends Combatientes{
    protected Habilidades habilidad;
    public Enemigo(String nombre){
        super(nombre);
    }
    
    /** 
     * @param objetivo El objetivo a quien se le utilizara la habilidad
     */
    public void usarHabilidad(Combatientes objetivo){
        habilidad.usar(objetivo);
    }
}
