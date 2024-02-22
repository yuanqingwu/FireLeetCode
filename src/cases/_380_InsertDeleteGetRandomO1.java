package cases;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

import tag.Tag_Array;
import tag.Tag_Design;
import tag.Tag_HashTable;

/**
 * [380. O(1)
 * 时间插入、删除和获取随机元素](https://leetcode.cn/problems/insert-delete-getrandom-o1)
 * <p>
 * Implement the RandomizedSet class:
 * <p>
 * RandomizedSet() Initializes the RandomizedSet object.
 * <p>
 * bool insert(int val) Inserts an item val into the set if not present. Returns
 * true if the item was not present, false otherwise.
 * <p>
 * bool remove(int val) Removes an item val from the set if present. Returns
 * true if the item was present, false otherwise.
 * <p>
 * int getRandom() Returns a random element from the current set of elements
 * <p>
 * (it's guaranteed that at least one element exists when this method is
 * called). Each element must have the same probability of being returned.
 * You must implement the functions of the class such that each function works
 * in average O(1) time complexity.
 * 
 * <p>
 * 实现RandomizedSet 类：
 * 
 * RandomizedSet() 初始化 RandomizedSet 对象
 * bool insert(int val) 当元素 val 不存在时，向集合中插入该项，并返回 true ；否则，返回 false 。
 * bool remove(int val) 当元素 val 存在时，从集合中移除该项，并返回 true ；否则，返回 false 。
 * int getRandom() 随机返回现有集合中的一项（测试用例保证调用此方法时集合中至少存在一个元素）。每个元素应该有 相同的概率 被返回。
 * 你必须实现类的所有函数，并满足每个函数的 平均 时间复杂度为 O(1) 。
 * 
 * <p>
 * 
 * Example 1:
 * <p>
 * Input
 * ["RandomizedSet", "insert", "remove", "insert", "getRandom", "remove",
 * "insert", "getRandom"]
 * [[], [1], [2], [2], [], [1], [2], []]
 * <p>
 * Output
 * [null, true, false, true, 2, true, false, 2]
 * <p>
 * Explanation
 * <p>
 * RandomizedSet randomizedSet = new RandomizedSet();
 * <p>
 * randomizedSet.insert(1); // Inserts 1 to the set. Returns true as 1 was
 * inserted successfully.
 * <p>
 * randomizedSet.remove(2); // Returns false as 2 does not exist in the set.
 * <p>
 * randomizedSet.insert(2); // Inserts 2 to the set, returns true. Set now
 * contains [1,2].
 * <p>
 * randomizedSet.getRandom(); // getRandom() should return either 1 or 2
 * randomly.
 * <p>
 * randomizedSet.remove(1); // Removes 1 from the set, returns true. Set now
 * contains [2].
 * <p>
 * randomizedSet.insert(2); // 2 was already in the set, so return false.
 * <p>
 * randomizedSet.getRandom(); // Since 2 is the only number in the set,
 * getRandom() will always return 2.
 * <p>
 * 
 * Constraints:
 * <p>
 * -231 <= val <= 231 - 1
 * 
 * At most 2 * 105 calls will be made to insert, remove, and getRandom.
 * 
 * There will be at least one element in the data structure when getRandom is
 * called.
 */
@Tag_Array
@Tag_HashTable
@Tag_Design
public class _380_InsertDeleteGetRandomO1 extends BaseSolution {

    public static void main(String[] args) {
        _380_InsertDeleteGetRandomO1 solution = new _380_InsertDeleteGetRandomO1();
        solution.run();
    }

    @Override
    void solution() {
        RandomizedSet randomizedSet = new RandomizedSet();
        boolean res = randomizedSet.insert(1);
        assertEquals(true, res);
        res = randomizedSet.remove(2);
        assertEquals(false, res);
        res = randomizedSet.insert(2);
        assertEquals(true, res);
        int random = randomizedSet.getRandom();
        assertTrue(random == 1 || random == 2);
        res = randomizedSet.remove(1);
        assertEquals(true, res);
        res = randomizedSet.insert(2);
        assertEquals(false, res);
        random = randomizedSet.getRandom();
        assertEquals(2, random);
    }

    /**
     * 变长数组可以在 O(1) 的时间内完成获取随机元素操作，但是由于无法在 O(1) 的时间内判断元素是否存在，因此不能在
     * O(1) 的时间内完成插入和删除操作。哈希表可以在 O(1)
     * 的时间内完成插入和删除操作，但是由于无法根据下标定位到特定元素，因此不能在 O(1)
     * 的时间内完成获取随机元素操作。为了满足插入、删除和获取随机元素操作的时间复杂度都是
     * O(1)，需要将变长数组和哈希表结合，变长数组中存储元素，哈希表中存储每个元素在变长数组中的下标。
     */
    @Tag_Design
    class RandomizedSet {

        private HashMap<Integer, Integer> map;
        private List<Integer> list;
        private Random random;

        public RandomizedSet() {
            map = new HashMap<>();
            list = new ArrayList<>();
            random = new Random();
        }

        public boolean insert(int val) {
            if (map.get(val) != null) {
                return false;
            }
            list.add(val);
            map.put(val, list.size() - 1);
            return true;
        }

        public boolean remove(int val) {
            if (map.get(val) == null) {
                return false;
            }
            int index = map.get(val);
            int last = list.get(list.size() - 1);
            list.set(index, last);
            list.remove(list.size() - 1);
            map.put(last, index);
            map.remove(val);
            return true;
        }

        public int getRandom() {
            return list.get(random.nextInt(list.size()));
        }
    }

    /**
     * Your RandomizedSet object will be instantiated and called as such:
     * RandomizedSet obj = new RandomizedSet();
     * boolean param_1 = obj.insert(val);
     * boolean param_2 = obj.remove(val);
     * int param_3 = obj.getRandom();
     */

}
