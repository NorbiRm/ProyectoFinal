import org.apache.commons.math3.distribution.ChiSquaredDistribution;

import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.LinkedList;

public class Pruebas {

    public Pruebas() {
    }
    public boolean ChiCuadrada(double datos[], double alpha){
        double k;
        double tsize = 0;
        double max = 0;
        double min = datos[0];
        double rango = 0;

        int totalElementos = datos.length;
        LinkedList<Integer> f0 = new LinkedList<Integer>();
        LinkedList<Double> f0Relativa = new LinkedList<Double>();
        //obtener maximo
        for(int i = 0; i < datos.length; i++){
            if(datos[i] > max){
                max = datos[i];
            }
        }
        //Obtener minimo
        for(int i = 0; i< datos.length; i++){
            if(datos[i] < min){
                min = datos[i];
            }
        }
        // rango
        rango = max - min;

        //Seleccionar numero de clases

        k = Math.floor(1 + 3.322 * Math.log10(totalElementos));
        System.out.println("k es "+k);
        DecimalFormat df = new DecimalFormat("#.00");
        tsize = rango/k;




        System.out.println("El valor max es " + max);
        System.out.println("El valor min es " + min);
        System.out.println("El valor de K es "+k);
        System.out.println("El valor de tsize es "+tsize);

        //Tabla de clases
        double cont = 0;
        double auxTabla2 = 0;
        double teorica=0;
        double auxlimit = 0;
        double opFinal = 0;
        double resuFinal = 0;
        for(int i = 0; i < k; i++){//primer ciclo
            if(i == k-1){

            }
            for(int j = 0; j < datos.length; j++){//segundo ciclo
                if(datos[j] < tsize && datos[j] > auxlimit){
                    cont++;
                }
            }//Fin primer ciclo
            f0.add((int)cont);
            //Auxiliar para agregar lista double
            auxTabla2 = cont/totalElementos;
            f0Relativa.add(auxTabla2);
            cont = 0;
            auxlimit += rango/k;
            tsize += rango/k;

            teorica = 1/k;//No cambia

            opFinal = Math.pow((auxTabla2-teorica),2)/teorica;
            resuFinal += opFinal;
            //System.out.println(f0.size());
            //System.out.println(f0Relativa.size());
            //System.out.print("f0 es "+f0);
            //System.out.print("f0 Relativo es "+f0Relativa);
            System.out.println();

        }//fin segundo ciclo

        ChiSquaredDistribution t = new ChiSquaredDistribution(k-1);
        double ChiSquareValue = 0;
        ChiSquareValue=t.inverseCumulativeProbability(1-alpha);

        System.out.println("Resultado final es "+resuFinal);
        System.out.println("ChiSquare Value es "+ ChiSquareValue);

        if(ChiSquareValue > resuFinal){
            return true;
        }else{
            return false;
        }

        //ObtenerTeorica

    }//Termina Chicuadrada

