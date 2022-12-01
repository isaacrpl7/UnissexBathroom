import java.util.concurrent.TimeUnit;

public class Pessoa extends Thread {
    private Character sexo;
    private Banheiro banheiro;

    public Pessoa(String name, Character sexo, Banheiro banheiro){
        super(name);
        this.sexo = sexo;
        this.banheiro = banheiro;
    }

    public Character getSexo(){
        return sexo;
    }

    public int getRandomNumber(int min, int max) {
        return (int) ((Math.random() * (max - min)) + min);
    }

    @Override
    public void run() {
        banheiro.entrar(this);
        try {
            TimeUnit.SECONDS.sleep(getRandomNumber(1, 5));
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            banheiro.sair(this);
        }
    }
}
