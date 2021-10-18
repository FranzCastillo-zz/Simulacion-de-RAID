/*
    NOMBRE: Francisco Javier Castillo Cerna 21562
    NOMBRE DE LA CLASE: Jefe.java
        Clase que modela los jefes del juego, heredando de enemigos
*/
public class Jefe extends Enemigo{
    protected Habilidades segundaHabilidad;
    public Jefe(String nombre) {
        super(nombre);
    }
    
    /** 
     * @param objetivo contra quien se utilizara la segunda habilidad
     */
    public void usarSegundaHabilidad(Combatientes objetivo){
        habilidad.usar(objetivo);
    }
}
