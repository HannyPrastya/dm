package no.ntnu.idi.dm.arm.apriori;

import java.util.*;

public class FkMinus1FKMinus1<V> extends BaseApriori<V> {

    public FkMinus1FKMinus1(List<ItemSet<V>> transactions) {
        super(transactions);
    }

    @Override
    public List<ItemSet<V>> aprioriGen(
            List<ItemSet<V>> frequentCandidatesKMinus1) {

        Collections.sort(frequentCandidatesKMinus1);
//        System.out.println("frequentCandidatesKMinus1" + frequentCandidatesKMinus1);
        List<ItemSet<V>> level_candidates = new ArrayList<ItemSet<V>>();
        for (int i = 0; i < frequentCandidatesKMinus1.size(); i++) {
            for (int j = 0; j < frequentCandidatesKMinus1.size(); j++) {
                ItemSet<V> c1 = frequentCandidatesKMinus1.get(i);
                ItemSet<V> c2 = frequentCandidatesKMinus1.get(j);
                if (c1.union(c2).size() == c1.size() + 1) {
                    ItemSet<V> candidate = c1.union(c2);
                    if (!level_candidates.contains(candidate))
                        level_candidates.add(candidate);
                    getAndCacheSupportForItemset(candidate);
                }
            }
        }
        return new LinkedList<ItemSet<V>>(level_candidates);
    }

}
