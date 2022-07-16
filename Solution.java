
public class NumArray {

    SegmentTree segmentTree;

    public NumArray(int[] nums) {
        segmentTree = new SegmentTree(nums);
    }

    public void update(int index, int value) {
        segmentTree.update(index, value);
    }

    public int sumRange(int left, int right) {
        return segmentTree.sumRange(left, right);
    }

}

class SegmentTree {

    int[] segments;
    int inputSize;

    public SegmentTree(int[] nums) {
        inputSize = nums.length;
        segments = new int[2 * inputSize];

        for (int i = inputSize; i < 2 * inputSize; ++i) {
            segments[i] = nums[i - inputSize];
        }
        for (int i = inputSize - 1; i > 0; --i) {
            segments[i] = segments[2 * i] + segments[2 * i + 1];
        }
    }

    public void update(int index, int value) {
        index += inputSize;
        segments[index] = value;

        while (index > 0) {
            int left = (index % 2 == 0) ? index : (index - 1);
            int right = (index % 2 == 0) ? (index + 1) : index;

            segments[index / 2] = segments[left] + segments[right];
            index /= 2;
        }
    }

    public int sumRange(int left, int right) {

        int sum = 0;
        left += inputSize;
        right += inputSize;

        while (left <= right) {
            if (left % 2 == 1) {
                sum += segments[left];
                ++left;
            }
            if (right % 2 == 0) {
                sum += segments[right];
                --right;
            }

            left /= 2;
            right /= 2;
        }

        return sum;
    }
}
