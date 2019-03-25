import java.util.ArrayList;

public class CentrosCuadrados {

    public ArrayList lista;
    public ArrayList listaNormalizados;
    private int x;
    private int n;

    public CentrosCuadrados(int x, int n) {
        this.x = x;
        this.n = n;
        lista = new ArrayList();
        listaNormalizados = new ArrayList();
        iniciar(x);

    }

    public void iniciar(int semilla){
        int generador;
        String noRandom = "";
        //Tabla
        generador = (int)Math.pow(semilla, 2);
        String s = "" + generador;

        if(s.length() < 9) {
            do{
                s = "0"+s;
            }while (s.length() < 9);
        }
        for (int i = 0; i < 8; i++){
            if( i == 3 || i == 4 || i == 5 || i == 6 ) {
                noRandom += s.charAt(i);
            }
        }
        double numNorm = Double.parseDouble(noRandom);
        numNorm = numNorm/10000;

        //Resultados de Tabla
        System.out.print(semilla);
        System.out.print("          ");
        System.out.print(generador);
        System.out.print("          ");
        System.out.print(noRandom);
        System.out.print("          ");
        System.out.print(numNorm);
        System.out.println();

        lista.add(Integer.parseInt(noRandom));
        listaNormalizados.add(numNorm);

        if(n > 1){
            n--;
            iniciar(Integer.parseInt(noRandom));
        }
    }


}//TerminaCentrosCuadrados

