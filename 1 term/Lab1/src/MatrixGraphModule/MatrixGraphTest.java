package MatrixGraphModule;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MatrixGraphTest {

    @Test
    void testAlgoritmsAndWorking() {
        {
            Integer[] arr = new Integer[] {6, 7, 8};
            MatrixGraph<Integer> matrixGraph = new MatrixGraph<Integer>(arr);
            matrixGraph.addGraphLine(0, 1);
            matrixGraph.addGraphLine(2, 1);
            matrixGraph.addGraphLine(2, 0);

            Assertions.assertTrue(matrixGraph.checkConnectivity(), "Checking connectivity");
            matrixGraph.removeGraphLine(2,1);

            Assertions.assertEquals(1, matrixGraph.getDistance(2, 0), "Testing getDistance");
            Assertions.assertEquals(1, matrixGraph.getDistance(0, 2), "Testing getDistance");

            Assertions.assertTrue(matrixGraph.checkConnectivity(), "Checking connectivity");

            matrixGraph.removeGraphLine(0,1);

            Assertions.assertFalse(matrixGraph.checkConnectivity(), "Checking connectivity");

            matrixGraph.removeVertex(1);
            Assertions.assertTrue(matrixGraph.checkConnectivity(), "Checking connectivity");

            Assertions.assertEquals(1, matrixGraph.getDistance(0, 1), "Testing getDistance");

            Assertions.assertEquals(6, matrixGraph.getVertexData(0), "Checking Data");
            Assertions.assertEquals(8, matrixGraph.getVertexData(1), "Checking Data");

            matrixGraph.removeVertex(0);

            Assertions.assertEquals(8, matrixGraph.getVertexData(0), "Checking Data");
        }
        {
            String[] arr = new String[] {"Hello", "World", "!"};
            MatrixGraph<String> matrixGraph = new MatrixGraph<String>(arr);
            matrixGraph.addGraphLine(0, 1);
            matrixGraph.addGraphLine(2, 1);
            matrixGraph.addGraphLine(2, 0);

            Assertions.assertTrue(matrixGraph.checkConnectivity(), "Checking connectivity");
            matrixGraph.removeGraphLine(2,1);

            Assertions.assertEquals(1, matrixGraph.getDistance(2, 0), "Testing getDistance");
            Assertions.assertEquals(1, matrixGraph.getDistance(0, 2), "Testing getDistance");

            Assertions.assertTrue(matrixGraph.checkConnectivity(), "Checking connectivity");

            matrixGraph.removeGraphLine(0,1);

            Assertions.assertFalse(matrixGraph.checkConnectivity(), "Checking connectivity");

            matrixGraph.removeVertex(1);
            Assertions.assertTrue(matrixGraph.checkConnectivity(), "Checking connectivity");

            Assertions.assertEquals(1, matrixGraph.getDistance(0, 1), "Testing getDistance");

            Assertions.assertEquals("Hello", matrixGraph.getVertexData(0), "Checking Data");
            Assertions.assertEquals("!", matrixGraph.getVertexData(1), "Checking Data");

            matrixGraph.removeVertex(0);

            Assertions.assertEquals("!", matrixGraph.getVertexData(0), "Checking Data");
        }

    }
}