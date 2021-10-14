public class PocionDebilitar extends Items{
    public PocionDebilitar(){
        this.nombre = "Pocion de Debilitar";
    }
    void usarItem(Combatientes receptor) {
        if(receptor.ataque > 5){
            receptor.modificarAtaque(-5);
        }else{
            receptor.ataque = 1;
        }
    }
}
