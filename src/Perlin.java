/**
 * Utilities for generating Perlin noise
 *
 * @author Lane Aasen <laneaasen@gmail.com>
 * @see [http://freespace.virgin.net/hugo.elias/models/m_perlin.htm](Noise Tutorial)
 *
 */

public class Perlin {
    public static final float P = 0.5f;
    public static final int OCTAVES = 1;

    public static float perlin2D(float x, float y) {
        float total = 0.0f;

        for (int i = 0; i < OCTAVES; i++) {
            //int frequency = (int) Math.pow(2, i);
            float frequency = 0.1f;
            //float amplitude = (float) Math.pow(P, i);
            float amplitude = 3;

            total += Noise2D.interpolate(x * frequency, y * frequency, Noise1D.primes[0]) * amplitude;
        }

        return total;
    }

    /**
     * Given two y values a and b which are 1 apart on the x axis, return the y value of the point that is x units from a on the x axis
     *
     * @param a y value of left most point
     * @param b y value of right most point
     * @param x offset of the point from a
     * @return
     */
    public static float cosine(float a, float b, float x) {
        float ft = x * (float) Math.PI;
        float f = (1.0f - (float) Math.cos(ft)) * 0.5f;

        return a*(1-f) + b*f;
    }
}