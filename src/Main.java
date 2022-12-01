public class Main {
    public static Character getRandomSex() {
        int random = (int) ((Math.random() * (3 - 1)) + 1);

        if(random == 1){
            return 'M';
        } else {
            return 'F';
        }
    }
    public static void main(String[] args) {
        int NUM_PESSOAS = Integer.parseInt(args[0]);
        int NUM_BANHEIROS = Integer.parseInt(args[1]);

        Banheiro banheiro = new Banheiro(NUM_BANHEIROS);
        Pessoa pessoas[] = new Pessoa[NUM_PESSOAS];

        for (int i = 0; i < NUM_PESSOAS; i++) {
            Character s;
            s = getRandomSex();

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