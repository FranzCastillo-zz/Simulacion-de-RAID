/*
    NOMBRE: Francisco Javier Castillo Cerna 21562
    NOMBRE DE LA CLASE: PocionDebilitar.java
        Clase que modela el item "PoionCuracion" e implementa su uso
*/
public class PocionDebilitar extends Items{
    public PocionDebilitar(){
        this.nombre = "Pocion de Debilitar";
    }
    
    /** 
     * @param receptor a quien se le reducira en 5pts el ataque
     */
    void usarItem(Combatientes receptor) {
        if(receptor.ataque > 5){
            receptor.modificarAtaque(-5);
        }else{
            receptor.ataque = 1;
        }
    }
}
