public class Main {
    public static void main(String[] args) {
        int NUM_PESSOAS = 5;

        Banheiro banheiro = new Banheiro(2);
        Pessoa pessoas[] = new Pessoa[NUM_PESSOAS];

        for (int i = 0; i < NUM_PESSOAS; i++) {
            Character s;
            if(i % 2 == 0){
                s = 'M';
            } else {
                s = 'F';
            }
            pessoas[i] = new Pessoa("Pessoa " + (i+1), s, banheiro);
        }

        for (int i = 0; i < NUM_PESSOAS; i++) {
            pessoas[i].start();
        }

        try {
            for (int i = 0; i < NUM_PESSOAS; i++) {
                pessoas[i].join();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}