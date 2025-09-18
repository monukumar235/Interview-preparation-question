package multithreading;

public class FibonacciQuestion {
    public static int fib(int n){
        if(n==0){
            return 0;
        }
        if(n==1){
            return 1;
        }
        return fib(n-1) + fib(n-2);
    }

    public static long fibopt(long n){
        if(n==0){
            return 0;
        }
        if(n==1){
            return 1;
        }
        if(t[(int)n]!=null) return t[(int)n];

        t[(int)n] = fibopt((int)n-1) + fibopt((int)n-2);
        return t[(int)n];
    }
    public static Long[]t;
    public static void main(String[] args) {
        long n=50;
        t = new Long[(int)n+1];
        for(long i=0;i<=n;i++){
            System.out.print(fibopt(i) + " ");
        }
    }

//    tc : 2pow(n)
}
