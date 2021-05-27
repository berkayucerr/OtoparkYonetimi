package Otopark.Park;

public class Invoker {
    DoldurBosalt doldurBosalt;

    public void setCommand(DoldurBosalt doldurBosalt){
        this.doldurBosalt=doldurBosalt;
    }

    public void Execute(){
        doldurBosalt.Execute();
    }
}
