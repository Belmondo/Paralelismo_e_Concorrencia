import java.util.ArrayList;
import java.util.Collection;

class CalculaPrimo extends Thread{
    int valorInicial;
    int valorFinal;
    Collection<Long> primos;

    public CalculaPrimo(int valorInicial, int valorFinal, Collection<Long> primos){
        this.valorInicial = valorInicial;
        this.valorFinal = valorFinal;
        this.primos = primos;
    }

    @Override
    public void run(){
        for(long i =valorInicial; i<=valorFinal; i++){
            int primo=0;
            for(int j = 2; j < i; j++){
                if((i%j)==0){
                    primo++;
                    break;
                }
            }
            //PONTO IMPORTANTE
            if(primo == 0){
                synchronized (primos){
                    primos.add(i);
                }
            }

        }

        System.out.println(this.getName() + "terminou!");
    }
}



class main{
    public static void main(String [] args) {

        long tempoInicial = System.currentTimeMillis();
        //PONTO IMPORTANTE
        int numProcessadores = 2;
        //System.out.println(numProcessadores);

        int valorInicial = 1;
        int valorFinal = 100000;
        //lista de Threads
        Collection<CalculaPrimo> threads = new ArrayList<>();
        //lista de primos
        Collection<Long> primos = new ArrayList<>();

        //divisão do trabalho
        //PONTO IMPORTANTE
        int trabalho = valorFinal/valorInicial;
        for(int i = 1; i<=numProcessadores; i++){
            int trabalhoArredondado = Math.round(trabalho/numProcessadores);

            int fim = trabalhoArredondado * i;
            int ini = (fim-trabalhoArredondado) + 1;

            CalculaPrimo thread = new CalculaPrimo(ini,fim, primos);
            thread.setName("Thread" + i);
            threads.add(thread);
        }

        //inicio das threads
        for(CalculaPrimo calculaPrimo: threads){
            calculaPrimo.start();
        }

        //join das threads PONTO IMPORTANTE
        for (CalculaPrimo calculaPrimo: threads){
            try {
                calculaPrimo.join();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

        //impressão dos numeros
        for(Long primo:primos){
            System.out.println(primo);
        }

        //calculo do tempo
        long tempoFinal = System.currentTimeMillis();
        System.out.println("Tempo "+ (tempoFinal-tempoInicial));

    }

}