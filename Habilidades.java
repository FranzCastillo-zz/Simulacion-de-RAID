/*
    NOMBRE: Francisco Javier Castillo Cerna 21562
    NOMBRE DE LA CLASE: Chorizo.java
        Clase abstracta de habilidades que nos permite que todas las habilidades tengan su propia forma de usarse
*/

public abstract class Habilidades {
    protected String nombre;

    public abstract void usar(Combatientes objetivo);
}
