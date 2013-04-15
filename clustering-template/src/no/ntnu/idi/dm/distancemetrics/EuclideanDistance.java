package no.ntnu.idi.dm.distancemetrics;

/**
 * Implements the L2 or Euclidean metric.
 * 
 */
public class EuclideanDistance {

	public double distance(double[] vector1, double[] vector2) {
		double dist = 0d;

        for (int i=0; i < vector1.length; i++) {
            dist = dist + Math.pow((vector1[i] - vector2[i]), 2);
        }
		return Math.sqrt(dist);
	}

}
