package com.zugaldia.robocar.cv;

import org.bytedeco.javacpp.opencv_core;

import static org.junit.Assert.assertTrue;

/**
 * Created by antonio on 6/16/17.
 */

public class BaseTest {

  void drawHistogram(int[] histogram, opencv_core.Mat thresholdBinary, String output) {
    final double binSize = thresholdBinary.size().width() / LaneManager.HISTOGRAM_BINS;
    opencv_core.Scalar color = new opencv_core.Scalar(255, 255, 255, 0); // White

    for (int bin = 0; bin < histogram.length; bin++) {
      int x = (int) ((bin + 0.5) * binSize);
      LaneManager.drawLine(thresholdBinary,
          new opencv_core.Point(x, thresholdBinary.size().height()),
          new opencv_core.Point(x, (int) (thresholdBinary.size().height() - 0.1 * histogram[bin])),
          color, 5);
    }

    assertTrue(LaneManager.writeImage(output, thresholdBinary));
  }

  float[] getSampleFromPoints() {
    return new float[] {
        594, 451,
        685, 451,
        268, 677,
        1037, 677};
  }

  float[] getSampleToPoints(float[] fromPoints, int width, int height) {
    float offset_x = 100;
    float offset_y = offset_x * height / width;
    float[] toPoints = new float[] {
        fromPoints[4] + offset_x, offset_y,
        fromPoints[6] - offset_x, offset_y,
        fromPoints[4] + offset_x, height,
        fromPoints[6] - offset_x, height};
    return toPoints;
  }
}