    public static boolean Kolmogorov(double datos[], double alpha){
        //Ordenar array
        Arrays.sort(datos);
        LinkedList<Double> firstCo = new LinkedList<Double>();
        LinkedList<Double> secCo = new LinkedList<Double>();
        LinkedList<Double> thiCo = new LinkedList<Double>();
        double sizeArray = datos.length;
        System.out.println("Size array "+sizeArray);
        for(int i = 1; i<=datos.length; i++){
            firstCo.add(i/sizeArray);
        }
        for(int i = 1; i<=datos.length; i++){
            secCo.add(firstCo.get(i-1) - datos[i-1]);
            System.out.println(datos[i-1]);
        }
        for(int i = 0; i<datos.length; i++){
            if(i == 0){
                thiCo.add(datos[0]);
            }else {
                thiCo.add(datos[i] - firstCo.get(i-1));
            }
        }

        double maxSec = 0;
        double maxThi = 0;
        double maxFinal = 0;

        //Obtener maximo de i/n - f(xi)
        for(int i = 0; i < secCo.size(); i++){
            if(secCo.get(i)> maxSec){
                maxSec = secCo.get(i);
            }
        }
        //Obtener maximo de f(xi) - (i-1)/n
        for(int i = 0; i < thiCo.size(); i++){
            if(thiCo.get(i) > maxThi){
                maxThi = thiCo.get(i);
            }
        }
        maxFinal = Math.max(maxSec, maxThi);
        double maxTabla = 0;


        double KolmogorovTable[][] = {
                {0.9, 0.95, 0.975, 0.99, 0.995, 0.9975, 0.999, 0.99950},
                {0.68337, 0.77639, 0.84189, 0.9, 0.92929, 0.95, 0.96838, 0.97764},
                {0.56481, 0.63604, 0.70760, 0.78456, 0.829, 0.86428, 0.9, 0.92065},
                {0.49265, 0.56522, 0.62394, 0.68887, 0.73424, 0.77639, 0.82217, 0.85047},
                {0.44698, 0.50945, 0.56328, 0.62718, 0.66853, 0.70543, 0.75000, 0.78137},
                {0.41037, 0.46799, 0.51926, 0.57741, 0.61661, 0.65287, 0.69571, 0.72479},
                {0.38148, 0.43607, 0.48342, 0.53844, 0.57581, 0.60975, 0.65071, 0.67930},
                {0.35831, 0.40962, 0.45427, 0.50654, 0.54179, 0.57429, 0.61368, 0.64098},
                {0.33910, 0.38746, 0.43001, 0.47960, 0.51332, 0.54443, 0.58210, 0.60846},
                {0.32260, 0.36866, 0.40925, 0.45562, 0.48893, 0.51872, 0.55500, 0.58042},
                {0.30829, 0.35242, 0.39122, 0.43670, 0.46770, 0.49539, 0.53135, 0 - 55588},
                {0.29577, 0.33815, 0.37543, 0.41918, 0.44905, 0.47672, 0.51047, 0.53422},
                {0.28470, 0.32549, 0.36143, 0.40362, 0.43247, 0.45921, 0.49189, 0.51490},
                {0.27481, 0.31417, 0.34890, 0.38970, 0.41762, 0.44352, 0.47520, 0.49753},
                {0.26589, 0.30397, 0.33750, 0.37713, 0.40420, 0.42934, 0.45611, 0.48182},
                {0.25778, 0.29472, 0.32733, 0.36571, 0.39201, 0.41644, 0.44637, 0.46750},
                {0.25039, 0.28627, 0.31796, 0.35528, 0.38086, 0.40464, 0.43380, 0.45540},
                {23735, 0.27136, 0.30143, 0.33685, 0.36117, 0.38379, 0.41156, 0.43119},
                {0.23156, 0.26473, 0.29408, 0.32866, 0.35241, 0.37451, 0.40165, 0.42085},
                {0.22517, 0.25858, 0.28724, 0.32104, 0.34426, 0.36588, 0.39243, 0.41122},
                {0.22115, 0.25283, 0.28087, 0.31394, 0.33666, 0.35782, 0.38382, 0.40223},
                {0.21646, 0.24746, 0.27495, 0.30728, 0.32954, 0.35027, 0.37575, 0.39380},
                {0.21205, 0.24242, 0.26931, 0.30104, 0.32286, 0.34318, 0.36787, 0.38588},
                {0.20790, 0.23768, 0.26404, 0.29518, 0.31657, 0.33651, 0.36104, 0.37743},
                {0.20399, 0.23320, 0.25908, 0.28962, 0.30963, 0.33022, 0.35431, 0.37139},
                {0.20030, 0.22898, 0.25438, 0.28438, 0.30502, 0.32425, 0.34794, 0.36473},
                {0.19680, 0.22497, 0.24993, 0.27942, 0.29971, 0.31862, 0.34190, 0.35842},
                {0.19348, 0.22117, 0.24571, 0.27471, 0.29466, 0.31327, 0.33617, 0.35242},
                {0.19032, 0.21756, 0.24170, 0.27023, 0.28986, 0.30818, 0.33072, 0.34672},
                {0.18732, 0.21412, 0.23788, 0.26596, 0.28529, 0.30333, 0.32553, 0.34129},
                {0.18445, 0.21085, 0.23424, 0.26189, 0.28094, 0.29870, 0.32058, 0.33611},
                {0.18171, 0.20771, 0.23076, 0.25801, 0.27577, 0.29428, 0.31584, 0.33115},
                {0.17909, 0.21472, 0.22743, 0.25429, 0.27271, 0.29005, 0.31131, 0.32641},
                {0.17659, 0.20185, 0.22425, 0.25073, 0.26897, 0.28600, 0.30597, 0.32187},
                {0.17418, 0.19910, 0.22119, 0.24732, 0.26532, 0.28211, 0.30281, 0.31751},
                {0.17188, 0.19646, 0.21826, 0.24404, 0.26180, 0.27838, 0.29882, 0.31333},
                {0.16966, 0.19392, 0.21544, 0.24089, 0.25843, 0.27483, 0.29498, 0.30931},
                {0.16753, 0.19148, 0.21273, 0.23785, 0.25518, 0.27135, 0.29125, 0.30544},
                {0.16547, 0.18913, 0.21012, 0.23494, 0.25205, 0.26803, 0.28772, 0.30171},
                {0.16349, 0.18687, 0.20760, 0.23213, 0.24904, 0.26482, 0.28429, 0.29811},
                {0.16158, 0.18468, 0.20517, 0.22941, 0.24613, 0.26173, 0.28097, 0.29465},
                {0.15974, 0.18257, 0.20283, 0.22679, 0.24332, 0.25875, 0.27778, 0.29130},
                {0.15795, 0.18051, 0.20056, 0.22426, 0.24060, 0.25587, 0.27468, 0.28806},
                {0.15623, 0.17856, 0.19837, 0.22181, 0.23798, 0.25308, 0.27169, 0.28493},
                {0.15457, 0.17665, 0.19625, 0.21944, 0.23544, 0.25038, 0.26880, 0.28190},
                {0.15295, 0.17481, 0.19420, 0.21715, 0.23298, 0.24776, 0.26600, 0.27896},
                {0.15139, 0.17301, 0.19221, 0.21493, 0.23059, 0.24523, 0.26328, 0.27611},
                {0.14987, 0.17128, 0.19028, 0.21281, 0.22832, 0.24281, 0.26069, 0.27339},
                {0.14840, 0.16959, 0.18841, 0.21068, 0.22604, 0.24039, 0.25809, 0.27067}};

        //maxTabla = KolmogorovTable[(int)sizeArray][alpha];
        if (alpha == 0.20) {
            alpha = 0;
        } else if (alpha == 0.10) {
            alpha = 1;

        }else if (alpha == 0.05){
            alpha = 2;

        }else if (alpha == 0.02){
            alpha = 3;

        }else if (alpha == 0.01){
            alpha = 4;

        }else if (alpha == 0.005){
            alpha = 5;

        }else if (alpha == 0.002){
            alpha = 6;

        }else if (alpha == 0.001){
            alpha = 7;
        }else {
            System.out.println("El valor no es adecuado");
        }
        if(datos.length<=49)
            maxTabla = KolmogorovTable[(int)sizeArray-1][(int)alpha];
        else{
            switch ((int)alpha){
                case 0:
                    maxTabla = 1.07/Math.sqrt(datos.length);
                    break;
                case 1:
                    maxTabla = 1.22/Math.sqrt(datos.length);
                    break;
                case 2:
                    maxTabla = 1.36/Math.sqrt(datos.length);
                    break;
                case 3:
                    maxTabla = 1.52/Math.sqrt(datos.length);
                    break;
                case 4:
                    maxTabla = 1.63/Math.sqrt(datos.length);
                    break;
                case 5:
                    maxTabla = 1.73/Math.sqrt(datos.length);
                    break;
                case 6:
                    maxTabla = 1.85/Math.sqrt(datos.length);
                    break;
                case 7:
                    maxTabla = 1.95/Math.sqrt(datos.length);
                    break;
            }
        }

        System.out.println("El valor generado es "+maxFinal);
        System.out.println("El valor obtenido de la tabla es "+maxTabla);
        if(maxTabla >= maxFinal){
            return true;
        }else {
            return false;
        }
    }//Termina Kolmogorov

}//Termina Pruebas


