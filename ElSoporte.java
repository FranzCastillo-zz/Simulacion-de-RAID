public class ElSoporte extends Habilidades{
    public ElSoporte(){
        this.nombre = "El Soporte";
    }
    public void usar(Combatientes objetivo) {
        objetivo.subirVida(5);
    }
}
