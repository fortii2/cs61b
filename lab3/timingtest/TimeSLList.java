package timingtest;

import edu.princeton.cs.algs4.Stopwatch;

/**
 * Created by hug.
 */
public class TimeSLList {
    private static void printTimingTable(AList<Integer> Ns, AList<Double> times, AList<Integer> opCounts) {
        System.out.printf("%12s %12s %12s %12s\n", "N", "time (s)", "# ops", "microsec/op");
        System.out.printf("------------------------------------------------------------\n");
        for (int i = 0; i < Ns.size(); i += 1) {
            int N = Ns.get(i);
            double time = times.get(i);
            int opCount = opCounts.get(i);
            double timePerOp = time / opCount * 1e6;
            System.out.printf("%12d %12.2f %12d %12.2f\n", N, time, opCount, timePerOp);
        }
    }

    public static void main(String[] args) {
        timeGetLast();
    }

    public static void timeGetLast() {
        AList<Integer> Ns = new AList<>();
        AList<Double> times = new AList<>();
        AList<Integer> opCounts = new AList<>();

        int N = 1000, M = 10000;
        while (N <= 128000) {
            Ns.addLast(N);
            opCounts.addLast(M);

            SLList<Integer> lst = generateList(N);

            Stopwatch sw = new Stopwatch();
            doMTimes(M, lst);
            times.addLast(sw.elapsedTime());

            N *= 2;
        }

        printTimingTable(Ns, times, opCounts);
    }

    private static void doMTimes(int m, SLList<Integer> lst) {
        while (m >= 0) {
            lst.getLast();
            m--;
        }
    }

    private static SLList<Integer> generateList(int N) {
        SLList<Integer> lst = new SLList<>();
        for (int i = 0; i < N; i++) {
            lst.addFirst(0);
        }
        return lst;
    }

}
