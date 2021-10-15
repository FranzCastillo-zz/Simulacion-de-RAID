public class K70 extends Enemigo{
    public K70(){
        super("K-70");
        this.vida = 120;
        this.ataque = 8;
        this.habilidad = new GolpeLetal(this);
    }
}
