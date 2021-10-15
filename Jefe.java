public class Jefe extends Enemigo{
    protected Habilidades segundaHabilidad;
    public Jefe(String nombre) {
        super(nombre);
    }
    public void usarSegundaHabilidad(Combatientes objetivo){
        habilidad.usar(objetivo);
    }
}
