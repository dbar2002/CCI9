// 8.13 You have a stack of n boxes, with widths wi
//, heights hi
//, and depths di
//. The boxes
//cannot be rotated and can only be stacked on top of one another if each box in the stack is strictly
//larger than the box above it in width, height, and depth. Implement a method to compute the
//height of the tallest possible stack. The height of a stack is the sum of the heights of each box.

import java.util.Arrays;

public class BoxStacking {

    public static int maxHeight(Box[] boxes) {
        // Generate all possible rotations of boxes (3 for each box).
        Box[] rotatedBoxes = generateRotations(boxes);

        // Sort the boxes in non-increasing order of base area (length * width).
        Arrays.sort(rotatedBoxes, (a, b) -> (b.length * b.width) - (a.length * a.width));

        int n = rotatedBoxes.length;
        int[] maxHeight = new int[n];

        for (int i = 0; i < n; i++) {
            maxHeight[i] = rotatedBoxes[i].height; // Initialize with the height of the current box.

            for (int j = 0; j < i; j++) {
                if (rotatedBoxes[i].length < rotatedBoxes[j].length && rotatedBoxes[i].width < rotatedBoxes[j].width) {
                    // If the current box can be placed on top of the previous box.
                    maxHeight[i] = Math.max(maxHeight[i], maxHeight[j] + rotatedBoxes[i].height);
                }
            }
        }

        int maxStackHeight = 0;
        for (int height : maxHeight) {
            maxStackHeight = Math.max(maxStackHeight, height);
        }

        return maxStackHeight;
    }

    public static Box[] generateRotations(Box[] boxes) {
        int n = boxes.length * 3;
        Box[] rotatedBoxes = new Box[n];
        int index = 0;

        for (int i = 0; i < boxes.length; i++) {
            Box original = boxes[i];
            rotatedBoxes[index++] = new Box(original.height, Math.max(original.length, original.width), Math.min(original.length, original.width));
            rotatedBoxes[index++] = new Box(original.width, Math.max(original.height, original.length), Math.min(original.height, original.length));
            rotatedBoxes[index++] = new Box(original.length, Math.max(original.width, original.height), Math.min(original.width, original.height));
        }

        return rotatedBoxes;
    }

    public static void main(String[] args) {
        Box[] boxes = {
                new Box(4, 6, 7),
                new Box(1, 2, 3),
                new Box(4, 5, 6),
                new Box(10, 12, 32)
        };

        int maxStackHeight = maxHeight(boxes);
        System.out.println("The maximum stack height is: " + maxStackHeight);
    }
}

class Box {
    int height;
    int length;
    int width;

    public Box(int height, int length, int width) {
        this.height = height;
        this.length = length;
        this.width = width;
    }
}
