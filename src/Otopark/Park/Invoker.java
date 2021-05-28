package Otopark.Park;

public class Invoker {
    private DoldurBosalt doldurBosalt;

    public void setCommand(DoldurBosalt doldurBosalt){
        this.doldurBosalt=doldurBosalt;
    }

    public void Execute(){
        doldurBosalt.Execute();
    }
}
