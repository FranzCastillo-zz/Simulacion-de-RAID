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
    public void setHabilidad(Habilidades newhabilidad){
        this.habilidad  = newhabilidad;
    }
    public Combatientes getAsociado(){
        return this.asociadoA;
    }
    public void usarHabilidad(Combatientes objetivo){
        this.habilidad.usar(objetivo);
    }
    public int getRondasMuerto(){
        return this.rondasMuerto;
    }
    public void setRondasMuerto(int newRondas){
        this.rondasMuerto =  newRondas;
    }
    public void regenerar(){
        this.vida = 75;
        this.ataque = 5;
        this.rondasMuerto = 0;
    }
    public boolean isDead(){
        if(this.rondasMuerto > 0){
            return true;
        }else{
            return false;
        }
    }
}
