void main() {
    float backPackVolume = 2500;
    int itemsCount = 100;
    int minItemSize = 10;
    int maxItemSize = 90;
    int iterations = 1000000;
    int bestIteration = 1;
    int cellToModify;

    HashMap<Integer,Float> allItems = new HashMap<>();
    Random r = new Random();

    int[] startingTable;
    int[] parent = new int[itemsCount];
    int[] child;

    float sumStarting = 0;
    float sumParent = 0;
    float sumChild;

    for(int i=0;i<itemsCount;i++){
        allItems.put(i, minItemSize + r.nextFloat() * (maxItemSize - minItemSize));
        parent[i] = ((int) (Math.random() * 2));
    }
    startingTable = parent.clone();

    for(int i=0;i<iterations;i++){
        child = parent.clone();
        cellToModify = r.nextInt(itemsCount);
        child[cellToModify] ^= 1;

        sumParent = 0f;
        sumChild = 0f;

        for (int j = 0; j < itemsCount; j++) {
            float itemValue = allItems.get(j);
            if (parent[j] == 1) sumParent += itemValue;
            if (child[j] == 1) sumChild += itemValue;
        }
        if(i==0) {
            sumStarting = sumParent;
        }

        boolean parentValid = sumParent <= backPackVolume;
        boolean childValid = sumChild <= backPackVolume;
        boolean shouldReplace = false;
        if (childValid && !parentValid) {
            shouldReplace = true;
        } else if (childValid && parentValid) {
            if (sumChild > sumParent) {
                shouldReplace = true;
            }
        } else if (!childValid && !parentValid) {
            if (sumChild < sumParent) {
                shouldReplace = true;
            }
        }
        if (shouldReplace) {
            parent = child;
            bestIteration = i;
        }
    }
    IO.println("Items:");
    IO.println(allItems);
    IO.println("Starting table: ");
    IO.println(Arrays.toString(startingTable));
    IO.println("Starting sum: " + sumStarting);
    IO.println("Ending table: ");
    IO.println(Arrays.toString(parent));
    IO.println("Ending sum: " + sumParent);
    IO.println("Best iteration: " + bestIteration);
    IO.println("Number of iterations: " + iterations);
}
