/**
 * Utilities for generating Perlin noise
 *
 * @author Lane Aasen <laneaasen@gmail.com>
 * @see [http://freespace.virgin.net/hugo.elias/models/m_perlin.htm](Noise Tutorial)
 *
 */

//https://github.com/aaasen/voxel-party/tree/master/src/nexus/model/generators
//We viewed the noise generator for a black box for the most part

public class Perlin {
    public static final float P = 0.5f;
    public static final int OCTAVES = 1;

    //This is the only area we modified
    public static float perlin2D(float x, float y) {
        float total = 0.0f;

        for (int i = 0; i < OCTAVES; i++) {
            //These values can be changed for desired terrain gen
            //We selected these to work well
            float frequency = 0.05f;
            float amplitude = 4;

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