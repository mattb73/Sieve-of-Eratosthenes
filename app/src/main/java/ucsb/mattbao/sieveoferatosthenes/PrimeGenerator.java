package ucsb.mattbao.sieveoferatosthenes;

import java.util.ArrayList;

/**
 * Created by Bo "Matt" Bao on 4/21/2015.
 * This class uses the Sieve of Eratosthenes algorithm to generate
 * a list of prime numbers up to the upper limit given (inclusive).
 * Sieve of Eratosthenes:
 * http://en.wikipedia.org/wiki/Sieve_of_Eratosthenes
 *
 * Added a field called lowerLimit so that user can specify a range
 * of prime numbers generated they want to see: [lowerLimit, upperLimit].
 */
public class PrimeGenerator {
    private int upperLimit;
    private int lowerLimit;
    private boolean[] numberList;
    private ArrayList<Integer> primeList;

    // Constructor with no argument
    public PrimeGenerator() {
        this.upperLimit = 0;
        this.lowerLimit = 0;
        this.numberList = new boolean[upperLimit + 1];
        // 0 is not considered, mark as true (cross off).
        numberList[0] = true;
        this.primeList = new ArrayList<Integer>();
    }

    // Constructor with only upper limit
    public PrimeGenerator(int upperLimit) {
        this.upperLimit = upperLimit;
        this.lowerLimit = 0;
        this.numberList = new boolean[upperLimit + 1];
        // 0 and 1 are not considered, mark as true (cross off).
        numberList[0] = true;
        numberList[1] = true;
        this.primeList = new ArrayList<Integer>();
    }

    // Constructor with both upper and lower limit
    public PrimeGenerator(int lowerLimit, int upperLimit) {
        this.upperLimit = upperLimit;
        this.lowerLimit = lowerLimit;
        this.numberList = new boolean[upperLimit + 1];
        // 0 and 1 are not considered, mark as true (cross off).
        numberList[0] = true;
        numberList[1] = true;
        this.primeList = new ArrayList<Integer>();
    }

    // Returns the list of numbers as an ArrayList<Integer>
    public ArrayList<Integer> getPrimeList() {
        for (int i = 2; i <= Math.sqrt(this.upperLimit); i++) {
            if (!this.numberList[i]) { // If not marked
                for (int j = i * i; j <= this.upperLimit; j += i) {
                    if (!this.numberList[j]) { // If not marked
                        this.numberList[j] = true;
                    }
                }
            }
        }
        // Supports range: [lowerLimit, upperLimit]
        for (int i = this.lowerLimit; i <= this.upperLimit; i++) {
            if (!this.numberList[i]) { // If not marked true, is prime number
                primeList.add(i);
            }
        }
        return primeList;
    }

    // Returns numberList
    public boolean[] getNumberList(){
        return this.numberList;
    }
}
