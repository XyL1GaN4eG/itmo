import java.util.*;

public class Main {

    private static int[][] graph = {
            {0, 1, 1, 5, 3},
            {1, 0, 3, 1, 5},
            {1, 3, 0, 11, 1},
            {5, 1, 11, 0, 1},
            {3, 5, 1, 1, 0}
    };
    private static final int citiesCount = graph.length;
    private static final int populationSize = 4;
    private static final double mutationProbability = 0.01;
    private static Random random = new Random();

    private static class Way {
        List<Integer> sequence;

        public Way(List<Integer> sequence) {
            this.sequence = new ArrayList<>(sequence);
        }

        public int calculateDistance() {
            int distance = 0;
            for (int i = 1; i < citiesCount; i++) {
                distance += graph[sequence.get(i - 1)][sequence.get(i)];
            }
            distance += graph[sequence.get(citiesCount - 1)][sequence.get(0)];
            return distance;
        }
    }

    public static void main(String[] args) {
        List<Way> population = generateInitialPopulation();
        int currentPopulationNumber = 1;
        int populationCount = 3;

        while (true) {

            if (currentPopulationNumber == populationCount) {
                break;
            }

            List<Way> parents = chooseParents(population);
            List<Way> nextPopulation = createNextPopulation(parents);
            for (int i = 0; i < nextPopulation.size(); i++) {
                Way mutatedChild = mutate(nextPopulation.get(i));
                if (mutatedChild != null) {
                    nextPopulation.set(i, mutatedChild);
                }
            }

            population.addAll(nextPopulation);
            Collections.sort(population, Comparator.comparingInt(Way::calculateDistance));
            if (population.size() > populationSize) {
                population = population.subList(0, populationSize);
            }

            currentPopulationNumber++;
        }

        Way optimalWay = population.get(0);
        System.out.println("Optimal: " + optimalWay.sequence + " Length: " + optimalWay.calculateDistance());
    }

    private static List<Way> generateInitialPopulation() {
        List<Way> population = new ArrayList<>();
        List<Integer> cities = new ArrayList<>();
        for (int i = 0; i < citiesCount; i++) {
            cities.add(i);
        }
        Collections.shuffle(cities);
        do {
            population.add(new Way(new ArrayList<>(cities)));
        } while (nextPermutation(cities) && population.size() < populationSize);
        return population;
    }

    private static boolean nextPermutation(List<Integer> array) {
        int k = array.size() - 2;
        while (k >= 0 && array.get(k) >= array.get(k + 1)) {
            k--;
        }
        if (k == -1) {
            return false;
        }
        int l = array.size() - 1;
        while (array.get(k) >= array.get(l)) {
            l--;
        }
        Collections.swap(array, k, l);
        Collections.reverse(array.subList(k + 1, array.size()));
        return true;
    }

    private static List<Way> chooseParents(List<Way> population) {
        List<Way> parents = new ArrayList<>();
        List<Way> nonChosenWays = new ArrayList<>(population);
        double sumOfDistances = nonChosenWays.stream().mapToDouble(Way::calculateDistance).sum();
        List<Double> cumulativeProbabilities = new ArrayList<>();
        double cumulativeSum = 0;

        for (Way way : nonChosenWays) {
            double probability = way.calculateDistance() / sumOfDistances;
            cumulativeSum += probability;
            cumulativeProbabilities.add(cumulativeSum);
        }

        for (int i = 0; i < populationSize; i++) {
            double r = random.nextDouble() * (cumulativeProbabilities.get(cumulativeProbabilities.size() - 1));
            int chosenIndex = Collections.binarySearch(cumulativeProbabilities, r);
            if (chosenIndex < 0) {
                chosenIndex = -chosenIndex - 1;
            }
            if (chosenIndex >= nonChosenWays.size()) {
                chosenIndex = nonChosenWays.size() - 1;
            }
            parents.add(nonChosenWays.get(chosenIndex));
            nonChosenWays.remove(chosenIndex);
        }
        return parents;
    }


    private static List<Way> createNextPopulation(List<Way> parents) {
        List<Way> nextPopulation = new ArrayList<>();
        for (int i = 0; i < parents.size(); i += 2) {
            Way parent1 = parents.get(i);
            Way parent2 = parents.get((i + 1) % parents.size());
            int breakPoint1 = random.nextInt(citiesCount - 1) + 1;
            int breakPoint2;
            do {
                breakPoint2 = random.nextInt(citiesCount - 1) + 1;
            } while (breakPoint1 == breakPoint2);
            if (breakPoint2 < breakPoint1) {
                int temp = breakPoint1;
                breakPoint1 = breakPoint2;
                breakPoint2 = temp;
            }
            nextPopulation.addAll(crossover(parent1, parent2, breakPoint1, breakPoint2));
        }
        return nextPopulation;
    }

    private static List<Way> crossover(Way parent1, Way parent2, int breakPoint1, int breakPoint2) {
        List<Integer> child1Sequence = new ArrayList<>(Collections.nCopies(citiesCount, -1));
        List<Integer> child2Sequence = new ArrayList<>(Collections.nCopies(citiesCount, -1));
        Set<Integer> takenCities = new HashSet<>();

        for (int i = breakPoint1++; i < breakPoint2; i++) {
            child1Sequence.set(i, parent2.sequence.get(i));
            takenCities.add(parent2.sequence.get(i));
            child2Sequence.set(i, parent1.sequence.get(i));
        }

        fillRemainingCities(parent1.sequence, child1Sequence, takenCities, breakPoint1, breakPoint2);
        takenCities.clear();
        fillRemainingCities(parent2.sequence, child2Sequence, takenCities, breakPoint1, breakPoint2);

        return Arrays.asList(new Way(child1Sequence), new Way(child2Sequence));
    }

    private static void fillRemainingCities(List<Integer> parentSequence, List<Integer> childSequence, Set<Integer> takenCities, int breakPoint1, int breakPoint2) {
        int index = 0;
        for (int i = 0; i < breakPoint1; i++) {
            while (takenCities.contains(parentSequence.get(index))) {
                index = (index + 1) % citiesCount;
            }
            childSequence.set(i, parentSequence.get(index));
            takenCities.add(parentSequence.get(index));
        }
        for (int i = breakPoint2; i < citiesCount; i++) {
            while (takenCities.contains(parentSequence.get(index))) {
                index = (index + 1) % citiesCount;
            }
            childSequence.set(i, parentSequence.get(index));
            takenCities.add(parentSequence.get(index));
        }
    }

    private static Way mutate(Way way) {
        if (random.nextDouble() <= mutationProbability) {
            int index1 = random.nextInt(citiesCount);
            int index2;
            do {
                index2 = random.nextInt(citiesCount);
            } while (index1 == index2);
            Collections.swap(way.sequence, index1, index2);
            return new Way(way.sequence);
        }
        return null;
    }
}