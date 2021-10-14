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

    public String getSaludo(){
        return this.textoSaludo;
    }
    public String getMuerte(){
        return this.textoMuerte;
    }
    public int getVida(){
        return this.vida;
    }
    public int getAtaque(){
        return this.ataque;
    }
    public void modificarAtaque(int cantidad){
        this.ataque += cantidad;
    }
    public void bajarVida(int cantidad){
        this.vida -= cantidad;
    }
    public void subirVida(int cantidad){
        this.vida += cantidad;
    }

    public void atacar(Combatientes objetivo){
        objetivo.bajarVida(this.ataque);
    }
}
