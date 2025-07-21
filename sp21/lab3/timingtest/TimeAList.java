package timingtest;

import edu.princeton.cs.algs4.Stopwatch;

/**
 * Created by hug.
 */
public class TimeAList {
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
        timeAListConstruction();
    }

    public static void timeAListConstruction() {
        AList<Integer> Ns = new AList<>();
        AList<Double> times = new AList<>();
        AList<Integer> opCounts = new AList<>();

        int N = 1000;
        while (N <= 128000) {
            doNthAddLast(Ns, N, opCounts, times);
            N *= 2;
        }

        printTimingTable(Ns, times, opCounts);
    }

    private static void doNthAddLast(AList<Integer> Ns, int N, AList<Integer> opCounts, AList<Double> times) {
        Ns.addLast(N);
        opCounts.addLast(N);

        Stopwatch sw = new Stopwatch();
        doNTimes(N);
        times.addLast(sw.elapsedTime());
    }

    private static void doNTimes(int n) {
        AList<Integer> target = new AList<>();
        while (n >= 0) {
            target.addLast(0);
            n--;
        }
    }
}
