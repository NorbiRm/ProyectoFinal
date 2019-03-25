package Util;

import java.util.ArrayList;

public class Congruencial {

    public int x,a,c,m;
    public int i;

    public ArrayList numerosGenerados;
    public ArrayList<Double> normalizados = new ArrayList<Double>();
    public ArrayList<Congruencial> congruenciales = new ArrayList<Congruencial>();

    public Congruencial() {
        i = 0;
        numerosGenerados = new ArrayList();
    }

    public Congruencial(int x,int a,int c,int m) {
        this.x  = x;
        this.a  = a;
        this.c  = c;
        this.m  = m;
        i = 0;
        numerosGenerados = new ArrayList();
    }
    public Congruencial(int x,int a,int m) {
        this.x  = x;
        this.a  = a;
        this.m  = m;
        i = 0;
        numerosGenerados = new ArrayList();
    }

    public double lineal(){
        x = ((a*x)+c)%m;
        return x;
    }
    public double multiplicativo(){
        x = (a*x)%m;
        return x;
    }

    public boolean hullDobel(){
        int q =  getQ(m,a);

        if (q == 0){ return false;}

        boolean fourCheck = (m%4==0 && (a-1)%4==0);
        int qmCheck = m%q;

        int qaCheck = (a-1)%q;


        if (Math.gcd(c,m)==1 && qmCheck==0 && qaCheck==0 && fourCheck) {
            return true;
        }
        else {
            return false;
        }
    }
    public int getQ(int m,int a) {

        for (int i = 2; i<=m ; i++){
            if (Math.isPrime(i) && (m%i == 0) && ((a - 1) % i == 0)){
                //Found q
//                System.out.println("Found q : "+i);
                return i;
            }
        }

        //System.out.println("Found q : "+i);

        return 0;
    }

    public ArrayList generarNumerosLineal(int n){
        numerosGenerados = new ArrayList();
        for (int i  = 0; i<n ;i++){
            numerosGenerados.add(lineal());
        }
        numerosNormalizados();
        return numerosGenerados;
    }

    public ArrayList generarNumerosMultiplicativo(int n){
        numerosGenerados = new ArrayList();
        for (int i  = 0; i<n ;i++){
            numerosGenerados.add(multiplicativo());
        }
        numerosNormalizados();
        return numerosGenerados;
    }

    public ArrayList numerosNormalizados(){
        ArrayList normalizados = new ArrayList();
        for (int i  = 0; i<numerosGenerados.size() ;i++){
            normalizados.add(Double.parseDouble(numerosGenerados.get(i).toString())/m);
        }
        this.normalizados = normalizados;
        return normalizados;
    }

    public void addCongruencial(int xi,int a,int m){
        congruenciales.add(new Congruencial(xi,a,m));
    }

    public ArrayList congruencialMixto(int n){

        int gen = 0;
        int sum = 0 ;

        for (int i=0;i<n;i++){
            for (int j = 1 ; j<=congruenciales.size(); j++){
                sum +=  Math.pow(-1,j-1)*(int)congruenciales.get(j-1).multiplicativo();
            }
            gen = sum % (congruenciales.get(0).m-1);
            numerosGenerados.add(gen);
            normalizados.add((double)gen/congruenciales.get(0).m);
            sum = 0;
        }

        return numerosGenerados;
    }

    public int lecuyer(){
        int  p = 1;

        for(int i =0; i<congruenciales.size();i++){
            p*=congruenciales.get(i).m-1;
        }
        p = (int) (p/(Math.pow(2,(congruenciales.size()-1))));
        return p;
    }
}
