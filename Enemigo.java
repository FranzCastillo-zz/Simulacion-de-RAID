public class Enemigo extends Combatientes{
    protected Habilidades habilidad;
    public Enemigo(String nombre){
        super(nombre);
    }
    public void usarHabilidad(Combatientes objetivo){
        habilidad.usar(objetivo);
    }
}
