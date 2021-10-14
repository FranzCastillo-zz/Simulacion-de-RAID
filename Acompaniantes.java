public class Acompaniantes extends Combatientes{
    private Combatientes asociadoA;
    private Habilidades habilidad;
    
    public Acompaniantes(String nombre, Habilidades habilidad){
        super(nombre);
        this.habilidad = habilidad;
    }
    public void setHabilidad(Habilidades newhabilidad){
        this.habilidad  = newhabilidad;
    }
}
