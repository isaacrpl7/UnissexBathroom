import java.util.ArrayList;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Banheiro {
    private ArrayList<Pessoa> pessoas;
    private Condition differentSex;
    private Condition isFull;
    private Lock lock;
    private int max_pessoas;
    private Character sexo;

    public Banheiro(int capacity){
        this.max_pessoas = capacity;
        pessoas = new ArrayList<Pessoa>();
        lock = new ReentrantLock(true);
        differentSex = lock.newCondition();
        isFull = lock.newCondition();
        sexo = 'N';
    }

    public void entrar(Pessoa pessoa) {
        lock.lock();
        try {
            while(sexo != 'N' && sexo != pessoa.getSexo()) {
                System.out.println(Thread.currentThread().getName() + " " + pessoa.getSexo() + " está esperando.");
                differentSex.await();
            }
            while (pessoas.size() == max_pessoas) {
                System.out.println("Banheiro está cheio. ");
                System.out.println(Thread.currentThread().getName() + " " + pessoa.getSexo() +
                        " suspended.\n");
                isFull.await();
            }

            pessoas.add(pessoa);
            if(sexo == 'N') {
                sexo = pessoa.getSexo();
            }
            System.out.println(Thread.currentThread().getName() +
                    " inserted " + pessoa.getSexo());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public void sair(Pessoa pessoa){
        lock.lock();
        try {
            int index = pessoas.indexOf(pessoa);
            Pessoa item = pessoas.remove(index);
            System.out.println(Thread.currentThread().getName() +
                    " saiu " + item.getSexo());
            if (pessoas.size() == 0) {
                System.out.println("Banheiro está vazio. ");
                sexo = 'N';
                differentSex.signalAll();
            }
            isFull.signal();
        } finally {
            lock.unlock();
        }
    }

}
