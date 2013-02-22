package no.ntnu.idi.dm.arm.apriori;

import java.util.ArrayList;
import java.util.List;

public class BruteForceApriori<V> extends BaseApriori<V> {

    private List<ItemSet<V>> single_candidates;

    public BruteForceApriori(List<ItemSet<V>> transactions) {
        super(transactions);
        single_candidates = new ArrayList<ItemSet<V>>();
    }

    @Override
    public void apriori(Double minSupport) {
        for (ItemSet<V> transaction : transactions) {
            for (V v : transaction.getItems()) {
                ItemSet<V> candidate = new ItemSet<V>(v);
                if (!single_candidates.contains(candidate))
                    single_candidates.add(candidate);
            }
        }

        removeInFrequent(single_candidates, minSupport);
        frequentItemSets.put(1, single_candidates);
        int levels = single_candidates.size();

        for (int k = 1; k < levels - 1; k++) {
            List<ItemSet<V>> level_candidates = new ArrayList<ItemSet<V>>();
            List<ItemSet<V>> kMinusSet = frequentItemSets.get(k);
            for (int i = 0; i < kMinusSet.size() - 1; i++) {
                for (int j = 0; j < kMinusSet.size() - 1; j++) {
                    if (i != j) {
                        ItemSet<V> c1 = kMinusSet.get(i);
                        ItemSet<V> c2 = kMinusSet.get(j);
                        ItemSet<V> candidate = c1.union(c2);
                        if (!level_candidates.contains(candidate))
                            level_candidates.add(candidate);
                    }
                }
            }
            removeInFrequent(level_candidates, minSupport);
            frequentItemSets.put(k + 1, level_candidates);
            System.out.println(frequentItemSets);
        }

        System.out.println();
    }

    private void removeInFrequent(List<ItemSet<V>> candidates, Double minSupport) {
        for (int i = 0; i < candidates.size() - 1; i++) {
            ItemSet<V> candidate = candidates.get(i);
            Double support = getAndCacheSupportForItemset(candidate);

            boolean isInFrequent = support < minSupport;
            if (isInFrequent) {
                candidates.remove(candidate);
//                System.out.println(candidates.get(i) + " removed(" + support + "<"+ minSupport + ")");
            }
        }
    }

}
